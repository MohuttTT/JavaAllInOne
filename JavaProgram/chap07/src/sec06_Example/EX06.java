package sec06_Example;

class F {
	// 클래스 F에 생성자가 2개 정의돼 있다.
	F(int k) {}
	F(double a, double b) {}
}

public class EX06 {

	public static void main(String[] args) {
		// 각각의 생성자를 이용해 객체를 생성하시오. (입력값은 자유롭게 지정)
		F f1 = new F(3);
		F f2 = new F(3.4, 4.5);

	}

}
