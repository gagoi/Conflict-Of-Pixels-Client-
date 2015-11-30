package fr.cop.game.serverConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import fr.cop.common.commands.CommandsThread;
import fr.cop.common.commands.Sender;
import fr.cop.game.core.Game_Frame;

public class ServerListener {
	private Socket s;
	private String ip;
	private int port = 163;
	private boolean op = true;
	private Sender sender;

	public ServerListener(String ip) {
		this.ip = ip;
		try {
			this.s = new Socket(ip, port);
			sender = new Sender(new OutputStreamWriter(s.getOutputStream()));
			new CommandsThread(new BufferedReader(new InputStreamReader(s.getInputStream())));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Game_Frame.logger.logTxt("<ServerListener:Start>", "Listener created, with ip : " + ip);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isOp() {
		return op;
	}

	public Sender getSender() {
		return this.sender;
	}
}
