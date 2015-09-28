
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
	/**Returns the order number 
	 * @return int */
	protected int getOrderNumber() {
		return orderNumber.get();
	}
	/**Returns the order date
	 * @return LocalDate */
	protected LocalDate getOrderDate() {
		return orderDate.get();
	}
	/**Returns the order's due date
	 * @return LocalDate */
	protected LocalDate getDueDate() {
		return dueDate.get();
	}
	/**Returns the order's first name
	 * @return String */
	protected String getFirstName() {
		return firstName.get();
	}
	/**Returns the order's last name
	 * @return String */
	protected String getLastName() {
		return lastName.get();
	}
	/**Returns the order's status 
	 * @return String */
	protected String getStatus() {
		return status.get();
	}
	/**Returns the order's description 
	 * @return String */
	protected String getOrderDesc() {
		return orderDesc.get();
	}
	/**Returns the order's street address 
	 * @return String */
	protected String getStreetAddress() {
		return streetAddress.get();
	}
	/**Returns the order's city 
	 * @return String */
	protected String getCity() {
		return city.get();
	}
	/**Returns the order's state 
	 * @return String */
	protected String getState() {
		return state.get();
	}
	/**Returns the order's zip code 
	 * @return String */
	protected String getZip() {
		return zip.get();
	}
	/**Returns the order's shipping address
	 * @return String */
	protected String getFullAddress() {
		return fullAddress.get();
	}
	/**Returns the order's payment status 
	 * @return String */
	protected String getPaymentStatus() {
		return paymentStatus.get();
	}
	/**Returns the order's email address 
	 * @return String */
	protected String getEmail() {
		return email.get();
	}
	/**Returns the order's price 
	 * @return double */
	protected double getPrice() {
		return price.get();
	}
	/**Returns the order's phone number
	 * @return String */
	protected String getPhoneNumber() {
		return phoneNumber.get();
	}
	/**Returns the order's payment method 
	 * @return String */
	protected String getPaymentMethod() {
		return paymentMethod.get();
	}
	/**Returns whether the order is SMS enabled or not 
	 * @return boolean */
	protected boolean getSmsEnabled() {
		return smsEnabled.get();
	}
	/**Returns the order's preferred contact method
	 * @return String */
	protected String getPrefContactMethod() {
		return prefContactMethod.get();
	}
	
	
	//property accessors
	/**@return IntegerProperty*/
	protected IntegerProperty orderNumberProperty() {
		return orderNumber;
	}
	/**@return ObjectProperty*/
	protected ObjectProperty<LocalDate> orderDateProperty() {
		return orderDate;
	}
	/**@return ObjectProperty*/
	protected ObjectProperty<LocalDate> dueDateProperty() {
		return dueDate;
	}
	/**@return StringProperty*/
	protected StringProperty firstNameProperty() {
		return firstName;
	}
	/**@return StringProperty*/
	protected StringProperty lastNameProperty() {
		return lastName;
	}
	/**@return StringProperty*/
	protected StringProperty statusProperty() {
		return status;
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
	protected StringProperty paymentStatusProperty() {
		return paymentStatus;
	}
	/**@return StringProperty*/
	protected StringProperty paymentMethodProperty() {
		return paymentMethod;
	}
	/**@return DoubleProperty*/
	protected DoubleProperty priceProperty() {
		return price;
	}
	/**@return StringProperty*/
	protected StringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	/**@return StringProperty*/
	protected StringProperty emailProperty() {
		return email;
	}
	/**@return BooleanProperty*/
	protected BooleanProperty smsEnabledProperty() {
		return smsEnabled;
	}
	/**@return StringProperty*/
	protected StringProperty prefContactMethodProperty() {
		return prefContactMethod;
	}
	/**@return SimpleStringProperty*/
	protected StringProperty smsEnabledStringProperty() {
		if (this.getSmsEnabled() == true) {
			return new SimpleStringProperty("Yes");
		}
		return new SimpleStringProperty("No");
		
		
	}

	//field mutators
	/**Sets the order number*/
	protected void setOrderNumber(int orderNumber) {
		this.orderNumber.set(orderNumber);
	}
	/**Sets the order's order date*/
	protected void setOrderDate(LocalDate orderDate) {
		this.orderDate.set(orderDate);
	}
	/**Sets the order's due date*/
	protected void setDueDate(LocalDate dueDate) {
		this.dueDate.set(dueDate);
	}
	/**Sets the order's first name*/
	protected void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	/**Sets the order's last name*/
	protected void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	/**Sets the order's status*/
	protected void setStatus(String status) {
		this.status.set(status);
	}
	/**Sets the order's description*/
	protected void setOrderDesc(String desc) {
		this.orderDesc.set(desc);
	}
	/**Sets the order's street address*/
	protected void setStreetAddress(String streetAddress) {
		this.streetAddress.set(streetAddress);
	}
	/**Sets the order's city*/
	protected void setCity(String city) {
		this.city.set(city);
	}
	/**Sets the order's state*/
	protected void setState(String state) {
		this.state.set(state);
	}
	/**Sets the order's payment status*/
	protected void setPaymentStatus(String paymentStatus) {
		this.paymentStatus.set(paymentStatus);
	}
	/**Sets the order's zip code*/
	protected void setZip(String zip) {
		this.zip.set(zip);
	}
	/**Sets the order's payment method*/
	protected void setPaymentMethod(String paymentMethod) {
		this.paymentMethod.set(paymentMethod);
	}
	/**Sets the order's price*/
	protected void setPrice(double price) {
		this.price.set(price);
	}
	/**Sets the order's phone number*/
	protected void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	/**Sets the order's email address*/
	protected void setEmail(String email) {
		this.email.set(email);
	}
	/**Sets whether the order is SMS enabled or not */
	protected void setSMSEnabled(boolean smsEnabled) {
		this.smsEnabled.set(smsEnabled);
	}
	/**Sets the order's preferred contact method*/
	protected void setPrefContactMethod(String prefContactMethod) {
		this.prefContactMethod.set(prefContactMethod);
	}
	
	protected void redoShippingAddress() {
		this.fullAddress.set(this.getStreetAddress() + "\n" + this.getCity() + " " + this.getState() + " " + this.getZip());
	}
	

	
	
}
