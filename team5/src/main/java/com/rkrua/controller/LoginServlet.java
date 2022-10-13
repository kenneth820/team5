package com.rkrua.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rkrua.dao.MemberDao;
import com.rkrua.dto.MemberVo;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String url = "member/login.jsp";
 		
 		// 세션 설정
 		HttpSession session = request.getSession();
 		// 만약, 세션 속성이 유지되고 있는 동안(즉, 로그인이 되어 있는 상태)에는 main.jsp 페이지로 이동
 		if(session.getAttribute("loginUser") != null) { // 세션에 로그인 회원  저장된 정보를 확인 후 가져오기
 			url="main.jsp";
		} /*
			 * else if (session.getAttribute("loginUser") == admin) { url ="manager.jsp"; }
			 */
 		
 		// 페이지 이동(forward 방식)
 		RequestDispatcher dispatcher;
 		dispatcher = request.getRequestDispatcher(url);
 		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 	// post 방식
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();		// 웹페이지 출력
		
		String url = "member/login.jsp";
		
		MemberDao mDao = MemberDao.getInstance();
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		//디비 연동 후, 가져올 아이디/ 암호
//		String id = "rkrua";
		int result = mDao.checkUser(userId, userPwd);
		
//		out.print("아이디: "+ userId);
//		out.print("<br>");
//		out.print("암호: "+ userPwd);
		
		// 아이디 암호  비교 후 페이지 이동
		if(result == 2) {
//			System.out.println("암호 일치");
			url = "manager.jsp";
			
			MemberVo mVo = mDao.getMember(userId);
//			System.out.println("회원 이름:" + mVo.getName());
			
	 		// 세션 설정
	 		HttpSession session = request.getSession(); // 세션 객체 호출
	 		session.setAttribute("loginUser", mVo);		// 세션에 회원 정보 저장
	 		System.out.println(session.getAttribute("loginUser"));
//			request.setAttribute("name", mVo.getName());
//			request.setAttribute("id", mVo.getUserid());
		} else if (result ==1) {
//			System.out.println("암호 일치");
			url = "main.jsp";
			
			MemberVo mVo = mDao.getMember(userId);
//			System.out.println("회원 이름:" + mVo.getName());
			
	 		// 세션 설정
	 		HttpSession session = request.getSession(); // 세션 객체 호출
	 		session.setAttribute("loginUser", mVo);		// 세션에 회원 정보 저장
	 		System.out.println(session.getAttribute("loginUser"));
//			request.setAttribute("name", mVo.getName());
//			request.setAttribute("id", mVo.getUserid());
		}	else if(result ==0){
			System.out.println("암호 불일치.");
		} else {			
			System.out.println("존재하지 않는 회원");
		}
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
