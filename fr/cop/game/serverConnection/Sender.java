package fr.cop.game.serverConnection;

import java.io.IOException;
import java.io.OutputStreamWriter;

import fr.cop.common.Game;
import fr.cop.game.core.Game_Frame;

public class Sender {
	private OutputStreamWriter out;

	public Sender(OutputStreamWriter out) {
		this.out = out;
	}

	public void send(String command) {

		try {
			out.write(command + "\n");
			out.flush();
			Game_Frame.logger.logTxt("<Sender:Send>", command);
		} catch (IOException e) {
			Game.logger.logTxt("<Sender:Error>", "Client non connecté....");
		}
	}
}
