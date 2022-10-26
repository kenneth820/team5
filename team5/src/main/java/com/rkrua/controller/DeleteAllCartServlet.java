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

import com.rkrua.dao.CartDao;
import com.rkrua.dto.CartVo;
import com.rkrua.dto.MemberVo;

@WebServlet("/deleteAllCart.do")
public class DeleteAllCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartDao cDao = CartDao.getInstance();
		HttpSession session = request.getSession(); // 세션 획득
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");		// 유저아이디 받기
		
		cDao.deleteAllcart(mVo.getUserid());		// 해당하는 유저의 장바구니 상품 모두 삭제

		List<CartVo> cartList = cDao.selectAllCart(mVo.getUserid());	// cartList = none
		request.setAttribute("CartList", cartList);
		int total = cDao.totalPrice(mVo.getUserid());
		request.setAttribute("total", total);


		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("product/Cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
