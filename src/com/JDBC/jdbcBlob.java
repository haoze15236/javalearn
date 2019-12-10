package com.JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * mysql中的blob字段类型  1G=1024M=1024*1024kb=1204*1024*1024 byte = 2^30字节
 * 		tinyblob 	255(2^8-1)字节
 * 		blob		65536(2^16-1)字节
 * 		mediumblob	16777215(2^24-1)字节
 * 		longblobS	4294967295(2^32-1)4G个字节
 * @author zee
 *
 */
public class jdbcBlob {
	public static void main(String[] args) throws Exception {
//		insertClob();
		queryClob();
	}
	
	public static void insertClob() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/zee", "root", "root");
		String sql = "INSERT INTO `zee`.`sys_user`(`name`,`headimg`) VALUES (?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "阿七");
		//[1]通过文件流输入
		ps.setBlob(2, new FileInputStream(new File("src/timg.jpg")));
		ps.executeUpdate();
	}
	
	public static void queryClob() throws Exception{
		Connection conn = JDBCUtil.getMysqlConn();
		String sql = "SELECT * FROM sys_user;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs   =ps.executeQuery();
		while(rs.next()){
			Blob c = rs.getBlob("headimg");
			if(c==null){
				continue;
			}
			InputStream r = c.getBinaryStream();
			int temp = 0;
			while((temp = r.read())!=-1){
				System.out.print((char)temp);
			}
		}
	}
}
