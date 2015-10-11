package fr.cop.game.core.helpful.logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.cop.game.core.Conflict_Of_Pixels_Client;

@SuppressWarnings("serial")
public class SimpleDebugWindow extends JFrame {

	private JLabel fpsLabel = new JLabel("FPS : ..fps."), upsLabel = new JLabel("UPS : ..ups.");
	private JLabel directionsKeyStateLabel = new JLabel("Up : ... | Down : ... | Left : ... | Right ...");
	private JLabel itemsKeysStateLabel1 = new JLabel("Items : 1 : ... | 3 : ... | 5 : ...");
	private JLabel itemsKeysStateLabel2 = new JLabel("                2 : ... | 4 : ... | 6 : ...");
	private JLabel spellsKeysStateLabel = new JLabel("Spells : A : ... | Z : ... | E : ... | R ...");

	private JButton debugModeButton = new JButton("Turn on debug mod.");
	private JButton stopButton = new JButton("Exit game.");
	private JButton changeFpsLimitationButton = new JButton("Change FPS limitation.");

	private JTextField fpsLimitationField = new JTextField();

	public SimpleDebugWindow() {
		setTitle("Cop Debug");
		setResizable(false);
		setLayout(null);
		setSize(500, 300);

		fpsLabel.setBounds(10, 45, 300, 30);
		upsLabel.setBounds(10, 65, 300, 30);

		directionsKeyStateLabel.setBounds(10, 100, 500, 30);
		itemsKeysStateLabel1.setBounds(10, 140, 500, 30);
		itemsKeysStateLabel2.setBounds(10, 160, 500, 30);
		spellsKeysStateLabel.setBounds(10, 200, 500, 30);

		fpsLimitationField.setBounds(220, 5, 100, 35);
		fpsLimitationField.setText("60");
		fpsLimitationField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				changeFpsLimitationButton.doClick();				
			}
		});

		debugModeButton.setBounds(100, 50, 200, 40);
		debugModeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Conflict_Of_Pixels_Client.GAME.setDebugState(!Conflict_Of_Pixels_Client.GAME.getDebugState());
				if (Conflict_Of_Pixels_Client.GAME.getDebugState()) debugModeButton.setText("Turn off debug mod.");
				else debugModeButton.setText("Turn on debug mod.");
			}
		});
		stopButton.setBounds(310, 50, 150, 40);
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Conflict_Of_Pixels_Client.GAME.stop();
			}
		});
		changeFpsLimitationButton.setBounds(10, 5, 200, 35);
		changeFpsLimitationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fpsString = fpsLimitationField.getText().trim();
				boolean isValid = true;
				if (fpsString != null && fpsString.charAt(0) == '-' || Character.isDigit(fpsString.charAt(0))) {
					for (int i = 1; i < fpsString.length(); i++) {
						if (!Character.isDigit(fpsString.charAt(i))) {
							isValid = false;
							fpsLimitationField.setText("");
							JOptionPane.showMessageDialog(Conflict_Of_Pixels_Client.GAME.debugWindow, "Seul un nombre peut être écrit ici.");
							break;
						}
					}
				} else {
					isValid = false;
					fpsLimitationField.setText("");
					JOptionPane.showMessageDialog(Conflict_Of_Pixels_Client.GAME.debugWindow, "Seul un nombre peut être écrit ici.");
				}

				if (isValid) {
					Conflict_Of_Pixels_Client.GAME.setFpsLimitatiob(Integer.parseInt(fpsString));
				}
			}
		});

		add(fpsLabel);
		add(upsLabel);

		add(directionsKeyStateLabel);
		add(itemsKeysStateLabel1);
		add(itemsKeysStateLabel2);
		add(spellsKeysStateLabel);
		add(fpsLabel);

		add(debugModeButton);
		add(stopButton);
		add(changeFpsLimitationButton);

		add(fpsLimitationField);

		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
	}
	public void setFPS(int fps) {
		fpsLabel.setText("<html>- FPS : " + fps + "fps.<html>");
	}

	public void setUPS(int ups) {
		upsLabel.setText("- UPS : " + ups + "ups.");
	}

	public void setDirectionKeysState(boolean[] directionsKeysState) {
		directionsKeyStateLabel.setText("<html>- Direction : Up : " + adaptValuesToColoredString(directionsKeysState[0]) + " | Down : " + adaptValuesToColoredString(directionsKeysState[1]) + " | Left : " + adaptValuesToColoredString(directionsKeysState[2]) + " | Right : " + adaptValuesToColoredString(directionsKeysState[3]) + ".</html>");
	}
	public void setItemsKeysState(boolean[] itemsKeysState) {
		itemsKeysStateLabel1.setText("<html>- Items : 1 : " + adaptValuesToColoredString(itemsKeysState[0]) + " | 3 : " + adaptValuesToColoredString(itemsKeysState[2]) + " | 5 : " + adaptValuesToColoredString(itemsKeysState[4]) + "</html>");
		itemsKeysStateLabel2.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 2 : " + adaptValuesToColoredString(itemsKeysState[1]) + " | 4 : " + adaptValuesToColoredString(itemsKeysState[3]) + " | 6 : " + adaptValuesToColoredString(itemsKeysState[5]) + "</html>");
	}
	public void setSpellsKeysState(boolean[] spellsKeysState) {
		spellsKeysStateLabel.setText("<html>- Spells : A : " + adaptValuesToColoredString(spellsKeysState[0]) + " | Z : " + adaptValuesToColoredString(spellsKeysState[1]) + " | E : " + adaptValuesToColoredString(spellsKeysState[2]) + " | R : " + adaptValuesToColoredString(spellsKeysState[3]) + ".</html>");
	}

	public String adaptValuesToColoredString(boolean value) {
		if (value) return "<span style=\"color:#009C15\">" + value + "</>";
		else return "<span style=\"color:#FF0000\">" + value + "</>";
	}
}
