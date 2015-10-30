package fr.cop.game.graphics.hud;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class HUD_Spell extends HUD_Element {

	private HUD_SpellsBar spellsBar;

	public HUD_Spell(int id, HUD_SpellsBar spellsBar) {
		super("/fr/cop/resources/images/characters/Test/spellIcon/" + id, "HUD_Spell_" + id);
		this.spellsBar = spellsBar;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	
	@Override
	public void setScale(float scale) {
		super.setScale(scale*15);
	}
	
	@Override
	public void refresh(Graphics g) {
		super.refresh(g);
	}
	
	@Override
	public int getPosX() {
		int middlePos = spellsBar.getPosX()+(spellsBar.getScaledWidth()/2);
		return 400;
//		return middlePos;
	}
	
	@Override
	public int getPosY() {
		return spellsBar.getPosY() + ((spellsBar.getScaledHeight() - background.getHeight(null)) / 2);
	}
}
