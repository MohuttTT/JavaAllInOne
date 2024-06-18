package sec06_Example;

class G {
	// 클래스 G를 다음과 같이 정의했을 때
	int m = 3;
	int n = 5;
	void abc(int m, int n) {
		m = this.m;
		n = n;
	}
}


public class EX07 {

	public static void main(String[] args) {
		// 다음 코드의 출력 결과를 쓰시오
		G g = new G();
		g.abc(7, 8);
		System.out.println(g.m);
		System.out.println(g.n);
	}

}
