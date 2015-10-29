/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

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
		this.streetAddressLine2 = new SimpleStringProperty(streetAddressLine2);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
		
		this.tax = new SimpleDoubleProperty(tax);
		

	}
	/** Returns the business' name */
	public String getBusinessName(){
		return businessName.get();
	}
	/** Returns the business' street address */
	public String getStreetAddress(){
		return streetAddress.get();
	}
	/** Returns the business' street address line 2 */
	public String getStreetAddressLine2(){
		return streetAddressLine2.get();
	}
	/** Returns the business' city */
	public String getCity(){
		return city.get();
	}
	/** Returns the business' state */
	public String getState(){
		return state.get();
	}
	/** Returns the business' zip code */
	public String getZip(){
		return zip.get();
	}
	/** Returns the business' tax rate */
	public double getTax(){
		return tax.get();
	}
	
	/** Sets the business' name */
	public void setBusinessName(String newBusiness){
		businessName.set(newBusiness);
	}
	/** Sets the business' street address */
	public void setStreetaddress(String newAddress){
		streetAddress.set(newAddress);
	}
	/** Sets the business' street address line 2 */
	public void setStreetaddressLine2(String newAddressLine2){
		streetAddressLine2.set(newAddressLine2);
	}
	/** Sets the business' city */
	public void setCity(String newCity){
		city.set(newCity);
	}
	/** Sets the business' state */
	public void setState(String newState){
		state.set(newState);
	}
	/** Sets the business' zip code */
	public void setZip(String newZip){
		zip.set(newZip);
	}
	/** Sets the business' tax rate */
	public void setTax(double newTax){
		if(newTax > 1){
			newTax = newTax / 100;
		}
		tax.set(newTax);
	}

}
