package sec05_example;

import java.util.LinkedList;
import java.util.Queue;

public class EX10 {
	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		
		queue.offer("땡큐");
		queue.offer("베리");
		queue.offer("감사");
		queue.poll();
		queue.offer("방가");
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}

}
