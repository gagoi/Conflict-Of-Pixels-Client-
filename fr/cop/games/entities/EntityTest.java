package fr.cop.games.entities;

import java.awt.Polygon;
import java.util.Vector;

import fr.cop.common.Buffs;
import fr.cop.common.Stats;
import fr.cop.common.entities.Entity;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.graphics.Sprites;

public class EntityTest implements Entity {

	Vector<Buffs> buffs = new Vector<Buffs>();
	Stats stats = new Stats();

	public EntityTest() {
		System.out.println("Create Entity");
		Game_Frame.GAME.entities.add(this);
	}

	@Override
	public Polygon getHitbox() {
		int[] x = { -getWidth() / 2, getWidth() / 2, getWidth() / 2, -getWidth() / 2 };
		int[] y = { -getHeight() / 2, getHeight() / 2, getHeight() / 2, -getHeight() / 2 };
		return new Polygon(x, y, 4);
	}

	@Override
	public String getDisplayedName() {
		return "Entity Test";
	}

	@Override
	public String getUUID() {
		return "test_0x000001";
	}

	@Override
	public int getPosX() {
		return 400;
	}

	@Override
	public int getPosY() {
		return 400;
	}

	@Override
	public int getWidth() {
		return 10;
	}

	@Override
	public int getHeight() {
		return 10;
	}

	@Override
	public Vector<Buffs> getBuffs() {
		return buffs;
	}

	@Override
	public Stats getStats() {
		return stats;
	}

	@Override
	public boolean isFocusable() {
		return true;
	}

	@Override
	public boolean isInvincible() {
		return false;
	}

	@Override
	public void onDeathEvent() {
		Game_Frame.logger.logTxt("<EntityTest:Death>", "Entity dead");
	}

	@Override
	public void onSpawnEvent() {
		Game_Frame.logger.logTxt("<EntityTest:Spawn>", "Entity alive");
	}

	@Override
	public void onRespawnEven() {
		Game_Frame.logger.logTxt("<EntityTest:Respawn>", "Entity alive");
	}

	@Override
	public void onDamageTaken() {
		Game_Frame.logger.logTxt("<EntityTest:DamageTaken>", "Entity take damage");
	}

	@Override
	public void onDamageDealt() {
		Game_Frame.logger.logTxt("<EntityTest:DamageDealt>", "Entity hit");
	}

	public int[] getSprite() {
		return Sprites.tree.pixels;
	}

	@Override
	public boolean mustBeDraw() {
		return true;
	}
}
