package fr.cop.game.graphics.inGameOptions;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

import fr.cop.game.graphics.inGameOptions.Elements.OptionTab;
import fr.cop.game.graphics.inGameOptions.Elements.Tab;

public class Frame {

	public ArrayList<Tab> tabs = new ArrayList<Tab>();
	private boolean isVisible = true;

	public Frame() {
		tabs.add(new Tab("Video", 0, 0, new OptionTab("Video", 200, 0)));
		tabs.add(new Tab("Input", 0, 50, new OptionTab("Input", 200, 0)));
		tabs.add(new Tab("HUD", 0, 100, new OptionTabHUD()));
		tabs.get(0).selectTab();
	}

	public void refresh(Graphics g) {
		if (isVisible()) {
			for (Tab tab : tabs) {
				tab.paint(g);
				if (tab.isSelected()) tab.getOptionTab().paint(g);
			}
		}
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void addMouseListeners(Canvas c) {
		for (Tab tab : tabs) {
			tab.getOptionTab().addListeners(c);
			c.addMouseListener(tab);
		}
	}
}
