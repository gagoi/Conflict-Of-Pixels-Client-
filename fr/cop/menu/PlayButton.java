package fr.cop.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PlayButton extends JButton {

	Polygon p1 = new Polygon();
	Polygon p2 = new Polygon();
	
	int centerX, centerY, radius;
	
	Font f;
	ImageIcon ico;

	public PlayButton(int centerX, int centerY, int radius, ImageIcon ico) {
		this.ico = ico;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		for (int i = -radius; i <= radius; i++) {

			p1.addPoint(i + centerX, (int) Math.sqrt(radius * radius - i * i) + centerY);
			p2.addPoint(i + centerX, (int) -Math.sqrt(radius * radius - i * i) + centerY);
		}

		try {
			InputStream is = getClass().getResourceAsStream("/fr/cop/resources/menus/fonts/zekton_rg.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			f = font.deriveFont(70f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setOpaque(true);
	
	}
	@Override
	public boolean contains(int x, int y) {
		return p1.contains(new Point(x, y)) || p2.contains(new Point(x,y));
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(ico.getImage(), centerX-radius, centerY-radius, null);
		
		g.setColor(Color.RED);
		g.setFont(f);
		g.drawString("Play", 450, 380);
		g.dispose();
	}
}
