package fivetonine;

import javax.swing.*;  
import java.awt.event.*;
import java.awt.*;

import java.util.LinkedList;
import java.math.BigDecimal;

import java.util.Random;

public class Applet {
	private static boolean isEmail(String emailString) {
		Boolean isEmail;
		Boolean hasAt = false;
		Boolean hasDot = false;
		
		for (char c : emailString.toCharArray()) {
			if (!hasAt & c == '@') {
				hasAt = true;
			} else if (hasAt & c == '.') {
				hasDot = true;
			}
		}
		
		isEmail = hasAt & hasDot;
		return isEmail;
	}
	
	static User thisUser = null;
	public static void main(String[] args) { 
		DatabaseConnector Connector;
		try {
			Connector = new DatabaseConnectorJDBC();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "An error occured! Check server configuration and try again!");
			exc.printStackTrace();
			return;
		}
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(500,500,500,500);
		
		JPanel login = new JPanel();
		JPanel createAccount = new JPanel();
		createAccount.setVisible(false);
		JPanel storefront = new JPanel();
		storefront.setVisible(false);
		JPanel cart = new JPanel();
		cart.setVisible(false);
		JPanel orders = new JPanel();
		orders.setVisible(false);
		JPanel editUser = new JPanel();
		editUser.setVisible(false);
		
		// Make Login Page: 
		SpringLayout loginLayout = new SpringLayout();
		login.setLayout(loginLayout);
		JTextField loginUsername = new JTextField("EMAIL ADDRESS");
		JTextField loginPassword = new JTextField("PASSWORD");
		
