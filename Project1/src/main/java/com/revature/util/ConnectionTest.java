package com.revature.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
	
	private static String filename = "dbconnection.properties";
	
	public static Connection getConnectionFromFile() throws IOException, SQLException {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream(filename));
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		return DriverManager.getConnection(url,username,password);		
	}
}