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
import com.rkrua.dto.TrandVo;

@WebServlet("/commList.do")
public class CommListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityDao cDao = CommunityDao.getInstance();
		
		// 기본 값 설정
		String column = "code"; // 검색 대상(분야)
		String keyword = "";	// 검색 내용(검색어)
		int page = 1;			// 페이지 번호

		// 키워드가 비어 있는 경우를 대비하여 컬럼과 키워드 값 임시 저장
		String t_column = request.getParameter("column");
		String t_keyword = request.getParameter("keyword");
		String t_page = request.getParameter("p");
				
		// null 값이 아닌 경우, 
		if(t_column != null && !t_column.equals("")) {
			column = t_column;
		}
		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;			
		}
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		
		// 모든 상품 리스트를 디비로 부터 조회하고 출력 
		List<ShowroomVo> showroomList = cDao.getShowroomList();
		int showCount = cDao.getShowroomCount();

		request.setAttribute("showroomList", showroomList);
		request.setAttribute("showCount", showCount);
		
		// 기본 값 설정
		column = "num"; // 검색 대상(분야)
		keyword = "";	// 검색 내용(검색어)
		page = 1;			// 페이지 번호

		// 키워드가 비어 있는 경우를 대비하여 컬럼과 키워드 값 임시 저장
		t_column = request.getParameter("column");
		t_keyword = request.getParameter("keyword");
		t_page = request.getParameter("p");
				
		// null 값이 아닌 경우, 
		if(t_column != null && !t_column.equals("")) {
			column = t_column;
		}
		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;			
		}
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		
		List<TrandVo> trandList = cDao.getTrandList(column, keyword, page);
		int Trandcount = cDao.getTrandCount(column, keyword);
		
		request.setAttribute("trandList", trandList);
		request.setAttribute("Trandcount", Trandcount);
		
		String url = "community/commList.jsp";
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		// 포워드 방식으로 페이지 이동		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
