package fr.cop.game.graphics.sprites;

public class ChampionSprite {

	public static final int DIRECTION_TOP = 0;
	public static final int DIRECTION_BOTTOM = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_LEFT = 3;

	public static final int STEP_CENTER = 0;
	public static final int STEP_RIGHT = 1;
	public static final int STEP_LEFT = 2;

	private final BaseSprite spells_sprites[] = new BaseSprite[4];
	private final BaseSprite champions_sprites[][] = new BaseSprite[4][3];

	public ChampionSprite(String name) {
		BaseSheet sheet = new BaseSheet("champions/" + name, 4, 7);

		spells_sprites[0] = new BaseSprite(0, 0, sheet);
		spells_sprites[1] = new BaseSprite(1, 0, sheet);
		spells_sprites[2] = new BaseSprite(2, 0, sheet);
		spells_sprites[3] = new BaseSprite(3, 0, sheet);

		for (int i = 0; i < champions_sprites.length; i++) {
			for (int j = 0; j < champions_sprites[i].length; j++) {
				champions_sprites[i][j] = new BaseSprite(i, j * 2, 16, 32, name + "_" + i + "_" + j, sheet);
			}
		}
	}

	public BaseSprite getSpellSprite(int spellID) {
		return this.spells_sprites[spellID];
	}

	public BaseSprite getChampionSprite(int direction_code, int step_code) {
		return this.champions_sprites[direction_code][step_code];
	}

}
