package fr.cop.game.graphics;

public class Sprite {
	final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet spritesSheet;

	public Sprite(int size, int x, int y, SpriteSheet spritesSheet) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x * size;
		this.y = y * size;
		this.spritesSheet = spritesSheet;
		loadSprites();
	}

	private void loadSprites() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x+y*SIZE] = spritesSheet.pixels[(x+this.x) + (y + this.y)*spritesSheet.SIZE];
			}
		}
	}

}
