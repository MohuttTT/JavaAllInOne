package sec00.ex04;

public class Horse extends Thread {
	
	private int horseNum;
	
		public Horse(int horseNum) {
		super();
		this.horseNum = horseNum;
	}


	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(horseNum + "번 경마 우선 순위 : " + getPriority());
		}
		System.out.println(horseNum + "번 경마 결승선 도착");
	}
	
}
