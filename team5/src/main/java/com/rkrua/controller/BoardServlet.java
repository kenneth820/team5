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
//		// 게시판 등록 데이터 처리
//		bVo.setName("작성자1");
//		bVo.setEmail("wrtier1@naver.com");
//		
//		bDao.insertBoard(bVo);
//		
//		// DB 데이터 조회
//		List<BoardVo> boardList = bDao.selectAllBoards();
//		request.setAttribute("boardList", boardList);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
