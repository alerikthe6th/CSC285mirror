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
import javafx.scene.image.Image;
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
	private Label lblStreetAddressLine2;
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
	private Label lblShippingCost;
	@FXML
	private Label lblPaymentMethod;
	@FXML
	private Label lblPaymentStatus;

	/** initializes a new viewOrderController */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert btnCloseWindow != null : "fx:id=\"closeWindowButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";		
	}
	/** closes the window when close button pressed */
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

	/** Pulls the info from the order in the database to be viewed */
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
		lblStreetAddressLine2.setText(viewOrder.getStreetAddressLine2());
		lblCity.setText(viewOrder.getCity());
		lblState.setText(viewOrder.getState());
		lblZip.setText(viewOrder.getZip());
		lblPaymentStatus.setText(viewOrder.getPaymentStatus().toString());
		lblPaymentMethod.setText(viewOrder.getPaymentMethod().toString());
		lblPrice.setText(viewOrder.getPrice()+"");
		lblShippingCost.setText(viewOrder.getShippingCost()+"");
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
	 * Prints the order being viewed using PrintInvoice's 
	 * 'createInvoice' and 'printPage' methods
	 * and Preference's data for return address with PrintInvoice's
	 * 'stateFormatter' method to format state & province names to
	 * be abbreviated for postal use
	 * @param ActionEvent e
	 */
	@FXML
	public void onPrintButtonPressed(ActionEvent e){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Printing");
		
		edu.augustana.comorant.launchers.PrintInvoice.createInvoice(//a new invoice! oh boy!
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getBusinessName(),//from name 
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getStreetAddress(),//from address
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getStreetAddressLine2(),//from address line 2 
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getCity()+", "+//from city,
			edu.augustana.comorant.launchers.PrintInvoice.stateFormatter(
					edu.augustana.comorant.controllers.MainController.getCurrentPreference().getState())+" "+//from state
			edu.augustana.comorant.controllers.MainController.getCurrentPreference().getZip(),//from zip
			lblFirstName.getText()+" "+lblLastName.getText(), //firstname, lastname
			""+lblStreetAddress.getText(), 
			""+lblStreetAddressLine2.getText(), //second to address line
			""+lblCity.getText()+", "+//to city
					edu.augustana.comorant.launchers.PrintInvoice.stateFormatter(lblState.getText())//to state(formatted)
			+" "+lblZip.getText(), ""+lblOrderDate.getText(), ""+txtOrderDesc.getText(), //to to zip, order date, description
			""+lblShippingCost.getText(),
			""+lblPrice.getText(), ""+lblPaymentMethod.getText()//price and payment method
		);

		edu.augustana.comorant.launchers.PrintInvoice.printPage();//if Lord Printer allows it...
		
		alert.setContentText("Print Successful!");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    alert.close();
		    DataAccess.saveOrders(mainController.orderList);
		    Stage stage = (Stage) btnPrint.getScene().getWindow();
		    stage.getIcons().add(new Image("comorantIconBorder2.png"));
			stage.close();
		} else {
		    alert.close();
		}
	}
}
