
package edu.augustana.comorant.launchers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import edu.augustana.comorant.controllers.MainController;
import edu.augustana.comorant.dataStructures.Customer;
import edu.augustana.comorant.dataStructures.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataAccess {

	public DataAccess() {

	}

	
	
	public static ObservableList<Customer> loadCustomers(){
		// load the sqlite-JDBC driver using the current class loader
				try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Connection connection = null;

				PreparedStatement getCustomers = null;
				ObservableList<Customer> customersList = FXCollections.observableArrayList();
				try {
					// create a database connection
					connection = DriverManager.getConnection("jdbc:sqlite:Pottery.db");


					Statement statement = connection.createStatement();
					statement.setQueryTimeout(30); // set timeout to 30 sec.


					createCustomersTable(connection);
					// Get all orders from Orders table
					String customersQuery = "SELECT * FROM Customers";
					getCustomers = connection.prepareStatement(customersQuery);
					ResultSet customersResultSet = getCustomers.executeQuery();
					while (customersResultSet.next()) {
						int rsCustomerNumber = customersResultSet.getInt("customerNumber");
						String rsFirstName = customersResultSet.getString("firstName");
						String rsLastName = customersResultSet.getString("lastName");
						String rsStreetAddress = customersResultSet.getString("streetAddress");
						String rsCity = customersResultSet.getString("city");
						String rsState = customersResultSet.getString("state");
						String rsZip = customersResultSet.getString("zip");
						String rsEmail = customersResultSet.getString("email");
						String rsPhone = customersResultSet.getString("phoneNumber");
						boolean rsSmsEnabled = customersResultSet.getBoolean("smsEnabled");
						String rsPrefContactMethod = customersResultSet.getString("prefContactMethod");
						customersList.add(new Customer(rsCustomerNumber, rsFirstName, rsLastName,
								rsStreetAddress, rsCity, rsState, rsZip,
								rsPhone, rsEmail, rsPrefContactMethod, rsSmsEnabled));
					}

				} catch (

				SQLException e)

				{
					// if the error message is "out of memory",
					// it probably means no database file is found
					System.err.println(e.getMessage());
				} finally

				{

					try {
						if (connection != null)
							connection.close();
					} catch (SQLException e) {
						// connection close failed.
						System.err.println(e);
					}
				}
				return customersList;
		
	}
	
	
	
	/**Returns the list of orders loaded from the database
	 * MainController's orderList should be set equal to the returned list at launch
	 * @return ObservableList<Order> */
	public static ObservableList<Order> loadOrders(ObservableList<Customer> customersList) {

		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection connection = null;

		PreparedStatement getOrders = null;
		ObservableList<Order> ordersList = FXCollections.observableArrayList();
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:Pottery.db");


			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.


			createOrdersTable(connection);

			/* INSERT DUMMY DATA
			 TODO remove dummy data insert
			 Remove old dummy data
			String removeOldDummyDataQuery = "DELETE FROM Orders WHERE zip='61201'";
			PreparedStatement removeOldDummyData = connection.prepareStatement(removeOldDummyDataQuery);
			removeOldDummyData.executeUpdate();
			 Insert new dummy data
			String insertDummyDataQuery = "INSERT INTO Orders("
					+ "orderDate, dueDate, status, firstName, lastName, orderDesc, streetAddress, city,"
					+ " state, zip, paymentStatus, paymentMethod, price, email, phone, smsEnabled, prefContactMethod)"
					+ " VALUES('2015-09-15', '2015-10-31', 'Incomplete', 'James', 'Smith', "
					+ "'Two mugs please', '136 Required Dr.', 'Rock Island', 'Illinois', '61201', 'Unpaid', 'Cash', 136.52, "
					+ "'555-555-5555', 'michaelcurrie12@augustana.edu', 1, 'Email');";

			PreparedStatement insertDummyData = connection.prepareStatement(insertDummyDataQuery);
			insertDummyData.executeUpdate();

			String insertDummyDataQuery2 = "INSERT INTO Orders("
					+ "orderDate, dueDate, status, firstName, lastName, orderDesc, streetAddress, city,"
					+ " state, zip, paymentStatus, paymentMethod, price, email, phone, smsEnabled, prefContactMethod)"
					+ " VALUES('2015-09-18', '2015-12-25', 'Incomplete', 'Saint', 'Nick', "
					+ "'Christmas plate and mug', '1010 North Pole Dr.', 'North Pole', 'The Arctic Circle', '61201', 'Unpaid', 'Cash', 100.01, "
					+ "'555-555-5555', 'michaelcurrie12@augustana.edu', 1, 'Email');";

			PreparedStatement insertDummyData2 = connection.prepareStatement(insertDummyDataQuery2);
			insertDummyData2.executeUpdate();*/

			// Get all orders from Orders table
			String ordersQuery = "SELECT * FROM Orders";
			getOrders = connection.prepareStatement(ordersQuery);
			ResultSet ordersResultSet = getOrders.executeQuery();
			// DateTimeFormatter sqlFormatter =
			// DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS.SSS");
			while (ordersResultSet.next()) {
				int customerNumber = ordersResultSet.getInt("customerID");
				Customer rsCustomer = null;
				for(Customer thisCustomer : customersList){
					if(thisCustomer.getCustomerNumber() == customerNumber){
						rsCustomer = thisCustomer;
						break;
					}
				}
				int rsOrderNumber = ordersResultSet.getInt("orderNumber");
				LocalDate rsOrderDate = LocalDate.parse(ordersResultSet.getString("orderDate"));
				LocalDate rsDueDate = LocalDate.parse(ordersResultSet.getString("dueDate"));
				String rsStatus = ordersResultSet.getString("status");
				String rsOrderDesc = ordersResultSet.getString("orderDesc");
				String rsPaymentStatus = ordersResultSet.getString("paymentStatus");
				String rsPaymentMethod = ordersResultSet.getString("paymentMethod");
				double rsPrice = ordersResultSet.getDouble("price");
				ordersList.add(new Order(rsCustomer, rsOrderNumber, rsOrderDate, rsDueDate, rsStatus,
						rsOrderDesc, rsPaymentStatus, rsPaymentMethod, rsPrice));
			}

		} catch (

		SQLException e)

		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally

		{

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
		return ordersList;
	}

	private static void createOrdersTable(Connection connection) throws SQLException {
		PreparedStatement createOrdersTable;
		String createOrdersTableQuery = "CREATE TABLE IF NOT EXISTS Orders(" + "orderNumber INTEGER PRIMARY KEY, "
				+ " orderDate TEXT, " + " dueDate TEXT, " + " status TEXT, " + " orderDesc TEXT, " + " paymentStatus TEXT, "
				+ " paymentMethod TEXT, " + " price REAL, " + " customerID INTEGER);";

		createOrdersTable = connection.prepareStatement(createOrdersTableQuery);
		createOrdersTable.executeUpdate();
	}
	
	private static void createCustomersTable(Connection connection) throws SQLException {
		PreparedStatement createCustomersTable;
		String createCustomersTableQuery = "CREATE TABLE IF NOT EXISTS Customers(" + "customerNumber INTEGER PRIMARY KEY, "
				+ " firstName TEXT, " + " lastName TEXT, " + " streetAddress TEXT, " + " city TEXT, " + " state TEXT, "
				+ " zip TEXT, " + " phoneNumber TEXT, " + " email TEXT, " + " prefContactMethod TEXT, " + " smsEnabled INTEGER);";

		createCustomersTable = connection.prepareStatement(createCustomersTableQuery);
		createCustomersTable.executeUpdate();
	}

	/**Save a list of orders to the database. Overwrites all data with new data
	 * @param ObservableList<Order> */
	public static void saveOrders(ObservableList<Order> orderList) {
		MainController.saving.set(true);
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection connection = null;

		// connection.setAutoCommit(false);
		Statement statement;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pottery.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			dropOldOrders(connection);

			for (Order order : orderList) {
				int orderNumber = order.getOrderNumber();
				String orderDate = order.getOrderDate().toString();
				String dueDate = order.getDueDate().toString();
				String status = order.getStatus();
				String orderDesc = order.getOrderDesc();
				String paymentStatus = order.getPaymentStatus();
				String paymentMethod = order.getPaymentMethod();
				double price = order.getPrice();
				int customerNumber = order.getCustomerNumber();
				//String email = order.getEmail();
				//String phone = order.getPhoneNumber();
				//boolean smsEnabled = order.getSmsEnabled();
				//int smsEnabledInt = 0;
				//if (smsEnabled)
					//smsEnabledInt = 1;
				//String prefContactMethod = order.getPrefContactMethod();

				String insertOrderQuery = "INSERT INTO Orders("
						+ "orderNumber, orderDate, dueDate, status, orderDesc, "
						+ "paymentStatus, paymentMethod, price, customerID)"
						+ " VALUES(?, ?, ?, ?, ?, "
						+ "?, ?, ?, ?);";
				System.out.println(insertOrderQuery);
				PreparedStatement insertOrder = connection.prepareStatement(insertOrderQuery);
				insertOrder.setString(1, orderNumber + "");
				insertOrder.setString(2, orderDate);
				insertOrder.setString(3, dueDate);
				insertOrder.setString(4, status);
				insertOrder.setString(5, orderDesc);
				insertOrder.setString(6, paymentStatus);
				insertOrder.setString(7, paymentMethod);
				insertOrder.setString(8, price+"");
				insertOrder.setString(9, customerNumber+"");
				insertOrder.executeUpdate();

			}

		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.out.println("List Saved!");
			hideSavingLabel();
			
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

	}
	
	/**Save a list of orders to the database. Overwrites all data with new data
	 * @param ObservableList<Order> */
	public static void saveCustomers(ObservableList<Customer> customersList) {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection connection = null;

		// connection.setAutoCommit(false);
		Statement statement;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pottery.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			dropOldCustomers(connection);

			for (Customer customer : customersList) {
				int customerNumber = customer.getCustomerNumber();
				String firstName = customer.getFirstName();
				String lastName = customer.getLastName();
				String streetAddress = customer.getStreetAddress();
				String city = customer.getCity();
				String state = customer.getState();
				String zip = customer.getZip();
				String phone = customer.getPhoneNumber();
				String email = customer.getEmail();

				boolean smsEnabled = customer.getSMSEnabled();
				int smsEnabledInt = 0;
				if (smsEnabled)
					smsEnabledInt = 1;
				String prefContactMethod = customer.getPrefContactMethod();

				String insertCustomersQuery = "INSERT INTO Customers("
						+ "customerNumber, firstName, lastName, streetAddress, city, "
						+ "state, zip, phoneNumber, email, smsEnabled, prefContactMethod)"
						+ " VALUES(?, ?, ?, ?, ?, "
						+ "?, ?, ?, ?, ? ,?);";
				System.out.println(insertCustomersQuery);
				PreparedStatement insertCustomer = connection.prepareStatement(insertCustomersQuery);
				System.out.println("SAve customer statement prepared");
				insertCustomer.setString(1, customerNumber + "");
				insertCustomer.setString(2, firstName);
				insertCustomer.setString(3, lastName);
				insertCustomer.setString(4, streetAddress);
				insertCustomer.setString(5, city);
				insertCustomer.setString(6, state);
				insertCustomer.setString(7, zip);
				insertCustomer.setString(8, phone);
				insertCustomer.setString(9, email);
				insertCustomer.setString(10, smsEnabledInt+"");
				insertCustomer.setString(11, prefContactMethod);
				insertCustomer.executeUpdate();

			}

		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.out.println("List Saved!");
			//hideSavingLabel();
			
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

	}

	private static void dropOldCustomers(Connection connection) throws SQLException {
		String dropOldDataQuery = "DELETE FROM customers;";
		PreparedStatement dropOldData = connection.prepareStatement(dropOldDataQuery);
		dropOldData.executeUpdate();
	}



	private static void dropOldOrders(Connection connection) throws SQLException {
		String dropOldDataQuery = "DELETE FROM Orders;";
		PreparedStatement dropOldData = connection.prepareStatement(dropOldDataQuery);
		dropOldData.executeUpdate();
	}

	private static void hideSavingLabel() {
		Thread t1 = new Thread(new Runnable() {
		     public void run() {
		    	 try {
		    		 Thread.sleep(3000);
		    	 } catch (InterruptedException e) {
		    		 // TODO Auto-generated catch block
		    		 e.printStackTrace();
		    	 }
		    	 MainController.saving.set(false);
		     }
		});  
		t1.start();
	}
	

}


