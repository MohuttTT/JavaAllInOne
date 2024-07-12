package sec06_Example;

class C {
	// 3. C 내부에는 int[] 객체를 입력매개변수로 입력받아 
	// 배열의 모든 원소를 합한 후 리턴하는 arraySum() 메서드가 정의돼 있다.
	int arraySum(int[] array) {
		System.out.println("arraySum()의 매개변수 값 : " + array);
		int sum = 0;
		for(int i = 0; i < array.length; i++) 
			sum += array[i];
		
		return sum;
	}
}

public class EX03 {

	public static void main(String[] args) {
		// 다음과 같이 4가지 방법으로 arraySum() 메서드를 호출할 때 오류가 발생하는 코드와 그 이유를 설명하시오.
		C c = new C();
		int[] data1 = new int[] {1, 2, 3};
		int[] data2 = {1, 2, 3};
		
		System.out.println("data2의 참조 변수 값 : " + data2);
		System.out.println(c.arraySum(data1));
		System.out.println(c.arraySum(data2));
		System.out.println(c.arraySum(new int[] {1, 2, 3}));
//		System.out.println(a.arraySum({1, 2, 3}));

	}

}
