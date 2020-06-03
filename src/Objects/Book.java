package Objects;

public class Book {
	
	private int ISBN, price, quantity, Threshold;
	private String title, author, publisher, category;
	private java.sql.Date Date;
	
	public Book(int iSBN, String title, String author, String publisher, int price, String category, int quantity, int threshold, java.sql.Date date) {
		super();
		ISBN = iSBN;
		this.price = price;
		this.quantity = quantity;
		Threshold = threshold;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
		Date = date;
	}

	public Book(int ISBN, String title, int quantity) {
		this.ISBN = ISBN;
		this.title = title;
		this.quantity = quantity;
	}

	
	
	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getThreshold() {
		return Threshold;
	}

	public void setThreshold(int threshold) {
		Threshold = threshold;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDate(java.sql.Date date) {
		Date = date;
	}

	public String getDate() {
		String st = "\'"+ Date.toString()+"\'";
		return st;
	}
}
