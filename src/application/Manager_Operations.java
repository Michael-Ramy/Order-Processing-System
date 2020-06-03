package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import Objects.Book;
import Objects.BookEntry;
import Objects.BookOrderEntry;
import Objects.BookSale;
import Objects.Cart;
import Objects.Publisher;
import Objects.UserEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Manager_Operations {

	private Connection myConnection;
	private ResultSet rs;
	
	public Manager_Operations(Connection connection) {
		this.myConnection = connection;
		rs = null;
	}
	
	public void Add_Book(Book book) {
		System.out.println(book.getPublisher());
		String statement = "insert into book values(" + book.getISBN() + ", \"" + book.getTitle() + "\", \"" + book.getAuthor() + "\", \"" + book.getPublisher() 
				+ "\", " + book.getDate() + ", " + +book.getPrice() + ", \"" + book.getCategory() + "\", " + book.getQuantity() + ", "+ book.getThreshold()+");";
    	try {
			Statement st = myConnection.createStatement();
			st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Add_Publisher(Publisher publisher) {
		String statement = "insert into publisher values(\"" + publisher.getName() + "\", \"" + publisher.getAddress() + "\", \"" + publisher.getPhone() + "\");";
    	try {
    		Statement st = myConnection.createStatement();
			st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<BookEntry> Get_All_books() {
		String statement = "select ISBN, title, publisher, price from book";
		ObservableList<BookEntry> Books = FXCollections.observableArrayList();
		Statement st = null;
		rs = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs.next() != false) {
				rs.first();
				while(!rs.isAfterLast()) {
					Books.add(new BookEntry(rs.getInt("ISBN"),rs.getString("title"),rs.getString("publisher"),rs.getInt("price")));
					rs.next();
				}	
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Books;
	}
	
	public Book searchBook(int ISBN, String title) {
		String statement;
		if(title.isEmpty()) {
			statement = "select * from book where ISBN = " + ISBN + ";";
		}
		else {
			statement = "select * from book where title = \"" + title + "\";";
		}
		Statement st = null;
		ResultSet rs = null;
		Book book = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
			if(rs.next() != false) {
				book = new Book(rs.getInt("ISBN"),rs.getString("title"),rs.getString("author"),rs.getString("publisher"),rs.getInt("price"),rs.getString("category"),rs.getInt("quantity"),rs.getInt("threshold"),rs.getDate("publicationYear"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	
	public boolean modifyBook(int ISBN,int newQuantity) {
		String statement = "update book set quantity = " + newQuantity +" where ISBN = " + ISBN;
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result){
			return true;
		}
		return false;
	}

	
	public ObservableList<BookEntry> searchGroup(String type, String keyword) {
		String statement;
		if(type.equals("Category")) {
			statement = "select ISBN, title, publisher, price from book where category = \"" + keyword + "\";";
		}
		else if(type.equals("Author")) {
			statement = "select ISBN, title, publisher, price from book where author = \"" + keyword + "\";";
		}
		else {
			statement = "select ISBN, title, publisher, price from book where publisher = \"" + keyword + "\";";
		}
		ObservableList<BookEntry> Books = FXCollections.observableArrayList();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs.next() != false) {
				rs.first();
				while(!rs.isAfterLast()) {
					Books.add(new BookEntry(rs.getInt("ISBN"),rs.getString("title"),rs.getString("publisher"),rs.getInt("price")));
					rs.next();
				}	
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Books;
	}
	
	public ArrayList<BookOrderEntry> getBookOrders() {
		String statement = "select * from booksorders;";
		ArrayList<BookOrderEntry> bookOrders = new ArrayList<>();
		Statement st = null;
		rs = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs.next() != false) {
				rs.first();
				while(!rs.isAfterLast()) {
					bookOrders.add(new BookOrderEntry(rs.getInt("bookISBN"),rs.getDate("orderDate"),rs.getTime("orderTime"),rs.getInt("quantity")));
					rs.next();
				}	
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookOrders;
	}
	
	public boolean ifBookOrders() {
		String statement = "select * from booksorders;";
		Statement st = null;
		rs = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs.next() != false) {
					return true;
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void confirmOrder(int ISBN) {
		String statement = "delete from booksorders where bookISBN = "+ ISBN +";";
		Statement st = null;
		try {
			st = myConnection.createStatement();
			st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addCustomerOrder(String username,ArrayList<Cart> carts, int totalAmount) {
		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());
		Time sqlTime = java.sql.Time.valueOf(LocalTime.now());
		String statement = "Insert into customersorders (username, purchaseDate, purchaseTime, amount) values (\"" +username + "\", '" + sqldate + "', '" + sqlTime + "', " + totalAmount + ");";
		Statement st = null;
		rs = null;
		int orderId = 0;
		try {
			st = myConnection.createStatement();
			st.execute(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		statement = "select max(orderId) as Max from customersorders;";
		try {
			rs = st.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs.next())
				orderId = rs.getInt("Max");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(int i = 0 ; i < carts.size(); i++) {
			statement = "insert into orders values (" + orderId + "," + carts.get(i).getBook().getISBN() + "," + carts.get(i).getQuantity() + ");";
			try {
				st.execute(statement);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserEntry> getTopUsers(){
		//select username, sum(amount) from customersorders where purchaseDate > DATE_SUB(curdate(), INTERVAL 3 month) group by username;
		String statement = "select username, sum(amount) from customersorders where purchaseDate > DATE_SUB(curdate(), INTERVAL 3 month) group by username order by sum(amount) DESC;";
		ArrayList<UserEntry> topUsers = new ArrayList<>();
		Statement st = null;
		rs = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs.next() != false) {
				rs.first();
				int i = 0;
				while(!rs.isAfterLast()) {
					i++;
					topUsers.add(new UserEntry(rs.getString("username"),rs.getInt("sum(amount)")));
					rs.next();
					if(i==5) {
						break;
					}
				}	
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return topUsers;
	}
	
	public ArrayList<Book> getTopSellingBooks(){
		//select bookISBN, title, sum(orders.quantity) from book,orders,customersorders where orders.orderId = customersorders.orderId AND purchaseDate > DATE_SUB(curdate(), INTERVAL 3 month) AND ISBN = bookISBN group by bookISBN;
		String statement = "select bookISBN, title, sum(orders.quantity) from book,orders,customersorders where orders.orderId = customersorders.orderId AND purchaseDate > DATE_SUB(curdate(), INTERVAL 3 month) AND ISBN = bookISBN group by bookISBN order by sum(orders.quantity) DESC;";
		ArrayList<Book> topBooks = new ArrayList<>();
		Statement st = null;
		rs = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs.next() != false) {
				rs.first();
				int i = 0;
				while(!rs.isAfterLast()) {
					i++;		
					topBooks.add(new Book(rs.getInt("bookISBN"),rs.getString("title"), rs.getInt("sum(orders.quantity)")));
					rs.next();
					if(i==10) {
						break;
					}
				}	
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return topBooks;
	}
	
	public ArrayList<BookSale> getBookSales() {
		//select username, bookISBN, title, purchaseDate, purchaseTime, orders.quantity from book,orders,customersorders where orders.orderId = customersorders.orderId AND purchaseDate > DATE_SUB(curdate(), INTERVAL 1 month) AND ISBN = bookISBN order by purchaseDate ASC, purchaseTime ASC;
		String statement = "select username, bookISBN, title, purchaseDate, purchaseTime, orders.quantity from book,orders,customersorders where orders.orderId = customersorders.orderId AND purchaseDate > DATE_SUB(curdate(), INTERVAL 1 month) AND ISBN = bookISBN order by purchaseDate ASC, purchaseTime ASC;";
		ArrayList<BookSale> bookSales = new ArrayList<>();
		Statement st = null;
		rs = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs.next() != false) {
				rs.first();
				while(!rs.isAfterLast()) {
					bookSales.add(new BookSale(rs.getString("username"), rs.getInt("bookISBN"),rs.getString("title"),rs.getDate("purchaseDate"), rs.getTime("purchaseTime"), rs.getInt("orders.quantity")));
					rs.next();
				}	
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookSales;
	}
	
}
