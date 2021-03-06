package fr.cop.game.graphics;

import fr.cop.game.core.Game_Frame;
import fr.cop.game.graphics.sprites.SpritesList;

public class Screen {

	public int width; // Taille de l'�cran
	public int height;
	public int[][] pixels; // Pixels de l'image = �cran.

	int internalTimer = 0; // Permet d'afficher des animations

	public Screen(int witdh, int height) { // Objet Screen.
		this.width = witdh; // On �tablit la largeur...
		this.height = height; // ... et la hauteur de l'image.
		pixels = new int[witdh][height]; // On cr��e notre tableau de pixels.
		Game_Frame.logger.logTxt("Screen", this.toString());
	}

	public void clear(int defaultValue) { // Methode pour vider l'�cran.
		for (int x = 0; x < pixels.length; x++) { // Pour chaque pixel...
			for (int y = 0; y < pixels[x].length; y++) {
				pixels[x][y] = defaultValue; // ... on met du noir.
			}
		}
	}

	public void render(int xOffset, int yOffset) { // M�thode de rendu.
		int renderedMapSize = Game_Frame.GAME.serverGame.getMap().getSize() * 16; // Taille de la map Apr�s avoir �t� rendue (chaque tile vaut 16 pixels).

		for (int y = 0; y < renderedMapSize; y++) { // Pour chaque pixel en hauteur.
			int yp = y + yOffset; // On applique le d�calage en abscisse.
			if (yp < 0 || yp >= renderedMapSize) continue; // Si on sort de la fen�tre, on arrete le rendu. ==> Optimisation.
			for (int x = 0; x < renderedMapSize; x++) { // Pour chaque pixel en largeur. 
				int xp = x + xOffset; // On applique le d�calage en ordonn�e.
				if (xp < 0 || xp >= renderedMapSize) continue; // Si on sort de la fen�tre, on arr�te le rendu. ==> Optimisation.
				try { // On essaie de :
					if (Game_Frame.GAME.isGameAnimated) pixels[xp][yp] = SpritesList.getAnimatedSprite(Game_Frame.GAME.serverGame.getMap().getSpriteCodeAt(x, y), (internalTimer / 2) % 16).getPixelAt(x % 16, y % 16);
					else pixels[xp][yp] = SpritesList.getSprite(Game_Frame.GAME.serverGame.getMap().getSpriteCodeAt(x, y)).getPixelAt(x % 16, y % 16);// faire le rendu de la map.

				} catch (Exception e) {// Si il y a une erreur...
					// On fait rien... xDDDD
				}
			}
		}
//		try {
//			Champion c = Game_Frame.GAME.serverGame.getChampion(0);
//			Sprite s = Sprites.getSpriteFromID(c.getSpriteInformation());
//
//			for (int j = 0; j < 16; j++) {
//				for (int j2 = 0; j2 < 16; j2++) {
//					pixels[c.getServerPosX()/4 + j][c.getServerPosY()/4 + j2] = s.getPixelValue(j % 16, j2 % 16);
//				}
//			}
//		} catch (Exception e) {
//
//		}
	}

	public void increaseTimer() { // M�thode pour incr�menter le timer utilis� pour les animations.
		internalTimer++; // On incr�mente le timer de 1.
		if (internalTimer > 16 * 20) internalTimer = 0; // Si le timer d�passe les 320 actualisations, on le remet � 0 (Faire varier le 20 pour changer la vitesse d'animation).
	}

	@Override
	public String toString() {
		return " - Base : [Width=" + width + " | Height=" + height + "] \r          - Upscaled : [Width=" + Game_Frame.size.width + " | Height=" + Game_Frame.size.height + "]";
	}

}
