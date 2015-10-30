package fr.cop.game.graphics.hud;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import fr.cop.game.core.Game_Frame;

public class HUD {

	private HUD_map map;
	private HUD_Statistiques stats;
	private HUD_SpellsBar spellsBar;
	private int width, height;
	Properties hudProp = new Properties();
	File propHudPropFile = new File(Game_Frame.gameFolder.getPath() + "\\config\\hud.properties");

	public HUD(int w, int h) {
		map = new HUD_map();
		stats = new HUD_Statistiques();
		spellsBar = new HUD_SpellsBar();

		this.width = w;
		this.height = h;
		try {
			loadHUD();
		} catch (Exception e) {
			saveHUD();
		}
	}

	public void loadHUD() throws FileNotFoundException, IOException {
		Game_Frame.logger.logTxt("HUD - Loader", "Start loading...");
		hudProp.load(new FileInputStream(propHudPropFile));
		map.readProps(hudProp);
		stats.readProps(hudProp);
		spellsBar.readProps(hudProp);
		Game_Frame.logger.logTxt("HUD - Loader", "Finish loading...");
	}

	public void saveHUD() {
		Game_Frame.logger.logTxt("HUD - Saving", "Start saving...");
		if (!propHudPropFile.getParentFile().exists()) {
			propHudPropFile.getParentFile().mkdirs();
			Game_Frame.logger.logTxt("HUD - Saving", "Folder error ! Create it.");
		}
		if (!propHudPropFile.exists()) try {
			propHudPropFile.createNewFile();
			Game_Frame.logger.logTxt("HUD - Saving", "File error ! Create it.");
		} catch (IOException e) {
			Game_Frame.logger.logErr("HUD - Saving", "Error during file creation, please send this error to developers.");
			e.printStackTrace();
		}

		map.storeProps(hudProp);
		stats.storeProps(hudProp);
		spellsBar.storeProps(hudProp);

		try {
			hudProp.store(new FileOutputStream(propHudPropFile), "");
			Game_Frame.logger.logTxt("HUD - Saving", "Properties stored in file.");
		} catch (IOException e) {
			Game_Frame.logger.logErr("HUD - Saving", "Error during file saving, please send this error to developers");
			e.printStackTrace();
		}
	}

	public void refreshGraphics(Graphics g, int w, int h) {
		if (height != h || width != w) {
			this.height = h;
			this.width = w;
			map.setMaxCoords(w, h);
			stats.setMaxCoords(w, h);
			spellsBar.setMaxCoords(w, h);
		}
		map.refresh(g);
		stats.refresh(g);
		spellsBar.refresh(g);
	}

	public void addMouseListeners(Canvas c) {
		c.addMouseListener(map);
		c.addMouseMotionListener(map);
		c.addMouseListener(stats);
		c.addMouseMotionListener(stats);
		c.addMouseListener(spellsBar);
		c.addMouseMotionListener(spellsBar);
	}
}
