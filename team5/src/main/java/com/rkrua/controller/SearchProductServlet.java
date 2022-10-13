package com.rkrua.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.ProductDao;
import com.rkrua.dto.PageVo;
import com.rkrua.dto.ProductVo;


@WebServlet("/searchProduct.do")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum=1;
		int amount=9;
		
		if(request.getParameter("pageNum") != null && 
				request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		ProductDao pDao = ProductDao.getInstance();
		
		// 기본값 설정
		String column = "code"; 	// 검색 대상(분야)
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
		int total = pDao.getTotal();
		PageVo pVo = new PageVo(pageNum, amount, total);

		request.setAttribute("pageVo", pVo);
		request.setAttribute("pageList", productList);
		
		// 포워드 방식으로 페이지 이동
		request.getRequestDispatcher("product/Shop.jsp").forward(request, response);
		};

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
