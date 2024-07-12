package sec02_string.EX05_MethodsOfString_1;

import java.util.Arrays;

public class MethodsOfString_1 {

	public static void main(String[] args) {
		// 문자열 길이 : length()
		String str1 = "Hello Java!";
		// 한글, 영문, 공백 구분 없이 한 문자당 1개의 크기를 가진다.
		System.out.println(str1.length());
				
		// 문자열 검색
		// @인맥스 번호로 해당 문자 검색 : chatAt()
		
		System.out.println(str1.charAt(1));
		
		// @문자로 인덱스(위칫값) 번호 검색 : indexOf() - 앞에서부터 검색, lastIndexOf() - 뒤에서부터 검색
		System.out.println(str1.indexOf('a'));
		System.out.println(str1.lastIndexOf('a'));
		// (last)indexOf(문자(or 문자열), 검색을 시작할 인덱스 번호)
		
		// 문자열 변환 및 연결
		// 기본 자료형 -> 문자열로 변환 : @String.valueOf()
		// 반대로 문자열을 정수/실수로 변환 : Integer.parseInt("문자열");
		String str2 = String.valueOf(2.3);
		String str3 = String.valueOf(false);
		System.out.println(str2);
		System.out.println(str3);
		
		// 문자열 연결 (+ 연결 연산자와 동일하게 동작) : @concat()
		String str4 = str2.concat(str3);
		System.out.println(str4);
		
		// 문자열 byte[] or char[] 변환
		// 자바 입출력 주로 사용
		String str5 = "안녕하세요";
		
		// 문자열 -> byte[] 변환 : @getBytes()
		byte[] array1 = str1.getBytes();
		byte[] array2 = str5.getBytes();
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		
		// 문자열 -> char[] 변환 : @toCharArray()
		char[] array3 = str1.toCharArray();
		char[] array4 = str5.toCharArray();
		System.out.println(Arrays.toString(array3));
		System.out.println(Arrays.toString(array4));

	}

}
