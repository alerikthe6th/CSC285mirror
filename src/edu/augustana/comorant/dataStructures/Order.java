/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

package edu.augustana.comorant.dataStructures;

import java.text.DecimalFormat;
import java.time.LocalDate;

import javafx.beans.property.*;

public class Order {
	protected Customer theCustomer;
	protected IntegerProperty orderNumber;
	protected ObjectProperty<LocalDate> orderDate;
	protected ObjectProperty<LocalDate> dueDate;
	protected StringProperty status;
	protected StringProperty orderDesc;
	protected StringProperty paymentStatus;
	protected StringProperty paymentMethod;
	protected DoubleProperty price;
	protected StringProperty priceString;
	protected StringProperty priceExpression;


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
	 * @param String email
	 * @param String phoneNumber
	 * @param boolean smsEnabled
	 * @param String prefContactMethod
	 * 
	 */
	

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
	 * @param String email
	 * @param String phoneNumber
	 * @param boolean smsEnabled
	 * @param String prefContactMethod
	 * 
	 */

	public Order(Customer customer, int orderNumber, LocalDate orderDate, LocalDate dueDate, String status,
			 String orderDesc,String paymentStatus, String paymentMethod, double price, String priceExp) {
		this.theCustomer = customer;

		this.orderNumber = new SimpleIntegerProperty(orderNumber);
		this.orderDate = new SimpleObjectProperty<LocalDate>(orderDate);
		this.dueDate = new SimpleObjectProperty<LocalDate>(dueDate);
		this.status = new SimpleStringProperty(status);
		this.orderDesc = new SimpleStringProperty(orderDesc);
		
		this.paymentStatus = new SimpleStringProperty(paymentStatus);
		this.paymentMethod = new SimpleStringProperty(paymentMethod);
		this.price = new SimpleDoubleProperty(price);
		DecimalFormat twoDigitFormat = new DecimalFormat("0.00");
		String priceStringString = "$" + twoDigitFormat.format(this.price.getValue());
		this.priceString = new SimpleStringProperty(priceStringString);
		this.priceExpression = new SimpleStringProperty(priceExp);

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
		return theCustomer.getFirstName();
	}
	/**Returns the order's last name
	 * @return String */
	public String getLastName() {
		return theCustomer.getLastName();
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
		return theCustomer.getStreetAddress();
	}
	/**Returns the order's city 
	 * @return String */
	public String getCity() {
		return theCustomer.getCity();
	}
	/**Returns the order's state 
	 * @return String */
	public String getState() {
		return theCustomer.getState();
	}
	/**Returns the order's zip code 
	 * @return String */
	public String getZip() {
		return theCustomer.getZip();
	}
	/**Returns the order's shipping address
	 * @return String */
	public String getFullAddress() {
		return theCustomer.getFullAddress();
	}
	/**Returns the order's payment status 
	 * @return String */
	public String getPaymentStatus() {
		return paymentStatus.get();
	}
	/**Returns the order's email address 
	 * @return String */
	public String getEmail() {
		return theCustomer.getEmail();
	}
	/**Returns the order's price 
	 * @return double */
	public double getPrice() {
		return price.get();
	}
	/**Returns the order's phone number
	 * @return String */
	public String getPhoneNumber() {
		return theCustomer.getPhoneNumber();
	}
	/**Returns the order's payment method 
	 * @return String */
	public String getPaymentMethod() {
		return paymentMethod.get();
	}
	/**Returns whether the order is SMS enabled or not 
	 * @return boolean */
	public boolean getSmsEnabled() {
		return theCustomer.getSMSEnabled();
	}
	/**Returns the order's preferred contact method
	 * @return String */
	public String getPrefContactMethod() {
		return theCustomer.getPrefContactMethod();
	}
	public int getCustomerNumber() {
		return theCustomer.getCustomerNumber();
	}
	public Customer getCustomer(){
		return theCustomer;
	}
	public String getPriceExp(){
		return priceExpression.get();
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
		return theCustomer.firstNameProperty();
	}
	/**@return StringProperty*/
	public StringProperty lastNameProperty() {
		return theCustomer.lastNameProperty();
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
		return theCustomer.streetAddressProperty();
	}
	/**@return StringProperty*/
	public StringProperty cityProperty() {
		return theCustomer.cityProperty();
	}
	/**@return StringProperty*/
	public StringProperty stateProperty() {
		return theCustomer.stateProperty();
	}
	/**@return StringProperty*/
	public StringProperty zipProperty() {
		return theCustomer.zipProperty();
	}
	/**@return StringProperty*/
	public StringProperty fullAddressProperty() {
		return theCustomer.fullAddressProperty();
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
		return theCustomer.phoneNumberProperty();
	}
	/**@return StringProperty*/
	public StringProperty emailProperty() {
		return theCustomer.emailProperty();
	}
	/**@return BooleanProperty*/
	public BooleanProperty smsEnabledProperty() {
		return theCustomer.smsEnabledProperty();
	}
	/**@return StringProperty*/
	public StringProperty prefContactMethodProperty() {
		return theCustomer.prefContactMethodProperty();
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
	public StringProperty priceExpProperty() {
		return priceExpression;
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
		theCustomer.firstName.set(firstName);
	}
	/**Sets the order's last name*/
	public void setLastName(String lastName) {
		theCustomer.lastName.set(lastName);
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
		theCustomer.streetAddress.set(streetAddress);
		redoShippingAddress();
	}
	/**Sets the order's city*/
	public void setCity(String city) {
		theCustomer.city.set(city);
		redoShippingAddress();
	}
	/**Sets the order's state*/
	public void setState(String state) {
		theCustomer.state.set(state);
		redoShippingAddress();
	}
	/**Sets the order's payment status*/
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus.set(paymentStatus);
	}
	/**Sets the order's zip code*/
	public void setZip(String zip) {
		theCustomer.zip.set(zip);
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
		theCustomer.phoneNumber.set(phoneNumber);
	}
	/**Sets the order's email address*/
	public void setEmail(String email) {
		theCustomer.email.set(email);
	}
	/**Sets whether the order is SMS enabled or not */
	public void setSMSEnabled(boolean smsEnabled) {
		theCustomer.smsEnabled.set(smsEnabled);
	}
	/**Sets the order's preferred contact method*/
	public void setPrefContactMethod(String prefContactMethod) {
		theCustomer.prefContactMethod.set(prefContactMethod);
	}
	/**Updates the order's full shipping address*/
	public void redoShippingAddress() {
		theCustomer.fullAddress.set(this.getStreetAddress() + "\n" + this.getCity() + " " + this.getState() + " " + this.getZip());
	}
	
	public void setPriceExp(String priceExp){
		this.priceExpression.set(priceExp);
	}
	

	
	
}

