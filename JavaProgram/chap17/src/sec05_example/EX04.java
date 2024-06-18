package sec05_example;

import java.util.LinkedList;
import java.util.List;

public class EX04 {
	public static void main(String[] args) {
		// list 인터페이스 구현한 자식 클래스 생성자를 이용해 List 객체를 생성한 후 0번 위치에 100,000 개의 데이터 추가
		// 어떤 클래스(ArrayList, Vector, LinkedList)를 사용해야 효울적일까?
		List<String> list = new LinkedList<String>();
		for(int i = 0; i < 100000; i++) 
			list.add(0, i + "데이터");
		
		System.out.println("완료");
	}
}
