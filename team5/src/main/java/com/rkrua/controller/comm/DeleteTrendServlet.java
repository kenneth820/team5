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
		cDao.deleteTrend(num);	// 상품 삭제
		
		// 기본값 설정
		String column = "title"; 
		String keyword = "";	
		int page = 1;			

		// 컬럼, 키워드, 페이지 값 받기
		String t_column = request.getParameter("column");
		String t_keyword = request.getParameter("k");
		String t_page = request.getParameter("p");
		// null 이 아니거나 ""이 아닌 경우,
		if(t_column != null && !t_column.equals("")) {
			column = t_column;			
		}
		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;			
		}
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		
		// 쇼룸 리스트 획득
		List<ShowroomVo> showroomList = cDao.getShowroomList();
		int showCount = cDao.getShowroomCount();
		request.setAttribute("showroomList", showroomList);
		request.setAttribute("showCount", showCount);
		
		// 나머지 트렌드 리스트 획득
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
