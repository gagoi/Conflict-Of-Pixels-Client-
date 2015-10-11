package fr.cop.game.visual;

import java.util.Random;

public class Screen {

	private int width, height; // Taille de l'écran
	public int[] pixels; // Pixels de l'image = écran.

	private static final int MAP_SIZE = 64; // Taille de la map.
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // Tiles (carrés) de l'images.
	private Random rand = new Random(); // Instance de random.

	int test = 0; /*Temporaire*/

	public Screen(int witdh, int height) { // Objet Screen.
		this.width = witdh; // On établit la largeur...
		this.height = height; // ... et la hauteur de l'image.
		pixels = new int[witdh * height]; // On créée notre tableau de pixels.

		
		/*Temporaire*/
		for (int i = 0; i < tiles.length; i++) { // On met une couleur random, à chacun de nos tiles.
			tiles[i] = rand.nextInt(0xff_ff_ff);
		}
		tiles[0] = 0;
		tiles[1] = 0;
		tiles[MAP_SIZE] = 0;
		tiles[MAP_SIZE+1] = 0;
	}

	public void clear() { // Methode pour vider l'écran.
		for (int i = 0; i < pixels.length; i++) { // Pour chaque pixel...
			pixels[i] = 0; // ... on met la couleur 0 (=noir).
		}
	}

	public void render(int xOffset, int yOffset) { // Fonction de rendu.
		for (int y = 0; y < height; y++) { // Pour chaque pixel en hauteur.
			int yy = y + yOffset; // permet de faire un décalage, oas encore utilisé.
			for (int x = 0; x < width; x++) { // Pour chaque pixel en largeur.
				int xx = x + xOffset; // permet de faire un décalage, pas encore pleinement utilisé.
				try { // On essaie de :
					int tileIndex = ((xx / 4) & MAP_SIZE-1) + ((yy / 4) & MAP_SIZE-1) * MAP_SIZE; // récupérer l'id du tile sur la map.
					pixels[x + y * width] = tiles[tileIndex]; // Et on met la couleur du pixel en fonction de celle du tile.
				} catch (Exception e) {
				}
			}
		}

		/*Temporaire*/
		test++; //Incremente test, pour animation.
	}
}
