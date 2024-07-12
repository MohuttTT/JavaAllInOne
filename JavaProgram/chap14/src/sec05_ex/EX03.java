package sec05_ex;

class A {
}

class B extends A {
}

public class EX03 {
	public static void main(String[] args) {
		// 2개의 try-catch-finally 구문으로 만든 예외 처리 코드다. 다중 catch 구문을 이용해 코드를 1개의 구문으로 수정하시오.

		try {
			int[] array = { 1, 2, 3 };
			int index = 4;
			System.out.println(array[index]);
			A aa = new A();
			B bb = (B) aa;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("배열값 읽기 실패");
		} catch (ClassCastException e) {
			System.out.println("클래스 다운캐스팅 실패");
		} finally {
			System.out.println("처리 완료");
		}

	}

}
