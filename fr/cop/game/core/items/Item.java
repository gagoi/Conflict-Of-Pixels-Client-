package game.core.items;

public interface Item {
	String stats[] = null;

	default Item setStatsAdded(){
		return this;
	}
}
