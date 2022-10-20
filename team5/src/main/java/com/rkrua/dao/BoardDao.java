package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.BoardVo;
import com.rkrua.dto.ProductVo;
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
	
	// 게시물 목록 조회
	public List<BoardVo> selectAllBoards() {
		String sql = "select * from board order by num desc";
		List<BoardVo> list = new ArrayList<BoardVo>(); // 리스트 컬렉션 객체 생성
		
		return list;
	}
	
	// 
	
	// 게시글 검색
	public List<BoardVo> getBoardList(){
		return getBoardList("", 1);
	}
	// 페이지 별 리스트 표시
	public List<BoardVo> getBoardList(int page){
		return getBoardList("",page);
	}
	// 검색 기능과 페이징을 구현
	public List<BoardVo> getBoardList(String keyword, int page){
		String sql = "select * from ("
				+ "select rownum n, b.* "
				+ "from (select * from board where title like ? order by num desc) b "
				+ ")"
				+ "where n between ? and ?";
		
//		등차수열의 n에 대한 식은 첫째항 A공차가 B인 경우 => A + B(n-1)
//		1 + (page-1)* 10
		List<BoardVo> list = new ArrayList<BoardVo>(); // 리스트 컬렉션 객체 생성

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 동적 쿼리

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, 1+(page-1)*2);
			pstmt.setInt(3, page * 2);
			
			
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				BoardVo bVo = new BoardVo();
				// 디비로부터 회원정보 획득
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name")); // DB에서 가져온 객체를 pVo객체에 저장
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				/* System.out.println(pVo); */
				list.add(bVo); // list 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 게시물 수 조회
	public int getBoardCount() {
		return getBoardCount("");
	}
	// 특정 컬럼의 키워드를 통해 게시물 수 조회
	public int getBoardCount(String keyword) {
		int count=0;
		String sql = "select count(num) as count from ( "
				+ "				select rownum n, b.* "
				+ "				from (select * from board where title like ? order by num desc) b"
				+ "				) ";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 동적 쿼리

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	// 게시물 번호로 특정 게시물 다음 게시물 데이터 조회
	public BoardVo getNextBoard(int num) {
		BoardVo bVo = null;
		return bVo;
	}
	
//	public 
	
	
	
}
