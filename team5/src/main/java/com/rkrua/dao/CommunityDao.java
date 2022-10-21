package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.ShowroomVo;
import com.rkrua.dto.TrendVo;
import com.rkrua.util.DBManager;

public class CommunityDao {
	// �떛湲��넠
	private CommunityDao() {		
	}
	private static CommunityDao instance = new CommunityDao();
	
	public static CommunityDao getInstance() {
		return instance;
	}
	
	// Create (insert) - 媛쒖씤猷� �벑濡�
	// �엯�젰媛� : �쟾泥� 媛쒖씤猷� �젙蹂�
	// 諛섑솚媛� : 荑쇰━ �닔�뻾 寃곌낵
	public int inserttrend(TrendVo tVo){
		String sql = "insert into trand values(trend_seq.nextval, ?, ?)";
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
	
	// �눥猷� 寃��깋
	public List<ShowroomVo> getShowroomList() {
		// 理쒓렐 �벑濡앺븳 �긽�뭹�쓣 癒쇱� 異쒕젰�븯湲�
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
	
	public List<TrendVo> gettrendList() {
		return gettrendList("num", "", 1);		
	}
	public List<TrendVo> gettrendList(int page) {
		return gettrendList("num", "", page);		
	}
	public List<TrendVo> gettrendList(String column, String keyword, int page){
//		System.out.println("keyword: "+ keyword + "column: "+ column);
		List<TrendVo> list = new ArrayList<TrendVo>();

		String sql = "SELECT * FROM ("
				+ "SELECT ROWNUM N, b.* "
				+ "FROM (SELECT * FROM trand where "+column+" like ? order by num desc) b"
				+ ") "
				+ "WHERE   N BETWEEN ? AND ?";
//		1, 11, 21, 31 => �벑李⑥닔�뿴 =>  an = 1+(page-1)*10
//		�벑李⑥닔�뿴�쓽 n�뿉 ���븳 �떇�� 泥レ㎏�빆�씠 A, 怨듭감媛� B�씤 寃쎌슦 =>  A + B(n-1)
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
				TrendVo tVo = new TrendVo();

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

	// 寃뚯떆湲� 媛쒖닔 �쉷�뱷
	public int getShowroomCount() {
		// 吏묎퀎�븯�뒗 媛믩쭔 �븘�슂
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

	// 寃뚯떆湲� 媛쒖닔 �쉷�뱷
	public int gettrendCount() {
		return gettrendCount("num", "");
	}
	// �듅�젙 而щ읆�쓽 �궎�썙�뱶瑜� �넻�빐 寃뚯떆臾� �닔 議고쉶
	public int gettrendCount(String column, String keyword) {
		System.out.println("keyword: "+ keyword);
		System.out.println("column: "+ column);
		// 吏묎퀎�븯�뒗 媛믩쭔 �븘�슂
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
