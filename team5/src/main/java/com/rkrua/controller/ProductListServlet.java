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
		HttpSession session = request.getSession(); // 세션 호출
		
		int page = 1;		// 기본 페이지 : 1
		String t_page = request.getParameter("p") ;
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		
		String keyword = ""; // 기본 키워드 : ""
		String t_keyword = request.getParameter("k");
		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;
		}
		
		int category = 00;	// 기본 카테고리 : 00 (모두 선택)
		String t_category = request.getParameter("c") ;
		if(t_category != null && !t_keyword.equals("00")) {
			category = Integer.parseInt(t_category);
		}
		
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");		// 유저아이디 세션으로 획득
		String userid = "";
		String t_userid = mVo.getUserid();		// 유저아이디 획득
		
		if(t_userid != null && !t_userid.equals("")) {		
			userid = t_userid;
		}
		List<ProductVo> pageList = pDao.getProductList(userid,category, keyword, page);		// 상품 리스트 가져오기 유저아이디는 해당 유저가 갖고있지 않은 상품만 보여줌 
		List<CartVo> cartList = cDao.selectAllCart(userid);	 // 장바구니 리스트 가져오기
		
		int count = pDao.getProductCount(userid, category, keyword);		// 페이징처리
		request.setAttribute("count", count);
		request.setAttribute("pageList", pageList);
		request.setAttribute("CartList", cartList);
		
		if (mVo.getAdmin()==1) {			
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("product/Shop_manager.jsp");
		dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("product/Shop.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
