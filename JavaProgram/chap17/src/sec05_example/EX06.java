package sec05_example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Data1 {
	int m;
	public Data1(int m) {
		this.m = m;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(m); // 인자값이 같은 경우라면 같은 해시 코드 반환
		// return m;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Data1) 
			return this.m == ((Data1)obj).m;
		else
			return false;
	}
	
}

public class EX06 {
	public static void main(String[] args) {
		// 다음 코드의 실행 결과로 Set 집합의 size()가 2가 나올 수 있도록 hashCode() 내부 작성
		Set<Data1> set = new HashSet<>();
		set.add(new Data1(2));
		set.add(new Data1(3));
		set.add(new Data1(2));
		System.out.println(set.size()); // 2
	}
}
