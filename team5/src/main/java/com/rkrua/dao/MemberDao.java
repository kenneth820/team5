package com.rkrua.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.rkrua.dao.MemberDao;
import com.rkrua.dto.MemberVo;
import com.rkrua.dto.ProductVo;
import com.rkrua.util.DBManager;
// 데이터 베이스 접근
public class MemberDao {
	
	private static MemberDao instance = new MemberDao();	
	// �깮�꽦�옄
	private MemberDao(){		
	}	
	// 硫붿냼�뱶
	public static MemberDao getInstance() {
		return instance;		
	}	

	// 로그인(사용자 인증): select
	// 입력값: 로그인 페이지에서 입력받은 사용자아이디와 암호 
	// 반환값 : result 1: 암호 일치 0: 암호 불일치 -1: 사용자 아이디 없음
	public int checkUser(String userid, String pwd) {
		int result= -1;
		
//		Statement stmt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//		String sql = "select pwd from member where userid='"+userid+"'";
		String sql = "select pwd, admin from member where userid=?";
		try {
//			// (1단계	) JDBC 드라이버 로드
//			Class.forName("oracle.jdbc.driver.OracleDriver");		
//			// (2단계) 데이터베이스 연결 객체 생성
//			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//			String uid = "ora_user";
//			String pass = "1234";
//			conn =  DriverManager.getConnection(url, uid, pass);
			conn = DBManager.getConnection();
			
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, userid);
			// (4단계) SQl문 실행 및 결과처리 => executeQuery : 조회(selecet)
			rs = pstmt.executeQuery();
			// rs.next() ": 다음 행 (row)확인, rs.getString("
			if(rs.next()){
				//아이디 / 암호 비교 후 페이지 이동
				if(rs.getString("pwd")!= null && 
						rs.getString("pwd").equals(pwd) && rs.getInt("admin") == 1) {
					result = 2;  // 암호 일치
				} else if(rs.getString("pwd")!= null && 
						rs.getString("pwd").equals(pwd)) {
					result = 1;
				} else {
					result = 0;
				}
				
			} else {
				result = -1;
			}
		} catch(Exception e) {		
			e.printStackTrace();
		} finally  {
			DBManager.close(conn, pstmt, rs);
			//			try {
//				// (5단계) 사용한 리소스 해제
//				rs.close();
////				stmt.close();
//				pstmt.close();
//				conn.close();				
//			} catch(Exception e) {
//				e.getMessage();
//			} 
		}
	return result;
	}	
	
	// 회원 가입
//	public int insertMember(String name, String id, String pwd, String email, String phone, int admin) {
	public int insertMember(MemberVo mVo) {
		int result = -1;
		Connection conn = null;
//		Statement stmt = null;		// 정적 쿼리
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;	// 동적 쿼리
		
		
//		String sql_insert= "insert into member values('"+name+"','"+id+"','"+pwd+"','"+email+"','"+phone+"',"+admin+")";
		String sql_insert= "insert into member(name, userid, pwd, email, phone) values(?, ?, ?, ?, ?)";
		
//		System.out.println(sql_insert);
		
		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql_insert);
//			pstmt.setString(1, name);
//			pstmt.setString(2, id);
//			pstmt.setString(3, pwd);
//			pstmt.setString(4, email);
//			pstmt.setString(5, phone);				// 문자형
//			pstmt.setInt(6, admin);					// 정수형
			
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getPhone());				// 문자형
//			pstmt.setFloat(admin, float x);			// 실수형
//			pstmt.setDate(idx, Date x);				// 날짜형
//			pstmt.setTimestamp(idx, Timestamp t);	// 시간형
			
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
//			result = stmt.executeUpdate(sql_insert);
			result = pstmt.executeUpdate();			// 쿼리 수행
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
			//			try {
