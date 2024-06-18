
public interface CartInterface {
	// 장바구니 처리를 담당할 인터페이스 - 장바구니에 관련된 기능을 추상 메서드로 선언
	
	void printBookList(Book[] p);
	boolean isCartInBook(String id);
	void insertBook(Book p);
	void removeCart(int numId);
	void deleteBook();
	void printCart();
}
