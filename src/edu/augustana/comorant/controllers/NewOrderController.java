/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

package edu.augustana.comorant.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import edu.augustana.comorant.dataStructures.Customer;
import edu.augustana.comorant.dataStructures.Order;
import edu.augustana.comorant.launchers.DataAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class NewOrderController implements Initializable {

	@FXML
	private Button btnCancelOrder;
	@FXML
	private Button btnSaveOrder;
	@FXML
	private MainController mainController;
	@FXML
	private TextField txtOrderNumber;
	@FXML
	private DatePicker dtpkOrderDate;
	@FXML
	private DatePicker dtpkDueDate;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private Button btnAutoFill;
	@FXML
	private TextArea txtOrderDesc;
	@FXML
	private TextField txtStreetAddress;
	@FXML
	private TextField txtCity;
	@FXML
	private ComboBox<String> cmbState;
	@FXML
	private TextField txtZip;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtEmail;
	@FXML
	private ComboBox<String> cmbOrderStatus;
	@FXML
	private ComboBox<String> cmbPrefContactMethod;
	@FXML
	private CheckBox chkSMSEnabled;
	@FXML
	private TextField txtPrice;
	@FXML
	private ComboBox<String> cmbPaymentMethod;
	@FXML
	private ComboBox<String> cmbPaymentStatus;
	@FXML
	private Label lblResult;
	@FXML 
	private Label lblTax;

	ObservableList<Customer> matchedCustomers = FXCollections.observableArrayList();

	private Customer matchedCustomer = null;
	boolean usingMatchedCustomer = false;

	/**
	 * Cancels the new order
	 */
	@FXML
	public void cancelOrderButtonPressed(ActionEvent e) {
		Stage stage = (Stage) btnCancelOrder.getScene().getWindow();
		stage.close();
	}
	/**
	 * Creates a NewOrderController and initializes it
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert btnCancelOrder != null : "fx:id=\"cancelOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		populateDropDowns();

		// checks if the input phone number is of 7,10, or 11 digits and ignores dashes.
		// if invalid phone number entered, clears the field
		txtPhone.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue && !newValue && !txtPhone.getText().equals("")) {
				String testPhone = txtPhone.getText();
				// 7,10,11 nums 1234567,1234567890,112345678900
				if (!(testPhone.length() == 7 || testPhone.length() == 10 || testPhone.length() == 11 || 
						// 7,10,11 nums 1234567,1234567890,112345678900
				(testPhone.length() == 8 && testPhone.charAt(3) == '-') || 
				// 10nums and 2 hyphens 123-456-7890
				(testPhone.length() == 12 && testPhone.charAt(3) == '-' && testPhone.charAt(7) == '-') || 
				// 11nums and 3 -'s 1-123-456-7890
				(testPhone.length() == 14 && testPhone.charAt(1) == '-' && testPhone.charAt(9) == '-'
				&& testPhone.charAt(9) == '-') || 
				//10 nums, one of each parentheses and two hyphens (123)-456-7890
				(testPhone.length() == 14 && testPhone.charAt(0) == '(' && testPhone.charAt(4) == ')'
						&& testPhone.charAt(5) == '-' && testPhone.charAt(9) == '-'))) {
					throwAlert("Phone Number", txtPhone);
				}
			}
		});

		// checks for a valid ZIP code input
		// invalid input clears the field
		txtZip.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue && !newValue && !txtZip.getText().equals("")) {
				String testZip = txtZip.getText();
				if (!(testZip.length() == 5 || testZip.length() == 9
						|| (testZip.length() == 10 && testZip.charAt(5) == '-')
						|| testZip.length() == 6 && isLetter(testZip.charAt(0)) && isLetter(testZip.charAt(2))
								&& isLetter(testZip.charAt(4)))) {
					throwAlert("ZIP Code", txtZip);
				}
			}
		});

		// checks if the price if a valid input i.e. no multiple '.',
		// non-negative, or more than 2 decimal places
		// invalid input clears the field
		txtPrice.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue && !newValue && !txtPrice.getText().equals("")) {
				try {
					String priceExp = txtPrice.getText();
					Expression expr = new ExpressionBuilder(priceExp).build();
					double resultPrice = expr.evaluate();
					if (resultPrice < 0) {
						throw new IllegalArgumentException();
					}
				} catch (IllegalArgumentException iae) {
					throwAlert("Price", txtPrice);
				}
			}
		});
		//checks to see if the price inserted is valid and if not sets it blank
		txtPrice.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				String priceExp = newValue;
				Expression expr = new ExpressionBuilder(priceExp).build();
				double resultPrice = expr.evaluate();
				double tax = resultPrice * mainController.getCurrentPreference().getTax();
				resultPrice = resultPrice + tax;

				if (resultPrice < 0) {
					throw new IllegalArgumentException();
				}
				DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
				String priceString = twoDigitFormat.format(resultPrice);
				String taxString = twoDigitFormat.format(tax);
				lblResult.setText(priceString);
				lblTax.setText(taxString);
			} catch (IllegalArgumentException iae) {
				lblResult.setText("...");
				lblTax.setText("...");
			}
		});

		// checks if the email contains only 1 '@' symbol
		// clears field if empty
		txtEmail.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue == true && newValue == false && !txtEmail.getText().equals("")) {
				int atCount = 0;
				for (int i = 0; i < txtEmail.getText().length(); i++) {
					if (txtEmail.getText().charAt(i) == '@') {
						atCount++;
					}
				}
				if (atCount != 1) {
					throwAlert("Email", txtEmail);
				}
			}
		});

		txtFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
			matchNameToCustomer();
		});

		txtLastName.textProperty().addListener((observable, oldValue, newValue) -> {
			matchNameToCustomer();
		});
	}

	/**
	 * Throws an alert for the parameters given. Available combinations are:
	 * "Phone Number" and txtPhone - "Zip Code" and txtZip - "Price" and
	 * txtPrice - "Email Address" and txtEmail -
	 * 
	 * ~!!STRINGS MUST MATCH EXACTLY!!~
	 * 
	 * @param invalidValueName
	 *            - String
	 * @param valueName
	 *            - TextField
	 * 
	 */
	protected void throwAlert(String invalidValueName, TextField valueName) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Invalid " + invalidValueName);
		alert.setHeaderText(null);
		alert.setContentText("Please enter a valid " + invalidValueName + " into the " + invalidValueName + " field");
		alert.showAndWait();
		valueName.setText("");
		valueName.requestFocus();
	}

	/**
	 * Checks if a character is a letter. Similar to isDigit()
	 * 
	 * @param ch
	 * @return boolean
	 */
	protected boolean isLetter(char ch) {
		return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'));
	}

	/**
	 * Called by the Main Controller. Used to pass a reference of the Main
	 * Controller to NewOrderController so that it can reference the orderList
	 */
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
		setOrderNumber();
	}

	/**
	 * Finds the largest order number in the list and increments it by one. Sets
	 * the Order Number textbox to that number
	 */
	private void setOrderNumber() {
		String newOrderNumber = (mainController.getLargestOrderNumber() + 1) + "";
		txtOrderNumber.setText(newOrderNumber);
		dtpkOrderDate.setValue(LocalDate.now());

	}

	/**
	 * Adds the new order to the main controller's orderList. Defaults empty
	 * boxes to empty Strings. Then saves orders to the DB.
	 */
	@FXML
	public void onSaveButtonPressed(ActionEvent e) {
		// Set default values here
		int saveOrderNumber = Integer.parseInt(txtOrderNumber.getText());
		LocalDate saveOrderDate = LocalDate.now();
		LocalDate saveDueDate = LocalDate.now();
		String saveStatus = "";
		String saveFirstName = "";
		String saveLastName = "";
		String saveOrderDesc = "";
		String saveStreetAddress = "";
		String saveCity = "";
		String saveState = "";
		String saveZip = "";
		String savePaymentStatus = "";
		String savePaymentMethod = "";
		double savePrice = 0;
		String saveEmail = "";
		String savePhone = "";
		boolean saveSmsEnabled = chkSMSEnabled.isSelected();
		String savePrefContactMethod = "";
		String savePriceExp = "";

		if (dtpkOrderDate.getValue() != null) {
			saveOrderDate = dtpkOrderDate.getValue();
		}
		if (dtpkDueDate.getValue() != null) {
			saveDueDate = dtpkDueDate.getValue();
		}
		if (cmbOrderStatus.getValue() != null) {
			saveStatus = cmbOrderStatus.getValue().toString();
		}
		if (txtFirstName.getText() != null && !txtFirstName.getText().trim().isEmpty()) {
			saveFirstName = txtFirstName.getText();
		}
		if (txtLastName.getText() != null && !txtLastName.getText().trim().isEmpty()) {
			saveLastName = txtLastName.getText();
		}
		if (txtOrderDesc.getText() != null && !txtOrderDesc.getText().trim().isEmpty()) {
			saveOrderDesc = txtOrderDesc.getText();
		}
		if (txtStreetAddress.getText() != null && !txtStreetAddress.getText().trim().isEmpty()) {
			saveStreetAddress = txtStreetAddress.getText();
		}
		if (txtCity.getText() != null && !txtCity.getText().trim().isEmpty()) {
			saveCity = txtCity.getText();
		}
		if (cmbState.getValue() != null && !cmbState.getValue().trim().isEmpty()) {
			saveState = cmbState.getValue();
		}
		if (txtZip.getText() != null && !txtZip.getText().trim().isEmpty()) {
			saveZip = txtZip.getText();
		}
		if (cmbPaymentStatus.getValue() != null) {
			savePaymentStatus = cmbPaymentStatus.getValue().toString();
		}
		if (cmbPaymentMethod.getValue() != null) {
			savePaymentMethod = cmbPaymentMethod.getValue().toString();
		}
		if (txtPrice.getText() != null && !txtPrice.getText().trim().isEmpty()) {
			savePriceExp = txtPrice.getText();
		}
		if (!lblResult.getText().equals("...")) {
			savePrice = Double.parseDouble(lblResult.getText());
		}
		if (txtEmail.getText() != null && !txtEmail.getText().trim().isEmpty()) {
			saveEmail = txtEmail.getText();
		}
		if (txtPhone.getText() != null && !txtPhone.getText().trim().isEmpty()) {
			savePhone = txtPhone.getText();
		}
		if (cmbPrefContactMethod.getValue() != null) {
			savePrefContactMethod = cmbPrefContactMethod.getValue().toString();
		}

		Customer newCustomer = new Customer((mainController.getLargestCustomerNumber() + 1), saveFirstName,
				saveLastName, saveStreetAddress, saveCity, saveState, saveZip, savePhone, saveEmail,
				savePrefContactMethod, saveSmsEnabled);

		if (matchedCustomer != null && matchedCustomer.equals(newCustomer)) {
			newCustomer = matchedCustomer;
			newCustomer.setStreetAddress(saveStreetAddress);
			newCustomer.setCity(saveCity);
			newCustomer.setState(saveState);
			newCustomer.setZip(saveZip);
			newCustomer.setPhoneNumber(savePhone);
			newCustomer.setEmail(saveEmail);
			newCustomer.setPrefContactMethod(savePrefContactMethod);
			newCustomer.setSmsEnabled(saveSmsEnabled);

		} else {

			mainController.customerList.add(newCustomer);
		}

		Order newOrder = new Order(newCustomer, saveOrderNumber, saveOrderDate, saveDueDate, saveStatus, saveOrderDesc,
				savePaymentStatus, savePaymentMethod, savePrice, savePriceExp);

		mainController.orderList.add(newOrder);

		DataAccess.saveCustomers(mainController.customerList);
		DataAccess.saveOrders(mainController.orderList);

		Stage stage = (Stage) btnSaveOrder.getScene().getWindow();
		stage.close();

	}

	/**
	 * Fills the comboboxes with the appropriate strings
	 */
	private void populateDropDowns() {

		// set order status
		ObservableList<String> orderStatusOptions = FXCollections.observableArrayList("Order Received", "Pot Thrown",
				"Pot Trimmed/Assembled", "Pot Fired", "Pot Glazed", "Ready to Ship", "Completed");
		cmbOrderStatus.setItems(orderStatusOptions);

		// set payment status
		ObservableList<String> paymentStatusOptions = FXCollections.observableArrayList("Paid", "Unpaid");
		cmbPaymentStatus.setItems(paymentStatusOptions);

		// set payment method
		ObservableList<String> paymentMethodOptions = FXCollections.observableArrayList("Cash", "Check", "Credit Card",
				"Debit Card");
		cmbPaymentMethod.setItems(paymentMethodOptions);

		// set payment method
		ObservableList<String> contactOptions = FXCollections.observableArrayList("Email", "Phone", "Text");
		cmbPrefContactMethod.setItems(contactOptions);

		ObservableList<String> statesList = FXCollections.observableArrayList("Alabama", "Alaska", "Arizona", 
				"Arkansas", "California", "Colorado", "Connecticut","Delaware", "Florida", "Georgia", "Hawaii",
				"Idaho", "Illinois", "Indiana", "Iowa", "Kansas","Kentucky", "Louisiana", "Maine", "Maryland",
				"Massachusetts", "Michigan", "Minnesota",	"Mississippi", "Missouri", "Montana", "Nebraska",
				"Nevada", "New Hampshire", "New Jersey","New Mexico", "New York", "North Carolina", 
				"North Dakota", "Ohio", "Oklahoma", "Oregon","Pennsylvania", "Rhode Island", "South Carolina",
				"South Dakota", "Tennessee", "Texas","Utah", "Vermont", "Virginia", "Washington", 
				"West Virginia", "Wisconsin", "Wyoming", "District of Columbia", "Alberta", "British Columbia",
				"Manitoba", "New Brunswick", "Newfoundland and Labrador", "Nova Scotia", "Northwest Territories",
				"Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon");
		cmbState.setItems(statesList);

	}
	/**
	 * Checks if the inputed name matches any previous customers
	 */
	@FXML
	public void matchNameToCustomer() {
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();

		for (Customer customer : mainController.customerList) {
			if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
				btnAutoFill.setDisable(false);
				matchedCustomers.add(customer);
				matchedCustomer = customer;
			}
		}

		if (matchedCustomers.size() == 0) {
			matchedCustomer = null;
			btnAutoFill.setDisable(true);

		}

	}
	/**
	 * If the name matches older customers and you create a 
	 * new order w/ an old customer it autofills
	 * @param e
	 */
	@FXML
	public void onAutoFill(ActionEvent e) {

		if (matchedCustomers.size() == 1) {
			matchedCustomer = matchedCustomers.get(0);
			txtStreetAddress.setText(matchedCustomer.getStreetAddress());
			txtCity.setText(matchedCustomer.getCity());
			cmbState.setValue(matchedCustomer.getState());
			txtZip.setText(matchedCustomer.getZip());
			txtEmail.setText(matchedCustomer.getEmail());
			txtPhone.setText(matchedCustomer.getPhoneNumber());
			cmbPrefContactMethod.setValue(matchedCustomer.getPrefContactMethod());
			chkSMSEnabled.setSelected(matchedCustomer.getSMSEnabled());
		} else {
			Parent root;
			try {

				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/edu/augustana/comorant/fxml/shippingAddressGUI.fxml"));
				root = loader.load();
				ShippingAddressController sac = (ShippingAddressController) loader.getController();
				sac.setCustomerList(this, matchedCustomers);
				Stage stage = new Stage();
				stage.setTitle("Shipping Addresses");
				stage.setScene(new Scene(root));
				stage.show();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	/**
	 * Autofills the customer data into the order 
	 * @param customer
	 */
	void setMatchedCustomer(Customer customer) {
		matchedCustomer = customer;
		if (matchedCustomer != null) {
			txtStreetAddress.setText(matchedCustomer.getStreetAddress());
			txtCity.setText(matchedCustomer.getCity());
			cmbState.setValue(matchedCustomer.getState());
			txtZip.setText(matchedCustomer.getZip());
			txtEmail.setText(matchedCustomer.getEmail());
			txtPhone.setText(matchedCustomer.getPhoneNumber());
			cmbPrefContactMethod.setValue(matchedCustomer.getPrefContactMethod());
			chkSMSEnabled.setSelected(matchedCustomer.getSMSEnabled());
		}

	}
}
