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


@WebServlet("/deleteCart.do")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	CartDao cDao = CartDao.getInstance();
	HttpSession session = request.getSession(); 
	MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
	
	String cartid = request.getParameter("cartid"); // 삭제하려는 카트 아이디값 얻기
	cDao.deleteCart(cartid);		// 얻은 카트 아이디값을 삭제

	List<CartVo> cartList = cDao.selectAllCart(mVo.getUserid());
	request.setAttribute("CartList", cartList);
	int total = cDao.totalPrice(mVo.getUserid());		// 삭제한 뒤 총 금액 계산
	request.setAttribute("total", total);

	int change = cDao.resultPrice(mVo.getPoint(), total);  // 삭제한 뒤 거스름돈 계산
	
	request.setAttribute("change", change);	
	
	// ���� �� ��� �������� �̵�
	RequestDispatcher dispatcher = 
			request.getRequestDispatcher("product/Cart.jsp");
	dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
