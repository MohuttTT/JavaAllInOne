package sec05_Example;

public class EX03 {

	public static void main(String[] args) {
		
		// 다음과 같이 출력되도록 if 문과 break, continue 를 이용해 for 문 코드를 완성하시오.
		
		for(int i = 0; ; i++) {
			if(i % 2 == 1) continue;
			if(i>10) break;
			// 0~10 짝수만 출력
			System.out.println(i);
		}
		
		// 실행 결과 0 2 4 6 8 10

	}

}
