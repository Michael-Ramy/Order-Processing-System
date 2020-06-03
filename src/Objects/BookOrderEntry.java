package Objects;

import java.sql.Date;
import java.sql.Time;

public class BookOrderEntry {
	
	private int bookISBN;
	private Date orderDate;
	private Time orderTime;
	private int quantity;
	
	public BookOrderEntry(int bookISBN, Date orderDate, Time orderTime, int quantity) {
		this.bookISBN = bookISBN;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.quantity = quantity;
	}
	
	public int getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(int bookISBN) {
		this.bookISBN = bookISBN;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Time getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Time orderTime) {
		this.orderTime = orderTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
