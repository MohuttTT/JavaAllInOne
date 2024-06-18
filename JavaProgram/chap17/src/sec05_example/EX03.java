package sec05_example;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class EX03 {
	public static void main(String[] args) {
		List<Boolean> list = new Vector();
		list.add(true);
		list.add(false);
		list.add(true);
		// list.size() < .toArray의 크기가 큰 경우
		Boolean[] bArray = list.toArray(new Boolean[5]);
		System.out.println(Arrays.toString(bArray));
	}
}
