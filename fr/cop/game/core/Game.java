package fr.cop.game.core;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import fr.cop.common.Level;
import fr.cop.common.entities.BaseEntity;
import fr.cop.common.logger.SimpleLog;

public class Game {

	public static File GAME_FOLDER;
	private Vector<BaseEntity> entities = new Vector<BaseEntity>(); // Contient
																	// toutes
																	// les
																	// entit�es
																	// du jeu.
	private Timer timer;
	private int time;
	private final Level lvl;
	public static final SimpleLog LOGGER = new SimpleLog("");

	public Game() {
		lvl = new Level("map", 50); // Cr�ation de notre map, de param�tre son
									// nom et sa taille.
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				time++;
			}
		}, 1000, 1000);
	}

	public Vector<BaseEntity> getEntities() {
		return this.entities;
	}

	public String formatedTime() {
		int hour = time % 3600;
		int min = (time - hour * 3600) % 60;
		int sec = time - min * 60;
		if (hour >= 1)
			return (hour + ":" + min + ":" + sec);
		else
			return (min + ":" + sec);
	}

	public Level getMap() {
		return lvl;
	}

	public String getPath() {
		return GAME_FOLDER.getPath();
	}
}
