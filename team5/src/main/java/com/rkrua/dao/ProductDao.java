package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.ProductVo;
import com.rkrua.util.DBManager;

public class ProductDao {

	// *** 싱글톤
	// 1. 생성자 private: 객체를 외부에서 만들지 못하도록
	// 2. 객체 생성 private static : 자신이 객체를 생성
	// 3. 객체 제공 기능 getInstance() : 자신의 객체(단지 1개)를 사용할 수 있음
	private ProductDao() {

	}

	private static ProductDao instance = new ProductDao();

	public static ProductDao getInstance() {
		return instance;
	}

	// 상품 등록
	// 입력값 : 전체 상품 정보
	public int insertProduct(ProductVo pVo) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql_insert = "insert into product(name,price,pictureurl,description,reg_date) values(?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_insert);

//			pstmt.setInt(1, pVo.getCode());
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice()); // 정수형
			pstmt.setString(3, pVo.getPictureurl());
			pstmt.setString(4, pVo.getDescription()); // 문자형
			pstmt.setDate(5, pVo.getReg_date()); // 날짜형

			result = pstmt.executeUpdate(); // 荑쇰━ �닔�뻾
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 전체 상품 조회
	public List<ProductVo> selectAllProducts() {
		String sql = "select * from product order by code desc";

//		ProductVo[] listArr = new ProductVo[100];	//  상품 100개 저장 가능한 배열 선언
//		int countList = 0;
		List<ProductVo> list = new ArrayList<ProductVo>(); // 리스트 컬렉션 객체 생성

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 동적 쿼리

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				ProductVo pVo = new ProductVo();
				// 디비로부터 회원정보 획득
		
				pVo.setCode(rs.getInt("code")); // 컬럼명 code인 정보를 가져옴
				pVo.setName(rs.getString("name")); // DB에서 가져온 객체를 pVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
				/* System.out.println(pVo); */
				list.add(pVo); // list 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 단일 상품 조회
	public ProductVo selectProductByCode(String code) {
		String sql = "select * from product where code=?";		
		Connection conn = null;
		PreparedStatement pstmt = null; // 동적 쿼리
		ResultSet rs = null;
		ProductVo pVo = null;

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				pVo = new ProductVo();
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pVo;
	}
	
	// 상품 수정
	public int updateProduct(ProductVo pVo)	{
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql_update = "update product set name=?, price=?, pictureurl=?, description=?, reg_date=? where code=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice()); // 정수형
			pstmt.setString(3, pVo.getPictureurl());
			pstmt.setString(4, pVo.getDescription()); // 문자형
			pstmt.setDate(5, pVo.getReg_date()); // 날짜형
			pstmt.setInt(6, pVo.getCode());

			result = pstmt.executeUpdate(); // 荑쇰━ �닔�뻾
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 상품 삭제
	public void deleteProduct(String code) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql_delete = "delete from product where code=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, code);

			result = pstmt.executeUpdate(); // 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 상품 검색
	// 입력값 : column: 검색대상(분야), keyword: 검색어
	// 반환값: 검색 결과 리슽으
	public List<ProductVo> searchProduct(String keyword) {
//		# 안되는 예시 : select * from product where 'code' like '%사과%' order by code desc;
//		=> String sql = "select * from product where ? like ? order by code desc";
//		String sql= "select * from product where "+column+" like ? order by code desc";
		String sql = "select * from product where name like ? order by code desc";
		
		List<ProductVo> list = new ArrayList<ProductVo>();		// List 컬렉션 객체 생성
		
		Connection conn = null;
		PreparedStatement pstmt = null; // 동적 쿼리
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				ProductVo pVo = new ProductVo();
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
				list.add(pVo);		// 리스트 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<ProductVo> getListProducts(int pageNum, int amount) {
		String sql = "select *"
				+ "from (select rownum rn,"
				+ "    a.*"
				+ "    from(select *"
				+ "        from product"
				+ "        order by code)a)"
				+ "where ? < rn and rn <= ?";

		List<ProductVo> list = new ArrayList<ProductVo>(); // 리스트 컬렉션 객체 생성

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 동적 쿼리

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum-1)*amount);
			pstmt.setInt(2, pageNum * amount);

			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				ProductVo pVo = new ProductVo();
				// 디비로부터 회원정보 획득
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name")); // DB에서 가져온 객체를 pVo객체에 저장
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
				/* System.out.println(pVo); */
				list.add(pVo); // list 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
	
	
	public int getTotal() {
		int result = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 동적 쿼리
		
		String sql = "select count(*) as total from product";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
}