package Objects;

public class Cart {
	
	private int quantity;
	private Book book;
	
	public Cart(int quantity, Book book) {
		super();
		this.quantity = quantity;
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}	
}
