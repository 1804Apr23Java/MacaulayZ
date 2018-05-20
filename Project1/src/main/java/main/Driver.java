package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ConnectionTest;

public class Driver {

	public static void main(String[] args) {
		try {
			Connection con = ConnectionTest.getConnectionFromFile();
			System.out.println(con.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
