package sec03_Example;

public class EX17 {

	public static void main(String[] args) {
		/*
		 * ### 10-1. 2차원 배열 활용 예제
		 * 각 행의 합계를 구하여 출력하는 코드를 작성해 보자. 
		 * 2X4(2행 4열)인 배열 선언
		 * 1행 = [2, 5, 7, 4]
		 * 2행 = [9, 3, 2, 8]
		 
		 * ### 10-2. 2차원 배열 활용 예제
		 * 2차원 배열을 이용하여 2X3 행렬 arr1과 arr2의 합을 구하는 코드를 작성해 보자.
		 * arr1 = 1행 [2, 3, 4] / 2행 [3, 2, 1]
		 * arr2 = 1행 [1, 2, 3] / 2행 [-4, -2, 1]
		 */

		System.out.println("====10-1번 문제====");
		int[][] myArr = new int[][] {{2, 5, 7, 4}, {9, 3, 2, 8}};
		int sum1 = 0;
		int sum2 = 0;
		
		for(int k : myArr[0]) {
			sum1 += k;
		}
		
		for(int k : myArr[1]) {
			sum2 += k;
		}
		
		System.out.printf("첫번째 행의 합계 %d\n두번째 행의 합계 %d\n", sum1, sum2);
		
		

		System.out.println();
		System.out.println("====10-2번 문제====");
		int rows = 2;
		int columns = 3;
		
		int[][] arr1 = {{2, 3, 4}, {3, 2, 1}};
		int[][] arr2 = {{1, 2, 3}, {-4, -2, 1}};
		
		int[][] sum = new int[rows][columns];
		for(int i=0; i < rows; i++) {
			for(int j=0; j <columns; j++) {
				sum[i][j] = arr1[i][j] + arr2[i][j];
			}
		}
		
		System.out.println("두 행렬의 합 : ");
		for(int i=0; i < rows; i++) {
			for(int j=0; j < columns; j++) {
				System.out.print(sum[i][j] + " ");
			}
			System.out.println();
		}
		

	}

}
