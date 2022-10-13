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
		// 상품 삭제 링크 클릭시 이동
		String code = request.getParameter("code");
		
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		// 데이터 베이스에서 삭제할 정보 확인
		pVo = pDao.selectProductByCode(code);
		
		request.setAttribute("product", pVo);
		
		// 페이지 이동 : 삭제 페이지
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("product/deleteProduct.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 삭제 코드 : 데이터베이스에서 상품 삭제
		
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		String code = request.getParameter("code");
		
		// 데이터베이스로부터 해당 코드의 정보 삭제
		pDao.deleteProduct(code);
		
		// 삭제 후 목록 페이지로 이동
		response.sendRedirect("productList.do");
	}

}
