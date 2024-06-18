import java.util.Scanner;

public class WelcomeOld {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	System.out.print("당신의 이름을 입력하세요 : ");
    	String name = sc.nextLine(); // 이름을 문자열로 입력 받기

        System.out.print("연락처를 입력하세요 : ");
        int contact = sc.nextInt(); // 연락처를 정수로 입력 받기

        // System.out.println("Welcome to Shopping Mall"); ----[프로젝트 1-1]에서 작성한 내용
        // System.out.println("Welcome to Book Market!"); ----[프로젝트 1-1]에서 작성한 내용

        String greeting = "Welcome to Shopping Mall";
        String tagline = "Welcome to Book Market!";
        
        int menuSelection = 0;

        while (menuSelection != 8) {
        	System.out.println("***********************************************");
        	System.out.println("\t" + greeting);
        	System.out.println("\t" + tagline);
        	System.out.println("***********************************************");
        	System.out.println(" 1. 고객 정보 확인하기 \t4. 바구니에 항목 추가하기");
        	System.out.println(" 2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
        	System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
        	System.out.println(" 7. 영수증 표시하기 \t8. 종료");
        	System.out.println("***********************************************");
        
        System.out.print("메뉴 번호를 선택해주세요: ");
        menuSelection = sc.nextInt(); // 메뉴 번호를 정수로 입력 받기

        System.out.println(menuSelection + "번을 선택했습니다"); // 선택한 메뉴 번호 출력
        
        // 메뉴 번호에 따른 처리
        switch (menuSelection) {
            case 1:
                System.out.println("현재 고객 정보 :");
                System.out.println("이름 " + name + "    연락처 " + contact);
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
            case 8:
                System.out.println();
                break;
            default:
                System.out.println("1부터 8까지의 숫자를 입력하세요.");
                break;
        	}

        }
        
        sc.close();
	}

}
