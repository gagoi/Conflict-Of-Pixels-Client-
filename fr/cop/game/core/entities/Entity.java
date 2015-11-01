package fr.cop.game.core.entities;

import fr.cop.game.core.usefull.Stats;

public interface Entity {

	public String getDisplayedName();
	
	public String getUUID();
	
	public int getPosX();
	
	public int getPosY();
	
	public int getWidth();
	
	public int getHeight();
	
	public boolean isHitting();
	
	public boolean canMove();
	
	public boolean isSlowed();
	
	public Stats getStats();
}
