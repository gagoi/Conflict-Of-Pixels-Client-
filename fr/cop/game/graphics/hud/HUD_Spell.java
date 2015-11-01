package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class HUD_Spell extends HUD_Element {

	private HUD_SpellsBar spellsBar;
	private int id;

	public HUD_Spell(int id, HUD_SpellsBar spellsBar) {
		super("/fr/cop/resources/images/characters/Test/spellIcon/" + id, "HUD_Spell_" + id, 1f);
		this.id = id;
		this.spellsBar = spellsBar;
//		background = background.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		System.out.println("/fr/cop/resources/images/characters/Test/spellIcon/" + id);
		System.out.println(background.getWidth(null) + "-" + background.getWidth(null));
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	
	@Override
	public void refresh(Graphics g) {
		g.drawImage(background, getPosX(), getPosY(),getScaledWidth(), getScaledHeight(), null);
		g.setColor(Color.BLACK);
		g.drawRect(getPosX(), getPosY(), getScaledWidth(), getScaledHeight());
	}
	
	@Override
	public int getPosX() {
		int pos = (int) (spellsBar.getPosX()+(spellsBar.getScaledWidth()/2)-(1.5*getScaledWidth()/2)-2*getScaledWidth()+id*(getScaledWidth()/2+getScaledWidth()));
		return pos;
	}
	
	@Override
	public int getPosY() {
		return spellsBar.getPosY() + ((spellsBar.getScaledHeight() - background.getHeight(null)) / 2);
	}
}
