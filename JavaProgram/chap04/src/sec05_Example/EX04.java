package sec05_Example;

public class EX04 {

	public static void main(String[] args) {
		
		// 다음 for문과 동일한 기능을 수행하는 while문을 작성하시오
		for(int i = 10; i > 0; i-=2) 
			System.out.println(i);
		
		System.out.println();
		System.out.println("=====while문 작성=====");
		System.out.println();
		
		// 초기식
		// while(조건식)
		// { 실행 구문; 증감식; }
		
		int i = 10; // 초기식
		while(i > 0) { // 조건식
			System.out.println(i);
			i-=2; // 증감식
		}

	}

}
