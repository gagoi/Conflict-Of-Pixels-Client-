package fr.cop.game.core;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelTest extends JPanel{

	public PanelTest() {
		setSize(1280, 720);
		add(new ButtonTest(new ImageIcon(getClass().getResource("/fr/cop/resources/images/characters/Test/spellIcon/0.png"))), BorderLayout.CENTER);
	}

}
