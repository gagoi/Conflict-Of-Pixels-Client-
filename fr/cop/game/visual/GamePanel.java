package fr.cop.game.visual;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.cop.game.core.Conflict_Of_Pixels_Client;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	Graphics g;
	
	public GamePanel(){
		setBackground(Color.BLACK);
		setLayout(null);
		g = super.getGraphics();
		System.out.println(g);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Iterator iterator = Conflict_Of_Pixels_Client.animations.iterator(); iterator.hasNext();) {
			Animation anim = (Animation) iterator.next();
			g.drawImage(anim.getIco().getImage(), anim.getActualX(), anim.getActualY(), null);
		}
		
	}
	
	public Graphics getGraphics(){
		return g;
	}
}
