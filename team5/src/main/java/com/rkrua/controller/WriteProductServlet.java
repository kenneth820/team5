package com.rkrua.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.rkrua.dao.ProductDao;
import com.rkrua.dto.ProductVo;

@WebServlet("/writeProduct.do")
public class WriteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("product/writeProduct.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		ProductVo pVo = new ProductVo();
		ProductDao pDao = ProductDao.getInstance();
		
		int result = -1;
		String savePath= "upload";
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";

		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println(uploadFilePath);
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,							// request 객체
					uploadFilePath,						// 서버상의 실제 파일 경로
					uploadFileSizeLimit,				// 최대 업로드 파일 크기
					encType,							// 인코딩 방식
					new DefaultFileRenamePolicy()		// 정책: 동일 이름시 다른 이름으로 추가
					);

//			int code = Integer.parseInt(multi.getParameter("code"));
			String name = multi.getParameter("name");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String pictureurl = multi.getFilesystemName("pictureurl");
			Date reg_date = Date.valueOf(multi.getParameter("reg_date"));			
			
//			System.out.println(name);
//			System.out.println(price);
//			System.out.println(description);
//			System.out.println(pictureurl);
//			System.out.println(reg_date);
			
			
//			pVo.setCode(code);		// 입력된 상품 정보 Vo에 저장
			pVo.setName(name);
			pVo.setPrice(price);
			pVo.setDescription(description);
			pVo.setPictureurl(pictureurl);
			pVo.setReg_date(reg_date);
			
			
			result = pDao.insertProduct(pVo);
			
			// 상품 등록 완료시, 메시지 출력
			if(result==1) {
//				System.out.println("상품 등록에 성공");
				request.setAttribute("message", "상품 등록에 성공하였습니다.");
			} else {
//				System.out.println("상품 등록에 실패");
				request.setAttribute("message", "상품 등록에 실패하였습니다.");
			}
			response.sendRedirect("productList.do");
			
//			RequestDispatcher dispatcher = 
//					request.getRequestDispatcher("productList.do");
//			dispatcher.forward(request, response);
		} catch(Exception e) {
			System.out.println("파일 업로드간 예외 발생" + e);
		}
	}

}
