package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.tables.Reimbursements;
import com.revature.util.ConnectionTest;

public class ReimbursementDaoImpl implements ReimbursementDao{
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
}
