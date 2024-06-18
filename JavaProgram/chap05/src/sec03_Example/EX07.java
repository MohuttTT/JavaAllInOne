package sec03_Example;

public class EX07 {

	public static void main(String[] args) {
		
		// 7. 다음 코드의 실행 결과를 쓰시오.
		String a = "방가";
		String b = new String("방가");
		String c = "방가";
		String d = new String("방가");
		String e = "방가";
		String f = new String("방가");
		
		System.out.println(a==b);
		System.out.println(a==c);
		System.out.println(a==d);
		System.out.println(a==e);
		System.out.println(a==f);
		System.out.println();
		
		System.out.println(b==c);
		System.out.println(b==d);
		System.out.println(b==e);
		System.out.println(b==f);
		

	}

}
