package sec03_Example;

public class EX14 {

	public static void main(String[] args) {
		/*
		 * ### 7. 별 표시하기
		 * while문과 for문을 사용하여 다음과 같이 별(*)을 표시하는 프로그램을 작성해 보자.
		 */
		
		/*
		 	[실행 결과]
		 	*
		 	**
		 	***
		 	****
		 	*****
		 */

		int i = 0;
		while (true) {
			i += 1; // while문 수행 시 1씩 증가
			if(i > 5) break; // i값이 5보다 크면 while문을 탈출
			
			for(int j = 0; j < i; j++) { // i 값만큼 *을 출력
				System.out.print('*');
			}
			System.out.println();
		}
		
		
		

	}

}
