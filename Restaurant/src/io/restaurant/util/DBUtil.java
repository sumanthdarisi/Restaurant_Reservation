package io.restaurant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String url ="jdbc:mysql://localhost:3306/";
	private static final String user ="root";
	private static final String password ="root";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("SQL connected");
		} catch (ClassNotFoundException e) {
			System.out.println("Connection failed....");
			e.printStackTrace();
		}
	}
	public static Connection Connect() {
			Connection con =null;
			try {
				con = DriverManager.getConnection(url, user, password);
				System.out.println("User credentials matched");
			} catch (SQLException e) {
				System.out.println("User credentials NOT matched");
				e.printStackTrace();
				System.out.println(e);
			}
			return con;
		}
}
