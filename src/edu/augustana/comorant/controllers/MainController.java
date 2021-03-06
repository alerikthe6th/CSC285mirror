/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

package edu.augustana.comorant.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.augustana.comorant.dataStructures.Customer;
import edu.augustana.comorant.dataStructures.Order;
import edu.augustana.comorant.dataStructures.Preference;
import edu.augustana.comorant.launchers.DataAccess;
import edu.augustana.comorant.launchers.MainApp;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MainController implements Initializable {

	@FXML
	private Button newOrderButton;
	@FXML
	private Button btnEditOrder;
	@FXML
	private Button btnDeleteOrder;
	@FXML
	private Button btnViewOrder;
	@FXML
	private Button btnNewOrderByCustomer;
	@FXML
	private TextField txtFilterOrders;
	@FXML
	private Button btnEditCustomer;
	
	@FXML
	private MenuItem miSave;
	@FXML
	private MenuItem miClose;
	@FXML
	private MenuItem miDelete;

	private MainApp mainApp;
	@FXML
	private ComboBox<String> cmbOrderStatus;
	@FXML
	private ComboBox<String> cmbOrderFilters;
	@FXML
	protected TableView<Order> tblOrders;
	@FXML
	private TableColumn<Order, Number> clmOrderNumber;
	@FXML
	private TableColumn<Order, LocalDate> clmOrderDate;
	@FXML
	private TableColumn<Order, LocalDate> clmDueDate;
	@FXML
	private TableColumn<Order, String> clmFirstName;
	@FXML
	private TableColumn<Order, String> clmLastName;
	@FXML
	private TableColumn<Order, String> clmStatus;
	@FXML
	private TableColumn<Order, String> clmOrderDesc;
	@FXML
	private TableColumn<Order, String> clmShippingAddress;
	@FXML
	private TableColumn<Order, String> clmPaymentMethod;
	@FXML
	private TableColumn<Order, String> clmPaymentStatus;
	@FXML
	private TableColumn<Order, String> clmPrice;
	@FXML
	private TableColumn<Order, String> clmEmail;
	@FXML
	private TableColumn<Order, String> clmPhone;
	@FXML
	private TableColumn<Order, Boolean> clmSMSEnabled;
	@FXML
	private TableColumn<Order, String> clmPrefContactMethod;
	@FXML
	private TableColumn<Order, String> clmDelivery;
	
	
	@FXML
	private TableView<Customer> tblCustomers;
	@FXML
	private TableColumn<Customer, String> clmCustName;
	@FXML
	private TableColumn<Customer, String> clmCustStreetAddress;
	@FXML
	private TableColumn<Customer, String> clmCustCity;
	@FXML
	private TableColumn<Customer, String> clmCustState;
	@FXML
	private TableColumn<Customer, String> clmCustZip;
	@FXML
	private TableColumn<Customer, String> clmCustPhone;
	@FXML
	private TableColumn<Customer, String> clmCustEmail;
	@FXML
	private TableColumn<Customer, String> clmCustBalance;
	@FXML
	private TableColumn<Customer, Boolean> clmCustSmsEnabled;
	@FXML
	private TableColumn<Customer, String> clmCustPrefContactMethod;
	
	@FXML
	private ComboBox<String> cmbPaymentStatus;
	
	
	
	
	@FXML
	private Label lblSaving;
	@FXML
	protected CheckBox chkCompletedOrders;

	protected ObservableList<Order> orderList = FXCollections.observableArrayList();
	protected ObservableList<Customer> customerList = FXCollections.observableArrayList();

	protected Order selectedOrder = null;
	SortedList<Order> sortedOrders = null;
	

	protected Customer selectedCustomer = null;
	protected static Preference currentPreference = null;

	public static BooleanProperty saving = new SimpleBooleanProperty(false);

	/**
	 * Launches the new order window. Passes into the New Order controller a
	 * reference to itself so that it can add data to orderList
	 */
	@FXML
	public void newOrderButtonPressed(ActionEvent e) {
		Parent root;
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/augustana/comorant/fxml/newOrderGUI.fxml"));
			root = loader.load();
			NewOrderController newOrderController = (NewOrderController) loader.getController();
			newOrderController.setMainController(this);
			Stage stage = new Stage();
			stage.setTitle("New Order");
			stage.getIcons().add(new Image("comorantIconBorder2.png"));
			stage.setScene(new Scene(root));
			stage.show();

			// hide this current window (if this is what you want
			// ((Node)(e.getSource())).getScene().getWindow().hide();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Launches a new order by customer window that passes in a New Order By Customer controller
	 * a reference to itself so that it can add data to the order list
	 */
	@FXML
	public void newOrderByCustomerPressed(ActionEvent e) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/augustana/comorant/fxml/newOrderByCustomerGUI.fxml"));
			root = loader.load();
			NewOrderController newOrderController = (NewOrderController) loader.getController();
			newOrderController.setMainController(this);
			Stage stage = new Stage();
			stage.setTitle("New Order By Customer");
			stage.getIcons().add(new Image("comorantIconBorder2.png"));
			stage.setScene(new Scene(root));
			stage.show();
			
			newOrderController.setExistingCustomer(selectedCustomer);

			// hide this current window (if this is what you want
			// ((Node)(e.getSource())).getScene().getWindow().hide();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Launches the edit order window. Passes into the Edit Order controller a
	 * reference to itself so that it can add data to orderList
	 */
	@FXML
	public void editOrderButtonPressed(ActionEvent e) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/augustana/comorant/fxml/editOrderGUI.fxml"));
			root = loader.load();
			EditOrderController editOrderController = (EditOrderController) loader.getController();
			editOrderController.setMainController(this);
			editOrderController.setEditedOrder(selectedOrder);

			Stage stage = new Stage();
			stage.setTitle("Edit Order");
			stage.getIcons().add(new Image("comorantIconBorder2.png"));
			stage.setScene(new Scene(root));
			stage.show();

			// hide this current window (if this is what you want
			// ((Node)(e.getSource())).getScene().getWindow().hide();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Launches the edit customer window. Passes into the Edit Customer controller a
	 * reference to itself so that it can add data to customerList
	 */
	@FXML
	public void editCustomerButtonPressed(ActionEvent e) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/augustana/comorant/fxml/editCustomerGUI.fxml"));
			root = loader.load();
			EditCustomerController editCustomerController = (EditCustomerController) loader.getController();
			editCustomerController.setMainController(this);
			editCustomerController.setEditedCustomer(selectedCustomer);

			Stage stage = new Stage();
			stage.setTitle("Edit Customer");
			stage.getIcons().add(new Image("comorantIconBorder2.png"));
			stage.setScene(new Scene(root));
			stage.show();

			// hide this current window (if this is what you want
			// ((Node)(e.getSource())).getScene().getWindow().hide();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Launches the view order window. Passes into the View Order controller a
	 * reference to itself so that it can add data to orderList
	 */
	@FXML
	public void viewOrderButtonPressed(ActionEvent e) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/augustana/comorant/fxml/viewOrderGUI.fxml"));
			root = loader.load();
			ViewOrderController viewOrderController = (ViewOrderController) loader.getController();
			viewOrderController.setMainController(this);
			viewOrderController.setViewOrder(selectedOrder);

			Stage stage = new Stage();
			stage.setTitle("View Order");
			stage.getIcons().add(new Image("comorantIconBorder2.png"));
			stage.setScene(new Scene(root));
			stage.show();

			// hide this current window (if this is what you want
			// ((Node)(e.getSource())).getScene().getWindow().hide();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/** Deletes the selected order when the delete button is pressed */
	@FXML
	public void deleteOrderButtonPressed(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Order");
		alert.setHeaderText("This will delete the order from the table.");
		alert.setContentText("Do you wish to proceed?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			
			boolean customerHasOtherOrder = false;
			for(Order order : orderList){
				if(selectedOrder.getCustomerNumber() == order.getCustomerNumber() && selectedOrder.getOrderNumber() != order.getOrderNumber()){
					customerHasOtherOrder = true;
					break;
				}
			}
			if(!customerHasOtherOrder){
				customerList.remove(selectedOrder.getCustomer());
			}
			orderList.remove(selectedOrder);
			
			// selectedOrder = null;
			alert.close();
			DataAccess.saveOrders(orderList);

		} else {
			alert.close();
		}
	}
	
	/** Opens the preferences window when the file button is clicked */
	@FXML
	protected void miPreferencesPressed(ActionEvent e){
		Parent root;
		try {

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/augustana/comorant/fxml/preferencesGUI.fxml"));
			root = loader.load();
			PrefController prefController = (PrefController) loader.getController();
			prefController.setPref(currentPreference);

			Stage stage = new Stage();
			stage.setTitle("Preferences");
			stage.getIcons().add(new Image("comorantIconBorder2.png"));
			stage.setScene(new Scene(root));
			stage.show();

			// hide this current window (if this is what you want
			// ((Node)(e.getSource())).getScene().getWindow().hide();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

	/** Opens the about window when the file button is clicked */
	@FXML
	public void miAboutPressed(ActionEvent e) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"/edu/augustana/comorant/fxml/aboutGUI.fxml"));
			root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("About");
			stage.getIcons().add(new Image("comorantIconBorder2.png"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/** Closes the window the close button was pressed on */
	@FXML
	public void miClosedPressed(ActionEvent e) {
		Stage stage = (Stage) tblOrders.getScene().getWindow();
		stage.close();
	}

	/** Fills the Order Status combobox with status strings */
	private void populateDropdowns() {

		ObservableList<String> options = FXCollections.observableArrayList("Order Received", "Pot Thrown",
				"Pot Trimmed/Assembled", "Pot Fired", "Pot Glazed", "Ready to Ship", "Completed");
		cmbOrderStatus.setItems(options);
		
		ObservableList<String> options2 = FXCollections.observableArrayList("Paid", "Unpaid");
		cmbPaymentStatus.setItems(options2);

		ObservableList<String> filters = FXCollections.observableArrayList("Status", "Name", "Shipping Address",
				"Order Description", "Payment Method", "Payment Status", "Price", "Email", "Phone Number",
				"Preffered Contact Method");
		cmbOrderFilters.setItems(filters);
		cmbOrderFilters.setValue("Name");

	}

	/** Initializes a new window */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		assert newOrderButton != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		assert cmbOrderStatus != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		populateDropdowns();
		
		cmbOrderStatus.setDisable(true);
		cmbPaymentStatus.setDisable(true);

		customerList = DataAccess.loadCustomers();
		orderList = DataAccess.loadOrders(customerList);
		currentPreference = DataAccess.loadPreference();
		sortedOrders = wrapOrdersList();

		sortedOrders.comparatorProperty().bind(tblOrders.comparatorProperty());

		populateTable();

		tblOrders.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				selectedOrder = newSelection;
				cmbOrderStatus.setValue(selectedOrder.getStatus());
				cmbPaymentStatus.setValue(selectedOrder.getPaymentStatus());
				btnEditOrder.setDisable(false);
				btnDeleteOrder.setDisable(false);
				btnViewOrder.setDisable(false);
				cmbOrderStatus.setDisable(false);
				cmbPaymentStatus.setDisable(false);
			} else {
				btnEditOrder.setDisable(true);
				btnDeleteOrder.setDisable(true);
				btnViewOrder.setDisable(true);
				cmbOrderStatus.setDisable(true);
				cmbPaymentStatus.setDisable(true);
			}
		});
		
		tblCustomers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				selectedCustomer = newSelection;

				btnEditCustomer.setDisable(false);
				btnNewOrderByCustomer.setDisable(false);

			} else {
				
				btnEditCustomer.setDisable(true);
				btnNewOrderByCustomer.setDisable(true);

			}
		});

		lblSaving.visibleProperty().bind(saving);
		
		tblOrders.setRowFactory( tv -> {
		    TableRow<Order> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            editOrderButtonPressed(null);
		        }
		    });
		    return row ;
		});
		//opens the preferences window if there are no orders
		if(orderList.toString().equals("[]")){
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/edu/augustana/comorant/fxml/preferencesGUI.fxml"));
				root = loader.load();
				PrefController prefController = (PrefController) loader.getController();
				prefController.setPref(currentPreference);
	
				Stage stage = new Stage();
				stage.centerOnScreen();
				stage.setTitle("Preferences");
				stage.getIcons().add(new Image("comorantIconBorder2.png"));
				stage.setScene(new Scene(root));
				
				stage.setAlwaysOnTop(true);
				stage.requestFocus();
				stage.show();
								
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/** Shows all the data in a wrapped, filtered list */
	private SortedList<Order> wrapOrdersList() {
		FilteredList<Order> filteredOrders = new FilteredList<>(orderList, p -> true); // Show all data. Wrapped in filtered list
		chkCompletedOrders.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (chkCompletedOrders.isFocused()){
			txtFilterOrders.setText("");
			}
			filteredOrders.setPredicate(order -> {
				if (newValue == null || newValue == false) {
					return true;
				}
				if (order.getStatus().equals("Completed") && order.getPaymentStatus().equals("Paid")) {
					return false;
				}
				return true;
			});
		});

		txtFilterOrders.textProperty().addListener((observable, oldValue, newValue) -> {
			setOrdersPredicate(filteredOrders, newValue);
		});
		cmbOrderFilters.valueProperty().addListener((observable, oldValue, newValue) -> {
			setOrdersPredicate(filteredOrders, txtFilterOrders.getText());
		});
		return new SortedList<>(filteredOrders);
	}

	/** Filters the orders based on the filter text field */
	private void setOrdersPredicate(FilteredList<Order> filteredOrders, String newValue) {
		filteredOrders.setPredicate(order -> {
			if (chkCompletedOrders.isSelected()){
				if (order.getStatus().equals("Completed") && order.getPaymentStatus().equals("Paid")) {
					return false;
				}
			}
			// If filter text is empty, display all persons
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}

			// Compare first name and last name of
			// every person with filter text.

			String lowerCaseFilter = newValue.toLowerCase();

			// use name if no filter selected
			if (cmbOrderFilters.getValue() == null) {
				if (order.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (order.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			}
			if (cmbOrderFilters.getValue().toString() == "Status") {
				if (order.getStatus().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches status.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Name") {
				if (order.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (order.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Shipping Address") {
				if (order.getFullAddress().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches shipping address.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Order Description") {
				if (order.getOrderDesc().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Order Description.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Payment Method") {
				if (order.getPaymentMethod().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Payment method.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Payment Status") {
				if (order.getPaymentStatus().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches payment status.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Email") {
				if (order.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches email.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Phone Number") {
				if (order.getPhoneNumber().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches phone number.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Price") {
				if ((order.getPrice() + "").toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches price.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Preferred Contact Method") {
				if (order.getPrefContactMethod().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches pref contact method.
				}
				return false; // Does not match.
			}
			return false;
		});
	}

	/** Gives a reference to the main application object if needed. */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/** Fills the Orders table with info from the orderList List */
	public void populateTable() {
		clmFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		clmLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		clmOrderNumber.setCellValueFactory(cellData -> cellData.getValue().orderNumberProperty());
		clmOrderDate.setCellValueFactory(cellData -> cellData.getValue().orderDateProperty());
		clmDueDate.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty());
		clmStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		clmOrderDesc.setCellValueFactory(cellData -> cellData.getValue().orderDescProperty());
		clmShippingAddress.setCellValueFactory(cellData -> cellData.getValue().fullAddressProperty());
		clmPaymentMethod.setCellValueFactory(cellData -> cellData.getValue().paymentMethodProperty());
		clmPaymentStatus.setCellValueFactory(cellData -> cellData.getValue().paymentStatusProperty());
		clmPrice.setCellValueFactory(cellData -> cellData.getValue().priceStringProperty());
		clmEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		clmPhone.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		clmSMSEnabled.setCellValueFactory(cellData -> cellData.getValue().smsEnabledProperty());
		clmPrefContactMethod.setCellValueFactory(cellData -> cellData.getValue().prefContactMethodProperty());
		clmDelivery.setCellValueFactory(cellData -> cellData.getValue().deliveryMethodProperty());

		tblOrders.setItems(sortedOrders);
		
		clmCustName.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
		clmCustStreetAddress.setCellValueFactory(cellData -> cellData.getValue().bothStreetAddressProperty());
		clmCustCity.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
		clmCustState.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		clmCustZip.setCellValueFactory(cellData -> cellData.getValue().zipProperty());
		clmCustPhone.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		clmCustEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		clmCustSmsEnabled.setCellValueFactory(cellData -> cellData.getValue().smsEnabledProperty());
		clmCustPrefContactMethod.setCellValueFactory(cellData -> cellData.getValue().prefContactMethodProperty());
		
		tblCustomers.setItems(customerList);
	}

	/**
	 * Sets the selected Order's status to what is selected in the combobox.
	 * Saves the data to DB if the status value has changed
	 * 
	 */
	public void onStatusDropdownChanged(Event e) {
		String oldSelectedOrderStatus = selectedOrder.getStatus();
		String newOrderStatus = cmbOrderStatus.getValue();

		selectedOrder.setStatus(newOrderStatus);
		if (!oldSelectedOrderStatus.equals(newOrderStatus)) {
			//refresh filter
			chkCompletedOrders.setSelected(!chkCompletedOrders.isSelected());
			chkCompletedOrders.setSelected(!chkCompletedOrders.isSelected());
			DataAccess.saveOrders(orderList);
		}

	}
	
	/**
	 * Sets the selected Order's payment status to what is selected in the combobox.
	 * Saves the data to DB if the status value has changed
	 * 
	 */
	public void onPaymentDropdownChanged(Event e) {
		String oldSelectedPaymentStatus = selectedOrder.getPaymentStatus();
		String newPaymentStatus = cmbPaymentStatus.getValue();

		selectedOrder.setPaymentStatus(newPaymentStatus);
		if (!oldSelectedPaymentStatus.equals(newPaymentStatus)) {
			//refresh filter
			chkCompletedOrders.setSelected(!chkCompletedOrders.isSelected());
			chkCompletedOrders.setSelected(!chkCompletedOrders.isSelected());
			DataAccess.saveOrders(orderList);
		}

	}

	/**
	 * Returns the current largest order number in the orders list. Used to get
	 * the next order number when adding an order.
	 */
	protected int getLargestOrderNumber() {
		ArrayList<Integer> orderNumberList = new ArrayList<Integer>();
		if (orderList.size() == 0) {
			return 0;
		}
		for (Order order : orderList) {
			orderNumberList.add(new Integer(order.getOrderNumber()));
		}
		return Collections.max(orderNumberList);
	}
	
	/**
	 * Returns the current largest customer number in the orders list. Used to get
	 * the next customer number when adding an customer.
	 */
	protected int getLargestCustomerNumber() {
		ArrayList<Integer> customerNumberList = new ArrayList<Integer>();
		if (customerList.size() == 0) {
			return 0;
		}
		for (Customer customer : customerList) {
			customerNumberList.add(new Integer(customer.getCustomerNumber()));
		}
		return Collections.max(customerNumberList);
	}
	
	public static Preference getCurrentPreference(){
		return currentPreference;
	}

}
