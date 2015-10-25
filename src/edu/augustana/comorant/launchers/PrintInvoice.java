/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

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

//printer class will take as parameters the following: from address(from preferences), to address(from customer), 
//date ordered, date shipped, order desc, price, payment method (from order)

public class PrintInvoice {
	/*
	public static void main(String[] args){
		createInvoice("Firsty Lastname", "123 Main Street", null, "New York, NY 12345",
				"John Cena", "123 JCena St.", null, "Cena WY, 54321", "Oct 13 2015", "12 Pots, 6 Plates, and one giant hug", 
				12.56, "Credit Card");
		//printPage();
	}
	*/
	/**
	 * @param fromName - String; ex: "Firsty Lastname Incorporated"
	 * @param fromAdrsLine1 - String; ex: "P.O. Box 772"
	 * @param fromAdrsLine2 - String; ex: "123 Main Street" - leave null or empty if nothing
	 * @param fromCSZ - String; ex: "New York, NY 12345"
	 * @param custName - String; ex: "John Q. Customer"
	 * @param custAdrsLine1 - String; ex: "C/o John Cena College" 
	 * @param custAdrsLine2 - String; ex: "P.O. Box 772"- leave null or empty if nothing
	 * @param custCSZ - String; ex: "New York, NY 12345"
	 * @param dateOrdered - String; ex: "October 12, 2015"
	 * @param orderDesc - String; ex: "12 Pots, 6 Plates, and a big hug"
	 * @param price - double; ex: 12.34
	 * @param paymentMethod - String; ex: "Credit Card"
	 */
	public static void createInvoice(String fromName, String fromAdrsLine1, String fromAdrsLine2, String fromCSZ, String custName, 
		String custAdrsLine1, String custAdrsLine2, String custCSZ, String dateOrdered, String orderDesc, String price, String paymentMethod){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//2015-10-25
		Date date = new Date();
		String currentDate = (dateFormat.format(date)).toString();
		PrintWriter writer = null;
		
		//catches for file not being found and for unsupported characters in URL (which won't happen now anyway)
		try {
			try {
				writer = new PrintWriter((""+System.getProperty("user.dir")+"/PrintOut.doc"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String lines="-----------------------------------";//35 chars - print twice for 70 wide line (easier than 7*10)
		
		writer.printf(fromName+"%"+(70-fromName.length())+"s", "+------+");//don't use \n here: it messes up the formatting
		writer.println();
		writer.printf(fromAdrsLine1+"%"+(70-fromAdrsLine1.length())+"s", "+-stamp+");//or here
		writer.println();
		if("" != fromAdrsLine2 && null != fromAdrsLine2){//is there a second address line?
			writer.printf(fromAdrsLine2+"%"+(70-fromAdrsLine2.length())+"s", "+------+");//or here
			writer.println();
		}
		writer.printf(fromCSZ+"%"+(70-fromCSZ.length())+"s", "+------+");//or here either
		writer.println();
		
		writer.println("\n\n\n\n");//5 returns
	
		writer.println("\t\t\t\t\t"+custName);
		writer.println("\t\t\t\t\t"+custAdrsLine1);
		if("" != custAdrsLine2 && null != custAdrsLine2){//is there a second address line?
			writer.println("\t\t\t\t\t"+custAdrsLine2);
		}
		writer.println("\t\t\t\t\t"+custCSZ+"\n");
				
		writer.println("\n\n\n\n"+lines+lines+"\n\n\n\n");//4 returns, a horizontal line and then 5 returns
		
		writer.println("Order Details:\n");
		
		writer.println("\tDate Ordered:\t" +dateOrdered+"\t\tDate Shipped:\t"+currentDate +"\n\n");
		writer.println("\tItems Ordered:\n\t" +orderDesc +"\n\n\n");
		writer.println("\tPaid With:\n\t" +paymentMethod+"\n");
		
		writer.printf("%70s", "Total Cost: "+price);
		
		writer.close();
	}
	
	//used code from: http://www.coderanch.com/t/410208/java/java/java-printing-printing-pdf
	
	/**
	 * Prints the file "PrintInvoice" saved in the working directory
	 */
	public static void printPage(){
		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();//gets default printer so it knows where to send it to
	    DocPrintJob printerJob = defaultPrintService.createPrintJob();
	    File pdfFile = new File((""+System.getProperty("user.dir")+"/PrintOut.doc"));//user.dir gets the directory this workspace is in
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