package commands;

import javax.servlet.http.HttpServletRequest;

import commands.Action;
import commands.Command;

public class CommandSave extends Command {

	@Override
	public String getOrderName() {
		return "save";
	}

	@Override
	public Action executeAction(HttpServletRequest req) {
		try {
			String edition = req.getParameter("edition");
			int id = new Integer( req.getParameter("id")).intValue();

			String title = req.getParameter("title");
			if (title == null || title.isEmpty()) title = "--";
			
			String desc = req.getParameter("description");
			if (desc == null || desc.isEmpty()) desc = "--";

			String price = req.getParameter("price");
			if (price == null || price.isEmpty()) price = "0.00";
			
			String author = req.getParameter("author");
			if (author == null || author.isEmpty()) author = "--";
			
			if (edition.equals("false")) {
				dao.add(id, title, desc, author, price);
			} else {
				System.out.println("Prepare to modify");
				dao.modify( id, title, desc, author, price);
			}
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Exception during edit operation");
		}
		return new Action("listbooks", false);
	}

}
