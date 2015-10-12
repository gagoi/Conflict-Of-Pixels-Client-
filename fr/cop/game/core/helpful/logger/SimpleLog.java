package fr.cop.game.core.helpful.logger;

public class SimpleLog {
	
	public void logTxt(String tag, String message){
		System.out.println(tag + " : " + message);
	}
	
	public void logErr(String tag, String message){
		System.err.println(tag + " : " + message);
	}
}
