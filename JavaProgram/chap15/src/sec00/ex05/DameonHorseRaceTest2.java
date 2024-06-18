package sec00.ex05;

public class DameonHorseRaceTest2 {
	public static void main(String[] args) {
		
		Thread horse1 = new Horse(1);
		horse1.setDaemon(true); // 일반 스레드로 설정 -- true : 기본값
		horse1.start();
		
		try {Thread.sleep(1000); } catch (InterruptedException e) {}
		
		System.out.println("메인 스레드 종료");
	}
}
