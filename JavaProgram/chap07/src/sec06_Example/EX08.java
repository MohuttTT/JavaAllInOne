package sec06_Example;

class H {
	// 클래스 H 내부에는 2개의 생성자가 정의돼 있다.
	int a, b, c, d;
	H() {
		this(5); // == H(int k)
	}
	
	H(int k) {
		a=k;
		b=k;
		c=k;
		d=k;
	}
	
}

public class EX08 {

	public static void main(String[] args) {
		// 다음과 같이 객체를 생하고 각 필드값을 출력했을 때 모든 필드값으로 5가 출력되도록 코드를 추가하시오.
		H h = new H();
		System.out.println(h.a);
		System.out.println(h.b);
		System.out.println(h.c);
		System.out.println(h.d);

	}

}
