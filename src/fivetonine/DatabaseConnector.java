package fivetonine;

import java.util.LinkedList;
import java.math.BigDecimal;

/**
 * Represents connection to MySQL database. Has methods for all relevant commands done by GUI. 
 * 
 * @author Alex Mailloux
 *
 */
public interface DatabaseConnector {
	public
		/**
	 	 * Gets user object specified. 
	 	 * 
	 	 * @param email Email address for account. 
	 	 * @param password The password for said account. 
	 	 * @return User object with above email and password. 
	 	 * @throws RuntimeException when no such account exists
	 	 */
		User getUser(String email, String password) throws RuntimeException;
	
		/**
		 * Gets products in user's cart. 
		 * 
		 * @param UID
		 * @return Linked List of products in cart. 
		 */
		LinkedList<Product> getCart(int UID);
		
		/**
		 * Gets products that user has ordered.
		 * 
		 * @param UID ID of user. 
		 * @return LinkedList of user's ordered products. 
		 */
		LinkedList<Product> getOrders(int UID);
		
		/**
		 * Gets nine random Products from database.
		 * 
		 * @return LinkedList with nine products. 
		 */
		LinkedList<Product> getProducts();
		
		/**
		 * Adds an product to a user's cart.
		 * 
		 * @param UID ID of user.
		 * @param PID ID of product.
		 */
		void addToCart(int UID, int PID);
		
		/**
		 * Order products in user's cart. 
		 * 
		 * @param UID ID of user. 
		 */
		void order(int UID);
 
		/**
		 * Remove a product from a user's cart. 
		 * 
		 * @param UID ID of user. 
		 * @param PID ID of product.
		 */
		void removeFromCart(int UID, int PID);
		
		/**
		 * Change parameters of specified user. 
		 * 
		 * @param usr User to change specified information of.
		 * @param password Password for user. 
		 */
		
		void changeUser(User usr, String password);
		
		/**
		 * Create new user account. 
		 * 
		 * @param usr Details of user to create. 
		 * @param password Password for user. 
		 * @throws Exception when user with email address already exists. 
		 */
		void makeUser(User usr, String password) throws RuntimeException;
		
		/**
		 * Find sum of value of user's cart.
		 *  
		 * @param UID User of cart. 
		 * @return Value of cart. 
		 */
		BigDecimal cartValue(int UID);
		
		/**
		 * Find value of all orders by user. 
		 * 
		 * @param UID User of orders.
		 * @return Value of orders. 
		 */
		BigDecimal orderValue(int UID);
}
