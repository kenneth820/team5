package com.rkrua.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	
	//DBCP ���� ����
//	<Resource auth="Container" driverClassName="oracle.jdbc.OracleDriver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/myoracle" password="1234" type="javax.sql.DataSource" url="jdbc:oracle:thin:@127.0.0.1:1521:orcl" username="ora_user"/>
	
	// DB ����
	public static Connection getConnection() {
		Connection conn = null;
		try {
//		// (1�ܰ�	) JDBC ����̹� �ε�
//		Class.forName("oracle.jdbc.driver.OracleDriver");		
//		// (2�ܰ�) �����ͺ��̽� ���� ��ü ����
//		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//		String uid = "ora_user";
//		String pass = "1234";
//		conn =  DriverManager.getConnection(url, uid, pass);	// DB ����
		
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		// etc.
		
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	// DB �ݱ�
	// DB ��ȸ�� ���
	public static void  close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			// ����� ���ҽ� ����
			if (rs != null) {				
				rs.close();
			}
			if(pstmt!=null) {				
				pstmt.close();
			}
			if (conn !=null) {				
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// DB ����/����/���� �� ���
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			// (5�ܰ�) ����� ���ҽ� ����
//			stmt.close();
			if(pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {				
				conn.close();				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
}
