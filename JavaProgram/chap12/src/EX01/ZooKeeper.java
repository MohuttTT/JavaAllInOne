package EX01;

public class ZooKeeper {
	/*1. 
	 * void feed(Tiger tiger) { // 호랑이가 오면 사과를 던져 준다.
	 * System.out.println("feed apple"); }
	 * 
	 * void feed(Lion lion) { // 사자가 오면 바나나를 던져 준다.
	 * System.out.println("feed banana"); }
	 */
	
	// 새로운 동물이 올 때 마다 feed 메서드를 오버로딩해야 하는 문제가 발생
	
	// 2~3. 모든 동물들이 하나의 인터페이스인 Predator의 자식으로 생성한 후 feed 메서드 정의
	void feed(Predator predator) {
		System.out.println("feed" + predator.getFood());
	}
}
