import java.util.Scanner;

public class Welcome {

	static final int NUM_BOOK = 3; // 총 도서 개수
	static final int NUM_ITEM = 7; // 도서 정보

	// 장바구니 항목 저장하기 위한 Cart 클래스 객체 생성
	static Cart mCart = new Cart();

	// 사용자 정보를 저장하기 위해 User 클래스의 객체 생성
	static User mUser;

	public static void main(String[] args) {

		// 도서 목록 저장할 수 있는 배열 선언
		Book[] mBook = new Book[NUM_BOOK];
		BookList(mBook);

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
	
	// 사용자 사용 가능 메뉴 출력
	private static void menuIntroduction() {
		System.out.println("***********************************************");
		System.out.println(" 1. 고객 정보 확인하기 \t4. 바구니에 항목 추가하기");
		System.out.println(" 2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
		System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
		System.out.println(" 7. 영수증 표시하기 \t8. 종료");
		System.out.println(" 9. 관리자 로그인");
		System.out.println("***********************************************");
	}

	// 도서 정보 저장 - Book[]에 세 가지 도서의 정보를 초기화
	public static void BookList(Book[] booklist) {

		// 1권 책 정보
		booklist[0] = new Book("ISBN1234", "쉽게 배우는 JSP 웹 프로그래밍", 27000);
		booklist[0].setAuthor("송미영");
		booklist[0].setDescription("단계별로 쇼핑몰을 구현하며 배우는 JSP 웹 프로그래밍 ");
		booklist[0].setCategory("IT전문서");
		booklist[0].setReleaseDate("2018/10/08");

		// 2권 책 정보
		booklist[1] = new Book("ISBN1235", "Do it! 자바 정복", 30000);
		booklist[1].setAuthor("우재남");
		booklist[1].setDescription("단계별 멘토링!");
		booklist[1].setCategory("IT전문서");
		booklist[1].setReleaseDate("2022/01/22");

		// 3권 책 정보
		booklist[2] = new Book("ISBN1236", "Do it! JSP", 18000, "고광일", "웹 서버의 기초", "IT전문서", "2024/10/08");

	}
	
	// 1. 고객 정보 확인하기
	private static void menuGuestInfo() {
		System.out.println("현재 고객 정보 :");
		System.out.println(mUser); // 객체.toString();
	}
	
	// 2. 장바구니 담긴 상품 목록 보기
	private static void menuCartItemList() {
		if (mCart.mCartCount >= 0)
			mCart.printCart();
	}
	
	// 3. 장바구니 비우기
	private static void menuCartClear() {
		System.out.println("3. 장바구니 비우기");
		// mCartCount : 장바구니에 담겨 있는 수량
		if(mCart.mCartCount == 0)
			System.out.println("장바구니에 항목이 없습니다.");
		else {
			System.out.println("장바구니의 모든 항목을 삭제하시겠습니까? Y | N");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			
			if(str.equalsIgnoreCase("Y")) {
				System.out.println("장바구니의 모든 항목을 삭제했습니다.");
				mCart.deleteBook(); // 장바구니 전체 비우는 메서드 호출
			} else
				System.out.println("장바구니 비우기를 취소하셨습니다.");
		}
	}
	
	// 4. 바구니에 항목 추가하기 - 사용자로부터 도서 ID를 입력받고, 입력된 ID가 유효하면 해당 도서를 카트에 추가
	private static void menuCartAddItem(Book[] book) {
		// 도서관에 있는 도서 전체 정보 출력
		mCart.printBookList(book);

		out: while (true) {
			System.out.println("장바구니에 추가할 도서의 ID를 입력하세요.");

			Scanner input = new Scanner(System.in);
			String str = input.nextLine();

			int numId = findBookIndexByISBN(book, str);

			// 장바구니에 추가 여부 묻기
			if (numId != -1) { // 도서에 ID를 제대로 입력했을 경우
				System.out.println("장바구니에 추가하시겠습니까? Y | N");
				str = input.nextLine();

				if (str.equalsIgnoreCase("Y")) {
					System.out.println(book[numId].getBookId() + " 도서가 장바구니에 추가되었습니다.");

					// 카트에 넣기
					// if(조건문) => 장바구니 클래스에 만든 isCartInBook을 활용하여 해당 도서가 이미 장바구니에 있는지 확인
					if (!mCart.isCartInBook(book[numId].getBookId()))
						mCart.insertBook(book[numId]);
				}

				break out;
			} else { // 도서 ID를 잘못 작성한 경우
				System.out.println("다시 입력해 주세요.");
			}

		}

	}

	// 해당하는 ISBN의 도서가 몇 번째 도서인지 알아내는 메서드
	private static int findBookIndexByISBN(Book[] book, String str) {
		System.out.println(str);
		for (int i = 0; i < NUM_BOOK; i++) {
			if (str.equalsIgnoreCase(book[i].getBookId())) {
				return i;
			}
		}
		return -1;
	}
	
	// 해당하는 ISBN의 도서가 카트에 몇 번쨰에 있는지 확인하는 메서드
	private static int findBookIndexByISBN(CartItem[] cartItems, String str) {
	    for (int i = 0; i < mCart.mCartCount; i++) {
	        if (str.equalsIgnoreCase(cartItems[i].getBookID())) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	// 5. 장바구니 항목 수량 줄이기
	private static void menuCartRemoveItemCount() {
		System.out.println("5. 장바구니의 항목 수량 줄이기 : ");
	}
	
    // 6. 장바구니 항목 삭제하기
    private static void menuCartRemoveItem() {
        System.out.println("6. 장바구니의 항목 삭제하기 : ");
        
        // 장바구니에 항목이 있는지 먼저 확인
        if(mCart.mCartCount == 0)
            System.out.println("장바구니에 항목이 없습니다.");
        else {
            menuCartItemList(); // 장바구니에 담긴 목록 보여주기
            out : while(true) {
                System.out.print("장바구니에서 삭제할 도서의 ID를 입력하세요 : ");
                Scanner input = new Scanner(System.in); // 사용자한테 입력을 받을 수 있도록 입력 스캐너 객체 생성
                String str = input.nextLine(); // 입력 받은 값을 str 변수에 할당
                
                // 사용자가 지우겠다고 입력한 isbn 번호가 카트의 몇 번째 도서인지 확인 -> 해당 도서가 없으면 -1를 반환 
                int numId = findBookIndexByISBN(mCart.mCartItem, str);
                
                if(numId != -1) { // 삭제하려는 도서가 있는 경우
                    System.out.println("장바구니의 항목을 삭제하시겠습니까? Y | N");
                    str = input.nextLine();
                    
                    if(str.equalsIgnoreCase("Y")) { // 사용자가 도서 삭제를 하겠다고 한 경우
                        System.out.println(mCart.mCartItem[numId].getBookID() + "장바구니에서 도서가 삭제되었습니다.");
                        mCart.removeCart(numId);
                    } 
                    else if((str.equalsIgnoreCase("N"))) // 사용자가 도서 삭제를 취소한 경우
                        System.out.println("항목 삭제를 취소하셨습니다.");
                    
                    break out;
                } 
                
                else { // 삭제하려는 도서가 없는 경우 
                    System.out.println("다시 입력해 주세요.");
                }
            }
        }
    }
	
	// 7. 영수증 표시하기
	private static void menuCartBill() {
		System.out.println("7. 영수증 표시하기 : ");
	}
	
	// 8. 종료
	private static void menuExit() {
		System.out.println("8. 종료");
	}


	// 9. 관리자 정보 입력받아 일치 확인
	private static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요.");

		Scanner input = new Scanner(System.in);
		System.out.println("아이디 : ");
		String adminId = input.nextLine();

		System.out.println("비밀번호 : ");
		String adminPwd = input.nextLine();

		Admin admin = new Admin(mUser.getName(), mUser.getPhone());
		if (adminId.equals(admin.getId()) && adminPwd.equals(admin.getPassword()))
			System.out.printf("이름 [%s] 연락처 [%d]\n관리자 아이디[%s] 관리자 비밀번호[%s]\n", admin.getName(), admin.getPhone(),
					admin.getId(), admin.getPassword());
		else
			System.out.println("관리자 정보가 일치하지 않습니다.");

	}









	








}