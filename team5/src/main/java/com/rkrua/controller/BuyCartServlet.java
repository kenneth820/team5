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
		List<CartVo> cartList = cDao.selectAllCart(userid);		// 모든 카트리스트 획득
		ItemDao iDao = ItemDao.getInstance();		// 아이템 호출
		
//		code = cartList.get(1).getCode();
//		System.out.println(code);
		
		for(int i=0; i<cartList.size();i++) {
			code = cartList.get(i).getCode(); // cartList n번째 값 얻기
//			System.out.println(code);
			iDao.insertItem(userid, code);	// n번째 값을 보유 아이템에 넣어줌 -> 상품 구매
		}
		
		/* System.out.println(cartList.get(1).getCode()); */
		
		
		int result = cDao.buyProduct(userid, change);		// 상품 구매

		if (result == 1){ 
			cDao.deleteAllcart(userid);		// 상품 구매시, 장바구니에 있는 상품들 삭제
		  
			MemberDao mDao = MemberDao.getInstance();
			MemberVo mVo = mDao.getMember(userid);
			mVo.setPoint(change);			// 거스름돈 = 유저포인트
					  
			HttpSession session = request.getSession(); // 세션 호출
			session.setAttribute("loginUser", mVo);		// 세션 획득 why? => 초기화된 값을 새로고침해주기 위해
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('상품이 구매되었습니다.'); location.href='productList.do';</script>");
			writer.close(); 
			}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
