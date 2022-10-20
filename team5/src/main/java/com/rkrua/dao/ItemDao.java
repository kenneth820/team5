package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.CartVo;
import com.rkrua.dto.ItemVo;
import com.rkrua.util.DBManager;

public class ItemDao {
	private ItemDao() {
	}
	
	private static ItemDao instance = new ItemDao();
	
	public static ItemDao getInstance() {
		return instance;
	}
	
	// 아이템 구매
	public int insertItem(String userid, int code ) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql_insert = "insert into item(userid,code) values(?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql_insert);
//			pstmt.setInt(1, pVo.getCode());
			pstmt.setString(1, userid);
			pstmt.setInt(2, code); // 정수형
//			System.out.println("유저아이디: "+userid);
//			System.out.println("상품아이디: "+code);
			
			result = pstmt.executeUpdate(); // 荑쇰━ �닔�뻾
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 아이템 리스트 얻기
	public List<ItemVo> getItemList(String userid) {
		String sql = "select  p.code "
				+ "    ,   p.pictureurl "
				+ "    ,   p.coordinate "
				+ "    ,   p.reg_date "
				+ "    from product p "
				+ "    left outer join item i "
				+ "        on i.code = p.code "
				+ "        and i.userid = ? "
				+ "        where i.code is null";

		List<ItemVo> list = new ArrayList<ItemVo>(); // 리스트 컬렉션 객체 생성
//		System.out.println(userid);
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 동적 쿼리

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				ItemVo iVo = new ItemVo();
				// 디비로부터 회원정보 획득
		
				iVo.setCode(rs.getInt("code"));
				iVo.setUserid(rs.getString("username"));
				iVo.setCoordinate(rs.getString("coordinate")); // DB에서 가져온 객체를 pVo객체에 저장
				iVo.setPictureurl(rs.getString("pictureurl"));
				/* System.out.println(pVo); */
				list.add(iVo); // list 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;

	}
	
	// 아이템 장착
	public int equipItem(String userid) {
		int result = -1;
		String sql = "update item set equip=1 where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);			
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			result = pstmt.executeUpdate();			// 쿼리 수행
			
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 아이템 해제
	public int unequipItem(String userid) {
		int result = -1;
		String sql = "update item set equip=0 where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);			
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			result = pstmt.executeUpdate();			// 쿼리 수행
			
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
		}
		return result;

	}
	

}
