package edu.augustana.comorant.dataStructures;



import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Preference {
	
	protected StringProperty businessName;
	protected StringProperty streetAddress;
	protected StringProperty streetAddressLine2;
	protected StringProperty city;
	protected StringProperty state;
	protected StringProperty zip;
	protected DoubleProperty tax;
	
	public Preference(String businessName, String streetAddress, String streetAddressLine2, String city, String state, String zip, double tax) {
		this.businessName = new SimpleStringProperty(businessName);
		this.streetAddress = new SimpleStringProperty(streetAddress);
		this.streetAddress = new SimpleStringProperty(streetAddressLine2);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
		
		this.tax = new SimpleDoubleProperty(tax);
		

	}
	
	public String getBusinessName(){
		return businessName.get();
	}
	public String getStreetAddress(){
		return streetAddress.get();
	}
	public String getStreetAddressLine2(){
		return streetAddressLine2.get();
	}
	public String getCity(){
		return city.get();
	}
	public String getState(){
		return state.get();
	}
	public String getZip(){
		return zip.get();
	}
	public double getTax(){
		return tax.get();
	}
	
	
	public void setBusinessName(String newBusiness){
		businessName.set(newBusiness);
	}
	public void setStreetaddress(String newAddress){
		streetAddress.set(newAddress);
	}
	public void setStreetaddressLine2(String newAddressLine2){
		streetAddressLine2.set(newAddressLine2);
	}
	public void setCity(String newCity){
		city.set(newCity);
	}
	public void setState(String newState){
		state.set(newState);
	}
	public void setZip(String newZip){
		zip.set(newZip);
	}
	public void setTax(double newTax){
		if(newTax > 1){
			newTax = newTax / 100;
		}
		tax.set(newTax);
	}

}
