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

import javax.swing.JFrame;

import fr.cop.game.core.characters.CharacterList;
import fr.cop.game.core.characters.TestCharacter;
import fr.cop.game.core.helpful.logger.SimpleDebugWindow;
import fr.cop.game.core.inputs.Keyboard;
import fr.cop.game.core.inputs.Mouse;
import fr.cop.game.graphics.Screen;
import fr.cop.launcher.Launcher_Panel;

public class Conflict_Of_Pixels_Client extends Canvas implements Runnable {
	private static final long		serialVersionUID	= 1L;																																																																																																																																																																																																																													 // Convention java.

	public static int				nbUps				= 1000 / 60;																																																																																																																																																																																																			 // Nombre d'UPS du jeu.
	private int						actualFPS, actualUPS;																																																																																																																																																																																																																																										 // Variable permettant d'afficher le
	// nombre de FPS.
	private static int				nbFps				= 1000 / 60;																																																																																																																																																																																																			 // Si nÃ©gatif, FPS non limitÃ©s.
	private boolean					running				= false;																																																																																																																																																																																																																 // Boolean permettant de dire si le jeu
	// fonctionne ou non.
	private boolean					pause				= false;

	public boolean					debug				= false;																																																																																																																																																																																																																 // Mode dÃ©bug.
	private static int				cameraSpeed			= 3;																																																																																																																																																																																																																													 // Vitesse de la camÃ©ra
	private static boolean			isCameraBlocked		= false;

	private static int				scale				= 5;																																																																																																																																																																																																																													 // Taille des pixels (pixels du jeu).
	public static int				width				= 250;																																																																																																																																																																																																																													 // Taille de la fenetre (largeur).
	public static int				height				= width / 16 * 9;																																																																																																																																																																																						 // Taille de la denetre
	// (hauteur).
	private int						imageRenderedWidth	= width * scale,
											imageRenderedHeight = height * scale;

	public Dimension				size				= new Dimension(imageRenderedWidth, imageRenderedHeight);																																																				 // Taille
	// de
	// la
	// fenetre.
	public static boolean			scorePWAL1;

	public static final Level		MAP					= new Level("map", 16);

