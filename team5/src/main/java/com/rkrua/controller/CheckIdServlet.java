package com.rkrua.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.MemberDao;

@WebServlet("/checkId.do")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		
		// 입력한 아이디를 DB에서 조회하여 동일한 아이디가 없는지 확인
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.confirmID(userid);
		
		if (result == 1) {
			request.setAttribute("message", "이 아이디는 사용 불가능합니다.");
		} else {
			request.setAttribute("message", "이 아이디는 사용 가능합니다.");
		}
		
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);

		RequestDispatcher dispatcher = request.getRequestDispatcher("member/checkId.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
