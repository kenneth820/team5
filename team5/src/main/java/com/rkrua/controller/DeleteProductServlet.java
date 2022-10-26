package com.rkrua.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.ProductDao;
import com.rkrua.dto.ProductVo;


@WebServlet("/deleteProduct.do")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 코드 획득
		String code = request.getParameter("code");
		
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		// 삭제하려는 상품데이터 코드로 획득
		pVo = pDao.selectProductByCode(code);
		
		request.setAttribute("product", pVo);
	
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("product/deleteProduct.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		ProductDao pDao = ProductDao.getInstance();
		
		String code = request.getParameter("code");
		
		// 코드로 얻은 상품 삭제
		pDao.deleteProduct(code);
		
		// 상품리스트로 이동
		response.sendRedirect("productList.do");
	}

}
