package fr.cop.game.graphics.sprites;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class BaseSprite {

	private final int width, height; // Taille du sprite.
	private final int x, y; // Position de son origine dans la spritesheet.
	private static final int baseSize = 16;
	private final SpriteSheet sheet; // Feuille sur laquelle on doit prendre le sprite.
	private final String id; // Permet de récupérer le sprite via une simple chaine de caractère.
	private final int[][] pixels;
	private Image img;
	/*
	 * Créer un sprite de base, en 16*16.
	 */
	public BaseSprite(int x, int y, String id, SpriteSheet sheet) {
		this.x = x * baseSize;
		this.y = y * baseSize;
		this.width = baseSize;
		this.height = baseSize;
		this.pixels = new int[this.height][this.width];
		this.id = id;
		this.sheet = sheet;
		loadSprite();
	}

	/*
	 * Créer un sprite de taille width/height.
	 */
	public BaseSprite(int x, int y, int width, int height, String id, SpriteSheet sheet) {
		this.x = x * baseSize;
		this.y = y * baseSize;
		this.width = width;
		this.height = height;
		this.pixels = new int[this.height][this.width];
		this.id = id;
		this.sheet = sheet;
		loadSprite();
	}

	private void loadSprite() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				pixels[y][x] = this.sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

	public Image getImage() {
		if (img == null) {
			BufferedImage bf = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
			int[] imgPixels = ((DataBufferInt) bf.getRaster().getDataBuffer()).getData();
			for (int y = 0; y < this.height; y++) {
				for (int x = 0; x < this.width; x++) {
					imgPixels[x+y*this.width] = this.sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
				}
			}
			img = bf;
		}
		return img;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getPixelAt(int x, int y){
		return this.pixels[y][x];
	}
	
	public String getId(){
		return this.id; 
	}
}
