package com.market.cart;

import com.market.bookitem.Book;

// ctrl+shift+o
public class Cart implements CartInterface {
	// 장바구니 기능을 모아놓은 인터페이스의 기능을 상속받아 실제 기능을 수행할 Cart 클래스
	// 해당 클래스에서 장바구니 배열을 만들어 해당 배열에 항목을 추가, 도서 목록 출력, 항목 삭제 등을 진행할 수 있다.

	static final int NUM_BOOK = 3;
	public CartItem[] mCartItem = new CartItem[NUM_BOOK];
	public static int mCartCount = 0;

	public Cart() {
		super();
	}

	// 도서 목록 출력
	@Override
	public void printBookList(Book[] bookList) {
		// bookList : 도서관에 있는 전체 도서
		for(int i = 0; i < bookList.length; i++) {
			System.out.print(bookList[i].getBookId() + " | ");
			System.out.print(bookList[i].getName() + " | ");
			System.out.print(bookList[i].getUnitPrice() + " | ");
			System.out.print(bookList[i].getAuthor() + " | ");
			System.out.print(bookList[i].getDescription() + " | ");
			System.out.print(bookList[i].getCategory() + " | ");
			System.out.print(bookList[i].getReleaseDate());
			System.out.println();
		}
		
	}

	// 장바구니에 담긴 도서가 있는지 없는지 확인 => 있는 경우라면 수량 증가
	@Override
	public boolean isCartInBook(String bookId) {
		boolean flag = false;

		for (int i = 0; i < mCartCount; i++) {
			// 사용자가 추가하려는 책 id == bookId
			// 장바구니에 이미 담겨있는 책 id == mCartItme[i].getBookID()
			if (bookId == mCartItem[i].getBookID()) {
				// 현재 장바구니에 담겨있는 도서(==사용자가 추가하겠다는 도서)에 수량을 설정 (원래 있던 수량 값을 가져와서 +1)
				mCartItem[i].setQuantity(mCartItem[i].getQuantity() + 1);
				flag = true;
			}
		}

		return flag;
	}

	// 도서 추가
	@Override
	public void insertBook(Book book) {
		mCartItem[mCartCount++] = new CartItem(book);
	}

	// 장바구니 항목 삭제
	@Override
	public void removeCart(int numId) {
		// mCartItem = 3개의 도서가 들어있었다.
		// numId == 내가 지우려고 하는 도서 번호
		
		// 새로운 장바구니를 임시로 만들어서
		CartItem[] cartItem = new CartItem[NUM_BOOK];
		int num = 0;
		
		// mCartCount 현재 담긴 도서의 갯수 = 3
		for(int i = 0; i < mCartCount; i++) { // 0 1 2까지 반복을 순회하면서
			// numId(내가 지우려고 하는 도서 번호)와 다른 값이라면
			if(numId != i)
				// 임시로 만들어 둔 장바구니에 도서를 추가 == 내가 지우려고 하는 도서는 추가가 되지 않는다.
				cartItem[num++] = mCartItem[i];
		}
		
		// 반복문을 다 돌고 나면 임시 카트인 cartItem에 지우려고 하는 도서를 제외한 나머지가 담겨있다.
		mCartCount = num;
		mCartItem = cartItem;
	}

	// 장바구니 전체 비우기
	@Override
	public void deleteBook() {
		// mCartItem : 장바구니 
		// mCartCount : 장바구니 총 수량
		mCartItem = new CartItem[NUM_BOOK];
		mCartCount = 0;
	}

	// 장바구니에 담긴 상품 목록 출력
	@Override
	public void printCart() {
		System.out.println("장바구니 상품 목록 보기 : ");
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

}
