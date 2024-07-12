package sec03_Example;

public class EX16 {

	public static void main(String[] args) {
		/*
		 * ### 9-1. 1차원 배열 활용 예제
		 * 배열의 모든 요소를 더하여 총합과 평균을 구하는 코드를 작성해 보자. 
		 * [100, 80, 100, 100, 90]
		 * 평균 점수는 소수점 둘째 자리까지 나타내시오.
		 
		 * ### 9-2. 1차원 배열 활용 예제
		 * 배열의 요소 중 제일 큰 값과 제일 작은 값을 찾는 코드를 작성해 보자.
		 * [79, 88, 91, 33, 100, 55, 95]
		 */

		System.out.println("====9-1번 문제====");
		int[] marks = {100, 80, 100, 100, 90};
		int total = 0;
				
		for(int mark : marks) {
			total += mark;
		}
		
		System.out.printf("총 점수 : %d\n", total);
		float average = (float)total / marks.length;
		System.out.printf("평균 점수 : %.2f\n", average); // 평균 출력
				
		System.out.println(); // 평균 출력
		
		
		System.out.println("====9-2번 문제====");
		int arr[] = {79, 88, 91, 33, 100, 55, 95};
		
		int max = arr[0];
		int min = arr[0];
		
		for(int k : arr) {
			if(k > max) max = k;
			else if(k < min) min = k;
		}

		System.out.printf("최댓값 : %d\n최솟값 : %d\n", max, min);
	}

}
