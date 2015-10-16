package fr.cop.game.core.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	public boolean[] clicks = new boolean[2];
	public int[][] positions = new int[2][2];
	@SuppressWarnings("unused")
	private boolean outside; // Essais pour empecher la souris de sortir de l'écran du jeu.
	public int[] coord = new int[2];

	public void update() {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicks[e.getButton()] = true;
		positions[e.getButton()][0] = e.getX();
		positions[e.getButton()][1] = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicks[e.getButton()] = false;
		positions[e.getButton()][0] = 0;
		positions[e.getButton()][1] = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
