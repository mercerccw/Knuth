package commands;

import javax.servlet.http.*;


public class CommandStatistics extends Command {

	
	public String getOrderName() {
		return "statistics";
	}

	public Action executeAction(HttpServletRequest req) {
		int bookCount = dao.bookCount();
		double totalPrice = dao.totalPrice();
		
		double averagePrice = totalPrice / bookCount;
		
		req.setAttribute("count", bookCount);
		req.setAttribute("averagePrice", averagePrice);
		
		return new Action("Statistics.jsp", false);
	}

}
