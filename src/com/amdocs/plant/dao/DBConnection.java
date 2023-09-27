package com.amdocs.plant.dao;

import java.sql.*;

public class DBConnection {
	private Connection con;
	
	public DBConnection() {
		try {
			con = DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl", "scott", "tiger"); // connection

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}
}
