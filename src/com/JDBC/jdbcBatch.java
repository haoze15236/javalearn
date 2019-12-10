package com.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 批处理
 * @author zee
 *
 */

public class jdbcBatch {

	public static void main(String[] args) {
		addBatch();
	}
	// 【1】批量处理sql 本机测试Statement的效率比PreparedStatement的效率高
	public static void addBatch() {

		String sql = "INSERT INTO `zee`.`sys_user`(`name`, `password`, `regtime`) VALUES (?,?,?);";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/zee", "root", "root");
				PreparedStatement ps = conn.prepareStatement(sql);
				Statement st = conn.createStatement();) {
			conn.setAutoCommit(false);
			long start = System.currentTimeMillis();
			for (int i = 0; i < 20000; i++) {
				ps.setObject(1, "阿七" + i);
				ps.setString(2, "5201314");
				ps.setObject(3, new Date(System.currentTimeMillis()));
				ps.addBatch();
				// st.addBatch("INSERT INTO `zee`.`sys_user`(`name`, `password`, `regtime`) VALUES ('zee','5201314','"
				// + new Date(System.currentTimeMillis()) + "');");
			}
			int[] result = ps.executeBatch();
			// int[] result = st.executeBatch();
			conn.commit();
			long end = System.currentTimeMillis();
			// ps.clearBatch();
			conn.setAutoCommit(true);
			System.out.println("插入" + result.length + "条数据耗时：" + (end - start));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
