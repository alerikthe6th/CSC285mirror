/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

package edu.augustana.comorant.dataStructures;
import java.text.DecimalFormat;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;

public class Customer {
	
	protected IntegerProperty customerNumber;
	protected StringProperty firstName;
	protected StringProperty lastName;
	protected StringProperty fullAddress;
	protected StringProperty streetAddress;
	protected StringProperty streetAddressLine2;
	protected StringProperty city;
	protected StringProperty state;
	protected StringProperty zip;
	protected StringProperty phoneNumber;
	protected StringProperty email;
	protected StringProperty prefContactMethod;
	protected BooleanProperty smsEnabled;
	
	protected StringProperty fullName;
	protected StringProperty balance;
	
	/**
	 * Constructor with some initial data.	
	 * Creates a new customer with given parameters
	 *
	 * @param String firstName
	 * @param String lastName
	 * @param String orderDesc
	 * @param String streetAddress
	 * @param String streetAddressLine2
	 * @param String city
	 * @param String state
	 * @param String zip
	 * @param String paymentMethod
	 * @param String phoneNumber
	 * @param String email
	 * @param String prefContactMethod
	 * 
	 */
	public Customer(int customerNumber, String firstName, String lastName, String streetAddress, 
			String streetAddressLine2, String city, String state, String zip,
			String phoneNumber, String email, String prefContactMethod, boolean smsEnabled) {
		
		this.customerNumber = new SimpleIntegerProperty(customerNumber);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.fullName = new SimpleStringProperty(firstName + " " + lastName);
		this.streetAddress = new SimpleStringProperty(streetAddress);
		this.streetAddressLine2 = new SimpleStringProperty(streetAddressLine2);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
		if(!(getStreetAddressLine2().equals(""))){
			this.fullAddress = new SimpleStringProperty(this.getStreetAddress() + "\n" + 
					this.getStreetAddressLine2() + "\n" + this.getCity() + ", " + this.getState() + " " + this.getZip());
		}else{
			this.fullAddress = new SimpleStringProperty(this.getStreetAddress() + "\n" +
					this.getCity() + ", " + this.getState() + " " + this.getZip());
		}
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
		this.prefContactMethod = new SimpleStringProperty(prefContactMethod);
		this.smsEnabled = new SimpleBooleanProperty(smsEnabled);
		this.balance = new SimpleStringProperty("");
	}
	
	//field accessors
	public int getCustomerNumber(){
		return customerNumber.get();
	}
	/**Returns the customer's first name 
	 * @return String */
	public String getFirstName() {
		return firstName.get();
	}
	/**Returns the customer's last name 
	 * @return String */
	public String getLastName() {
		return lastName.get();
	}
	/**Returns the customer's street address 
	 * @return String */
	public String getStreetAddress() {
		return streetAddress.get();
	}
	/**Returns the customer's street address line 2 
	 * @return String */
	public String getStreetAddressLine2() {
		return streetAddressLine2.get();
	}
	/**Returns the customer's city 
	 * @return String */
	public String getCity() {
		return city.get();
	}
	/**Returns the customer's state
	 * @return String */
	public String getState() {
		return state.get();
	}
	/**Returns the customer's zip code
	 * @return String */
	public String getZip() {
		return zip.get();
	}
	/**Returns the customer's phone number 
	 * @return String */
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	/**Returns the customer's shipping address 
	 * @return String */
	public String getFullAddress() {
		return fullAddress.get();
	}
	/**Returns the customer's preferred contact method
	 * @return String */
	public String getPrefContactMethod() {
		return prefContactMethod.get();
	}
	/**Returns the customer's email address
	 * @return String */
	public String getEmail() {
		return email.get();
	}
	/**Returns whether or not the customer can receive texts
	 * @return boolean */
	public boolean getSMSEnabled() {
		return smsEnabled.get();
	}
	
	
	//property accessors
	public IntegerProperty customerNumberProperty() {
		return customerNumber;
	}
	/**@return StringProperty*/
	public StringProperty firstNameProperty() {
		return firstName;
	}
	/**@return StringProperty*/
	public StringProperty lastNameProperty() {
		return lastName;
	}
	/**@return StringProperty*/
	public StringProperty streetAddressProperty() {
		return streetAddress;
	}
	/**@return StringProperty*/
	public StringProperty streetAddressPropertyLine2() {
		return streetAddressLine2;
	}
	/**@return StringProperty*/
	public StringProperty bothStreetAddressProperty() {
		if (streetAddressLine2.getValue().equals("")){
			return streetAddress;
		}else{
			StringProperty s3 = new SimpleStringProperty();
			s3.bind(Bindings.concat(streetAddress, "\n", streetAddressLine2));
			return s3;
		}
	}
	/**@return StringProperty*/
	public StringProperty cityProperty() {
		return city;
	}
	/**@return StringProperty*/
	public StringProperty stateProperty() {
		return state;
	}
	/**@return StringProperty*/
	public StringProperty zipProperty() {
		return zip;
	}
	/**@return StringProperty*/
	public StringProperty fullAddressProperty() {
		return fullAddress;
	}
	/**@return StringProperty*/
	public StringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	/**@return StringProperty*/
	public StringProperty emailProperty() {
		return email;
	}
	/**@return StringProperty*/
	public StringProperty prefContactMethodProperty() {
		return prefContactMethod;
	}
	/**@return BooleanProperty*/
	public BooleanProperty smsEnabledProperty() {
		return smsEnabled;
	}
	/**@return StringProperty*/
	public StringProperty fullNameProperty(){
		return fullName;
	}
	/**@return StringProperty*/
	public StringProperty balanceProperty(){
		return balance;
	}
	
