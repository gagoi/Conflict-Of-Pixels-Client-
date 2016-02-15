package fr.cop.game.serverConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import fr.cop.game.core.Game;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.serverConnection.commands.CommandsList;
import fr.cop.game.serverConnection.commands.MainCommand;

public class ServerListener implements Runnable {
	private Socket socket;
	private String ip;
	private int port = 1630;
	private boolean op = true;
	private boolean isConnected;

	public ServerListener(String ip) {
		this.ip = ip;
		try {
			socket = new Socket(ip, port);
			Thread t = new Thread(this, "Server Listener");
			isConnected = true;
			Game_Frame.logger.logTxt("<ServerListener:Start>", "Listener created, with ip : " + ip + ":" + socket.getPort());
			t.start();
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

	public boolean isConnected() {
		return isConnected;
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input = "";
			
			while ((input = bf.readLine()) != null) {
				Game_Frame.logger.logTxt("<INPUT>", input);
				for (MainCommand command : CommandsList.getCommands()) {
					if (command.verifyValidity(input)) {
						command.action(input.split(" "));
						break;
					}
				}
			}
		} catch (IOException e) {
			Game_Frame.logger.logErr("<SERVER:ERROR>", "Disconnected");
		} 
	}

	public void send(String command) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(command + "\n");
			bw.flush();
			Game_Frame.logger.logTxt("<SERVER:Send>", command);
		} catch (IOException e) {
			Game_Frame.logger.logTxt("<SERVER:ERROR>", "Serveur non connect�....");
		}
	}
}
