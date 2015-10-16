package edu.augustana.comorant.launchers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;


//Will need a popup stating that an invoice was created on the user's desktop and they need to open the file and print from there
//printer class will take as parameters the following: from address(from preferences), to address(from customer), 
//date ordered, date shipped, order desc, price, payment method (from order)

//will date ordered be a string?
public class PrintInvoice {
	/*
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		createInvoice("C:/Users/Joseph/Desktop/PrintOut.doc", "Firsty Lastname", "123 Main Street", "New York, NY 12345",
		//createInvoice("C:/Users/josephgodfrey14/Desktop/PrintOut.doc", "Firsty Lastname", "123 Main Street", "New York, NY 12345", 
				"John Cena", "123 JCena St.", "Cena WY, 54321", "Oct 13 2015", "12 Pots, 6 Plates, and one giant hug", 
				12.56, "Credit Card");
		//printPage("C:/Users/josephgodfrey14/Desktop/PrintOut.doc");
	}
	*/
	/**
	 * 
	 * @param saveToDesktopString - String; ex: "C:/Users/Joseph/Desktop/PrintOut.doc"
	 * @param fromName - String; ex: "Firsty Lastname"
	 * @param fromAdrs - String; ex: "123 Main Street"
	 * @param fromCSZ - String; ex: "New York, NY 12345"
	 * @param custName - String; ex: "John Q. Customer"
	 * @param custAdrs - String; ex: "1755 John Cena Boulevard"
	 * @param custCSZ - String; ex: "New York, NY 12345"
	 * @param dateOrdered - String; ex: "October 12, 2015"
	 * @param orderDesc - String; ex: "12 Pots, 6 Plates, and a big hug"
	 * @param price - double; ex: 12.34
	 * @param paymentMethod - String; ex: "Credit Card"
	 * @throws FileNotFoundException only thrown if the filepath is incorrect
	 * @throws UnsupportedEncodingException when the URL(filepath) of the file has invalid characters
	 */
	protected void createInvoice(String saveToDesktopString, String fromName, String fromAdrs, String fromCSZ, String custName, String custAdrs,
		String custCSZ, String dateOrdered, String orderDesc, double price, String paymentMethod)
		throws FileNotFoundException, UnsupportedEncodingException{
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
		Date date = new Date();
		String currentDate = (dateFormat.format(date)).toString(); //10/15/2015
		
		PrintWriter writer = new PrintWriter(saveToDesktopString, "UTF-8");

		String lines="-----------------------------------";//35 chars - print twice for 70 wide line (easier than 7*10)
		

		writer.printf(fromName+"%"+(70-fromName.length())+"s", "+------+");//don't use \n here: it messes up the formatting
		writer.println();
		writer.printf(fromAdrs+"%"+(70-fromAdrs.length())+"s", "+-stamp+");//or here
		writer.println();
		writer.printf(fromCSZ+"%"+(70-fromCSZ.length())+"s", "+------+");//or here either
		writer.println();
		
		writer.println("\n\n\n\n");//5 return
	
		writer.println("\t\t\t\t\t"+custName);
		writer.println("\t\t\t\t\t"+custAdrs);
		writer.println("\t\t\t\t\t"+custCSZ+"\n");
				
		writer.println("\n\n\n\n"+lines+lines+"\n\n\n\n");//4 returns, a horizontal line and then 5 returns
		
		writer.println("Order Details:\n");
		
//		writer.println("\tDate Ordered:\t" +dateOrdered+"\t\tDate Shipped:\t"+dateShipped +"\n\n");
		writer.println("\tDate Ordered:\t" +dateOrdered+"\t\tDate Shipped:\t"+currentDate +"\n\n");
		writer.println("\tDate Ordered:\t" +dateOrdered+"\t\tDate Shipped:\t"+currentDate +"\n\n");
		writer.println("\tItems Ordered:\n\t" +orderDesc +"\n\n\n");
		writer.println("\tPaid With:\n\t" +paymentMethod+"\n");
		
		writer.printf("%70s", "Total Cost: "+price);
		
		writer.close();
	}
	
	//used code from: http://www.coderanch.com/t/410208/java/java/java-printing-printing-pdf
	
	//DO NOT TEST - LOADS PAPER BUT DOES NOTHING WITH IT
	//EDIT: prints fine in olin - Al can attest
	/**
	 * Takes in a filepath and name (in one string) and prints the target file
	 * @param filePathAndName - String; ex: "C:/Users/Joseph/Desktop/PrintOut.doc"
	 */
	protected void printPage(String filePathAndName){
		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
	    DocPrintJob printerJob = defaultPrintService.createPrintJob();
	    File pdfFile = new File(filePathAndName);
	    SimpleDoc simpleDoc = null;
	     
	    try {
	        simpleDoc = new SimpleDoc(pdfFile.toURI().toURL(), DocFlavor.URL.AUTOSENSE, null);
	    } catch (MalformedURLException ex) {
	        ex.printStackTrace();
	    }
	    try {
	        printerJob.print(simpleDoc, null);
	    } catch (PrintException ex) {
	        ex.printStackTrace();
	    }
	}
}