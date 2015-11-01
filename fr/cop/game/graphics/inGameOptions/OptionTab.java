package fr.cop.game.graphics.inGameOptions;

import java.awt.Canvas;
import java.awt.Graphics;


public class OptionTab extends OptionElement{
	
	private OptionElementBoolean test, test2;

	public OptionTab(String name, int posX, int posY) {
		super(name, posX, posY);
		test = new OptionElementBoolean("Test", 10, 110, true);
		test2 = new OptionElementBoolean("Test", 10, 150, false);
	}
	
	@Override
	public void paint(Graphics g) {
		g.fillRect(getPosX(), getPosY(), 400, 100);
		test.paint(g);
		test2.paint(g);
	}
	
	public void addListeners(Canvas c){
		c.addMouseListener(test);
		c.addMouseListener(test2);
	}

}
