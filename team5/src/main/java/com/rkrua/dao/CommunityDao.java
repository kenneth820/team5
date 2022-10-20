package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.ShowroomVo;
import com.rkrua.dto.TrandVo;
import com.rkrua.util.DBManager;

public class CommunityDao {
	// 싱글톤
	private CommunityDao() {		
	}
	private static CommunityDao instance = new CommunityDao();
	
	public static CommunityDao getInstance() {
		return instance;
	}
	
	// Create (insert) - 개인룸 등록
	// 입력값 : 전체 개인룸 정보
	// 반환값 : 쿼리 수행 결과
	public int insertTrand(TrandVo tVo){
		String sql = "insert into trand values(trand_seq.nextval, ?, ?)";
		int result = -1;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {			
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tVo.getName());
			pstmt.setString(2, tVo.getPictureUrl());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 쇼룸 검색
	public List<ShowroomVo> getShowroomList() {
		// 최근 등록한 상품을 먼저 출력하기
		String sql = "select * from showroom order by code desc";
		
		List<ShowroomVo> list = new ArrayList<ShowroomVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				ShowroomVo sVo = new ShowroomVo();
				
				sVo.setCode(rs.getInt("code"));
				sVo.setName(rs.getString("name"));
				sVo.setPictureUrl(rs.getString("pictureUrl"));
				System.out.println(sVo);
				
				list.add(sVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;		
	}
	
	public List<TrandVo> getTrandList() {
		return getTrandList("num", "", 1);		
	}
	public List<TrandVo> getTrandList(int page) {
		return getTrandList("num", "", page);		
	}
	public List<TrandVo> getTrandList(String column, String keyword, int page){
//		System.out.println("keyword: "+ keyword + "column: "+ column);
		List<TrandVo> list = new ArrayList<TrandVo>();

		String sql = "SELECT * FROM ("
				+ "SELECT ROWNUM N, b.* "
				+ "FROM (SELECT * FROM trand where "+column+" like ? order by num desc) b"
				+ ") "
				+ "WHERE   N BETWEEN ? AND ?";
//		1, 11, 21, 31 => 등차수열 =>  an = 1+(page-1)*10
//		등차수열의 n에 대한 식은 첫째항이 A, 공차가 B인 경우 =>  A + B(n-1)
//		10, 20, 30, 40 => page*10
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TrandVo tVo = new TrandVo();

				tVo.setNum(rs.getInt("num"));
				tVo.setName(rs.getString("name"));
				tVo.setPictureUrl(rs.getString("pictureUrl"));
				System.out.println(tVo);
				
				list.add(tVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 게시글 개수 획득
	public int getShowroomCount() {
		// 집계하는 값만 필요
		String sql = "select COUNT(code) count from showroom order by code desc";
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
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

	// 게시글 개수 획득
	public int getTrandCount() {
		return getTrandCount("num", "");
	}
	// 특정 컬럼의 키워드를 통해 게시물 수 조회
	public int getTrandCount(String column, String keyword) {
		System.out.println("keyword: "+ keyword);
		System.out.println("column: "+ column);
		// 집계하는 값만 필요
		String sql = "SELECT COUNT(num) count FROM ("
				+ "    SELECT ROWNUM N, b.* "
				+ "    FROM (SELECT * FROM trand WHERE "+column+" like ? order by num desc) b"
				+ ") ";
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			
			rs = pstmt.executeQuery();
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
}
