import java.util.Scanner;

public class Welcome {

	
	static final int NUM_BOOK = 3; // 총 도서 개수
	static final int NUM_ITEM = 7; // 도서 정보
	static CartItem[] mCartItem = new CartItem[NUM_BOOK];
	static int mCartCount = 0;
	
	// 사용자 정보를 저장하기 위해 User 클래스의 객체 생성
	static User mUser;

	public static void main(String[] args) {
		

		// 도서 목록 저장할 수 있는 배열 선언
		String[][] mBook = new String[NUM_BOOK][NUM_ITEM];

		Scanner input = new Scanner(System.in);

		// 사용자 정보 받는 부분
		System.out.println("당신의 이름을 입력하세요 : ");
		String userName = input.nextLine();

		System.out.println("연락처를 입력하세요 : ");
		int userMobile = input.nextInt();
		
		// 사용자에게 입력 받은 값을 이용하여 User 객체 생성
		mUser = new User(userName, userMobile);
		
		
		String greeting = "Welcome to Shopping Mall";
		String tagline = "Welcome to Book Market!";

		// 레이블문 사용
		out: while (true) {
			System.out.println("***********************************************");
			System.out.println("\t" + greeting);
			System.out.println("\t" + tagline);

			menuIntroduction(); // 메뉴 소개 출력

			System.out.println("메뉴 번호를 선택해주세요.");
			int n = input.nextInt();

			
			switch (n) {
				case 1:
					menuGuestInfo();
					break;
				case 2:
					menuCartItemList();
					break;
				case 3:
					menuCartClear();
					break;
				case 4:
					menuCartAddItem(mBook);
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
					break out; // 레이블문(while 전체) 탈출
				case 9:
					menuAdminLogin();
					break;
				default:
					System.out.println("1부터 9까지의 숫자를 입력하세요.");
					break;
				}

		}

		input.close();
	}

	// 관리자 정보 입력받아 일치 확인
	private static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요.");
		
		Scanner input = new Scanner(System.in);
		System.out.println("아이디 : ");
		String adminId = input.nextLine();
		
		System.out.println("비밀번호 : ");
		String adminPwd = input.nextLine();
		
		Admin admin = new Admin(mUser.getName(), mUser.getPhone());
		if(adminId.equals(admin.getId()) && adminPwd.equals(admin.getPassword())) 
			System.out.printf("이름 [%s] 연락처 [%d]\n관리자 아이디[%s] 관리자 비밀번호[%s]\n",
					admin.getName(), admin.getPhone(), admin.getId(), admin.getPassword());
		else 
			System.out.println("관리자 정보가 일치하지 않습니다.");
		
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

	private static void menuCartAddItem(String[][] book) {
		// 인자 값으로 받은 빈 배열에 도서 목록 정보 저장
		BookList(book);

		for (int i = 0; i < NUM_BOOK; i++) {
			for (int j = 0; j < NUM_ITEM; j++)
				System.out.print(book[i][j] + " | ");
			System.out.println();
		}
		
		out : while(true) {
			System.out.println("장바구니에 추가할 도서의 ID를 입력하세요.");
			
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			
			int numId = findBookIndexByISBN(book, str);
			
			// 장바구니에 추가 여부 묻기
			if(numId != -1) { // 도서에 ID를 제대로 입력했을 경우
				System.out.println("장바구니에 추가하시겠습니까? Y | N");
				str = input.nextLine();
				
				if(str.equalsIgnoreCase("Y")) {
					System.out.println(book[numId][0] + " 도서가 장바구니에 추가되었습니다.");
					
					// 카트에 넣기
					if(!isCartInBook(book[numId][0])) 
						mCartItem[mCartCount++] = new CartItem(book[numId]);
					
					System.out.println(mCartCount);
					for(int i = 0; i < mCartCount; i++) {
						System.out.println(mCartItem[i].toString());
					}
				}
				
				break out;
			} 
			else { // 도서 ID를 잘못 작성한 경우
				System.out.println("다시 입력해 주세요.");
			}
			
		}

	}

	
	private static int findBookIndexByISBN(String[][] book, String str) {
		System.out.println(str);
		for(int i = 0; i < NUM_BOOK; i++) {
			if(str.equalsIgnoreCase(book[i][0])) {
				return i;
			}
		}
		return -1;
	}

	// 장바구니에 도서가 있는지 확인
	private static boolean isCartInBook(String bookId) {
		boolean flag = false;
		
		for(int i = 0; i < mCartCount; i++) {
			// 사용자가 추가하려는 책 id == bookId
			// 장바구니에 이미 담겨있는 책 id == mCartItme[i].getBookID()
			if(bookId == mCartItem[i].getBookID()) {
				// 현재 장바구니에 담겨있는 도서(==사용자가 추가하겠다는 도서)에 수량을 설정 (원래 있던 수량 값을 가져와서 +1)
				mCartItem[i].setQuantity(mCartItem[i].getQuantity() + 1);
				flag = true;
			}
		}
		
		return flag;
	}

	private static void menuCartClear() {
		System.out.println("3. 장바구니 비우기");
	}

	private static void menuCartItemList() {
		System.out.println("2. 장바구니 상품 목록 보기 : ");
		System.out.println("---------------------------------------------");
		System.out.println("    도서ID \t|     수량 \t|      합계");
		for (int i = 0; i < mCartCount; i++) {
			System.out.print("    " + mCartItem[i].getBookID() + " \t| ");
			System.out.print("    " + mCartItem[i].getQuantity() + " \t| ");
			System.out.print("    " + mCartItem[i].getTotalPrice());
			System.out.println("  ");
		}
		System.out.println("---------------------------------------------");
	}

	private static void menuGuestInfo() {
		System.out.println("현재 고객 정보 :");
		System.out.println(mUser); // 객체.toString();
	}

	private static void menuIntroduction() {
		System.out.println("***********************************************");
		System.out.println(" 1. 고객 정보 확인하기 \t4. 바구니에 항목 추가하기");
		System.out.println(" 2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
		System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
		System.out.println(" 7. 영수증 표시하기 \t8. 종료");
		System.out.println(" 9. 관리자 로그인");
		System.out.println("***********************************************");
	}

	// 도서 정보 저장
	public static void BookList(String[][] book) {
		// 1권 책 정보
		book[0][0] = "ISBN1234";
		book[0][1] = "쉽게 배우는 JSP 웹 프로그래밍";
		book[0][2] = "27000";
		book[0][3] = "송미영";
		book[0][4] = "단계별로 쇼핑몰을 구현하며 배우는 JSP 웹 프로그래밍 ";
		book[0][5] = "IT전문서";
		book[0][6] = "2018/10/08";

		// 2권 책 정보
		book[1][0] = "ISBN1235";
		book[1][1] = "Do it! 자바 정복";
		book[1][2] = "30000";
		book[1][3] = "우재남";
		book[1][4] = "단계별 멘토링!";
		book[1][5] = "IT전문서";
		book[1][6] = "2022/01/22";

		// 3권 책 정보
		book[2][0] = "ISBN1236";
		book[2][1] = "Do it! JSP";
		book[2][2] = "18000";
		book[2][3] = "고광일";
		book[2][4] = "웹 서버의 기초";
		book[2][5] = "전문서";
		book[2][6] = "2024/10/08";
	}
}