/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

package edu.augustana.comorant.launchers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	 * @param price - String; ex: "12.34"
	 * @param paymentMethod - String; ex: "Credit Card"
	 */
	public static void createInvoice(String fromName, String fromAdrsLine1, String fromAdrsLine2, String fromCSZ, String custName, 
		String custAdrsLine1, String custAdrsLine2, String custCSZ, String dateOrdered, String orderDesc, String shippingCost, String price, String paymentMethod){
		
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
		if(!(fromAdrsLine2.equals(""))){//is there a second address line?
			writer.printf(fromAdrsLine2+"%"+(70-fromAdrsLine2.length())+"s", "+------+");//or here
			writer.println();
		}
		writer.printf(fromCSZ+"%"+(70-fromCSZ.length())+"s", "+------+");//or here either
		writer.println();
		
		writer.println("\n\n\n\n");//5 returns
	
		
		writer.println("\t\t\t\t"+custName);
		writer.println("\t\t\t\t"+custAdrsLine1);
		if(!(custAdrsLine2.equals(""))){//is there a second address line?
			writer.println("\t\t\t\t"+custAdrsLine2);
		}
		writer.println("\t\t\t\t"+custCSZ+"\n");
				
		writer.println("\n\n\n\n"+lines+lines+"\n\n\n\n");//4 returns, a horizontal line and then 5 returns
		
		writer.println("Order Details:\n");
		
		writer.println("\tDate Ordered:\t" +dateOrdered+"\t\tDate Shipped:\t"+currentDate +"\n\n");
		
		
		writer.print("\tItems Ordered:");
		writer.println();
		int stringSize = orderDesc.length();
		if(stringSize>55){
			for(int i=0; i<stringSize; i+=55){
				if (orderDesc.charAt(i)==' '){
					i+=1;
				}
				writer.print("                      ");//22 spaces - looks nice, works better than \t
				if (i+55 < stringSize){
					writer.print(orderDesc.substring(i,(i+55)));
					if(!(orderDesc.charAt(i+55)==' ' || orderDesc.charAt(i+54)==' ')){
						writer.println("-");
					}else{
						writer.println();// \n breaks it
					}
				}else{
					writer.print(orderDesc.substring(i)+"\n");
				}
			}
			writer.println("\n\n\n");
		}else{
			writer.print("                      ");
			writer.println(orderDesc +"\n\n\n");
		}
		
		
		
		writer.println("\tPaid With:\n    " +paymentMethod+"\n");
		
		writer.printf("%70s", "Shipping Cost: $"+shippingCost);
		writer.println();
		writer.printf("%70s", "Total Cost: $"+price);
		
		writer.close();
	}
	
	//used code from: http://www.coderanch.com/t/410208/java/java/java-printing-printing-pdf
	
	/** Prints the file "PrintInvoice.doc" saved in the working directory and then deletes it */
	public static void printPage(){
		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();//gets default printer so it knows where to send it to
	    DocPrintJob printerJob = defaultPrintService.createPrintJob();
	    File pdfFile = new File((""+System.getProperty("user.dir")+"/PrintOut.doc"));//user.dir gets the directory this workspace is in
	    SimpleDoc simpleDoc = null;
	     
	    try {
	        simpleDoc = new SimpleDoc(pdfFile.toURI().toURL(), DocFlavor.URL.AUTOSENSE, null);
	    } catch (MalformedURLException ex) {
	        ex.printStackTrace();
	    } try {
	        printerJob.print(simpleDoc, null);
	    } catch (PrintException ex) {
	        ex.printStackTrace();
	    }
	    
	    //deletes the file
	    Path path = Paths.get(""+System.getProperty("user.dir")+"/PrintOut.doc");
		try {
			Files.delete(path);
		} catch (NoSuchFileException e) {
			e.printStackTrace();
		} catch (DirectoryNotEmptyException e) {
			e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	/** Takes in a whole state/territory name string and returns the shipping abbreviation in a string */
	public static String stateFormatter(String state){
		//super special cases
		//(these have spaces but don't format like the others)
		if (state.equals("District of Columbia")){
			return "DC";
		}else if (state.equals("Newfoundland and Labrador")){
			return "NL";
			
		//two words abbreviated - first letters each word
		}else if(state.contains(" ")){
			return state.substring(0, 1)+""+state.substring(state.indexOf(" ")+1,state.indexOf(" ")+2);
			
		//first and last letter cases
		}else if ((state.equals("Connecticut"))||(state.equals("Georgia"))||
				(state.equals("Hawaii"))||(state.equals("Iowa"))||
				(state.equals("Kansas"))||(state.equals("Kentucky"))||
				(state.equals("Louisiana"))||(state.equals("Maine"))||
				(state.equals("Maryland"))||(state.equals("Pennsylvania"))||
				(state.equals("Vermont"))||(state.equals("Virginia"))||
				(state.equals("Quebec"))){
			return (state.substring(0, 1)+""+state.substring(state.length()-1)).toUpperCase();

		//3rd letter is part of abbreviation
		}else if ((state.equals("Minnesota"))||(state.equals("Mississippi"))||
				(state.equals("Nevada"))||(state.equals("Tennessee"))||
				(state.equals("Texas"))||(state.equals("Alberta"))){
			return (state.substring(0,1)+""+(state.substring(2,3))).toUpperCase();
			
		//4th letter is part of abbreviation
		}else if ((state.equals("Arizona"))||(state.equals("Montana"))||
				(state.equals("Saskatchewan"))){
			return (state.substring(0,1)+""+(state.substring(3,4))).toUpperCase();
			
		//5th letter is part of abbreviation
		}else if ((state.equals("Alaska"))||(state.equals("Missouri"))){
			return (state.substring(0,1)+""+(state.substring(4,5))).toUpperCase();
			
		//canadian provinces special cases
		}else if (state.equals("Manitoba")){//7th letter (not making special case)
			return "MB";
		}else if (state.equals("Yukon")){
			return "YT";//YT = Yukon Territory
			
		//everything that just uses first 2 letters
		}else{
			return state.substring(0,2).toUpperCase();
		}
	}
}
