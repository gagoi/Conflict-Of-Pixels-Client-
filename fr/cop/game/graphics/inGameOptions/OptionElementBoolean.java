package fr.cop.game.graphics.inGameOptions;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class OptionElementBoolean extends OptionElement implements MouseListener {

	private static Image bg = new ImageIcon(Tab.class.getResource("/fr/cop/resources/textures/inGameOptions/booleanBG.png")).getImage();
	private static Image fg = new ImageIcon(Tab.class.getResource("/fr/cop/resources/textures/inGameOptions/booleanFG.png")).getImage();

	private int tempX;
	private int privateTimer;
	private boolean isEnable;

	public OptionElementBoolean(String name, int posX, int posY, boolean isEnable) {
		super(name, posX, posY);
		this.isEnable = isEnable;
		if (isEnable) tempX = getPosX() + 40;
		else tempX = getPosX();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, getPosX(), getPosY(), null);
		g.drawImage(fg, tempX, getPosY(), null);
	}

	public void swap() {
		isEnable = !isEnable;
		if (isEnable) tempX = getPosX() + 40;
		else tempX = getPosX();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (x > this.getPosX() && x < this.getPosX() + 80 && y > this.getPosY() && y < this.getPosY() + 20) this.swap();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