	//field mutators
	/** Sets the customer's number */
	public void setcustomerNumber(int customerNumber){
		this.customerNumber.set(customerNumber);
	}
	/**Sets the customer's first name*/
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
		this.fullName.set(this.firstName.get() + " " + this.lastName.get());
	}
	/**Sets the customer's last name*/
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
		this.fullName.set(this.firstName.get() + " " + this.lastName.get());
	}
	/**Sets the customer's street address*/
	public void setStreetAddress(String streetAddress) {
		this.streetAddress.set(streetAddress);
		resetFullAddress();
	}
	/**Sets the customer's street address line 2*/
	public void setStreetAddressLine2(String streetAddressLine2) {
		this.streetAddressLine2.set(streetAddressLine2);
		resetFullAddress();
	}
	/**Sets the customer's city*/
	public void setCity(String city) {
		this.city.set(city);
		resetFullAddress();
	}
	/**Sets the customer's state*/
	public void setState(String state) {
		this.state.set(state);
		resetFullAddress();
	}
	/**Sets the customer's zip code*/
	public void setZip(String zip) {
		this.zip.set(zip);
		resetFullAddress();
	}
	/**Sets the customer's phone number*/
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	/**Sets the customer's email address*/
	public void setEmail(String email) {
		this.email.set(email);
	}
	/**Sets the customer's preferred contact method*/
	public void setPrefContactMethod(String prefContactMethod) {
		this.prefContactMethod.set(prefContactMethod);
	}
	/**Sets the customer's SMS Enabled*/
	public void setSmsEnabled(boolean newSmsEnabled) {
		this.smsEnabled.set(newSmsEnabled);
	}
	
	/** Sets the customer's balance */
	public void setBalance(double balance){
		DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
		String balanceString = "$" + twoDigitFormat.format(balance);
		this.balance.set(balanceString);
	}
	
	/** Clears and reinstates the full address */
	public void resetFullAddress(){
		if(this.getStreetAddressLine2() != "" && this.getStreetAddressLine2() != null){
			this.fullAddress.set(this.getStreetAddress() + "\n" + this.getStreetAddressLine2() + "\n" + this.getCity() + ", " + this.getState() + " " + this.getZip());
		}else{
			this.fullAddress.set(this.getStreetAddress() + "\n" + this.getCity() + ", " + this.getState() + " " + this.getZip());
		}
	}
	
	/** checks if a customer is equal to another */
	public boolean equals(Customer testCustomer){
		return this.getFirstName().toLowerCase().equals(testCustomer.getFirstName().toLowerCase()) && this.getLastName().toLowerCase().equals(testCustomer.getLastName().toLowerCase())
				&& this.getFullAddress().equals(testCustomer.getFullAddress());
	}

	



	

}

