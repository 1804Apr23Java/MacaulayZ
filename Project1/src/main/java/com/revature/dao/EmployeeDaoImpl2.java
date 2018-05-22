package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.java.IncorrectPasswordException;
import com.revature.java.OverdraftException;
import com.revature.tables.Employee;
import com.revature.tables.Reimbursements;
import com.revature.util.ConnectionTest;

public class EmployeeDaoImpl2 implements EmployeeDao{
	private int employeeId;
	private String password;
	
	@Override
	public boolean employeeLogin(int employeeId, String password) throws IncorrectPasswordException {
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ? AND EMPLOYEE_PASSWORD = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,employeeId);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Employee viewEmployeeAccount(Employee a) throws SQLException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursements> getReimbursements(Employee a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeById(int employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deposit(Employee a) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(Employee a) throws OverdraftException, SQLException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(Employee a) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
}
