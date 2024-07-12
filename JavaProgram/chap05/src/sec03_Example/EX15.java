package sec03_Example;

public class EX15 {

	public static void main(String[] args) {
		/*
		 * ### 8. 평균 점수 구하기
		 * A 학급에 총 10명의 학생이 있다. 해당 학생들의 중간고사 점수는 다음과 같다.
		 * [70, 60, 55, 75, 95, 90, 80, 80, 85, 100]
		 * for-each 반복문을 사용하여 A 학급의 평균 점수를 구하시오.
		 */

		int[] marks = {70, 60, 55, 75, 95, 90, 80, 80, 85, 100};
		int total = 0;
		
		for(int mark : marks) {
			total += mark;
		}
		
		System.out.printf("총 점수 : %d\n", total);
		float average = (float)total / marks.length;
		System.out.printf("평균 점수 : %.2f\n", average); // 평균 출력

	}

}
