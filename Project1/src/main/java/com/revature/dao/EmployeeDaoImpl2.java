package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.java.IncorrectPasswordException;
import com.revature.tables.Employee;
import com.revature.util.ConnectionTest;

public class EmployeeDaoImpl2 implements EmployeeDao{
	
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
	public Employee getEmployeeById(int empId) {
		Employee emp = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String password = rs.getString("EMPLOYEE_PASSWORD");
				int managerID = rs.getInt("MANAGER_ID");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				emp = new Employee(empId, password, managerID, firstName, lastName);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return emp;
	}


	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
}
