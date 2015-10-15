package fr.cop.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ProfilBarPanel extends JPanel{
	ImageIcon profilIco, backgroundIco;
	Font f;

	public ProfilBarPanel() {
		setLayout(null);
		profilIco = new ImageIcon(getClass().getResource("/fr/cop/resources/icons/icon.png"));
		backgroundIco = new ImageIcon(getClass().getResource("/fr/cop/resources/menus/profile_bar_background.png"));
		try {
			InputStream is = getClass().getResourceAsStream("/fr/cop/resources/menus/fonts/zekton_rg.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			f = font.deriveFont(50f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void paint(Graphics g) {
		g.drawImage(backgroundIco.getImage(), 0, 0,null);

		g.drawImage(profilIco.getImage(), 5, 5, 80, 80,null);
		g.setFont(f);
		g.setColor(Color.YELLOW);
		g.drawString("Gagoi", 100, 60);
		g.dispose();
	}
}
