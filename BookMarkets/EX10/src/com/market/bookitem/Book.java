package com.market.bookitem;

public class Book extends Item {
	// Book 클래스는 ISBN, 이름, 가격을 제외하고 나머지 항목들을 관리하는 클래스이다.
	// 기본적인 도서의 필수값 또한 필요하기 때문에 Item 클래스를 상속받을 수 있게 구현.
	
	// 필드 - 저자, 도서 설명, 카테고리, 출판일
	private String author;
	private String description;
	private String category;
	private String releaseDate;
	
	// 생성자
	public Book(String bookId, String name, int unitPrice) {
		super(bookId, name, unitPrice);
	}

	public Book(String bookId, String name, int unitPrice, String author, String description, String category,
			String releaseDate) {
		super(bookId, name, unitPrice);
		this.author = author;
		this.description = description;
		this.category = category;
		this.releaseDate = releaseDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String getBookId() {
		return bookId;
	}

	@Override
	void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	void setName(String name) {
		this.name = name;
	}

	@Override
	public int getUnitPrice() {
		return unitPrice;
	}

	@Override
	void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	

}
