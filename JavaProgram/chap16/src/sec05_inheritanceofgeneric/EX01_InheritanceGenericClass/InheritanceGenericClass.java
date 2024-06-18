package sec05_inheritanceofgeneric.EX01_InheritanceGenericClass;

class Parent<T> {
	T t;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
}

class Child1<T> extends Parent<T> {
	// 멤버 - 부모님에게 물려받은 T t, T getT() / setT()
}

class Child2<T, V> extends Parent<T> {
	// 멤버 - 부모님에게 물려받은 T t, T getT() / setT()
	// 추가로 자신이 정의한 V v, V getV() / setV()
	V v;
	public V getV() {
		return v;
	}
	public void setV(V v) {
		this.v = v;
	}
}

public class InheritanceGenericClass {
	public static void main(String[] args) {
		// 부모 제네릭 클래스
		Parent<String> p = new Parent<>();
		p.setT("부모 제네릭 클래스");
		System.out.println(p.getT());
		
		// 자식 클래스1
		Child1<String> c1 = new Child1<>();
		c1.setT("자식 1 제네릭 클래스");
		System.out.println(c1.getT());
		
		// 자식 클래스2
		Child2<String, Integer> c2 = new Child2<>();
		c2.setT("자식 2 제네릭 클래스");
		c2.setV(100);
		System.out.println(c2.getT());
		System.out.println(c2.getV());
	}
}
