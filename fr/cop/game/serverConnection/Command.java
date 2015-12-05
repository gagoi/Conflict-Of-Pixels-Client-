package fr.cop.game.serverConnection;

import fr.cop.common.Game;
import fr.cop.common.Param;
import fr.cop.login.LoginApp;

public class Command {

	private String name;
	private Param[] params;

	public static Command[] commands = { 
		new Command("client:connect ", Param.PLAYER_ID) {
			public void use() {
				super.use();
				LoginApp.app.connect(this.getParams()[0].getValue());
		};
	},

	};

	public Command(String name, Param[] params) {
		this.name = name;
		this.setParams(params);
	}

	public Command(String name) {
		this.name = name;
		this.setParams(null);
	}

	public Command(String name, Param param) {
		this.name = name;
		this.setParams(new Param[] { param });
	}

	public boolean test(String input) {
		String[] values = input.split(" ");

		if (values[0].equals(name)) {
			if (getParams() == null) return true;
			if (getParams().length == values.length - 1) {
				for (int i = 1; i < values.length; i++) {
					if (!getParams()[i - 1].test(values[i])) return false;
				}
				return true;
			}
		}
		return false;
	}

	public void use() {
		Game.logger.logTxt(name, "Command used.");
	}

	public Param[] getParams() {
		return params;
	}

	public void setParams(Param[] params) {
		this.params = params;
	}

}
