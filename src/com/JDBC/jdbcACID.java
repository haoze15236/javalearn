package com.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 事务的四大特征
 * atomicity(原子性)：一个事务内的所有操作，要么一起成功，要么一起失败
 * consistency(一致性)：一个事务内有一个失败，则全部回滚
 * isolation(隔离性):一个事务操作的数据的状态，要么是其他事务处理前的状态，要么是处理后的状态；
 * durability(持久性):持久性事务完成之后，对于系统的影响是永久性的
 * @author zee
 *
 */
public class jdbcACID {
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/zee", "root", "root");
		conn.setAutoCommit(false);
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
		
		
		sql = "INSERT INTO `zee`.`sys_user`(`id`, `name`, `password`, `regtime`) VALUES (?,?,?,?,?);";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, 2);
		ps.setString(2, "阿七");
		ps.setString(3, "5201314");
		ps.setObject(4, new Date(System.currentTimeMillis()));
		ps.executeUpdate();
		conn.commit();
	}

}
