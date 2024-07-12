package EX02;

public class Lion extends Animal {
	// 다중 상속이 불가능하기 때문에 Animal 혹은 Predator만 상속 받아야 한다.
	@Override
	public String getFood() {
		return "banana";
	}
	
	/*
	 * @Override public void bark() { System.out.println("으르렁"); }
	 */

}
