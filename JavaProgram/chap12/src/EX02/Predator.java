package EX02;

public abstract class Predator {
	abstract String getFood();
	
	void printFood() {
		System.out.printf("my food is %s\n", getFood());
	}
	
	static int LEG_COUNT = 4;
		
	int speed() {
		return LEG_COUNT * 30;

	}
}
