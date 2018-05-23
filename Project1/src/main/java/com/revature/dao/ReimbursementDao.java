package com.revature.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.revature.tables.Employee;
import com.revature.tables.Reimbursements;

public interface ReimbursementDao {
	public Reimbursements getReimbursementById(int id);
	public List<Reimbursements> getReimbursements(Employee a);
	public boolean submitReimbursementRequest(int reimbursementId, float amount, int goesTo) throws SQLException, IOException;
}
