package com.revature.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.revature.tables.Employee;
import com.revature.java.IncorrectPasswordException;
import com.revature.java.OverdraftException;
import com.revature.tables.Reimbursements;

public interface EmployeeDao {
	public void createEmployeeAccount(int employeeID, String password) throws SQLException, IOException;
	public void userLogin() throws IncorrectPasswordException;
	public Employee viewEmployeeAccount(Employee a) throws SQLException, IOException;
	public List<Reimbursements> getReimbursements(Employee a);
	public Employee getEmployeeById(int employeeID);
	public void deposit(Employee a) throws SQLException, IOException;
	public void withdraw(Employee a) throws OverdraftException, SQLException, IOException;
	public void deleteAccount(Employee a) throws SQLException, IOException;
	public void logout();
}
