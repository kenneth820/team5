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
	// �̱���
	private static BoardDao instance = new BoardDao();
	private BoardDao() {
	}
	public static BoardDao getInstance() {
		return instance;
	}
	
	// �Խù� ���
//	�Է°�: ��ü �Խù� ����
//	��ȯ��: ���� ���� ���
	public int insertBoard(BoardVo bVo) {
		int result = -1;
		Connection conn = null;
		// ������ �������� Ư�� ���� �ٲ㼭 ������ �����ؾ� �� ��, �Ű������� ���Ƽ� ������ ���� �ʿ�
		PreparedStatement pstmt = null;

		String sql = "insert into board(name,email,pass,title,content) values(?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail()); // ������
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle()); // ������
			pstmt.setString(5, bVo.getContent()); // ��¥��

			result = pstmt.executeUpdate(); // ��������
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// �Խù� ��� ��ȸ
	public List<BoardVo> selectAllBoards() {
		String sql = "select * from board order by num desc";
		List<BoardVo> list = new ArrayList<BoardVo>(); // ����Ʈ �÷��� ��ü ����
		
		return list;
	}
	
	// 
	
	// �Խñ� �˻�
	public List<BoardVo> getBoardList(){
		return getBoardList("", 1);
	}
	// ������ �� ����Ʈ ǥ��
	public List<BoardVo> getBoardList(int page){
		return getBoardList("",page);
	}
	// �˻� ��ɰ� ����¡�� ����
	public List<BoardVo> getBoardList(String keyword, int page){
		String sql = "select * from ("
				+ "select rownum n, b.* "
				+ "from (select * from board where title like ? order by num desc) b "
				+ ")"
				+ "where n between ? and ?";
		
//		���������� n�� ���� ���� ù°�� A������ B�� ��� => A + B(n-1)
//		1 + (page-1)* 10
		List<BoardVo> list = new ArrayList<BoardVo>(); // ����Ʈ �÷��� ��ü ����

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // ���� ����

		try {
			conn = DBManager.getConnection();
			// (3�ܰ�) Statement ��ü ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, 1+(page-1)*2);
			pstmt.setInt(3, page * 2);
			
			
			// (4�ܰ�) SQl�� ���� �� ���ó�� => executeUpdate : ����(insert/update/delete)
			rs = pstmt.executeQuery(); // ���� ����
			while (rs.next()) {
				BoardVo bVo = new BoardVo();
				// ���κ��� ȸ������ ȹ��
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name")); // DB���� ������ ��ü�� pVo��ü�� ����
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				/* System.out.println(pVo); */
				list.add(bVo); // list ��ü�� ������ �߰�
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// �Խù� �� ��ȸ
	public int getBoardCount() {
		return getBoardCount("");
	}
	// Ư�� �÷��� Ű���带 ���� �Խù� �� ��ȸ
	public int getBoardCount(String keyword) {
		int count=0;
		String sql = "select count(num) as count from ( "
				+ "				select rownum n, b.* "
				+ "				from (select * from board where title like ? order by num desc) b"
				+ "				) ";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // ���� ����

		try {
			conn = DBManager.getConnection();
			// (3�ܰ�) Statement ��ü ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			
			// (4�ܰ�) SQl�� ���� �� ���ó�� => executeUpdate : ����(insert/update/delete)
			rs = pstmt.executeQuery(); // ���� ����
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
	
	// �Խù� ��ȣ�� Ư�� �Խù� ���� �Խù� ������ ��ȸ
	public BoardVo getNextBoard(int num) {
		BoardVo bVo = null;
		return bVo;
	}
	
//	public 
	
	
	
}
