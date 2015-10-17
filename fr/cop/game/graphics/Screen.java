package fr.cop.game.graphics;

import java.awt.Color;
import java.util.Random;

import fr.cop.game.core.Conflict_Of_Pixels_Client;

public class Screen {

	private int width, height; // Taille de l'écran
	public int[] pixels; // Pixels de l'image = écran.

	private static final int MAP_SIZE = 8; // Taille de la map.
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // Tiles (carrés) de
														// l'images.
	@SuppressWarnings("unused") // Temporaire
	private Random rand = new Random(); // Instance de random.

	int test = 0; /* Temporaire */

	public Screen(int witdh, int height) { // Objet Screen.
		this.width = witdh; // On établit la largeur...
		this.height = height; // ... et la hauteur de l'image.
		pixels = new int[witdh * height]; // On créée notre tableau de pixels.
	}

	public void clear() { // Methode pour vider l'écran.
		for (int i = 0; i < pixels.length; i++) { // Pour chaque pixel...
			pixels[i] = 0; // ... on met la couleur 0 (=noir).
		}
	}

	public void render(int xOffset, int yOffset) { // Fonction de rendu.
		for (int y = 0; y < height; y++) { // Pour chaque pixel en hauteur.
			int yp = y + yOffset; // permet de faire un décalage, oas encore
									// utilisé.
			if (yp < 0 || yp >= height)
				continue;
			for (int x = 0; x < width; x++) { // Pour chaque pixel en largeur.
				int xp = x + xOffset; // permet de faire un décalage, pas
										// encore pleinement utilisé.
				if (xp < 0 || xp >= width)
					continue;
				try { // On essaie de :
					pixels[xp + yp * width] = Conflict_Of_Pixels_Client.MAP
							.getSpriteAt(x, y, Conflict_Of_Pixels_Client.width, Conflict_Of_Pixels_Client.height)
							.getPixelValue(x % 16, y % 16);

				} catch (Exception e) {
					pixels[xp+yp*width] = new Color(125, 125, 255).getRGB();
				}
			}
		}
		/* Temporaire */
		test++; // Incremente test, pour animation.
	}
}
