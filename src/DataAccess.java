
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import org.sqlite.SQLiteConfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataAccess {

	public DataAccess() {

	}

	public static ObservableList<Order> loadOrders() {

		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement createOrdersTable = null;
		PreparedStatement getOrders = null;
		ObservableList<Order> ordersList = FXCollections.observableArrayList();
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:Pottery.db");

			// connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// DatabaseMetaData meta = connection.getMetaData();
			// String checkForOrdersTableQuery ="SELECT name FROM sqlite_master
			// WHERE type='table' AND name='Orders';";
			// PreparedStatement checkForOrdersTable =
			// connection.prepareStatement(checkForOrdersTableQuery);
			// ResultSet table = checkForOrdersTable.executeQuery();
			// If Orders table doesn't exist, create it.

			String createOrdersTableQuery = "CREATE TABLE IF NOT EXISTS Orders("
					+ "orderNumber INTEGER PRIMARY KEY AUTOINCREMENT, " + " orderDate TEXT, " + " dueDate TEXT, "
					+ " status TEXT, " + " firstName TEXT, " + " lastName TEXT, " + " orderDesc TEXT, "
					+ " shippingAddress TEXT, " + " streetAddress TEXT, " + " city TEXT, " + " state TEXT, "
					+ " zip TEXT, " + " paymentStatus TEXT, " + " paymentMethod TEXT, " + " price REAL, "
					+ " email TEXT, " + " phone TEXT, " + " smsEnabled INTEGER, " + " prefContactMethod TEXT);";

			createOrdersTable = connection.prepareStatement(createOrdersTableQuery);
			createOrdersTable.executeUpdate();

			// INSERT DUMMY DATA
			// TODO remove dummy data insert
			// Remove old dummy data
			String removeOldDummyDataQuery = "DELETE FROM Orders WHERE zip='61201'";
			PreparedStatement removeOldDummyData = connection.prepareStatement(removeOldDummyDataQuery);
			removeOldDummyData.executeUpdate();
			// Insert new dummy data
			String insertDummyDataQuery = "INSERT INTO Orders("
					+ "orderDate, dueDate, status, firstName, lastName, orderDesc, streetAddress, city,"
					+ " state, zip, paymentStatus, paymentMethod, price, email, phone, smsEnabled, prefContactMethod)"
					+ " VALUES('2015-09-15', '2015-10-31', 'Incomplete', 'James', 'Smith', "
					+ "'Two mugs please', '136 Required Dr.', 'Rock Island', 'Illinois', '61201', 'Unpaid', 'Cash', 136.52, "
					+ "'michaelcurrie12@augustana.edu', '555-555-5555', 1, 'Email');";

			PreparedStatement insertDummyData = connection.prepareStatement(insertDummyDataQuery);
			insertDummyData.executeUpdate();

			String insertDummyDataQuery2 = "INSERT INTO Orders("
					+ "orderDate, dueDate, status, firstName, lastName, orderDesc, streetAddress, city,"
					+ " state, zip, paymentStatus, paymentMethod, price, email, phone, smsEnabled, prefContactMethod)"
					+ " VALUES('2015-09-18', '2015-12-25', 'Incomplete', 'Saint', 'Nick', "
					+ "'Christmas plate and mug', '1010 North Pole Dr.', 'North Pole', 'The Arctic Circle', '61201', 'Unpaid', 'Cash', 100.01, "
					+ "'michaelcurrie12@augustana.edu', '555-555-5555', 1, 'Email');";

			PreparedStatement insertDummyData2 = connection.prepareStatement(insertDummyDataQuery2);
			insertDummyData2.executeUpdate();

			// Get all orders from Orders table
			String ordersQuery = "SELECT * FROM Orders";
			getOrders = connection.prepareStatement(ordersQuery);
			ResultSet ordersResultSet = getOrders.executeQuery();
			// DateTimeFormatter sqlFormatter =
			// DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS.SSS");
			while (ordersResultSet.next()) {
				int rsOrderNumber = ordersResultSet.getInt("orderNumber");
				LocalDate rsOrderDate = LocalDate.parse(ordersResultSet.getString("orderDate"));
				LocalDate rsDueDate = LocalDate.parse(ordersResultSet.getString("dueDate"));
				String rsStatus = ordersResultSet.getString("status");
				String rsFirstName = ordersResultSet.getString("firstName");
				String rsLastName = ordersResultSet.getString("lastName");
				String rsOrderDesc = ordersResultSet.getString("orderDesc");
				// String rsShippingAddress =
				// ordersResultSet.getString("shippingAddress");
				String rsStreetAddress = ordersResultSet.getString("streetAddress");
				String rsCity = ordersResultSet.getString("city");
				String rsState = ordersResultSet.getString("state");
				String rsZip = ordersResultSet.getString("zip");
				String rsPaymentStatus = ordersResultSet.getString("paymentStatus");
				String rsPaymentMethod = ordersResultSet.getString("paymentMethod");
				double rsPrice = ordersResultSet.getDouble("price");
				String rsEmail = ordersResultSet.getString("email");
				String rsPhone = ordersResultSet.getString("phone");
				boolean rsSmsEnabled = ordersResultSet.getBoolean("smsEnabled");
				String rsPrefContactMethod = ordersResultSet.getString("prefContactMethod");
				ordersList.add(new Order(rsOrderNumber, rsOrderDate, rsDueDate, rsStatus, rsFirstName, rsLastName,
						rsOrderDesc, rsStreetAddress, rsCity, rsState, rsZip, rsPaymentStatus, rsPaymentMethod, rsPrice,
						rsEmail, rsPhone, rsSmsEnabled, rsPrefContactMethod));
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

	@SuppressWarnings({ "null" })
	public static void saveOrders(ObservableList<Order> orderList) {
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

			LinkedList<Integer> orderNumberList = new LinkedList<Integer>();;
			String getOrderNumberQuery = "SELECT orderNumber FROM Orders";
			PreparedStatement getOrderNumbers = connection.prepareStatement(getOrderNumberQuery);
			ResultSet rsOrderNumbers = getOrderNumbers.executeQuery();
			while (rsOrderNumbers.next()) {
				System.out.println(rsOrderNumbers.getInt("orderNumber"));
				orderNumberList.add(new Integer(rsOrderNumbers.getInt("orderNumber")));
			}

			for (Order order : orderList) {
				int orderNumber = order.getOrderNumber();
				String orderDate = order.getOrderDate().toString();
				String dueDate = order.getDueDate().toString();
				String status = order.getStatus();
				String firstName = order.getFirstName();
				String lastName = order.getLastName();
				String orderDesc = order.getOrderDesc();
				// String rsShippingAddress =
				// ordersResultSet.getString("shippingAddress");
				String streetAddress = order.getStreetAddress();
				String city = order.getCity();
				String state = order.getState();
				String zip = order.getZip();
				String paymentStatus = order.getPaymentStatus();
				String paymentMethod = order.getPaymentMethod();
				double price = order.getPrice();
				String email = order.getEmail();
				String phone = order.getPhoneNumber();
				boolean smsEnabled = order.getSmsEnabled();
				int smsEnabledInt = 0;
				if (smsEnabled)
					smsEnabledInt = 1;
				String prefContactMethod = order.getPrefContactMethod();

				if (orderNumberList.contains(new Integer(orderNumber))) {
					String updateOrdersQuery = "Update Orders SET orderDate = " + orderDate + ", dueDate = " + dueDate
							+ ", status = " + status + ", firstName = " + firstName + ", lastName = " + lastName
							+ ", orderDesc = " + orderDesc + ", streetAddress = " + streetAddress + ", city = " + city
							+ ",state = " + state + ",zip = " + zip + ",paymentStatus = " + paymentStatus
							+ ", paymentMethod = " + paymentMethod + ",price = " + price + ", email = " + email
							+ ", phone = " + phone + ", smsEnabled = " + smsEnabledInt + ",prefContactMethod = "
							+ prefContactMethod + "WHERE orderNumber = " + orderNumber + ";";
					PreparedStatement updateOrder = connection.prepareStatement(updateOrdersQuery);
					updateOrder.executeUpdate();

				} else {
					String insertOrderQuery = "INSERT INTO Orders("
							+ "orderDate, dueDate, status, firstName, lastName, orderDesc, streetAddress, city,"
							+ " state, zip, paymentStatus, paymentMethod, price, email, phone, smsEnabled, prefContactMethod)"
							+ " VALUES('" + orderDate + "', '" + dueDate + "', '" + status + "', '" + firstName + "', '"
							+ lastName + "', " + "'" + orderDesc + "', '" + streetAddress + "', '" + city + "', '"
							+ state + "', '" + zip + "', '" + paymentStatus + "', '" + paymentMethod + "', " + price
							+ ", " + "'" + email + "', '" + phone + "', " + smsEnabledInt + ", '" + prefContactMethod
							+ "');";
					PreparedStatement insertOrder = connection.prepareStatement(insertOrderQuery);
					insertOrder.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	}

}

