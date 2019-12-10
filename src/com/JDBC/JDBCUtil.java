package com.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	static Properties pros = null;// 可以帮助读取和处理资源文件的信息

	static {// 加载JDBCUtil类时执行一次
		pros = new Properties();

		try {
			// 默认从根目录src开始找
			pros.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("com/JDBC/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getMysqlConn() {
		Connection conn = null;
		try {
			Class.forName(pros.getProperty("Driver"));
			conn = DriverManager.getConnection(pros.getProperty("Url"),
					pros.getProperty("User"), pros.getProperty("pwd"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(AutoCloseable... autoCloseables) {
		for (AutoCloseable temp : autoCloseables) {
			try {
				temp.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