//				// (5단계) 사용한 리소스 해제
////				stmt.close();
//				pstmt.close();
//				conn.close();				
//			} catch(SQLException e) {
//				e.printStackTrace();
//			} 
		}
		return result;
}

	// 회원 정보 가져오기 : select
	// 입력값: 사용자id(userid)
	// 반환값: 성공여부
	public MemberVo getMember(String userid) {
		int result = -1;
		String sql = "select * from member where userid = ?";
		MemberVo mVo = null;
		
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
			if(rs.next()) {
				// 디비로부터 회원정보 획득
				mVo = new MemberVo();
				mVo.setName(rs.getString("name")); 	// 컬럼명 name인 정보를 가져옴
				mVo.setUserid(rs.getString("userid")); // 	컬럼명 name인 정보를 가져옴
				mVo.setPwd(rs.getString("pwd")); // 	컬럼명 name인 정보를 가져옴
				mVo.setEmail(rs.getString("email")); // 	컬럼명 name인 정보를 가져옴
				mVo.setPhone(rs.getString("phone")); // 	컬럼명 name인 정보를 가져옴
				mVo.setAdmin(rs.getInt("admin")); // 	컬럼명 name인 정보를 가져옴
				mVo.setPictureurl(rs.getString("pictureurl"));
				mVo.setPoint(rs.getInt("point"));
			} else {
				result = -1;		// 디비에 userid 없음
			}
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt, rs);
		}
		return mVo;
	}
	
	// 회원 정보 업데이트: update
	// 입력값: 회원 테이블 정보
	// 반환값 : 성공여부
	public int updateMember(MemberVo mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?, phone=?, admin=?, pictureurl=?, name=? where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getPictureurl());
			pstmt.setString(6, mVo.getName());
			pstmt.setString(7, mVo.getUserid());	
			
			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			result = pstmt.executeUpdate();			// 쿼리 수행
			
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
		}
		
		
		return result;
	}

	// 아이디 확인 : select
	// 입력값: 중복 체크 할 유저 아이디
	// 반환값: 체크한 ID의 DB 존재 여부
	public int confirmID(String userid) {
		int result = -1;
		String sql = "select userid from member where userid = ?";
		
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
				// 디비로부터 회원정보 획득
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
	public List<MemberVo> getMemberList() {
		return getMemberList("userid","",1);
	}
	public List<MemberVo> getMemberList(int page) {
		return getMemberList("userid","",page);
	}
	public List<MemberVo> getMemberList(String keyword, int page) {
		return getMemberList("userid",keyword,1);
	}
	public List<MemberVo> getMemberList(String column ,String keyword, int page) {
		String sql = "select * from ( "
				+ "select rownum n, b.* "
				+ "from (select * from member where "+column+" like ? order by name ) b "
				+ ")"
				+ "where n between ? and ? ";

		List<MemberVo> list = new ArrayList<MemberVo>(); // 리스트 컬렉션 객체 생성

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 동적 쿼리

		try {
			conn = DBManager.getConnection();
			// (3단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, 1+(page-1)*9);
			pstmt.setInt(3, page * 9);

			// (4단계) SQl문 실행 및 결과처리 => executeUpdate : 삽입(insert/update/delete)
			rs = pstmt.executeQuery(); // 쿼리 수행
			while (rs.next()) {
				MemberVo mVo = new MemberVo();
				// 디비로부터 회원정보 획득
				mVo.setUserid(rs.getString("userid"));
				mVo.setName(rs.getString("name")); // DB에서 가져온 객체를 pVo객체에 저장
				mVo.setPwd(rs.getString("pwd"));
				mVo.setPictureurl(rs.getString("pictureurl"));
				mVo.setEmail(rs.getString("Email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setPoint(rs.getInt("point"));
				/* System.out.println(pVo); */
				list.add(mVo); // list 객체에 데이터 추가
				/* System.out.println(mVo); */
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int getMemberCount() {
		return getMemberCount("userid","");
	}
	public int getMemberCount(String keyword) {
		return getMemberCount("userid",keyword);
	}
	public int getMemberCount(String column,String keyword) {
		int count = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 동적 쿼리
		
		String sql = "select count(*) as count from ( select rownum n, b.*  "
				+ "from (select * from member where "+ column +" like ? order by name desc) b)";

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
			DBManager.close(conn, pstmt);
		}
		return count;
	}
	
	public void deleteMember(String userid) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할 때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;

		String sql = "delete from member where userid=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			result = pstmt.executeUpdate(); // 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
}