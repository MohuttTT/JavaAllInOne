package sec01_selectcontrolstatement.EX01_IfControlStatement;

public class IfControlStatement {
	public static void main(String[] args) {
		// 유형 1. if
		int value1 = 5;
		if(value1 > 3) {
			System.out.println("실행1");	// 실행
		}
		if(value1 < 5) {
			System.out.println("실행2");	// 실행 X
		}
		
		boolean bool1 = true;
		boolean bool2 = false;
		if(bool1) {
			System.out.println("실행3");	// 실행
		}
		if(bool2) {
			System.out.println("실행4");	// 실행 X
		}
		
		// 유형 2. if-else
		int value2 = 5;
		if(value2 > 3) {
			System.out.println("실행5");	// 실행
		}
		else {
			System.out.println("실행6");	// 실행 X
		}
		
		// cf. 삼항 연산자와 변환 가능
		System.out.println((value2 > 3) ? "실행5" : "실행6");
		System.out.println();
		
		// 유형 3. if-else if-else if-...-else
		int value3 = 85;
		if(value3 >= 90) {
			System.out.println("A학점");	// 실행 X => 다음 조건 확인
		}
		else if(value3 >= 80) {
			System.out.println("B학점");	// 실행 => 다음 조건 확인하지 않고 if문 탈출
		}
		else if(value3 >= 70) {
			System.out.println("C학점");
		}
		else {
			System.out.println("F학점");
		}
		// 탈출
		
		if(value3 >= 70) {
			System.out.println("C학점");	// 실행 => 다음 조건 확인하지 않고 if문 탈출
		}
		else if(value3 >= 80) {
			System.out.println("B학점");
		}
		else if(value3 >= 90) {
			System.out.println("A학점");
		}
		else {
			System.out.println("F학점");
		}
		// 탈출
		
		// 변수의 범위를 표현하여 조건을 명확히 줘야 한다.
		
		if(value3 >= 70 && value3 < 80) {
			// 1. 85 >= 70 : true
			// 2. 85 < 80 : false
			// true && false => false
			System.out.println("C학점");	// 실행 X
		}
		else if(value3 >= 80 && value3 < 90) {
			// 1. 85 >= 80 : true
			// 2. 85 < 90 : true
			// 3. true && true => true
			System.out.println("B학점");	// 실행 => 다음 조건 확인하지 않고 if문 탈출
		}
		else if(value3 >= 90) {
			System.out.println("A학점");
		}
		else {
			System.out.println("F학점");
		}
	}			
}
