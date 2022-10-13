package com.rkrua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.CartDao;
import com.rkrua.dto.CartVo;
import com.rkrua.dto.ProductVo;


@WebServlet("/addCart.do")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int code = Integer.parseInt(request.getParameter("code"));
		String userid = request.getParameter("userid");
		
		CartVo cVo = new CartVo();
		CartDao cDao = CartDao.getInstance();		
		
		System.out.println(cVo.getUserid());
		
		int result = cDao.checkCart(userid, code);
		if (result == -1){
			response.setContentType("text/html; charset=UTF-8");
			cDao.addCart(userid, code);			
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('장바구니에 추가되었습니다.'); location.href='productList.do';</script>");
			writer.close();
		} else {
			response.setContentType("text/html; charset=UTF-8");		
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('이미 장바구니에 있는 상품입니다.'); location.href='productList.do';</script>");
			writer.close();
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
