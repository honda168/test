public class Person {
	String name;
	int age;
	double height;
	double weight;
	
	static int count;
	
	public Person(String name, int age, double height, double weight) {
		this.name = name;
		this.age = age;
		this.height = height;//身長
		this.weight = weight;//体重
		count++;
	}
	
	public double getBmi(double height, double weight) {
		double bmi = weight / (height * 2);
		bmi = Math.round(bmi * 100.0) / 100.0;
		return bmi;
		
	}
	public void print() {
	    System.out.println("名前は" + name + "です。");
	    System.out.println("年齢は" + age + "です。");
	    System.out.println("BMIは" + getBmi(height, weight) + "です。");
	    System.out.println();
	}
	
	public static void printCount() {
		System.out.println("合計" + Person.count + "人です。");
	}
}
