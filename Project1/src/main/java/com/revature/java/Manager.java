package com.revature.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.util.ConnectionTest;


public class Manager {
	private static String filename = "dbconnection.properties";
	private static String employeeID;
	private static String password;
	private static String managerID;
	private static String managerPassword;
	
	public Manager(String managerID, String managerPassword) {
		super();
		Manager.managerID = managerID;
		this.setPassword(managerPassword);
	}
	
	public String getID() {
		return managerID;
	}

	public String getManagerPassword() {
		return managerPassword;
	}
	
	public void setPassword(String managerPassword) {
		Manager.managerPassword = managerPassword;
	}
	
	public static void createManager() throws SQLException, IOException {
		PreparedStatement pstmt = null;
		try (Connection con = ConnectionTest.getConnectionFromFile(filename)) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter a password.");
			String managerPassword = sc.nextLine();
			String sql = "INSERT INTO MANAGER VALUES(MANAGER_SEQ.NEXTVAL,?)"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, managerPassword);
			pstmt.executeQuery();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
				employeeID = generatedKeys.getString(1);
			}
			System.out.println("Account created. Your username is ");
			Employee a = new Employee(employeeID,password);
			viewEmployee(a);
			sc.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
