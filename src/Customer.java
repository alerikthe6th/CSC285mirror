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
	protected String getFirstName() {
		return firstName.get();
	}
	protected String getLastName() {
		return lastName.get();
	}
	protected String getOrderDesc() {
		return orderDesc.get();
	}
	protected String getStreetAddress() {
		return streetAddress.get();
	}
	protected String getCity() {
		return city.get();
	}
	protected String getState() {
		return state.get();
	}
	protected String getZip() {
		return zip.get();
	}
	protected String getPaymentMethod() {
		return paymentMethod.get();
	}
	protected String getPhoneNumber() {
		return phoneNumber.get();
	}
	protected String getFullAddress() {
		return fullAddress.get();
	}
	protected String getPrefContactMethod() {
		return prefContactMethod.get();
	}
	protected String getEmail() {
		return email.get();
	}
	
	
	//property accessors
	protected StringProperty firstNameProperty() {
		return firstName;
	}
	protected StringProperty lastNameProperty() {
		return lastName;
	}
	protected StringProperty orderDescProperty() {
		return orderDesc;
	}
	protected StringProperty streetAddressProperty() {
		return streetAddress;
	}
	protected StringProperty cityProperty() {
		return city;
	}
	protected StringProperty stateProperty() {
		return state;
	}
	protected StringProperty zipProperty() {
		return zip;
	}
	protected StringProperty fullAddressProperty() {
		return fullAddress;
	}
	protected StringProperty paymentMethodProperty() {
		return paymentMethod;
	}
	protected StringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	protected StringProperty emailProperty() {
		return email;
	}
	protected StringProperty prefContactMethodProperty() {
		return prefContactMethod;
	}
	
	
	//field mutators
	protected void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	protected void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	protected void setOrderDesc(String status) {
		this.orderDesc.set(status);
	}
	protected void setStreetAddress(String streetAddress) {
		this.streetAddress.set(streetAddress);
	}
	protected void setCity(String city) {
		this.city.set(city);
	}
	protected void setState(String state) {
		this.state.set(state);
	}
	protected void setZip(String zip) {
		this.zip.set(zip);
	}
	protected void setPaymentMethod(String paymentMethod) {
		this.paymentMethod.set(paymentMethod);
	}
	protected void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	protected void setEmail(String email) {
		this.email.set(email);
	}
	protected void setPrefContactMethod(String prefContactMethod) {
		this.prefContactMethod.set(prefContactMethod);
	}



	

}
