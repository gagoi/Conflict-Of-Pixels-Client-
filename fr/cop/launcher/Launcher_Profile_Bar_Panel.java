package fr.cop.launcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Launcher_Profile_Bar_Panel extends JPanel{
	ImageIcon profilIco, backgroundIco; // Images de profil et d'arriere plan.
	Font f; // La police utilis�e.
	Color[] rankColor = {new Color(255, 0, 0)}; // Couleur de mon rank, (niveau) sera chang�.
	private int xp = 58, xpMax = 1000; // Valeur par d�faut permettant de test� la barre d'xp.

	public Launcher_Profile_Bar_Panel() { // Objet panel, personalis�
		setLayout(null); // On enleve le layoutManager
		profilIco = new ImageIcon(getClass().getResource("/fr/cop/resources/icons/icon.png")); //On instancie notre icone de profil.
		backgroundIco = new ImageIcon(getClass().getResource("/fr/cop/resources/menus/profile_bar_background.png")); // On instancie l'arriere plan
		try { // On essaie de ...
			InputStream is = getClass().getResourceAsStream("/fr/cop/resources/menus/fonts/zekton_rg.ttf"); //...r�cuperer notre fichier de police
			Font font = Font.createFont(Font.TRUETYPE_FONT, is); // Pour pouvoir la convertir en police.
			f = font.deriveFont(50f); // convertion de notre police precedente en police definitive de taille 50.
		} catch (Exception e) {// Si on y arrive pas.
			e.printStackTrace(); // On �crit dans la console le probl�me. (ne devrait jamais se produire sauf en cas de fichier manquant).
		}
	}

	
	@Override
	public void paint(Graphics g) { // On r��crit l'arriere plan de notre panel.
		g.drawImage(backgroundIco.getImage(), 0, 0,null); // On y met notre image d'arriere plan.
		g.drawImage(profilIco.getImage(), 5, 5, 95, 95,null); // On dessine l'icone de profil du joueur.
		g.setColor(rankColor[0]); // On met la couleur en fonction de la couleur du rank du joueur.
		for (int i = 0; i < 4; i++) { // Pour i < epaisseur du bandeau.
			g.drawLine(0, i+135, 450, i+135); // On dessine notre bandeau ligne par ligne.
			g.drawLine(450, i+135, 462, 147+i);
			g.drawLine(462, i+147, 568, i+147);
			g.drawLine(567, i+147, 671+i, 0);
		}
		
		g.setColor(new Color(150, 0, 0)); // On met la couleur d'arriere plan de la barre d'xp.
		g.fillRect(10, 108, 400, 15); // On dessine cette barre.
		
		g.setColor(new Color(255, 0, 0)); // On met la couleur de la barre d'xp "rempli"
		g.fillRect(10, 108, 400*xp/xpMax, 15); // Et on dessine cette barre "rempli" en faisant un pourcentage sur l'axe des x.
		
		g.setColor(Color.BLACK); // On met en noir la couleur.
		g.drawRect(10, 108, 400, 15); // On dessine la bordure de la barre d'xp.
		g.drawRect(9, 107, 401, 16); // De 2 pixels d'epaisseur.
		
		g.setFont(f.deriveFont(15f)); // On change la police de notre panel, pour y mettre la notre avec une taille de 15px.
		g.drawString(xp + " / " + xpMax + " XP", 160, 123); // On �crit dans la barre son etat de remplissage.
		
		g.setFont(f); // On change la police pour mettre celle avec la taille par defaut.
		g.setColor(Color.YELLOW); // On met la couleur en jaune.
		g.drawString("Gagoi", 120, 70); // On �crit le pseudo.
		g.dispose(); // On supprime notre objet graphic pour all�ger la ram.
	}
}
