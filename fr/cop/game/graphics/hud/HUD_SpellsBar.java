package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class HUD_SpellsBar extends HUD_Element {

	private Image[] spells = new Image[4];

	public HUD_SpellsBar() {
		super("spellsBarBackground", "HUD_SpellsBar");
		for (int i = 0; i < spells.length; i++) {
			spells[i] = new ImageIcon(getClass().getResource("/fr/cop/resources/images/characters/Test/spellIcon/" + i + ".png")).getImage();
			spells[i] = spells[i].getScaledInstance((int) (spells[i].getWidth(null) * 12 * this.getScale()), (int) (spells[i].getHeight(null) * 12 * this.getScale()), Image.SCALE_SMOOTH);
		}
	}

	@Override
	public void refresh(Graphics g) {
		super.refresh(g);

		g.setColor(Color.BLUE);
		g.fillRect(getPosX() + 10, getPosY() + 10, getScaledWidth() - 20, getScaledHeight() - 20);
		for (int i = 0; i < spells.length; i++) {
			g.drawImage(spells[i],(int) (getPosX() + ((getScaledWidth()-spells[i].getWidth(null))/2) + i*spells[i].getWidth(null)*1.1f - (2*spells[i].getWidth(null))) , getPosY() + ((getScaledHeight()-spells[i].getHeight(null))/2), null);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
	}
}
