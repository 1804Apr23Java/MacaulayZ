package com.revature.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.java.IncorrectPasswordException;
import com.revature.tables.Manager;

public interface ManagerDao {
	public void createManagerAccount(int manId, String password) throws SQLException, IOException;
	public void userLogin() throws IncorrectPasswordException;
	public Manager getManagerById(int id);
	public void logout();
}
