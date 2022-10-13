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

@WebServlet("/updateProduct.do")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿼리스트링으로 전달받은 code 획득
		String code = request.getParameter("code");
		
		// 상품 수정 링크 클릭시 수정할 상품 정보를 표시
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		// 데이터 베이스에서 수정할 정보 확인
		pVo = pDao.selectProductByCode(code);
		
		request.setAttribute("product", pVo);
		
		// 페이지 이동 : 수정 페이지
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("product/updateProduct.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 수정 코드 : 데이터베이스에서 상품 삭제
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		// 파일 업로드 코드 작성	
		int result = -1;
		String savePath= "upload";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";

//		System.out.println(uploadFilePath);
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,							// request 객체
					uploadFilePath,						// 서버상의 실제 파일 경로
					uploadFileSizeLimit,				// 최대 업로드 파일 크기
					encType,							// 인코딩 방식
					new DefaultFileRenamePolicy()		// 정책: 동일 이름시 다른 이름으로 추가
					);

			int code = Integer.parseInt(multi.getParameter("code"));
			String name = multi.getParameter("name");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String pictureurl = multi.getFilesystemName("pictureurl");
			Date reg_date = Date.valueOf(multi.getParameter("reg_date"));			
			
			pVo.setCode(code);		// 입력된 상품 정보 Vo에 저장
			pVo.setName(name);
			pVo.setPrice(price);
			pVo.setDescription(description);
			pVo.setPictureurl(pictureurl);
			pVo.setReg_date(reg_date);
		} catch(Exception e) {
			System.out.println("파일 업로드간 예외 발생: " + e);
		}
		
		// 데이터베이스로부터 해당 코드의 정보 수정
		result = pDao.updateProduct(pVo);
		
			// 상품 등록 완료시, 메시지 출력
			if(result==1) {
				System.out.println("상품 수정에 성공");
			} else {
				System.out.println("상품 수정에 실패");	
			}		
		
		// 수정 후 목록 페이지로 이동
		response.sendRedirect("productList.do");
	}

}
