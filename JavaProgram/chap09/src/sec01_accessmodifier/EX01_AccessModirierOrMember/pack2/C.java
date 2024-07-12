package sec01_accessmodifier.EX01_AccessModirierOrMember.pack2;
// 다른 패키지 내의 클래스를 사용하기 위해서 import 구문 사용
// import의 경우 public class만 가능
import sec01_accessmodifier.EX01_AccessModirierOrMember.pack1.A;

// A와 패키지 다른 경우
public class C {
	
	A a = new A();
	
	void print() {
		System.out.println(a.a);
	}

}
