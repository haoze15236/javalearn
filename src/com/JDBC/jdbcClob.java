package com.JDBC;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * mysql中的clob字段类型  1G=1024M=1024*1024kb=1204*1024*1024 byte = 2^30字节
 * 		tinytext 	255(2^8-1)字节
 * 		text		65536(2^16-1)字节
 * 		mediumtext	16777215(2^24-1)字节
 * 		longtext	4294967295(2^32-1)4G个字节
 * @author zee
 *
 */
public class jdbcClob {

	public static void main(String[] args) throws Exception {
//		insertClob();
		queryClob();
	}
	
	public static void insertClob() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/zee", "root", "root");
		String sql = "INSERT INTO `zee`.`sys_user`(`name`,`info`) VALUES (?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "阿七");
		//[1]通过文件流输入
//		ps.setClob(2, new FileReader(new File("src/com/IO/source.txt")));
		//[2]通过字节数组流直接输入字符串
		ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("我是郝泽女朋友".getBytes()))));
		ps.executeUpdate();
	}
	
	public static void queryClob() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/zee", "root", "root");
		String sql = "SELECT * FROM sys_user;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs   =ps.executeQuery();
		while(rs.next()){
			Clob c = rs.getClob("info");
			Reader r = c.getCharacterStream();
			int temp = 0;
			while((temp = r.read())!=-1){
				System.out.print((char)temp);
			}
		}
	}
}
