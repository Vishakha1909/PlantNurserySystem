package com.amdocs.plant.dao;

import java.sql.*;

public class DBConnection {
	static Connection con;
	Statement stmt;
	ResultSet r;
	public DBConnection()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //registration 
			con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","scott","tiger"); //connection
			
			}	catch(Exception e){e.printStackTrace();}
	}
	public static Connection getCon() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //registration 
			con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","scott","tiger"); //connection
		}
		catch(Exception e) {
			
		}
		return con;
	}
}
