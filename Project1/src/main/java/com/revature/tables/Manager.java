package com.revature.tables;

public class Manager {
	public Manager(int manId, String password) {
		super();
		this.manId = manId;
		this.password = password;
	}

	public Manager() {
		super();
	}

	private int manId;
	private String password;
	
	public int getId() {
		return manId;
	}
	public void setId(int manId) {
		this.manId = manId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Manager [manId=" + manId + ", password=" + password + "]";
	}
	
}
