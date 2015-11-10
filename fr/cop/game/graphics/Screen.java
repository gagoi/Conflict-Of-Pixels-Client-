package fr.cop.game.graphics;

import fr.cop.common.entities.champions.Champion;
import fr.cop.common.entities.spells.Spell_Base;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.graphics.sprites.Sprite;
import fr.cop.game.graphics.sprites.Sprites;

public class Screen {

	public int width; // Taille de l'écran
	public int height;
	public int[][] pixels; // Pixels de l'image = écran.

	int internalTimer = 0; // Permet d'afficher des animations

	public Screen(int witdh, int height) { // Objet Screen.
		this.width = witdh; // On établit la largeur...
		this.height = height; // ... et la hauteur de l'image.
		pixels = new int[witdh][height]; // On créée notre tableau de pixels.
	}

	public void clear(int defaultValue) { // Methode pour vider l'écran.
		for (int x = 0; x < pixels.length; x++) { // Pour chaque pixel...
			for (int y = 0; y < pixels[x].length; y++) {
				pixels[x][y] = defaultValue; // ... on met du noir.
			}
		}
	}

	public void render(int xOffset, int yOffset) { // Méthode de rendu.
		int renderedMapSize = Game_Frame.GAME.serverGame.getMap().getSize() * 16; // Taille de la map Après avoir été rendue (chaque tile vaut 16 pixels).
		for (int y = 0; y < renderedMapSize; y++) { // Pour chaque pixel en hauteur.
			int yp = y + yOffset; // On applique le décalage en abscisse.
			if (yp < 0 || yp >= renderedMapSize) continue; // Si on sort de la fenêtre, on arrete le rendu. ==> Optimisation.
			for (int x = 0; x < renderedMapSize; x++) { // Pour chaque pixel en largeur. 
				int xp = x + xOffset; // On applique le décalage en ordonnée.
				if (xp < 0 || xp >= renderedMapSize) continue; // Si on sort de la fenêtre, on arrête le rendu. ==> Optimisation.
				try { // On essaie de :
					if (Game_Frame.GAME.isGameAnimated) pixels[xp][yp] = Sprites.getAnimatedSprite(Game_Frame.GAME.serverGame.getMap().getSpriteCodeAt(x, y), (internalTimer / 2) % 16).getPixelValue(x % 16, y % 16);
					else pixels[xp][yp] = Sprites.getSprite(Game_Frame.GAME.serverGame.getMap().getSpriteCodeAt(x, y)).getPixelValue(x % 16, y % 16);// faire le rendu de la map.
				} catch (Exception e) {// Si il y a une erreur...
					// On fait rien... xDDDD
				}
			}
		}
		renderEntities(xOffset, yOffset);
	}

	public void renderEntities(int xOffset, int yOffset) { // Méthode de rendu.
		int renderedMapSize = Game_Frame.GAME.serverGame.getMap().getSize() * 16; // Taille de la map Après avoir été rendue (chaque tile vaut 16 pixels).
		for (int y = 0; y < renderedMapSize; y++) { // Pour chaque pixel en hauteur.
			int yp = y + yOffset; // On applique le décalage en abscisse.
			if (yp < 0 || yp >= renderedMapSize) continue; // Si on sort de la fenêtre, on arrete le rendu. ==> Optimisation.
			for (int x = 0; x < renderedMapSize; x++) { // Pour chaque pixel en largeur. 
				int xp = x + xOffset; // On applique le décalage en ordonnée.
				if (xp < 0 || xp >= renderedMapSize) continue; // Si on sort de la fenêtre, on arrête le rendu. ==> Optimisation.
				try { // On essaie de :
					for (int i = 0; i < Game_Frame.GAME.serverGame.getChampionsNumber(); i++) {
						Champion c = Game_Frame.GAME.serverGame.getChampion(i);
						if (c.getPosX() == x && c.getPosY() == y) {
							for (int j = 0; j < 16; j++) {
								for (int j2 = 0; j2 < 16; j2++) {
									pixels[xp + j][yp + j2] = Sprites.getSpriteFromID(c.getSpriteInformation()).getPixelValue(j % 16, j2 % 16);
								}
							}
						}
					}
					if (!Game_Frame.GAME.serverGame.getSpells().isEmpty()) {
						for (Spell_Base spell : Game_Frame.GAME.serverGame.getSpells()) {
							if (spell.getPosX() == x && spell.getPosY() == y) {
								Sprite spellSprite = Sprites.getSpriteFromID(spell.getSpriteInformation());
								for (int j = 0; j < spellSprite.pixels.length; j++) {
									pixels[xp + j % spellSprite.getSize()][yp + j / spellSprite.getSize()] = spellSprite.getPixelValue((j % spellSprite.getSize()) % 16, (j / spellSprite.getSize()) % 16);
								}
							}
						}
					}
				} catch (Exception e) {// Si il y a une erreur...
					// On fait rien... xDDDD
				}
			}
		}
	}

	public void increaseTimer() { // Méthode pour incrémenter le timer utilisé pour les animations.
		internalTimer++; // On incrémente le timer de 1.
		if (internalTimer > 16 * 20) internalTimer = 0; // Si le timer dépasse les 320 actualisations, on le remet à 0 (Faire varier le 20 pour changer la vitesse d'animation).
	}

}
