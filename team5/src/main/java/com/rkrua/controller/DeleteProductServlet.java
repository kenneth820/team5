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
		// ��ǰ ���� ��ũ Ŭ���� �̵�
		String code = request.getParameter("code");
		
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		// ������ ���̽����� ������ ���� Ȯ��
		pVo = pDao.selectProductByCode(code);
		
		request.setAttribute("product", pVo);
		
		// ������ �̵� : ���� ������
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("product/deleteProduct.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ǰ ���� �ڵ� : �����ͺ��̽����� ��ǰ ����
		
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		String code = request.getParameter("code");
		
		// �����ͺ��̽��κ��� �ش� �ڵ��� ���� ����
		pDao.deleteProduct(code);
		
		// ���� �� ��� �������� �̵�
		response.sendRedirect("productList.do");
	}

}
