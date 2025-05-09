package crriculum_B;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Qes6 {
	public static void main(String[] args) {
		System.out.println("在庫の知りたい商品名を入力してください。「、」で区切る");
		
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();
		
		//入力された文章を「、」で区切ったものを配列に格納する処理
		List<String> input = new ArrayList<String>();
        for (String a : str.split("、")) {
            input.add(a);
        }
        //リクエストのあった商品に重複があれば省く処理
//        input = new ArrayList<String>(new HashSet<>(input));
        
        /**
         * 入力された商品のリストに対して、
         * 指定された商品名（パソコン、冷蔵庫、扇風機、洗濯機、加湿器、テレビ、ディスプレイ）であれば
         * 0～11のランダムな在庫数を生成し、商品ごとに在庫数を表示する。
         * テレビとディスプレイは在庫数の算出方法が異なります。
         * 指定外の商品が入力された場合は、その旨のメッセージを表示する。
         */
        Random rand = new Random();
        int televisionstock = rand.nextInt(12);
        // 入力された各商品について処理を行う
        for (String product : input) {
            String message = "";
            // 0～11のランダム
			int stock = rand.nextInt(12);
            // 商品名によって処理を分岐
            switch (product) {
                case "パソコン":
                case "冷蔵庫":
                case "扇風機":
                case "洗濯機":
                case "加湿器":
                    message = product + "の残り台数は" + stock + "台です";
                    break;
                // テレビとディスプレイの場合は個別に在庫数を調整
                case "テレビ":
                case "ディスプレイ":
                	stock = product.equals("テレビ") ? televisionstock : 11-televisionstock;
                    message = product + "の残り台数は" + stock + "台です";
                    break;
                // 上記以外の商品は指定外としてメッセージを表示
                default:
                    message = "『 " + product + " 』は指定の商品ではありません";
            }
            // 結果を出力
            System.out.println(message);
        }
	}
}
