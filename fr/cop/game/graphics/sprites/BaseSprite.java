package fr.cop.game.graphics.sprites;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class BaseSprite {

	private final int width, height; // Taille du sprite.
	private final int x, y; // Position de son origine dans la spritesheet.
	private static final int baseSize = 16;
	private final BaseSheet sheet; // Feuille sur laquelle on doit prendre le sprite.
	private final String id; // Permet de récupérer le sprite via une simple chaine de caractère.
	private final int[][] pixels; // Tableau contenant tous les pixels (leur couleur).
	private final char code;// Code du sprite (utilisé pour la map).
	private Image img; // Sprite sous forme d'image.
	
	/*
	 * Créer un sprite de base, en 16*16.
	 */
	public BaseSprite(int x, int y, String id, BaseSheet sheet) {
		this.x = x * baseSize;
		this.y = y * baseSize;
		this.width = baseSize;
		this.height = baseSize;
		this.pixels = new int[this.height][this.width];
		this.id = id;
		this.sheet = sheet;
		this.code = 'Z';
		loadSprite();
	}
	
	/*
	 * Créer un sprite de base, en 16*16.
	 */
	public BaseSprite(int x, int y, BaseSheet sheet) {
		this.x = x * baseSize;
		this.y = y * baseSize;
		this.width = baseSize;
		this.height = baseSize;
		this.pixels = new int[this.height][this.width];
		this.id = "";
		this.sheet = sheet;
		this.code = 'Z';
		loadSprite();
	}
	
	/*
	 * Créer un sprite de base, en 16*16.
	 */
	public BaseSprite(int x, int y, String id, char code, BaseSheet sheet) {
		this.x = x * baseSize;
		this.y = y * baseSize;
		this.width = baseSize;
		this.height = baseSize;
		this.pixels = new int[this.height][this.width];
		this.id = id;
		this.sheet = sheet;
		this.code = code;
		loadSprite();
	}

	/*
	 * Créer un sprite de taille width/height.
	 */
	public BaseSprite(int x, int y, int width, int height, String id, BaseSheet sheet) {
		this.x = x * baseSize;
		this.y = y * baseSize;
		this.width = width;
		this.height = height;
		this.pixels = new int[this.height][this.width];
		this.id = id;
		this.sheet = sheet;
		this.code = 'Z';
		loadSprite();
	}

	/*
	 * Créer un sprite de taille width/height.
	 */
	public BaseSprite(int x, int y, int width, int height, String id, char code, BaseSheet sheet) {
		this.x = x * baseSize;
		this.y = y * baseSize;
		this.width = width;
		this.height = height;
		this.pixels = new int[this.height][this.width];
		this.id = id;
		this.sheet = sheet;
		this.code = code;
		loadSprite();
	}
	
	private void loadSprite() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				pixels[y][x] = this.sheet.getPixel((x + this.x) + (y + this.y) * sheet.getWidth());
			}
		}
	}

	public Image getImage() {
		if (img == null) {
			BufferedImage bf = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
			int[] imgPixels = ((DataBufferInt) bf.getRaster().getDataBuffer()).getData();
			for (int y = 0; y < this.height; y++) {
				for (int x = 0; x < this.width; x++) {
					imgPixels[x + y * this.width] = this.sheet.getPixel((x + this.x) + (y + this.y) * sheet.getWidth());
				}
			}
			img = bf;
		}
		return img;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getPixelAt(int x, int y) {
		return this.pixels[y][x];
	}

	public String getId() {
		return this.id;
	}
	
	public char getCode() {
		return code;
	}

}
