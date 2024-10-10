package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.BoardBean;
import board.dao.BoardDAO;

public class BoardReplyFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 데이터 처리
		int pg  = Integer.parseInt(request.getParameter("pg"));
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		// db : 원글 데이터 필요
		BoardDAO dao  = new BoardDAO();
		BoardBean article = dao.boardDetail(board_num);
		System.out.println("article= " + article.toString());
		// 2. 데이터 공유
		request.setAttribute("pg", pg);
		request.setAttribute("article", article);
		
		// 3. view 처리 파일명 리턴
		request.setAttribute("req_page", "/board/boardReply.jsp");
					
		return "index.jsp";
		//return "/board/boardReply.jsp";
	}

}
