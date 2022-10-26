package com.rkrua.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rkrua.dao.VisitDao;
import com.rkrua.dto.MemberVo;
import com.rkrua.dto.VisitVo;


@WebServlet("/visitShowroom.do")
public class VisitShowroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(); // ���� ��ü ȣ��
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		System.out.println(mVo.getUserid());
		String userid = request.getParameter("userid");
		System.out.println(userid);
		VisitVo vVo = new VisitVo();
		VisitDao vDao = VisitDao.getInstance();
		
		vVo = vDao.moveShowroomByCode(userid);
		System.out.println(vVo);
		request.setAttribute("vVo", vVo);

		String url;
		
		if(userid.equals(mVo.getUserid())) {
			url = "main.jsp";
		} else {
			url= "sample.jsp";
		}
		System.out.println(url);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
