package fr.cop.game.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.print.attribute.Size2DSyntax;

import fr.cop.game.graphics.Sprite;
import fr.cop.game.graphics.Sprites;

public class Level {

	private final String PATH;
	private final int SIZE;
	private final char[] tiles;
	private String map;

	public Level(String path, int size) {
		SIZE = size;
		PATH = path;
		tiles = new char[SIZE * SIZE];
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream("/fr/cop/resources/maps/" + PATH + ".txt")));
			String line = " ";
			while ((line = br.readLine()) != null) {
				if (map == null)
					map = line;
				else
					map += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadMap();
	}

	private void loadMap() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				tiles[x + y * SIZE] = (char) map.charAt(x + y * SIZE);
			}
		}
	}

	public Sprite getSpriteAt(int x, int y, int time, boolean isAnimated) {
		int xMap = x/16;
		int yMap = y/16;
		if(x > Conflict_Of_Pixels_Client.MAP.SIZE*16-1) return null;
		if(isAnimated) return Sprites.getAnimatedSprite(tiles[xMap+yMap*SIZE], time);
		else return Sprites.getSprite(tiles[xMap + yMap*SIZE]);
	}

	public int getSize() {
		return SIZE;
	}
}
