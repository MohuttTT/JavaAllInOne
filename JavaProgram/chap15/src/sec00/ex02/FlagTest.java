package sec00.ex02;

public class FlagTest {

	public static void main(String[] args) {
		// Runnable 인터페이스를 구현한 클래스 객체 생성
		White white = new White();
		Blue blue = new Blue();
		
		// Thread에 start() 메서드 사용하기 위해 Thread 객체 생성
		Thread t1 = new Thread(white);
		Thread t2 = new Thread(blue);
		
		// start() 메서드 호출하여 스텍 메모리 준비 및 run() 실행 
		t1.start();
		t2.start();
	}

}
