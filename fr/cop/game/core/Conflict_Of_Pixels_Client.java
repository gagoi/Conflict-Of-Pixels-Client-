package fr.cop.game.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import fr.cop.game.core.characters.CharacterList;
import fr.cop.game.core.characters.TestCharacter;
import fr.cop.game.visual.Animation;
import fr.cop.game.visual.Screen;

public class Conflict_Of_Pixels_Client extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int nbUps = 1000 / 60;
	private int actualFPS, actualUPS;
	private static int nbFps = 1000 / 60;
	private boolean running = false;
	private static boolean debug = true;

	private static int scale = 3; // Taille des pixels (pixels du jeu).
	private static int width = 350; // Taille de la fenetre (largeur).
	private static int height = width / 16 * 9; // Taille de la denetre
												// (hauteur).
	private static Dimension size = new Dimension(width * scale, height * scale);

	private BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();

	private JFrame f;
	private Screen screen;

	private TestCharacter champTest;
	private Thread t;
	public static Conflict_Of_Pixels_Client GAME;
	public static CharacterList CHARACTER_LIST;
	public static ArrayList<Animation> animations = new ArrayList<Animation>();

	public Conflict_Of_Pixels_Client() { // Objet etant notre jeu.
		setSize(size);
		f = new JFrame();
		screen = new Screen(width, height);
	}

	public static void main(String[] args) { // Methode de demarrage d'un
												// programme en Java.
		GAME = new Conflict_Of_Pixels_Client();

		GAME.f.setTitle("Conflict Of Pixels");
		GAME.f.setResizable(false);
		GAME.f.setLayout(null);
		GAME.f.setSize(size);
		GAME.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GAME.f.add(GAME);

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
		long timer = System.currentTimeMillis();
		long startTimeUPS = System.currentTimeMillis(); // Temps de depart du
														// programme, utilise
														// pour les UPS.
		long startTimeFPS = System.currentTimeMillis(); // Temps de depart du
														// programme, utilise
														// pour les FPS.
		int fpsCalc = 0, upsCalc = 0;
		while (running) { // Boucle principale de notre programme.
			long currentTime = System.currentTimeMillis(); // Temps en
															// millisecondes.
			if (currentTime >= startTimeUPS + nbUps) { // Si le temps du
														// processeur est
														// sup�rieur au temps de
														// depart + nbUps...
				startTimeUPS = System.currentTimeMillis(); // ... on remet le
															// temps de depart a
															// 0 ...
				gameUpdate(); // ... puis on fait une update.
				upsCalc++;
			}
			if (currentTime >= startTimeFPS + nbFps || nbFps < 0) { // Si le temps du
														// processeur est
														// sup�rieur au temps de
														// depart + nbFps...
				startTimeFPS = System.currentTimeMillis(); // ... on remet le
															// temps de depart a
															// 0 ...
				visualUpdate(); // ... puis on fait une update.
				fpsCalc++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				actualFPS = fpsCalc;
				actualUPS = upsCalc;
				fpsCalc = 0;
				upsCalc = 0;
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

		// Temporaire avant serveur
		for (Iterator<Animation> iterator = animations.iterator(); iterator.hasNext();) {
			Animation anim = (Animation) iterator.next();
			anim.move();
		}
	}

	public synchronized void visualUpdate() { // Methode actualisant
														// l'ecran.
		// Pas le jeu. Peut etre
		// different entre chaque
		// joueur.
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		screen.render();

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);

		if (debug) {
			g.setColor(Color.WHITE);
			g.fillRect(10, 10, 200, 100);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial Black", Font.BOLD, 20));
			g.drawString("FPS : " + actualFPS, 25, 40);
			g.drawString("UPS : " + actualUPS, 25, 60);
		}
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
