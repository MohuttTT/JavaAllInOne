package sec03_Example;

public class EX11 {

	public static void main(String[] args) {
		/*
		 * ### 4. 조건문의 참과 거짓 판단하기
		 * 다음 코드의 출력 결과는 무엇인가?
		 * contains("포함 문자열") : 인자값 문자열이 포함되어 있는지 확인 = true | false로 return
		 */

		String a = "write once, run anywhere";
		if(a.contains("wife")) {
			System.out.println("wife");
		} else if (a.contains("once") && !a.contains("run")) {
			System.out.println("once");
		} else if (!a.contains("everywhere")) {
			System.out.println("everywhere");
		} else if (a.contains("anywhere")) {
			System.out.println("anywhere");
		} else {
			System.out.println("none");
		}

	}

}
