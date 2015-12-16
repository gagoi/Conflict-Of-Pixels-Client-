package fr.cop.game.serverConnection.commands;

import fr.cop.common.Profil;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.serverConnection.commands.params.MainParamList;

public class Connect extends MainCommand{
	public Connect() {
		super(MainCommand.TYPE_CLIENT, "connect", MainParamList.PLAYER_UUID);	}

	@Override
	public void action(String[] splitedInput) {
		System.out.println("connect");
		Game_Frame.connectedProfil = new Profil(splitedInput[0]);
	}
}
