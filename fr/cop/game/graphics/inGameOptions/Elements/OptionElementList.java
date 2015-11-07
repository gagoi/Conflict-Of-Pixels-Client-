package fr.cop.game.graphics.inGameOptions.Elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class OptionElementList extends OptionElement implements MouseListener {

	ArrayList<?> list;
	boolean isClicked;

	public OptionElementList(String name, int posX, int posY, ArrayList<?> list) {
		super(name, posX, posY);
		this.list = list;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (isClicked) {
			for (int i = 0; i < list.size(); i++) {
				g.setColor(Color.WHITE);
				g.fillRect(getPosX(), getPosY() + i * 50, getWidth(), 50);
				g.setColor(Color.BLACK);
				g.drawRect(getPosX(), getPosY() + i * 50, getWidth(), 50);
				g.drawString(list.get(i).toString(), getPosX(), getPosY() + i * 50);
			}
		} else {
			g.setColor(Color.GREEN);
			g.fillRect(getPosX(), getPosY(), getWidth(), getHeight());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int clickCoordX = e.getX();
		int clickCoordY = e.getY();

		if (clickCoordX > getPosX() && clickCoordX < getPosX() + getWidth() && clickCoordY > getPosY() && clickCoordY < getPosY() + getHeight()) {
			isClicked = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWidth() {
		return 200;
	}

	@Override
	public int getHeight() {
		return 50;
	}

}
