package edu.augustana.comorant.launchers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


//Will need a popup stating that an invoice was created on the user's desktop and they need to open the file and print from there
//printer class will take as parameters the following: from address(from preferences), to address(from customer), 
//date ordered, date shipped, order desc, price, payment method (from order)

//will date ordered and date shipped be strings?
public class PrintInvoice {
	protected void createInvoice(String saveToDesktopString, String fromName, String fromAdrs, String fromCSZ, String custName, String custAdrs,
		String custCSZ, String dateOrdered, String dateShipped, String orderDesc, double price, String paymentMethod)
		throws FileNotFoundException, UnsupportedEncodingException{
		
		PrintWriter writer = new PrintWriter(saveToDesktopString, "UTF-8");

		String lines="-----------------------------------";//35 chars - print twice for 70 wide line (easier than 7*10)
		
		writer.print(fromName);
		writer.printf("%"+(70-fromName.length())+"s", "+------+\n");
		writer.print(fromAdrs);
		writer.printf("%"+(70-fromAdrs.length())+"s", "+-stamp+\n");
		writer.print(fromCSZ);
		writer.printf("%"+(70-fromCSZ.length())+"s", "+------+\n");
		
		writer.println("\n\n\n\n");
		
		writer.println("\t\t\t\t\t"+custName);
		writer.println("\t\t\t\t\t"+custAdrs);
		writer.println("\t\t\t\t\t"+custCSZ+"\n");
		
		writer.println("\n\n\n\n"+lines+lines+"\n\n\n\n");
		
		writer.println("Order Details:\n");
		
		writer.println("\tDate Ordered:\t" +dateOrdered+"\t\tDate Shipped:\t"+dateShipped+"\n");
		writer.println("\n\tItems Ordered:\n\t" +orderDesc+"\n\n");
		writer.println("\n\tPaid With:\n\t" +paymentMethod+"\n");
		
		writer.printf("%70s", "Total Cost: "+price+"\n");
		
		writer.close();
	}
}
/* original printer
 
  	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("C:/Users/Joseph/Desktop/PrintOut.doc", "UTF-8");
		String compName = "Company Name";
		String compAdrs = "123 Main Street";
		String compCSZ = "New York, NY 12345";
		String custName = "Customer Name";
		String custAdrs = "1234 Customer Boulevard";
		String custCSZ = "MiddleOfNowhere, IA, 52253";
		
		String dateOrdered = "10/13/2015";//will this be a string?
		String dateShipped = "10/21/2015";//^Ditto^
		String orderDesc = "12 pots, 6 plates and one giant hug";
		double price = 69.69;
		String paymentMethod = "Credit Card";
		
		
		
		String lines="-----------------------------------";//35 chars
		
		
		writer.print(compName);
		writer.printf("%"+(70-compName.length())+"s", "+------+\n");
		writer.print(compAdrs);
		writer.printf("%"+(70-compAdrs.length())+"s", "+-stamp+\n");
		writer.print(compCSZ);
		writer.printf("%"+(70-compCSZ.length())+"s", "+------+\n");
		
		writer.println("\n\n\n\n");
		
		writer.println("\t\t\t\t\t"+custName);
		writer.println("\t\t\t\t\t"+custAdrs);
		writer.println("\t\t\t\t\t"+custCSZ+"\n");
		
		writer.println("\n\n\n\n"+lines+lines+"\n\n\n\n");
		
		writer.println("Order Details:\n");
		
		writer.println("\tDate Ordered:\t" +dateOrdered+"\t\tDate Shipped:\t"+dateShipped+"\n");
		writer.println("\n\tItems Ordered:\n\t" +orderDesc+"\n\n");
		writer.println("\n\tPaid With:\n\t" +paymentMethod+"\n");
		
		writer.printf("%70s", "Total Cost: "+price+"\n");
		
		
		
		writer.println("\n\n\n\n\n\n");
		
		
		
		writer.close();
	}
*/
 