		JButton loginButton = new JButton("LOGIN");
		JButton createAccountButton = new JButton("CREATE ACCOUNT");
		
		
		login.add(loginUsername);
		login.add(loginPassword);
		login.add(loginButton);
		login.add(createAccountButton);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, loginUsername, -30, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginUsername, 0, SpringLayout.HORIZONTAL_CENTER, login);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, loginPassword, 0, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginPassword, 0, SpringLayout.HORIZONTAL_CENTER, login);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, loginButton, 30, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginButton, 0, SpringLayout.HORIZONTAL_CENTER, login);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, createAccountButton, 60, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, createAccountButton, 0, SpringLayout.HORIZONTAL_CENTER, login);
		
		loginLayout.putConstraint(SpringLayout.WEST, login, 0, SpringLayout.WEST, window);
		loginLayout.putConstraint(SpringLayout.NORTH, login, 0, SpringLayout.NORTH, window);
		
		// Make makeAccount page
		
		JTextField newUserName = new JTextField("Username");
		JTextField newPassword = new JTextField("Password");
		JTextField newEmail = new JTextField("Email Address");
		JTextField newStreet = new JTextField("Street Address");
		JTextField newCity = new JTextField("City Name");
		JTextField newState = new JTextField("State");
		JTextField newZip = new JTextField("Zip Code");
		JButton caButton = new JButton("Create!");
		
		login.add(newUserName);
		login.add(newPassword);
		login.add(newEmail);
		login.add(newStreet);
		login.add(newCity);
		login.add(newState);
		login.add(newZip);
		login.add(caButton);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, newUserName, -105, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newUserName, 0, SpringLayout.HORIZONTAL_CENTER, login);
		newUserName.setVisible(false);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, newPassword, -75, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newPassword, 0, SpringLayout.HORIZONTAL_CENTER, login);
		newPassword.setVisible(false);

		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, newEmail, -45, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newEmail, 0, SpringLayout.HORIZONTAL_CENTER, login);
		newEmail.setVisible(false);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, newStreet, -15, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newStreet, 0, SpringLayout.HORIZONTAL_CENTER, login);
		newStreet.setVisible(false);

		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, newCity, 15, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newCity, 0, SpringLayout.HORIZONTAL_CENTER, login);
		newCity.setVisible(false);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, newState, 45, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newState, 0, SpringLayout.HORIZONTAL_CENTER, login);
		newState.setVisible(false);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, newZip, 75, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newZip, 0, SpringLayout.HORIZONTAL_CENTER, login);
		newZip.setVisible(false);
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, caButton, 105, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, caButton, 0, SpringLayout.HORIZONTAL_CENTER, login);
		caButton.setVisible(false);
		
		// make Navigation Buttons
		JButton homeButton = new JButton("Home");
		JButton cartButton = new JButton("Cart");
		JButton ordersButton = new JButton("Orders");
		
		JButton orderButton = new JButton("Order");
		
		login.add(homeButton);
		login.add(cartButton);
		login.add(ordersButton);
		
		login.add(orderButton);
		
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, homeButton, -100, SpringLayout.HORIZONTAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.NORTH, homeButton, 25, SpringLayout.NORTH, login);
		homeButton.setVisible(false);
		
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cartButton, 0, SpringLayout.HORIZONTAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.NORTH, cartButton, 25, SpringLayout.NORTH, login);
		cartButton.setVisible(false);
		
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ordersButton, 100, SpringLayout.HORIZONTAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.NORTH, ordersButton, 25, SpringLayout.NORTH, login);
		ordersButton.setVisible(false);
		
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, orderButton, 0, SpringLayout.HORIZONTAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.SOUTH, orderButton, -25, SpringLayout.SOUTH, login);
		orderButton.setVisible(false);
		
		// Make Products Screen
		JScrollPane productsScrollPane = new JScrollPane();
		JPanel productsPanel = new JPanel();
		
		productsScrollPane.setViewportView(productsPanel);
		productsScrollPane.setPreferredSize(new Dimension(300,300));
		
		login.add(productsScrollPane);
		
		productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, productsScrollPane, 0, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, productsScrollPane, 0, SpringLayout.HORIZONTAL_CENTER, login);
		productsScrollPane.setVisible(false);
		
		// Make Cart Screen
		JScrollPane cartScrollPane = new JScrollPane();
		JPanel cartPanel = new JPanel();
		
		cartScrollPane.setViewportView(cartPanel);
		cartScrollPane.setPreferredSize(new Dimension(300,300));
		
		login.add(cartScrollPane);
		
		cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cartScrollPane, 0, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cartScrollPane, 0, SpringLayout.HORIZONTAL_CENTER, login);
		cartScrollPane.setVisible(false);
		
		// Make Orders Screen
		JScrollPane ordersScrollPane = new JScrollPane();
		JPanel ordersPanel = new JPanel();
		
		ordersScrollPane.setViewportView(ordersPanel);
		ordersScrollPane.setPreferredSize(new Dimension(300,300));
		
		login.add(ordersScrollPane);
		
		ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
		
		loginLayout.putConstraint(SpringLayout.VERTICAL_CENTER, ordersScrollPane, 0, SpringLayout.VERTICAL_CENTER, login);
		loginLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ordersScrollPane, 0, SpringLayout.HORIZONTAL_CENTER, login);
		ordersScrollPane.setVisible(false);
		
		// Add functions to buttons
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					thisUser = Connector.getUser(loginUsername.getText(), loginPassword.getText());
				} catch (Exception exec) {
					JOptionPane.showMessageDialog(null, "Invalid Email Address or Password!");
					return;
				}
				
				
				loginUsername.setVisible(false);
				loginPassword.setVisible(false);
				loginButton.setVisible(false);
				createAccountButton.setVisible(false);
				
				productsScrollPane.setVisible(true);
				
				homeButton.setVisible(true);
				cartButton.setVisible(true);
				ordersButton.setVisible(true);
				
				LinkedList<Product> prodList = Connector.getProducts();
				
				JButton oButton;
				
				for (Product p : prodList) {
					productsPanel.add(new JLabel(p.Name));
					productsPanel.add(new JLabel(p.Price.toString()));
					productsPanel.add(new JLabel(p.Description));
					
					oButton = new JButton("Add to cart!");
					
					oButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (p.Quantity >= 1) {
								Connector.addToCart(thisUser.UID, p.PID);
							} else {
								JOptionPane.showMessageDialog(null, "Item out of stock!!");
							}
							p.Quantity = p.Quantity - 1;
							
							
						}
					});
					
					productsPanel.add(oButton);
					
					
				}
				
			}
		});
		
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				loginUsername.setVisible(false);
				loginPassword.setVisible(false);
				loginButton.setVisible(false);
				createAccountButton.setVisible(false);
				
				newUserName.setVisible(true);
				newPassword.setVisible(true);
				newEmail.setVisible(true);
				newStreet.setVisible(true);
				newCity.setVisible(true);
				newState.setVisible(true);
				newZip.setVisible(true);
				caButton.setVisible(true);
			}
		});
		
		caButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isEmail(newEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Not a valid email address!");
					return;
				}
				
				Random r = new Random();
				User newUser = new User();
				
				newUser.UID = r.nextInt(8999) + 1000;
				newUser.Email = newEmail.getText();
				newUser.Uname = newUserName.getText();
				newUser.Street = newStreet.getText();
				newUser.City = newCity.getText();
				newUser.State = newState.getText();
				try {
					newUser.Zip = Integer.parseInt(newZip.getText());
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "Zip Code not formatted as integer!!");
					return;
				}
				
				try {
					Connector.makeUser(newUser, newPassword.getText());
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "An error has occured! Check server connection and try again!");
					return;
				}
				loginUsername.setVisible(true);
				loginPassword.setVisible(true);
				loginButton.setVisible(true);
				createAccountButton.setVisible(true);
				
				newUserName.setVisible(false);
				newPassword.setVisible(false);
				newEmail.setVisible(false);
				newStreet.setVisible(false);
				newCity.setVisible(false);
				newState.setVisible(false);
				newZip.setVisible(false);
				caButton.setVisible(false);
			}
			
			
		});
		
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productsScrollPane.setVisible(true);
				cartScrollPane.setVisible(false);
				ordersScrollPane.setVisible(false);
				
				cartPanel.removeAll();
				ordersPanel.removeAll();
				
				orderButton.setVisible(false);
				

				LinkedList<Product> prodList = Connector.getProducts();
				
				JButton oButton;
				
				for (Product p : prodList) {
					productsPanel.add(new JLabel(p.Name));
					productsPanel.add(new JLabel(p.Price.toString()));
					productsPanel.add(new JLabel(p.Description));
					
					oButton = new JButton("Add to cart!");
					
					oButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (p.Quantity >= 1) {
								Connector.addToCart(thisUser.UID, p.PID);
							} else {
								JOptionPane.showMessageDialog(null, "Item out of stock!!");
							}
							p.Quantity = p.Quantity - 1;
						}
					});
					
					productsPanel.add(oButton);
					
				}
			}
		});
		
		cartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productsScrollPane.setVisible(false);
				cartScrollPane.setVisible(true);
				ordersScrollPane.setVisible(false);
				
				productsPanel.removeAll();
				ordersPanel.removeAll();
				
				orderButton.setVisible(true);
				
				LinkedList<Product> prodList = Connector.getCart(thisUser.UID);
				
				//JButton oButton;
				
				BigDecimal priceSum = new BigDecimal(0);
				
				for (Product p : prodList) {
					cartPanel.add(new JLabel(p.Name));
					cartPanel.add(new JLabel(p.Price.toString()));
					cartPanel.add(new JLabel(p.Description));
					
					JButton oButton = new JButton("Remove from Cart");

					priceSum = priceSum.add(p.Price);
					
					oButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Connector.removeFromCart(thisUser.UID, p.PID);
							
							oButton.setVisible(false);
						}
					});
					
					cartPanel.add(oButton);
					
				}
				
				JLabel cartTotal = new JLabel("Order Total: " + priceSum.toString());
				cartPanel.add(cartTotal);
			}
		});
		
		ordersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productsScrollPane.setVisible(false);
				cartScrollPane.setVisible(false);
				ordersScrollPane.setVisible(true);
				
				cartPanel.removeAll();
				productsPanel.removeAll();
				
				orderButton.setVisible(false);
				
				LinkedList<Product> prodList = Connector.getOrders(thisUser.UID);
				
				
				for (Product p : prodList) {
					ordersPanel.add(new JLabel(p.Name));
					ordersPanel.add(new JLabel(p.Price.toString()));
					ordersPanel.add(new JLabel(p.Description));
					ordersPanel.add(Box.createRigidArea(new Dimension(0,20)));
				}
				
			}
		});
		
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connector.order(thisUser.UID);
				
				cartScrollPane.setVisible(false);
			}
		});
		
		window.add(login);
		window.setVisible(true);
		

		
	}  
}  