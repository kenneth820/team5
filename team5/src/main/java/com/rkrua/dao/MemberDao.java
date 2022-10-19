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
// �곗�댄�� 踰��댁�� ��洹�
public class MemberDao {
	
	private static MemberDao instance = new MemberDao();	
	// 占쎄문占쎄쉐占쎌��
	private MemberDao(){		
	}	
	// 筌�遺용�쇽옙諭�
	public static MemberDao getInstance() {
		return instance;		
	}	

	// 濡�洹몄��(�ъ�⑹�� �몄�): select
	// ���κ�: 濡�洹몄�� ���댁����� ���λ��� �ъ�⑹�����대���� ���� 
	// 諛���媛� : result 1: ���� �쇱� 0: ���� 遺��쇱� -1: �ъ�⑹�� ���대�� ����
	public int checkUser(String userid, String pwd) {
		int result= -1;
		
//		Statement stmt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//		String sql = "select pwd from member where userid='"+userid+"'";
		String sql = "select pwd, admin from member where userid=?";
		try {
//			// (1�④�	) JDBC ���쇱�대� 濡���
//			Class.forName("oracle.jdbc.driver.OracleDriver");		
//			// (2�④�) �곗�댄�곕��댁�� �곌껐 媛�泥� ����
//			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//			String uid = "ora_user";
//			String pass = "1234";
//			conn =  DriverManager.getConnection(url, uid, pass);
			conn = DBManager.getConnection();
			
			// (3�④�) Statement 媛�泥� ����
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, userid);
			// (4�④�) SQl臾� �ㅽ�� 諛� 寃곌낵泥�由� => executeQuery : 議고��(selecet)
			rs = pstmt.executeQuery();
			// rs.next() ": �ㅼ�� �� (row)����, rs.getString("
			if(rs.next()){
				//���대�� / ���� 鍮�援� �� ���댁� �대��
				if(rs.getString("pwd")!= null && 
						rs.getString("pwd").equals(pwd) && rs.getInt("admin") == 1) {
					result = 2;  // ���� �쇱�
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
//				// (5�④�) �ъ�⑺�� 由ъ���� �댁��
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
	
	// ���� 媛���
//	public int insertMember(String name, String id, String pwd, String email, String phone, int admin) {
	public int insertMember(MemberVo mVo) {
		int result = -1;
		Connection conn = null;
//		Statement stmt = null;		// ���� 荑쇰━
		// ���쇳�� 荑쇰━臾몄�� �뱀�� 媛�留� 諛�轅��� �щ�щ� �ㅽ���댁�� �� ��, 留ㅺ�蹂���媛� 留����� 荑쇰━臾� ��由� ����
		PreparedStatement pstmt = null;	// ���� 荑쇰━
		
		
//		String sql_insert= "insert into member values('"+name+"','"+id+"','"+pwd+"','"+email+"','"+phone+"',"+admin+")";
		String sql_insert= "insert into member(name, userid, pwd, email, phone, selfcomment) values(?, ?, ?, ?, ?, ?)";
		
//		System.out.println(sql_insert);
		
		try {
			conn = DBManager.getConnection();
			// (3�④�) Statement 媛�泥� ����
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql_insert);
//			pstmt.setString(1, name);
//			pstmt.setString(2, id);
//			pstmt.setString(3, pwd);
//			pstmt.setString(4, email);
//			pstmt.setString(5, phone);				// 臾몄����
//			pstmt.setInt(6, admin);					// ������
			
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getPhone());				// 臾몄����
			pstmt.setString(6, mVo.getSelfcomment());
//			pstmt.setFloat(admin, float x);			// �ㅼ����
//			pstmt.setDate(idx, Date x);				// ��吏���
//			pstmt.setTimestamp(idx, Timestamp t);	// ��媛���
			
			// (4�④�) SQl臾� �ㅽ�� 諛� 寃곌낵泥�由� => executeUpdate : �쎌��(insert/update/delete)
//			result = stmt.executeUpdate(sql_insert);
			result = pstmt.executeUpdate();			// 荑쇰━ ����
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
			//			try {
//				// (5�④�) �ъ�⑺�� 由ъ���� �댁��
////				stmt.close();
//				pstmt.close();
//				conn.close();				
//			} catch(SQLException e) {
//				e.printStackTrace();
//			} 
		}
		return result;
}

	// ���� ��蹂� 媛��몄�ㅺ린 : select
	// ���κ�: �ъ�⑹��id(userid)
	// 諛���媛�: �깃났�щ�
	public MemberVo getMember(String userid) {
		int result = -1;
		String sql = "select * from member where userid = ?";
		MemberVo mVo = null;
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;	// ���� 荑쇰━
		
		try {
			conn = DBManager.getConnection();
			// (3�④�) Statement 媛�泥� ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
		
			
			// (4�④�) SQl臾� �ㅽ�� 諛� 寃곌낵泥�由� => executeUpdate : �쎌��(insert/update/delete)
			rs = pstmt.executeQuery();			// 荑쇰━ ����
			if(rs.next()) {
				// ��鍮�濡�遺��� ������蹂� ����
				mVo = new MemberVo();
				mVo.setName(rs.getString("name")); 	// 而щ�쇰� name�� ��蹂대�� 媛��몄��
				mVo.setUserid(rs.getString("userid")); // 	而щ�쇰� name�� ��蹂대�� 媛��몄��
				mVo.setPwd(rs.getString("pwd")); // 	而щ�쇰� name�� ��蹂대�� 媛��몄��
				mVo.setEmail(rs.getString("email")); // 	而щ�쇰� name�� ��蹂대�� 媛��몄��
				mVo.setPhone(rs.getString("phone")); // 	而щ�쇰� name�� ��蹂대�� 媛��몄��
				mVo.setAdmin(rs.getInt("admin")); // 	而щ�쇰� name�� ��蹂대�� 媛��몄��
				mVo.setPictureurl(rs.getString("pictureurl"));
				mVo.setPoint(rs.getInt("point"));
				mVo.setSelfcomment(rs.getString("selfcomment"));
			} else {
				result = -1;		// ��鍮��� userid ����
			}
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt, rs);
		}
		return mVo;
	}
	
