package com.rkrua.controller;

import java.io.IOException;
import java.util.List;

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


@WebServlet("/searchProduct.do")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // 세션 객체 호출
		
		
		ProductDao pDao = ProductDao.getInstance();
		CartDao cDao = CartDao.getInstance();
		
		// 기본값 설정
		String column = "name"; 	// 검색 대상(분야)
		String keyword = "";	// 검색 내용 (검색어
		
		// 키워드가 비어있는 경우를 대비하여 컬럼과 키워드 값 임시 저장
		String t_column = request.getParameter("column");
		String t_keyword = request.getParameter("keyword");
		
//		System.out.print("c:" + column);
//		System.out.println(" k:" + keyword);
		
		// null 값이 아닌 경우,
		if(t_column != null)
			column = t_column;
		if(t_keyword != null)
			keyword = t_keyword;
	
		
		// 컬럼과 키워드를 사용하여 디비로부터 검색한 결과 리스트를 반환하고 전달
		List<ProductVo> productList = pDao.searchProduct(keyword);
		int total = pDao.getProductCount(keyword);
		System.out.println(total);
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		List<CartVo> cartList = cDao.selectAllCart(mVo.getUserid());
		request.setAttribute("pageList", productList);
		request.setAttribute("CartList", cartList);
		
		// 포워드 방식으로 페이지 이동
		request.getRequestDispatcher("product/Shop.jsp").forward(request, response);
		};

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
