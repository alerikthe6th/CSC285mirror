package edu.augustana.comorant.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import edu.augustana.comorant.dataStructures.Customer;
import edu.augustana.comorant.dataStructures.Order;
import edu.augustana.comorant.launchers.DataAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewOrderByCustomerController implements Initializable {

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

	public NewOrderByCustomerController() {

	}

	@FXML
	public void cancelOrderButtonPressed(ActionEvent e) {
		System.out.println("Cancel Order!");
		Stage stage = (Stage) btnCancelOrder.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert btnCancelOrder != null : "fx:id=\"cancelOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		populateDropDowns();
		
		//checks if the input phone number is of 7,10, or 11 digits and ignores dashes.
		//if invalid phone number entered, clears the field
		txtPhone.focusedProperty().addListener((observable, oldValue, newValue) ->{
			if(oldValue&& !newValue&& !txtPhone.getText().equals("")){
				String testPhone = txtPhone.getText();
				if (!(testPhone.length() == 7 ||testPhone.length() == 10 ||testPhone.length() == 11 ||//7,10,11 numbers 1234567,1234567890,112345678900
				(testPhone.length() == 8 &&testPhone.charAt(3)=='-')||//7nums and a hyphen 123-4567
				(testPhone.length() == 12 &&testPhone.charAt(3)=='-' &&testPhone.charAt(7)=='-' )||//10nums and 2 -'s 123-456-7890
				(testPhone.length() == 14 &&testPhone.charAt(1)=='-' &&testPhone.charAt(9)=='-' &&testPhone.charAt(9)=='-')||//11nums and 3 -'s 1-123-456-7890
				(testPhone.length() == 14 &&testPhone.charAt(0)=='(' &&testPhone.charAt(4)==')' &&testPhone.charAt(5)=='-' &&testPhone.charAt(9)=='-'))){//10 nums 1(,1),2- (123)-456-7890
					throwAlert("Phone Number", txtPhone);
				}
			}
		});

		//checks for a valid ZIP code input
		//invalid input clears the field
		txtZip.focusedProperty().addListener((observable, oldValue, newValue) ->{
			if(oldValue&& !newValue&& !txtZip.getText().equals("")){
				String testZip = txtZip.getText();
				if (!(testZip.length() == 5 ||testZip.length() == 9 ||(testZip.length() == 10 &&testZip.charAt(5)=='-')
						||testZip.length() == 6 &&  isLetter(testZip.charAt(0)) &&  isLetter(testZip.charAt(2)) &&  isLetter(testZip.charAt(4)))){
					throwAlert("ZIP Code", txtZip);
				}
			}
		});
		
		//checks if the price if a valid input i.e. no multiple '.', non-negative, or more than 2 decimal places
		//invalid input clears the field
		txtPrice.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue && !newValue && !txtPrice.getText().equals("")) {
				String testPrice = txtPrice.getText();
				try {
					for (int i = 0; i < txtPrice.getText().length(); i++) {
						if (txtPrice.getText().charAt(i) == '.') {

							if (txtPrice.getText().substring(i).length() > 3 || txtPrice.getText().charAt(0) == '-') {
								throwAlert("Price", txtPrice);
							}
						}
					}
					Double.parseDouble(testPrice);
				} catch (NumberFormatException npe) {
					throwAlert("Price", txtPrice);
				}
			}
		});
		
		//checks if the email contains only 1 '@' symbol
		//clears field if empty
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
	}
	/**
	 * Throws an alert for the parameters given. 
	 * Available combinations are: 
	 * "Phone Number" and txtPhone - 
	 * "Zip Code" and txtZip - 
	 * "Price" and txtPrice - 
	 * "Email Address" and txtEmail - 
	 * 
	 * ~!!STRINGS MUST MATCH EXACTLY!!~
	 * 
	 * @param invalidValueName - String
	 * @param valueName - TextField
	 * 
	 */
	protected void throwAlert(String invalidValueName, TextField valueName){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Invalid "+ invalidValueName);
		alert.setHeaderText(null);
		alert.setContentText("Please enter a valid "+invalidValueName+" into the "+invalidValueName+" field");
		alert.showAndWait();
		valueName.setText("");
		valueName.requestFocus();
	}
	/**
	 * Checks if a character is a letter. Similar to isDigit() 
	 * @param ch
	 * @return boolean
	 */
	protected boolean isLetter(char ch){
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
		if (txtZip.getText() != null  && !txtZip.getText().trim().isEmpty()) {
			saveZip = txtZip.getText();
		}
		if (cmbPaymentStatus.getValue() != null) {
			savePaymentStatus = cmbPaymentStatus.getValue().toString();
		}
		if (cmbPaymentMethod.getValue() != null) {
			savePaymentMethod = cmbPaymentMethod.getValue().toString();
		}
		if (txtPrice.getText() != null && !txtPrice.getText().trim().isEmpty()) {
			savePrice = Double.parseDouble(txtPrice.getText());
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

		Customer newCustomer = new Customer((mainController.getLargestCustomerNumber() + 1), saveFirstName, saveLastName,
				saveStreetAddress, saveCity, saveState, saveZip, savePhone, saveEmail, savePrefContactMethod, saveSmsEnabled);
		
		mainController.customerList.add(newCustomer);
		
		mainController.orderList.add(new Order(newCustomer, saveOrderNumber, saveOrderDate, saveDueDate, saveStatus, 
				saveOrderDesc, savePaymentStatus,
				savePaymentMethod, savePrice, ""));

		
		DataAccess.saveCustomers(mainController.customerList);
		DataAccess.saveOrders(mainController.orderList);
		
		System.out.println("Save Order!");
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
		
		ObservableList<String> statesList = FXCollections.observableArrayList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
				"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
				"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
				"Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
				"New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
				"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
				"Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "District of Columbia",
				 "Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador", 
				 "Nova Scotia", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan");
		cmbState.setItems(statesList);

	}


	

}