package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.rkrua.dto.BoardVo;
import com.rkrua.util.DBManager;

public class BoardDao {
	// 싱글톤
	private static BoardDao instance = new BoardDao();
	private BoardDao() {
	}
	public static BoardDao getInstance() {
		return instance;
	}
	
	// 게시물 등록
//	입력값: 전체 게시물 정보
//	반환값: 쿼리 수행 결과
	public int insertBoard(BoardVo bVo) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql = "insert into board(name,email,pass,title,content) values(?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail()); // 정수형
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle()); // 문자형
			pstmt.setString(5, bVo.getContent()); // 날짜형

			result = pstmt.executeUpdate(); // 쿼리수행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
//	public List<BoardVo> selectAllBoards() {
//		String sql = "select * from board order by num desc";
//		
////		return list;
		
	
}
