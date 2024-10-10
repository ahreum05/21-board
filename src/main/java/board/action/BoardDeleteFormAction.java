package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.BoardBean;
import board.dao.BoardDAO;

public class BoardDeleteFormAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
        request.setAttribute("req_page", "/board/boardDelete.jsp");
		
		return "index.jsp";
		//return "/board/boardDelete.jsp";
	}

}
