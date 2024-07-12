package sec05_Example;

public class EX05 {

	public static void main(String[] args) {
		
		// 이중 for문을 실행했을 때 'A'는 몇 회 출력되는지 쓰시오
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 3; j++) {
				if(i==2) continue;
				if(j==1) break;
				
				System.out.println("A");
			}
		}
		
		

	}

}
