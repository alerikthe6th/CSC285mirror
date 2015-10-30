package edu.augustana.comorant.controllers;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CostUtilities {
	
	
	public static double getCost(String priceExpression) {
		Expression expr = new ExpressionBuilder(priceExpression).build();
		double resultPrice = expr.evaluate();
		return resultPrice;
	}
	public static double getCostIncludingTax(double evaluatedPrice, double taxRate) {
		
		double resultPrice = evaluatedPrice;
		double tax = resultPrice * taxRate;
		double priceWithTax = resultPrice + tax;
		return priceWithTax;
	}
	public static double getTaxCost(Double evaluatedPrice, double taxRate) {
		return evaluatedPrice * taxRate;
	}

}
