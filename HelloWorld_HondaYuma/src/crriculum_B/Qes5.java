package crriculum_B;

public class Qes5 {
	public static void main(String[] args) {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 20; j++) {
				System.out.printf("%03d * %03d = %03d", j, i, i*j);
				if(j != 20) {
					System.out.print(" || ");
				}
			}
			System.out.println("\n");
		}
	}
}
