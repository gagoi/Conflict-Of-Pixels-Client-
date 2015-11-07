package fr.cop.game.graphics.inGameOptions.Elements;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.cop.game.core.Game_Frame;

public class OptionTab extends OptionElement implements MouseListener {

	private OptionElementBoolean test, test2;

	public OptionTab(String name, int posX, int posY) {
		super(name, posX, posY);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(0xFF00FF));
		g.fillRect(getPosX(), getPosY(), 400, Game_Frame.GAME.optionFrame.tabs.size()*50);
	}

	public void addListeners(Canvas c) {
		c.addMouseListener(test);
		c.addMouseListener(test2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
