import java.time.LocalDate;

import javafx.beans.property.*;

//well, i didn't have much time but i 
//filtered out what i think would best describe the customer class.
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
	
	/*
	 * TODO: finish constructor
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
				this.getStreetAddress() + "\n" + this.getCity() + " " + this.getState() + " " + this.getZip());
		this.paymentMethod = new SimpleStringProperty(paymentMethod);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
		this.prefContactMethod = new SimpleStringProperty(prefContactMethod);
		
	}
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getOrderDesc() {
		return orderDesc.get();
	}

	public void setOrderDesc(String status) {
		this.orderDesc.set(status);
	}

	public StringProperty orderDescProperty() {
		return orderDesc;
	}

	public String getStreetAddress() {
		return streetAddress.get();
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress.set(streetAddress);
	}

	public StringProperty streetAddressProperty() {
		return streetAddress;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty() {
		return city;
	}

	public String getState() {
		return state.get();
	}

	public void setState(String state) {
		this.state.set(state);
	}

	public StringProperty stateProperty() {
		return state;
	}

	public String getZip() {
		return zip.get();
	}

	public void setZip(String zip) {
		this.zip.set(zip);
	}

	public StringProperty zipProperty() {
		return zip;
	}

	public String getFullAddress() {
		return fullAddress.get();
	}

	public StringProperty fullAddressProperty() {
		return fullAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod.get();
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod.set(paymentMethod);
	}

	public StringProperty paymentMethodProperty() {
		return paymentMethod;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public StringProperty phoneNumberProperty() {
		return phoneNumber;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public StringProperty emailProperty() {
		return email;
	}

	public String getPrefContactMethod() {
		return prefContactMethod.get();
	}

	public void setPrefContactMethod(String prefContactMethod) {
		this.prefContactMethod.set(prefContactMethod);
	}

	public StringProperty prefContactMethodProperty() {
		return prefContactMethod;
	}


	

}
