/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */



package edu.augustana.comorant.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class EditOrderController implements Initializable {

	Order editedOrder;
	@FXML
	private Button btnCancelEdit;
	@FXML
	private Button btnSaveEdit;
	@FXML
	private Button btnDeleteOrder;
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
	private TextField txtStreetAddressLine2;
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

	@FXML
	public void cancelEditButtonPressed(ActionEvent e) {
		Stage stage = (Stage) btnCancelEdit.getScene().getWindow();
		stage.close();
	}
	/**Creates a new edit order controller
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert btnCancelEdit != null : "fx:id=\"cancelOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		populateDropDowns();
		
		
		//checks if the input phone number is of 7,10, or 11 digits and ignores dashes.
		//if invalid phone number entered, reverts back to previous state
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
		//invalid input reverts to previous state filled in the field
		txtZip.focusedProperty().addListener((observable, oldValue, newValue) ->{
			if(oldValue&& !newValue&& !txtZip.getText().equals("")){
				String testZip = txtZip.getText();
				if (!(testZip.length() == 5 ||testZip.length() == 9 ||(testZip.length() == 10 &&testZip.charAt(5)=='-'))){
					throwAlert("Zip Code", txtZip);
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
							
							if (resultPrice < 0){
								throw new IllegalArgumentException();
							}
						} catch (IllegalArgumentException iae) {
							throwAlert("Price", txtPrice);
						}
					}
				});
				
				txtPrice.textProperty().addListener((observable, oldValue, newValue) -> {
						try {
							String priceExp = newValue;
							Expression expr = new ExpressionBuilder(priceExp).build();
							double resultPrice = expr.evaluate();
							double tax = resultPrice * mainController.getCurrentPreference().getTax();
							resultPrice = resultPrice + tax;

							if (resultPrice < 0){
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
				
		//checks if the email contains only 1 '@' symbol
		//reverts to previous state if invalid input was entered
		txtEmail.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue == true && newValue == false && !txtEmail.getText().equals("")) {
				int atCount = 0;
				for (int i = 0; i < txtEmail.getText().length(); i++) {
					if (txtEmail.getText().charAt(i) == '@') {
						atCount++;
					}
				}
				if (atCount != 1) {
					throwAlert("Email Address", txtEmail);
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
		
		if (invalidValueName.toLowerCase()=="phone number"){valueName.setText(editedOrder.getPhoneNumber() + "");}
		if (invalidValueName.toLowerCase()=="zip code"){valueName.setText(editedOrder.getZip() + "");}
		if (invalidValueName.toLowerCase()=="price"){valueName.setText(editedOrder.getPrice() + "");}
		if (invalidValueName.toLowerCase()=="email address"){valueName.setText(editedOrder.getEmail() + "");}
		
		valueName.requestFocus();
	}

	/**
	 * Called by the Main Controller. Used to pass a reference of the Main
	 * Controller to NewOrderController so that it can reference the orderList
	 */
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	/**Sets the 'edited' order to the current changes */
	public void setEditedOrder(Order editedOrder) {
		DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
		String priceString = twoDigitFormat.format(editedOrder.getPrice());
		this.editedOrder = editedOrder;
		txtOrderNumber.setText(editedOrder.getOrderNumber() + "");
		dtpkOrderDate.setValue(editedOrder.getOrderDate());
		dtpkDueDate.setValue(editedOrder.getDueDate());
		cmbOrderStatus.setValue(editedOrder.getStatus());
		txtOrderDesc.setText(editedOrder.getOrderDesc());
		txtFirstName.setText(editedOrder.getFirstName());
		txtLastName.setText(editedOrder.getLastName());
		txtStreetAddress.setText(editedOrder.getStreetAddress());
		txtStreetAddressLine2.setText(editedOrder.getStreetAddressLine2());
		txtCity.setText(editedOrder.getCity());
		cmbState.setValue(editedOrder.getState());
		txtZip.setText(editedOrder.getZip());
		cmbPaymentStatus.setValue(editedOrder.getPaymentStatus());
		cmbPaymentMethod.setValue(editedOrder.getPaymentMethod());
		txtPrice.setText(editedOrder.getPriceExp());
		txtEmail.setText(editedOrder.getEmail());
		txtPhone.setText(editedOrder.getPhoneNumber());
		cmbPrefContactMethod.setValue(editedOrder.getPrefContactMethod());
		chkSMSEnabled.setSelected(editedOrder.getSmsEnabled());

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
		String saveStreetAddressLine2 = "";
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
		if (txtStreetAddressLine2.getText() != null && !txtStreetAddressLine2.getText().trim().isEmpty()) {
			saveStreetAddressLine2 = txtStreetAddressLine2.getText();
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

		editedOrder.setOrderDate(saveOrderDate);
		editedOrder.setDueDate(saveDueDate);
		editedOrder.setStatus(saveStatus);
		editedOrder.setFirstName(saveFirstName);
		editedOrder.setLastName(saveLastName);
		editedOrder.setOrderDesc(saveOrderDesc);
		editedOrder.setStreetAddress(saveStreetAddress);
		editedOrder.setStreetAddressLine2(saveStreetAddressLine2);
		editedOrder.setCity(saveCity);
		editedOrder.setState(saveState);
		editedOrder.setZip(saveZip);
		editedOrder.setPaymentStatus(savePaymentStatus);
		editedOrder.setPaymentMethod(savePaymentMethod);
		editedOrder.setPrice(savePrice);
		editedOrder.setEmail(saveEmail);
		editedOrder.setPhoneNumber(savePhone);
		editedOrder.setPrefContactMethod(savePrefContactMethod);
		editedOrder.setSMSEnabled(saveSmsEnabled);
		editedOrder.setPriceExp(savePriceExp);
		//editedOrder.redoShippingAddress();

		DataAccess.saveOrders(mainController.orderList);
		DataAccess.saveCustomers(mainController.customerList);//TODO added this line
		mainController.chkCompletedOrders.setSelected(!mainController.chkCompletedOrders.isSelected());
		mainController.chkCompletedOrders.setSelected(!mainController.chkCompletedOrders.isSelected());
		Stage stage = (Stage) btnSaveEdit.getScene().getWindow();
		stage.close();

	}
	/**
	 * removes an order or customer from the list
	 * @param ActionEvent e
	 */
	@FXML
	public void onDeleteButtonPressed(ActionEvent e){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Order");
		alert.setHeaderText("This will delete the order from the table.");
		alert.setContentText("Do you wish to proceed?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			boolean customerHasOtherOrder = false;
			for(Order order : mainController.orderList){
				if(editedOrder.getCustomerNumber() == order.getCustomerNumber() && editedOrder.getOrderNumber() != order.getOrderNumber()){
					customerHasOtherOrder = true;
					break;
				}
			}
			if(!customerHasOtherOrder){
				mainController.customerList.remove(editedOrder.getCustomer());
			}
			mainController.orderList.remove(editedOrder);
		    alert.close();
		    DataAccess.saveOrders(mainController.orderList);
		    DataAccess.saveCustomers(mainController.customerList);
		    Stage stage = (Stage) btnDeleteOrder.getScene().getWindow();
			stage.close();
		} else {
		    alert.close();
		}
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
}
