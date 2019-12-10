package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 【1】加载驱动类
 * 		Class.forName("oracle.jdbc.driver.OracleDriver")
 * 		Class.forName("com.mysql.jdbc.Driver");
 * 【2】获取连接
 * 		DriverManager.getConnection("jdbc:oracle:thin:@host:port:database","user","password");
 * 		DriverManager.getConnection("jdbc:mysql://host:port/database","user","password");
 * @author zee
 *
 */
public class JdbcConnection {

	public static void main(String[] args) {
		try {
			//【1】加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			//【2】获取连接
			long start = System.currentTimeMillis();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zee","root","root");
			long end = System.currentTimeMillis();
			System.out.println(conn);  //com.mysql.jdbc.JDBC4Connection@5ce65a89  实现类是JDBC4Connection
			System.out.println(conn.getClass().getClassLoader());  //sun.misc.Launcher$AppClassLoader@73d16e93 使用AppClassLoader加载
			System.out.println(end-start);//建立连接比较耗时,一般通过连接池管理对象
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
