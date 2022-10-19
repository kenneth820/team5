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
		BoardDao bDao = BoardDao.getInstance();
		BoardVo bVo = new BoardVo();
//		
		int page = 1;
		String t_page = request.getParameter("p") ;
		
		if(t_page != null && !t_page.equals("")) {
			page = Integer.parseInt(t_page);
		}
		
		String keyword = "";
		
		String t_keyword = request.getParameter("k");
		
		if(t_keyword != null && !t_keyword.equals("")) {
			keyword = t_keyword;
		}
		
//		// 게시판 등록 데이터 처리
//		bVo.setName("작성자1");
//		bVo.setEmail("wrtier1@naver.com");
//		
//		bDao.insertBoard(bVo);
//		
//		// DB 데이터 조회
//		List<BoardVo> boardList = bDao.selectAllBoards();	// 모든 데이터 표시
		List<BoardVo> boardList = bDao.getBoardList(keyword, page); 		// 하나의 페이지에 표시할 데이터
		int count = bDao.getBoardCount(keyword);		// 게시글 수
		request.setAttribute("count", count);
		request.setAttribute("boardList", boardList);
		
		
		// 페이지 이동
		String url = "board/boardList.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
