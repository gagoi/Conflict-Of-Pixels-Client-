package fr.cop.game.graphics;

public class AnimatedSprite {
	final int SIZE;
	private int x;
	private SpriteSheet spritesSheet;
	private Sprite[] sprites;
	private String id;
	private char code;
	private int nbImages;

	public AnimatedSprite(int size, int x, int nbImages, SpriteSheet spritesSheet, String id, char code) {
		SIZE = size;
		this.x =x;
		this.nbImages = nbImages;
		this.spritesSheet = spritesSheet;
		this.sprites = new Sprite[nbImages];
		this.id = id;
		this.code = code;
		loadSprites();
	}

	private void loadSprites() {
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new Sprite(16, x, i, spritesSheet);
		}
	}

	public Sprite getSprite(int imageId) {
		return sprites[imageId];
	}

	public String getId() {
		return id;
	}

	public char getCode() {
		return code;
	}

	public int getNbImage() {
		return this.nbImages;
	}
}
