package com.revature.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.util.ConnectionTest;


public class Employee {
	private static String filename = "dbconnection.properties";
	private static String employeeID;
	private static String password;
	private static String reimbursementID;
	
	public Employee(String employeeID, String password) {
		super();
		Employee.employeeID = employeeID;
		this.setPassword(password);
	}
	
	public String getID() {
		return employeeID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Employee.password = password;
	}

	/**public static List<Reimbursement> getReimbursements(Employee a) {
		List<Reimbursement> reimburseList = new ArrayList<>();
		try (Connection con = ConnectionTest.getConnectionFromFile(filename)) {
			String sql = "SELECT * FROM REIMBURSEMENTS WHERE EMPLOYEE_ID = " + a.getID();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String employeeID = rs.getString("EMPLOYEE_ID");
				String imburseID = rs.getString("REIMBURSEMENT_ID");
				double amount = rs.getDouble("AMOUNT");
				reimburseList.add(new Reimbursement(employeeID, imburseID, amount));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reimburseList;
	}*/
	
	/**public static void userLogin() throws IncorrectPasswordException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your user ID.");
		String employeeID = sc.next();
		System.out.println("Please enter your password.");
		String password = sc.next();
		Employee a = new Employee(employeeID, password);
		try (Connection con = ConnectionTest.getConnectionFromFile(filename)) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ? AND EMPLOYEE_PASSWORD = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,employeeID);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				throw new IncorrectPasswordException("Incorrect username or password. Please try again.");
			} else {
				viewEmployeeAccount(a);
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
	}*/	
	
	/**public static void createEmployeeAccount() throws SQLException, IOException {	
		PreparedStatement pstmt = null;
		try (Connection con = ConnectionTest.getConnectionFromFile(filename)) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter a password.");
			String password = sc.nextLine();
			String sql = "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL,?)"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.executeQuery();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
				employeeID = generatedKeys.getString(1);
			}
			System.out.println("Account created. Your username is ");
			Employee a = new Employee(employeeID,password);
			viewEmployeeAccount(a);
			sc.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**public static void viewEmployeeAccount(Employee a) throws SQLException, IOException {
		System.out.println("Welcome to your dashboard, Employee " + a.getID() + "!");
		System.out.println("");
		List<Reimbursement> reimbursements = getReimbursements(a);
		System.out.println("Your current accounts are: "); 
		System.out.println("");
		for (int x = 0; x < reimbursements.size(); x++) {
			System.out.println(x+1 + ". Reimbursement ID: " + reimbursements.get(x).getReimbursementID() + ", Amount: " + reimbursements.get(x).getAmount());
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
	}*/
	
	/**public static void deposit(Employee a) throws SQLException, IOException {
		PreparedStatement pstmt = null;
		Reimbursement re = null;
		List<Reimbursement> reimbursements = getReimbursements(a);
		System.out.println("Your current accounts are: "); 
		for (int x = 0; x < reimbursements.size(); x++) {
			System.out.println(x+1 + ". Bank Account ID: " + reimbursements.get(x).getReimbursementID() + ", Balance: " + reimbursements.get(x).getAmount());
		}
		System.out.println("Which account would you like to deposit into?");
		Scanner sc = new Scanner(System.in);
		String accountIn = sc.next();
		switch(accountIn) {
			case "1":
				re = reimbursements.get(0);
				reimbursementID = re.getReimbursementID();
				break;
			case "2":
				re = reimbursements.get(1);
				reimbursementID = re.getReimbursementID();
				break;
			case "3":
				re = reimbursements.get(2);
				reimbursementID =	re.getReimbursementID();
				break;
			case "4":
				re = reimbursements.get(3);
				reimbursementID = re.getReimbursementID();
				break;
			case "5":
				re = reimbursements.get(4);
				reimbursementID =	re.getReimbursementID();
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				deposit(a);	
		}				
		System.out.println("Enter the amount you would like to deposit.");
		try (Connection con = ConnectionTest.getConnectionFromFile(filename)) {
			int depositAmt = sc.nextInt();
			String sql2 = "UPDATE REIMBURSEMENTS SET AMOUNT = ? WHERE EMPLOYEE_ID = ? AND REIMBURSEMENT_ID = ?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setDouble(1, re.getAmount() + depositAmt);
			pstmt.setString(2, employeeID);
			pstmt.setString(3, reimbursementID);
			pstmt.executeQuery();
			System.out.println("Deposit successful. Your new balance is " + (re.getAmount() + depositAmt) + ".");
			System.out.println("*********");
			viewEmployeeAccount(a);	
		con.close();
		} catch (Exception e) {
			deposit(a);
		} 
		sc.close();
	}*/
	
