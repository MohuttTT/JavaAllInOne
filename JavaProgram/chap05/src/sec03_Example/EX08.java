package sec03_Example;

public class EX08 {

	public static void main(String[] args) {
		
		/* 8. String str = "내 이름은 [홍길동]입니다. 나이는 [15]살입니다"라는 문자열이 있을 때
		 * String 클래스의 메서드를 이용해 String name -> "홍길동", int age -> 15가 저장되도록 코드를 작성하시오. 
		 * 단, String 클래스의 indexOf(), lastIndexOf(), substring() 메서드는 반드시 한 번 이상 사용해야 함
		 */
		
		String str = "내 이름은 [홍길동]입니다. 나이는 [15]살입니다.";
		String name;
		int age; 
		
		// 코드 작성
		int start1 = str.indexOf("["); // "["홍길동 : 인덱스 번호
		int end1 = str.indexOf("]"); // 홍길동"]" : 인덱스 번호
		name = str.substring(start1+1, end1);
		// name = str.substring(str.indexOf("홍"), str.indexOf("]"));
		
		int start2 = str.lastIndexOf("["); // "["15 : 인덱스 번호
		int end2 = str.lastIndexOf("]"); // 15"]" : 인덱스 번호 
		age = Integer.parseInt(str.substring(start2+1, end2));
		
		System.out.println(name); // 홍길동
		System.out.println(age); // 15
		

	}

}
