package application;

import java.util.ArrayList;

import Objects.Book;
import Objects.Cart;

public class CartHandler {

	private int Total_Cost;
	ArrayList<Cart> Cart;
	
	public CartHandler() {
		Total_Cost = 0;
		Cart = new ArrayList<>();
	}
	
	public void Add_Item (Cart cart) {
		Book book = cart.getBook();
		Total_Cost += book.getPrice() * cart.getQuantity();
		Cart.add(cart);
	}
	
	public void Remove_Item (Cart cart) {
		Book book = cart.getBook();
		Total_Cost -= book.getPrice() * cart.getQuantity();
		for (int i = 0; i < Cart.size(); i++) {
			if (Cart.get(i).getBook().getISBN() == cart.getBook().getISBN()) {
				Cart.remove(i);
				break;
			}
		}
	}
	
	public void Update_Item (Cart cart, boolean up) {
		for (int i = 0; i < Cart.size(); i++) {
			if (Cart.get(i).getBook().getISBN() == cart.getBook().getISBN()) {
				Cart c = Cart.get(i);
				if (up) {
					c.setQuantity(c.getQuantity() + 1);
					Total_Cost += c.getBook().getPrice();
				} else {
					c.setQuantity(c.getQuantity() - 1);
					Total_Cost -= c.getBook().getPrice();
				}
				break;
			}
		}
	}
	
	public int get_Total_Cost() {
		return Total_Cost;
	}
	
	public ArrayList<Cart> get_Cart() {
		return Cart;
	}
	
	public void Remove_All() {
		Cart.clear();
		Total_Cost = 0;
	}
}
