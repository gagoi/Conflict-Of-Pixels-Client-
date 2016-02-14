package fr.cop.game.graphics.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.cop.common.Game;
import fr.cop.game.core.Game_Frame;

public class BaseSheet {
	private final String path; // Chemin de l'image.
	private final int width; // Largeur de l'image.
	private final int height; // Hauteur de l'image.
	private int pixels[]; // Tableau contenant chaque pixel (son code couleur).

	/*
	 * Contructeut permettant la création d'une feuille de sprite rectangulaire.
	 * 
	 * @param path : "/fr/cop/resources/textures/<path>
	 * 
	 * @param width : Largeur de l'image en "sprite".
	 * 
	 * @param height : Hauteur de l'image en "sprite".
	 * 
	 */
	public BaseSheet(String path, int width, int height) {
		this.width = width * 16; // On définit la largeur de l'image.
		this.height = height * 16; // On définit la hauteur de l'image.
		this.path = path; // On définit  son chemin d'accès.
		this.pixels = new int[this.height * this.width]; // On créé le tableau de pixel à la bonne taille.
		loadImageFile(); // On charge l'image.
	}

	/*
	 * Contructeut permettant la création d'une feuille de sprite carrée.
	 * 
	 * @param path : "/fr/cop/resources/textures/<path>
	 * 
	 * @param size : Taille de l'image en "sprite".
	 * 
	 */
	public BaseSheet(String path, int size) {
		this.width = size * 16; // On définit la largeur de l'image.
		this.height = size * 16; // On définit la hauteur de l'image.
		this.path = path; // On définit  son chemin d'accès.
		this.pixels = new int[this.height * this.width]; // On créé le tableau de pixel à la bonne taille.
		loadImageFile(); // On charge l'image.
	}

	private void loadImageFile() {
		try {
			BufferedImage img = ImageIO.read(getClass().getResource("/fr/cop/resources/textures/" + path));
			img.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (Exception e) {
			Game_Frame.logger.logErr("<BaseSheet:" + path + ">", "Error while loading...");
		}
	}

	public int getPixel(int i) {
		try {
			return this.pixels[i];
		} catch (Exception e) {
		}
		return 0;
	}

	public int getWidth() {
		return this.width;
	}

}
