package fivetonine;

import java.util.LinkedList;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;
import java.security.MessageDigest


/**
 * Facilitates connection to MySQL database using JDBC. Acts as a "server" for the FiveToNine "site".
 * @author Alex Mailloux
 *
 */
public class DatabaseConnectorJDBC implements DatabaseConnector {
	
	Connection myConn;
	Statement myStmt;
	
	DatabaseConnectorJDBC() {
		// open connection to database:
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fivetonine", "root", "password");
			myStmt = myConn.createStatement();
			myStmt.execute("SET FOREIGN_KEY_CHECKS=0");
			
			if (!myConn.isValid(0)) {
				throw new RuntimeException();
			}
		} 
		catch (Exception exc) {
			throw new RuntimeException();
		}
	}

	@Override
	public User getUser(String email, String password) throws RuntimeException {
		// generate testable user class
		User usr = new User();
		
		String query = "select fivetonine.user.UID, fivetonine.user.email, fivetonine.user.uname, fivetonine.address.street, fivetonine.geoid.city, fivetonine.geoid.state, fivetonine.geoid.zip  from fivetonine.user\n" +
				"INNER JOIN fivetonine.address ON fivetonine.address.addressID = fivetonine.user.addressID\n" +
				"INNER JOIN fivetonine.geoid ON fivetonine.address.geoID = fivetonine.geoid.geoID\n" +
				"WHERE email = '" + email + "'\n"+
				"AND password = '" + hashPassword(password) + "'\n";
		// System.out.print(query);
		ResultSet myRst;
		try {
			myRst = myStmt.executeQuery(query);
			if (!myRst.next()) {
				throw new RuntimeException();
			}
			usr.UID = myRst.getInt("UID");
			usr.Email = myRst.getString("email");
			usr.Uname = myRst.getString("uname");
			usr.Street = myRst.getString("street");;
			usr.City = myRst.getString("city");;
			usr.State = myRst.getString("state");;
			usr.Zip = myRst.getInt("zip");
		} catch (Exception exc) {
			throw new RuntimeException();
		}
		
		
		
		return usr;
	}

	@Override
	public LinkedList<Product> getCart(int UID) {
		// Generate testable list of products
		LinkedList<Product> retList = new LinkedList<Product>();
		String query = "SELECT fivetonine.product.pid, fivetonine.product.name, fivetonine.product.price, fivetonine.product.description, fivetonine.product.quantity FROM fivetonine.product\n" +
				"INNER JOIN fivetonine.cart ON fivetonine.cart.pid = fivetonine.product.pid\n" + 
				"WHERE fivetonine.cart.uid = " + UID;
		ResultSet myRs;
				
		Product newProduct;
				
		try {
			myRs = myStmt.executeQuery(query);
		} catch (Exception exc) {
			return null;
		}
				
		try {
			while (myRs.next()) {
				newProduct = new Product();
				newProduct.Name = myRs.getString("name");
				newProduct.Price = myRs.getBigDecimal("price");
				newProduct.Description = myRs.getString("description");
				newProduct.Quantity = myRs.getInt("quantity");
				
				newProduct.PID = myRs.getInt("pid");
				
				retList.add(newProduct);
			}
		} catch (Exception exc) {
			return null;
		}
		return retList;
	}

	@Override
	public LinkedList<Product> getOrders(int UID) {
		// Generate testable list of products
		LinkedList<Product> retList = new LinkedList<Product>();
		
		String query = "SELECT fivetonine.order.uid, fivetonine.product.pid, fivetonine.product.name, fivetonine.product.price, fivetonine.product.description, fivetonine.product.quantity FROM fivetonine.product\n" +
				"INNER JOIN fivetonine.order ON fivetonine.order.pid = fivetonine.product.pid\n" + 
				"WHERE fivetonine.order.uid = " + UID;
		ResultSet myRs;
				
		Product newProduct;
				
		try {
			// System.out.print(query);
			myRs = myStmt.executeQuery(query);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
				
		try {
			while (myRs.next()) {
				newProduct = new Product();
				newProduct.Name = myRs.getString("name");
				newProduct.Price = myRs.getBigDecimal("price");
				newProduct.Description = myRs.getString("description");
				newProduct.Quantity = myRs.getInt("quantity");
				
				newProduct.PID = myRs.getInt("pid");
				
				
				retList.add(newProduct);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return retList;
	}

	@Override
	public LinkedList<Product> getProducts() {
		// Generate testable list of products
		LinkedList<Product> retList = new LinkedList<Product>();
		
		String query = "SELECT * FROM fivetonine.product\n" +
		"WHERE fivetonine.product.quantity > 0";
		ResultSet myRs;
		
		Product newProduct;
		
		try {
			myRs = myStmt.executeQuery(query);
		} catch (Exception exc) {
			return null;
		}
		
		try {
			while (myRs.next()) {
				newProduct = new Product();
				newProduct.Name = myRs.getString("name");
				newProduct.Price = myRs.getBigDecimal("price");
				newProduct.Description = myRs.getString("description");
				newProduct.Quantity = myRs.getInt("quantity");
				
				newProduct.PID = myRs.getInt("pid");
				
				retList.add(newProduct);
			}
		} catch (Exception exc) {
			return null;
		}
		return retList;
	}

	@Override
	public void addToCart(int UID, int PID) {
		// Test adding to cart
		String productUpdateQuery = "UPDATE fivetonine.product\n" +
				"SET fivetonine.product.quantity = fivetonine.product.quantity - 1\n" +
				"WHERE pid = 9409";
		Random r = new Random();
		int cartID = r.nextInt(8999) + 1000;
		String cartQuery = "INSERT INTO fivetonine.cart\n" +
		"VALUES (" + cartID + ", " + UID + ", " + PID + ")";
		// System.out.println(cartID);
		try {
			myStmt.execute(cartQuery);
			// System.out.println(productUpdateQuery);
			myStmt.execute(productUpdateQuery);
			
		} catch (Exception exc) {
			return;
		}
	}

	@Override
	public void order(int UID) {
		// Test ordering
		String pidsQuery = "SELECT fivetonine.cart.pid FROM fivetonine.cart\n"
				+ "WHERE fivetonine.cart.uid = " + UID;
		
		String delQuery = "DELETE FROM fivetonine.cart\n" +
		"WHERE fivetonine.cart.uid = " + UID;
		
		ResultSet myRst;
		try {
			myRst = myStmt.executeQuery(pidsQuery);
		} catch (Exception exc) {
			exc.printStackTrace();
			return;
		}
		
		Random r = new Random();
		
		LinkedList<Integer> pids = new LinkedList<Integer>();
		
		try {
			while (myRst.next()) {
				pids.add(myRst.getInt("pid"));
				
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return;
		}
		
		for (int i : pids) {
			int oid = r.nextInt(89999) + 10000;
			String orderQuery = "INSERT INTO fivetonine.order\n" +
			"VALUES (" + oid + ", " + UID + ", " + i + ")";
			// System.out.println(orderQuery);
			try {
				myStmt.execute(orderQuery);
			} catch (Exception exc) {
				return;
			}
		}
		try {
			myStmt.execute(delQuery);
		} catch (Exception exc) {
			return;
		}
	}

	@Override
	public void removeFromCart(int UID, int PID) {
		// Test removing from cart
		String productUpdateQuery = "UPDATE fivetonine.product\n" +
				"SET fivetonine.product.quantity = fivetonine.product.quantity + 1\n" +
				"WHERE pid = 9409";
		
		String cartQuery = "DELETE FROM fivetonine.cart\n" +
		"WHERE uid = " + UID + " AND pid = " + PID + "\n" +
				"LIMIT 1";
		// System.out.println(cartID);
		try {
			myStmt.execute(cartQuery);
			// System.out.println(productUpdateQuery);
			myStmt.execute(productUpdateQuery);
			
		} catch (Exception exc) {
			return;
		}
	}

	@Override
	public void changeUser(User usr, String password) {
		// Test modifying user
		// System.out.println("User Email: " + usr.Email);
		// System.out.println("User pass: " + password);
	}

	@Override
	public void makeUser(User usr, String password) throws RuntimeException {
		// Test creating user
		String query = "SELECT * FROM fivetonine.user \nWHERE fivetonine.user.email = '" + usr.Email + "'\nLIMIT 1";
		ResultSet myRs = null;
		try {
			myRs = myStmt.executeQuery(query);
		} catch (Exception exc) {
			throw new RuntimeException();
		}
		
		try {
			if (myRs.next()) {
				throw new RuntimeException();
			}
		} catch (Exception exc) {
			throw new RuntimeException();
		}
		Random r = new Random();
		int geoID = r.nextInt(8999) + 1000;
		int addressID = r.nextInt(8999) + 1000;
		usr.UID = r.nextInt(8999) + 1000;
		String geoQuery = "INSERT INTO fivetonine.geoID\n" +
		"VALUES (" + geoID + ", '" + usr.City + "', '" + usr.State.substring(0,2) + "', " + usr.Zip + ")";
		
		String addressQuery = "INSERT INTO fivetonine.address\n" +
		"VALUES (" + addressID + ", '" + usr.Street + "', " + geoID + ")";
		
		String userQuery = "INSERT INTO fivetonine.user\n" +
		"VALUES (" + usr.UID + ", '" + usr.Email + "', '" + usr.Uname + "', '" + hashPassword(password) + "', " + addressID + ")";
		// System.out.println(userQuery);
		try {
			myStmt.execute(geoQuery);
			myStmt.execute(addressQuery);
			myStmt.execute(userQuery);
		} catch (Exception exc) {
			throw new RuntimeException();
		}
	}

	@Override
	public BigDecimal cartValue(int UID) {
		// Test value of items in cart
		return new BigDecimal(100.34);
	}

	@Override
	public BigDecimal orderValue(int UID) {
		// Test value of order
		return new BigDecimal(100.34);
	}
	
	/**
	* Hashes a password string, to simulate server-side hashing when entering values into database table and seeking data
	*/
	private String hashPassword(String password) {
		// Create MessageDigest to hash the password. SHA-256 algorithm selected for its relative security compared to MD5 and SHA-1 included in Java
		MessageDigest dig = MessageDigest.getInstance("SHA-256");
		// Create Hash of Password
		byte[] hashArray = dig.digest(password.getBytes("UTF-8"));
		
		// Return byte[] array as String
		return hashArray.toString();
		
	}

}