	// ���� ��蹂� ���곗�댄��: update
	// ���κ�: ���� ���대� ��蹂�
	// 諛���媛� : �깃났�щ�
	public int updateMember(MemberVo mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?, phone=?, admin=?, pictureurl=?, name=?, selfcomment=? where userid=?";


		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			// (3�④�) Statement 媛�泥� ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getPictureurl());
			pstmt.setString(6, mVo.getName());
			pstmt.setString(7, mVo.getSelfcomment());
			pstmt.setString(8, mVo.getUserid());
			
			result = pstmt.executeUpdate();			// 荑쇰━ ����
			System.out.println(mVo);
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
		}
		
		
		return result;
	}

	// ���대�� ���� : select
	// ���κ�: 以�蹂� 泥댄�� �� ���� ���대��
	// 諛���媛�: 泥댄�ы�� ID�� DB 議댁�� �щ�
	public int confirmID(String userid) {
		int result = -1;
		String sql = "select userid from member where userid = ?";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;	// ���� 荑쇰━
		
		try {
			conn = DBManager.getConnection();
			// (3�④�) Statement 媛�泥� ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);		
			
			// (4�④�) SQl臾� �ㅽ�� 諛� 寃곌낵泥�由� => executeUpdate : �쎌��(insert/update/delete)
			rs = pstmt.executeQuery();			// 荑쇰━ ����
			// res.next() : �ㅼ�� ��(row)�� ����, rs.getString("而щ�쇰�")
			if(rs.next()) {
				// ��鍮�濡�遺��� ������蹂� ����
				result = 1;			// ��鍮��� userid ����
			} else {
				result = -1;		// ��鍮��� userid ����
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

		List<MemberVo> list = new ArrayList<MemberVo>(); // 由ъ�ㅽ�� 而щ���� 媛�泥� ����

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // ���� 荑쇰━

		try {
			conn = DBManager.getConnection();
			// (3�④�) Statement 媛�泥� ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, 1+(page-1)*9);
			pstmt.setInt(3, page * 9);

			// (4�④�) SQl臾� �ㅽ�� 諛� 寃곌낵泥�由� => executeUpdate : �쎌��(insert/update/delete)
			rs = pstmt.executeQuery(); // 荑쇰━ ����
			while (rs.next()) {
				MemberVo mVo = new MemberVo();
				// ��鍮�濡�遺��� ������蹂� ����
				mVo.setUserid(rs.getString("userid"));
				mVo.setName(rs.getString("name")); // DB���� 媛��몄�� 媛�泥대�� pVo媛�泥댁�� ����
				mVo.setPwd(rs.getString("pwd"));
				mVo.setPictureurl(rs.getString("pictureurl"));
				mVo.setEmail(rs.getString("Email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setPoint(rs.getInt("point"));
				mVo.setSelfcomment(rs.getString("selfcomment"));
				/* System.out.println(pVo); */
				list.add(mVo); // list 媛�泥댁�� �곗�댄�� 異�媛�
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
		PreparedStatement pstmt = null; // ���� 荑쇰━
		
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
		// ���쇳�� 荑쇰━臾몄�� �뱀�� 媛�留� 諛�轅��� �щ�щ� �ㅽ���댁�� �� ��, 留ㅺ�蹂���媛� 留����� 荑쇰━臾� ��由� ����
		PreparedStatement pstmt = null;

		String sql = "delete from member where userid=?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			result = pstmt.executeUpdate(); // 荑쇰━臾� �ㅽ��
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
}