package sec02_string.EX06_MethodsOfString_2;

import java.util.Arrays;

public class MethodsOfString_2 {

	public static void main(String[] args) {
		// 문자열 수정
		// 영문자 모두 소문자로 변환 : @toLowerCase()
		// 영문자 모두 대문자로 변환 : @toUpperCase()
		String str1 = "Java Study";
		System.out.println(str1.toLowerCase());
		System.out.println(str1.toUpperCase());
		
		// 일부 문자열을 다른 문자열로 대체 : @replace()
		System.out.println(str1.replace("Study", "공부"));
		
		// 문자열의 일부만을 포함한 새로운 문자열 객체 생성 : @substring()
		// substring(시작 위치 인덱스 번호) / substring(시작 위치 인덱스 번호, 끝 인덱스 번호)
		// 시작 위치 <= 인덱스 < 끝 위치
		System.out.println(str1.substring(0, 5));
		
		// 특정 기호(구분자)를 기준으로 문자열을 분리 : @split()
		String[] strArray = "abc/def-ghi jkl".split("/|-| ");
		System.out.println(Arrays.toString(strArray));
		String[] strArray2 = "a:b:c:d".split(":");
		System.out.println(Arrays.toString(strArray2));
		
		// 좌우 공백 제거 : @trim()
		System.out.println("   abc   ".trim());

		System.out.println();
		
		// 문자열 내용 비교 : @equals(), 대소문자 상관하지 않고 비교하는 @equalsIgnoreCase()
		String str2 = new String("Java");
		String str3 = new String("Java");
		String str4 = new String("java");
		
		// == : 일치 비교 : stack 메모리 값을 비교
		System.out.println(str2 == str3);
		System.out.println(str4 == str3);
		
		System.out.println();
		
		System.out.println(str2.equals(str3));
		System.out.println(str2.equals(str4));	// 대소문자 구분
		System.out.println(str2.equalsIgnoreCase(str4));	// 대소문자 구분 X
		// str2, str4 대소문자 변경(소문자든 대문자든)
	}

}
