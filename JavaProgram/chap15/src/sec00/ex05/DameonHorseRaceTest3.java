package sec00.ex05;

public class DameonHorseRaceTest3 {
	public static void main(String[] args) {
		
		Thread horse1 = new Horse(1);
		horse1.setDaemon(true); // 일반 스레드로 설정 -- true
		horse1.start();
		
		Thread horse2 = new Horse(2);
		horse2.setDaemon(false); // 일반 스레드로 설정 -- false : 기본값
		horse2.start();
		
		try {Thread.sleep(1000); } catch (InterruptedException e) {}
		
		System.out.println("메인 스레드 종료");
	}
}
