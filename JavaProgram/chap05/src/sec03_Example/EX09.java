package sec03_Example;

public class EX09 {

	public static void main(String[] args) {
		/*
		 * ### 1. 주민등록 번호 나누기 홍길동 씨의 주민등록 번호는 881120-1068234이다. 
		 * 홍길동 씨의 주민등록 번호 앞자리에 해당하는 부분인 연월일(YYYYMMDD)과 뒷자리에 해당하는 부분으로 나누어 출력해 보자.
		 * 
		 * ### 2. 원하는 숫자 추출하기 주문등록 번호 뒷자리의 첫 번째 숫자는 성별을 나타낸다. 
		 * 첫 번째 숫자가 1이면 남자를, 2이면 여자를 의미한다. 
		 * 홍길동 씨의 주민등록 번호 881120-1068234에서 성별을 나타내는 숫자를 출력해 보자.
		 */

		String pin = "881120-1068234";
		String yyyyMMdd = pin.substring(0, 6);
		String num = pin.substring(7);

		System.out.println(yyyyMMdd); // 앞자리 출력
		System.out.println(num); // 뒷자리 출력
		System.out.println(pin.charAt(7)); // 성별 나타내는 숫자 출력

	}

}
