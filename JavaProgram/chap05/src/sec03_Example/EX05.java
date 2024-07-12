package sec03_Example;

public class EX05 {

	public static void main(String[] args) {
		
		// 5. 2차원 배열 객체를 생성하고 각 위치에 다음과 같이 값을 초기화하는 코드를 작성
		int[][] a = new int[2][];
		// a[0], a[1]
		
		// @방법1 : 추천
		a[0] = new int[] {1, 3, 5};
		a[1] = new int[] {7, 9};
		
		// @방법2
		/*
		 * a[0] = new int[3]; a[1] = new int[2]; a[0][0] = 1; a[0][1] = 3;
		 */
		
		
		/* 6. 이중 for문을 이용해 위의 2차원 배열 a의 모든 원소를 출력하는 코드를 작성
			: 단, 2개의 for문에 들어갈 조건식에는 반드시 .length를 사용해야 함.
		*/
		for(int i=0; i < a.length; i++) {
			for(int j=0; j < a[i].length; j++) {
				System.out.println(a[i][j]);
			}
		}
		
		for(int[] m : a) {
			for(int n : m) {
				System.out.println(n);
			}
		}
		

	}

}
