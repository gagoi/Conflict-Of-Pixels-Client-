package fr.cop.game.graphics.inGameOptions;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

import fr.cop.game.graphics.inGameOptions.Elements.OptionElementList;
import fr.cop.game.graphics.inGameOptions.Elements.OptionTab;

public class OptionTabHUD extends OptionTab{

	
	ArrayList<String> testArrayList = new ArrayList<>();
	OptionElementList listTest = new OptionElementList("Test", 300, 50, testArrayList);
	
	
	public OptionTabHUD() {
		super("Interface", 200, 0);
		testArrayList.add("Truc");
		testArrayList.add("Test");
		testArrayList.add("Rein");
		testArrayList.add("ien");
		testArrayList.add("Rien");
		testArrayList.add("Merde");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		listTest.paint(g);
	}
	
	@Override
	public void addListeners(Canvas c) {
		c.addMouseListener(listTest);
	}

}
