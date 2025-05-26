package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OmikujiController {

	@GetMapping("/")
	public String index() {
		return "omikujiController";
	}
	@PostMapping("/omikuji")
	public String omikuji(
			@RequestParam("name") String name, Model model) {
		
		int rand = (int) (Math.random() * 6);

		String kekka = "";
		switch (rand) {
		case 0:
			kekka = "大吉";
			break;
		case 1:
		case 2:
		case 3:
			kekka = "吉";
			break;
		case 4:
			kekka = "小吉";
			break;
		case 5:
			kekka = "凶";
			break;
		default:
			kekka ="エラー";
		}
		
		if(name.trim().isEmpty()) {
			name = "ゲスト";
		}
		
		model.addAttribute("name", name);
		model.addAttribute("kekka", kekka);
		return "omikujiResult";
	}
}
