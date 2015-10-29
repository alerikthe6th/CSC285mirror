/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

package edu.augustana.comorant.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.augustana.comorant.dataStructures.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ShippingAddressController implements Initializable {
	
	@FXML
	private TableView<Customer> tblShipping;
	@FXML
	private TableColumn<Customer, String> clmStreetAddress;
	@FXML
	private TableColumn<Customer, String> clmCity;
	@FXML
	private TableColumn<Customer, String> clmState;
	@FXML
	private TableColumn<Customer, String> clmZip;
	@FXML
	private Button btnSelect;
	@FXML
	private Button btnCancel;
	
	private ObservableList<Customer> customerList = FXCollections.observableArrayList();
	private NewOrderController noc = null;
	
	private Customer selectedCustomer = null;

	/** Initializes a new shippingAddressController */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblShipping.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			selectedCustomer = newSelection;
			if (newSelection != null){
				btnSelect.setDisable(false);
			} else{
				btnSelect.setDisable(true);
			}	
		});
	}
	
	public void setCustomerList(NewOrderController noc, ObservableList<Customer> list){
		this.noc = noc;
		customerList = list;
		populateTable();
	}
	
	private void populateTable(){
		clmStreetAddress.setCellValueFactory(cellData -> cellData.getValue().streetAddressProperty());
		clmCity.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
		clmState.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		clmZip.setCellValueFactory(cellData -> cellData.getValue().zipProperty());
		
		tblShipping.setItems(customerList);
	}
	
	@FXML
	private void onSelect(ActionEvent e){
		noc.setMatchedCustomer(selectedCustomer);
		Stage stage = (Stage) btnSelect.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void onCancel(ActionEvent e){
		noc.setMatchedCustomer(selectedCustomer);
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

}
