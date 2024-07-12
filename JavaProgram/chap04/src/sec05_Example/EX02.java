package sec05_Example;

public class EX02 {

	public static void main(String[] args) {
		int score = 72;
		
		// 91/10 => 9.1 (int 정수이기 때문에 뒤의 소수자리 제거) => 9
		// 85/10 => 8.5 => 8
		// 72/10 => 7.2 => 7
		
		switch (score/10) {
		case 10:
		case 9:
			System.out.println("A학점");
			break;
		case 8:
			System.out.println("B학점");
			break;
		case 7:
			System.out.println("C학점");
			break;

		default:
			System.out.println("F학점");
			break;
		}

	}

}
