package com.carconnect.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
	static Connection connStudent;

	public static Connection createConnection() {
		try {
			ResourceBundle resMySQL = ResourceBundle.getBundle("mysql");

			String url = resMySQL.getString("url");
			String username = resMySQL.getString("username");
			String password = resMySQL.getString("password");
			String driver = resMySQL.getString("driver");

			Class.forName(driver);

			connStudent = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		return connStudent;
	}

	public static void closeConnection(){
		try{connStudent.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}