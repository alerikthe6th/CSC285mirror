/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

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

	/**
	 * initializes a new viewOrderController
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert btnCloseWindow != null : "fx:id=\"closeWindowButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";		
	}
	/**
	 * closes the window when close button pressed
	 * @param e
	 */
	@FXML
	public void closeWindowButtonPressed(ActionEvent e) {
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
	 * Pulls the info from the order in the database to be viewed
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
	
	/**
	 * prints the order being viewed
	 * @param e
	 */
	@FXML
	public void onPrintButtonPressed(ActionEvent e){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Printing");
		//'null' statements are for second address lines: leave as null or "" if empty
		//would ""+preferences.addressLine2.getText() return anything if it's blank?
		
		
		edu.augustana.comorant.launchers.PrintInvoice.createInvoice(
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getBusinessName(), 
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getStreetAddress(),
			null, 
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getCity()+", "+
			edu.augustana.comorant.launchers.PrintInvoice.stateFormatter(
					edu.augustana.comorant.controllers.MainController.getCurrentPreference().getState())+", "+
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getZip(),
			lblFirstName.getText()+" "+lblLastName.getText(), ""+lblStreetAddress.getText(), null, 
			""+lblCity.getText()+", "+edu.augustana.comorant.launchers.PrintInvoice.stateFormatter(lblState.getText())
			+" "+lblZip.getText(), ""+lblOrderDate.getText(), ""+txtOrderDesc.getText(), 
			""+lblPrice.getText(), ""+lblPaymentMethod.getText()
		);

		edu.augustana.comorant.launchers.PrintInvoice.printPage();
		
		alert.setContentText("Print Successful!");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    alert.close();
		    DataAccess.saveOrders(mainController.orderList);
		    Stage stage = (Stage) btnPrint.getScene().getWindow();
			stage.close();
		} else {
		    alert.close();
		}
	}
}
