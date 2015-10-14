package fr.cop.game.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;

public class PlayButton extends JButton {

	Polygon p1 = new Polygon();
	Polygon p2 = new Polygon();
	
	Font f;

	public PlayButton(int centerX, int centerY, int radius) {
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
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("play");

			}
		});
	}
	@Override
	public boolean contains(int x, int y) {
		return p1.contains(new Point(x, y)) || p2.contains(new Point(x,y));
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillPolygon(p1);
		g.setColor(Color.BLUE);
		g.fillPolygon(p2);
		
		g.setColor(Color.RED);
		g.setFont(f);
		g.drawString("Play", 480, 360);
		g.dispose();
	}
}
