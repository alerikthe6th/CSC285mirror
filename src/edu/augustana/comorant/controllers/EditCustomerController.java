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

public class EditCustomerController implements Initializable {


	Customer editedCustomer;
	@FXML
	private Button btnCancelEdit;
	@FXML
	private Button btnSaveEdit;
	@FXML
	private MainController mainController;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
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
	private ComboBox<String> cmbPrefContactMethod;
	@FXML
	private CheckBox chkSMSEnabled;

	
	/**
	 * Closes the edit window
	 * @param e
	 */
	@FXML
	public void cancelEditButtonPressed(ActionEvent e) {
		Stage stage = (Stage) btnCancelEdit.getScene().getWindow();
		stage.close();
	}
	/**Creates a new edit order controller */
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
		
		if (invalidValueName.toLowerCase()=="phone number"){valueName.setText(editedCustomer.getPhoneNumber() + "");}
		if (invalidValueName.toLowerCase()=="zip code"){valueName.setText(editedCustomer.getZip() + "");}
		if (invalidValueName.toLowerCase()=="email address"){valueName.setText(editedCustomer.getEmail() + "");}
		
		valueName.requestFocus();
	}

	/**
	 * Called by the Main Controller. Used to pass a reference of the Main
	 * Controller to NewOrderController so that it can reference the orderList
	 */
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	/** Sets the 'edited' order to the current changes */
	public void setEditedCustomer(Customer editedCustomer) {
		this.editedCustomer = editedCustomer;
		
		txtFirstName.setText(editedCustomer.getFirstName());
		txtLastName.setText(editedCustomer.getLastName());
		txtStreetAddress.setText(editedCustomer.getStreetAddress());
		txtStreetAddressLine2.setText(editedCustomer.getStreetAddressLine2());
		txtCity.setText(editedCustomer.getCity());
		cmbState.setValue(editedCustomer.getState());
		txtZip.setText(editedCustomer.getZip());
		txtEmail.setText(editedCustomer.getEmail());
		txtPhone.setText(editedCustomer.getPhoneNumber());
		cmbPrefContactMethod.setValue(editedCustomer.getPrefContactMethod());
		chkSMSEnabled.setSelected(editedCustomer.getSMSEnabled());

	}

	/**
	 * Adds the new order to the main controller's orderList. Defaults empty
	 * boxes to empty Strings. Then saves orders to the DB.
	 */
	@FXML
	public void onSaveButtonPressed(ActionEvent e) {
		// Set default values here
		String saveFirstName = "";
		String saveLastName = "";
		String saveStreetAddress = "";
		String saveStreetAddressLine2 = "";
		String saveCity = "";
		String saveState = "";
		String saveZip = "";
		String saveEmail = "";
		String savePhone = "";
		boolean saveSmsEnabled = chkSMSEnabled.isSelected();
		String savePrefContactMethod = "";

		if (txtFirstName.getText() != null && !txtFirstName.getText().trim().isEmpty()) {
			saveFirstName = txtFirstName.getText();
		}
		if (txtLastName.getText() != null && !txtLastName.getText().trim().isEmpty()) {
			saveLastName = txtLastName.getText();
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
		if (txtEmail.getText() != null && !txtEmail.getText().trim().isEmpty()) {
			saveEmail = txtEmail.getText();
		}
		if (txtPhone.getText() != null && !txtPhone.getText().trim().isEmpty()) {
			savePhone = txtPhone.getText();
		}
		if (cmbPrefContactMethod.getValue() != null) {
			savePrefContactMethod = cmbPrefContactMethod.getValue().toString();
		}

		editedCustomer.setFirstName(saveFirstName);
		editedCustomer.setLastName(saveLastName);
		editedCustomer.setStreetAddress(saveStreetAddress);
		editedCustomer.setStreetAddressLine2(saveStreetAddressLine2);
		editedCustomer.setCity(saveCity);
		editedCustomer.setState(saveState);
		editedCustomer.setZip(saveZip);
		editedCustomer.setEmail(saveEmail);
		editedCustomer.setPhoneNumber(savePhone);
		editedCustomer.setPrefContactMethod(savePrefContactMethod);
		editedCustomer.setSmsEnabled(saveSmsEnabled);
		//editedOrder.redoShippingAddress();

		DataAccess.saveCustomers(mainController.customerList);
		mainController.chkCompletedOrders.setSelected(!mainController.chkCompletedOrders.isSelected());
		mainController.chkCompletedOrders.setSelected(!mainController.chkCompletedOrders.isSelected());
		Stage stage = (Stage) btnSaveEdit.getScene().getWindow();
		stage.close();
		}

	}
	
	/** Fills the comboboxes with the appropriate strings */
	private void populateDropDowns() {

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
