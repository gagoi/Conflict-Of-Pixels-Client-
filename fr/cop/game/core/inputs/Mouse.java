package fr.cop.game.core.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.core.Game_Frame;

public class Mouse implements MouseListener, MouseMotionListener {
	public boolean[] clicks = new boolean[4];
	public int[][] positions = new int[4][2];
	@SuppressWarnings("unused")
	private boolean outside; // Essais pour empecher la souris de sortir de l'�cran du jeu.
	public int[] coord = new int[2];

	public void update() {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicks[e.getButton()] = true;
		positions[e.getButton()][0] = e.getX();
		positions[e.getButton()][1] = e.getY();
//		Game_Frame.GAME.serverGame.requestMove(Game_Frame.GAME.serverGame.getChampion(0), convertToServerPosX(e.getX()), convertToServerPosY(e.getY()));
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
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	public int convertToServerPosX(int renderX) {
		return (((renderX * Conflict_Of_Pixels_Client.width * Conflict_Of_Pixels_Client.scale) / Game_Frame.size.width) + 4 * Game_Frame.GAME.x);
	}

	public int convertToServerPosY(int renderY) {
		return (((renderY * Conflict_Of_Pixels_Client.height * Conflict_Of_Pixels_Client.scale) / Game_Frame.size.height) + 4 * Game_Frame.GAME.y);
	}

}
