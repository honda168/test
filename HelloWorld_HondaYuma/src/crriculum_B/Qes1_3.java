package crriculum_B;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Qes1_3 {
	public static void main(String[] args) {
		System.out.println("名前をいれてね");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		
	    if (NameAlphaNumCheck(name)) {// 照合結果をtrueかfalseで取得
	    	
			if (name.length() >= 10) {
				System.out.println("名前を10文字以内にしてください");
			} else if(name == null || name.length() <= 0 ){
				System.out.println("名前を入力してください");
			} else {
				System.out.println("ユーザー名「" + name + "」を登録しました");
			}
			
		}else {
			System.out.println("半角英数字のみで名前を入力してください");
		}
		
	}
	
	static boolean NameAlphaNumCheck(String name) {
		String regex_AlphaNum = "^[A-Za-z0-9]+$";//半角英数字のみ
		
		Pattern p1 = Pattern.compile(regex_AlphaNum); // 正規表現パターンの読み込み
	    Matcher m1 = p1.matcher(name); // パターンと検査対象文字列の照合
		return m1.matches();
		
	}
}
