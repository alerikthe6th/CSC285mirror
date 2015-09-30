import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingGUI {
	
	private JFrame frame;
	private JTextField lastName;
	private JTextField firstName;
	private JTextField emailAddress;
	private JTextField phoneNumber;
	private JTextField orderDate;
	private JTextField orderCost;
	private JTextArea spreadSheet;
	private JButton editCurrent;
	private JButton deleteCurrent;
	private JPanel editButtons; 
	private JLabel last;
	private JLabel first;
	private JLabel email;
	private JLabel phone;
	private JLabel date;
	private JLabel cost;
	private JTextArea orderSpecs;
	
	
	public static void main(String[] args){
		SwingGUI yeah = new SwingGUI();
	}
	
	public SwingGUI(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1200, 800));
		frame.setLayout(new BorderLayout());
		frame.setTitle("Swing GUI");
		
		
		//North part (spreadsheet area and edit/delete buttons
		JPanel north = new JPanel(new BorderLayout());
		spreadSheet = new JTextArea("Stuffy Stuff etc", 10, 10);
		spreadSheet.setFont(new Font("Arial", Font.PLAIN, 20));
		editCurrent = new JButton("Edit Current");
		editCurrent.setFont(new Font("Arial", Font.PLAIN, 20));
		deleteCurrent = new JButton("Delete Current");
		deleteCurrent.setFont(new Font("Arial", Font.PLAIN, 20));
		editButtons = new JPanel(new GridLayout(2,1));
		editButtons.add(editCurrent);
		editButtons.add(deleteCurrent);
		north.add(editButtons, BorderLayout.EAST);
		north.add(spreadSheet, BorderLayout.CENTER);
		
		frame.add(north, BorderLayout.NORTH);
		
		//East part (order specs)
		JPanel east = new JPanel(new FlowLayout());
		orderSpecs = new JTextArea("Order Specifications", 10,10);
		orderSpecs.setFont(new Font("Arial", Font.PLAIN, 20));
		east.add(orderSpecs);
		
		frame.add(east, BorderLayout.EAST);
		
		//Center Bit with fields for data
		
		last = new JLabel("Last Name:");
		last.setFont(new Font("Arial", Font.BOLD, 20));
		first = new JLabel("First Name:");
		first.setFont(new Font("Arial", Font.BOLD, 20));
		email = new JLabel("Email Address:");
		email.setFont(new Font("Arial", Font.BOLD, 20));
		phone = new JLabel("Phone Number:");
		phone.setFont(new Font("Arial", Font.BOLD, 20));
		cost = new JLabel("Order Cost:");
		cost.setFont(new Font("Arial", Font.BOLD, 20));
		date = new JLabel("Order Date:");
		date.setFont(new Font("Arial", Font.BOLD, 20));
		
		
		lastName = new JTextField("Smith", 15);
		lastName.setFont(new Font("Arial", Font.PLAIN, 20));
		firstName = new JTextField("John");
		firstName.setFont(new Font("Arial", Font.PLAIN, 20));
		emailAddress = new JTextField("example@gmail.com", 35);
		emailAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		phoneNumber = new JTextField("1-234-567-8901");
		phoneNumber.setFont(new Font("Arial", Font.PLAIN, 20));
		orderCost = new JTextField("000.00", 18);
		orderCost.setFont(new Font("Arial", Font.PLAIN, 20));
		orderDate = new JTextField("MM/DD/YYYY");
		orderDate.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel dolSign = new JLabel("$");
		dolSign.setFont(new Font("Arial", Font.PLAIN, 20));

		JPanel costWithSign = new JPanel(new FlowLayout());
		costWithSign.add(dolSign);
		costWithSign.add(orderCost);
		
		JPanel halves = new JPanel(new FlowLayout(FlowLayout.CENTER,50,50));
		
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
		
		//TODO - remove placeholders
		JLabel place = new JLabel("Placeholder Yay");
		place.setFont(new Font("Arial", Font.BOLD, 20));
		JTextField holder = new JTextField("Placeholder Yay");
		holder.setFont(new Font("Arial", Font.PLAIN, 20));
		JLabel place2 = new JLabel("Placeholder Yay");
		place2.setFont(new Font("Arial", Font.BOLD, 20));
		JTextField holder2 = new JTextField("Placeholder Yay");
		holder2.setFont(new Font("Arial", Font.PLAIN, 20));
		
		left.add(last);
		left.add(lastName);
		left.add(email);
		left.add(emailAddress);
		//TODO - remove placeholders
		left.add(place);
		left.add(holder);
		left.add(place2);
		left.add(holder2);
		
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		
		
		right.add(first);
		right.add(firstName);
		right.add(phone);
		right.add(phoneNumber);
		right.add(date);
		right.add(orderDate);
		right.add(cost);
		
		right.add(costWithSign);
		
		halves.add(left);
		halves.add(right);
		
		frame.add(halves, BorderLayout.CENTER);

		
		
		
		
		frame.setVisible(true);
		
	}
}
