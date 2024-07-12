package sec05_ex;

public class EX02 {
	
	public static void main(String[] args) {
		
		// 2. 다음 코드의 try{} 구문과 catch(){} 구문에 공통적인 코드를 finally{} 블록을 사용하여 중복을 제거하시오.
		try {
			int a = 3;
			System.out.println(5 / a);
		} catch (ArithmeticException e) {
			System.out.println("예외 발생");
		} finally {
			System.out.println("출력 내용 1");
			System.out.println("출력 내용 2");
			System.out.println("출력 내용 3");
		}
	}

}
