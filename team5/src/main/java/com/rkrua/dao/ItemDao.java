package com.rkrua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rkrua.dto.CartVo;
import com.rkrua.dto.ItemVo;
import com.rkrua.dto.ProductVo;
import com.rkrua.util.DBManager;

public class ItemDao {
	private ItemDao() {
	}
	
	private static ItemDao instance = new ItemDao();
	
	public static ItemDao getInstance() {
		return instance;
	}
	
	// �븘�씠�뀥 援щℓ
	public int insertItem(String userid, int code ) {
		int result = -1;
		Connection conn = null;
		// �룞�씪�븳 荑쇰━臾몄쓣 �듅�젙 媛믩쭔 諛붽퓭�꽌 �뿬�윭踰� �떎�뻾�빐�빞 �븷 �븣, 留ㅺ컻蹂��닔媛� 留롮븘�꽌 荑쇰━臾� �젙由� �븘�슂
		PreparedStatement pstmt = null;

		String sql_insert = "insert into item(userid,code) values(?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql_insert);
//			pstmt.setInt(1, pVo.getCode());
			pstmt.setString(1, userid);
			pstmt.setInt(2, code); // �젙�닔�삎
//			System.out.println("�쑀���븘�씠�뵒: "+userid);
//			System.out.println("�긽�뭹�븘�씠�뵒: "+code);
			
			result = pstmt.executeUpdate(); // �뜎�눖�봺 占쎈땾占쎈뻬
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// �븘�씠�뀥 由ъ뒪�듃 �뼸湲�
	
	// �븘�씠�뀥 遺꾨쪟
	public List<ItemVo> getItemList(){
		return getItemList("",00, 1);
	}
	// �럹�씠吏� 蹂� 由ъ뒪�듃 �몴�떆
	public List<ItemVo> getItemList(int page){
		return getItemList("",00,page);
	}
	public List<ItemVo> getItemList(int category,int page){
		return getItemList("",category,page);
	}

	public List<ItemVo> getItemList(String userid, int category, int page) {
		String sql = "select * from ( "
				+ "				select rownum n, b.* "
				+ "				from ( "
				+ "    select  * "
				+ "    from product p "
				+ "    left outer join item i "
				+ "        on i.code = p.code "
				+ "        and i.userid = ? "
				+ "        where i.code is not null "
				+ "        and category like ?) b "
				+ "				) "
				+ "				where n between ? and ? ";

		List<ItemVo> list = new ArrayList<ItemVo>(); // 由ъ뒪�듃 而щ젆�뀡 媛앹껜 �깮�꽦
//		System.out.println(userid);
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // �룞�쟻 荑쇰━

		try {
			conn = DBManager.getConnection();
			// (3�떒怨�) Statement 媛앹껜 �깮�꽦
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, "%"+category+"%");
			pstmt.setInt(3, 1+(page-1)*9);
			pstmt.setInt(4, page * 9);


			// (4�떒怨�) SQl臾� �떎�뻾 諛� 寃곌낵泥섎━ => executeUpdate : �궫�엯(insert/update/delete)
			rs = pstmt.executeQuery(); // 荑쇰━ �닔�뻾
			while (rs.next()) {
				ItemVo iVo = new ItemVo();
				// �뵒鍮꾨줈遺��꽣 �쉶�썝�젙蹂� �쉷�뱷
		
				iVo.setName(rs.getString("name"));
				iVo.setCategory(rs.getInt("category"));
				iVo.setCode(rs.getInt("code"));
				iVo.setUserid(rs.getString("userid"));
				iVo.setCoordinate(rs.getString("coordinate")); // DB�뿉�꽌 媛��졇�삩 媛앹껜瑜� pVo媛앹껜�뿉 ���옣
				iVo.setPictureurl(rs.getString("pictureurl"));
				iVo.setEquip(rs.getInt("equip"));
				/* System.out.println(pVo); */
				list.add(iVo); // list 媛앹껜�뿉 �뜲�씠�꽣 異붽�
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;

	}
	
	// �븘�씠�뀥 �옣李�
	public int equipItem(String userid) {
		int result = -1;
		String sql = "update item set equip=1 where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			// (3�떒怨�) Statement 媛앹껜 �깮�꽦
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);			
			// (4�떒怨�) SQl臾� �떎�뻾 諛� 寃곌낵泥섎━ => executeUpdate : �궫�엯(insert/update/delete)
			result = pstmt.executeUpdate();			// 荑쇰━ �닔�뻾
			
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// �븘�씠�뀥 �빐�젣
	public int unequipItem(String userid) {
		int result = -1;
		String sql = "update item set equip=0 where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			// (3�떒怨�) Statement 媛앹껜 �깮�꽦
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);			
			// (4�떒怨�) SQl臾� �떎�뻾 諛� 寃곌낵泥섎━ => executeUpdate : �궫�엯(insert/update/delete)
			result = pstmt.executeUpdate();			// 荑쇰━ �닔�뻾
			
		} catch(Exception e) {			
			e.printStackTrace();			
		} finally  {
			DBManager.close(conn, pstmt);
		}
		return result;

	}

	// 寃뚯떆臾� �닔 議고쉶
	public int getItemCount() {
		return getItemCount("",00);
	}
	public int getItemCount(int category) {
		return getItemCount("",category);
	}
	public int getItemCount(String userid, int category) {
		int count=0;
		String sql = "select count(*) as count from ( "
				+ "				select rownum n, b.* "
				+ "				from (select  * "
				+ "    from product p "
				+ "    left outer join item i "
				+ "        on i.code = p.code "
				+ "        and i.userid = ? "
				+ "        where i.code is not null "
				+ "        and category like ? ) b "
				+ "				)";
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; // �룞�쟻 荑쇰━

		try {
			conn = DBManager.getConnection();
			// (3�떒怨�) Statement 媛앹껜 �깮�꽦
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, "%"+category+"%");
			
			// (4�떒怨�) SQl臾� �떎�뻾 諛� 寃곌낵泥섎━ => executeUpdate : �궫�엯(insert/update/delete)
			rs = pstmt.executeQuery(); // 荑쇰━ �닔�뻾
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
