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

}
