package crriculum_B;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Qes1_3 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String name = null;
		boolean checkflag = true;
		
		while (checkflag) {
			System.out.println("名前をいれてね");
			name = scanner.next();
			if (!NameAlphaNumCheck(name)) {// 照合結果をtrueかfalseで取得
				System.out.println("半角英数字のみで名前を入力してください");
				continue;
			} else if (name.length() >= 10) {
				System.out.println("名前を10文字以内にしてください");
				continue;
			} else if(name == null || name.length() <= 0) {
				System.out.println("名前を入力してください");
				continue;
			}
			checkflag = false;
		}

		System.out.println("ユーザー名「" + name + "」を登録しました");

		Random random = new Random();
		boolean janken = true;//勝ったらfalseになってループを抜ける
		int count = 1;//何回で勝てたかカウント
		while (janken) {
			int myhand = random.nextInt(3);
			int enemy = random.nextInt(3);
			String[] hand = {"グー","チョキ","パー"}; 
			System.out.println(name +"の手は「" + hand[myhand] + "」");
			System.out.println("相手の手は「" + hand[enemy] + "」\n");
			
			if (myhand - enemy == 0) {
				System.out.println("DRAW あいこ もう一回しましょう！\n");
				count++;
				continue;
			} else if (myhand - enemy == -2 || myhand - enemy == 1) {//負け
				
				switch (myhand) {
				case 0://自分の手がグーで負け
					System.out.println("俺の勝ち！\n負けは次につながるチャンスです！ネバーギブアップ\n");
					break;
				case 1://自分の手がチョキで負け
					System.out.println("俺の勝ち！\nたかがじゃんけん、そう思ってないですか？\nそれやったら次も、俺が勝ちますよ\n");
					break;
				case 2://自分の手がパーで負け
					System.out.println("俺の勝ち！\nなんで負けたか、明日まで考えといてください。\nそしたら何かが見えてくるはずです\n");
				}
				count++;
				continue;
			} else if (myhand - enemy == -1 || myhand - enemy == 2) {//勝ち
				System.out.println("やるやん。\n次は俺にリベンジさせて\n");
				System.out.println("勝つまでにかかった合計回数は" + count + "回です");
				janken = false;
			}
			
			
		}
	}
	
	static boolean NameAlphaNumCheck(String name) {
		String regex_AlphaNum = "^[A-Za-z0-9]+$";//半角英数字のみ
		
		Pattern p1 = Pattern.compile(regex_AlphaNum); // 正規表現パターンの読み込み
	    Matcher m1 = p1.matcher(name); // パターンと検査対象文字列の照合
		return m1.matches();
		
	}
}
