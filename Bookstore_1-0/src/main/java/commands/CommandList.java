package commands;

import beans.Book;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class CommandList extends Command {

	@Override
	public String getOrderName() {
		return "listbooks";
	}

	@Override
	public Action executeAction(HttpServletRequest req) {
		String field = req.getParameter("field");
		String sort = req.getParameter("sort");
		
		List<Book> books = null;
		
		if ("asc".equals(sort) && "title".equals(field)) {
			books = dao.listByTitleAsc();
		}
		else if ("desc".equals(sort) && "title".equals(field)) {
			books = dao.listByTitleDesc();
		}
		else if ("asc".equals(sort) && "price".equals(field)) {
			books = dao.listByPriceAsc();
		}
		else if ("desc".equals(sort) && "price".equals(field)) {
			books = dao.listByPriceDesc();
		}
		else if ("asc".equals(sort) && "author".equals(field)) {
			books = dao.listByAuthorAsc();
		}
		else if ("desc".equals(sort) && "author".equals(field)) {
			books = dao.listByAuthorDesc();
		}
		else if ("asc".equals(sort) && "id".equals(field)) {
			books = dao.listByIdAsc();
		}
		else if ("desc".equals(sort) && "id".equals(field)) {
			books = dao.listByIdDesc();
		} else {
			books = dao.list();
		}
		req.setAttribute("books", books);
		
		return new Action("ListBooks.jsp", false);
	}

}
