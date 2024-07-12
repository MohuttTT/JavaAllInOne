import java.util.Arrays;

public class CartItem {
 
	private String[] itemBook = new String[7]; // 사용자가 장바구니에 추가한 책의 정보
	private String bookID; // 사용자가 장바구니에 추가한 책의 ID(=ISBN)
	private int quantity; // 사용자가 장바구니에 추가한 책 수량
	private int totalPrice; // 사용자가 장바구니에 추가한 각 책별의 총 가격
	
	
	// 기본 생성자
	public CartItem() {
		super();
	}

	
	// String[] book 매개변수를 둔 생성자 - itemBook, bookID, quantity(1 설정), updateTotalPrice() 호출
	// String[] book == 사용자가 장바구니에 추가하려는 도서의 정보 (7가지)
	public CartItem(String[] book) {
		super();
		this.itemBook = book; // 도서 정보 저장
		this.bookID = book[0]; // 도서 정보 중에 0번째 == ISBN 번호
		this.quantity = 1;
		updateTotalPrice();
	}


	// 한 권을 기준으로 수량이 늘어날 때마다 책 가격을 업데이트
	public void updateTotalPrice() {
		this.totalPrice = Integer.parseInt(this.itemBook[2]) * this.quantity;
	}


	public String[] getItemBook() {
		return itemBook;
	}


	public void setItemBook(String[] itemBook) {
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
		return "CartItem [itemBook=" + Arrays.toString(itemBook) + ", bookID=" + bookID + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + "]";
	}
	
	
	
	

	
	
}
