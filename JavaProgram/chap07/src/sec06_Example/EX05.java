package sec06_Example;

class E {
	/* 가변 길이 자료형을 이용해 여러 개의 정수를 개수와 상관없이 입력받아 
	 * 평균을 출력하는 averageScore() 메서드를 클래스 E 안에 정의하시오.
	 */

	public void averageScore(int... i) {
		// int[] i; int[] = 인자값 할당;
		int sum = 0; // 합계
		double avg = 0; // 평균
		
		for(int k : i) 
			sum += k;
		
		avg = (double)sum / (double)i.length;
		
		System.out.println(avg);
		
	}
	
	
	
	
}

public class EX05 {

	public static void main(String[] args) {
		
		E e = new E();
		e.averageScore(1);
		e.averageScore(1, 2);
		e.averageScore(1, 2, 3);
		e.averageScore(1, 2, 3, 4);
		

	}

}
