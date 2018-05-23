package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.tables.Employee;
import com.revature.tables.Reimbursements;
import com.revature.util.ConnectionTest;

public class ReimbursementDaoImpl implements ReimbursementDao{
	@Override
	public Reimbursements getReimbursementById(int id) {
		Reimbursements reimburse = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENT_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
				float amount = rs.getFloat("AMOUNT");
				int goesTo = rs.getInt("EMPLOYEE_ID");
				reimburse = new Reimbursements(reimbursementId, amount, goesTo);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return reimburse;
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
	public boolean submitReimbursementRequest(int reimbursementId, float amount, int goesTo) throws SQLException, IOException {
		try (Connection con = ConnectionTest.getConnectionFromFile()) {
			String sql = "INSERT INTO REIMBURSEMENTS(REIMBURSEMENT_ID, AMOUNT, GOES_TO) VALUES(?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reimbursementId);
			pstmt.setFloat(2, amount);
			pstmt.setInt(3, goesTo);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
}
