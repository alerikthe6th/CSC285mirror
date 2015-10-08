package edu.augustana.comorant.controllers;

import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	
	private String placeHolder;

	public EditOrderController() {
		

	}

	@FXML
	public void cancelEditButtonPressed(ActionEvent e) {
		System.out.println("Cancel Order!");
		Stage stage = (Stage) btnCancelEdit.getScene().getWindow();
		stage.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert btnCancelEdit != null : "fx:id=\"cancelOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		populateDropDowns();
		
		txtZip.focusedProperty().addListener((observable, oldValue, newValue) ->{
			if(oldValue&& !newValue&& !txtZip.getText().equals("")){
				String testZip = txtZip.getText();
				if (!(testZip.length() == 5 ||testZip.length() == 9 ||(testZip.length() == 10 &&testZip.charAt(5)=='-'))){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Invalid ZIP code");
					alert.setHeaderText(null);
					alert.setContentText("Please enter a valid ZIP code into the zip code field");

					alert.showAndWait();
					txtZip.setText(editedOrder.getZip() + "");
					txtZip.requestFocus();
				}
			}
		}
				);
		
		
		txtPrice.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(oldValue&& !newValue && !txtPrice.getText().equals("")){
				String testPrice = txtPrice.getText();
				try{
					for(int i = 0; i < txtPrice.getText().length(); i++) {
						if(txtPrice.getText().charAt(i) == '.') {
							
							if(txtPrice.getText().substring(i).length() > 3 || txtPrice.getText().charAt(0)=='-') {
								
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Invalid Price Format");
								alert.setHeaderText(null);
								alert.setContentText("Please enter a valid number into the Price field");
								
								alert.showAndWait();
								txtPrice.setText(editedOrder.getPrice() + "");
								txtPrice.requestFocus();
							}	
						}
					}
					Double.parseDouble(testPrice);
				} catch (NumberFormatException npe){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Invalid Price Format");
					alert.setHeaderText(null);
					alert.setContentText("Please enter a valid number into the Price field");

					alert.showAndWait();
					txtPrice.setText(editedOrder.getPrice() + "");
					txtPrice.requestFocus();
				}
			}
		});

	}

	/**
	 * Called by the Main Controller. Used to pass a reference of the Main
	 * Controller to NewOrderController so that it can reference the orderList
	 */
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	/**
	 * 
	 */
	public void setEditedOrder(Order editedOrder) {
		this.editedOrder = editedOrder;
		txtOrderNumber.setText(editedOrder.getOrderNumber() + "");
		dtpkOrderDate.setValue(editedOrder.getOrderDate());
		dtpkDueDate.setValue(editedOrder.getDueDate());
		cmbOrderStatus.setValue(editedOrder.getStatus());
		txtOrderDesc.setText(editedOrder.getOrderDesc());
		txtFirstName.setText(editedOrder.getFirstName());
		txtLastName.setText(editedOrder.getLastName());
		txtStreetAddress.setText(editedOrder.getStreetAddress());
		txtCity.setText(editedOrder.getCity());
		cmbState.setValue(editedOrder.getState());
		txtZip.setText(editedOrder.getZip());
		cmbPaymentStatus.setValue(editedOrder.getPaymentStatus());
		cmbPaymentMethod.setValue(editedOrder.getPaymentMethod());
		txtPrice.setText(editedOrder.getPrice()+"");
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

		editedOrder.setOrderDate(saveOrderDate);
		editedOrder.setDueDate(saveDueDate);
		editedOrder.setStatus(saveStatus);
		editedOrder.setFirstName(saveFirstName);
		editedOrder.setLastName(saveLastName);
		editedOrder.setOrderDesc(saveOrderDesc);
		editedOrder.setStreetAddress(saveStreetAddress);
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
		editedOrder.redoShippingAddress();

		DataAccess.saveOrders(mainController.orderList);
		System.out.println("Save Edit!");
		mainController.chkCompletedOrders.setSelected(!mainController.chkCompletedOrders.isSelected());
		mainController.chkCompletedOrders.setSelected(!mainController.chkCompletedOrders.isSelected());
		Stage stage = (Stage) btnSaveEdit.getScene().getWindow();
		stage.close();

	}
	
	@FXML
	public void onDeleteButtonPressed(ActionEvent e){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Order");
		alert.setHeaderText("This will delete the order from the table.");
		alert.setContentText("Do you wish to proceed?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    mainController.orderList.remove(editedOrder);
		    alert.close();
		    DataAccess.saveOrders(mainController.orderList);
		    System.out.println("Delete Order!");
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

	}

}
