package fr.cop.game.graphics.inGameOptions;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

public class Frame {

	private ArrayList<Tab> tabs = new ArrayList<Tab>();
	private boolean isVisible = true;
	private volatile int internalTimer;

	public Frame() {
		tabs.add(new Tab("Video", 0, 0, new OptionTab("Video", 200, 0)));
		tabs.add(new Tab("Input", 0, 50, new OptionTab("Input", 200, 0)));
		tabs.get(0).selectTab();
	}

	public void refresh(Graphics g) {
		OptionElement.internalTimer = internalTimer;
		if (isVisible) {
			for (Tab tab : tabs) {
				tab.paint(g);
				if (tab.isSelected()) tab.getOptionTab().paint(g);
			}
		}
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void increaseTimer() { // M�thode pour incr�menter le timer utilis� pour les animations.
		internalTimer+=2; // On incr�mente le timer de 1.
		if (internalTimer > 100) internalTimer = 0; // Si le timer d�passe les 100 actualisations, on le remet � 0 (Faire varier le 20 pour changer la vitesse d'animation).
	}

	public void addMouseListeners(Canvas c) {
		for (Tab tab : tabs) {
			tab.getOptionTab().addListeners(c);
		}
	}
}
