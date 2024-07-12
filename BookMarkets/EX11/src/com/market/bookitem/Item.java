package com.market.bookitem;

public abstract class Item {
	// 아이템 클래스는 도서가 가지고 있는 정보의 필수 값들 (ISBN, 도서 제목, 도서 가격)을 다루는 클래스이다.
	
	// 필드
	String bookId;
	String name;
	int unitPrice;
	
	// 각 필드 항목의 초기값은 생성자를 이용하여 인자 값으로 받을 수 있도록 한다.
	public Item(String bookId, String name, int unitPrice) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	// setter, getter 추상 메서드 생성
	abstract String getBookId();
	abstract void setBookId(String bookId);
	abstract String getName();
	abstract void setName(String name);
	abstract int getUnitPrice();
	abstract void setUnitPrice(int unitPrice);

}
