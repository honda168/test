package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Record;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	HttpSession session;

	/**
	 * 掲示板トップページ表示
	 * @return 掲示板テンプレート名
	 */
	@GetMapping("/")
	public String index() {
		// セッションをクリア（掲示板をリセットしたい場合のみ）
		 session.invalidate(); // 通常はコメントアウト推奨
		return "board";
	}

	/**
	 * 投稿処理
	 * @param name 投稿者名
	 * @param contents 投稿内容
	 * @param model テンプレートに渡すモデル
	 * @return 掲示板テンプレート名
	 */
		
	/**
	 * 文字列がnullまたは空文字かを判定するユーティリティメソッド
	 */
	private boolean isNullOrEmpty(String str) {
	    return str == null || str.trim().isEmpty();
	}

	
	@PostMapping("/apply")
	public String apply(
			@RequestParam("name") String name,
			@RequestParam("contents") String contents,
			Model model) {

		// バリデーション処理
		if (isNullOrEmpty(name) || isNullOrEmpty(contents)) {
		    // 名前が未入力の場合、エラーメッセージをモデルに追加
		    if (isNullOrEmpty(name)) {
		        model.addAttribute("nameError", "名前を入力しろ～～～");
		    }
		    // 内容が未入力の場合、エラーメッセージをモデルに追加
		    if (isNullOrEmpty(contents)) {
		        model.addAttribute("contentsError", "内容を入力しろ～～～");
		    }

		    // セッションから投稿リストを取得（前回の投稿内容を表示するため）
		    @SuppressWarnings("unchecked")
		    List<Record> allContents = (List<Record>) session.getAttribute("contentsList");
		    // リストが取得できた場合のみモデルに追加
		    if (allContents != null) {
		        model.addAttribute("allContents", allContents);
		    }

		    // エラーメッセージ付きで掲示板画面に遷移
		    return "board";
		}

		// 以下、通常の投稿処理
		// エスケープ処理（自作メソッド利用）
		String safeName = escapeHtml(name);
		String safeContents = escapeHtml(contents);

		@SuppressWarnings("unchecked")
		List<Record> allContents = (List<Record>) session.getAttribute("contentsList");
		if (allContents == null) {
		    allContents = new ArrayList<>();
		    session.setAttribute("contentsList", allContents);
		}

		Record a = new Record(safeName, safeContents);
		allContents.add(a);
		model.addAttribute("allContents", allContents);

		System.out.println(session.getAttribute("contentsList"));

		return "board";

	}
	
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

}
