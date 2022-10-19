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
// 占쎄퀣占쎈똾占쏙옙 甕곤옙占쎈똻占쏙옙 占쏙옙域뱄옙
public class MemberDao {
	
	private static MemberDao instance = new MemberDao();	
	// �뜝�럡臾멨뜝�럡�뎽�뜝�럩占쏙옙
	private MemberDao(){		
	}	
	// 嶺뚳옙�겫�슜占쎌눦�삕獄�占�
	public static MemberDao getInstance() {
		return instance;		
	}	

	// 嚥∽옙域밸챷占쏙옙(占싼딉옙�뫗占쏙옙 占쎈챷占�): select
	// 占쏙옙占싸븝옙: 嚥∽옙域밸챷占쏙옙 占쏙옙占쎈똻占쏙옙占쏙옙占� 占쏙옙占싸삼옙占쏙옙 占싼딉옙�뫗占쏙옙占쏙옙占쎈�占쏙옙占쏙옙 占쏙옙占쏙옙 
	// 獄쏉옙占쏙옙揶쏉옙 : result 1: 占쏙옙占쏙옙 占쎌눘占� 0: 占쏙옙占쏙옙 �겫占쏙옙�눘占� -1: 占싼딉옙�뫗占쏙옙 占쏙옙占쎈�占쏙옙 占쏙옙占쏙옙
	public int checkUser(String userid, String pwd) {
		int result= -1;
		
//		Statement stmt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//		String sql = "select pwd from member where userid='"+userid+"'";
		String sql = "select pwd, admin from member where userid=?";
		try {
//			// (1占썩몿占�	) JDBC 占쏙옙占쎌눘占쎈�占� 嚥∽옙占쏙옙
//			Class.forName("oracle.jdbc.driver.OracleDriver");		
//			// (2占썩몿占�) 占쎄퀣占쎈똾占쎄퀡占쏙옙�똻占쏙옙 占쎄퀗猿� 揶쏉옙筌ｏ옙 占쏙옙占쏙옙
//			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//			String uid = "ora_user";
//			String pass = "1234";
//			conn =  DriverManager.getConnection(url, uid, pass);
			conn = DBManager.getConnection();
			
			// (3占썩몿占�) Statement 揶쏉옙筌ｏ옙 占쏙옙占쏙옙
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, userid);
			// (4占썩몿占�) SQl�눧占� 占썬끋占쏙옙 獄쏉옙 野껉퀗�궢筌ｏ옙�뵳占� => executeQuery : 鈺곌퀬占쏙옙(selecet)
			rs = pstmt.executeQuery();
			// rs.next() ": 占썬끉占쏙옙 占쏙옙 (row)占쏙옙占쏙옙, rs.getString("
			if(rs.next()){
				//占쏙옙占쎈�占쏙옙 / 占쏙옙占쏙옙 �뜮占썸뤃占� 占쏙옙 占쏙옙占쎈똻占� 占쎈�占쏙옙
				if(rs.getString("pwd")!= null && 
						rs.getString("pwd").equals(pwd) && rs.getInt("admin") == 1) {
					result = 2;  // 占쏙옙占쏙옙 占쎌눘占�
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
//				// (5占썩몿占�) 占싼딉옙�뫚占쏙옙 �뵳�딉옙占쏙옙占� 占쎈똻占쏙옙
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
	
	// 占쏙옙占쏙옙 揶쏉옙占쏙옙
//	public int insertMember(String name, String id, String pwd, String email, String phone, int admin) {
	public int insertMember(MemberVo mVo) {
		int result = -1;
		Connection conn = null;
//		Statement stmt = null;		// 占쏙옙占쏙옙 �뜎�눖�봺
		// 占쏙옙占쎌눛占쏙옙 �뜎�눖�봺�눧紐꾬옙占� 占쎈�占쏙옙 揶쏉옙筌랃옙 獄쏉옙饔낉옙占쏙옙 占싼됵옙�됵옙 占썬끋占쏙옙占쎈똻占쏙옙 占쏙옙 占쏙옙, 筌띲끆占썼퉪占쏙옙占썲첎占� 筌랃옙占쏙옙占쏙옙 �뜎�눖�봺�눧占� 占쏙옙�뵳占� 占쏙옙占쏙옙
		PreparedStatement pstmt = null;	// 占쏙옙占쏙옙 �뜎�눖�봺
		
		
//		String sql_insert= "insert into member values('"+name+"','"+id+"','"+pwd+"','"+email+"','"+phone+"',"+admin+")";
		String sql_insert= "insert into member(name, userid, pwd, email, phone) values(?, ?, ?, ?, ?)";
		
//		System.out.println(sql_insert);
		
		try {
			conn = DBManager.getConnection();
			// (3占썩몿占�) Statement 揶쏉옙筌ｏ옙 占쏙옙占쏙옙
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql_insert);
//			pstmt.setString(1, name);
//			pstmt.setString(2, id);
//			pstmt.setString(3, pwd);
//			pstmt.setString(4, email);
//			pstmt.setString(5, phone);				// �눧紐꾬옙占쏙옙占�
//			pstmt.setInt(6, admin);					// 占쏙옙占쏙옙占쏙옙
			
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getPhone());				// �눧紐꾬옙占쏙옙占�
//			pstmt.setFloat(admin, float x);			// 占썬끉占쏙옙占쏙옙
//			pstmt.setDate(idx, Date x);				// 占쏙옙筌욑옙占쏙옙
//			pstmt.setTimestamp(idx, Timestamp t);	// 占쏙옙揶쏉옙占쏙옙
			
			// (4占썩몿占�) SQl�눧占� 占썬끋占쏙옙 獄쏉옙 野껉퀗�궢筌ｏ옙�뵳占� => executeUpdate : 占쎌럩占쏙옙(insert/update/delete)
//			result = stmt.executeUpdate(sql_insert);
			result = pstmt.executeUpdate();			// �뜎�눖�봺 占쏙옙占쏙옙
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
			//			try {
//				// (5占썩몿占�) 占싼딉옙�뫚占쏙옙 �뵳�딉옙占쏙옙占� 占쎈똻占쏙옙
////				stmt.close();
//				pstmt.close();
//				conn.close();				
//			} catch(SQLException e) {
//				e.printStackTrace();
//			} 
		}
		return result;
}

	// 占쏙옙占쏙옙 占쏙옙癰귨옙 揶쏉옙占쎈챷占썬끆由� : select
	// 占쏙옙占싸븝옙: 占싼딉옙�뫗占쏙옙id(userid)
	// 獄쏉옙占쏙옙揶쏉옙: 占쎄퉫�궗占싼됵옙
	public MemberVo getMember(String userid) {
		int result = -1;
		String sql = "select * from member where userid = ?";
		MemberVo mVo = null;
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;	// 占쏙옙占쏙옙 �뜎�눖�봺
		
		try {
			conn = DBManager.getConnection();
			// (3占썩몿占�) Statement 揶쏉옙筌ｏ옙 占쏙옙占쏙옙
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
		
			
			// (4占썩몿占�) SQl�눧占� 占썬끋占쏙옙 獄쏉옙 野껉퀗�궢筌ｏ옙�뵳占� => executeUpdate : 占쎌럩占쏙옙(insert/update/delete)
			rs = pstmt.executeQuery();			// �뜎�눖�봺 占쏙옙占쏙옙
			if(rs.next()) {
				// 占쏙옙�뜮占썸에占썽겫占쏙옙占� 占쏙옙占쏙옙占쏙옙癰귨옙 占쏙옙占쏙옙
				mVo = new MemberVo();
				mVo.setName(rs.getString("name")); 	// �뚎됵옙�눖占� name占쏙옙 占쏙옙癰귣�占쏙옙 揶쏉옙占쎈챷占쏙옙
				mVo.setUserid(rs.getString("userid")); // 	�뚎됵옙�눖占� name占쏙옙 占쏙옙癰귣�占쏙옙 揶쏉옙占쎈챷占쏙옙
				mVo.setPwd(rs.getString("pwd")); // 	�뚎됵옙�눖占� name占쏙옙 占쏙옙癰귣�占쏙옙 揶쏉옙占쎈챷占쏙옙
				mVo.setEmail(rs.getString("email")); // 	�뚎됵옙�눖占� name占쏙옙 占쏙옙癰귣�占쏙옙 揶쏉옙占쎈챷占쏙옙
				mVo.setPhone(rs.getString("phone")); // 	�뚎됵옙�눖占� name占쏙옙 占쏙옙癰귣�占쏙옙 揶쏉옙占쎈챷占쏙옙
				mVo.setAdmin(rs.getInt("admin")); // 	�뚎됵옙�눖占� name占쏙옙 占쏙옙癰귣�占쏙옙 揶쏉옙占쎈챷占쏙옙
				mVo.setPictureurl(rs.getString("pictureurl"));
				mVo.setPoint(rs.getInt("point"));
				mVo.setSelfcomment(rs.getString("selfcomment"));
			} else {
				result = -1;		// 占쏙옙�뜮占쏙옙占� userid 占쏙옙占쏙옙
			}
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt, rs);
		}
		return mVo;
	}
	
