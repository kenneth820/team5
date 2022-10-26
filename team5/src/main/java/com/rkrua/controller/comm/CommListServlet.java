package com.rkrua.controller.comm;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/commList.do")
public class CommListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityDao cDao = CommunityDao.getInstance();
		HttpSession session = request.getSession(); // 세션 객체 호출
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		// 湲곕낯 媛� �꽕�젙
		String column = "title"; // 기본 컬럼
		String keyword = "";	// 기본 키워드
		int page = 1;			// 기본 페이지

		// jsp 에서 값 받기
		String t_column = request.getParameter("column");
		String t_keyword = request.getParameter("k");
		String t_page = request.getParameter("p");
				
		// null ,"" 아닌 경우
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
		
		// 트렌드 리스트 획득
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

		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		// �룷�썙�뱶 諛⑹떇�쑝濡� �럹�씠吏� �씠�룞		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
