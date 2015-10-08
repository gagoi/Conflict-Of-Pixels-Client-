package fr.cop.game.visual;

import javax.swing.JFrame;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.visual.customsButtons.SpellButton;

@SuppressWarnings("serial")
public class GameWindow extends JFrame implements Runnable {

	private int pixelSize = 0; // Taille des pixels (pixels du jeu).
	private int width = 720; // Taille de la fenetre (largeur).
	private int height = 720; // Taille de la denetre (hauteur).
	private GamePanel panel;

	public GameWindow() {
		Thread t = new Thread(this);
		t.start();
	}

	public GamePanel getPanel() {
		return this.panel;
	}

	public void addUI() {
		getPanel().add(new SpellButton(Conflict_Of_Pixels_Client.GAME, Conflict_Of_Pixels_Client.GAME.getChampTest(), 0));
		getPanel().add(new SpellButton(Conflict_Of_Pixels_Client.GAME, Conflict_Of_Pixels_Client.GAME.getChampTest(), 1));
		getPanel().add(new SpellButton(Conflict_Of_Pixels_Client.GAME, Conflict_Of_Pixels_Client.GAME.getChampTest(), 2));
		getPanel().add(new SpellButton(Conflict_Of_Pixels_Client.GAME, Conflict_Of_Pixels_Client.GAME.getChampTest(), 3));
	}

	@Override
	public void run() {
		setTitle("Conflict Of Pixels");
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new GamePanel();
		add(panel);
		addUI();

		setVisible(true);

		while (true) {
			getPanel().repaint();
		}

	}
}
