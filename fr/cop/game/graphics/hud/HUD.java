package fr.cop.game.graphics.hud;

import java.awt.Canvas;
import java.awt.Graphics;

public class HUD {

	private HUD_map map;
	private int width, height;

	public HUD(int w, int h) {
		map = new HUD_map(w, h);
		this.width = w;
		this.height = h;
	}

	public void refreshGraphics(Graphics g, int w, int h) {
		if (height != h || width != w) {
			this.height = h;
			this.width = w;
			map.setMaxCoords(w, h);
		}
		map.refresh(g);
	}

	public void addMouseListeners(Canvas c) {
		c.addMouseListener(map);
		c.addMouseMotionListener(map);
	}
}
