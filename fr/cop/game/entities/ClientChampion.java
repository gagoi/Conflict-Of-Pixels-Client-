package fr.cop.game.entities;
import fr.cop.common.entities.Entity;
import fr.cop.common.entities.champions.Champion;;

public class ClientChampion{

	private final String DISPLAYED_NAME;
	private final Champion SERVER_CHAMPION;
	private int step;

	public ClientChampion(Champion c, String displayed_name) {
		this.SERVER_CHAMPION = c;
		this.DISPLAYED_NAME = displayed_name;
	}

	public String getDisplayedName() {
		return this.DISPLAYED_NAME;
	}

	public int getRenderWith() {
		return 16;
	}

	public int getRenderHeight() {
		return 32;
	}

	public int getStep() {
		return this.step;
	}

//	@Override
	public void move() {
		this.step++;
		if (this.step > 2) this.step = 0;
//		super.move();
	}

	public Champion getServerChamp() {
		return this.SERVER_CHAMPION;
	}
}
