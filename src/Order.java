
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

	public int getOrderNumber() {
		return orderNumber.get();
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber.set(orderNumber);
	}

	public IntegerProperty orderNumberProperty() {
		return orderNumber;
	}

	public LocalDate getOrderDate() {
		return orderDate.get();
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate.set(orderDate);
	}

	public ObjectProperty<LocalDate> orderDateProperty() {
		return orderDate;
	}

	public LocalDate getDueDate() {
		return dueDate.get();
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate.set(dueDate);
	}

	public ObjectProperty<LocalDate> dueDateProperty() {
		return dueDate;
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

	public String getStatus() {
		return status.get();
	}

	public void setStatus(String status) {
		this.status.set(status);
	}

	public StringProperty statusProperty() {
		return status;
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

	public String getPaymentStatus() {
		return paymentStatus.get();
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus.set(paymentStatus);
	}

	public StringProperty paymentStatusProperty() {
		return paymentStatus;
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

	public double getPrice() {
		return price.get();
	}

	public void setPrice(double price) {
		this.price.set(price);
	}

	public DoubleProperty priceProperty() {
		return price;
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

	public boolean getSmsEnabled() {
		return smsEnabled.get();
	}

	public void setSmsEnabled(boolean smsEnabled) {
		this.smsEnabled.set(smsEnabled);
	}

	public BooleanProperty smsEnabledProperty() {
		return smsEnabled;
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

	public StringProperty smsEnabledStringProperty() {
		if (this.getSmsEnabled() == true) {
			return new SimpleStringProperty("Yes");
		}
		return new SimpleStringProperty("No");
	}
}
