package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rkrua.dto.VisitVo;
import com.rkrua.util.DBManager;

public class VisitDao {
	private static VisitDao instance = new VisitDao();	
	// �깮�꽦�옄
	private VisitDao(){		
	}	
	// 硫붿냼�뱶
	public static VisitDao getInstance() {
		return instance;		
	}	
	
	// 해당하는 사람의 쇼룸으로 이동
	public VisitVo moveShowroomByCode(String userid) {
		String sql = "select t.userid, "
				+ "    m.selfcomment, "
				+ "    m.pictureurl, "
				+ "    m.showroompicture, "
				+ "    m.name "
				+ "from trend t "
				+ "join member m "
				+ "on t.userid = ? "
				+ "and m.userid = t.userid ";		
		Connection conn = null;
		PreparedStatement pstmt = null; // 동적 쿼리
		ResultSet rs = null;
		VisitVo vVo = null;

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				vVo = new VisitVo();
				vVo.setUserid(rs.getString("userid"));
				vVo.setName(rs.getString("name"));
				vVo.setSelfcomment(rs.getString("selfcomment"));
				vVo.setPictureurl(rs.getString("pictureurl"));
				vVo.setShowpicture(rs.getString("showroompicture"));
				System.out.println(vVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vVo;

	}
	
}
