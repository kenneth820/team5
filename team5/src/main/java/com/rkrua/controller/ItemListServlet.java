package com.rkrua.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rkrua.dao.ItemDao;
import com.rkrua.dto.ItemVo;
import com.rkrua.dto.MemberVo;


@WebServlet("/itemList.do")
public class ItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao iDao = ItemDao.getInstance();
		HttpSession session = request.getSession(); // ���� ��ü ȣ��
		
		int page = 1;	// 기본페이지 : 1페이지
		String t_page = request.getParameter("p") ;
		
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		
		String keyword = "";	// 기본 페이지 : ""
				
		int category = 00;		// 기본 카테고리 : 00(모두 선택)
		String t_category = request.getParameter("c") ;
		
		if(t_category != null && !t_category.equals("00")) {
			category = Integer.parseInt(t_category);
		}
		
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		String userid = "";
		String t_userid = mVo.getUserid();
		
		if(t_userid != null && !t_userid.equals("")) {
			userid = t_userid;
		}
		
		List<ItemVo> itemList = iDao.getItemList(userid,category, page);
		System.out.println(itemList);
		
		
		
		int count = iDao.getItemCount(userid, category);
		request.setAttribute("count", count);
		request.setAttribute("itemList", itemList);
		System.out.println(count);
		request.getRequestDispatcher("member/itemList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		request.setAttribute("code", code);
	}

}
