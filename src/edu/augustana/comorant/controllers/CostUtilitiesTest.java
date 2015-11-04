package edu.augustana.comorant.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

public class CostUtilitiesTest {

	@Test
	public void testCostUtilities() {
		double evaluatedCost = CostUtilities.getCost("10*5+6*1");
		double costIncludingTax = CostUtilities.getCostIncludingTax(evaluatedCost, 0.05);
		String totalCost = "" + costIncludingTax;
		assertEquals(totalCost, "58.8");

	}
	@Test
	public void testCostUtilitiesNegative() {
		double evaluatedCost = CostUtilities.getCost("10*5+6*-1");
		double costIncludingTax = CostUtilities.getCostIncludingTax(evaluatedCost, 0.00);
		String totalCost = "" + costIncludingTax;
		assertEquals(totalCost, "44.0");

	}
	
	@Test
	public void testCostUtilitiesTax() {
		double evaluatedCost = CostUtilities.getCost("10*5+6*1");
		double costIncludingTax = CostUtilities.getCostIncludingTax(evaluatedCost, 0.50);
		double tax = costIncludingTax - evaluatedCost;
		String strTax = "" + tax;
		assertEquals(strTax, "28.0");

	}
	

}
