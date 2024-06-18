package sec06_Example;

class B {

	// 2. 4가지 형태로 오버로딩된 print() 메서드의 실행 결과가 출력될 수 있도록 클래스 B를 완성하시오.
	public void print() {
		System.out.println("입력값이 없습니다.");
	}

	public void print(int i) {
		System.out.println("정수 입력값 : " + i);
	}

	public void print(double d) {
		System.out.println("실수 입력값 : " + d);
	}

	public void print(String string) {
		System.out.println("문자열 입력값 : " + string);
	}
	
}

public class EX02 {

	public static void main(String[] args) {
		
		B b = new B();
		b.print();
		b.print(3);
		b.print(5.8);
		b.print("안녕");

	}

}
