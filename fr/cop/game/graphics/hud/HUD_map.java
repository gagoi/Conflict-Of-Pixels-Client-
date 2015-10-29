package fr.cop.game.graphics.hud;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import fr.cop.game.core.Game_Frame;

public class HUD_map extends MouseAdapter implements MouseMotionListener {

	private float scale = 0.1f; // Ratio de taille de l'image.
	private Image background = new ImageIcon(getClass().getResource("/fr/cop/resources/textures/hud/mapBackground.png")).getImage();
	private int initialCoordX = 25, initialCoordY = 75, dragZoneSize = 10, clickCoordX = initialCoordX, clickCoordY = initialCoordY;
	private int tempCoordX = initialCoordX, tempCoordY = initialCoordY;
	private int maxX, maxY;
	private int width = (int) (background.getWidth(null) * scale);
	private int height = (int) (background.getHeight(null) * scale);

	public HUD_map(int maxX, int maxY) {
		background = background.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	public void refresh(Graphics g) {
		g.drawImage(background, tempCoordX, tempCoordY, null);
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
		Game_Frame.logger.logTxt("Mouse Listener <Map:Drag>", "Click at (" + e.getX() + ";" + e.getY() + ").");
		tempCoordX = e.getX() - (clickCoordX - initialCoordX);
		tempCoordY = e.getY() - (clickCoordY - initialCoordY);
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
}
