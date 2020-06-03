package Objects;

import java.sql.Date;
import java.sql.Time;

public class BookSale {
	//select username, bookISBN, title, purchaseDate, purchaseTime, orders.quantity from book,orders,customersorders where orders.orderId = customersorders.orderId AND purchaseDate > DATE_SUB(curdate(), INTERVAL 1 month) AND ISBN = bookISBN order by purchaseDate ASC, purchaseTime ASC;
	private String username;
	private int bookISBN;
	private String title;
	private Date purchaseDate;
	private Time purchaseTime;
	private int quantity;
	
	public BookSale(String username, int bookISBN, String title, Date purchcaseDate, Time purchaseTime, int quantity) {
		this.username = username;
		this.bookISBN = bookISBN;
		this.title = title;
		this.purchaseDate = purchcaseDate;
		this.purchaseTime = purchaseTime;
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(int bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Time getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Time purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
