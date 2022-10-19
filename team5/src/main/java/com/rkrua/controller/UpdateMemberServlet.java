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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.rkrua.dao.MemberDao;
import com.rkrua.dao.ProductDao;
import com.rkrua.dto.MemberVo;
import com.rkrua.dto.ProductVo;

@WebServlet("/updateMember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		
		MemberDao mDao = MemberDao.getInstance();		// 데이터베이스 연동
		MemberVo mVo = mDao.getMember(userId);	// 데이터베이스로부터 회원정보 로딩
		
//		request.setAttribute("name", mVo.getName());
		request.setAttribute("mVo", mVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/updateMember.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터베이스에 수정된 정보를 업데이트
		

		
		MemberDao mDao = MemberDao.getInstance();			// 데이터베이스 연동
		MemberVo mVo = new MemberVo();
		
		// 상품 수정 코드 : 데이터베이스에서 상품 삭제
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		HttpSession session = request.getSession(); // 세션 객체 호출
		mVo = (MemberVo)session.getAttribute("loginUser");
		
		// 파일 업로드 코드 작성	
		int result = -1;
		String savePath= "profilePhoto";
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

			String name = multi.getParameter("name");
			String userid = multi.getParameter("userid");
			String pwd = multi.getParameter("pwd");
			String email = multi.getParameter("email");
			String pictureurl = multi.getFilesystemName("pictureurl");
			String phone = multi.getParameter("phone");
			int admin = Integer.parseInt(multi.getParameter("admin"));
			
			mVo.setUserid(userid);		// 입력된 상품 정보 Vo에 저장
			mVo.setName(name);
			mVo.setPwd(pwd);
			mVo.setEmail(email);
			mVo.setPictureurl(pictureurl);
			mVo.setPhone(phone);
		} catch(Exception e) {
			System.out.println("파일 업로드간 예외 발생: " + e);
		}
		
		// 데이터베이스로부터 해당 코드의 정보 수정
		result = mDao.updateMember(mVo);
		
			// 상품 등록 완료시, 메시지 출력
			if(result==1) {
				System.out.println("회원정보 수정에 성공");
			} else {
				System.out.println("회원정보 수정에 실패");	
			}		
		
		// 수정 후 목록 페이지로 이동
			if(mVo.getAdmin()==1) {
				response.sendRedirect("memberList.do");				
			} else {
				response.sendRedirect("main.jsp");
			}
	}
	}


