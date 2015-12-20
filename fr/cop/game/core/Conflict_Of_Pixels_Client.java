package fr.cop.game.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Vector;

import javax.swing.JFrame;

import fr.cop.common.Game;
import fr.cop.common.entities.Entity;
import fr.cop.common.entities.champions.Champion;
import fr.cop.game.core.helpful.logger.SimpleDebugWindow;
import fr.cop.game.core.inputs.Keyboard;
import fr.cop.game.core.inputs.Mouse;
import fr.cop.game.graphics.Screen;
import fr.cop.game.graphics.hud.HUD;
import fr.cop.game.graphics.inGameOptions.Frame;
import fr.cop.game.graphics.sprites.Sprite;
import fr.cop.game.graphics.sprites.Sprites;

public class Conflict_Of_Pixels_Client extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L; // Convention java.

	public static int nbUps = 1000 / 60; // Nombre d'UPS du jeu.
	private int actualFPS, actualUPS; // Variable permettant d'afficher le nombre de FPS.
	private static int nbFps = 1000 / 60; // Si n�gatif, FPS non limit�s.
	private boolean running = false; // Boolean permettant de dire si le jeu fonctionne ou non.
	public boolean isGamePaused = false; // Permet de mettre le jeu en pause. Ne sera utilisable que depuis le serveur (� terme).

	public boolean debug = false; // Mode d�bug.
	private static int cameraSpeed = 3; // Vitesse de la cam�ra
	private static boolean isCameraBlocked = false;

	public static int scale = 5; // Taille des pixels (pixels du jeu).
	public static int width = 250; // Taille de la fenetre (largeur).
	public static int height = width / 16 * 9; // Taille de la fen�tre (hauteur).

	private int imageRenderedWidth = width * scale, imageRenderedHeight = height * scale; // Taille de rendu de l'image en tampon du jeu.

	public Dimension size = new Dimension(imageRenderedWidth, imageRenderedHeight); // Taille de la fenetre.
	public static boolean scorePWAL1;

	public BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Image de notre jeu (en tampon).

	public int[] pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData(); // Pixels de l'image.

	public static Game_Frame gameFrame; // Fenetre de notre jeu (lanc�).
	public static Screen screenGAME; // Notre ecran de jeu, permettant le rendu (pixel � pixel).
	public HUD hud;
	private Keyboard keyboard; // Entr�es clavier.
	private Mouse mouse; // Entr�e souris.
	public static SimpleDebugWindow debugWindow; // Fenetre de debug.
	public static JFrame menuFrame; // Fen�tre �tant notre Launcher.
	public Frame optionFrame = new Frame();

	private Thread t; // Thread de notre jeu.
	public Vector<Entity> entities = new Vector<Entity>();

	public static boolean isFullScreen;
	public boolean isGameAnimated = false;

	public int x = 0;
	public int y = 0; /* Temporaire */
	public Game serverGame;
	private Champion[] tempChamps = new Champion[10];

	public Conflict_Of_Pixels_Client() { // Objet etant notre jeu.
		setSize(size); // Cet objet �tant un canvas, on choisis sa taille.
		screenGAME = new Screen(width, height); // ... et notre screen.
		hud = new HUD(imageRenderedWidth, imageRenderedHeight);
		keyboard = new Keyboard(); // Cr�ation de notre �couteur clavier.
		mouse = new Mouse(); // Cr�ation de notre �couteur souris.
		addKeyListener(keyboard); // On ajoute notre �couteur clavier au jeu.
		addMouseListener(mouse); // On ajoute notre �couteur souris au jeu.

		hud.addMouseListeners(this);
		optionFrame.addMouseListeners(this);

		debugWindow = new SimpleDebugWindow(); // Cr�ation de notre fen�tre de debug.
		serverGame = new Game("C:\\Conflict Of Pixels\\", Game.TYPE_CLIENT) {
			//			@Override
			//			public void getInformation() {
			//				this.setChampions(tempChamps);
			//			}
		};
	}

	public synchronized void start() { // Fonction de d�marrage du jeu.
		running = true; // On met la variable running sur vrai.
		t = new Thread(this, "CoP"); // On cr�er le Thread du jeu. (pas celui de l'affichage).

		t.start(); // On d�marre le jeu.

	}

	@Override
	public void run() { // Exécution de notre jeu.
		requestFocus(); // Met le focus sur le jeu.
		long timer = System.currentTimeMillis(); // Pour compter FPS et UPS
		long startTimeUPS = System.currentTimeMillis(); // Temps de depart du programme, utilise pour les UPS.
		long startTimeFPS = System.currentTimeMillis(); // Temps de depart du programme, utilise pour les FPS.
		int fpsCalc = 0, upsCalc = 0; // Pour compter FPS et UPS.
		debugWindow.changePauseStatue(); // On affiche l'�tat de la pause dans la fen�tre de debug.
		while (running) { // Boucle principale de notre programme.
			long currentTime = System.currentTimeMillis(); // Temps en millisecondes.
			if (currentTime >= startTimeUPS + nbUps) { // Si le temps du processeur est sup�rieur au temps de depart + nbUps...
				startTimeUPS = System.currentTimeMillis(); // ... on remet le temps de depart a 0 ...
				gameUpdate(); // ... puis on fait une update.
				upsCalc++;
			}
			if (currentTime >= startTimeFPS + nbFps || nbFps < 0) { // Si le temps du processeur est sup�rieur au temps de depart + nbFps ou si on ne limite pas les fps...
				startTimeFPS = System.currentTimeMillis(); // ... on remet le temps de depart a 0 ...
				visualUpdate(); // ... puis on fait une update.
				fpsCalc++;
			}

			if (System.currentTimeMillis() - timer > 1000) { // Si le jeu passe 1 seconde.
				timer += 1000; // On augmente de 1 sec la limite à atteindre.
				actualFPS = fpsCalc; // On d�finit le nombre actuel de FPS ...
				actualUPS = upsCalc; // ... et d'UPS.
				fpsCalc = 0; // On remet le compteur de FPS ...
				upsCalc = 0; // ... et d'UPS � 0.
			}
		}
	}

	public synchronized void gameUpdate() { // Methode visant l'actualisation du jeu. Le jeu sera limite a 60ups (update per second). Pour que tout le monde joue a la meme vitesse.

		/*
		 * TO-DO : - Utilisation de la souris. - Envoie des packets au
		 * server. - Lecture des packets du server.
		 */

		/* Temporaire avant serveur */

		keyboard.update(); // On met � jour le keyboard.
		if (keyboard.keys[KeyEvent.VK_P] && keyboard.canPressP && !isGamePaused) pause(); // Si on appuie sur 'P', que l'on peut mettre/enlever la pause et qu'on est pas d�j� en pause, on met la pause.
		else if (keyboard.keys[KeyEvent.VK_P] && keyboard.canPressP && isGamePaused) backToTheGame(); // Si on appuie sur 'P', que l'on peut mettre/enlever la pause et qu'on est d�j� en pause, on enl�ve la pause.

		if (!isGamePaused) { // Si le jeu n'est pas en pause.
			if (keyboard.directions[0]) y += cameraSpeed; // On d�place la cam�ra en direction du haut.
			if (keyboard.directions[1]) y -= cameraSpeed; // On d�place la cam�ra en direction du bas.
			if (keyboard.directions[2]) x += cameraSpeed; // On d�place la cam�ra en direction de la droite.
			if (keyboard.directions[3]) x -= cameraSpeed; // On d�place la cam�ra en direction de la gauche.

			debugWindow.setDirectionKeysState(keyboard.directions); // On actualise l'�tat des touches de directions dans la fen�tre de debug.
			debugWindow.setItemsKeysState(keyboard.items); // On actualise l'�tat des touches des items dans le fen�tre de debug.
			debugWindow.setSpellsKeysState(keyboard.spells); // On actualise l'�tat des touches des sorts dans la fen�tre de debug.

			screenGAME.increaseTimer(); // On incr�mente le timer de notre screen, permet d'avoir des animations.
			serverGame.getChampion(0).move();
		}
	}

	public synchronized void visualUpdate() { // Methode actualisant l'ecran. Pas le jeu. Peut etre different entre chaque joueur.
		BufferStrategy bs = getBufferStrategy(); // On r�cup�re la strat�gie de buffer (du canvas).
		if (bs == null) { // Si elle n'�xiste pas ...
			createBufferStrategy(3); // ... on met du triple Buffering. (3 images tampons).
			return; // Et on ne dessine rien, pour ne pas crash.
		}

		screenGAME.clear(0); // On vide l'�cran actuel.
		screenGAME.render(x, y); // On fait le rendu du jeu.

		// On transforme tous les pixels rendus en pixels affich�s.
		for (int x = 0; x < screenGAME.pixels.length; x++) {
			for (int y = 0; y < screenGAME.pixels[x].length; y++) {
				try {
					pixels[x + y * width] = screenGAME.pixels[x][y];
				} catch (Exception e) {
				}
			}
		}

		debugWindow.setFPS(actualFPS); // On actualise les FPS dans la fen�tre de debug.
		debugWindow.setUPS(actualUPS); // On actualise les UPS dans la fen�tre de debug.

		Graphics g = bs.getDrawGraphics(); // On r�cup�re les graphics de notre canvas.
		g.setColor(Color.BLACK); // On met la couleur en noire pour ...
		g.fillRect(0, 0, getWidth(), getHeight()); // ... vider l'�cran.
		g.drawImage(bufferedImage, 0, 0, imageRenderedWidth, imageRenderedHeight, null); // Puis on dessine notre image.

		Champion c = Game_Frame.GAME.serverGame.getChampion(0);
		Sprite s = Sprites.getSpriteFromID(c.getSpriteInformation());
		int cx = ((c.getServerPosX() + 4 * x)/width)*gameFrame.getWidth();
		int cy = ((c.getServerPosX() + 4 * y)/height)*gameFrame.getHeight();
		g.drawImage(s.getImage(), cx, cy, 64, 64, null);

		hud.refreshGraphics(g, imageRenderedWidth, imageRenderedHeight);
		if (optionFrame.isVisible()) optionFrame.refresh(g);

		if (debug) { // Si le debug est activ�. On affiche les options de debug. (A modifier plus tard pour avoir plus bel affichage).
			g.setColor(Color.WHITE); // Couleur blanche.
			g.fillRect(10, 10, 200, 100); // Pour dessiner l'arri�re plan.
			g.setColor(Color.BLACK); // Ecriture noire.
			g.setFont(new Font("Arial Black", Font.BOLD, 20)); // Choix de la police de caract�re.
			g.drawString("FPS : " + actualFPS, 25, 40); // Affichage des FPS.
			g.drawString("UPS : " + actualUPS, 25, 60); // Affichage des UPS.
		}
		g.dispose(); // On d�truit notre objet graphique. Pour lib�rer la ram pour la prochaine image.
		bs.show(); // On affiche notre buffered Image.
	}

	public synchronized void pause() { // M�thode pour mettre en pause le jeu.
		isGamePaused = true; // On dis que le jeu est en pause.
		keyboard.canPressP = false; // On oblige le relachement de la touche de pause avant de l'enlever.
		debugWindow.changePauseStatue(); // On affiche l'�tat de la pause dans la fen�tre de debug.
	}

	public synchronized void backToTheGame() { // M�thode pour enlever la pause.
		isGamePaused = false; // On dit que le jeu n'est pas en pause.
		keyboard.canPressP = false; // On oblige le relancement de la touche de pause avant de la remettre.
		debugWindow.changePauseStatue();// On affiche l'�tat de la pause dans la fen�tre de debug.
	}

	public synchronized void pauseBeforeChange() { // M�thode pour mettre le Thread en pause.
		running = false; // On arr�te la boucle principale.
		try { // On essaie de ...
			t.join(); // ... arr�ter le Thread.
		} catch (InterruptedException e) { // Si une erreur est lev�e...
			e.printStackTrace(); // ... on �crit le rapport d'erreur dans la console.
		}
	}

	public synchronized void continueAfterChange() { // M�thode pour relance le Thread
		running = true; // On dit que la boucle principale peut tourner.
		t.start(); // Et on lance le jeu.
	}

	public synchronized void stop() { // M�thode pour arr�tre le jeu.
		running = false; // On arrete la boucle principale du jeu.
		System.exit(0);
	}

	public boolean getDebugState() { // M�thode pour savoir si le debug est activ�.
		return debug;
	}

	public void setDebugState(boolean debugState) { // M�thode pour activer ou non le debug sur la fenetre de jeu.
		debug = debugState;
	}

	public void setFpsLimitatiob(int fps) { // M�thode pour changer la limitation de FPS
		nbFps = 1000 / fps;
	}

	public synchronized void setImageRenderedSize(int width, int height) { // M�thode pour changer la taille � laquelle notre bufferedImage sera affich�e.
		imageRenderedWidth = width; // en largeur.
		imageRenderedHeight = height; // en hauteur.
	}
}
