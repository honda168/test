package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Customer;

@Controller
public class TopController {

	@GetMapping("/top")
	public Model top(Model model) {
		boolean bool = true;
		int a = 5;
		int b = 10;
		String phm = "プレースホルダー用めっせーじ";

		model.addAttribute("isFlag", bool);
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		model.addAttribute("placeholderMessage", phm);
		model.addAttribute("userName", "本田");

		List<Customer> itemList = new ArrayList<>();

		Customer c1 = new Customer("本田", "神戸", 1); // Customerクラスのインスタンス生成
		Customer c2 = new Customer("ガッシュ", "魔界", 2);
		Customer c3 = new Customer("うんこ", "トイレ", 3);
		
		itemList.add(c1); // リストに追加
		itemList.add(c2);
		itemList.add(c3);
		model.addAttribute("customers", itemList);
		return model;
	}
	
    @GetMapping("/withDate")
    public String withDate(Model model) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        model.addAttribute("currentDate", now.format(fmt));
        return "topWithDate";
    }


}