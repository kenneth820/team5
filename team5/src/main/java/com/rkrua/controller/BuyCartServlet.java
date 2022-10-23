     package com.rkrua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rkrua.dao.CartDao;
import com.rkrua.dao.ItemDao;
import com.rkrua.dao.MemberDao;
import com.rkrua.dto.CartVo;
import com.rkrua.dto.MemberVo;

@WebServlet("/buyCart.do")
public class BuyCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		int change = Integer.parseInt(request.getParameter("change"));
		int code;
		
		CartDao cDao = CartDao.getInstance();		
		List<CartVo> cartList = cDao.selectAllCart(userid);
		ItemDao iDao = ItemDao.getInstance();
		
//		code = cartList.get(1).getCode();
//		System.out.println(code);
		
		for(int i=0; i<cartList.size();i++) {
			code = cartList.get(i).getCode(); // cartList의 코드 따오기
			System.out.println("코드:"+code);
			iDao.insertItem(userid, code);
		}
		
		/* System.out.println(cartList.get(1).getCode()); */
		
		
		int result = cDao.buyProduct(userid, change);

		if (result == 1){ 
			cDao.deleteAllcart(userid);
		  
			MemberDao mDao = MemberDao.getInstance();
			MemberVo mVo = mDao.getMember(userid);
			mVo.setPoint(change);
					  
			HttpSession session = request.getSession(); // 세션 객체 호출
			session.setAttribute("loginUser", mVo);		// 세션에 회원 정보 저장
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('상품이 구매되었습니다.'); location.href='productList.do';</script>");
			writer.close(); 
			}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
