package EX01;

public interface Predator {
	public abstract String getFood();
	
	default void printFood() {
		System.out.printf("my food is %s\n", getFood());
	}
	
	public static final int LEG_COUNT = 4;
	
	static int speed() {
		return LEG_COUNT * 30;
	}
}
