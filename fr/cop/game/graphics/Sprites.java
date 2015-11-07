package fr.cop.game.graphics;

public class Sprites {

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.testSheet, "grass", 'G');
	public static Sprite tree = new Sprite(16, 1, 0, SpriteSheet.testSheet, "tree", 'T');
	public static Sprite top_tree = new Sprite(16, 2, 0, SpriteSheet.testSheet, "top_tree", 'H');
	public static Sprite bot_tree = new Sprite(16, 2, 1, SpriteSheet.testSheet, "bot_tree", 'B');
	public static Sprite debug = new Sprite(16, 0, 1, SpriteSheet.testSheet, "debug", 'Z');

	public static AnimatedSprite grass_animated = new AnimatedSprite(16, 0, 16, SpriteSheet.animatedSheet, "grass", 'A');
	public static AnimatedSprite water_animated = new AnimatedSprite(16, 1, 2, SpriteSheet.animatedSheet, "water", 'W');
	
	
	public static Sprite physical_damage_icon = new Sprite(16, 0, 0, SpriteSheet.iconsSheet);
	public static Sprite magical_damage_icon = new Sprite(16, 1, 0, SpriteSheet.iconsSheet);
	public static Sprite physical_resistance_icon = new Sprite(16, 2, 0, SpriteSheet.iconsSheet);

	public static Sprite[] sprites = {grass, tree, top_tree, bot_tree, debug}; // Tableau contenant tous les sprites non animés.
	public static AnimatedSprite[] animatedSprites = {grass_animated, water_animated}; // Tableau contenant tous les sprites animés.

	public static Sprite getSprite(char code) { // Méthode permettant de savoir quel Sprite correspond à un code donné.
		for (int i = 0; i < sprites.length; i++) { // On parcourt le tableau contenant tous les Sprites (non animés) ...
			if (sprites[i].getCode() == code) return sprites[i]; // ... si on trouve le bon sprite, on le renvoie.
		}
		return debug; // Si la boucle s'est finie sans renvoyer de sprite, on utilise le sprite de debug.
	}

	public static Sprite getAnimatedSprite(char code, int time) { // Méthode permettant de savoir quel Sprite correspond à un code donné.
		for (int i = 0; i < animatedSprites.length; i++) { // On parcourt tous les sprites animés...
			if (animatedSprites[i].getCode() == code) return animatedSprites[i].getSprite(time % animatedSprites[i].getNbImage()); // ... si on trouvez le bon sprite, on renvoie un sprite du sprite animé en fonction du temps.
		}
		return getSprite(code); // Si la boucle se termine, c'est que l'on n'a pas trouvé de sprite animé. On cherche donc un sprite non-animé pour le remplacer.
	}
}
