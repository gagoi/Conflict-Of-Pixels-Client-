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
	private int width, height;
	Properties hudProp = new Properties();
	File propHudPropFile = new File(Game_Frame.gameFolder.getPath() + "\\config\\hud.properties");

	public HUD(int w, int h) {
		map = new HUD_map(w, h);
		stats = new HUD_Statistiques(w, h);
		this.width = w;
		this.height = h;

		try {
			loadHUD();
		} catch (Exception e) {
			saveHUD();
		}
	}

	public void loadHUD() throws FileNotFoundException, IOException {
		hudProp.load(new FileInputStream(propHudPropFile));
		map.readProps(hudProp);
		stats.readProps(hudProp);
	}

	public void saveHUD() {
		if (!Game_Frame.gameFolder.exists()) Game_Frame.gameFolder.mkdirs();
		if(!propHudPropFile.getParentFile().exists()) propHudPropFile.getParentFile().mkdirs();
		if (!propHudPropFile.exists()) try {
			System.out.println(propHudPropFile.getPath());
			propHudPropFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		map.storeProps(hudProp);
		stats.storeProps(hudProp);

		try {
			hudProp.store(new FileOutputStream(propHudPropFile), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void refreshGraphics(Graphics g, int w, int h) {
		if (height != h || width != w) {
			this.height = h;
			this.width = w;
			map.setMaxCoords(w, h);
			stats.setMaxCoords(w, h);
		}
		map.refresh(g);
		stats.refresh(g);
	}

	public void addMouseListeners(Canvas c) {
		c.addMouseListener(map);
		c.addMouseMotionListener(map);
		c.addMouseListener(stats);
		c.addMouseMotionListener(stats);
	}
}
