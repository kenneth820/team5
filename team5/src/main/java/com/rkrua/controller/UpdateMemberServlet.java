package com.rkrua.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.MemberDao;
import com.rkrua.dto.MemberVo;

@WebServlet("/updateMember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		
		MemberDao mDao = MemberDao.getInstance();		// �����ͺ��̽� ����
		MemberVo mVo = mDao.getMember(userId);	// �����ͺ��̽��κ��� ȸ������ �ε�
		
//		request.setAttribute("name", mVo.getName());
		request.setAttribute("mVo", mVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/updateMember.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����ͺ��̽��� ������ ������ ������Ʈ
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		
		MemberDao mDao = MemberDao.getInstance();			// �����ͺ��̽� ����
		MemberVo mVo = new MemberVo();
		mVo.setUserid(userid);
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setAdmin(Integer.parseInt(admin));
		
		System.out.println(mVo.getUserid());
		
		mDao.updateMember(mVo);
		
		response.sendRedirect("login.do");
	}

}
