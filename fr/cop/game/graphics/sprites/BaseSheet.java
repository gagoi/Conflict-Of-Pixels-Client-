package fr.cop.game.graphics.sprites;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		try { // On essaie de : 
			System.out.println("/fr/cop/resources/textures/" + path);
			BufferedImage img = ImageIO.read(getClass().getResource("/fr/cop/resources/textures/" + path)); // Chargement de l'image.
			BufferedImage a  = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			a.setData(img.getRaster());
			a.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) { // En cas d'échec : 
			e.printStackTrace(); // On affiche une erreur.
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
