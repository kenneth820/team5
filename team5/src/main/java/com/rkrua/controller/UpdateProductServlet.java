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
		// ������Ʈ������ ���޹��� code ȹ��
		String code = request.getParameter("code");
		
		// ��ǰ ���� ��ũ Ŭ���� ������ ��ǰ ������ ǥ��
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		// ������ ���̽����� ������ ���� Ȯ��
		pVo = pDao.selectProductByCode(code);
		
		request.setAttribute("product", pVo);
		
		// ������ �̵� : ���� ������
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("product/updateProduct.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ǰ ���� �ڵ� : �����ͺ��̽����� ��ǰ ����
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		// ���� ���ε� �ڵ� �ۼ�	
		int result = -1;
		String savePath= "upload";
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

			int code = Integer.parseInt(multi.getParameter("code"));
			String name = multi.getParameter("name");
			int price = Integer.parseInt(multi.getParameter("price"));
			int category = Integer.parseInt(multi.getParameter("category"));
			String pictureurl = multi.getFilesystemName("pictureurl");			
			
			pVo.setCode(code);		// �Էµ� ��ǰ ���� Vo�� ����
			pVo.setName(name);
			pVo.setPrice(price);
			pVo.setCategory(category);
			pVo.setPictureurl(pictureurl);
		} catch(Exception e) {
			System.out.println("���� ���ε尣 ���� �߻�: " + e);
		}
		
		// �����ͺ��̽��κ��� �ش� �ڵ��� ���� ����
		result = pDao.updateProduct(pVo);
		
			// ��ǰ ��� �Ϸ��, �޽��� ���
			if(result==1) {
				System.out.println("��ǰ ������ ����");
			} else {
				System.out.println("��ǰ ������ ����");	
			}		
		
		// ���� �� ��� �������� �̵�
		response.sendRedirect("productList.do");
	}

}
