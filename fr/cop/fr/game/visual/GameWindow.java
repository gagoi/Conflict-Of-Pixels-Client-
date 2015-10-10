package fr.game.visual;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame implements Runnable {

	@SuppressWarnings("unused")
	private int pixelSize = 0; // Taille des pixels (pixels du jeu).
	private int width = 720; // Taille de la fenetre (largeur).
	private int height = 720; // Taille de la denetre (hauteur).
	private GamePanel gamePanel;
	private UIPanel uiPanel;

	public GameWindow() {
		Thread t = new Thread(this);
		t.start();
	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

	public UIPanel getUiPanel() {
		return uiPanel;
	}
	@Override
	public void run() {
		setTitle("Conflict Of Pixels");
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		uiPanel = new UIPanel();
		uiPanel.setBounds(0, 0, getWidth(), getHeight());
		gamePanel = new GamePanel();
		gamePanel.setBounds(0, 0, getWidth(), getHeight());
		add(gamePanel);
		add(uiPanel);

		setVisible(true);

	}
}
