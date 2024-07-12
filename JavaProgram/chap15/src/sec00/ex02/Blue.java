package sec00.ex02;

public class Blue implements Runnable {
	@Override
	public void run() {
		while (true) {
			System.out.println("청기 올려!");
		}
	}
}
