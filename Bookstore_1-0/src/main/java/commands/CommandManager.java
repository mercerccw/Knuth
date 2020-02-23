package commands;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

		protected static ConcurrentHashMap<String, Command> orders = new ConcurrentHashMap<String,Command>();
		
		static {
			init();
		}
		
		public static void init() {
			orders.put("listbooks", new CommandList());
			orders.put("edit", new CommandEdit());
			orders.put("delete", new CommandDelete());
			orders.put("save", new CommandSave());
		}

		public static Command getOrder( String name) {
			Command ord = orders.get( name );
			return ord;
		}
}
