package fr.cop.game.core.inputs;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.cop.game.core.Conflict_Of_Pixels_Client;

public class Keyboard extends KeyAdapter {

	public boolean[] keys = new boolean[KeyEvent.KEY_LAST]; // Tableau permettant de savoir quelles touches sont appuyées.
	public boolean[] spells = new boolean[4];
	public boolean[] directions = new boolean[4];
	public boolean[] items = new boolean[6];
	
	public boolean pwal;
	private JFrame pwalFrame;
	public boolean canPressP;

	public void update() {
		spells[0] = keys[KeyEvent.VK_A];
		spells[1] = keys[KeyEvent.VK_Z];
		spells[2] = keys[KeyEvent.VK_E];
		spells[3] = keys[KeyEvent.VK_R];

		directions[0] = keys[KeyEvent.VK_UP];
		directions[1] = keys[KeyEvent.VK_DOWN];
		directions[2] = keys[KeyEvent.VK_LEFT];
		directions[3] = keys[KeyEvent.VK_RIGHT];

		items[0] = keys[KeyEvent.VK_1] || keys[KeyEvent.VK_NUMPAD1];
		items[1] = keys[KeyEvent.VK_2] || keys[KeyEvent.VK_NUMPAD2];
		items[2] = keys[KeyEvent.VK_3] || keys[KeyEvent.VK_NUMPAD3];
		items[3] = keys[KeyEvent.VK_4] || keys[KeyEvent.VK_NUMPAD4];
		items[4] = keys[KeyEvent.VK_5] || keys[KeyEvent.VK_NUMPAD5];
		items[5] = keys[KeyEvent.VK_6] || keys[KeyEvent.VK_NUMPAD6];
		
		pwal = (keys[KeyEvent.VK_P] && keys[KeyEvent.VK_A] && keys[KeyEvent.VK_W] && keys[KeyEvent.VK_L]);
		
		
		if(pwal && !Conflict_Of_Pixels_Client.scorePWAL1){
			Conflict_Of_Pixels_Client.scorePWAL1 = true;
			pwalFrame = new JFrame();
			pwalFrame.setResizable(false);
			pwalFrame.setSize(250, 60);
			pwalFrame.setTitle("~~GG PWAL~~");
			pwalFrame.add(new JLabel("Bravo tu aimes les PWALs !!"), BorderLayout.CENTER);
			pwalFrame.setVisible(true);
			Conflict_Of_Pixels_Client.debugWindow.repaint();
			keys[KeyEvent.VK_A] = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		if(e.getKeyCode() == KeyEvent.VK_P) canPressP = true;
	}
}
