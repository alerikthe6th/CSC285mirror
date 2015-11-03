package edu.augustana.comorant.launchers;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrintInvoiceStateFormatterTest {

	@Test
	public void testStatesandProvinces() {
	
		assertEquals(PrintInvoice.stateFormatter("Alabama") + " - " + PrintInvoice.stateFormatter("Alaska") +" - "+ PrintInvoice.stateFormatter("Arizona") +" - "+ PrintInvoice.stateFormatter("Arkansas"),("AL - AK - AZ - AR"));
		assertEquals(PrintInvoice.stateFormatter("California") + " - " + PrintInvoice.stateFormatter("Colorado") +" - "+ PrintInvoice.stateFormatter("Connecticut") +" - "+ PrintInvoice.stateFormatter("Delaware"),("CA - CO - CT - DE"));
		assertEquals(PrintInvoice.stateFormatter("Florida") + " - " + PrintInvoice.stateFormatter("Georgia") +" - "+ PrintInvoice.stateFormatter("Hawaii") +" - "+ PrintInvoice.stateFormatter("Idaho"),("FL - GA - HI - ID"));
		assertEquals(PrintInvoice.stateFormatter("Illinois") + " - " + PrintInvoice.stateFormatter("Indiana") +" - "+ PrintInvoice.stateFormatter("Iowa") +" - "+ PrintInvoice.stateFormatter("Kansas"),("IL - IN - IA - KS"));
		
		
		assertEquals(PrintInvoice.stateFormatter("Kentucky") + " - " + PrintInvoice.stateFormatter("Louisiana") +" - "+ PrintInvoice.stateFormatter("Maine") +" - "+ PrintInvoice.stateFormatter("Maryland"),("KY - LA - ME - MD"));
		assertEquals(PrintInvoice.stateFormatter("Massachusetts") + " - " + PrintInvoice.stateFormatter("Michigan") +" - "+ PrintInvoice.stateFormatter("Minnesota") +" - "+ PrintInvoice.stateFormatter("Mississippi"),("MA - MI - MN - MS"));
		assertEquals(PrintInvoice.stateFormatter("Missouri") + " - " + PrintInvoice.stateFormatter("Montana") +" - "+ PrintInvoice.stateFormatter("Nebraska") +" - "+ PrintInvoice.stateFormatter("Nevada"),("MO - MT - NE - NV"));
		assertEquals(PrintInvoice.stateFormatter("New Hampshire") + " - " + PrintInvoice.stateFormatter("New Jersey") +" - "+ PrintInvoice.stateFormatter("New Mexico") +" - "+ PrintInvoice.stateFormatter("New York"),("NH - NJ - NM - NY"));
		
		
		assertEquals(PrintInvoice.stateFormatter("North Carolina") + " - " + PrintInvoice.stateFormatter("North Dakota") +" - "+ PrintInvoice.stateFormatter("Ohio") +" - "+ PrintInvoice.stateFormatter("Oklahoma"),("NC - ND - OH - OK"));
		assertEquals(PrintInvoice.stateFormatter("Oregon") + " - " + PrintInvoice.stateFormatter("Pennsylvania") +" - "+ PrintInvoice.stateFormatter("Rhode Island") +" - "+ PrintInvoice.stateFormatter("South Carolina"),("OR - PA - RI - SC"));
		assertEquals(PrintInvoice.stateFormatter("South Dakota") + " - " + PrintInvoice.stateFormatter("Tennessee") +" - "+ PrintInvoice.stateFormatter("Texas") +" - "+ PrintInvoice.stateFormatter("Utah"),("SD - TN - TX - UT"));
		assertEquals(PrintInvoice.stateFormatter("Vermont") + " - " + PrintInvoice.stateFormatter("Virginia") +" - "+ PrintInvoice.stateFormatter("Washington") +" - "+ PrintInvoice.stateFormatter("West Virginia"),("VT - VA - WA - WV"));
		
		
		assertEquals(PrintInvoice.stateFormatter("Wisconsin") + " - " + PrintInvoice.stateFormatter("Wyoming") +" - "+ PrintInvoice.stateFormatter("District of Columbia") +" - "+ PrintInvoice.stateFormatter("Alberta"),("WI - WY - DC - AB"));
		assertEquals(PrintInvoice.stateFormatter("British Columbia") + " - " + PrintInvoice.stateFormatter("Manitoba") +" - "+ PrintInvoice.stateFormatter("New Brunswick") +" - "+ PrintInvoice.stateFormatter("Newfoundland and Labrador"),("BC - MB - NB - NL"));
		assertEquals(PrintInvoice.stateFormatter("Nova Scotia") + " - " + PrintInvoice.stateFormatter("Northwest Territories") +" - "+ PrintInvoice.stateFormatter("Nunavut") +" - "+ PrintInvoice.stateFormatter("Ontario"),("NS - NT - NU - ON"));
		assertEquals(PrintInvoice.stateFormatter("Prince Edward Island") + " - " + PrintInvoice.stateFormatter("Quebec") +" - "+ PrintInvoice.stateFormatter("Saskatchewan") +" - "+ PrintInvoice.stateFormatter("Yukon"),("PE - QC - SK - YT"));

	}
	 
}
