package fr.cop.game.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[KeyEvent.KEY_LAST]; // Tableau permettant de savoir quelles touches sont appuyées.
	public boolean[] spells = new boolean[4];
	public boolean[] directions = new boolean[4];
	public boolean[] items = new boolean[6];

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
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.println("Pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
