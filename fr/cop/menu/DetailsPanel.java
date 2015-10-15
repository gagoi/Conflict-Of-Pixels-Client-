package fr.cop.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DetailsPanel extends JPanel {

	String file;
	JLabel label;

	public DetailsPanel() {
		label = new JLabel("PWAL");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/gagoi/Conflict-Of-Pixels-Client-/master/fr/cop/resources/changelog.txt").openConnection().getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				if (file == null) file = line;
				else file += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setText(file);
		label.setBackground(Color.BLUE);
		label.setBounds(0, 50, 250, 250);
		add(label);
		setOpaque(true);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}
