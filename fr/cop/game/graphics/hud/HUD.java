package fr.cop.game.graphics.hud;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import fr.cop.game.core.Game;
import fr.cop.game.core.Game_Frame;

public class HUD {

	// Les différentes parties du HUD :
	private HUD_map map; // - la minimap.
	private HUD_Statistiques stats; // - l'onglet affichant les stats du
									// joueurs.
	private HUD_SpellsBar spellsBar; // - la barre de sorts.
	private int width, height; // Taille de la fenetre.
	Properties hudProp = new Properties(); // Propriétés, permettant de
											// facilement sayvegarder les
											// préférences de l'utilisateur (en
											// ce qui concerne le HUD)
	File propHudPropFile = new File(Game_Frame.GAME.serverGame.getPath() + "\\config\\hud.properties"); // Fichiers
																										// de
																										// propriétes.

	public HUD(int w, int h) { // Objet HUD
		map = new HUD_map(); // On instancie la map
		stats = new HUD_Statistiques(); // On instancie les stats
		spellsBar = new HUD_SpellsBar(); // On instancie la barre de sort.

		this.width = w; // On instancie la taille de l'écran.
		this.height = h; // On instancie la taille de l'écran.
		try { // On essaie ...
			loadHUD(); // ... de charger le HUD depuis les préférences de
						// l'utilisateur.
		} catch (Exception e) { // En cas d'erreur...
			saveHUD(); // On créer les fichier par défaut.
			try {
				loadHUD();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void loadHUD() throws FileNotFoundException, IOException { // Fonction
																		// permettant
																		// de
																		// charger
																		// les
																		// fichiers.
		Game_Frame.logger.logTxt("HUD - Loader", "Start loading...");
		hudProp.load(new FileInputStream(propHudPropFile)); // On lit le fichier
															// de propriétés.
		map.readProps(hudProp); // On permet à chaque partie du HUD de lire les
								// infos dont il a besoin dans le fichier.
		stats.readProps(hudProp);
		spellsBar.readProps(hudProp);
		Game_Frame.logger.logTxt("HUD - Loader", "Finish loading...");
	}

	public void saveHUD() { // Méthode pour sauvegarder les informations sur le
							// HUD.
		Game_Frame.logger.logTxt("HUD - Saving", "Start saving...");
		if (!propHudPropFile.exists())
			try { // Si le fichier n'existe pas...
				propHudPropFile.createNewFile(); // on le créer.
				Game_Frame.logger.logTxt("HUD - Saving", "File error ! Create it.");
			} catch (IOException e) { // En cas d'erreur
				Game_Frame.logger.logErr("HUD - Saving",
						"Error during file creation, please send this error to developers.");
				e.printStackTrace(); // On écrit l'erreur dans la console.
			}
		// On permet à chauqe partie du HUD d'écrire les propriétés dont il a
		// besoin.
		map.storeProps(hudProp);
		stats.storeProps(hudProp);
		spellsBar.storeProps(hudProp);

		try { // On essaie ...
			hudProp.store(new FileOutputStream(propHudPropFile), ""); // ... de
																		// sauvegarder
																		// les
																		// propriétés
																		// dans
																		// le
																		// fichier.
			Game_Frame.logger.logTxt("HUD - Saving", "Properties stored in file.");
		} catch (IOException e) { // En cas d'erreur...
			Game_Frame.logger.logErr("HUD - Saving", "Error during file saving, please send this error to developers");
			e.printStackTrace(); // ... on écrit le rapport d'erreur dans la
									// console.
		}
	}

	public void refreshGraphics(Graphics g, int w, int h) { // Méthode pour
															// faire le rendu du
															// HUD sur l'écran
															// de jeu.
		if (height != h || width != w) {
			this.height = h;
			this.width = w;
			map.setMaxCoords(w, h);
			stats.setMaxCoords(w, h);
			spellsBar.setMaxCoords(w, h);
		}
		// Permet à chauqe composant de se redissn
		map.refresh(g);
		stats.refresh(g);
		spellsBar.refresh(g);
	}

	public void addMouseListeners(Canvas c) { // Méthode pour ajouter chaque
												// click listener. (Permet la
												// gestiond e la souris).
		c.addMouseListener(map);
		c.addMouseMotionListener(map);
		c.addMouseListener(stats);
		c.addMouseMotionListener(stats);
		c.addMouseListener(spellsBar);
		c.addMouseMotionListener(spellsBar);
	}
}
