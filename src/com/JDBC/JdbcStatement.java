package com.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * statement存在SQL注入的风险
 * PreparedStatement预编译防止SQL注入且效率更高
 * @author zee
 *
 */
public class JdbcStatement {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/zee", "root", "root");
			//【1】测试PreparedStatement执行DDL语句
			//execPreparedStatement(conn);
			//【2】测试ResultSet
			queryResultSet(conn);
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//【2】测试ResultSet
	public static void queryResultSet(Connection conn){
		String sql = "SELECT * FROM sys_user;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getObject(2));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    //【1】测试PreparedStatement执行DDL语句
	public static void execPreparedStatement(Connection conn) {
		try {
			String sql = "INSERT INTO `zee`.`sys_user`(`id`, `name`, `password`, `regtime`) VALUES (?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			// [2.1]通过占位符防止SQL注入且提高效率
			ps.setInt(1, 1);
			ps.setString(2, "阿七");
			ps.setString(3, "5201314");
			// ps.setDate(4, new Date(System.currentTimeMillis()));
			// 可以统一采用setObject的方式传参
			ps.setObject(4, new Date(System.currentTimeMillis()));
			// ps.execute();
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
