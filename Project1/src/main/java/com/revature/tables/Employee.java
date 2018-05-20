package com.revature.tables;

public class Employee {
	public Employee() {
		super();
	}
	public Employee(int empId, String password, int manId, String firstName, String lastName) {
		super();
		this.empId = empId;
		this.password = password;
		this.manId = manId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	private int empId;
	private String password;
	private int manId;
	private String firstName;
	private String lastName;
	public int getId() {
		return empId;
	}
	public void setId(int empId) {
		this.empId = empId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getManId() {
		return manId;
	}
	public void setManId(int manId) {
		this.manId = manId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", password=" + password + ", manId=" + manId + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
}
