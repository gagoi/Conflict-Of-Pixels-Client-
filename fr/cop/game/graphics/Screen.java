package fr.cop.game.graphics;

import fr.cop.game.core.Conflict_Of_Pixels_Client;

public class Screen {

	public int width; // Taille de l'écran
	public int height;
	public int[][] pixels; // Pixels de l'image = écran.

	int internalTimer = 0; // Permet d'afficher des animations

	public static Screen instance;
	
	public Screen(int witdh, int height) { // Objet Screen.
		instance = this;
		this.width = witdh; // On établit la largeur...
		this.height = height; // ... et la hauteur de l'image.
		pixels = new int[witdh][height]; // On créée notre tableau de pixels.
	}

	public void clear() { // Methode pour vider l'écran.
		for (int x = 0; x < pixels.length; x++) { // Pour chaque pixel...
			for (int y = 0; y < pixels[x].length; y++) {
				pixels[x][y] = 0;
			}
		}
	}

	public void render(int xOffset, int yOffset) { // Fonction de rendu.
		int tempHeight = height*2, tempWidth = width*2;
		for (int y = 0; y < tempHeight; y++) { // Pour chaque pixel en hauteur.
			int yp = y + yOffset; // permet de faire un décalage, oas encore
									// utilisé.
			if (yp < 0 || yp >= tempHeight)
				continue;
			for (int x = 0; x < tempWidth; x++) { // Pour chaque pixel en largeur.
				int xp = x + xOffset; // permet de faire un décalage, pas
										// encore pleinement utilisé.
				if (xp < 0 || xp >= tempWidth)
					continue;
				try { // On essaie de :
					pixels[xp][yp] = Conflict_Of_Pixels_Client.MAP.getSpriteAt(x, y, (internalTimer / 2) % 16, true)
							.getPixelValue(x % 16, y % 16);

				} catch (Exception e) {
				}
			}
		}
	}

	public void increaseTimer() {
		internalTimer += 1;
		if (internalTimer > 160 * 2)
			internalTimer = 0;
	}

}
