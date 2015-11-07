package fr.cop.game.graphics.hud;

import java.awt.Graphics;

public class HUD_SpellsBar extends HUD_Element {

	private HUD_Spell[] spells = new HUD_Spell[4]; // Tableau contenant nos sorts.

	public HUD_SpellsBar() {
		super("/fr/cop/resources/textures/hud/spellsBarBackground", "HUD_SpellsBar"); // On donne � la superclassen, les param�tres n�cessaires.
		spells[0] = new HUD_Spell(0, this); // On d�finit le bouton A.
		spells[1] = new HUD_Spell(1, this); // On d�finit le bouton Z.
		spells[2] = new HUD_Spell(2, this); // On d�finit le bouton E.
		spells[3] = new HUD_Spell(3, this); // On d�finit le bouton R.
	}

	@Override
	public void refresh(Graphics g) { // On red�finit la fonction refresh, pour ajouter le dessin des sorts � celui de la barre.
		super.refresh(g);
		spells[0].refresh(g);
		spells[1].refresh(g);
		spells[2].refresh(g);
		spells[3].refresh(g);
	}

//	@Override
//	public void mousePressed(MouseEvent e) {
//		super.mousePressed(e);
//	}
}
