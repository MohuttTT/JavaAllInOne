package sec00.ex02;

public class White implements Runnable {
	@Override
	public void run() {
		while (true) {
			System.out.println("백기 올려!");
		}
	}
}
