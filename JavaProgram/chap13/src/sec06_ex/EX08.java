package sec06_ex;

class A8 {
	static interface B{
		static void bcd() {
			System.out.println("이너 인터페이스 내 static 메서드");
		}
	}
}


public class EX08 {

	public static void main(String[] args) {
		// 이너 인터페이스의 static 메서드 호출
		A8.B.bcd();
	}

}
