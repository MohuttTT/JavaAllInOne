package sec00.ex01;

public class Blue extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println("청기 올려!");
		}
	}
}
