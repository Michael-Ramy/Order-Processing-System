package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Objects.User;

public final class UserHandler {

	private static Connection myConnection;
	private static User Current;
	
	private UserHandler() {}
	
	public static void set_Connection(Connection connection) {
		Current = null;
		UserHandler.myConnection = connection;
	}

	public static boolean user_login(String user_name, String password) {
		String statement = "select username, pass, firstname, lastname, email, phone, address, privilege from users where username = \"" + user_name + "\";";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = myConnection.createStatement();
			rs = st.executeQuery(statement);
			if(rs.next() != false) {
				if (rs.getString("pass").equals(password)) {
					Current = new User(rs.getString("username"), rs.getString("pass"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"),
							rs.getString("phone"), rs.getString("address"), rs.getBoolean("privilege"));
					return true;
				} else
					return false;
			}
			else 
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean user_signUp(User user) {
		String statement = "insert into users values(\"" + user.getUsername() + "\", \"" + user.getPassword() + "\", \"" + user.getFirstname() + "\", \"" + user.getLastname()
				+ "\", \"" + user.getEmail() + "\", \"" + user.getPhone() + "\", \"" + user.getAddress() + "\", " + user.isPrivilege() + ");";
    	try {
    		Statement st = myConnection.createStatement();
			st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    	return true;
	}

	public static boolean change_password_of(String user_name, String newPassword) {
		String statement = "update users set pass = \"" + newPassword + "\" where username = \"" + user_name + "\";";
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean change_last_name_of(String user_name, String newlastName) {
		String statement = "update users set lastname = \"" + newlastName + "\" where username = \"" + user_name + "\";";
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean change_first_name_of(String user_name, String newFirstName) {
		String statement = "update users set firstname = \"" + newFirstName + "\" where username = " + user_name + "\";";
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean change_email_of(String user_name, String newEmail) {
		String statement = "update users set email = \"" + newEmail + "\" where username = \"" + user_name + "\";";
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean change_phoneNum_of(String user_name, String newPhoneNum) {
		String statement = "update users set phone = \"" + newPhoneNum + "\" where username = \"" + user_name + "\";";
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean change_shipping_address_of(String user_name, String newShipping_address) {
		String statement = "update users set pass = \"" + newShipping_address + "\" where username = \"" + user_name + "\";";
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean promote_user(String user_name) {
		String statement = "update users set privilege = " + true + " where username = \"" + user_name + "\";";
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean demote_user(String user_name) {
		String statement = "update users set privilege = " + false + " where username = \"" + user_name + "\";";
		Statement st = null;
		boolean result = false;
		try {
			st = myConnection.createStatement();
			result = st.execute(statement);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static ArrayList<User> getAllUsers() {
		String statement;
		statement = "select username, pass, firstname, lastname, email, phone, address, privilege  from users where privilege = " + false + ";";
		ArrayList<User> users = new ArrayList<>();
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
					users.add(new User(rs.getString("username"), rs.getString("pass"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getBoolean("privilege")));
					rs.next();
				}	
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public static User Get_Current_User() {
		return Current;
	}
}