	// 占쏙옙占쏙옙 占쏙옙癰귨옙 占쏙옙占쎄퀣占쎈똾占쏙옙: update
	// 占쏙옙占싸븝옙: 占쏙옙占쏙옙 占쏙옙占쎈�占� 占쏙옙癰귨옙
	// 獄쏉옙占쏙옙揶쏉옙 : 占쎄퉫�궗占싼됵옙
	public int updateMember(MemberVo mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?, phone=?, admin=?, pictureurl=?, name=?, selfcomment=?, point=? where userid=?";


		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			// (3占썩몿占�) Statement 揶쏉옙筌ｏ옙 占쏙옙占쏙옙
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getPictureurl());
			pstmt.setString(6, mVo.getName());
			pstmt.setString(7, mVo.getSelfcomment());
			pstmt.setInt(8, mVo.getPoint());
			pstmt.setString(9, mVo.getUserid());
			
			result = pstmt.executeUpdate();			// �뜎�눖�봺 占쏙옙占쏙옙
			System.out.println(mVo);
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
		}
		
		
		return result;
	}

	// 占쏙옙占쎈�占쏙옙 占쏙옙占쏙옙 : select
	// 占쏙옙占싸븝옙: 餓ο옙癰귨옙 筌ｋ똾占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙占쎈�占쏙옙
	// 獄쏉옙占쏙옙揶쏉옙: 筌ｋ똾占싼뗰옙占� ID占쏙옙 DB 鈺곕똻占쏙옙 占싼됵옙
	public int confirmID(String userid) {
		int result = -1;
		String sql = "select userid from member where userid = ?";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;	// 占쏙옙占쏙옙 �뜎�눖�봺
		
		try {
			conn = DBManager.getConnection();
			// (3占썩몿占�) Statement 揶쏉옙筌ｏ옙 占쏙옙占쏙옙
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);		
			
			// (4占썩몿占�) SQl�눧占� 占썬끋占쏙옙 獄쏉옙 野껉퀗�궢筌ｏ옙�뵳占� => executeUpdate : 占쎌럩占쏙옙(insert/update/delete)
			rs = pstmt.executeQuery();			// �뜎�눖�봺 占쏙옙占쏙옙
			// res.next() : 占썬끉占쏙옙 占쏙옙(row)占쏙옙 占쏙옙占쏙옙, rs.getString("�뚎됵옙�눖占�")
			if(rs.next()) {
				// 占쏙옙�뜮占썸에占썽겫占쏙옙占� 占쏙옙占쏙옙占쏙옙癰귨옙 占쏙옙占쏙옙
				result = 1;			// 占쏙옙�뜮占쏙옙占� userid 占쏙옙占쏙옙
			} else {
				result = -1;		// 占쏙옙�뜮占쏙옙占� userid 占쏙옙占쏙옙
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

		List<MemberVo> list = new ArrayList<MemberVo>(); // �뵳�딉옙�끋占쏙옙 �뚎됵옙占쏙옙占� 揶쏉옙筌ｏ옙 占쏙옙占쏙옙

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // 占쏙옙占쏙옙 �뜎�눖�봺

		try {
			conn = DBManager.getConnection();
			// (3占썩몿占�) Statement 揶쏉옙筌ｏ옙 占쏙옙占쏙옙
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, 1+(page-1)*9);
			pstmt.setInt(3, page * 9);

			// (4占썩몿占�) SQl�눧占� 占썬끋占쏙옙 獄쏉옙 野껉퀗�궢筌ｏ옙�뵳占� => executeUpdate : 占쎌럩占쏙옙(insert/update/delete)
			rs = pstmt.executeQuery(); // �뜎�눖�봺 占쏙옙占쏙옙
			while (rs.next()) {
				MemberVo mVo = new MemberVo();
				// 占쏙옙�뜮占썸에占썽겫占쏙옙占� 占쏙옙占쏙옙占쏙옙癰귨옙 占쏙옙占쏙옙
				mVo.setUserid(rs.getString("userid"));
				mVo.setName(rs.getString("name")); // DB占쏙옙占쏙옙 揶쏉옙占쎈챷占쏙옙 揶쏉옙筌ｋ�占쏙옙 pVo揶쏉옙筌ｋ똻占쏙옙 占쏙옙占쏙옙
				mVo.setPwd(rs.getString("pwd"));
				mVo.setPictureurl(rs.getString("pictureurl"));
				mVo.setEmail(rs.getString("Email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setPoint(rs.getInt("point"));
				mVo.setSelfcomment(rs.getString("selfcomment"));
				/* System.out.println(pVo); */
				list.add(mVo); // list 揶쏉옙筌ｋ똻占쏙옙 占쎄퀣占쎈똾占쏙옙 �빊占썲첎占�
				System.out.println(mVo);
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
		PreparedStatement pstmt = null; // 占쏙옙占쏙옙 �뜎�눖�봺
		
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
		// 占쏙옙占쎌눛占쏙옙 �뜎�눖�봺�눧紐꾬옙占� 占쎈�占쏙옙 揶쏉옙筌랃옙 獄쏉옙饔낉옙占쏙옙 占싼됵옙�됵옙 占썬끋占쏙옙占쎈똻占쏙옙 占쏙옙 占쏙옙, 筌띲끆占썼퉪占쏙옙占썲첎占� 筌랃옙占쏙옙占쏙옙 �뜎�눖�봺�눧占� 占쏙옙�뵳占� 占쏙옙占쏙옙
		PreparedStatement pstmt = null;

		String sql = "delete from member where userid=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			result = pstmt.executeUpdate(); // �뜎�눖�봺�눧占� 占썬끋占쏙옙
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
}