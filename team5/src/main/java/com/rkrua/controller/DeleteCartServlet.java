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
	CartVo cVo = new CartVo();
	HttpSession session = request.getSession(); // ���� ��ü ȣ��
	MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
	
	String cartid = request.getParameter("cartid");
	cDao.deleteCart(cartid);

	List<CartVo> cartList = cDao.selectAllCart(mVo.getUserid());
	request.setAttribute("CartList", cartList);
	int total = cDao.totalPrice(mVo.getUserid());
	request.setAttribute("total", total);

	int change = cDao.resultPrice(mVo.getPoint(), total);
	
	request.setAttribute("change", change);
	// �����ͺ��̽��κ��� �ش� �ڵ��� ���� ����
	
	// ���� �� ��� �������� �̵�
	RequestDispatcher dispatcher = 
			request.getRequestDispatcher("product/Cart.jsp");
	dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
