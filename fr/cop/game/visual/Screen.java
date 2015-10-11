package fr.cop.game.visual;

import java.util.Random;

public class Screen {

	private int width, height;
	public int[] pixels;

	private int mapSize = 128;
	public int[] tiles = new int[mapSize * mapSize];
	private Random rand = new Random();

	int test = 0;

	public Screen(int witdh, int height) {
		this.width = witdh;
		this.height = height;
		pixels = new int[witdh * height];

		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = rand.nextInt(0xff_ff_ff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			int yy = y;
			for (int x = 0; x < width; x++) {
				int xx = x + test;
				try {
					int tileIndex = ((xx / 4) & mapSize-1) + ((yy / 4) & mapSize-1) * mapSize;
					pixels[x + y * width] = tiles[tileIndex];
				} catch (Exception e) {
				}
			}
		}

		test++;
	}
}
