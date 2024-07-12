package test;

public class Test3 {
	public static void main(String[] args) {
		int sum = 0;
		float average = 0f;
		
		int[] score = {100, 80, 100, 100, 95};
		
		for (int i = 0; i < score.length; i++) {
			sum += score[i];
		}
		average = sum / (float)score.length;
		
		System.out.println("총 점수 = " + sum);
		System.out.println("평균 점수 = " + average);
	}
}
