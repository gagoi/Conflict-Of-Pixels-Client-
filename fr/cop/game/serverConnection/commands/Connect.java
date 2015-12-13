package fr.cop.game.serverConnection.commands;

import fr.cop.game.serverConnection.commands.params.MainParamList;
import fr.cop.login.LoginApp;

public class Connect extends MainCommand{
	public Connect() {
		super(MainCommand.TYPE_CLIENT, "connect", MainParamList.PLAYER_UUID);	}

	@Override
	public void action() {
		System.out.println("connect");
		LoginApp.app.connect(getParams()[0].toString(), getParams()[1].toString());
	}
}
