package com.revature.tables;

public class Reimbursements {
	public Reimbursements(int reimbursementId, float amount, int goesTo) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.goesTo = goesTo;
	}

	public Reimbursements() {
		super();
	}

	private int reimbursementId;
	private float amount;
	private int goesTo;
	
	public int getId() {
		return reimbursementId;
	}
	public void setId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getGoesTo() {
		return goesTo;
	}
	public void setGoesTo(int goesTo) {
		this.goesTo = goesTo;
	}
	
	@Override
	public String toString() {
		return "Reimbursements [reimbursementId=" + reimbursementId + ", amount=" + amount + ", goesTo=" + goesTo + "]";
	}
	
}
