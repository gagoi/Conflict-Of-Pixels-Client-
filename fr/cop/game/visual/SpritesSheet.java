package fr.cop.game.visual;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpritesSheet {
	private String path;
	final int SIZE;
	public int[] pixels;
	
	
	public static SpritesSheet testSheet = new SpritesSheet("spritesTest.png", 256);

	public SpritesSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		loadImageFile();

	}
	
	private void loadImageFile() {
		try {
			BufferedImage img = ImageIO.read(getClass().getResource("/fr/cop/resources/textures/" + path));
			int w = img.getWidth(), h = img.getHeight();
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
