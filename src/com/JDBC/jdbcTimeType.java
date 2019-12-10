package com.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 * 时间类型
 * java.util.Date(父类)
 * 		java.sql.Date 表示年月日     对应mysql的date
 * 		java.sql.time 表示时分秒     对应mysql的time
 * 		java.sql.Timestamp 表示年月日时分秒    对应mysql的timestamp
 * @author zee
 *
 */
public class jdbcTimeType {

	public static void main(String[] args) throws Exception {
		testTimeType();
	}
	//【1】测试日期类的区别
	public static void testTimeType()  throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/zee", "root", "root");
		String sql = "INSERT INTO `zee`.`sys_user`(`id`, `name`, `password`, `regtime`) VALUES (?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		// [2.1]通过占位符防止SQL注入且提高效率
		ps.setInt(1, 1);
		ps.setString(2, "阿七");
		ps.setString(3, "5201314");
		// ps.setDate(4, new Date(System.currentTimeMillis()));
		// 可以统一采用setObject的方式传参
//		ps.setObject(4, new Date(System.currentTimeMillis()));//这样传入数据库中没有时分秒
		ps.setObject(4, new Timestamp(System.currentTimeMillis())); //可以使用Calendar.DateFormat
		// ps.execute();
		ps.executeUpdate();
	}
}
