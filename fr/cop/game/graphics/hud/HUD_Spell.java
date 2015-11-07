package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class HUD_Spell extends HUD_Element {

	private HUD_SpellsBar spellsBar;
	private int id;

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
		g.drawImage(background, getPosX(), getPosY(),getScaledWidth(), getScaledHeight(), null); // On dessine l'arri�re-plan du sort (son ic�ne).
		g.setColor(Color.BLACK); // On met la couleur en noire...
		g.drawRect(getPosX(), getPosY(), getScaledWidth(), getScaledHeight()); // ... pour dessiner un contour.
	}
	
	@Override
	public int getPosX() { // On red�finit la fonction getPosX(), pour pouvoir le centrer dans la barre de sorts.
		int pos = (int) (spellsBar.getPosX()+(spellsBar.getScaledWidth()/2)-(1.5*getScaledWidth()/2)-2*getScaledWidth()+id*(getScaledWidth()/2+getScaledWidth()));
		return pos;
	}
	
	@Override
	public int getPosY() { // On red�finit la fonction getPosY(), pour pouvoir le centrer dans la barre de sorts.
		return spellsBar.getPosY() + (spellsBar.getScaledHeight()- getScaledWidth())/ 2;
	}
	
	@Override
	public int getScaledWidth() { // On red�finit la fonction getScaledWidth(), pour ne pas avoir un sort trop petit par rapport � la barre.
		return (int) (spellsBar.getScaledHeight()/2.5); 
	}
	
	@Override
	public int getScaledHeight() { // On red�finit la fonction getScaledHeight(), pour renvoyer la valeur getScaledWidth() et avoir un carr�.
		return getScaledWidth();
	}
}
