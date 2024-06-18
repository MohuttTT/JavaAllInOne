package sec01_selectcontrolstatement.EX02_SwitchControlStatement;

public class SwitchControlStatement {

	public static void main(String[] args) {
		
		// 한 줄 복사 : ctrl+alt+위/아래 방향키
		// 한 줄 삭제 : ctrl+D
		// 한 줄 이동 : alt+위/아래 방향키
		// System.out.println : syso 작성한 다음에 ctrl+space(자동 완성)
		
		// break 포함 X
		int val1 = 2;
		switch(val1) {
		case 1:
			System.out.println("A");
		case 2:
			System.out.println("B");	// 실행
		case 3:
			System.out.println("C");	// 실행
		default:
			System.out.println("D");	// 실행
		}	// switch문 끝
		
		System.out.println();
		
		// break 포함
		int val2 = 2;
		switch (val2) {
		case 1:
			System.out.println("A");
			break;
		case 2:
			System.out.println("B");	// 실행
			break;	// 가장 가까운 닫는 중괄호 찾아서 탈출
		case 3:
			System.out.println("C");
			break;
		default:
			System.out.println("A");
			// default 구문의 경우 일치하지 않는 경우 실행되는 구문이기 때문에 구문 실행 후 switch문을 탈출한다
			// break 생략 가능
		}	// 탈출
		System.out.println();
		
		// if - else if - else 구문으로 변환
		if(val1 == 1)
			System.out.println("A");
		else if(val1 == 2)
			System.out.println("B");	// 실행 => 다음 조건 확인하지 않고 if문 탈출
		else if(val1 == 3)
			System.out.println("C");
		else
			System.out.println("D");
	}	// 탈출

}
