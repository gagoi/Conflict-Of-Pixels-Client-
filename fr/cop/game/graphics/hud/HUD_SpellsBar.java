package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class HUD_SpellsBar extends HUD_Element {

	private HUD_Spell[] spells = new HUD_Spell[4];

	public HUD_SpellsBar() {
		super("/fr/cop/resources/textures/hud/spellsBarBackground", "HUD_SpellsBar");
		spells[0] = new HUD_Spell(0, this);
		spells[1] = new HUD_Spell(1, this);
		spells[2] = new HUD_Spell(2, this);
		spells[3] = new HUD_Spell(3, this);
	}

	@Override
	public void refresh(Graphics g) {
		super.refresh(g);

		spells[0].refresh(g);
		spells[1].refresh(g);
		spells[2].refresh(g);
		spells[3].refresh(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
	}
}
