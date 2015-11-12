package fr.cop.game.graphics.inGameOptions.Elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import fr.cop.game.core.Game_Frame;

public class Tab extends OptionElement implements MouseListener {

	private static Image bgUnselected = new ImageIcon(Tab.class.getResource("/fr/cop/resources/textures/inGameOptions/tab.png")).getImage();
	private static Image bgSelected = new ImageIcon(Tab.class.getResource("/fr/cop/resources/textures/inGameOptions/selectedTab.png")).getImage();

	private boolean isSelected;
	private OptionTab optionTab;

	public Tab(String name, int posX, int posY, OptionTab optionTab) {
		super(name, posX, posY);
		setPosX(posX);
		setPosY(posY);
		this.optionTab = optionTab;

	}

	public void paint(Graphics g) {
		super.paint(g);
		if (isSelected) g.drawImage(bgSelected, getPosX(), getPosY(), null);
		else g.drawImage(bgUnselected, getPosX(), getPosY(), null);
		g.drawString(getName(), getPosX() + 100 - getName().length() * 5, getPosY() + 25);
	}

	public void selectTab() {
		isSelected = true;
	}

	public void unSelectTab() {
		isSelected = false;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public OptionTab getOptionTab() {
		return optionTab;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int clickCoordX = e.getX();
		int clickCoordY = e.getY();

		if (clickCoordX > getPosX() && clickCoordX < getPosX() + getWidth() && clickCoordY > getPosY() && clickCoordY < getPosY() + getHeight()) {
			for (Tab tab : Game_Frame.GAME.optionFrame.tabs) {
				tab.unSelectTab();
			}
			selectTab();
		}
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
	
	@Override
	public int getWidth() {
		return bgSelected.getWidth(null);
	}
	
	@Override
	public int getHeight() {
		return bgUnselected.getHeight(null);
	}
}
