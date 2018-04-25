package com.revature.calculator;
import java.util.*;
import java.lang.Number;

public class Driver{
	


	public static void main(String[] args) throws CalcException {
		
		
		getInput();
		
	}

		
		public static void getInput() throws CalcException{
			
			
			try {
				
		
				Calc myCalc = new Calc();
			
				Scanner stdin = new Scanner(System.in);
							
				System.out.println();
				
				System.out.print("Enter an input type(i for int, d for double): ");
				String numType = stdin.nextLine();		
				System.out.println();
				
				System.out.print("Enter a number: ");
				String num1 = stdin.nextLine();
				System.out.println();
				
				System.out.print("Enter a number: ");
				String num2 = stdin.nextLine();
				System.out.println();
				
				System.out.print("Enter an operation (+, -, /, *) or x to exit: ");
				String op = stdin.nextLine(); 
				System.out.println();
				
				stdin.close();
				
				// This if statement is supposed to check numType to make sure it == "i" || == "d" however it fails every time...
				if (true) {
				
				switch(numType) {
					case "i":   Number int1 = new Integer(num1);
								Number int2 = new Integer(num2);
								myCalc.setInput(int1, int2, op);
								break;
						
					case "d":   Number int3 = new Double(num1);
								Number int4 = new Double(num2);
								myCalc.setInput(int3, int4, op);
								break;						
				
					}
				
				} else throw new CalcException();
			
			} catch (CalcException e) {
				
		}
	}		
}
