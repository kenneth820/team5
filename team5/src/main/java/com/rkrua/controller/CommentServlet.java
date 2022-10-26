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
import com.rkrua.dto.CommentVo;
import com.rkrua.dto.MemberVo;

@WebServlet("/commentprofile.do")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); // 세션 객체 호출
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		String userid = request.getParameter("userid");
		
		CommentDao cDao = CommentDao.getInstance();		
		
		System.out.println(mVo.getUserid());
		List<CommentVo> commentList = cDao.selectCommentByUserid(userid);
//		System.out.println(commentList);
//		System.out.println(cartList.size());
		request.setAttribute("commentList", commentList);
		request.setAttribute("writer", mVo);
		//		int total = cDao.totalPrice(userid);
//		int change = cDao.resultPrice(mVo.getPoint(), total);
//		System.out.println(change);
		
//		request.setAttribute("change", change);
//		request.setAttribute("total", total);
//		System.out.println(cartList); 
		
		// 리스트 페이지로 이동
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(); // 세션 객체 호출
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		CommentVo cVo = new CommentVo();
		CommentDao cDao = CommentDao.getInstance();	

		String visitor = mVo.getUserid();
		String content = request.getParameter("content");
		cDao.addreply(visitor, content, visitor);
		
		List<CommentVo> commentList = cDao.selectCommentByUserid(mVo.getUserid());
//		System.out.println(commentList);
//		System.out.println(cartList.size());
		request.setAttribute("commentList", commentList);

		
		request.getRequestDispatcher("main.jsp").forward(request, response);
		
	}

}
