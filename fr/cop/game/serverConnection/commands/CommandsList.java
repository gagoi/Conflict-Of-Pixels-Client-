package fr.cop.game.serverConnection.commands;

import java.util.ArrayList;

public class CommandsList {

	private static MainCommand[] commands = new MainCommand[] {
			new Connect(),
	};

	public static MainCommand getCommandFromName(String name) {
		for (MainCommand c : commands) {
			if (c.getTotalName().equals(name)) return c;
		}
		return null;
	}

	public static MainCommand[] getCommandsOf(String type) {
		ArrayList<MainCommand> typeCom = new ArrayList<MainCommand>();
		
		for (MainCommand c : commands) {
			if(c.getType().equals(type)) typeCom.add(c);
		}
		
		return (MainCommand[]) typeCom.toArray();
	}
	
	public static MainCommand[] getCommands(){
		System.out.println("Input");
		System.out.flush();
		return commands;
	}

}
