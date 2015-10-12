package fr.cop.game.core;

import java.io.File;

public class Level {

	File f;
	
	public Level(String path) {
		f = new File("/fr/cop/ressources/maps/" + path + ".txt");
	}

}
