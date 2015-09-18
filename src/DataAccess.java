import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.sqlite.SQLiteConfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataAccess {

	public DataAccess() {

	}

	public static ObservableList<Order> loadOrders() throws ClassNotFoundException {

		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		PreparedStatement createOrdersTable = null;
		PreparedStatement getOrders = null;
		ObservableList<Order> ordersList = FXCollections.observableArrayList();
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:Pottery.db");
			

			//connection.setAutoCommit(false);
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
			//Remove old dummy data
			String removeOldDummyDataQuery = "DELETE FROM Orders WHERE zip='61201'";
			PreparedStatement removeOldDummyData = connection.prepareStatement(removeOldDummyDataQuery);
			removeOldDummyData.executeUpdate();
			//Insert new dummy data
			String insertDummyDataQuery = "INSERT INTO Orders("
					+ "orderDate, dueDate, status, firstName, lastName, orderDesc, streetAddress, city,"
					+ " state, zip, paymentStatus, paymentMethod, price, email, phone, smsEnabled, prefContactMethod)"
					+ " VALUES('2015-09-15', '2015-10-31', 'Incomplete', 'James', 'Smith', "
					+ "'Two mugs please', '136 Required Dr.', 'Rock Island', 'Illinois', '61201', 'Unpaid', 'Cash', 136.52, "
					+ "'michaelcurrie12@augustana.edu', '555-555-5555', 1, 'Email');";

			PreparedStatement insertDummyData = connection.prepareStatement(insertDummyDataQuery);
			insertDummyData.executeUpdate();
			
			//Get all orders from Orders table
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

}
