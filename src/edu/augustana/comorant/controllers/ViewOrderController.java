
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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ViewOrderController implements Initializable {
	
	Order viewOrder;
	@FXML
	private Button btnCloseWindow;
	@FXML
	private Button btnPrint;
	@FXML
	private MainController mainController;
	@FXML
	private Label lblOrderNumber;
	@FXML
	private Label lblOrderDate;
	@FXML
	private Label lblDueDate;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblLastName;
	@FXML
	private Button btnAutoFill;
	@FXML
	private TextArea txtOrderDesc;
	@FXML
	private Label lblStreetAddress;
	@FXML
	private Label lblCity;
	@FXML
	private Label lblState;
	@FXML
	private Label lblZip;
	@FXML
	private Label lblPhone;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblOrderStatus;
	@FXML
	private Label lblPrefContactMethod;
	@FXML
	private Label lblSMSEnabled;
	@FXML
	private Label lblPrice;
	@FXML
	private Label lblPaymentMethod;
	@FXML
	private Label lblPaymentStatus;

	
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
		lblOrderNumber.setText(viewOrder.getOrderNumber() + "");
		lblOrderDate.setText(viewOrder.getOrderDate().toString());
		lblDueDate.setText(viewOrder.getDueDate().toString());
		lblOrderStatus.setText(viewOrder.getStatus().toString());
		txtOrderDesc.setText(viewOrder.getOrderDesc());
		lblFirstName.setText(viewOrder.getFirstName());
		lblLastName.setText(viewOrder.getLastName());
		lblStreetAddress.setText(viewOrder.getStreetAddress());
		lblCity.setText(viewOrder.getCity());
		lblState.setText(viewOrder.getState());
		lblZip.setText(viewOrder.getZip());
		lblPaymentStatus.setText(viewOrder.getPaymentStatus().toString());
		lblPaymentMethod.setText(viewOrder.getPaymentMethod().toString());
		lblPrice.setText(viewOrder.getPrice()+"");
		lblEmail.setText(viewOrder.getEmail());
		lblPhone.setText(viewOrder.getPhoneNumber());
		lblPrefContactMethod.setText(viewOrder.getPrefContactMethod().toString());

		
		if(viewOrder.getSmsEnabled()) {
			lblSMSEnabled.setText("Yes");
		}else{
			lblSMSEnabled.setText("No");
		}
		

	}
	
	@FXML
	public void onPrintButtonPressed(ActionEvent e){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Printing");
		alert.setContentText("Print Successful!");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    alert.close();
		    DataAccess.saveOrders(mainController.orderList);
		    System.out.println("Print!");
		    Stage stage = (Stage) btnPrint.getScene().getWindow();
			stage.close();
		} else {
		    alert.close();
		}
		
		
	}


}

