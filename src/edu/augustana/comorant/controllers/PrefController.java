package edu.augustana.comorant.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import edu.augustana.comorant.dataStructures.Preference;
import edu.augustana.comorant.launchers.DataAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrefController implements Initializable {
	
	Preference currentPref = null;
	
	@FXML
	protected TextField txtBusiness;
	@FXML
	protected TextField txtStreetAddress;
	@FXML
	protected TextField txtCity;
	@FXML
	protected ComboBox<String> cmbState;
	@FXML
	protected TextField txtZip;
	@FXML
	protected TextField txtTax;
	@FXML
	protected Button btnCancel;
	@FXML
	protected Button btnSave;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateDropDowns();

	}

	private void populateDropDowns() {
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

	public void setPref(Preference currentPreference) {
		this.currentPref = currentPreference;
		this.txtBusiness.setText(currentPref.getBusinessName());
		this.txtStreetAddress.setText(currentPref.getStreetAddress());
		this.txtCity.setText(currentPref.getCity());
		this.cmbState.setValue(currentPref.getState());
		this.txtZip.setText(currentPref.getZip());
		
		DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
		String taxString = twoDigitFormat.format(currentPref.getTax());
		this.txtTax.setText(taxString);
		
	}
	
	public void onCancel(ActionEvent e){
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}
	
	public void onSave(ActionEvent e){
		String saveBusinessName = "";
		String saveStreetAddress = "";
		String saveCity = "";
		String saveState = "";
		String saveZip = "";
		double saveTax = 0.06;
		
		if (txtBusiness.getText() != null && !txtBusiness.getText().trim().isEmpty()) {
			saveBusinessName = txtBusiness.getText();
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
		if (txtTax.getText() != null && !txtTax.getText().trim().isEmpty()) {
			String taxString = txtTax.getText();
			try{
				saveTax = Double.parseDouble(taxString);
			} catch(Exception e1){
				saveTax = 0.06;
			}
		}
		
		
		currentPref.setBusinessName(saveBusinessName);
		currentPref.setStreetaddress(saveStreetAddress);
		currentPref.setCity(saveCity);
		currentPref.setState(saveState);
		currentPref.setZip(saveZip);
		currentPref.setTax(saveTax);
		
		DataAccess.savePreference(currentPref);
		

		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

}
