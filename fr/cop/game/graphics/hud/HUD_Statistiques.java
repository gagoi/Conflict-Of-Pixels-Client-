package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import fr.cop.common.Stats;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.graphics.sprites.Sprites;

public class HUD_Statistiques extends HUD_Element {

	private ArrayList<String> statsToDisplay = new ArrayList<String>();

	public HUD_Statistiques() {
		super("HUD_Stats", 800, 1080);
		this.setScale(0.5f);
		this.setCoords(0, Game_Frame.size.height - getScaledHeight());

		Stats s = new Stats();
		s.setStat(Stats.STAT_ID_Physical_Damage, 0);
		s.setStat(Stats.STAT_ID_Magic_Damage, 1);
		s.setStat(Stats.STAT_ID_Physical_Resistance, 2);
		s.setStat(Stats.STAT_ID_Magic_Resistance, 3);
		s.setStat(Stats.STAT_ID_Physical_Pen, 4);
		s.setStat(Stats.STAT_ID_Magic_Pen, 5);
		s.setStat(Stats.STAT_ID_Cooldown_Reduction, 6);
		s.setStat(Stats.STAT_ID_Attack_Speed, 7);
		s.setStat(Stats.STAT_ID_Move_Speed, 8);
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Physical_Damage) + "");
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Magic_Damage) + "");
		
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Physical_Resistance) + "");
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Magic_Resistance) + "");
		
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Physical_Pen) + "");
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Magic_Pen) + "");
		
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Cooldown_Reduction) + "");
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Attack_Speed) + "");
		statsToDisplay.add(s.getStat(Stats.STAT_ID_Move_Speed) + "");
	}

	@Override
	public void refresh(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getPosX(), getPosY(), getScaledWidth(), getScaledHeight());
		g.setColor(Color.BLACK);

		g.drawImage(Sprites.physical_damage_icon.getImage(), getPosX() + 5, getPosY() + 10, null);
		g.drawImage(Sprites.magical_damage_icon.getImage(), getPosX() + getScaledWidth() / 2 - 5, getPosY() + 10, null);
		g.drawImage(Sprites.physical_resistance_icon.getImage(), getPosX() + 5, getPosY() + 40, null);
		g.drawImage(Sprites.magical_resistance_icon.getImage(), getPosX() + getScaledWidth() / 2 - 5, getPosY() + 40, null);
		g.drawImage(Sprites.physical_penetration_icon.getImage(), getPosX() + 5, getPosY() + 80, null);
		g.drawImage(Sprites.magical_penetration_icon.getImage(), getPosX() + getScaledWidth() / 2 - 5, getPosY() + 80, null);
		g.drawImage(Sprites.cooldown_reduction_icon.getImage(), getPosX() + 5, getPosY() + 120, null);
		g.drawImage(Sprites.move_speed_icon.getImage(), getPosX() + getScaledWidth() / 2 - 5, getPosY() + 120, null);
		g.drawImage(Sprites.attack_speed_icon.getImage(), getPosX() + 5, getPosY() + 160, null);
		
		
		g.drawString(statsToDisplay.get(0), getPosX() + 25, getPosY() + 25);
		g.drawString(statsToDisplay.get(1), getPosX() + getScaledWidth() / 2 + 20, getPosY() + 25);
		g.drawString(statsToDisplay.get(2), getPosX() + 25, getPosY() + 55);
		g.drawString(statsToDisplay.get(3), getPosX() + getScaledWidth() / 2 + 20, getPosY() + 55);
		g.drawString(statsToDisplay.get(4), getPosX() + 25, getPosY() + 85);
		g.drawString(statsToDisplay.get(5), getPosX() + getScaledWidth() / 2 + 20, getPosY() + 85);
		g.drawString(statsToDisplay.get(6), getPosX() + 25, getPosY() + 115);
		g.drawString(statsToDisplay.get(8), getPosX() + getScaledWidth() / 2 + 20, getPosY() + 115);
		
	}
}