package sec03_Example;

public class EX12 {

	public static void main(String[] args) {
		/*
		 * ### 5. 3의 배수의 합 구하기
		 * while 반복문을 사용하여 1부터 1000까지의 자연수 중 3의 배수의 합을 구해 보자.
		 */

		int result = 0;

		int i = 1;

		// 반복문 사용
		while(i <= 1000) {
			if(i%3==0) result += i; // result = result + i;
			i++; // i+=1(i = i+1);
		}

		System.out.println(result);

	}

}
