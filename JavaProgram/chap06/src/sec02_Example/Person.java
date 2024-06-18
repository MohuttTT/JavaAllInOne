package sec02_Example;

public class Person {
	// 속성(공통적인) : 이름, 주민번호
	String name;
	int reg_num;
	
	// 행동(공통적인) : 걷기, 달리기
	void walk() {
		System.out.printf("%s님이 걷습니다.", this.name);
	}
	
	void run() {
		System.out.printf("%s님이 달립니다.", this.name);
	}
	
}
