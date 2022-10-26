package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.ProductVo;
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
	
	// Create (insert) - 등록
	public int inserttrend(TrendVo tVo){
		String sql = "insert into trend(num,userid,title,pictureurl,text) values(trend_seq.nextval, ?, ?, ?, ?)";
		int result = -1;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {			
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tVo.getUserid());
			pstmt.setString(2, tVo.getTitle());
			pstmt.setString(3, tVo.getPictureUrl());
			/* System.out.println("pstmt 텍스트:" + tVo.getText()); */
			pstmt.setString(4, tVo.getText());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 쇼룸 등록
	public int insertShowroom(ShowroomVo sVo) {
		String sql = "insert into showroom values(showroom_seq.nextval, ?, ?)";
		int result = -1;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {			
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sVo.getName());
			pstmt.setString(2, sVo.getPictureUrl());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 쇼룸 삭제
	public void deleteShowroom(int code) {
		String sql = "delete from showroom where code =?";
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);

			result = pstmt.executeUpdate(); // 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 트렌드 삭제
	public void deleteTrend(int num) {
		String sql = "delete from Trend where num=?";
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			result = pstmt.executeUpdate(); // 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	// 트렌드 수정
	public int updateTrend(TrendVo tVo)	{
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql_update = "update Trend set title=?, pictureurl=?, text=? where num=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, tVo.getTitle());
			pstmt.setString(2, tVo.getPictureUrl()); // 정수형
			pstmt.setString(3, tVo.getText());
			pstmt.setInt(4, tVo.getNum());

			result = pstmt.executeUpdate(); // 荑쇰━ �닔�뻾
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	//특정 게시글 가져오기
	public TrendVo selectTrendByNum(int num) {
		String sql = "select * from trend where num=?";		
		Connection conn = null;
		PreparedStatement pstmt = null; // 동적 쿼리
		ResultSet rs = null;
		TrendVo tVo = null;

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				tVo = new TrendVo();
				tVo.setNum(rs.getInt("num"));
				tVo.setTitle(rs.getString("title"));
				tVo.setPictureUrl(rs.getString("pictureurl"));
				tVo.setText(rs.getString("text"));
				tVo.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return tVo;
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
				/* System.out.println(sVo); */
				
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
		return gettrendList("title", "", 1);		
	}
	public List<TrendVo> gettrendList(int page) {
		return gettrendList("title", "", page);		
	}
	public List<TrendVo> gettrendList(String column, String keyword, int page){
//		System.out.println("keyword: "+ keyword + "column: "+ column);
		List<TrendVo> list = new ArrayList<TrendVo>();

		String sql = "SELECT * FROM ("
				+ "SELECT ROWNUM N, b.* "
				+ "FROM (SELECT * FROM trend where "+column+" like ? order by num desc) b"
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
			pstmt.setInt(2, 1+(page-1)*9);
			pstmt.setInt(3, page*9);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TrendVo tVo = new TrendVo();

				tVo.setNum(rs.getInt("num"));
				tVo.setUserid(rs.getString("userid"));
				tVo.setTitle(rs.getString("title"));
				tVo.setText(rs.getString("text"));
				tVo.setWritedate(rs.getTimestamp("writedate"));
				tVo.setPictureUrl(rs.getString("pictureUrl"));
				/* System.out.println(tVo); */
				
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
				+ "    FROM (SELECT * FROM trend WHERE "+column+" like ? order by num desc) b"
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
