package fr.cop.game.graphics.hud;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.swing.ImageIcon;

import fr.cop.game.core.Game_Frame;

public class HUD_Element extends MouseAdapter {

	protected float scale = 0.1f;
	protected Image background;
	private int initialCoordX = 50, initialCoordY = 300, clickCoordX = initialCoordX, clickCoordY = initialCoordY;
	protected int tempCoordX = initialCoordX;
	protected int tempCoordY = initialCoordY;
	protected int maxX, maxY;
	protected int width;
	protected int height;

	private String hudElementName;

	public HUD_Element(String imageName, String hudElementName) {
		background = new ImageIcon(getClass().getResource( imageName + ".png")).getImage();
		width = background.getWidth(null);
		height = background.getHeight(null);
		this.hudElementName = hudElementName;
	}

	public HUD_Element(String hudElementName, int width, int height) {
		this.width = width;
		this.height = height;
		this.hudElementName = hudElementName;
	}

	public void refresh(Graphics g) {
		if (background != null) g.drawImage(background, getPosX(), getPosY(), getScaledWidth(), getScaledHeight(), null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clickCoordX = e.getX();
		clickCoordY = e.getY();
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + getScaledWidth() && clickCoordY > initialCoordY && clickCoordY < initialCoordY + getScaledHeight()) {
			Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Press>", "Click at (" + clickCoordX + ";" + clickCoordY + ").");
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + getScaledWidth() && clickCoordY > initialCoordY && clickCoordY < initialCoordY +getScaledHeight()) {
			Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Drag>", "Click at (" + e.getX() + ";" + e.getY() + ").");
			tempCoordX = e.getX() - (clickCoordX - initialCoordX);
			tempCoordY = e.getY() - (clickCoordY - initialCoordY);
		}
	};

	@Override
	public void mouseReleased(MouseEvent e) {
		Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Release>", "Unclick at (" + e.getX() + ";" + e.getY() + ").");

		if (tempCoordX != initialCoordX || tempCoordY != initialCoordY) {
			initialCoordX = tempCoordX;
			initialCoordY = tempCoordY;
		}
	}

	public void setMaxCoords(int w, int h) {
		this.maxX = w;
		this.maxY = h;
	}

	public void storeProps(Properties prop) {
		Game_Frame.logger.logTxt(hudElementName + " Saving", hudElementName + " Properties.");
		prop.setProperty(hudElementName + "_scale", "" + this.getScale());
		prop.setProperty(hudElementName + "_coord_x", "" + initialCoordX);
		prop.setProperty(hudElementName + "_coord_y", "" + initialCoordY);
	}

	public void readProps(Properties prop) {
		Game_Frame.logger.logTxt(hudElementName + " Loading", hudElementName + " Properties.");
		this.setScale(Float.parseFloat(prop.getProperty(hudElementName + "_scale")));
		this.setCoords(Integer.parseInt(prop.getProperty(hudElementName + "_coord_x")), Integer.parseInt(prop.getProperty(hudElementName + "_coord_y")));
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void setCoords(int x, int y) {
		this.initialCoordX = this.tempCoordX = x;
		this.initialCoordY = this.tempCoordY = y;
	}

	public int getPosX() {
		return tempCoordX;
	}

	public int getPosY() {
		return tempCoordY;
	}

	public float getScale() {
		return scale;
	}

	public int getScaledWidth() {
		return (int) (width * getScale());
	}

	public int getScaledHeight() {
		return (int) (height * getScale());
	}
}
