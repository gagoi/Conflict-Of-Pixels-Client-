package fr.cop.game.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.cop.game.graphics.Sprite;
import fr.cop.game.graphics.Sprites;

public class Level {

	private final String PATH;
	private final int SIZE;
	private final Sprite[] tiles;
	private String map;

	public Level(String path, int size) {
		SIZE = size;
		PATH = path;
		tiles = new Sprite[SIZE * SIZE];
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
				System.out.println(Sprites.getSprites(map.charAt(x + y * SIZE)).getId());
				tiles[x + y * SIZE] = Sprites.getSprites(map.charAt(x + y * SIZE));
			}
		}
	}

	public Sprite getSpriteAt(int x, int y, int width, int height) {
		int xMap = (x*SIZE)/width;
		int yMap = (y*SIZE)/height;
		return tiles[xMap + yMap * SIZE];
	}
}