	/**public static void withdraw(Employee a) throws OverdraftException, SQLException, IOException {
		PreparedStatement pstmt = null;
		Reimbursement re = null;
		List<Reimbursement> reimbursements = getReimbursements(a);
		System.out.println("Your current accounts are: "); 
		for (int x = 0; x < reimbursements.size(); x++) {
			System.out.println(x+1 + ". Bank Account ID: " + reimbursements.get(x).getReimbursementID() + ", Balance: " + reimbursements.get(x).getAmount());
		}
		System.out.println("Which account would you like to withdraw from?");
		Scanner sc = new Scanner(System.in);
		String accountIn = sc.next();
		switch(accountIn) {
			case "1":
				re = reimbursements.get(0);
				reimbursementID = re.getReimbursementID();
				break;
			case "2":
				re = reimbursements.get(1);
				reimbursementID = re.getReimbursementID();
				break;
			case "3":
				re = reimbursements.get(2);
				reimbursementID =	re.getReimbursementID();
				break;
			case "4":
				re = reimbursements.get(3);
				reimbursementID = re.getReimbursementID();
				break;
			case "5":
				re = reimbursements.get(4);
				reimbursementID =	re.getReimbursementID();
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				deposit(a);	
		}				
		try (Connection con = ConnectionTest.getConnectionFromFile(filename)) {
			System.out.println("Enter the amount you would like to withdraw.");
			int withdrawAmt = sc.nextInt();
			if (withdrawAmt > re.getAmount()) {
				throw new OverdraftException("Insufficient funds. Please try again.");
			} else {
				String sql2 = "UPDATE REIMBURSEMENTS SET AMOUNT = ? WHERE EMPLOYEE_ID = ? AND REIMBURSEMENT_ID = ?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setDouble(1, re.getAmount()- withdrawAmt);
				pstmt.setString(2, employeeID);
				pstmt.setString(3, reimbursementID);
				pstmt.executeQuery();
				System.out.println("Withdrawal successful. Your new balance is " + (re.getAmount() - withdrawAmt) + ".");
				System.out.println("*********");
				viewEmployeeAccount(a);
			}			
		} catch (OverdraftException e) {
			withdraw(a);
		} 
		sc.close();
	}*/
	
	/**public static void deleteAccount(Employee a) throws SQLException, IOException {
		PreparedStatement pstmt = null;
		List<Reimbursement> reimbursements = getReimbursements(a);
		System.out.println("Your current reimbursements are: "); 
		for (int x = 0; x < reimbursements.size(); x++) {
			System.out.println(x+1 + ". Reimbursement ID: " + reimbursements.get(x).getReimbursementID() + ", Amount: " + reimbursements.get(x).getAmount());
		}
		System.out.println("Which account would you like to delete?");
		Scanner sc = new Scanner(System.in);
		String accountIn = sc.next();
		switch(accountIn) {
			case "1":
				reimbursementID = reimbursements.get(0).getReimbursementID();
				break;
			case "2":
				reimbursementID = reimbursements.get(1).getReimbursementID();
				break;
			case "3":
				reimbursementID =	reimbursements.get(2).getReimbursementID();
				break;
			case "4":
				reimbursementID = reimbursements.get(3).getReimbursementID();
				break;
			case "5":
				reimbursementID =	reimbursements.get(4).getReimbursementID();
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				deposit(a);	
		}				
		try (Connection con = ConnectionTest.getConnectionFromFile(filename)) {
			System.out.println("Are you sure you want to delete your account? Y/N");
			String input = sc.next().toUpperCase();
			while (!input.equals("Y") && !input.equals("N")) {
				System.out.println("Invalid input. Please enter Y or N.");
				input = sc.next().toUpperCase();
			} if (input.equals("Y")) {
				String sql = "DELETE FROM REIMBURSEMENTS WHERE EMPLOYEE_ID = ? AND REIMBURSEMENT_ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, a.getID());
				pstmt.setString(2, reimbursementID);
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
	}*/
	
	public static void createBankAccount() {
		/* generate BANK_ACCOUNT_ID?
		 * insert into ACCOUNTS
		 * VALUES(BANK_ACCOUNT_ID,EMPLOYEE_ID,0);
		 * 
		 */
	}
	
	/**public static void logout() {
		System.out.println("Thank you for using JDBC Bank.");
		System.exit(0);
	}*/
}
