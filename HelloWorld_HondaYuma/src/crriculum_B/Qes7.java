package crriculum_B;

import java.util.Scanner;

public class Qes7 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = 0;
		// 生徒の人数が2以上になるまで入力を促す
		while (num < 2) {
			System.out.print("生徒の人数を入力してください（2以上）: ");
			num = scanner.nextInt();
			System.out.println();
		}
		
		// 科目名を配列で用意
		String[] subject = {"英語","数学","理科","社会"};
		// 生徒ごとの科目点数を格納する2次元配列
		int[][] score = new int[num][subject.length];
		
		// 各生徒ごとに各科目の点数を入力
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < subject.length; j++) {
				System.out.print((i+1) + "人目の『" + subject[j] + "』の点数を入力してください :");
				score[i][j] = scanner.nextInt();
			}
			System.out.println();
		}
		
		// 各生徒の平均点を計算して表示
		double sumscore = 0;
		for (int i = 0; i < score.length; i++) {
			for (int j = 0; j < subject.length; j++) {
				sumscore = sumscore + score[i][j];
			}
			System.out.println((i+1) + "人目の平均点は" + (sumscore/subject.length) + "点です。");
		}
		System.out.println();
		
		// 各科目ごとの平均点を計算して表示
		double[] avgsubject = {0,0,0,0};
		for (int i = 0; i < subject.length; i++) {
			for (int j = 0; j < num; j++) {
				avgsubject[i] = avgsubject[i] + score[j][i];  
			}
			avgsubject[i] = avgsubject[i]/num;
			System.out.println(subject[i] + "の平均点は" + avgsubject[i] + "点です。");
		}
	}
}
