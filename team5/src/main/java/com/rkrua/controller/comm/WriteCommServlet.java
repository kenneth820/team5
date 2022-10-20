package com.rkrua.controller.comm;

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
import com.rkrua.dao.CommunityDao;
import com.rkrua.dto.TrandVo;

@WebServlet("/writeComm.do")
public class WriteCommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("community/writeComm.jsp");
		dispatcher.forward(request, response);		// 포워드 방식으로 페이지 이동
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		// post 방식 한글처리
		response.setContentType("text/html; charset=UTF-8");
		
		CommunityDao cDao = CommunityDao.getInstance();
		TrandVo tVo = new TrandVo();
		
		int result = -1;
		// 파일 업로드 관련 정보
		String savePath = "upload";
		int uploadFileSizeLimit = 5 * 1024 * 1024;	// 파일 최대 업로드 크기(5M)
		String encType = "UTF-8";					// 인코딩 방식
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉토리: ");
		System.out.println(uploadFilePath);
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					uploadFileSizeLimit,
					encType,
					new DefaultFileRenamePolicy()
				);
			
			String name = multi.getParameter("name");
			String pictureUrl = multi.getFilesystemName("pictureUrl");
			
			tVo.setName(name);
			tVo.setPictureUrl(pictureUrl);

			result = cDao.insertTrand(tVo);
			
		} catch(Exception e) {
			System.out.println("[상품 등록 예외 발생]: " + e);
		}
		
		if(result == 1) {
			// 상품 등록 완료 시, 상품 코드를 저장
//			session.setAttribute("code", pVo.getCode());
			System.out.println("상품 등록에 성공했습니다.");
			request.setAttribute("message", "상품 등록에 성공했습니다.");			
		} else {
			System.out.println("상품 등록에 실패했습니다.");
			request.setAttribute("message", "상품 등록에 실패했습니다.");
		}
		response.sendRedirect("commList.do");

	}
}
