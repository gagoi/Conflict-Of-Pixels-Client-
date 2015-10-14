package fr.cop.game.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonTest extends JButton {

	Icon ico;
	int x0, x1, x2, x3; // Points clés
	int y0, y1, y2, y3, y4; // Points clés
	int a1 = (y0-y2)/(y1-y2), a2 = (y0-y3)/(y3-y4); // Coefficients directeurs des droites inclinees.

	public ButtonTest(Icon ico) {
		super(ico);
		setPreferredSize(new Dimension(90, 90));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setOpaque(true);

		this.ico = ico;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("AppuyÃ©");
			}
		});
	}

	@Override
	public boolean contains(int x, int y) {
		
		return true;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 100, 100);
		super.paintComponent(g);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 100, 100);
		super.paint(g);
	}

}
