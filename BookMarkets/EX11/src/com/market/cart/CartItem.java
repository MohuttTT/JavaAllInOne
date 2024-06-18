package com.market.cart;

import com.market.bookitem.Book;

public class CartItem {
	// 장바구니에 담긴 아이템들을 저장하는 클래스 CartItem에 itemBook 배열을 저장했지만,
	// Book 이라는 도서 항목을 저장하고 있는 클래스를 생성했기 때문에 해당 클래스를 사용할 수 있도록 필드와 생성자를 수정해야 한다.
 
	// private String[] itemBook = new String[7]; - 대신 Book 클래스 사용
	private Book itemBook; // 사용자가 장바구니에 추가한 책의 정보
	private String bookID; // 사용자가 장바구니에 추가한 책의 ID(=ISBN)
	private int quantity; // 사용자가 장바구니에 추가한 책 수량
	private int totalPrice; // 사용자가 장바구니에 추가한 각 책별의 총 가격
	
	
	// 기본 생성자
	public CartItem() {
		super();
	}

	
	public CartItem(Book bookList) {
		super();
		this.itemBook = bookList; // 도서 정보 저장
		this.bookID = bookList.getBookId(); // 도서 정보 클래스의 getBookId() == ISBN 번호
		this.quantity = 1;
		updateTotalPrice();
	}


	// 한 권을 기준으로 수량이 늘어날 때마다 책 가격을 업데이트
	public void updateTotalPrice() {
		this.totalPrice = this.itemBook.getUnitPrice() * this.quantity;
		// 도서 정보 클래스의 getUnitPrice() == 책 가격
	}


	public Book getItemBook() {
		return itemBook;
	}


	public void setItemBook(Book itemBook) {
		this.itemBook = itemBook;
	}


	public String getBookID() {
		return bookID;
	}


	public void setBookID(String bookID) {
		this.bookID = bookID;
		this.updateTotalPrice();
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	@Override
	public String toString() {
		return "CartItem [itemBook=" + itemBook + ", bookID=" + bookID + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + "]";
	}
	
	
	
	

	
	
}
