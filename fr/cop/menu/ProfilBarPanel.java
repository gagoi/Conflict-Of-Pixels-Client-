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
	Color[] rankColor = {new Color(255, 0, 0)};
	private int xp = 750, xpMax = 1000;

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
		g.drawImage(profilIco.getImage(), 5, 5, 95, 95,null);
		g.setColor(rankColor[0]);
		for (int i = 0; i < 4; i++) {
			g.drawLine(0, i+135, 450, i+135);
			g.drawLine(450, i+135, 462, 147+i);
			g.drawLine(462, i+147, 568, i+147);
			g.drawLine(567, i+147, 671+i, 0);
		}
		
		g.setColor(new Color(120, 0, 0));
		g.fillRect(10, 108, 400, 15);
		
		g.setColor(new Color(255, 0, 0));
		g.fillRect(10, 108, 400*xp/xpMax, 15);
		
		g.setColor(Color.BLACK);
		g.drawRect(10, 108, 400, 15);
		g.drawRect(9, 107, 401, 16);
		
		g.setFont(f.deriveFont(15f));
		g.drawString(xp + " / " + xpMax + " XP", 160, 123);
		
		g.setFont(f);
		g.setColor(Color.YELLOW);
		g.drawString("Gagoi", 120, 70);
		g.dispose();
	}
}
