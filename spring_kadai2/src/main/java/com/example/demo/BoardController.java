package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

    // HttpSessionはコントローラーメソッドの引数で受け取るのが一般的（※後述）
    // ただし、Spring Bootの仕様でフィールドインジェクションも可能
    @Autowired
    HttpSession session;

    // 掲示板画面表示
    @GetMapping("/board")
    public String view() {
        return "board";
    }

    // セッションリセット
    @GetMapping("/reset")
    public String reset() {
        session.invalidate(); // セッションを破棄
        return "board";
    }

    // 投稿処理
    @PostMapping("/apply")
    public String apply(
            Model model,
            @RequestParam("name") String name,
            @RequestParam("feeling") String feeling,
            @RequestParam("contents") String contents) {

        // 名前または本文が空の場合、エラーメッセージを表示
        if (name.trim().isEmpty() || contents.trim().isEmpty()) {
            model.addAttribute("error", "名前と書き込みを入力してください");
            return "board";
        }

        // XSS対策：HTMLエスケープ
        String safeName = escapeHtml(name);
        String safeContents = escapeHtml(contents);

        // セッションから投稿リストを取得。なければ新規作成
        @SuppressWarnings("unchecked")
        List<Record> allContents = (List<Record>) session.getAttribute("contentsList");
        if (allContents == null) {
            allContents = new ArrayList<>();
            session.setAttribute("contentsList", allContents);
        }

        // URLを<a>タグで囲む
        safeContents = convertUrlsToLinks(safeContents);

        // 新しい投稿をリストに追加
        Record newRecord = new Record(safeName, feeling, safeContents);
        allContents.add(newRecord);

        // モデルに投稿リストをセット
        model.addAttribute("allContents", allContents);

        // デバッグ用：セッションの投稿リストをコンソール出力
        System.out.println(session.getAttribute("contentsList"));

        return "board";
    }

    // HTMLエスケープ処理
    public static String escapeHtml(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder escaped = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '<':
                    escaped.append("&lt;");
                    break;
                case '>':
                    escaped.append("&gt;");
                    break;
                case '&':
                    escaped.append("&amp;");
                    break;
                case '"':
                    escaped.append("&quot;");
                    break;
                case '\'':
                    escaped.append("&apos;");
                    break;
                default:
                    escaped.append(c);
            }
        }
        return escaped.toString();
    }

    // テキスト中のURLを<a>タグで囲む
    public static String convertUrlsToLinks(String text) {
        // 一般的なURLの正規表現パターン（簡易版）
        String urlPattern = "https?://[\\w/:%#$&?()~.=+\\-]+";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String url = matcher.group();
            // エスケープ済みのURLを<a>タグで囲む
            // ※HTMLエスケープ済みなので、URL部分は安全
            matcher.appendReplacement(sb, "<a href=\"" + url + "\">" + url + "</a>");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
