package com.revature.dao;

import com.revature.tables.Employee;
import com.revature.java.IncorrectPasswordException;

public interface EmployeeDao {
	public boolean employeeLogin(int employeeId, String password) throws IncorrectPasswordException;
	public Employee getEmployeeById(int empId);
}
