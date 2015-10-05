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

import edu.augustana.comorant.dataStructures.Order;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
	private TextField txtFilterOrders;

	private MainApp mainApp;
	@FXML
	private ComboBox<String> cmbOrderStatus;
	@FXML
	private ComboBox<String> cmbOrderFilters;
	@FXML
	private TableView<Order> tblOrders;
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
	private TableColumn<Order, Number> clmPrice;
	@FXML
	private TableColumn<Order, String> clmEmail;
	@FXML
	private TableColumn<Order, String> clmPhone;
	@FXML
	private TableColumn<Order, Boolean> clmSMSEnabled;
	@FXML
	private TableColumn<Order, String> clmPrefContactMethod;
	@FXML
	private Label lblSaving;
	@FXML
	protected CheckBox chkCompletedOrders;

	protected ObservableList<Order> orderList = FXCollections.observableArrayList();

	protected Order selectedOrder = new Order();
	SortedList<Order> sortedOrders = null;

	public static BooleanProperty saving = new SimpleBooleanProperty(false);

	public MainController() {

	}

	/**
	 * Launches the new order window. Passes into the New Order controller a
	 * reference to itself so that it can add data to orderList
	 */
	@FXML
	public void newOrderButtonPressed(ActionEvent e) {
		System.out.println("New Order!");
		Parent root;
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/augustana/comorant/fxml/newOrderGUI.fxml"));
			root = loader.load();
			NewOrderController newOrderController = (NewOrderController) loader.getController();
			newOrderController.setMainController(this);
			Stage stage = new Stage();
			stage.setTitle("New Order");
			stage.setScene(new Scene(root));
			stage.show();

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
		System.out.println("Edit Order!");
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
		System.out.println("View Order!");
		Parent root;
		try {

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/augustana/comorant/fxml/viewOrder.fxml"));
			root = loader.load();
			ViewOrderController viewOrderController = (ViewOrderController) loader.getController();
			viewOrderController.setMainController(this);
			viewOrderController.setViewOrder(selectedOrder);

			Stage stage = new Stage();
			stage.setTitle("View Order");
			stage.setScene(new Scene(root));
			stage.show();

			// hide this current window (if this is what you want
			// ((Node)(e.getSource())).getScene().getWindow().hide();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void deleteOrderButtonPressed(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Order");
		alert.setHeaderText("This will delete the order from the table.");
		alert.setContentText("Do you wish to proceed?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			orderList.remove(selectedOrder);
			// selectedOrder = null;
			alert.close();
			DataAccess.saveOrders(orderList);
			System.out.println("Delete Order!");

		} else {
			alert.close();
		}
	}

	/**
	 * Fills the Order Status combobox with status strings
	 */
	private void populateDropdowns() {

		ObservableList<String> options = FXCollections.observableArrayList("Order Recieved", "Pot Thrown",
				"Pot Trimmed/Assembled", "Pot Fired", "Pot Glazed", "Ready to Ship", "Completed");
		cmbOrderStatus.setItems(options);

		ObservableList<String> filters = FXCollections.observableArrayList("Status", "Name", "Shipping Address",
				"Order Description", "Payment Method", "Payment Status", "Price", "Email", "Phone Number",
				"Preffered Contact Method");
		cmbOrderFilters.setItems(filters);
		cmbOrderFilters.setValue("Name");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert newOrderButton != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		assert cmbOrderStatus != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		populateDropdowns();

		orderList = DataAccess.loadOrders();
		sortedOrders = wrapOrdersList();

		sortedOrders.comparatorProperty().bind(tblOrders.comparatorProperty());

		populateTable();

		tblOrders.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				selectedOrder = newSelection;
				cmbOrderStatus.setValue(selectedOrder.getStatus());
				btnEditOrder.setDisable(false);
				btnDeleteOrder.setDisable(false);
				btnViewOrder.setDisable(false);

			} else {
				btnEditOrder.setDisable(true);
				btnDeleteOrder.setDisable(true);
				btnViewOrder.setDisable(true);
			}
		});

		lblSaving.visibleProperty().bind(saving);

	}

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

	private void setOrdersPredicate(FilteredList<Order> filteredOrders, String newValue) {
		filteredOrders.setPredicate(order -> {
			if (chkCompletedOrders.isSelected()){
				if (order.getStatus().equals("Completed") && order.getPaymentStatus().equals("Paid")) {
					return false;

				}
			}
			// If filter text is empty, display all persons.
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}

			// Compare first name and last name of every person with filter text.

			String lowerCaseFilter = newValue.toLowerCase();

			// use name if no filter selected
			if (cmbOrderFilters.getValue() == null) {
				if (order.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by first name.
				} else if (order.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by last name.
				}
				return false; // Does not match.
			}
			if (cmbOrderFilters.getValue().toString() == "Status") {
				if (order.getStatus().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by status.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Name") {
				if (order.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by first name.
				} else if (order.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by last name.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Shipping Address") {
				if (order.getFullAddress().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by shipping address.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Order Description") {
				if (order.getOrderDesc().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by Order Description.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Payment Method") {
				if (order.getPaymentMethod().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by Payment method.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Payment Status") {
				if (order.getPaymentStatus().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by payment status.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Email") {
				if (order.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by email.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Phone Number") {
				if (order.getPhoneNumber().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by phone number.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Price") {
				if ((order.getPrice() + "").toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by phone number.
				}
				return false; // Does not match.
			} else if (cmbOrderFilters.getValue().toString() == "Preffered Contact Method") {
				if (order.getPrefContactMethod().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches by pref contact method.
				}
				return false; // Does not match.
			}
			return false;
		});
	}

	/**
	 * Gives a reference to the main application object if needed.
	 * 
	 * 
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Fills the Orders table with info from the orderList List
	 */
	public void populateTable() {
		// orderList.add(new Order(13625, LocalDate.now(), LocalDate.of(2015,
		// 10, 31), "Incomplete", "James", "Smith",
		// "Two mugs please", "136 Required Dr.", "Rock Island", "Illinois",
		// "61201", "Unpaid", "Cash", 136.52,
		// "michaelcurrie12@augustana.edu", "555-555-5555", true, "Email"));
		// orderList.add(new Order(13626, LocalDate.now(), LocalDate.of(2015,
		// 11, 02), "Incomplete", "John", "Doe",
		// "Lots and lots of plates", "123 Living Way", "Aurora", "Illinois",
		// "60506", "Unpaid", "Credit Card",
		// 678.90, "michaelcurrie12@augustana.edu", "555-555-5555", false,
		// "Email"));

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
		clmPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
		clmEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		clmPhone.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		clmSMSEnabled.setCellValueFactory(cellData -> cellData.getValue().smsEnabledProperty());
		clmPrefContactMethod.setCellValueFactory(cellData -> cellData.getValue().prefContactMethodProperty());

		tblOrders.setItems(sortedOrders);
	}

	/**
	 * Sets the selected Order's status to what is selected in the combobox.
	 * Saves the data to DB if the status value has changed
	 * 
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

}
