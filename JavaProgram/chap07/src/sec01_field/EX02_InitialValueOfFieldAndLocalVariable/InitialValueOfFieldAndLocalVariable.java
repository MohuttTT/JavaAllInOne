package sec01_field.EX02_InitialValueOfFieldAndLocalVariable;

// 클래스 정의
class A {
	boolean m1;
	int m2;
	double m3;
	String m4;
	void printFieldValue() {
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		System.out.println(m4);
	}
	void printLocalVariable() {
		int k;
//		System.out.println(k);
	}
}
public class InitialValueOfFieldAndLocalVariable {
	public static void main(String[] args) {
		// 클래스를 활용해 객체 생성
		A a = new A();
		// 객체 활용
		a.printFieldValue();
	}
}
