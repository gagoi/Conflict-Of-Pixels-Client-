package fr.cop.game.graphics.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BaseSheet {
	private final String path;  // Chemin de l'image.
	private final int width; // Largeur de l'image.
	private final int height; // Hauteur de l'image.
	private int pixels[]; // Tableau contenant chaque pixel (son code couleur).
	
	/*
	 * Contructeut permettant la création d'une feuille de sprite rectangulaire.
	 * 
	 * @param path : "/fr/cop/resources/textures/<path>
	 * @param width : Largeur de l'image en "sprite".
	 * @param height : Hauteur de l'image en "sprite".
	 * 
	 */
	public BaseSheet(String path, int width, int height) {
		this.width = width*16; // On définit la largeur de l'image.
		this.height = height*16; // On définit la hauteur de l'image.
		this.path = path; // On définit  son chemin d'accès.
		this.pixels = new int[this.height*this.width]; // On créé le tableau de pixel à la bonne taille.
		loadImageFile(); // On charge l'image.
	}
	
	/*
	 * Contructeut permettant la création d'une feuille de sprite carrée.
	 * 
	 * @param path : "/fr/cop/resources/textures/<path>
	 * @param size : Taille de l'image en "sprite".
	 * 
	 */
	public BaseSheet(String path, int size) {
		this.width = size*16; // On définit la largeur de l'image.
		this.height = size*16; // On définit la hauteur de l'image.
		this.path = path; // On définit  son chemin d'accès.
		this.pixels = new int[this.height*this.width]; // On créé le tableau de pixel à la bonne taille.
		loadImageFile(); // On charge l'image.
	}	
	
	private void loadImageFile() {
		try { // On essaie de : 
			BufferedImage img = ImageIO.read(getClass().getResource("/fr/cop/resources/textures/" + path)); // Chargement de l'image.
			img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth()); // On remplit le tableau de pixel.
		} catch (IOException e) { // En cas d'échec : 
			e.printStackTrace(); // On affiche une erreur.
		}
	}

	public int getPixel(int i) {
		return this.pixels[i];
	}

	public int getWidth() {
		return this.width;
	}

}
