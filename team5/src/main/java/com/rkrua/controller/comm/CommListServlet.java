package com.rkrua.controller.comm;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.CommunityDao;
import com.rkrua.dto.ShowroomVo;
import com.rkrua.dto.TrendVo;

@WebServlet("/commList.do")
public class CommListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityDao cDao = CommunityDao.getInstance();
		
		// 湲곕낯 媛� �꽕�젙
		String column = "code"; // 寃��깋 ���긽(遺꾩빞)
		String keyword = "";	// 寃��깋 �궡�슜(寃��깋�뼱)
		int page = 1;			// �럹�씠吏� 踰덊샇

		// �궎�썙�뱶媛� 鍮꾩뼱 �엳�뒗 寃쎌슦瑜� ��鍮꾪븯�뿬 而щ읆怨� �궎�썙�뱶 媛� �엫�떆 ���옣
		String t_column = request.getParameter("column");
		String t_keyword = request.getParameter("keyword");
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
		
		// 湲곕낯 媛� �꽕�젙
		column = "num"; // 寃��깋 ���긽(遺꾩빞)
		keyword = "";	// 寃��깋 �궡�슜(寃��깋�뼱)
		page = 1;			// �럹�씠吏� 踰덊샇

		// �궎�썙�뱶媛� 鍮꾩뼱 �엳�뒗 寃쎌슦瑜� ��鍮꾪븯�뿬 而щ읆怨� �궎�썙�뱶 媛� �엫�떆 ���옣
		t_column = request.getParameter("column");
		t_keyword = request.getParameter("keyword");
		t_page = request.getParameter("p");
				
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
		
		List<TrendVo> trendList = cDao.gettrendList(column, keyword, page);
		int trendcount = cDao.gettrendCount(column, keyword);
		
		request.setAttribute("trendList", trendList);
		request.setAttribute("trendcount", trendcount);
		
		String url = "community/commList.jsp";
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		// �룷�썙�뱶 諛⑹떇�쑝濡� �럹�씠吏� �씠�룞		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
