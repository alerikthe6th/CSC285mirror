/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

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
import edu.augustana.comorant.dataStructures.Preference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataAccess {

	public static ObservableList<Customer> loadCustomers() {
		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Connection connection = null;

		PreparedStatement getCustomers = null;
		ObservableList<Customer> customersList = FXCollections
				.observableArrayList();
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
				int rsCustomerNumber = customersResultSet
						.getInt("customerNumber");
				String rsFirstName = customersResultSet.getString("firstName");
				String rsLastName = customersResultSet.getString("lastName");
				String rsStreetAddress = customersResultSet
						.getString("streetAddress");
				String rsCity = customersResultSet.getString("city");
				String rsState = customersResultSet.getString("state");
				String rsZip = customersResultSet.getString("zip");
				String rsEmail = customersResultSet.getString("email");
				String rsPhone = customersResultSet.getString("phoneNumber");
				boolean rsSmsEnabled = customersResultSet
						.getBoolean("smsEnabled");
				String rsPrefContactMethod = customersResultSet
						.getString("prefContactMethod");
				customersList.add(new Customer(rsCustomerNumber, rsFirstName,
						rsLastName, rsStreetAddress, rsCity, rsState, rsZip,
						rsPhone, rsEmail, rsPrefContactMethod, rsSmsEnabled));
			}

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
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

	/**
	 * Returns the list of orders loaded from the database MainController's
	 * orderList should be set equal to the returned list at launch
	 * 
	 * @return ObservableList<Order>
	 */
	public static ObservableList<Order> loadOrders(
			ObservableList<Customer> customersList) {

		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
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

			// Get all orders from Orders table
			String ordersQuery = "SELECT * FROM Orders";
			getOrders = connection.prepareStatement(ordersQuery);
			ResultSet ordersResultSet = getOrders.executeQuery();
			// DateTimeFormatter sqlFormatter =
			// DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS.SSS");
			while (ordersResultSet.next()) {
				int customerNumber = ordersResultSet.getInt("customerID");
				Customer rsCustomer = null;
				for (Customer thisCustomer : customersList) {
					if (thisCustomer.getCustomerNumber() == customerNumber) {
						rsCustomer = thisCustomer;
						break;
					}
				}
				int rsOrderNumber = ordersResultSet.getInt("orderNumber");
				LocalDate rsOrderDate = LocalDate.parse(ordersResultSet
						.getString("orderDate"));
				LocalDate rsDueDate = LocalDate.parse(ordersResultSet
						.getString("dueDate"));
				String rsStatus = ordersResultSet.getString("status");
				String rsOrderDesc = ordersResultSet.getString("orderDesc");
				String rsPaymentStatus = ordersResultSet
						.getString("paymentStatus");
				String rsPaymentMethod = ordersResultSet
						.getString("paymentMethod");
				double rsPrice = ordersResultSet.getDouble("price");
				String rsPriceExp = ordersResultSet.getString("priceExp");
				ordersList.add(new Order(rsCustomer, rsOrderNumber,
						rsOrderDate, rsDueDate, rsStatus, rsOrderDesc,
						rsPaymentStatus, rsPaymentMethod, rsPrice, rsPriceExp));
			}

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
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
	
	/**
	 * Returns the list of orders loaded from the database MainController's
	 * orderList should be set equal to the returned list at launch
	 * 
	 * @return ObservableList<Order>
	 */
	public static Preference loadPreference() {

		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Connection connection = null;
		Preference retPref = null;

		PreparedStatement getPref = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:Pottery.db");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			createPreferenceTable(connection);

			// Get all orders from Orders table
			String prefQuery = "SELECT * FROM Preferences";
			getPref = connection.prepareStatement(prefQuery);
			ResultSet prefResultSet = getPref.executeQuery();
			// DateTimeFormatter sqlFormatter =
			// DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS.SSS");
			String businessName = "Sondahl Pottery";
			String streetAddress = "6326 Maine St";
			String city = "Spirit Lake";
			String state = "Idaho";
			String zip = "83869";
			double tax = 0.06;
			
			if (prefResultSet.next()) {
				businessName = prefResultSet.getString("businessName");
				streetAddress = prefResultSet.getString("streetAddress");
				city = prefResultSet.getString("city");
				state = prefResultSet.getString("state");
				zip = prefResultSet.getString("zip");
				tax = prefResultSet.getDouble("tax");
			}
			
			retPref = new Preference(businessName, streetAddress, city, state, zip, tax); 

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
		return retPref;
	}

	/**
	 * Creates a new table of orders
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	private static void createOrdersTable(Connection connection)
			throws SQLException {
		PreparedStatement createOrdersTable;
		String createOrdersTableQuery = "CREATE TABLE IF NOT EXISTS Orders("
				+ "orderNumber INTEGER PRIMARY KEY, " + " orderDate TEXT, "
				+ " dueDate TEXT, " + " status TEXT, " + " orderDesc TEXT, "
				+ " paymentStatus TEXT, " + " paymentMethod TEXT, "
				+ " price REAL, " + " priceExp TEXT, "
				+ " customerID INTEGER);";

		createOrdersTable = connection.prepareStatement(createOrdersTableQuery);
		createOrdersTable.executeUpdate();
	}

	/**
	 * Creates a new table of customers
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	private static void createCustomersTable(Connection connection)
			throws SQLException {
		PreparedStatement createCustomersTable;
		String createCustomersTableQuery = "CREATE TABLE IF NOT EXISTS Customers("
				+ "customerNumber INTEGER PRIMARY KEY, "
				+ " firstName TEXT, "
				+ " lastName TEXT, "
				+ " streetAddress TEXT, "
				+ " city TEXT, "
				+ " state TEXT, "
				+ " zip TEXT, "
				+ " phoneNumber TEXT, "
				+ " email TEXT, "
				+ " prefContactMethod TEXT, "
				+ " smsEnabled INTEGER);";

		createCustomersTable = connection
				.prepareStatement(createCustomersTableQuery);
		createCustomersTable.executeUpdate();
	}

	/**
	 * Creates a new table of preferences
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	private static void createPreferenceTable(Connection connection)
			throws SQLException {
		PreparedStatement createPreferencesTable;
		String createPreferencesTableQuery = "CREATE TABLE IF NOT EXISTS Preferences("
				+ "preferenceNumber INTEGER PRIMARY KEY, "
				+ " businessName TEXT, "
				+ " streetAddress TEXT, "
				+ " city TEXT, "
				+ " state TEXT, "
				+ " zip TEXT, "
				+ " tax REAL);";

		createPreferencesTable = connection
				.prepareStatement(createPreferencesTableQuery);
		createPreferencesTable.executeUpdate();
	}

	/**
	 * Save a list of orders to the database. Overwrites all data with new data
	 * 
	 * @param ObservableList
	 *            <Order>
	 */
	public static void saveOrders(ObservableList<Order> orderList) {
		MainController.saving.set(true);
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
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
				String priceExp = order.getPriceExp();

				String insertOrderQuery = "INSERT INTO Orders("
						+ "orderNumber, orderDate, dueDate, status, orderDesc, "
						+ "paymentStatus, paymentMethod, price, priceExp, customerID)"
						+ " VALUES(?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?);";
				PreparedStatement insertOrder = connection
						.prepareStatement(insertOrderQuery);
				insertOrder.setString(1, orderNumber + "");
				insertOrder.setString(2, orderDate);
				insertOrder.setString(3, dueDate);
				insertOrder.setString(4, status);
				insertOrder.setString(5, orderDesc);
				insertOrder.setString(6, paymentStatus);
				insertOrder.setString(7, paymentMethod);
				insertOrder.setString(8, price + "");
				insertOrder.setString(9, priceExp);
				insertOrder.setString(10, customerNumber + "");
				insertOrder.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	/**
	 * Save a list of orders to the database. Overwrites all data with new data
	 * 
	 * @param ObservableList
	 *            <Order>
	 */
	public static void saveCustomers(ObservableList<Customer> customersList) {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
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
						+ " VALUES(?, ?, ?, ?, ?, " + "?, ?, ?, ?, ? ,?);";
				PreparedStatement insertCustomer = connection
						.prepareStatement(insertCustomersQuery);
				insertCustomer.setString(1, customerNumber + "");
				insertCustomer.setString(2, firstName);
				insertCustomer.setString(3, lastName);
				insertCustomer.setString(4, streetAddress);
				insertCustomer.setString(5, city);
				insertCustomer.setString(6, state);
				insertCustomer.setString(7, zip);
				insertCustomer.setString(8, phone);
				insertCustomer.setString(9, email);
				insertCustomer.setString(10, smsEnabledInt + "");
				insertCustomer.setString(11, prefContactMethod);
				insertCustomer.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

	}

	/**
	 * Save a preference of orders to the database. Overwrites all data with new
	 * data
	 * 
	 * @param ObservableList
	 *            <Order>
	 */
	public static void savePreference(Preference pref) {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;

		// connection.setAutoCommit(false);
		Statement statement;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Pottery.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			dropOldPreferences(connection);


			String businessName = pref.getBusinessName();
			String streetAddress = pref.getStreetAddress();
			String city = pref.getCity();
			String state = pref.getState();
			String zip = pref.getZip();
			double tax = pref.getTax();
			

			String insertPreferencesQuery = "INSERT INTO Preferences("
					+ "businessName, streetAddress, city, "
					+ "state, zip, tax)"
					+ " VALUES(?, ?, ?, ?, ?, ?); ";
			PreparedStatement insertCustomer = connection
					.prepareStatement(insertPreferencesQuery);
			insertCustomer.setString(1, businessName);
			insertCustomer.setString(2, streetAddress);
			insertCustomer.setString(3, city);
			insertCustomer.setString(4, state);
			insertCustomer.setString(5, zip);
			insertCustomer.setString(6, tax+"");
			insertCustomer.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

	}

	// TODO javadoc
	private static void dropOldCustomers(Connection connection)
			throws SQLException {
		String dropOldDataQuery = "DELETE FROM customers;";
		PreparedStatement dropOldData = connection
				.prepareStatement(dropOldDataQuery);
		dropOldData.executeUpdate();
	}

	// TODO javadoc
	private static void dropOldOrders(Connection connection)
			throws SQLException {
		String dropOldDataQuery = "DELETE FROM Orders;";
		PreparedStatement dropOldData = connection
				.prepareStatement(dropOldDataQuery);
		dropOldData.executeUpdate();
	}

	// TODO javadoc
	private static void dropOldPreferences(Connection connection)
			throws SQLException {
		String dropOldDataQuery = "DELETE FROM Preferences;";
		PreparedStatement dropOldData = connection
				.prepareStatement(dropOldDataQuery);
		dropOldData.executeUpdate();
	}

	/**
	 * Hides "saving..." on window after 3 seconds
	 */
	private static void hideSavingLabel() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				MainController.saving.set(false);
			}
		});
		t1.start();
	}

}
