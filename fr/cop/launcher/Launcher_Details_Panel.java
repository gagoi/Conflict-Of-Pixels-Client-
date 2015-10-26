package fr.cop.launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Launcher_Details_Panel extends JPanel {

	String file;
	JLabel label;
	ImageIcon background;

	public Launcher_Details_Panel() {
		super();
		label = new JLabel("PWAL");
		background = new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_detailed_panel_background.png"));
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/gagoi/Conflict-Of-Pixels-Client-/master/fr/cop/resources/changelog.txt").openConnection().getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (file == null) file = line;
				else file += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setText(file);
		setLayout(null);
		label.setForeground(Color.YELLOW);
		label.setVerticalAlignment(JLabel.TOP);
		label.setBounds(720, 0, 500, 1000);
		add(label);
		setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.getImage(), 530, 0, null);
	}

}