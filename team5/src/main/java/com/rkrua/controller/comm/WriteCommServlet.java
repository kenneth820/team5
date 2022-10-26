package com.rkrua.controller.comm;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.rkrua.dao.CommunityDao;
import com.rkrua.dto.MemberVo;
import com.rkrua.dto.TrendVo;

@WebServlet("/writeComm.do")
public class WriteCommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("writeComm.do get");
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("community/writeComm.jsp");
		dispatcher.forward(request, response);		// 커뮤니티 페이지 이동
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(); // 세션 객체 호출
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		CommunityDao cDao = CommunityDao.getInstance();
		TrendVo tVo = new TrendVo();
		
		int result = -1;
		// 저장 경로 설정
		String savePath = "trend";
		int uploadFileSizeLimit = 5 * 1024 * 1024;	// 파일 크기(5M)
		String encType = "UTF-8";					// 인코딩 타입
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
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
			
			String title = multi.getParameter("title");
			String pictureUrl = multi.getFilesystemName("pictureUrl");
			String text = multi.getParameter("content");
			System.out.println("text:"+text);
			
			tVo.setUserid(mVo.getUserid());
			tVo.setTitle(title);
			tVo.setPictureUrl(pictureUrl);
			tVo.setText(text);
			System.out.println("text:"+tVo);
			result = cDao.inserttrend(tVo);
			
		} catch(Exception e) {
			System.out.println("[�긽�뭹 �벑濡� �삁�쇅 諛쒖깮]: " + e);
		}
		
		if(result == 1) {
			// �긽�뭹 �벑濡� �셿猷� �떆, �긽�뭹 肄붾뱶瑜� ���옣
//			session.setAttribute("code", pVo.getCode());
			System.out.println("상품 등록에 성공");
			request.setAttribute("message", "�긽�뭹 �벑濡앹뿉 �꽦怨듯뻽�뒿�땲�떎.");			
		} else {
			System.out.println("상품 등록에 실패.");
			request.setAttribute("message", "�긽�뭹 �벑濡앹뿉 �떎�뙣�뻽�뒿�땲�떎.");
		}
		request.getRequestDispatcher("commList.do").forward(request, response);

	}
}
