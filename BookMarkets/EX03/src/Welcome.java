import java.util.Scanner;

public class Welcome {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("당신의 이름을 입력하세요 : ");
        String userName = input.nextLine();

        System.out.println("연락처를 입력하세요 : ");
        // String userMobile = input.nextLine();
        int userMobile = input.nextInt();

        String greeting = "Welcome to Shopping Mall";
        String tagline = "Welcome to Book Market!";
        boolean quit = false;

        out: while (true) { 
            // while(!quit)
            // quit : false (not 연산자 사용) => true
            System.out.println("***********************************************");
            System.out.println("\t" + greeting);
            System.out.println("\t" + tagline);
            System.out.println("***********************************************");
            System.out.println(" 1. 고객 정보 확인하기 \t4. 바구니에 항목 추가하기");
            System.out.println(" 2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
            System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
            System.out.println(" 7. 영수증 표시하기 \t8. 종료");
            System.out.println("***********************************************");

            System.out.println("메뉴 번호를 선택해주세요.");
            int n = input.nextInt();
            
            if(n < 1 || n > 8) { // 1~8까지 숫자를 입력하지 않은 경우
                System.out.println("1부터 8까지의 숫자를 입력하세요.");
            } else {
                switch (n) {
                case 1:
                	menuGuestInfo(userName, userMobile);
                    break;
                case 2:
                    menuCartItemList();
                    break;
                case 3:
                    menuCartClear();
                    break;
                case 4:
                    menuCartAddItem();
                    break;
                case 5:
                    menuCartRemoveItemCount();
                    break;
                case 6:
                    menuCartRemoveItem();
                    break;
                case 7:
                    menuCartBill();
                    break;
                case 8:
                    menuExit();
                    // quit = true;
                    // break;
                    break out;
                default:
                    break;
                }
            }
            
        }
    }

	private static void menuExit() {
		System.out.println("8. 종료");
	}

	private static void menuCartBill() {
		System.out.println("7. 영수증 표시하기 : ");
	}

	private static void menuCartRemoveItem() {
		System.out.println("6. 장바구니의 항목 삭제하기 : ");
	}

	private static void menuCartRemoveItemCount() {
		System.out.println("5. 장바구니의 항목 수량 줄이기 : ");
	}

	private static void menuCartAddItem() {
		System.out.println("4. 장바구니에 항목 추가하기 : ");
	}

	private static void menuCartClear() {
		System.out.println("3. 장바구니 비우기");
	}

	private static void menuGuestInfo(String userName, int userMobile) {
		System.out.println("현재 고객 정보 :");
		System.out.printf("이름 : %s 연락처 : %d\n", userName, userMobile);
	}

	private static void menuCartItemList() {
		System.out.println("2. 장바구니 상품 목록 보기 : ");
	}
}