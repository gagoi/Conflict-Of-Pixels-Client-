package fr.cop.game.core.helpful.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.cop.game.core.Game_Frame;

public class SimpleLog {

	public File logFile = new File(Game_Frame.gameFolder, "log.txt");
	private FileOutputStream logFileOut;

	public SimpleLog() {
		if (!Game_Frame.gameFolder.exists()) Game_Frame.gameFolder.mkdirs();
		if (!logFile.exists()) try {
			logFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void logTxt(String tag, String message) {
		System.out.println(tag + " : " + message);
	}

	public void logErr(String tag, String message) {
		System.err.println(tag + " : " + message);
	}
}
