package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.CartVo;
import com.rkrua.dto.ProductVo;
import com.rkrua.util.DBManager;

public class CartDao {
	private CartDao() {
	}
	
	private static CartDao instance = new CartDao();
	
	public static CartDao getInstance() {
		return instance;
	}
	
	// 카트에 상품 추가
	public int addCart(String userid, int code) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql_insert = "insert into cart(userid,code) values(?, ?)";

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
	
	// 카트에 상품 삭제
	public void deleteCart(String cartid) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql_delete = "delete from cart where cartid=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, cartid);

			result = pstmt.executeUpdate(); // 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 카트에 상품 모두 삭제
	public void deleteAllcart(String userid) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql_delete = "delete from cart where userid=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, userid);

			result = pstmt.executeUpdate(); // 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 카트 상품 보여주기
	public List<CartVo> selectAllCart(String userid) {
		String sql = "select  c.userid"
				+ "    ,   m.name as username"
				+ "    ,   p.pictureurl"
				+ "    ,   p.code"
				+ "    ,   p.price"
				+ "    ,   p.name  as productname"
				+ "    ,   c.cartid"
				+ "    from cart c join product p"
				+ "                    on c.code = p.code "
				+ "                join member m"
				+ "                    on c.userid = m.userid"
				+ "                    and m.userid = ?";

		List<CartVo> list = new ArrayList<CartVo>(); // 리스트 컬렉션 객체 생성
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
				CartVo cVo = new CartVo();
				// 디비로부터 회원정보 획득
		
				cVo.setCartid(rs.getInt("cartid")); // 컬럼명 code인 정보를 가져옴
				cVo.setCode(rs.getInt("code"));
				cVo.setUserid(rs.getString("username"));
				cVo.setName(rs.getString("productname")); // DB에서 가져온 객체를 pVo객체에 저장
				cVo.setPrice(rs.getInt("price"));
				cVo.setPictureurl(rs.getString("pictureurl"));
				/* System.out.println(pVo); */
				list.add(cVo); // list 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int checkCart(String userid, int code) {
		int result = -1;
		String sql = "select userid, code from cart where userid = ? and code = ?";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;	// 동적 쿼리
		
		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, code);
			
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery();			// 쿼리 수행
			// res.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			if(rs.next()) {
				// 디비로부터 카트정보 획득
				result = 1;			// 디비에 userid 있음
			} else {
				result = -1;		// 디비에 userid 없음
			}
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt, rs);
		}	
		return result;
	}
	public int totalPrice(String userid) {
		int t_price = 0;
		String sql = "select sum(price) as price "
				+ "from (select p.price"
				+ "    from cart c "
				+ "    join product p"
				+ "        on c.code = p.code "
				+ "    join member m"
				+ "        on c.userid = m.userid"
				+ "        and m.userid = ?) price";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;	// 동적 쿼리
		
		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery();			// 쿼리 수행
			// res.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			if(rs.next()) {
				// 디비로부터 카트정보 획득
				t_price = rs.getInt("price");
			}
			
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt, rs);
		}	
		return t_price;
	}
	public int resultPrice(int userpoint, int totalpoint) {
		int result = -1;
		int price = userpoint - totalpoint;
		System.out.println(price);
		if(price > 0 || price == 0) {
			result = price;
		} 
		return result;
	}
	
	public int buyProduct(String userid, int change) {
		int result = -1;
		String sql = "update member set point=? where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, change);
			pstmt.setString(2, userid);			
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
