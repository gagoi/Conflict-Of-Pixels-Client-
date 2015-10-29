package fr.cop.game.graphics.hud;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;

public class SpellBar extends JComponent {
	private JButton spell1, spell2, spell3, spell4;

	public SpellBar() {
		spell1 = new JButton();
		spell2 = new JButton();
		spell3 = new JButton();
		spell4 = new JButton();
		setBackground(Color.BLUE);
		setSize(500, 50);
	}



}
