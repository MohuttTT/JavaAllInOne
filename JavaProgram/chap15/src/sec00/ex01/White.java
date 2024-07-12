package sec00.ex01;

public class White extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println("백기 올려!");
		}
	}
}
