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
import com.rkrua.dao.ProductDao;
import com.rkrua.dto.CartVo;
import com.rkrua.dto.MemberVo;
import com.rkrua.dto.PageVo;
import com.rkrua.dto.ProductVo;

@WebServlet("/productList.do")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao pDao = ProductDao.getInstance();
		CartDao cDao = CartDao.getInstance();
		CartVo cVo = new CartVo();
		HttpSession session = request.getSession(); // ���� ��ü ȣ��
		
		int pageNum=1;
		int amount=9;
		
		if(request.getParameter("pageNum") != null && 
				request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		
		// ��� ��ǰ ����Ʈ�� ���κ��� ��ȸ�Ͽ� ���
//		ProductVo[] productList = pDao.selectAllProducts();
//		System.out.println(productList[0]);
		
		List<ProductVo> pageList = pDao.getListProducts(pageNum, amount);
		
		int total = pDao.getTotal();
		
		PageVo pVo = new PageVo(pageNum, amount, total);
		
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
//		List<CartVo> cartList = cDao.selectAllCart(session.getId());
		List<CartVo> cartList = cDao.selectAllCart(mVo.getUserid());
		
		
		request.setAttribute("pageVo", pVo);
		request.setAttribute("pageList", pageList);
		request.setAttribute("CartList", cartList);
//		productList.size();
//		productList.get(0);

		// ����Ʈ �������� �̵�
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("product/Shop.jsp");
		dispatcher.forward(request, response);
		
//		request.getRequestDispatcher("product/productList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
