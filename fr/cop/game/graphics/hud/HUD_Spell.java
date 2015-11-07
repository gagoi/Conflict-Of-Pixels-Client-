package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class HUD_Spell extends HUD_Element {

	private HUD_SpellsBar spellsBar;
	private int id;
	private static Image border = new ImageIcon(HUD_Spell.class.getResource("/fr/cop/resources/textures/hud/spellBorders.png")).getImage();

	public HUD_Spell(int id, HUD_SpellsBar spellsBar) { //Obejt Spell
		super("/fr/cop/resources/images/characters/Test/spellIcon/" + id, "HUD_Spell_" + id, 1f); // On d�finit les param�tres de sa superclass.
		this.id = id; // On met une variable d'instance de la valeur id.
		this.spellsBar = spellsBar; // On met une variable d'instance de la spellBar, � laquelle il appartient.
	}

	@Override
	public void mouseDragged(MouseEvent e) { // On supprime la possibilit� de faire un cliquer/glisser pour d�placer les sorts. (Ils doivent d�pendre de la barre de sort).
	}

	@Override
	public void refresh(Graphics g) { // On red�finit la fonction refresh, pour dessiner ce que l'on veut.
		g.drawImage(background, getPosX(), getPosY(), getScaledWidth(), getScaledHeight(), null); // On dessine l'arri�re-plan du sort (son ic�ne).
		g.drawImage(border, getPosX()-5, getPosY()-5, getScaledWidth()+10, getScaledHeight()+10, null);
	}

	@Override
	public int getPosX() { // On red�finit la fonction getPosX(), pour pouvoir le centrer dans la barre de sorts.
		int pos = (int) (spellsBar.getPosX() + (spellsBar.getScaledWidth() / 2) - (1.5 * getScaledWidth() / 2) - 2 * getScaledWidth() + id * (getScaledWidth() / 2 + getScaledWidth()));
		return pos;
	}

	@Override
	public int getPosY() { // On red�finit la fonction getPosY(), pour pouvoir le centrer dans la barre de sorts.
		return spellsBar.getPosY() + (spellsBar.getScaledHeight() - getScaledWidth()) / 2;
	}

	@Override
	public int getScaledWidth() { // On red�finit la fonction getScaledWidth(), pour ne pas avoir un sort trop petit par rapport � la barre.
		return (int) (spellsBar.getScaledHeight() / 2.5);
	}

	@Override
	public int getScaledHeight() { // On red�finit la fonction getScaledHeight(), pour renvoyer la valeur getScaledWidth() et avoir un carr�.
		return getScaledWidth();
	}
}
