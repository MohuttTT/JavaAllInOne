package sec05_example;

import java.util.TreeSet;

class MyData implements Comparable<MyData> {
	String str;
	public MyData(String str) {
		this.str = str;
	}
	
	@Override
	public int compareTo(MyData o) {
		// 글자 수의 오름차순으로 비교를 할 수 있도록 오버라이딩 
		if(this.str.length() < o.str.length())
			return -1;
		else if(this.str.length() == o.str.length())
			return 0;
		else
			return 1;
	}
	
	@Override
	public String toString() {
		return str;
	}
	
	
}

public class EX07 {
	public static void main(String[] args) {
		MyData md1 = new MyData("자바 프로그램");
		MyData md2 = new MyData("반가워");
		MyData md3 = new MyData("감사합니다");
		
		TreeSet<MyData> treeSet = new TreeSet<MyData>();
		treeSet.add(md1);
		treeSet.add(md2);
		treeSet.add(md3);
		System.out.println(treeSet);
	}
}
