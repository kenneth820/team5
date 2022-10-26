package com.rkrua.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.MemberDao;
import com.rkrua.dto.MemberVo;

@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/join.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ȸ�����Կ��� �ۼ��� �����͸� ������ ���̽��� ����(insert)
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();		// �������� ��� ó��
		
		String name = request.getParameter("name");		//�Է¾�����κ��� �ޱ�
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		//String admin = request.getParameter("admin");
		int admin = Integer.parseInt(request.getParameter("admin"));
		
//		System.out.println("name: " + name);
//		System.out.println("userid: " + userid);
//		System.out.println("pwd: " + pwd);
//		System.out.println("email: " + email);
//		System.out.println("phone: " + phone);
//		System.out.println("admin: " + admin);
		
		MemberDao mDao = MemberDao.getInstance();	// DB���� 
		
		MemberVo mVo = new MemberVo();
		mVo.setName(name);			// ���͸� ���� MemberVo Ŭ������ ���� ����
		mVo.setUserid(userid);
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setAdmin(admin);
		
//		mDao.insertMember(name, userid, pwd, email, phone, admin);
//		mDao.insertMember(mVo);
		int result = mDao.insertMember(mVo);
		if (result == 1 ) {
			System.out.println("ȸ������ ����");
		} else {
			System.out.println("ȸ������ ����");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);			// ������ �̵�
	}

}
