

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
	private final IntegerProperty orderNumber;
    private final ObjectProperty<LocalDate> orderDate;
    private final ObjectProperty<LocalDate> dueDate;
    private final StringProperty status;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty orderDesc;
    private final StringProperty shippingAddress;
    private final StringProperty paymentStatus;
    private final StringProperty paymentMethod;
    private final DoubleProperty price;
    private final StringProperty phoneNumber;
    private final StringProperty email;
    private final BooleanProperty smsEnabled;
    private final StringProperty prefContactMethod;

    /**
     * Default constructor.
     */
    public Order() {
        this(0, null, null, null, null, null, null, null, null, null, 0, null, null, false, null);
    }

    /**
     * Constructor with some initial data.
     */
    public Order(int orderNumber, LocalDate orderDate, LocalDate dueDate, String status, String firstName, String lastName,
    		String orderDesc, String shippingAddress, String paymentStatus, String paymentMethod, double price, String phoneNumber,
    		String email, boolean smsEnabled, String prefContactMethod) {
    	this.orderNumber = new SimpleIntegerProperty(orderNumber);
    	this.orderDate = new SimpleObjectProperty<LocalDate>(orderDate);
    	this.dueDate = new SimpleObjectProperty<LocalDate>(dueDate);
    	this.status = new SimpleStringProperty(status);
    	this.orderDesc = new SimpleStringProperty(orderDesc);
    	this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.shippingAddress = new SimpleStringProperty(shippingAddress);
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

    

}
