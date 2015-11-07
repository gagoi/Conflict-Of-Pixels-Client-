package fr.cop.game.graphics.hud;

import java.awt.Graphics;

import fr.cop.game.core.Game_Frame;

public class HUD_SpellsBar extends HUD_Element {

	private HUD_Spell[] spells = new HUD_Spell[4]; // Tableau contenant nos sorts.

	public HUD_SpellsBar() {
		super("/fr/cop/resources/textures/hud/spellsBarBackground", "HUD_SpellsBar"); // On donne à la superclassen, les paramètres nécessaires.
		spells[0] = new HUD_Spell(0, this); // On définit le bouton A.
		spells[1] = new HUD_Spell(1, this); // On définit le bouton Z.
		spells[2] = new HUD_Spell(2, this); // On définit le bouton E.
		spells[3] = new HUD_Spell(3, this); // On définit le bouton R.
		setScale(0.5f);
		setCoords((int) (Game_Frame.size.width/2-getScaledWidth()/2), Game_Frame.size.height-getScaledHeight());
	}

	@Override
	public void refresh(Graphics g) { // On redéfinit la fonction refresh, pour ajouter le dessin des sorts à celui de la barre.
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
