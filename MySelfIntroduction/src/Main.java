
public class Main {
	public static void main(String[] args) {
	    Person person1 = new Person("鈴木太郎", 20, 1.7, 60);
	    Person person2 = new Person("本田悠馬", 28, 1.8, 65);
	    person1.print();
	    person2.print();
	    Person.printCount();
	}
}
