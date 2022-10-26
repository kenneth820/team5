package com.rkrua.controller;

import java.io.File;
import java.io.IOException;

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
		String savePath= "upload";		// 저장경로
		int uploadFileSizeLimit = 5 * 1024 * 1024;		// 파일 크기
		String encType = "UTF-8";		// 인코딩 타입

		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);	// 실제 서버 경로 획득
		System.out.println(uploadFilePath);
		
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		File Folder = new File(uploadFilePath);
		if (!Folder.exists()) {
			try{
				Folder.mkdir(); 	//폴더 생성합니다.
//				System.out.println("폴더가 생성되었습니다.");
			} catch(Exception e){
				e.getStackTrace();
			}
        }else {
//			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,							
					uploadFilePath,						
					uploadFileSizeLimit,				
					encType,							
					new DefaultFileRenamePolicy()		
					);

			String name = multi.getParameter("name");
			int price = Integer.parseInt(multi.getParameter("price"));
			int category = Integer.parseInt(multi.getParameter("category"));
			String pictureurl = multi.getFilesystemName("pictureurl");
			
			pVo.setName(name);
			pVo.setPrice(price);
			pVo.setCategory(category);
			pVo.setPictureurl(pictureurl);
			
			result = pDao.insertProduct(pVo);
			
			if(result==1) {
				request.setAttribute("message", "��ǰ ��Ͽ� �����Ͽ����ϴ�.");
			} else {
				request.setAttribute("message", "��ǰ ��Ͽ� �����Ͽ����ϴ�.");
			}
			response.sendRedirect("productList.do");
			
		} catch(Exception e) {
			System.out.println("���� ���ε尣 ���� �߻�" + e);
		}
	}

}
