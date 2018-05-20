package com.revature.java;

public class Reimbursement {
	private String employeeID;
	private String reimbursementID;
	private double amount;
	public Reimbursement(String employeeID,  String reimbursementID, double amount) {
		super();
		this.setEmployeeID(employeeID);
		this.setReimbursementID(reimbursementID);
		this.setAmount(amount);
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getReimbursementID() {
		return reimbursementID;
	}
	public void setReimbursementID(String reimbursementID) {
		this.reimbursementID = reimbursementID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
