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
				System.out.println("Appuyé");
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
