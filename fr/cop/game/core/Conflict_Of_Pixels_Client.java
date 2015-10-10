package fr.cop.game.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import fr.cop.game.core.characters.CharacterList;
import fr.cop.game.core.characters.TestCharacter;
import fr.cop.game.visual.Animation;

public class Conflict_Of_Pixels_Client extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int nbUps = 1000 / 60;
	private static int nbFps = 1000 / 60;
	private boolean running = false;

	private int scale = 3; // Taille des pixels (pixels du jeu).
	private int width = 300; // Taille de la fenetre (largeur).
	private int height = width / 16 * 9; // Taille de la denetre (hauteur).
	private Dimension size = new Dimension(width * scale, height * scale);

	private JFrame f;

	private TestCharacter champTest;
	private Thread t;
	public static Conflict_Of_Pixels_Client GAME;
	public static CharacterList CHARACTER_LIST;
	public static ArrayList<Animation> animations = new ArrayList<Animation>();

	public Conflict_Of_Pixels_Client() { // Objet etant notre jeu. 
		setPreferredSize(size);
		f = new JFrame();
	}

	public static void main(String[] args) { // Methode de demarrage d'un programme en Java.
		GAME = new Conflict_Of_Pixels_Client();

		GAME.f.setTitle("Conflict Of Pixels");
		GAME.f.setResizable(false);
		GAME.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GAME.f.add(GAME);

		GAME.f.pack();
		GAME.f.setVisible(true);

		GAME.start();
	}

	public synchronized void start() {
		running = true;

		CHARACTER_LIST = new CharacterList();
		t = new Thread(this, "CoP");
		t.start();

	}

	@Override
	public void run() {

		long startTimeUPS = System.currentTimeMillis(); //Temps de depart du programme, utilise pour les UPS.
		long startTimeFPS = System.currentTimeMillis(); //Temps de depart du programme, utilise pour les FPS.
		while (running) { // Boucle principale de notre programme.
			long currentTime = System.currentTimeMillis(); //Temps en millisecondes.
			if (currentTime >= startTimeUPS + nbUps) { // Si le temps du processeur est sup�rieur au temps de depart + nbUps...
				startTimeUPS = System.currentTimeMillis(); // ... on remet le temps de depart a 0 ...
				gameUpdate(); //... puis on fait une update.
			}
			if (currentTime >= startTimeFPS + nbFps) { // Si le temps du processeur est sup�rieur au temps de depart + nbFps...			
				startTimeFPS = System.currentTimeMillis(); // ... on remet le temps de depart a 0 ...
				visualUpdate(); //... puis on fait une update.
			}
		}

	}

	public synchronized void gameUpdate() { // Methode visant l'actualisation du jeu. Le jeu sera limite a 60ups (update per second). Pour que tout le monde joue a la meme vitesse.

		/* TO-DO :
		 * - Utilisation des touches + souris.
		 * - Envoie des packets au server.
		 * - Lecture des packets du server.
		 * */

		//		Temporaire avant serveur
		for (Iterator<Animation> iterator = animations.iterator(); iterator.hasNext();) {
			Animation anim = (Animation) iterator.next();
			anim.move();
		}
	}

	public synchronized void visualUpdate() { // Methode actualisant l'ecran. Pas le jeu. Peut etre different entre chaque joueur.
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.RED);
		g.drawLine(0, 0, 50, 50);
		g.dispose();
		bs.show();

	}

	public TestCharacter getChampTest() {
		return champTest;
	}

	public void setChampTest(TestCharacter champTest) {
		this.champTest = champTest;
	}
}
