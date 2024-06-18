package sec05_Example;

public class EX06 {

	public static void main(String[] args) {
		// 6~7 다음은 바깥쪽 10회, 안쪽 5회를 반복하는 이중 for문이다.

		// 6. 레이블을 사용하지 않고 i=3, j=2일 때 이중 for문을 한 번에 탈출하는 코드를 완성
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 3 && j == 2) {
					i = 100;
					break;
				}
			}
		}

		// 7. 레이블을 사용해 i=3, j=2일 때 이중 for문을 한 번에 탈출하는 코드를 완성.
		out: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 3 && j == 2) {
					break out;
				}
			}
		}

	}

}
