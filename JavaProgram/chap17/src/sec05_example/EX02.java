package sec05_example;

import java.util.ArrayList;
import java.util.List;

public class EX02 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList();
		list.add(2);	
		list.add(3);	
		list.add(4);
		System.out.println(list); // [2, 3, 4]
		list.remove(2); // index 번호가 2번인 요소를 지워라.
		System.out.println(list);
	}
}
