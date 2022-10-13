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
		
		// �⺻�� ����
		String column = "code"; 	// �˻� ���(�о�)
		String keyword = "";	// �˻� ���� (�˻���
		
		// Ű���尡 ����ִ� ��츦 ����Ͽ� �÷��� Ű���� �� �ӽ� ����
		String t_column = request.getParameter("column");
		String t_keyword = request.getParameter("keyword");
		
//		System.out.print("c:" + column);
//		System.out.println(" k:" + keyword);
		
		// null ���� �ƴ� ���,
		if(t_column != null)
			column = t_column;
		if(t_keyword != null)
			keyword = t_keyword;
	
		
		// �÷��� Ű���带 ����Ͽ� ���κ��� �˻��� ��� ����Ʈ�� ��ȯ�ϰ� ����
		List<ProductVo> productList = pDao.searchProduct(keyword);
		int total = pDao.getTotal();
		PageVo pVo = new PageVo(pageNum, amount, total);

		request.setAttribute("pageVo", pVo);
		request.setAttribute("pageList", productList);
		
		// ������ ������� ������ �̵�
		request.getRequestDispatcher("product/Shop.jsp").forward(request, response);
		};

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
