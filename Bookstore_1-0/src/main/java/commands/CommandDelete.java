package commands;

import javax.servlet.http.HttpServletRequest;

import commands.Action;
import commands.Command;

public class CommandDelete extends Command {

	@Override
	public String getOrderName() {
		return "delete";
	}

	@Override
	public Action executeAction(HttpServletRequest req) {
		String idStr = req.getParameter("id");
		dao.delete(new Integer(idStr).intValue());
		
		return new Action("listbooks", false);
	}

}
