
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML
	private Button newOrderButton;
	private MainApp mainApp;
	@FXML
	private ComboBox<String> cmbOrderStatus;
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
	private TableColumn<Order, String> clmSMSEnabled;
	@FXML
	private TableColumn<Order, String> clmPrefContactMethod;

	private ObservableList<Order> orderList = FXCollections.observableArrayList();

	public MainController() {

	}

	@FXML
	public void newOrderButtonPressed(ActionEvent e) {
		System.out.println("New Order!");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("newOrderGUI.fxml"));
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

	private void populateOrderStatus() {

		ObservableList<String> options = FXCollections.observableArrayList("Order Recieved", "Pot Thrown",
				"Pot Trimmed/Assembled", "Pot Fired", "Pot Glazed", "Ready to Ship", "Completed");
		cmbOrderStatus.setItems(options);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert newOrderButton != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		assert cmbOrderStatus != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		populateOrderStatus();
		populateTable();

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void populateTable() {
		orderList.add(new Order(13625, LocalDate.now(), LocalDate.of(2015, 10, 31), "Incomplete", "James", "Smith",
				"Two mugs please", "136 Required Dr.", "Rock Island", "Illinois", "61201", "Unpaid", "Cash", 136.52,
				"michaelcurrie12@augustana.edu", "555-555-5555", true, "Email"));
		orderList.add(new Order(13626, LocalDate.now(), LocalDate.of(2015, 11, 02), "Incomplete", "John", "Doe",
				"Lots and lots of plates", "123 Living Way", "Aurora", "Illinois", "60506", "Unpaid", "Credit Card",
				678.90, "michaelcurrie12@augustana.edu", "555-555-5555", false, "Email"));

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
		clmSMSEnabled.setCellValueFactory(cellData -> cellData.getValue().smsEnabledStringProperty());
		clmPrefContactMethod.setCellValueFactory(cellData -> cellData.getValue().prefContactMethodProperty());

		tblOrders.setItems(orderList);
	}

	public void orderStatusChanged(Event e) {
		Order selectedOrder = tblOrders.getSelectionModel().getSelectedItem();
		String newOrderStatus = cmbOrderStatus.getValue();
		if (selectedOrder != null) {
			selectedOrder.setStatus(newOrderStatus);
		}
	}

	public void orderTableClicked(Event e) {
		Order selectedOrder = tblOrders.getSelectionModel().getSelectedItem();
		if (selectedOrder != null) {
			String orderStatus = selectedOrder.getStatus();
			cmbOrderStatus.setValue(orderStatus);
		}

	}

}
