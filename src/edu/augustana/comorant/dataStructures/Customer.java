package edu.augustana.comorant.dataStructures;
import javafx.beans.property.*;

public class Customer {
	
	protected StringProperty firstName;
	protected StringProperty lastName;
	protected StringProperty orderDesc;
	protected StringProperty fullAddress;
	protected StringProperty streetAddress;
	protected StringProperty city;
	protected StringProperty state;
	protected StringProperty zip;
	protected StringProperty paymentMethod;
	protected StringProperty phoneNumber;
	protected StringProperty email;
	protected StringProperty prefContactMethod;
	
	/**
	 * Constructor with some initial data.	
	 * Creates a new customer with given parameters
	 *
	 * @param String firstName
	 * @param String lastName
	 * @param String orderDesc
	 * @param String streetAddress
	 * @param String city
	 * @param String state
	 * @param String zip
	 * @param String paymentMethod
	 * @param String phoneNumber
	 * @param String email
	 * @param String prefContactMethod
	 * 
	 */
	public Customer(String firstName, String lastName, String orderDesc, String streetAddress, 
			String city, String state, String zip, String paymentMethod, 
			String phoneNumber, String email, String prefContactMethod) {
		
		this.orderDesc = new SimpleStringProperty(orderDesc);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.streetAddress = new SimpleStringProperty(streetAddress);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
		this.fullAddress = new SimpleStringProperty(
				this.getStreetAddress() + "\n" + this.getCity() + ", " + this.getState() + " " + this.getZip());
		this.paymentMethod = new SimpleStringProperty(paymentMethod);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
		this.prefContactMethod = new SimpleStringProperty(prefContactMethod);
	}
	
	//field accessors
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
	/**Returns the customer's order description 
	 * @return String */
	public String getOrderDesc() {
		return orderDesc.get();
	}
	/**Returns the customer's street address 
	 * @return String */
	public String getStreetAddress() {
		return streetAddress.get();
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
	/**Returns the customer's payment method 
	 * @return String */
	public String getPaymentMethod() {
		return paymentMethod.get();
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
	
	
	//property accessors
	/**@return StringProperty*/
	public StringProperty firstNameProperty() {
		return firstName;
	}
	/**@return StringProperty*/
	public StringProperty lastNameProperty() {
		return lastName;
	}
	/**@return StringProperty*/
	public StringProperty orderDescProperty() {
		return orderDesc;
	}
	/**@return StringProperty*/
	public StringProperty streetAddressProperty() {
		return streetAddress;
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
	public StringProperty paymentMethodProperty() {
		return paymentMethod;
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
	
	
	//field mutators
	/**Sets the customer's first name*/
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	/**Sets the customer's last name*/
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	/**Sets the customer's order description*/
	public void setOrderDesc(String status) {
		this.orderDesc.set(status);
	}
	/**Sets the customer's street address*/
	public void setStreetAddress(String streetAddress) {
		this.streetAddress.set(streetAddress);
	}
	/**Sets the customer's city*/
	public void setCity(String city) {
		this.city.set(city);
	}
	/**Sets the customer's state*/
	public void setState(String state) {
		this.state.set(state);
	}
	/**Sets the customer's zip code*/
	public void setZip(String zip) {
		this.zip.set(zip);
	}
	/**Sets the customer's payment method*/
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod.set(paymentMethod);
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



	

}
