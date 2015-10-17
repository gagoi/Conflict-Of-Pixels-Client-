package fr.cop.game.graphics;

public class Sprite {
	final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet spritesSheet;
	private String id;
	private char code;

	public Sprite(int size, int x, int y, SpriteSheet spritesSheet, String id, char code) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x * size;
		this.y = y * size;
		this.spritesSheet = spritesSheet;
		this.setId(id);
		this.setCode(code);
		loadSprites();
	}

	private void loadSprites() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x+y*SIZE] = spritesSheet.pixels[(x+this.x) + (y + this.y)*spritesSheet.SIZE];
			}
		}
	}
	
	public int getPixelValue(int x, int y){
		int xPixel = 0;
		int yPixel = 0;
		return pixels[(x+y*SIZE)];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char getCode() {
		return code;
	}

	public void setCode(char code) {
		this.code = code;
	}

}
