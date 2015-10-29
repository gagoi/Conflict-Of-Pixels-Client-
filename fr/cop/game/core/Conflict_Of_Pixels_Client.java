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
import fr.cop.game.core.helpful.logger.SimpleDebugWindow;
import fr.cop.game.core.inputs.Keyboard;
import fr.cop.game.core.inputs.Mouse;
import fr.cop.game.graphics.Screen;
import fr.cop.launcher.Launcher_Panel;

public class Conflict_Of_Pixels_Client extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;																																																																																																																																																																																																																																																															 // Convention java.

	public static int nbUps = 1000 / 60;  // Nombre d'UPS du jeu.
	private int actualFPS, actualUPS; // Variable permettant d'afficher le nombre de FPS.
	private static int nbFps = 1000 / 60; // Si négatif, FPS non limités.
	private boolean running = false; // Boolean permettant de dire si le jeu fonctionne ou non.
	private boolean pause = false; // Permet de mettre le jeu en pause. Ne sera utilisable que depuis le serveur (à terme).

	public boolean debug = false; // Mode débug.
	private static int cameraSpeed = 3; // Vitesse de la caméra
	private static boolean isCameraBlocked = false;

	public static int scale = 5; // Taille des pixels (pixels du jeu).
	public static int width = 250; // Taille de la fenetre (largeur).
	public static int height = width / 16 * 9; // Taille de la fenêtre (hauteur).
	
	private int imageRenderedWidth = width * scale, imageRenderedHeight = height * scale; // Taille de rendu de l'image en tampon du jeu.
	
	public Dimension size = new Dimension(imageRenderedWidth, imageRenderedHeight); // Taille de la fenetre.
	public static boolean scorePWAL1;
	public final Level MAP = new Level("map", 16); // Création de notre map, de paramêtre son nom et sa taille.
	
	public BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Image de notre jeu (en tampon).
	public int[] pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData(); // Pixels de l'image.

	public static Game_Frame gameFrame;	// Fenetre de notre jeu (lancé).
	public static Screen screenGAME, screenHUD; // Notre ecran de jeu, permettant le rendu (pixel à pixel).
	private Keyboard keyboard; // Entrées clavier.
	private Mouse mouse; // Entrée souris.
	public static SimpleDebugWindow debugWindow; // Fenetre de debug.
	public static JFrame menuFrame; // Fenêtre étant notre Launcher.

	private Thread t;																																																																																																																																																																																																																																																																																																																																																																																 // Thread de notre jeu.
	public static CharacterList CHARACTER_LIST;																																																																																																																																																																																																																																																																																																																																 // Liste des Champions.

	public static boolean isFullScreen;

	public int x = 0;
	public int y = 0; /* Temporaire */

	public Conflict_Of_Pixels_Client() { // Objet etant notre jeu.
		setSize(size); // Cet objet étant un canvas, on choisis sa taille.
		screenGAME = new Screen(width, height); // ... et notre screen.
		screenHUD = new Screen(imageRenderedWidth, imageRenderedHeight);
		keyboard = new Keyboard(); // Création de notre écouteur clavier.
		mouse = new Mouse(); // Création de notre écouteur souris.
		addKeyListener(keyboard); // On ajoute notre écouteur clavier au jeu.
		addMouseListener(mouse); // On ajoute notre écouteur souris au jeu.

		debugWindow = new SimpleDebugWindow(); // Création de notre fenêtre de debug.
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

		CHARACTER_LIST = new CharacterList(); // On instancie notre liste de champions.
		t = new Thread(this, "CoP"); // On créer le Thread du jeu. (pas celui de l'affichage).
		t.start(); // On démarre le jeu.

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
				startTimeFPS = System.currentTimeMillis(); // ... on remet le temps de depart a 0 ...
				visualUpdate(); // ... puis on fait une update.
				fpsCalc++;
			}

			if (System.currentTimeMillis() - timer > 1000) { // Si le jeu passe 1 seconde.
				timer += 1000; // On augmente de 1 sec la limite Ã  atteindre.
				actualFPS = fpsCalc; // On définit le nombre actuel de FPS ...
				actualUPS = upsCalc; // ... et d'UPS.
				fpsCalc = 0; // On remet le compteur de FPS ...
				upsCalc = 0; // ... et d'UPS à  0.
			}
		}
	}

	public synchronized void gameUpdate() { // Methode visant l'actualisation du jeu. Le jeu sera limite a 60ups (update per second). Pour que tout le monde joue a la meme vitesse.

		/*
		 * TO-DO : - Utilisation de la souris. - Envoie des packets au
		 * server. - Lecture des packets du server.
		 */

		/* Temporaire avant serveur */

		keyboard.update(); // On met à jour le keyboard.
		if (keyboard.keys[KeyEvent.VK_P] && keyboard.canPressP && !pause) pause(); // Si on appuie sur 'P', que l'on peut mettre/enlever la pause et qu'on est pas déjà en pause, on met la pause.
		else if (keyboard.keys[KeyEvent.VK_P] && keyboard.canPressP && pause) backToTheGame(); // Si on appuie sur 'P', que l'on peut mettre/enlever la pause et qu'on est déjà en pause, on enlève la pause.

		if (!pause) { // Si le jeu n'est pas en pause.
			if (keyboard.directions[0]) y += cameraSpeed; // On déplace la caméra en direction du haut.
			if (keyboard.directions[1]) y -= cameraSpeed; // On déplace la caméra en direction du bas.
			if (keyboard.directions[2]) x += cameraSpeed; // On déplace la caméra en direction de la droite.
			if (keyboard.directions[3]) x -= cameraSpeed; // On déplace la caméra en direction de la gauche.

			debugWindow.setDirectionKeysState(keyboard.directions); // On actualise l'état des touches de directions dans la fenêtre de debug.
			debugWindow.setItemsKeysState(keyboard.items); // On actualise l'état des touches des items dans le fenêtre de debug.
			debugWindow.setSpellsKeysState(keyboard.spells); // On actualise l'état des touches des sorts dans la fenêtre de debug.

			screenGAME.increaseTimer(); // On incrémente le timer de notre screen, permet d'avoir des animations.
			screenHUD.increaseTimer(); // On incrémente le timer de notre screen, permet d'avoir des animations.

		}
	}

	public synchronized void visualUpdate() { // Methode actualisant l'ecran. Pas le jeu. Peut etre different entre chaque joueur.
		BufferStrategy bs = getBufferStrategy(); // On récupère la stratégie de buffer (du canvas).
		if (bs == null) { // Si elle n'éxiste pas ...
			createBufferStrategy(3); // ... on met du triple Buffering. (3 images tampons).
			return; // Et on ne dessine rien, pour ne pas crash.
		}

		screenGAME.clear(0); // On vide l'écran actuel.
		screenHUD.clear(-1);
		screenGAME.render(x, y); // On fait le rendu du jeu.
		screenHUD.renderHUD(); // On fait le rendu du HUD.
		
		// On transforme tous les pixels rendus en pixels affichés.
		for (int x = 0; x < screenGAME.pixels.length; x++) {
			for (int y = 0; y < screenGAME.pixels[x].length; y++) {
				pixels[x + y * width] = screenGAME.pixels[x][y];
				if(screenHUD.pixels[x][y] != -1) pixels[x+y*width] = screenHUD.pixels[x][y];
			}
		}

		debugWindow.setFPS(actualFPS); // On actualise les FPS dans la fenêtre de debug.
		debugWindow.setUPS(actualUPS); // On actualise les UPS dans la fenêtre de debug.

		Graphics g = bs.getDrawGraphics(); // On récupère les graphics de notre canvas.
		g.setColor(Color.BLACK); // On met la couleur en noire pour ...
		g.fillRect(0, 0, getWidth(), getHeight()); // ... vider l'écran.
		g.drawImage(bufferedImage, 0, 0, imageRenderedWidth, imageRenderedHeight, null); // Puis on dessine notre image.

		if (debug) { // Si le debug est activé. On affiche les options de debug. (A modifier plus tard pour avoir plus bel affichage).
			g.setColor(Color.WHITE); // Couleur blanche.
			g.fillRect(10, 10, 200, 100); // Pour dessiner l'arrière plan.
			g.setColor(Color.BLACK); // Ecriture noire.
			g.setFont(new Font("Arial Black", Font.BOLD, 20)); // Choix de la police de caractère.
			g.drawString("FPS : " + actualFPS, 25, 40); // Affichage des FPS.
			g.drawString("UPS : " + actualUPS, 25, 60); // Affichage des UPS.
		}
		g.dispose(); // On détruit notre objet graphique. Pour libérer la ram pour la prochaine image.
		bs.show(); // On affiche notre buffered Image.
	}

	public synchronized void pause() { // Méthode pour mettre en pause le jeu.
		pause = true; // On dis que le jeu est en pause.
		keyboard.canPressP = false; // On oblige le relachement de la touche de pause avant de l'enlever.
	}

	public synchronized void backToTheGame() { // Méthode pour enlever la pause.
		pause = false; // On dit que le jeu n'est pas en pause.
		keyboard.canPressP = false; // On oblige le relancement de la touche de pause avant de la remettre.
	}

	public synchronized void pauseBeforeChange() { // Méthode pour mettre le Thread en pause.
		running = false; // On arrête la boucle principale.
		try { // On essaie de ...
			t.join(); // ... arrêter le Thread.
		} catch (InterruptedException e) { // Si une erreur est levée...
			e.printStackTrace(); // ... on écrit le rapport d'erreur dans la console.
		}
	}

	public synchronized void continueAfterChange() { // Méthode pour relance le Thread
		running = true; // On dit que la boucle principale peut tourner.
		t.start(); // Et on lance le jeu.
	}

	public synchronized void stop() { // Méthode pour arrêtre le jeu.
		running = false; // On arrete la boucle principale du jeu.
		try { // On essaie ..
			t.join(); // ... de stoper le Thread contenant notre jeu.
		} catch (InterruptedException e) { // Si il y a un problème....
			e.printStackTrace(); // ... on écrit le rapport d'erreur dans la console.
		} finally { // Dans tous les cas ...
			System.exit(0); // ... on quite le programme. (Sera remplacée par une fenêtre de confirmation de déconnection.
		}
	}

	public boolean getDebugState() { // Méthode pour savoir si le debug est activé.
		return debug;
	}

	public void setDebugState(boolean debugState) { // Méthode pour activer ou non le debug sur la fenetre de jeu.
		debug = debugState;
	}

	public void setFpsLimitatiob(int fps) { // Méthode pour changer la limitation de FPS
		nbFps = 1000 / fps;
	}

	public synchronized void setImageRenderedSize(int width, int height) { // Méthode pour changer la taille à laquelle notre bufferedImage sera affichée.
		imageRenderedWidth = width; // en largeur.
		imageRenderedHeight = height; // en hauteur.
	}
}
