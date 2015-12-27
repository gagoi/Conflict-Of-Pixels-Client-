package fr.cop.game.graphics.sprites;

public class SpritesList {

	/*
	 * ------------------------------------------------------------------------
	 * Feuilles de sprites
	 * ------------------------------------------------------------------------
	 */
	public static BaseSheet iconsSheet = new BaseSheet("icons_sheet.png", 9, 1); // Feuille contenant les icons du HUD.
	public static BaseSheet mapSheet = new BaseSheet("map_sheet.png", 16); // Feuille contenant les textures fixes de la map.
	public static BaseSheet animatedMapSheet = new BaseSheet("animated_map_sheet.png", 17, 2); // Feuille contenant les textures anim�es de la map.

	/*
	 * ------------------------------------------------------------------------
	 * Sprites de la map.
	 * ------------------------------------------------------------------------
	 */

	public static final BaseSprite map_grass = new BaseSprite(0, 0, "grass", 'G', mapSheet);
	public static final BaseSprite map_tree = new BaseSprite(1, 0, "tree", 'T', mapSheet);
	public static final BaseSprite map_top_tree = new BaseSprite(2, 0, "top_tree", 'H', mapSheet);
	public static final BaseSprite map_bot_tree = new BaseSprite(2, 1, "bot_tree", 'B', mapSheet);

	public static final AnimatedSprite animated_map_grass = new AnimatedSprite(0, 17, "grass", 'G', animatedMapSheet);
	public static final AnimatedSprite animated_map_water = new AnimatedSprite(1, 2, "water", 'W', animatedMapSheet);

	public static final BaseSprite debug = new BaseSprite(0, 1, "debug", 'Z', mapSheet);

	public static final BaseSprite[] map_sprites = {map_grass, map_tree, map_top_tree, map_bot_tree}; // Tableau contenant tous les sprites non animés.
	public static final AnimatedSprite[] animatedSprites = {animated_map_grass, animated_map_water}; // Tableau contenant tous les sprites animés.

	
	
	
	/*
	 * ------------------------------------------------------------------------
	 * Sprites du HUD.
	 * ------------------------------------------------------------------------
	 */
	public static final BaseSprite physical_damage_icon = new BaseSprite(0, 0, iconsSheet);
	public static final BaseSprite magical_damage_icon = new BaseSprite(1, 0, iconsSheet);
	public static final BaseSprite physical_resistance_icon = new BaseSprite(2, 0, iconsSheet);
	public static final BaseSprite magical_resistance_icon = new BaseSprite(3, 0, iconsSheet);
	public static final BaseSprite attack_speed_icon = new BaseSprite(4, 0, iconsSheet);
	public static final BaseSprite physical_penetration_icon = new BaseSprite(5, 0, iconsSheet);
	public static final BaseSprite magical_penetration_icon = new BaseSprite(6, 0, iconsSheet);
	public static final BaseSprite cooldown_reduction_icon = new BaseSprite(7, 0, iconsSheet);
	public static final BaseSprite move_speed_icon = new BaseSprite(8, 0, iconsSheet);
	
	
	
	public static BaseSprite getSprite(char code) { // Méthode permettant de savoir quel Sprite correspond à un code donné.
		for (int i = 0; i < map_sprites.length; i++) { // On parcourt le tableau contenant tous les Sprites (non animés) ...
			if (map_sprites[i].getCode() == code) return map_sprites[i]; // ... si on trouve le bon sprite, on le renvoie.
		}
		return debug; // Si la boucle s'est finie sans renvoyer de sprite, on utilise le sprite de debug.
	}

	public static BaseSprite getSpriteFromID(String id) { // Méthode permettant de savoir quel Sprite correspond à un code donné.
		for (int i = 0; i < map_sprites.length; i++) { // On parcourt le tableau contenant tous les Sprites (non animés) ...
			if (map_sprites[i].getId() == id) return map_sprites[i]; // ... si on trouve le bon sprite, on le renvoie.
		}
		return debug; // Si la boucle s'est finie sans renvoyer de sprite, on utilise le sprite de debug.
	}

	public static BaseSprite getAnimatedSprite(char code, int time) { // Méthode permettant de savoir quel Sprite correspond à un code donné.
		for (int i = 0; i < animatedSprites.length; i++) { // On parcourt tous les sprites animés...
			if (animatedSprites[i].getCode() == code) return animatedSprites[i].getSprite(time % animatedSprites[i].getNbImage()); // ... si on trouvez le bon sprite, on renvoie un sprite du sprite animé en fonction du temps.
		}
		return getSprite(code); // Si la boucle se termine, c'est que l'on n'a pas trouvé de sprite animé. On cherche donc un sprite non-animé pour le remplacer.
	}
	
	public static BaseSprite getAnimatedSpriteFromID(String id, int time) { // Méthode permettant de savoir quel Sprite correspond à un code donné.
		for (int i = 0; i < animatedSprites.length; i++) { // On parcourt tous les sprites animés...
			if (animatedSprites[i].getId() == id) return animatedSprites[i].getSprite(time % animatedSprites[i].getNbImage()); // ... si on trouvez le bon sprite, on renvoie un sprite du sprite animé en fonction du temps.
		}
		return getSpriteFromID(id); // Si la boucle se termine, c'est que l'on n'a pas trouvé de sprite animé. On cherche donc un sprite non-animé pour le remplacer.
	}
}
