package com.rkrua.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.MemberDao;
import com.rkrua.dto.MemberVo;


@WebServlet("/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao mDao = MemberDao.getInstance();
		
		String keyword = "";
		String column = "userid";
		int page = 1;
		
		String t_keyword = request.getParameter("k");
		String t_column = request.getParameter("c");
		String t_page = request.getParameter("p");
		System.out.println("컬럼"+t_column);
		System.out.println("키워드"+t_keyword);
		
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;
		}
		if(t_column != null && !t_column.equals("userid")) {
			column = t_column;
		}
		
		
		List<MemberVo> memberList = mDao.getMemberList(column, keyword, page);
		System.out.println(memberList);
		System.out.println(column);
		System.out.println(keyword);
		System.out.println(page);
		int count = mDao.getMemberCount(column, keyword);
		
		request.setAttribute("memberList", memberList);
		request.setAttribute("count", count);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("member/member_manager.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
