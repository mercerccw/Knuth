package commands;

import dao.BookDAO;
import javax.servlet.http.HttpServletRequest;

public abstract class Command {

		protected BookDAO dao;
		
		public void setDAO ( BookDAO dao) {
			this.dao = dao;
		}
		
		public abstract String getOrderName();
		
		public abstract Action executeAction(HttpServletRequest req);
		
}


