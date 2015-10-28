package fr.cop.game.core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game_Frame extends JFrame {
	private static final long serialVersionUID = 5397342146506493113L;

	private static int scale = 5; // Taille des pixels (pixels du jeu).
	private static int width = 250; // Taille de la fenetre (largeur).
	private static int height = width / 16 * 9; // Taille de la denetre
												// (hauteur).
	private static Dimension size = new Dimension(width * scale, height * scale); // Taille
	// de
	// la
	// fenetre.

	private static boolean isFullScreen = false;
	public static Conflict_Of_Pixels_Client GAME;
	
	public static Game_Frame instance;
	
	public Game_Frame(Conflict_Of_Pixels_Client gameInstance) {
		GAME = gameInstance;
		instance = this;
		setTitle("Conflict Of Pixels");
		if(isFullScreen) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setUndecorated(true);
			GAME.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		}
		else {
			GAME.setSize(size);
			setSize(size);
			setLocationRelativeTo(null);
		}
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gameInstance);
		setIconImage(new ImageIcon(getClass().getResource("/fr/cop/resources/images/gameLogo.jpg")).getImage());
		setVisible(true);
		gameInstance.start();
	}
	
	public synchronized void toggleFullScreen(){
		isFullScreen = !isFullScreen;
		GAME.stop();
		GAME = new Conflict_Of_Pixels_Client();
		Game_Frame.instance.dispose();
		Game_Frame.instance = new Game_Frame(GAME);
	}
}
