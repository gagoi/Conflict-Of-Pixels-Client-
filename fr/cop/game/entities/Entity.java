package fr.cop.game.entities;

import fr.cop.common.entities.BaseEntity;
import fr.cop.game.graphics.sprites.BaseSprite;
import fr.cop.game.graphics.sprites.SpritesList;

public class Entity extends BaseEntity{
	
	private BaseSprite sprite;
	
	public Entity() {
		this.UUID = "debug";
		this.sprite = SpritesList.debug.getScaledInstance(50, 50);
	}

	public BaseSprite getSprite(){
		return this.sprite;
	}
	
}
