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
 		
 		// ���� ����
 		HttpSession session = request.getSession();
 		// ����, ���� �Ӽ��� �����ǰ� �ִ� ����(��, �α����� �Ǿ� �ִ� ����)���� main.jsp �������� �̵�
 		if(session.getAttribute("loginUser") != null) { // ���ǿ� �α��� ȸ��  ����� ������ Ȯ�� �� ��������
 			url="main.jsp";
		} /*
			 * else if (session.getAttribute("loginUser") == admin) { url ="manager.jsp"; }
			 */
 		
 		// ������ �̵�(forward ���)
 		RequestDispatcher dispatcher;
 		dispatcher = request.getRequestDispatcher(url);
 		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 	// post ���
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();		// �������� ���
		
		String url = "member/login.jsp";
		
		MemberDao mDao = MemberDao.getInstance();
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		//��� ���� ��, ������ ���̵�/ ��ȣ
//		String id = "rkrua";
		int result = mDao.checkUser(userId, userPwd);
		
//		out.print("���̵�: "+ userId);
//		out.print("<br>");
//		out.print("��ȣ: "+ userPwd);
		
		// ���̵� ��ȣ  �� �� ������ �̵�
		if(result == 2) {
//			System.out.println("��ȣ ��ġ");
			url = "manager.jsp";
			
			MemberVo mVo = mDao.getMember(userId);
//			System.out.println("ȸ�� �̸�:" + mVo.getName());
			
	 		// ���� ����
	 		HttpSession session = request.getSession(); // ���� ��ü ȣ��
	 		session.setAttribute("loginUser", mVo);		// ���ǿ� ȸ�� ���� ����
	 		System.out.println(session.getAttribute("loginUser"));
//			request.setAttribute("name", mVo.getName());
//			request.setAttribute("id", mVo.getUserid());
		} else if (result ==1) {
//			System.out.println("��ȣ ��ġ");
			url = "main.jsp";
			
			MemberVo mVo = mDao.getMember(userId);
//			System.out.println("ȸ�� �̸�:" + mVo.getName());
			
	 		// ���� ����
	 		HttpSession session = request.getSession(); // ���� ��ü ȣ��
	 		session.setAttribute("loginUser", mVo);		// ���ǿ� ȸ�� ���� ����
	 		System.out.println(session.getAttribute("loginUser"));
//			request.setAttribute("name", mVo.getName());
//			request.setAttribute("id", mVo.getUserid());
		}	else if(result ==0){
			System.out.println("��ȣ ����ġ.");
		} else {			
			System.out.println("�������� �ʴ� ȸ��");
		}
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
