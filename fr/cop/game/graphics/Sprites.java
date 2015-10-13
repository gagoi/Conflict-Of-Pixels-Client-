package fr.cop.game.graphics;

public class Sprites {

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.testSheet, "grass", 'A');
	public static Sprite debug = new Sprite(16, 1, 0, SpriteSheet.testSheet, "debug", 'B');

	public static Sprite[] sprites = {grass, debug};
	
	public static Sprite getSprites(char code){
		for (int i = 0; i < sprites.length; i++) {
			if(sprites[i].getCode() == code) return sprites[i];
		}
		return null;
	}

}
