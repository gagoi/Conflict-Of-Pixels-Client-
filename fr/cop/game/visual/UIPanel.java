package game.visual;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.visual.customsButtons.SpellButton;

@SuppressWarnings("serial")
public class UIPanel extends JPanel {
	public UIPanel(){
		setOpaque(true);
		setLayout(null);
		add(new SpellButton(Conflict_Of_Pixels_Client.GAME, Conflict_Of_Pixels_Client.GAME.getChampTest(), 0));
		add(new SpellButton(Conflict_Of_Pixels_Client.GAME, Conflict_Of_Pixels_Client.GAME.getChampTest(), 1));
		add(new SpellButton(Conflict_Of_Pixels_Client.GAME, Conflict_Of_Pixels_Client.GAME.getChampTest(), 2));
		add(new SpellButton(Conflict_Of_Pixels_Client.GAME, Conflict_Of_Pixels_Client.GAME.getChampTest(), 3));
	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		g.fillRect(400, 400, 500, 500);
	}

}
