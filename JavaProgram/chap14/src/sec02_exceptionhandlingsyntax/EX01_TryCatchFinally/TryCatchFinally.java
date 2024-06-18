package sec02_exceptionhandlingsyntax.EX01_TryCatchFinally;

public class TryCatchFinally {

	public static void main(String[] args) {
		
		// 1. try-catch
		try {
			System.out.println(3 / 0); // 예외가 발생 - 예외 처리할 수 있는 catch 구문으로 이동하려고 함
			System.out.println("프로그램 종료"); // 실행될 수 없음
		}catch (ArithmeticException e) {
			System.out.println("숫자는 0으로 나눌 수 없습니다.");
			System.out.println("프로그램 종료");
		}
		
		System.out.println();
		
		// catch 구문으로 예외를 처리했기 때문에 프로그램이 중단되지 않고 아래의 코드를 실행
		
		// 2. try-catch-finally
		try {
			System.out.println(3 / 3);
		}catch (ArithmeticException e) {
			System.out.println("숫자는 0으로 나눌 수 없습니다."); // 예외 발생하지 않았기 때문에 건너 뜀
		}finally {
			System.out.println("finally 프로그램 종료"); // 예외가 발생하든 하지 않든 무조건 실행
		}

	}

}
