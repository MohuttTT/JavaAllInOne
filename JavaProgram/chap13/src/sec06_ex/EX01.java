package sec06_ex;

class A { // 아우터 클래스 A
	class B{} // A 클래스의 인스턴스 이너 클래스 B
	interface C {} // A 클래스의 이너 인터페이스 C
	class D { // A 클래스의 인스턴스 이너 클래스 D
		void def() { // A.D의 멤버 def() 
			class E{} // 지역 이너 클래스 E
		}
	}
}


public class EX01 {

}
