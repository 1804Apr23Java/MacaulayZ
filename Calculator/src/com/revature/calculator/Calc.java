package com.revature.calculator;
import java.lang.Number;

public class Calc {
		
	private void add(Number num1, Number num2) {
		System.out.println(num1.doubleValue() + num2.doubleValue());
	}
	
	private void subtract(Number num1, Number num2) {
		System.out.println(num1.doubleValue() - num2.doubleValue());
	}
	
	private void multiply(Number num1, Number num2) {
		System.out.println(num1.doubleValue() * num2.doubleValue());
	}
	
	private void divide(Number num1, Number num2) {
		System.out.println(num1.doubleValue() / num2.doubleValue());
	}


	public void setInput(Number int1, Number int2, String op) {
			
			switch(op) {
		
		case "+": add(int1, int2);
					break;		
		case "-": subtract(int1, int2);
					break;
		case "*": multiply(int1, int2);
					break;
		case "/": divide(int1, int2);
					break;
			}
	}
}
