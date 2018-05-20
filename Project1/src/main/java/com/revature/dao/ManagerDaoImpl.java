package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.java.IncorrectPasswordException;
import com.revature.tables.Employee;
import com.revature.tables.Manager;
import com.revature.util.ConnectionTest;

public class ManagerDaoImpl implements ManagerDao{
	public void createManagerAccount(int manId, String password) throws SQLException, IOException {	
		PreparedStatement pstmt = null;
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter a password.");
			password = sc.nextLine();
			String sql = "INSERT INTO MANAGER VALUES(MANAGER_SEQ.NEXTVAL,?)"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.executeQuery();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
				manId = generatedKeys.getInt(1);
			}
			System.out.println("Account created. Your username is ");
			Manager a = new Manager(manId, password);
			viewManagerAccount(a);
			sc.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void userLogin() throws IncorrectPasswordException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your user ID.");
		int empId = sc.nextInt();
		System.out.println("Please enter your password.");
		String password = sc.next();
		Employee a = new Employee(empId, password, 0, null, null);
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "SELECT * FROM MANAGER WHERE MANAGER_ID = ? AND MANAGER_PASSWORD = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,empId);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				throw new IncorrectPasswordException("Incorrect username or password. Please try again.");
			} else {
				viewManagerAccount(a);
			}
			con.close();
			sc.close();
		} catch (IncorrectPasswordException e) {
			userLogin();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Manager getManagerById(int id) {
		Manager man = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "SELECT * FROM MANAGER WHERE MANAGER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int manId = rs.getInt("MANAGER_ID");
				String password = rs.getString("MANAGER_PASSWORD");
				man = new Manager(manId, password);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return man;
	}
	
	public void logout() {
		System.out.println("Thank you for using JDBC Bank.");
		System.exit(0);
	}
}
