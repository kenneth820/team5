package com.rkrua.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rkrua.dao.BoardDao;
import com.rkrua.dto.BoardVo;


@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BoardDao bDao = BoardDao.getInstance();
//		BoardVo bVo = new BoardVo();
//		
//		// �Խ��� ��� ������ ó��
//		bVo.setName("�ۼ���1");
//		bVo.setEmail("wrtier1@naver.com");
//		
//		bDao.insertBoard(bVo);
//		
//		// DB ������ ��ȸ
//		List<BoardVo> boardList = bDao.selectAllBoards();
//		request.setAttribute("boardList", boardList);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
