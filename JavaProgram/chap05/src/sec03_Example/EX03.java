package sec03_Example;

import java.util.Arrays;

public class EX03 {

	public static void main(String[] args) {
		
		// 3. 다음 코드의 출력 결과를 쓰시오
		double[] a = {1.2, 3.4, 5.6};
		double[] b = a; // stack 메모리 값 할당
		b[0] = 7.8;
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		

	}

}
