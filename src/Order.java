
import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
	protected IntegerProperty orderNumber;
	protected ObjectProperty<LocalDate> orderDate;
	protected ObjectProperty<LocalDate> dueDate;
	protected StringProperty status;
	protected StringProperty firstName;
	protected StringProperty lastName;
	protected StringProperty orderDesc;
	protected StringProperty fullAddress;
	protected StringProperty streetAddress;
	protected StringProperty city;
	protected StringProperty state;
	protected StringProperty zip;
	protected StringProperty paymentStatus;
	protected StringProperty paymentMethod;
	protected DoubleProperty price;
	protected StringProperty phoneNumber;
	protected StringProperty email;
	protected BooleanProperty smsEnabled;
	protected StringProperty prefContactMethod;

	/**
	 * Default constructor.
	 */
	public Order() {
		this(0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, false, null);
	}

	/**
	 * Constructor with some initial data.
	 */
	public Order(int orderNumber, LocalDate orderDate, LocalDate dueDate, String status, String firstName,
			String lastName, String orderDesc, String streetAddress, String city, String state, String zip,
			String paymentStatus, String paymentMethod, double price, String phoneNumber, String email,
			boolean smsEnabled, String prefContactMethod) {
		this.orderNumber = new SimpleIntegerProperty(orderNumber);
		this.orderDate = new SimpleObjectProperty<LocalDate>(orderDate);
		this.dueDate = new SimpleObjectProperty<LocalDate>(dueDate);
		this.status = new SimpleStringProperty(status);
		this.orderDesc = new SimpleStringProperty(orderDesc);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.streetAddress = new SimpleStringProperty(streetAddress);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
		this.fullAddress = new SimpleStringProperty(
				this.getStreetAddress() + "\n" + this.getCity() + " " + this.getState() + " " + this.getZip());
		this.paymentStatus = new SimpleStringProperty(paymentStatus);
		this.paymentMethod = new SimpleStringProperty(paymentMethod);
		this.price = new SimpleDoubleProperty(price);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
		this.smsEnabled = new SimpleBooleanProperty(smsEnabled);
		this.prefContactMethod = new SimpleStringProperty(prefContactMethod);
	}

	
	//field accessors
	protected int getOrderNumber() {
		return orderNumber.get();
	}
	protected LocalDate getOrderDate() {
		return orderDate.get();
	}
	protected LocalDate getDueDate() {
		return dueDate.get();
	}
	protected String getFirstName() {
		return firstName.get();
	}
	protected String getLastName() {
		return lastName.get();
	}
	protected String getStatus() {
		return status.get();
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
	protected String getFullAddress() {
		return fullAddress.get();
	}
	protected String getPaymentStatus() {
		return paymentStatus.get();
	}
	protected String getEmail() {
		return email.get();
	}
	protected double getPrice() {
		return price.get();
	}
	protected String getPhoneNumber() {
		return phoneNumber.get();
	}
	protected String getPaymentMethod() {
		return paymentMethod.get();
	}
	protected boolean getSmsEnabled() {
		return smsEnabled.get();
	}
	protected String getPrefContactMethod() {
		return prefContactMethod.get();
	}
	
	
	//property accessors
	protected IntegerProperty orderNumberProperty() {
		return orderNumber;
	}
	protected ObjectProperty<LocalDate> orderDateProperty() {
		return orderDate;
	}
	protected ObjectProperty<LocalDate> dueDateProperty() {
		return dueDate;
	}
	protected StringProperty firstNameProperty() {
		return firstName;
	}
	protected StringProperty lastNameProperty() {
		return lastName;
	}
	protected StringProperty statusProperty() {
		return status;
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
	protected StringProperty paymentStatusProperty() {
		return paymentStatus;
	}
	protected StringProperty paymentMethodProperty() {
		return paymentMethod;
	}
	protected DoubleProperty priceProperty() {
		return price;
	}
	protected StringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	protected StringProperty emailProperty() {
		return email;
	}
	protected BooleanProperty smsEnabledProperty() {
		return smsEnabled;
	}
	protected StringProperty prefContactMethodProperty() {
		return prefContactMethod;
	}
	protected StringProperty smsEnabledStringProperty() {
		if (this.getSmsEnabled() == true) {
			return new SimpleStringProperty("Yes");
		}
		return new SimpleStringProperty("No");
		
		
	}

	//field mutators
	protected void setOrderNumber(int orderNumber) {
		this.orderNumber.set(orderNumber);
	}
	protected void setOrderDate(LocalDate orderDate) {
		this.orderDate.set(orderDate);
	}
	protected void setDueDate(LocalDate dueDate) {
		this.dueDate.set(dueDate);
	}
	protected void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	protected void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	protected void setStatus(String status) {
		this.status.set(status);
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
	protected void setPaymentStatus(String paymentStatus) {
		this.paymentStatus.set(paymentStatus);
	}
	protected void setZip(String zip) {
		this.zip.set(zip);
	}
	protected void setPaymentMethod(String paymentMethod) {
		this.paymentMethod.set(paymentMethod);
	}
	protected void setPrice(double price) {
		this.price.set(price);
	}
	protected void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	protected void setEmail(String email) {
		this.email.set(email);
	}
	protected void setSmsEnabled(boolean smsEnabled) {
		this.smsEnabled.set(smsEnabled);
	}
	protected void setPrefContactMethod(String prefContactMethod) {
		this.prefContactMethod.set(prefContactMethod);
	}

	
	
}
