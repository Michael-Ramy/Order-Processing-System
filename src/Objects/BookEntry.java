package Objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookEntry {

	private SimpleStringProperty title, publisher;
	private SimpleIntegerProperty isbn, price;
	
	public BookEntry(Integer isbn, String title, String publisher, Integer price) {
		this.title = new SimpleStringProperty(title);
		this.publisher = new SimpleStringProperty(publisher);
		this.isbn = new SimpleIntegerProperty(isbn);
		this.price = new SimpleIntegerProperty(price);
	}

	public String getTitle() {
		return title.get();
	}

	public String getPublisher() {
		return publisher.get();
	}

	public Integer getIsbn() {
		return isbn.get();
	}

	public Integer getPrice() {
		return price.get();
	}
}
