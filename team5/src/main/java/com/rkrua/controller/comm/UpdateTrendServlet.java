package com.rkrua.controller.comm;

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

@WebServlet("/updateTrend.do")
public class UpdateTrendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		// 상품 수정 링크 클릭시 수정할 상품 정보를 표시
		CommunityDao tDao = CommunityDao.getInstance();
		TrendVo tVo = new TrendVo();
		
		// 데이터 베이스에서 수정할 정보 확인
		tVo = tDao.selectTrendByNum(num);
		
		request.setAttribute("trend", tVo);
		
		// 페이지 이동 : 수정 페이지
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("community/updateTrend.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		// post 諛⑹떇 �븳湲�泥섎━
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(); // 세션 객체 호출
		MemberVo mVo = (MemberVo)session.getAttribute("loginUser");
		
		CommunityDao cDao = CommunityDao.getInstance();
		TrendVo tVo = new TrendVo();
		
		int result = -1;
		// �뙆�씪 �뾽濡쒕뱶 愿��젴 �젙蹂�
		String savePath = "trend";
		int uploadFileSizeLimit = 5 * 1024 * 1024;	// �뙆�씪 理쒕� �뾽濡쒕뱶 �겕湲�(5M)
		String encType = "UTF-8";					// �씤肄붾뵫 諛⑹떇
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("�꽌踰꾩긽�쓽 �떎�젣 �뵒�젆�넗由�: ");
		System.out.println(uploadFilePath);
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					uploadFileSizeLimit,
					encType,
					new DefaultFileRenamePolicy()
				);
			
			int num = Integer.parseInt(multi.getParameter("num"));
			String title = multi.getParameter("title");
			String pictureUrl = multi.getFilesystemName("pictureUrl");
			String text = multi.getParameter("content");
			System.out.println("text:"+text);
			
			tVo.setUserid(mVo.getUserid());
			tVo.setTitle(title);
			tVo.setPictureUrl(pictureUrl);
			tVo.setText(text);
			tVo.setNum(num);
			System.out.println("text:"+tVo);
			result = cDao.updateTrend(tVo);
			
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
