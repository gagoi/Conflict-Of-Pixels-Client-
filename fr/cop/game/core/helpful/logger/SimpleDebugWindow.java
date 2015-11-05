package fr.cop.game.core.helpful.logger;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.core.Game_Frame;

@SuppressWarnings("serial")
public class SimpleDebugWindow extends JFrame {

	private JLabel fpsLabel = new JLabel("- FPS : ..fps.");
	private JLabel upsLabel = new JLabel("- UPS : ..ups.");
	private JLabel directionsKeyStateLabel = new JLabel("- Up : ... | Down : ... | Left : ... | Right ...");
	private JLabel itemsKeysStateLabel1 = new JLabel("- Items : 1 : ... | 3 : ... | 5 : ...");
	private JLabel itemsKeysStateLabel2 = new JLabel("                  2 : ... | 4 : ... | 6 : ...");
	private JLabel spellsKeysStateLabel = new JLabel("- Spells : A : ... | Z : ... | E : ... | R ...");
	private JLabel isGamePausedLabel = new JLabel("- Pause : ...");

	private JButton debugModeButton = new JButton("Turn on debug mod.");
	private JButton stopButton = new JButton("Exit game.");
	private JButton changeFpsLimitationButton = new JButton("Change FPS limitation.");
	private JButton toggleFullScreenButton = new JButton("Toggle FullScreen.");
	private JButton toggleAnimationsButton = new JButton("Toggle Animations");

	private JTextField fpsLimitationField = new JTextField();

	private ImageIcon icon;

	public SimpleDebugWindow() {
		setTitle("Cop Debug");
		setResizable(false);
		setLayout(null);
		setSize(700, 300);
		icon = new ImageIcon(getClass().getResource("/fr/cop/resources/images/gameLogo.jpg"));

		fpsLabel.setBounds(10, 45, 300, 30);
		upsLabel.setBounds(10, 65, 300, 30);

		directionsKeyStateLabel.setBounds(10, 100, 500, 30);
		itemsKeysStateLabel1.setBounds(10, 140, 500, 30);
		itemsKeysStateLabel2.setBounds(10, 160, 500, 30);
		spellsKeysStateLabel.setBounds(10, 200, 500, 30);
		isGamePausedLabel.setBounds(10, 240, 500, 30);

		fpsLimitationField.setBounds(220, 5, 100, 35);
		fpsLimitationField.setText("60");
		fpsLimitationField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) changeFpsLimitationButton.doClick();
			}
		});

		debugModeButton.setBounds(100, 50, 200, 40);
		debugModeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game_Frame.GAME.setDebugState(!Game_Frame.GAME.getDebugState());
				if (Game_Frame.GAME.getDebugState()) debugModeButton.setText("Turn off debug mod.");
				else debugModeButton.setText("Turn on debug mod.");
			}
		});
		stopButton.setBounds(490, 5, 200, 35);
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game_Frame.GAME.stop();
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
							JOptionPane.showMessageDialog(Conflict_Of_Pixels_Client.debugWindow, "Seul un nombre peut être écrit ici.");
							break;
						}
					}
				} else {
					isValid = false;
					fpsLimitationField.setText("");
					JOptionPane.showMessageDialog(Conflict_Of_Pixels_Client.debugWindow, "Seul un nombre peut être écrit ici.");
				}

				if (isValid) {
					Game_Frame.GAME.setFpsLimitatiob(Integer.parseInt(fpsString));
				}
			}
		});

		toggleFullScreenButton.setBounds(330, 5, 150, 35);
		toggleFullScreenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game_Frame.instance.toggleFullScreen();

			}
		});

		toggleAnimationsButton.setBounds(330, 50, 150, 40);
		toggleAnimationsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game_Frame.GAME.isGameAnimated = !Game_Frame.GAME.isGameAnimated;

			}
		});

		add(fpsLabel);
		add(upsLabel);

		add(directionsKeyStateLabel);
		add(itemsKeysStateLabel1);
		add(itemsKeysStateLabel2);
		add(spellsKeysStateLabel);
		add(isGamePausedLabel);

		add(debugModeButton);
		add(stopButton);
		add(changeFpsLimitationButton);
		add(toggleFullScreenButton);
		add(toggleAnimationsButton);

		add(fpsLimitationField);

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
		directionsKeyStateLabel.setText("<html>- Direction : Up : " + adaptValuesToColoredString(directionsKeysState[0]) + " | Down : " + adaptValuesToColoredString(directionsKeysState[1]) + " | Left : "
				+ adaptValuesToColoredString(directionsKeysState[2]) + " | Right : " + adaptValuesToColoredString(directionsKeysState[3]) + ".</html>");
	}

	public void setItemsKeysState(boolean[] itemsKeysState) {
		itemsKeysStateLabel1.setText("<html>- Items : 1 : " + adaptValuesToColoredString(itemsKeysState[0]) + " | 3 : " + adaptValuesToColoredString(itemsKeysState[2]) + " | 5 : " + adaptValuesToColoredString(itemsKeysState[4]) + "</html>");
		itemsKeysStateLabel2.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 2 : " + adaptValuesToColoredString(itemsKeysState[1]) + " | 4 : " + adaptValuesToColoredString(itemsKeysState[3]) + " | 6 : "
				+ adaptValuesToColoredString(itemsKeysState[5]) + "</html>");
	}

	public void setSpellsKeysState(boolean[] spellsKeysState) {
		spellsKeysStateLabel.setText("<html>- Spells : A : " + adaptValuesToColoredString(spellsKeysState[0]) + " | Z : " + adaptValuesToColoredString(spellsKeysState[1]) + " | E : " + adaptValuesToColoredString(spellsKeysState[2]) + " | R : "
				+ adaptValuesToColoredString(spellsKeysState[3]) + ".</html>");
	}

	public void changePauseStatue() {
		isGamePausedLabel.setText("<html>- Pause : " + adaptValuesToColoredString(Game_Frame.GAME.isGamePaused) + "</html>");
	}

	public String adaptValuesToColoredString(boolean value) {
		if (value) return "<span style=\"color:#009C15\">" + value + "</>";
		else return "<span style=\"color:#FF0000\">" + value + "</>";
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (Conflict_Of_Pixels_Client.scorePWAL1) g.drawImage(icon.getImage(), 450, 30, 40, 40, null);
	}
}
