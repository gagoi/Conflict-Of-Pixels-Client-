package fr.cop.game.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.cop.game.core.helpful.logger.SimpleLog;

public class Game_Frame extends JFrame {
	private static final long serialVersionUID = 5397342146506493113L;

	private static int scale = 5; // Taille des pixels (pixels du jeu).
	private static int width = 250; // Taille de la fenetre (largeur).
	private static int height = width / 16 * 9; // Taille de la denetre
												// (hauteur).
	public static Dimension size = new Dimension(width * scale, height * scale); // Taille de la fenetre.

	private static boolean isFullScreen = false; // permet de savoir si on est en plein �cran.
	public static Conflict_Of_Pixels_Client GAME; // Instance du jeu. Accessible depuis partout.

	public static Game_Frame instance; // Instance de la fen�tre du jeu.

	public static SimpleLog logger = new SimpleLog();
	
	public static File gameFolder = new File("C:\\Conflict Of Pixels\\");
	


	public Game_Frame() { // Objet fen�tre.
		GAME = new Conflict_Of_Pixels_Client(); // On d�finit la variable GAME.
		instance = this; // On instancie notre instance de fen�tre.
		setTitle("Conflict Of Pixels");
		if (isFullScreen) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setUndecorated(true);
			GAME.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
			GAME.setImageRenderedSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

		} else {
			GAME.setSize(size);
			setSize(size);
			GAME.setImageRenderedSize(getSize().width, getSize().height);
			setLocationRelativeTo(null);
		}
		setResizable(false);
		GAME.setBounds(0, 0, getWidth(), getHeight());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String objButtons[] = {"Yes", "No"};
				int answer = JOptionPane.showOptionDialog(instance, "Are you sure ?", "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, objButtons, objButtons[1]);
				if (answer == 0){
					GAME.hud.saveHUD();
					System.exit(0);
				}
			}
		});
		add(GAME, BorderLayout.CENTER);
		setIconImage(new ImageIcon(getClass().getResource("/fr/cop/resources/images/gameLogo.jpg")).getImage());
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GAME.start();
	}

	public synchronized void toggleFullScreen() {
		isFullScreen = !isFullScreen;
		GAME.pauseBeforeChange();
		if (isFullScreen) GAME.setImageRenderedSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		else GAME.setImageRenderedSize(getSize().width, getSize().height);
		Game_Frame.instance.dispose();
		Game_Frame.instance = new Game_Frame();
	}
}
