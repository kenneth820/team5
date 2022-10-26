package com.rkrua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rkrua.dao.CommentDao;
import com.rkrua.dao.MemberDao;
import com.rkrua.dto.CommentVo;
import com.rkrua.dto.MemberVo;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String url = "member/login.jsp";
 		
 		// 占쏙옙占쏙옙 占쏙옙占쏙옙
 		HttpSession session = request.getSession();
 		// 占쏙옙占쏙옙, 占쏙옙占쏙옙 占쌈쇽옙占쏙옙 占쏙옙占쏙옙占실곤옙 占쌍댐옙 占쏙옙占쏙옙(占쏙옙, 占싸깍옙占쏙옙占쏙옙 占실억옙 占쌍댐옙 占쏙옙占쏙옙)占쏙옙占쏙옙 main.jsp 占쏙옙占쏙옙占쏙옙占쏙옙 占싱듸옙
 		if(session.getAttribute("loginUser") != null) { // 占쏙옙占실울옙 占싸깍옙占쏙옙 회占쏙옙  占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙
 			url="main.jsp";
		} 
 		
 		RequestDispatcher dispatcher;
 		dispatcher = request.getRequestDispatcher(url);
 		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 	// post 占쏙옙占�
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();		// 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占�
		
		String url = "index.jsp";
		
		MemberDao mDao = MemberDao.getInstance();
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		//占쏙옙占� 占쏙옙占쏙옙 占쏙옙, 占쏙옙占쏙옙占쏙옙 占쏙옙占싱듸옙/ 占쏙옙호
//		String id = "rkrua";
		int result = mDao.checkUser(userId, userPwd);
		
//		out.print("占쏙옙占싱듸옙: "+ userId);
//		out.print("<br>");
//		out.print("占쏙옙호: "+ userPwd);
		
		// 占쏙옙占싱듸옙 占쏙옙호  占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙 占싱듸옙
		if(result == 2) {
//			System.out.println("占쏙옙호 占쏙옙치");
			url = "manager.jsp";
			
			MemberVo mVo = mDao.getMember(userId);
//			System.out.println("회占쏙옙 占싱몌옙:" + mVo.getName());
			
	 		// 占쏙옙占쏙옙 占쏙옙占쏙옙
	 		HttpSession session = request.getSession(); // 占쏙옙占쏙옙 占쏙옙체 호占쏙옙
	 		session.setAttribute("loginUser", mVo);		// 占쏙옙占실울옙 회占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	 		System.out.println(session.getAttribute("loginUser"));
//			request.setAttribute("name", mVo.getName());
//			request.setAttribute("id", mVo.getUserid());
		} else if (result ==1) {
//			System.out.println("占쏙옙호 占쏙옙치");
			url = "main.jsp";
			
			MemberVo mVo = mDao.getMember(userId);
			CommentDao cDao = CommentDao.getInstance();
//			System.out.println("회占쏙옙 占싱몌옙:" + mVo.getName());
			
	 		// 占쏙옙占쏙옙 占쏙옙占쏙옙
	 		HttpSession session = request.getSession(); // 占쏙옙占쏙옙 占쏙옙체 호占쏙옙
	 		session.setAttribute("loginUser", mVo);		// 占쏙옙占실울옙 회占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	 		List<CommentVo> commentList = cDao.selectCommentByUserid(mVo.getUserid());
	 		
	 		request.setAttribute("commentList", commentList);
	 		System.out.println(session.getAttribute("loginUser"));
//			request.setAttribute("name", mVo.getName());
//			request.setAttribute("id", mVo.getUserid());
		}	else if(result ==0){
			System.out.println("占쏙옙호 占쏙옙占쏙옙치.");
		} else {			
			System.out.println("占쏙옙占쏙옙占쏙옙占쏙옙 占십댐옙 회占쏙옙");
		}
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
