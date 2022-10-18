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
		HttpSession session = request.getSession(); // ���� ��ü ȣ��
		
		
		ProductDao pDao = ProductDao.getInstance();
		CartDao cDao = CartDao.getInstance();
		
		// �⺻�� ����
		String column = "name"; 	// �˻� ���(�о�)
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
		int total = pDao.getProductCount(keyword);
		System.out.println(total);
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		List<CartVo> cartList = cDao.selectAllCart(mVo.getUserid());
		request.setAttribute("pageList", productList);
		request.setAttribute("CartList", cartList);
		
		// ������ ������� ������ �̵�
		request.getRequestDispatcher("product/Shop.jsp").forward(request, response);
		};

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
