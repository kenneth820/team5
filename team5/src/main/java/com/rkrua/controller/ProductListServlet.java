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
import com.rkrua.dto.ProductVo;

@WebServlet("/productList.do")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao pDao = ProductDao.getInstance();
		CartDao cDao = CartDao.getInstance();
		HttpSession session = request.getSession(); // 세션 객체 호출
		
		int page = 1;
		String t_page = request.getParameter("p") ;
		
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		
		String keyword = "";
		
		String t_keyword = request.getParameter("k");
		
		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;
		}
		
		int category = 00;
		String t_category = request.getParameter("c") ;
		
		if(t_category != null && !t_keyword.equals("00")) {
			category = Integer.parseInt(t_category);
		}
		
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		String userid = "";
		String t_userid = mVo.getUserid();
		
		if(t_userid != null && !t_userid.equals("")) {
			userid = t_userid;
		}
		
		List<ProductVo> pageList = pDao.getProductList(userid,category, keyword, page);
		System.out.println(pageList);
		
		
		List<CartVo> cartList = cDao.selectAllCart(userid);
		
		
		int count = pDao.getProductCount(userid, category, keyword);
		request.setAttribute("count", count);
		request.setAttribute("pageList", pageList);
		request.setAttribute("CartList", cartList);
//		productList.size();
//		productList.get(0);

		// 리스트 페이지로 이동
		if (mVo.getAdmin()==1) {			
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("product/Shop_manager.jsp");
		dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("product/Shop.jsp");
			dispatcher.forward(request, response);
		}
		
//		request.getRequestDispatcher("product/productList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
