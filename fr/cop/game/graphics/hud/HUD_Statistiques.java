package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import fr.cop.common.Stats;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.graphics.Sprites;

public class HUD_Statistiques extends HUD_Element {

	private ArrayList<String> statsToDisplay = new ArrayList<String>();

	public HUD_Statistiques() {
		super("HUD_Stats", 800, 1080);
		this.setScale(0.2f);
		this.setCoords(0, Game_Frame.size.height - getScaledHeight());

		Stats s = new Stats();
		s.setStat(Stats.STAT_ID_Physical_Damage, 500);
		s.setStat(Stats.STAT_ID_Physical_Resistance, 35);
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Physical_Damage) + "");
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Magic_Damage) + "");
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Physical_Resistance) + "");
		statsToDisplay.add("M Res : " + s.getStat(Stats.STAT_ID_Magic_Resistance));
		statsToDisplay.add("AS : " + s.getStat(Stats.STAT_ID_Attack_Speed));
		statsToDisplay.add("Range : " + s.getStat(Stats.STAT_ID_Range));
		statsToDisplay.add("Crit : " + s.getStat(Stats.STAT_ID_Critical_Chance));
		statsToDisplay.add("CDR : " + s.getStat(Stats.STAT_ID_Cooldown_Reduction));
		statsToDisplay.add("RPD : " + s.getStat(Stats.STAT_ID_Returned_Physical_Damage));
		statsToDisplay.add("RMD : " + s.getStat(Stats.STAT_ID_Returned_Magic_Damage));
	}

	@Override
	public void refresh(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getPosX(), getPosY(), getScaledWidth(), getScaledHeight());
		g.setColor(Color.BLACK);

		g.drawImage(Sprites.physical_damage_icon.getImage(), getPosX() + 5, getPosY() + 10, null);
		g.drawImage(Sprites.magical_damage_icon.getImage(), getPosX() + getScaledWidth() / 2 - 5, getPosY() + 10, null);
		g.drawImage(Sprites.physical_resistance_icon.getImage(), getPosX() + 5, getPosY() + 40, null);

		g.drawString(statsToDisplay.get(0), getPosX() + 25, getPosY() + 25);
		g.drawString(statsToDisplay.get(1), getPosX() + getScaledWidth() / 2 + 20, getPosY() + 25);
		g.drawString(statsToDisplay.get(2), getPosX() + 25, getPosY() + 55);
	}
}