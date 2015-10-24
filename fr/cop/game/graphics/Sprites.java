package fr.cop.game.graphics;

public class Sprites {

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.testSheet, "grass", 'A');
	public static Sprite debug = new Sprite(16, 1, 0, SpriteSheet.testSheet, "debug", 'B');
	
	public static AnimatedSprite grass_animated = new AnimatedSprite(16, 0, 16, SpriteSheet.animatedSheet, "grass", 'A');
	public static AnimatedSprite water_animated = new AnimatedSprite(16, 1, 2, SpriteSheet.animatedSheet, "water", 'W');

	public static Sprite[] sprites = {grass, debug};
	public static AnimatedSprite[] animatedSprites = {grass_animated, water_animated};
	
	public static Sprite getSprite(char code){
		for (int i = 0; i < sprites.length; i++) {
			if(sprites[i].getCode() == code) return sprites[i];
		}
		return debug;
	}

	public static Sprite getAnimatedSprite(char code, int time){
		for (int i = 0; i < animatedSprites.length; i++) {
			if(animatedSprites[i].getCode() == code) {
				return animatedSprites[i].getSprite(time%animatedSprites[i].getNbImage());
			}
		}
		return debug;
	}
}
