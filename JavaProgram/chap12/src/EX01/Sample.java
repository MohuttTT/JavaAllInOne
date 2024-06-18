package EX01;

public class Sample {
	public static void main(String[] args) {
		// 사육사 고용
		ZooKeeper zooKeeper = new ZooKeeper();
		
		// 동물원에 있는 동물들
		Tiger tiger = new Tiger();
		Lion lion = new Lion();
				
		// 사육사가 할 일 - 각 동물에 따라 밥을 준다.
		zooKeeper.feed(tiger);
		zooKeeper.feed(lion);
		
		// 인터페이스에 정의한 pringFood() default 메서드 사용
		tiger.printFood();
		lion.printFood();
		
		System.out.println(Predator.speed());
		
		Bouncer bouncer = new Bouncer();
		bouncer.barkAnimal(tiger);
		bouncer.barkAnimal(lion);
		
		// Barkable, Predator를 각각 상속받고 있는 Tiger
		Barkable t1 = new Tiger();
		t1.bark();
		// t1.getFood();
		Predator t2 = new Tiger();
		t2.getFood();
		// t2.bark();
		
		// BarkablePredator(Barkable, Predator를 부모로 두고 있음)를 상속받고 있는 Lion
		BarkablePredator l1 = new Lion();
		l1.bark();
		l1.getFood();
	}
}
