package fr.cop.game.graphics.inGameOptions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.InputStream;

public class OptionElement {

	private int posX, posY;
	private String name;
	static int internalTimer;
	public static Font f;

	public OptionElement(String name, int posX, int posY) {
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		try {
			InputStream is = getClass().getResourceAsStream("/fr/cop/resources/menus/fonts/zekton_rg.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			f = font.deriveFont(20f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(f);
	}

	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	
	public String getName(){
		return name;
	}
	

	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}

}
