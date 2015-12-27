package fr.cop.game.graphics.sprites;

public class AnimatedSprite {
	private final int width, height; // Taille des sprites en "sprites".
	private static final int baseSize = 16; // Taille de base d'un sprite.
	private final int y; // Position de la ligne de sprites dans la feuille.
	private final BaseSheet sheet; // Feuille de sprite.
	private final BaseSprite[] sprites; //Tableau contenant tous les sprites.
	private final String id; // Permet de récupérer le sprite via une simple chaine de caractère.
	private final char code; // Code du sprite (utilisé pour la map).
	private final int nbImages; // Nombre de sprites qui constituent le sprite animé.

	public AnimatedSprite(int yPosition, int nbImages, String id, char code, BaseSheet sheet) {
		this.width = baseSize;
		this.height = baseSize;
		this.y = yPosition * baseSize;
		this.nbImages = nbImages;
		this.sheet = sheet;
		this.sprites = new BaseSprite[nbImages];
		this.id = id;
		this.code = code;
		loadSprites();
	}

	public AnimatedSprite(int yPosition, int nbImages, String id, BaseSheet sheet) {
		this.width = baseSize;
		this.height = baseSize;
		this.y = yPosition * baseSize;
		this.nbImages = nbImages;
		this.sheet = sheet;
		this.sprites = new BaseSprite[nbImages];
		this.id = id;
		this.code = 'Z';
		loadSprites();
	}

	public AnimatedSprite(int yPosition, int width, int height, int nbImages, String id, char code, BaseSheet sheet) {
		this.width = width * baseSize;
		this.height = height * baseSize;
		this.y = yPosition * baseSize;
		this.nbImages = nbImages;
		this.sheet = sheet;
		this.sprites = new BaseSprite[nbImages];
		this.id = id;
		this.code = code;
		loadSprites();
	}

	public AnimatedSprite(int yPosition, int width, int height, int nbImages, String id, BaseSheet sheet) {
		this.width = width * baseSize;
		this.height = height * baseSize;
		this.y = yPosition * baseSize;
		this.nbImages = nbImages;
		this.sheet = sheet;
		this.sprites = new BaseSprite[nbImages];
		this.id = id;
		this.code = 'Z';
		loadSprites();
	}

	private void loadSprites() {
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new BaseSprite(i, this.y, this.width, this.height, this.id + "_" + i, this.code, this.sheet);
		}
	}

	public BaseSprite getSprite(int imageId) {
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
