package fr.cop.game.serverConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import fr.cop.game.core.Game_Frame;

public class ServerListener {
	private Socket s;
	private String ip;
	private int port = 163;
	private boolean op = true;
	private Sender sender;
	private boolean isConnected;

	public ServerListener(String ip) {
		this.ip = ip;
		try {
			this.s = new Socket(ip, port);
			sender = new Sender(new OutputStreamWriter(s.getOutputStream()));
			new fr.cop.game.serverConnection.CommandsThread(new BufferedReader(new InputStreamReader(s.getInputStream())));
			isConnected = true;
			Game_Frame.logger.logTxt("<ServerListener:Start>", "Listener created, with ip : " + ip);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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

	public boolean isConnected() {
		return isConnected;
	}
}
