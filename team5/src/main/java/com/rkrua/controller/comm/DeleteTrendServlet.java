package com.rkrua.controller.comm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rkrua.dao.CommunityDao;
import com.rkrua.dto.MemberVo;
import com.rkrua.dto.ShowroomVo;
import com.rkrua.dto.TrendVo;

@WebServlet("/deleteTrend.do")
public class DeleteTrendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		HttpSession session = request.getSession(); // 세션 객체 호출
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		CommunityDao cDao = CommunityDao.getInstance();
		cDao.deleteTrend(num);
		
		// 湲곕낯 媛� �꽕�젙
		String column = "title"; // 寃��깋 ���긽(遺꾩빞)
		String keyword = "";	// 寃��깋 �궡�슜(寃��깋�뼱)
		int page = 1;			// �럹�씠吏� 踰덊샇

		// �궎�썙�뱶媛� 鍮꾩뼱 �엳�뒗 寃쎌슦瑜� ��鍮꾪븯�뿬 而щ읆怨� �궎�썙�뱶 媛� �엫�떆 ���옣
		String t_column = request.getParameter("column");
		String t_keyword = request.getParameter("k");
		String t_page = request.getParameter("p");
				
		// null 媛믪씠 �븘�땶 寃쎌슦,
		if(t_column != null && !t_column.equals("")) {
			column = t_column;			
		}

		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;			
		}
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		
		// 紐⑤뱺 �긽�뭹 由ъ뒪�듃瑜� �뵒鍮꾨줈 遺��꽣 議고쉶�븯怨� 異쒕젰 
		List<ShowroomVo> showroomList = cDao.getShowroomList();
		int showCount = cDao.getShowroomCount();

		request.setAttribute("showroomList", showroomList);
		request.setAttribute("showCount", showCount);
		
		List<TrendVo> trendList = cDao.gettrendList(column, keyword, page);
		int count = cDao.gettrendCount(column, keyword);
		
		request.setAttribute("count", count);
		request.setAttribute("trendList", trendList);
		
		String url;
		// 커뮤니티 페이지로 이동
		if (mVo.getAdmin()==1) {			
			url = "board/boardList.jsp";
		} else {
			url = "community/commList.jsp";			
		}
		request.getRequestDispatcher(url).forward(request, response);		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
