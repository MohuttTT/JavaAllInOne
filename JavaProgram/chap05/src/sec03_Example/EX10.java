package sec03_Example;

public class EX10 {

	public static void main(String[] args) {
		/*
		 * ### 3. 문자열 바꾸기
		 * 다음과 같은 문자열 a:b:c:d가 있다. 배운 메서드를 활용하여 a#b#c#d로 바꿔서 출력해 보자.
		 */

		String a = "a:b:c:d";
		
		String b = a.replaceAll(":", "#");
				
		System.out.println(b);

	}

}
