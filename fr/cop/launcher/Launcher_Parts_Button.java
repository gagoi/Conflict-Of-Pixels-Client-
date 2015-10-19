package fr.cop.launcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Launcher_Parts_Button extends JButton {

	ImageIcon ico;
	int[] xAvaible, yAvaible;
	Polygon p;
	Font f;
	String text;
	Laucher_Play_Button pb;

	public Launcher_Parts_Button(Polygon p, String text, ImageIcon ico, Laucher_Play_Button pb) {
		this.xAvaible = p.xpoints;
		this.yAvaible = p.ypoints;
		this.p = p;
		this.text = text;
		this.ico = ico;
		this.pb = pb;

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
		Point pt = new Point(x, y);
		return p.contains(pt);
	}

	@Override
	public void paint(Graphics g) {
		int pos = p.ypoints[0];
		int middle = p.ypoints[p.ypoints.length / 2];
		if (pos - middle < 0)
			g.drawImage(ico.getImage(), getX(), pos, null);
		else
			g.drawImage(ico.getImage(), getX(), middle, null);
		g.setColor(Color.RED);
		g.setFont(f);
		g.drawString(this.text, (p.xpoints[0] + 100), (p.ypoints[0] + 58));
		g.dispose();
	}
}
