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
		
		MemberDao mDao = MemberDao.getInstance();		// �����ͺ��̽� ����
		MemberVo mVo = mDao.getMember(userId);	// �����ͺ��̽��κ��� ȸ������ �ε�
		
//		request.setAttribute("name", mVo.getName());
		request.setAttribute("mVo", mVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/updateMember.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����ͺ��̽��� ������ ������ ������Ʈ
		

		
		MemberDao mDao = MemberDao.getInstance();			// �����ͺ��̽� ����
		MemberVo mVo = new MemberVo();
		
		// ��ǰ ���� �ڵ� : �����ͺ��̽����� ��ǰ ����
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		HttpSession session = request.getSession(); // ���� ��ü ȣ��
		mVo = (MemberVo)session.getAttribute("loginUser");
		
		// ���� ���ε� �ڵ� �ۼ�	
		int result = -1;
		String savePath= "profilePhoto";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";

//		System.out.println(uploadFilePath);
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,							// request ��ü
					uploadFilePath,						// �������� ���� ���� ���
					uploadFileSizeLimit,				// �ִ� ���ε� ���� ũ��
					encType,							// ���ڵ� ���
					new DefaultFileRenamePolicy()		// ��å: ���� �̸��� �ٸ� �̸����� �߰�
					);

			String name = multi.getParameter("name");
			String userid = multi.getParameter("userid");
			String pwd = multi.getParameter("pwd");
			String email = multi.getParameter("email");
			String pictureurl = multi.getFilesystemName("pictureurl");
			String phone = multi.getParameter("phone");
			int admin = Integer.parseInt(multi.getParameter("admin"));
			
			mVo.setUserid(userid);		// �Էµ� ��ǰ ���� Vo�� ����
			mVo.setName(name);
			mVo.setPwd(pwd);
			mVo.setEmail(email);
			mVo.setPictureurl(pictureurl);
			mVo.setPhone(phone);
		} catch(Exception e) {
			System.out.println("���� ���ε尣 ���� �߻�: " + e);
		}
		
		// �����ͺ��̽��κ��� �ش� �ڵ��� ���� ����
		result = mDao.updateMember(mVo);
		
			// ��ǰ ��� �Ϸ��, �޽��� ���
			if(result==1) {
				System.out.println("ȸ������ ������ ����");
			} else {
				System.out.println("ȸ������ ������ ����");	
			}		
		
		// ���� �� ��� �������� �̵�
			if(mVo.getAdmin()==1) {
				response.sendRedirect("memberList.do");				
			} else {
				response.sendRedirect("main.jsp");
			}
	}
	}


