package com.revature.main;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.dao.CaveDao;
import com.revature.dao.CaveDaoImpl;
import com.revature.domain.Cave;
import com.revature.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		/*try {
			Connection con = ConnectionUtil.getConnectionFromFile("connection.properties");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}*/
		CaveDao cd = new CaveDaoImpl();
		
		List<Cave> caves = cd.getCaves();
		
		for (Cave c : caves) {
			System.out.println(c.toString());
		}
		
		Cave c = cd.getCaveById(2);
		System.out.println(c.toString());
	}

}
