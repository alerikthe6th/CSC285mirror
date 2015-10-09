
package edu.augustana.comorant.dataStructures;

import java.text.DecimalFormat;
import java.time.LocalDate;

import javafx.beans.property.*;

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
	protected StringProperty priceString;
	protected StringProperty phoneNumber;
	protected StringProperty email;
	protected BooleanProperty smsEnabled;
	protected StringProperty prefContactMethod;

	/**
	 * Default constructor.
	 * Creates a new Order with no data.
	 * 
	 * @param int orderNumber 
	 * @param LocalDate orderDate
	 * @param LocalDate dueDate
	 * @param String status
	 * @param String firstName
	 * @param String lastName
	 * @param String orderDesc
	 * @param String streetAddress
	 * @param String city
	 * @param String state
	 * @param String zip
	 * @param String paymentStatus
	 * @param String paymentMethod
	 * @param double price
	 * @param String phoneNumber
	 * @param String email
	 * @param boolean smsEnabled
	 * @param String prefContactMethod
	 * 
	 */
	public Order() {
		this(0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, false, null);
	}

	/**
	 * Constructor with some initial data.	
	 * Creates a new order with given parameters
	 *
	 * @param int orderNumber 
	 * @param LocalDate orderDate
	 * @param LocalDate dueDate
	 * @param String status
	 * @param String firstName
	 * @param String lastName
	 * @param String orderDesc
	 * @param String streetAddress
	 * @param String city
	 * @param String state
	 * @param String zip
	 * @param String paymentStatus
	 * @param String paymentMethod
	 * @param double price
	 * @param String phoneNumber
	 * @param String email
	 * @param boolean smsEnabled
	 * @param String prefContactMethod
	 * 
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
		DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
		String priceStringString = "$" + twoDigitFormat.format(this.price.getValue());
		this.priceString = new SimpleStringProperty(priceStringString);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
		this.smsEnabled = new SimpleBooleanProperty(smsEnabled);
		this.prefContactMethod = new SimpleStringProperty(prefContactMethod);
	}

	
	//field accessors
	/**Returns the order number 
	 * @return int */
	public int getOrderNumber() {
		return orderNumber.get();
	}
	/**Returns the order date
	 * @return LocalDate */
	public LocalDate getOrderDate() {
		return orderDate.get();
	}
	/**Returns the order's due date
	 * @return LocalDate */
	public LocalDate getDueDate() {
		return dueDate.get();
	}
	/**Returns the order's first name
	 * @return String */
	public String getFirstName() {
		return firstName.get();
	}
	/**Returns the order's last name
	 * @return String */
	public String getLastName() {
		return lastName.get();
	}
	/**Returns the order's status 
	 * @return String */
	public String getStatus() {
		return status.get();
	}
	/**Returns the order's description 
	 * @return String */
	public String getOrderDesc() {
		return orderDesc.get();
	}
	/**Returns the order's street address 
	 * @return String */
	public String getStreetAddress() {
		return streetAddress.get();
	}
	/**Returns the order's city 
	 * @return String */
	public String getCity() {
		return city.get();
	}
	/**Returns the order's state 
	 * @return String */
	public String getState() {
		return state.get();
	}
	/**Returns the order's zip code 
	 * @return String */
	public String getZip() {
		return zip.get();
	}
	/**Returns the order's shipping address
	 * @return String */
	public String getFullAddress() {
		return fullAddress.get();
	}
	/**Returns the order's payment status 
	 * @return String */
	public String getPaymentStatus() {
		return paymentStatus.get();
	}
	/**Returns the order's email address 
	 * @return String */
	public String getEmail() {
		return email.get();
	}
	/**Returns the order's price 
	 * @return double */
	public double getPrice() {
		return price.get();
	}
	/**Returns the order's phone number
	 * @return String */
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	/**Returns the order's payment method 
	 * @return String */
	public String getPaymentMethod() {
		return paymentMethod.get();
	}
	/**Returns whether the order is SMS enabled or not 
	 * @return boolean */
	public boolean getSmsEnabled() {
		return smsEnabled.get();
	}
	/**Returns the order's preferred contact method
	 * @return String */
	public String getPrefContactMethod() {
		return prefContactMethod.get();
	}
	
	
	//property accessors
	/**@return IntegerProperty*/
	public IntegerProperty orderNumberProperty() {
		return orderNumber;
	}
	/**@return ObjectProperty*/
	public ObjectProperty<LocalDate> orderDateProperty() {
		return orderDate;
	}
	/**@return ObjectProperty*/
	public ObjectProperty<LocalDate> dueDateProperty() {
		return dueDate;
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
	public StringProperty statusProperty() {
		return status;
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
	public StringProperty paymentStatusProperty() {
		return paymentStatus;
	}
	/**@return StringProperty*/
	public StringProperty paymentMethodProperty() {
		return paymentMethod;
	}
	/**@return DoubleProperty*/
	public DoubleProperty priceProperty() {
		return price;
	}
	/**@return StringProperty*/
	public StringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	/**@return StringProperty*/
	public StringProperty emailProperty() {
		return email;
	}
	/**@return BooleanProperty*/
	public BooleanProperty smsEnabledProperty() {
		return smsEnabled;
	}
	/**@return StringProperty*/
	public StringProperty prefContactMethodProperty() {
		return prefContactMethod;
	}
	/**@return SimpleStringProperty*/
	public StringProperty smsEnabledStringProperty() {
		if (this.getSmsEnabled() == true) {
			return new SimpleStringProperty("Yes");
		}
		return new SimpleStringProperty("No");
		
		
	}
	/**@return StringProperty*/
	public StringProperty priceStringProperty() {
		return priceString;
	}

	//field mutators
	/**Sets the order number*/
	public void setOrderNumber(int orderNumber) {
		this.orderNumber.set(orderNumber);
	}
	/**Sets the order's order date*/
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate.set(orderDate);
	}
	/**Sets the order's due date*/
	public void setDueDate(LocalDate dueDate) {
		this.dueDate.set(dueDate);
	}
	/**Sets the order's first name*/
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	/**Sets the order's last name*/
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	/**Sets the order's status*/
	public void setStatus(String status) {
		this.status.set(status);
	}
	/**Sets the order's description*/
	public void setOrderDesc(String desc) {
		this.orderDesc.set(desc);
	}
	/**Sets the order's street address*/
	public void setStreetAddress(String streetAddress) {
		this.streetAddress.set(streetAddress);
		redoShippingAddress();
	}
	/**Sets the order's city*/
	public void setCity(String city) {
		this.city.set(city);
		redoShippingAddress();
	}
	/**Sets the order's state*/
	public void setState(String state) {
		this.state.set(state);
		redoShippingAddress();
	}
	/**Sets the order's payment status*/
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus.set(paymentStatus);
	}
	/**Sets the order's zip code*/
	public void setZip(String zip) {
		this.zip.set(zip);
		redoShippingAddress();
	}
	/**Sets the order's payment method*/
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod.set(paymentMethod);
	}
	/**Sets the order's price*/
	public void setPrice(double price) {
		this.price.set(price);
		DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
		String priceStringString = "$" + twoDigitFormat.format(price);
		this.priceString.set(priceStringString);
	}
	/**Sets the order's phone number*/
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	/**Sets the order's email address*/
	public void setEmail(String email) {
		this.email.set(email);
	}
	/**Sets whether the order is SMS enabled or not */
	public void setSMSEnabled(boolean smsEnabled) {
		this.smsEnabled.set(smsEnabled);
	}
	/**Sets the order's preferred contact method*/
	public void setPrefContactMethod(String prefContactMethod) {
		this.prefContactMethod.set(prefContactMethod);
	}
	/**Updates the order's full shipping address*/
	public void redoShippingAddress() {
		this.fullAddress.set(this.getStreetAddress() + "\n" + this.getCity() + " " + this.getState() + " " + this.getZip());
	}
	

	
	
}

