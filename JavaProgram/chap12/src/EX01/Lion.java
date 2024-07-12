package EX01;

public class Lion extends Animal implements BarkablePredator {

	@Override
	public String getFood() {
		return "banana";
	}
	
	@Override
	public void bark() {
		System.out.println("으르렁");
	}

}
