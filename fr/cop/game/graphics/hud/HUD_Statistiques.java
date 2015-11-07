package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;

import fr.cop.game.core.Game_Frame;

public class HUD_Statistiques extends HUD_Element {

	public HUD_Statistiques() {
		super("HUD_Stats", 800, 1080);
		this.setScale(0.2f);
		this.setCoords(0, Game_Frame.size.height-getScaledHeight());
	}

	@Override
	public void refresh(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getPosX(), getPosY(), getScaledWidth(), getScaledHeight());
	}
}