package sec05_Example;

public class EX01 {

	public static void main(String[] args) {
		// 실행 결과 : C학점
		int score = 72;
		
		if(score >= 80 && score < 90) 
			System.out.println("B학점");
		else if(score >= 90) 
			System.out.println("A학점");
		else if(score >= 70 && score < 80) 
			System.out.println("C학점");
		else 
			System.out.println("F학점");

	}

}
