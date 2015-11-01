package fr.cop.game.graphics.inGameOptions;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tab extends OptionElement{

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
	public OptionTab getOptionTab(){
		return optionTab;
	}
}