	public BufferedImage			bufferedImage		= new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);																																							 // Image
	// de
	// notre
	// jeu
	// (en
	// tempon)
	public int[]					pixels				= ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData(); // Pixels
																																					// de
																																					// l'image.

	public static Game_Frame		gameFrame;																																																																																																																																																																																																																																																																																	 // Fenetre
	public static Screen			screen;																																																																																																																																																																																																																																																																																														 // Notre ecran de jeu.
	private Keyboard				keyboard;																																																																																																																																																																																																																																																																																	 // EntrÃ©e clavier.
	private Mouse					mouse;
	public static SimpleDebugWindow	debugWindow;																																																																																																																																																																																																																																																																				 // Fenetre de debug.
	public static JFrame			menuFrame;

	private TestCharacter			champTest;																					/* Temporaire */
	private Thread					t;																																																																																																																																																																																																																																																																																																											 // Thread de notre jeu.
	public static CharacterList		CHARACTER_LIST;																																																																																																																																																																																																																																																																				 // Liste des Champions.

	public static boolean			isFullScreen;

	public int						x					= 0,
											y = 0;																				/* Temporaire */

	public Conflict_Of_Pixels_Client() { // Objet etant notre jeu.
		setSize(size); // Cet objet Ã©tant un canvas, on choisis sa taille.
		screen = new Screen(width, height); // ... et notre screen.
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		addMouseListener(mouse);

		debugWindow = new SimpleDebugWindow();
	}

	public static void main(String[] args) { // Methode de demarrage d'un programme en Java.

		menuFrame = new JFrame(); // On instancie notre fenetre de launcher
		menuFrame.add(new Launcher_Panel()); // On lui ajoute un nouveau paneau de menu.
		menuFrame.setTitle("Menu CoP... Test"); // On met son titre.
		menuFrame.setSize(1280, 720); // On choisit sa taille.
		menuFrame.setResizable(false); // On empeche le redimensionnement de la fenetre.
		menuFrame.setVisible(true); // On l'affiche.
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On ferme le programme lorsque la fentre est fermée.

	}

	public synchronized void start() { // Fonction de démarrage du jeu.
		running = true; // On met la variable running sur vrai.

		CHARACTER_LIST = new CharacterList(); // On instancie notre liste de
												// champions.
		t = new Thread(this, "CoP"); // On crÃ©er le Thread du jeu. (pas celui
									// de l'affichage).
		t.start(); // On dÃ©marre le jeu.

	}

	@Override
	public void run() { // ExÃ©cution de notre jeu.
		requestFocus(); // Met le focus sur le jeu.
		long timer = System.currentTimeMillis(); // Pour compter FPS et UPS
		long startTimeUPS = System.currentTimeMillis(); // Temps de depart du programme, utilise pour les UPS.
		long startTimeFPS = System.currentTimeMillis(); // Temps de depart du programme, utilise pour les FPS.
		int fpsCalc = 0, upsCalc = 0; // Pour compter FPS et UPS.
		while (running) { // Boucle principale de notre programme.
			long currentTime = System.currentTimeMillis(); // Temps en millisecondes.
			if (currentTime >= startTimeUPS + nbUps) { // Si le temps du processeur est supérieur au temps de depart + nbUps...
				startTimeUPS = System.currentTimeMillis(); // ... on remet le temps de depart a 0 ...
				gameUpdate(); // ... puis on fait une update.
				upsCalc++;
			}
			if (currentTime >= startTimeFPS + nbFps || nbFps < 0) { // Si le temps du processeur est supérieur au temps de depart + nbFps ou si on ne limite pas les fps...
				startTimeFPS = System.currentTimeMillis(); // ... on remet le
															// temps de depart a
															// 0 ...
				visualUpdate(); // ... puis on fait une update.
				fpsCalc++;
			}

			if (System.currentTimeMillis() - timer > 1000) { // Si le jeu vient
															// de passer 1
															// secondes.
				timer += 1000; // On augmente de 1 sec la limite Ã  atteindre.
				actualFPS = fpsCalc; // On dÃ©finit le nombre actuel de FPS ...
				actualUPS = upsCalc; // ... et d'UPS.
				fpsCalc = 0; // On remet le compteur de FPS ...
				upsCalc = 0; // ... et d'UPS Ã  0.
			}
		}
	}

	public synchronized void gameUpdate() { // Methode visant l'actualisation du
											// jeu. Le jeu sera limite a 60ups
											// (update per second). Pour que
											// tout le monde joue a la meme
											// vitesse.

		/*
		 * TO-DO : - Utilisation des touches + souris. - Envoie des packets au
		 * server. - Lecture des packets du server.
		 */

		/* Temporaire avant serveur */

		keyboard.update();
		if (keyboard.keys[KeyEvent.VK_P] && keyboard.canPressP && !pause) pause();
		else if (keyboard.keys[KeyEvent.VK_P] && keyboard.canPressP && pause) backToTheGame();
		
		if (!pause) {
			if (keyboard.directions[0]) y += cameraSpeed;
			if (keyboard.directions[1]) y -= cameraSpeed;
			if (keyboard.directions[2]) x += cameraSpeed;
			if (keyboard.directions[3]) x -= cameraSpeed;

			System.out.println("Pause : " + pause + " - canPressP : " + keyboard.canPressP);

			debugWindow.setDirectionKeysState(keyboard.directions);
			debugWindow.setItemsKeysState(keyboard.items);
			debugWindow.setSpellsKeysState(keyboard.spells);

			screen.increaseTimer();

		}
	}

	public synchronized void visualUpdate() { // Methode actualisant l'ecran. Pas le jeu. Peut etre different entre chaque joueur.
		BufferStrategy bs = getBufferStrategy(); // On récupère la stratégie de buffer (du canvas).
		if (bs == null) { // Si elle n'éxiste pas ...
			createBufferStrategy(3); // ... on met du triple Buffering. (3 images tampons).
			return; // Et on ne dessine rien, pour ne pas crash.
		}

		screen.clear(); // On vide l'écran actuel.
		screen.render(x, y); // On fait le rendu du jeu.

		// On transforme tous les pixels rendus en pixels affichés.
		for (int x = 0; x < screen.pixels.length; x++) {
			for (int y = 0; y < screen.pixels[x].length; y++) {
				pixels[x + y * width] = screen.pixels[x][y];
			}
		}

		debugWindow.setFPS(actualFPS);
		debugWindow.setUPS(actualUPS);

		Graphics g = bs.getDrawGraphics(); // On récupère les graphics de notre canvas.
		g.setColor(Color.BLACK); // On met la couleur en noire pour ...
		g.fillRect(0, 0, getWidth(), getHeight()); // ... vider l'écran.
		g.drawImage(bufferedImage, 0, 0, imageRenderedWidth, imageRenderedHeight, null); // Puis on dessine notre image.

		if (debug) { // Si le debug est activé. On affiche les options de debug. (A modifier plus tard pour avoir plus bel affichage).
			g.setColor(Color.WHITE); // Fond blanc.
			g.fillRect(10, 10, 200, 100);
			g.setColor(Color.BLACK); // Ecriture noire.
			g.setFont(new Font("Arial Black", Font.BOLD, 20)); // Choix de la police de caractère.
			g.drawString("FPS : " + actualFPS, 25, 40); // Affichage des FPS.
			g.drawString("UPS : " + actualUPS, 25, 60); // Affichage des UPS.
		}
		g.setColor(Color.BLACK);
		g.drawLine(getWidth() / 2 - 50, getHeight() / 2, getWidth() / 2 + 50, getHeight() / 2);
		g.drawLine(getWidth() / 2, getHeight() / 2 - 50, getWidth() / 2, getHeight() / 2 + 50);
		g.dispose(); // On dÃ©truit notre objet graphique. Pour libÃ©rer la ram
					// pour la prochaine image.
		bs.show(); // On affiche notre buffered Image.
	}

	public synchronized void pause() {
		pause = true;
		keyboard.canPressP = false;
	}

	public synchronized void backToTheGame() {
		pause = false;
		keyboard.canPressP = false;
	}

	public synchronized void pauseBeforeChange() {
		running = false;
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void continueAfterChange() {
		running = true;
		t.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public boolean getDebugState() {
		return debug;
	}

	public void setDebugState(boolean debugState) {
		debug = debugState;
	}

	public void setFpsLimitatiob(int fps) {
		nbFps = 1000 / fps;
	}

	public synchronized void setImageRenderedSize(int width, int height) {
		imageRenderedWidth = width;
		imageRenderedHeight = height;
	}
}
