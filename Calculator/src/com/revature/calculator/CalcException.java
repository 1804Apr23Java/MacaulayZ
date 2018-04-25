package com.revature.calculator;

public class CalcException extends Exception {
	
		private static final long serialVersionUID = 3921922643968841510L;

		public CalcException() {
			
			System.out.println("Hey that's not a valid input.");
			
		}
		
		public CalcException(String msg) {
			super(msg);
		
	}
}

