package sec05_example;

import java.util.Stack;

public class EX09 {
	public static void main(String[] args) {
		Stack<Double> stack = new Stack<Double>();
		stack.push(1.1);
		stack.push(2.2);
		stack.pop();
		stack.push(3.3);
		stack.push(4.4);
		
		System.out.println(stack.search(1.1));
		System.out.println(stack.search(2.2));
		System.out.println(stack.search(3.3));
		System.out.println(stack.search(4.4));
	}
}
