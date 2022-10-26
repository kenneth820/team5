package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.CommentVo;
import com.rkrua.dto.ProductVo;
import com.rkrua.util.DBManager;

public class CommentDao {
	private CommentDao() {
	}
	
	private static CommentDao instance = new CommentDao();
	
	public static CommentDao getInstance() {
		return instance;
	}
	
	public int addreply(String userid, String reply,  String replier) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql_insert = "insert into commentinprofile(userid,reply,replier) values(?, ?, ?)";

		try {
			
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_insert);


			pstmt.setString(1, userid);
			pstmt.setString(2, reply); 
			pstmt.setString(3, replier);
			
			
			result = pstmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	public List<CommentVo> selectCommentByUserid(String userid) {
		String sql = "select * from commentinprofile where userid=?";		
		Connection conn = null;
		PreparedStatement pstmt = null; // ���� ???��??
		ResultSet rs = null;
		CommentVo cVo = null;
		List<CommentVo> list = new ArrayList<CommentVo>();		// List ???���� �?�泥�? ����

		try {
			conn = DBManager.getConnection();
			// (3��?��) Statement �?�泥�? ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			// (4��?��) SQl?�� ��?���? �?�? �?�??�泥��?�� => executeUpdate : ��??��(insert/update/delete)
			rs = pstmt.executeQuery(); // ???��?? ����
			while (rs.next()) {
				cVo = new CommentVo();
				cVo.setUserid(rs.getString("userid"));
				cVo.setReplier(rs.getString("replier"));
				cVo.setReply(rs.getString("reply"));
				cVo.setWritedate(rs.getTimestamp("writedate"));
				System.out.println(cVo);
				list.add(cVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
}
