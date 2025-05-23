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
	@PostMapping("/apply")
	public String apply(
			@RequestParam("name") String name,
			@RequestParam("contents") String contents,
			Model model) {

		// 名前または内容が空の場合、エラーメッセージを表示して投稿処理をスキップ
		if (name == null || name.trim().isEmpty() || contents == null || contents.trim().isEmpty()) {
			model.addAttribute("error", "名前と内容を入力してください");
			// 投稿リストを再取得（セッションから）
			@SuppressWarnings("unchecked")
			List<Record> allContents = (List<Record>) session.getAttribute("contentsList");
			if (allContents != null) {
				model.addAttribute("allContents", allContents);
			}
			return "board"; // エラーメッセージ付きで掲示板に戻る
		}

		// 以下、通常の投稿処理
		@SuppressWarnings("unchecked")
		List<Record> allContents = (List<Record>) session.getAttribute("contentsList");
		if (allContents == null) {
			allContents = new ArrayList<>();
			session.setAttribute("contentsList", allContents);
		}
		Record a = new Record(name, contents);
		allContents.add(a);
		model.addAttribute("allContents", allContents);
		return "board";
	}

}
