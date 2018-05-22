package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;
import com.revature.dao.*;
import com.revature.java.IncorrectPasswordException;

import org.junit.jupiter.api.Test;

class Project1Test {
	EmployeeDaoImpl2 empDao = new EmployeeDaoImpl2();
	@Test
	void employeeLoginTest() {
		try {
			assertEquals(true, empDao.employeeLogin(1, "password"));
		} catch (IncorrectPasswordException e) {
			e.printStackTrace();
		}
	}

}
