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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.rkrua.dao.ItemDao;
import com.rkrua.dao.MemberDao;
import com.rkrua.dto.MemberVo;

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
		HttpSession session = request.getSession(); // ���� ��ü ȣ��
		MemberVo admininfo = (MemberVo)session.getAttribute("loginUser");
		
		// ���� ���ε� �ڵ� �ۼ�	
		int result = -1;
		String savePath= "profilePhoto";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		int uploadFileSizeLimit = 8 * 1024 * 1024;
		String encType = "UTF-8";

//		System.out.println(uploadFilePath);
		
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
			String selfcomment = multi.getParameter("selfcomment");
			int admin = Integer.parseInt(multi.getParameter("admin"));
			int point = Integer.parseInt(multi.getParameter("point"));
			
			mVo.setUserid(userid);		// �Էµ� ��ǰ ���� Vo�� ����
			mVo.setName(name);
			mVo.setPwd(pwd);
			mVo.setEmail(email);
			mVo.setPictureurl(pictureurl);
			mVo.setPhone(phone);
			mVo.setSelfcomment(selfcomment);
			mVo.setAdmin(admin);
			mVo.setPoint(point);
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
			if(admininfo.getAdmin() == 1) {
				response.sendRedirect("memberList.do");				
			} else {
				response.sendRedirect("main.jsp");
			}
	}
	}


