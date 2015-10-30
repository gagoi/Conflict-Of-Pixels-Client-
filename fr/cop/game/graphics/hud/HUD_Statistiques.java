package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;

public class HUD_Statistiques extends HUD_Element {

	public HUD_Statistiques() {
		super("HUD_Stats", 800, 1080);
		this.setScale(0.1f);
		this.setCoords(25, 600);
	}

	@Override
	public void refresh(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getPosX(), getPosY(), getScaledWidth(), getScaledHeight());
	}
}