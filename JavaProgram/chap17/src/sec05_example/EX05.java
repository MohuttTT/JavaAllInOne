package sec05_example;

class Data {
	int m;
	public Data(int m) {
		this.m = m;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Data) 
			return this.m == ((Data)obj).m;
		else
			return false;
	}
}

public class EX05 {
	public static void main(String[] args) {
		Data data1 = new Data(3);
		Data data2 = new Data(3);
		
		System.out.println(data1 == data2); // 스택 메모리 값을 비교 (객체 위칫값이 다름)
		System.out.println(data1.equals(data2)); // data 필드 값을 비교
		System.out.println(data1.hashCode() == data2.hashCode()); // 오버라이딩 XX
		// -> 객체의 위칫값을 고유한 숫자로 바꿔서 반환
	}

}
