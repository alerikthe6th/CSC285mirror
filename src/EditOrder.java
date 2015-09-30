
import java.time.LocalDate;

import javafx.beans.property.*;

/**
 * 9/21/15
 * created order class, copied from order class without the get methods.
 * the fields should be filled in the graph using the order class. the field includes an order object
 * that's declared in some other method that can be edited, hence the title of the class.
 * 
 * this is based on my thinking of how this edit class should function, subject to change.
 * feel free to change/input.
 * 
 * the purpose of this class is to edit the fields of the order class by 
 * utilizing the set methods of the order class
 * 
 * @edit Alerik Vi
 *
 */

public class EditOrder {
	
	protected Order order;

	
	//constructor
	public EditOrder(Order order) {
	}
	
	//field mutators
		/**Sets the order number*/
		protected void setOrderNumber(int orderNumber) {
			order.setOrderNumber(orderNumber);
		}
		/**Sets the order's order date*/
		protected void setOrderDate(LocalDate orderDate) {
			order.setOrderDate(orderDate);
		}
		/**Sets the order's due date*/
		protected void setDueDate(LocalDate dueDate) {
			order.setDueDate(dueDate);
		}
		/**Sets the order's first name*/
		protected void setFirstName(String firstName) {
			order.setFirstName(firstName);
		}
		/**Sets the order's last name*/
		protected void setLastName(String lastName) {
			order.setLastName(lastName);
		}
		/**Sets the order's status*/
		protected void setStatus(String status) {
			order.setStatus(status);
		}
		/**Sets the order's description*/
		protected void setOrderDesc(String desc) {
			order.setOrderDesc(desc);
		}
		/**Sets the order's street address*/
		protected void setStreetAddress(String streetAddress) {
			order.setStreetAddress(streetAddress);
		}
		/**Sets the order's city*/
		protected void setCity(String city) {
			order.setCity(city);
		}
		/**Sets the order's state*/
		protected void setState(String state) {
			order.setState(state);
		}
		/**Sets the order's payment status*/
		protected void setPaymentStatus(String paymentStatus) {
			order.setPaymentStatus(paymentStatus);
		}
		/**Sets the order's zip code*/
		protected void setZip(String zip) {
			order.setZip(zip);
		}
		/**Sets the order's payment method*/
		protected void setPaymentMethod(String paymentMethod) {
			order.setPaymentMethod(paymentMethod);
		}
		/**Sets the order's price*/
		protected void setPrice(double price) {
			order.setPrice(price);
		}
		/**Sets the order's phone number*/
		protected void setPhoneNumber(String phoneNumber) {
			order.setPhoneNumber(phoneNumber);
		}
		/**Sets the order's email address*/
		protected void setEmail(String email) {
			order.setEmail(email);
		}
		/**Sets whether the order is SMS enabled or not */
		protected void setSMSEnabled(boolean smsEnabled) {
			order.setSMSEnabled(smsEnabled);
		}
		/**Sets the order's preferred contact method*/
		protected void setPrefContactMethod(String prefContactMethod) {
			order.setPrefContactMethod(prefContactMethod);
		}
}

