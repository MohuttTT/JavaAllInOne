package sec04_boundedtype.EX03_BoundedTypeOfInputArguments;

class A {}
class B extends A {}
class C extends B {}
class D extends C {}

class Goods<T> {
	private T t;
	public T get() {
		return t;
	}
	public void set(T t) {
		this.t = t;
	}
}

class Test {
	void method1(Goods<A> g) {}				// case1
	void method2(Goods<?> g) {}				// case2
	void method3(Goods<? extends B> g) {}	// case3
	void method4(Goods<? super B> g) {}		// case4
}
public class BoundedTypeOfInputArguments {
	public static void main(String[] args) {
		Test t = new Test();
		
		// case1
		t.method1(new Goods<A>());
//		t.method1(new Goods<B>());
//		t.method1(new Goods<C>());
//		t.method1(new Goods<D>());
		
		// case2
		t.method2(new Goods<A>());
		t.method2(new Goods<B>());
		t.method2(new Goods<C>());
		t.method2(new Goods<D>());
		
		// case3
//		t.method3(new Goods<A>());	B의 부모인 A는 불가능
		t.method3(new Goods<B>());
		t.method3(new Goods<C>());
		t.method3(new Goods<D>());
		
		// case4
		t.method4(new Goods<A>());
		t.method4(new Goods<B>());
//		t.method4(new Goods<C>());	B의 자식인 C는 불가능
//		t.method4(new Goods<D>());	B의 자손인 D는 불가능
	}
}
