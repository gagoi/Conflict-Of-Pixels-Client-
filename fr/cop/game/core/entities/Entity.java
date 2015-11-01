package fr.cop.game.core.entities;

import fr.cop.game.core.usefull.Buffs;
import fr.cop.game.core.usefull.Stats;
import fr.cop.game.graphics.Sprite;

public interface Entity {

	public String getDisplayedName();
	
	public String getUUID();
	
	public int getPosX();
	
	public int getPosY();
	
	public int getWidth();
	
	public int getHeight();
	
	public boolean isHitting();
	
	public Buffs[] getBuffs();
	
	public void addBuff(Buffs buff);
	
	public void removeBuff(Buffs buff);
	
	public Stats getStats();
	
	public Sprite getSprite();
	
	public boolean isFocusable();
	
	public boolean isInvincible();
	
	public void onDeathEvent();
	
	public void onSpawnEvent();
	
	public void onRespawnEven();
	
	public void onDamageTaken();
	
	public void onDamageDealt();
}
