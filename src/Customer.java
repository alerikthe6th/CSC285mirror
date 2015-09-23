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
	protected String getFirstName() {
		return firstName.get();
	}
	/**Returns the customer's last name 
	 * @return String */
	protected String getLastName() {
		return lastName.get();
	}
	/**Returns the customer's order description 
	 * @return String */
	protected String getOrderDesc() {
		return orderDesc.get();
	}
	/**Returns the customer's street address 
	 * @return String */
	protected String getStreetAddress() {
		return streetAddress.get();
	}
	/**Returns the customer's city 
	 * @return String */
	protected String getCity() {
		return city.get();
	}
	/**Returns the customer's state
	 * @return String */
	protected String getState() {
		return state.get();
	}
	/**Returns the customer's zip code
	 * @return String */
	protected String getZip() {
		return zip.get();
	}
	/**Returns the customer's payment method 
	 * @return String */
	protected String getPaymentMethod() {
		return paymentMethod.get();
	}
	/**Returns the customer's phone number 
	 * @return String */
	protected String getPhoneNumber() {
		return phoneNumber.get();
	}
	/**Returns the customer's shipping address 
	 * @return String */
	protected String getFullAddress() {
		return fullAddress.get();
	}
	/**Returns the customer's preferred contact method
	 * @return String */
	protected String getPrefContactMethod() {
		return prefContactMethod.get();
	}
	/**Returns the customer's email address
	 * @return String */
	protected String getEmail() {
		return email.get();
	}
	
	
	//property accessors
	/**@return StringProperty*/
	protected StringProperty firstNameProperty() {
		return firstName;
	}
	/**@return StringProperty*/
	protected StringProperty lastNameProperty() {
		return lastName;
	}
	/**@return StringProperty*/
	protected StringProperty orderDescProperty() {
		return orderDesc;
	}
	/**@return StringProperty*/
	protected StringProperty streetAddressProperty() {
		return streetAddress;
	}
	/**@return StringProperty*/
	protected StringProperty cityProperty() {
		return city;
	}
	/**@return StringProperty*/
	protected StringProperty stateProperty() {
		return state;
	}
	/**@return StringProperty*/
	protected StringProperty zipProperty() {
		return zip;
	}
	/**@return StringProperty*/
	protected StringProperty fullAddressProperty() {
		return fullAddress;
	}
	/**@return StringProperty*/
	protected StringProperty paymentMethodProperty() {
		return paymentMethod;
	}
	/**@return StringProperty*/
	protected StringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	/**@return StringProperty*/
	protected StringProperty emailProperty() {
		return email;
	}
	/**@return StringProperty*/
	protected StringProperty prefContactMethodProperty() {
		return prefContactMethod;
	}
	
	
	//field mutators
	/**Sets the customer's first name*/
	protected void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	/**Sets the customer's last name*/
	protected void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	/**Sets the customer's order description*/
	protected void setOrderDesc(String status) {
		this.orderDesc.set(status);
	}
	/**Sets the customer's street address*/
	protected void setStreetAddress(String streetAddress) {
		this.streetAddress.set(streetAddress);
	}
	/**Sets the customer's city*/
	protected void setCity(String city) {
		this.city.set(city);
	}
	/**Sets the customer's state*/
	protected void setState(String state) {
		this.state.set(state);
	}
	/**Sets the customer's zip code*/
	protected void setZip(String zip) {
		this.zip.set(zip);
	}
	/**Sets the customer's payment method*/
	protected void setPaymentMethod(String paymentMethod) {
		this.paymentMethod.set(paymentMethod);
	}
	/**Sets the customer's phone number*/
	protected void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	/**Sets the customer's email address*/
	protected void setEmail(String email) {
		this.email.set(email);
	}
	/**Sets the customer's preferred contact method*/
	protected void setPrefContactMethod(String prefContactMethod) {
		this.prefContactMethod.set(prefContactMethod);
	}



	

}
