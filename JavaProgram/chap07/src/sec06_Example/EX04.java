package sec06_Example;

import java.util.Arrays;

class D {
	// 기본 자료형과 참조 자료형을 입력매개변수로 하는 abc(), bcd() 메서드가 정의돼 있다.
	void abc(int m) {
		m = 8;
	}
	
	void bcd(int[] n) {
		n[0] = 4;
		n[1] = 5;
		n[2] = 6;
	}
}

public class EX04 {

	public static void main(String[] args) {
		// 이때 다음 코드의 출력값을 쓰시오.
		D d = new D();
		int m = 5;
		int[] n = {1, 2, 3};
		
		d.abc(m);
		d.bcd(n); // n 참조 변수의 stack 메모리 값 : 위칫값
		
		System.out.println(m);
		System.out.println(Arrays.toString(n));

	}

}
