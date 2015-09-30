import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

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

public class ViewOrderController implements Initializable {
	
	Order viewOrder;
	@FXML
	private Button btnCloseWindow;
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
	private TextField txtState;
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

	
	public ViewOrderController() {
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert btnCloseWindow != null : "fx:id=\"closeWindowButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";

		
	}
	
	@FXML
	public void closeWindowButtonPressed(ActionEvent e) {
		System.out.println("Close window");
		Stage stage = (Stage) btnCloseWindow.getScene().getWindow();
		stage.close();

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
	public void setViewOrder(Order viewOrder) {
		this.viewOrder = viewOrder;
		txtOrderNumber.setText(viewOrder.getOrderNumber() + "");
		dtpkOrderDate.setValue(viewOrder.getOrderDate());
		dtpkDueDate.setValue(viewOrder.getDueDate());
		cmbOrderStatus.setValue(viewOrder.getStatus());
		txtOrderDesc.setText(viewOrder.getOrderDesc());
		txtFirstName.setText(viewOrder.getFirstName());
		txtLastName.setText(viewOrder.getLastName());
		txtStreetAddress.setText(viewOrder.getStreetAddress());
		txtCity.setText(viewOrder.getCity());
		txtState.setText(viewOrder.getState());
		txtZip.setText(viewOrder.getZip());
		cmbPaymentStatus.setValue(viewOrder.getPaymentStatus());
		cmbPaymentMethod.setValue(viewOrder.getPaymentMethod());
		txtPrice.setText(viewOrder.getPrice()+"");
		txtEmail.setText(viewOrder.getEmail());
		txtPhone.setText(viewOrder.getPhoneNumber());
		cmbPrefContactMethod.setValue(viewOrder.getPrefContactMethod());
		chkSMSEnabled.setSelected(viewOrder.getSmsEnabled());

	}


}

