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

import com.rkrua.dao.CartDao;
import com.rkrua.dto.CartVo;
import com.rkrua.dto.MemberVo;


@WebServlet("/cartList.do")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); // 세션 객체 호출
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		String userid = request.getParameter("userid");
		
		CartVo cVo = new CartVo();
		CartDao cDao = CartDao.getInstance();		
	
		List<CartVo> cartList = cDao.selectAllCart(userid);
//		System.out.println(cartList.size());
		request.setAttribute("CartList", cartList);
		int total = cDao.totalPrice(userid);
		int extrapoint = cDao.resultPrice(mVo.getPoint(), total);
		
		request.setAttribute("extrapoint", extrapoint);
		request.setAttribute("total", total);
		System.out.println(cartList);
		
		// 리스트 페이지로 이동
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("product/Cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
