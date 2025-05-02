package crriculum_B;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Qes6 {
	public static void main(String[] args) {
		String[] goods = {"パソコン","冷蔵庫","扇風機","洗濯機","加湿器","テレビ","ディスプレイ"}; 
		System.out.println("在庫の知りたい商品名を入力してください。「、」で区切る");
		
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();
		
		//入力された文章を「、」で区切る
		List<String> request = new ArrayList<String>();
        for (String a : str.split("、")) {
            request.add(a);
        }
        //リクエストのあった商品に重複があれば省く
        List<String> listWithoutDuplicates = new ArrayList<String>(new HashSet<>(request));
        System.out.println(listWithoutDuplicates);
        
	}
}
