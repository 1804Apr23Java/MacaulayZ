package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.tables.Employee;
import com.revature.java.IncorrectPasswordException;
import com.revature.java.OverdraftException;
import com.revature.tables.Reimbursements;
import com.revature.util.ConnectionTest;

public class EmployeeDaoImpl implements EmployeeDao{
	private String filename = "dbconnection.properties";
	private int employeeID;
	private String password;
	private int reimbursementID;
	private float amount;
	
	public void createEmployeeAccount(int empId, String password) throws SQLException, IOException {	
		PreparedStatement pstmt = null;
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			//Scanner sc = new Scanner(System.in);
			//System.out.println("Please enter a password.");
			//password = sc.nextLine();
			String sql = "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL,?)"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.executeQuery();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
				employeeID = generatedKeys.getInt(1);
			}
			System.out.println("Account created. Your username is ");
			Employee a = new Employee(empId, password, 0, null, null);
			viewEmployeeAccount(a);
			//sc.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void userLogin() throws IncorrectPasswordException {
		//Scanner sc = new Scanner(System.in);
		//System.out.println("Please enter your user ID.");
		//int empId = sc.nextInt();
		//System.out.println("Please enter your password.");
		//String password = sc.next();
		Employee a = new Employee(empId, password, 0, null, null);
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ? AND EMPLOYEE_PASSWORD = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,empId);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				throw new IncorrectPasswordException("Incorrect username or password. Please try again.");
			} else {
				viewEmployeeAccount(a);
			}
			con.close();
			//sc.close();
		} catch (IncorrectPasswordException e) {
			userLogin();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Employee viewEmployeeAccount(Employee a) throws SQLException, IOException {
		System.out.println("Welcome to your dashboard, Employee " + a.getId() + "!");
		System.out.println("");
		List<Reimbursements> reimbursements = getReimbursements(a);
		System.out.println("Your current reimbursements are: "); 
		System.out.println("");
		for (int x = 0; x < reimbursements.size(); x++) {
			System.out.println(x+1 + ". Reimbursement ID: " + reimbursements.get(x).getId() + ", Amount: " + reimbursements.get(x).getAmount());
		}
		System.out.println("To make a deposit, enter 1.");
		System.out.println("To withdraw an amount, enter 2.");
		System.out.println("To delete an account, enter 3. (Note: your account balance must be 0.)");
		System.out.println("To create another account, enter 4. (Maximum 5 accounts allowed.)");
		System.out.println("To logout, enter 5.");
			
		Scanner sc = new Scanner(System.in);
		String input = sc.next();	
		switch(input) {
			case "1" :
				deposit(a);
				break;
			case "2" :
				try {
					withdraw(a);
					break;
				} catch (OverdraftException e) {
					//loop
				}
			case "3" :
				deleteAccount(a);
				break;
			case "4" :
				createBankAccount();
				break;
			case "5" :
				logout();
				break;
			default :
				System.out.println("Invalid input. Please enter either 1, 2, 3, 4, or 5.");
				viewEmployeeAccount(a);
			}
		sc.close();
	}
	
	@Override
	public List<Reimbursements> getReimbursements(Employee a) {
		List<Reimbursements> reimburseList = new ArrayList<>();
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "SELECT * FROM REIMBURSEMENTS WHERE EMPLOYEE_ID = " + a.getId();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
				float amount = rs.getFloat("AMOUNT");
				int goesTo = rs.getInt("EMPLOYEE_ID");
				reimburseList.add(new Reimbursements(reimbursementId, amount, goesTo));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reimburseList;
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		Employee emp = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String password = rs.getString("EMPLOYEE_PASSWORD");
				int managerID = rs.getInt("MANAGER_ID");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				emp = new Employee(id, password, managerID, firstName, lastName);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return emp;
	}
	
	public void deposit(Employee a) throws SQLException, IOException {
		PreparedStatement pstmt = null;
		Reimbursements re = null;
		List<Reimbursements> reimbursements = getReimbursements(a);
		System.out.println("Your current accounts are: "); 
		for (int x = 0; x < reimbursements.size(); x++) {
			System.out.println(x+1 + ". Bank Account ID: " + reimbursements.get(x).getId() + ", Balance: " + reimbursements.get(x).getAmount());
		}
		System.out.println("Which account would you like to deposit into?");
		Scanner sc = new Scanner(System.in);
		String accountIn = sc.next();
		switch(accountIn) {
			case "1":
				re = reimbursements.get(0);
				reimbursementID = re.getId();
				break;
			case "2":
				re = reimbursements.get(1);
				reimbursementID = re.getId();
				break;
			case "3":
				re = reimbursements.get(2);
				reimbursementID =	re.getId();
				break;
			case "4":
				re = reimbursements.get(3);
				reimbursementID = re.getId();
				break;
			case "5":
				re = reimbursements.get(4);
				reimbursementID =	re.getId();
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				deposit(a);	
		}				
		System.out.println("Enter the amount you would like to deposit.");
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			int depositAmt = sc.nextInt();
			String sql2 = "UPDATE REIMBURSEMENTS SET AMOUNT = ? WHERE EMPLOYEE_ID = ? AND REIMBURSEMENT_ID = ?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setFloat(1, re.getAmount() + depositAmt);
			pstmt.setInt(2, employeeID);
			pstmt.setInt(3, reimbursementID);
			pstmt.executeQuery();
			System.out.println("Deposit successful. Your new balance is " + (re.getAmount() + depositAmt) + ".");
			System.out.println("*********");
			viewEmployeeAccount(a);	
		con.close();
		} catch (Exception e) {
			deposit(a);
		} 
		sc.close();
	}
	
	public void withdraw(Employee a) throws OverdraftException, SQLException, IOException {
		PreparedStatement pstmt = null;
		Reimbursements re = null;
		List<Reimbursements> reimbursements = getReimbursements(a);
		System.out.println("Your current accounts are: "); 
		for (int x = 0; x < reimbursements.size(); x++) {
			System.out.println(x+1 + ". Bank Account ID: " + reimbursements.get(x).getId() + ", Balance: " + reimbursements.get(x).getAmount());
		}
		System.out.println("Which account would you like to withdraw from?");
		Scanner sc = new Scanner(System.in);
		String accountIn = sc.next();
		switch(accountIn) {
			case "1":
				re = reimbursements.get(0);
				reimbursementID = re.getId();
				break;
			case "2":
				re = reimbursements.get(1);
				reimbursementID = re.getId();
				break;
			case "3":
				re = reimbursements.get(2);
				reimbursementID =	re.getId();
				break;
			case "4":
				re = reimbursements.get(3);
				reimbursementID = re.getId();
				break;
			case "5":
				re = reimbursements.get(4);
				reimbursementID =	re.getId();
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				deposit(a);	
		}				
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			System.out.println("Enter the amount you would like to withdraw.");
			int withdrawAmt = sc.nextInt();
			if (withdrawAmt > re.getAmount()) {
				throw new OverdraftException("Insufficient funds. Please try again.");
			} else {
				String sql2 = "UPDATE REIMBURSEMENTS SET AMOUNT = ? WHERE EMPLOYEE_ID = ? AND REIMBURSEMENT_ID = ?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setDouble(1, re.getAmount()- withdrawAmt);
				pstmt.setInt(2, employeeID);
				pstmt.setInt(3, reimbursementID);
				pstmt.executeQuery();
				System.out.println("Withdrawal successful. Your new balance is " + (re.getAmount() - withdrawAmt) + ".");
				System.out.println("*********");
				viewEmployeeAccount(a);
			}			
		} catch (OverdraftException e) {
			withdraw(a);
		} 
		sc.close();
	}
	
	public void deleteAccount(Employee a) throws SQLException, IOException {
		PreparedStatement pstmt = null;
		List<Reimbursements> reimbursements = getReimbursements(a);
		System.out.println("Your current reimbursements are: "); 
		for (int x = 0; x < reimbursements.size(); x++) {
			System.out.println(x+1 + ". Reimbursement ID: " + reimbursements.get(x).getId() + ", Amount: " + reimbursements.get(x).getAmount());
		}
		System.out.println("Which account would you like to delete?");
		Scanner sc = new Scanner(System.in);
		String accountIn = sc.next();
		switch(accountIn) {
			case "1":
				reimbursementID = reimbursements.get(0).getId();
				break;
			case "2":
				reimbursementID = reimbursements.get(1).getId();
				break;
			case "3":
				reimbursementID =	reimbursements.get(2).getId();
				break;
			case "4":
				reimbursementID = reimbursements.get(3).getId();
				break;
			case "5":
				reimbursementID =	reimbursements.get(4).getId();
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				deposit(a);	
		}				
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			System.out.println("Are you sure you want to delete your account? Y/N");
			String input = sc.next().toUpperCase();
			while (!input.equals("Y") && !input.equals("N")) {
				System.out.println("Invalid input. Please enter Y or N.");
				input = sc.next().toUpperCase();
			} if (input.equals("Y")) {
				String sql = "DELETE FROM REIMBURSEMENTS WHERE EMPLOYEE_ID = ? AND REIMBURSEMENT_ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, a.getId());
				pstmt.setInt(2, reimbursementID);
				pstmt.executeQuery();
				System.out.println("Account deleted.");
				System.out.println("*********");
				viewEmployeeAccount(a);
			} if (input.equals("N")) {
				viewEmployeeAccount(a);
			}
			sc.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logout() {
		System.out.println("Thank you for using JDBC Bank.");
		System.exit(0);
	}
}
