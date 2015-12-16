package fr.cop.game.serverConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import fr.cop.common.Game;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.serverConnection.commands.CommandsList;
import fr.cop.game.serverConnection.commands.MainCommand;

public class ServerListener implements Runnable {
	private Socket socket;
	private String ip;
	private int port = 163;
	private boolean op = true;
	private boolean isConnected;

	public ServerListener(String ip) {
		this.ip = ip;
		try {
			socket = new Socket(ip, 163);
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
			Game.logger.logErr("<SERVER:ERROR>", "Disconnected");
		} 
	}

	public void send(String command) {
		System.out.println("Try Sending to send : " + command);
	
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(command + "\n");
			bw.flush();
			Game_Frame.logger.logTxt("<Sender:Send>", command);
		} catch (IOException e) {
			Game.logger.logTxt("<Sender:Error>", "Serveur non connecté....");
		}
	}
}
