package commands;

import javax.servlet.http.*;
import beans.Book;


public class CommandEdit extends Command {

	
	public String getOrderName() {
		return "edit";
	}

	public Action executeAction(HttpServletRequest req) {
		String idStr = req.getParameter("id");
		if (idStr!=null && !idStr.isEmpty()) {
			int id = new Integer(idStr).intValue();
			Book book = dao.find(id);
			req.setAttribute("book", book);
		}
		
		return new Action("BookForm.jsp", false);
	}

}
