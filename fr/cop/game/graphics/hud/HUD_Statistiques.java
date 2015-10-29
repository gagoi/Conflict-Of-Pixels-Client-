package fr.cop.game.graphics.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.swing.ImageIcon;

import fr.cop.game.core.Game_Frame;

public class HUD_Statistiques extends MouseAdapter {

	private float scale = 0.1f; // Ratio de taille de l'image.
	private Image background = new ImageIcon(getClass().getResource("/fr/cop/resources/textures/hud/mapBackground.png")).getImage();
	private int initialCoordX = 50, initialCoordY = 300, dragZoneSize = 10, clickCoordX = initialCoordX, clickCoordY = initialCoordY;
	private int tempCoordX = initialCoordX, tempCoordY = initialCoordY;
	private int maxX, maxY;
	private int width = (int) (background.getWidth(null) * scale);
	private int height = (int) (background.getHeight(null) * scale);

	public HUD_Statistiques(int maxX, int maxY) {
		background = background.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	public void refresh(Graphics g) {
		g.drawImage(background, tempCoordX, tempCoordY, null);
		g.setColor(Color.RED);
		g.fillRect(tempCoordX, tempCoordY, 100, 100);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clickCoordX = e.getX();
		clickCoordY = e.getY();
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + width && clickCoordY > initialCoordY && clickCoordY < initialCoordY + height) {
			Game_Frame.logger.logTxt("Mouse Listener <MAP:Press>", "Click at (" + clickCoordX + ";" + clickCoordY + ").");
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + width && clickCoordY > initialCoordY && clickCoordY < initialCoordY + height) {

			Game_Frame.logger.logTxt("Mouse Listener <Map:Drag>", "Click at (" + e.getX() + ";" + e.getY() + ").");
			tempCoordX = e.getX() - (clickCoordX - initialCoordX);
			tempCoordY = e.getY() - (clickCoordY - initialCoordY);
		}
	};

	@Override
	public void mouseReleased(MouseEvent e) {
		Game_Frame.logger.logTxt("Mouse Listenet <Map:Release>", "Unclick at (" + e.getX() + ";" + e.getY() + ").");

		if (tempCoordX != initialCoordX || tempCoordY != initialCoordY) {
			initialCoordX = tempCoordX;
			initialCoordY = tempCoordY;
		}
	}

	public void setMaxCoords(int w, int y) {
		this.maxX = w;
		this.maxY = y;

	}


	public void storeProps(Properties prop){
		prop.setProperty("stats_scale", "" + scale);
		prop.setProperty("stats_coord_x", "" + initialCoordX);
		prop.setProperty("stats_coord_y", "" + initialCoordY);
	}
	
	public void readProps(Properties prop){
		scale = Float.parseFloat(prop.getProperty("map_scale"));
		initialCoordX = tempCoordX = Integer.parseInt(prop.getProperty("map_coord_x"));
		initialCoordY = tempCoordY = Integer.parseInt(prop.getProperty("map_coord_y"));
	}
}
