package fr.cop.launcher;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.core.Game_Frame;

@SuppressWarnings("serial")
public class Launcher_Panel extends JPanel {

	private JLabel versionLabel;
	private JButton twitterButton, facebookButton;
	private ImageIcon background;

	public Launcher_Panel() {
		super();
		background = new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_background.png"));
		setSize(1280, 720); // On ï¿½tabli la taille de notre paneau.
		int[] x = {0, 400, 512, 400, 0}; // On definit un tableau portant des
											// coordonnes de points en x.
		int[][] y = {{180, 180, 360, 252, 252}, {252, 252, 360, 324, 324}, {324, 324, 360, 397, 397}, {397, 397, 360, 469, 469}, {469, 469, 360, 541, 541}};
		// Et la correspondance en y, en fonction du bouton
		setLayout(null); // On supprime le layout manager, afin de placï¿½ nos
							// ï¿½lï¿½ments sur le bon pixel.

		// On crï¿½ï¿½ un bouton play, avec sa position, son diametre et son arriere
		// plan. Puis on dï¿½finis les bords du boutons.
		Laucher_Play_Button pb = new Laucher_Play_Button(512, 360, 100, new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_round.png")));
		pb.setBounds(0, 0, getWidth(), getHeight());

		// On créée un bouton de menu, avec un polygon comme base, prenant en  paramètre nos coordonnées précedentes. On y met un texte, et un arrière-plan. Puis on définit les bords du boutons.
		Launcher_Parts_Button v0 = new Launcher_Parts_Button(new Polygon(x, y[0], x.length), "Profil", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_haut_2.png")), pb);
		v0.setBounds(0, 0, getWidth(), getHeight());
		
		// On créée un bouton de menu, avec un polygon comme base, prenant en  paramètre nos coordonnées précedentes. On y met un texte, et un arrière-plan. Puis on définit les bords du boutons.
		Launcher_Parts_Button v1 = new Launcher_Parts_Button(new Polygon(x, y[1], x.length), "Shop", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_haut_1.png")), pb);
		v1.setBounds(0, 0, getWidth(), getHeight());
		
		// On créée un bouton de menu, avec un polygon comme base, prenant en  paramètre nos coordonnées précedentes. On y met un texte, et un arrière-plan. Puis on définit les bords du boutons.
		Launcher_Parts_Button v2 = new Launcher_Parts_Button(new Polygon(x, y[2], x.length), "News", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_middle.png")), pb);
		v2.setBounds(0, 0, getWidth(), getHeight());
		
		// On créée un bouton de menu, avec un polygon comme base, prenant en  paramètre nos coordonnées précedentes. On y met un texte, et un arrière-plan. Puis on définit les bords du boutons.
		Launcher_Parts_Button v3 = new Launcher_Parts_Button(new Polygon(x, y[3], x.length), "Friends", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_bas_1.png")), pb);
		v3.setBounds(0, 0, getWidth(), getHeight());
		
		// On créée un bouton de menu, avec un polygon comme base, prenant en  paramètre nos coordonnées précedentes. On y met un texte, et un arrière-plan. Puis on définit les bords du boutons.
		Launcher_Parts_Button v4 = new Launcher_Parts_Button(new Polygon(x, y[4], x.length), "Options", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_bas_2.png")), pb);
		v4.setBounds(0, 0, getWidth(), getHeight());

		// On ajoute une action à notre bouton play.
		pb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// On ferme le launcher, et on lance le jeu.
				Conflict_Of_Pixels_Client.menuFrame.dispose();
				Conflict_Of_Pixels_Client.gameFrame = new Game_Frame(new Conflict_Of_Pixels_Client());
			}
		});

		// On créée un paneau pour nos details de menus (ex : news). Puis on lui mets des bords.
		Launcher_Details_Panel detailPanel = new Launcher_Details_Panel();
		detailPanel.setBounds(50, 108, getWidth(), getHeight() - 200);

		// On créée un paneau pour notre barre de profil. Puis on lui  mets des bords.
		Launcher_Profile_Bar_Panel profilBar = new Launcher_Profile_Bar_Panel();
		profilBar.setBounds(0, 0, getWidth(), getHeight());

		versionLabel = new JLabel("Version : alpha-0.0.0.2");
		versionLabel.setBounds(10, getHeight() - 55, 200, 30);

		twitterButton = new JButton("Twitter");
		twitterButton.setBounds(getWidth() - 100, 10, 80, 30);
		twitterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://twitter.com/TheGagoi"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});

		facebookButton = new JButton("Facebook");
		facebookButton.setBounds(getWidth() - 210, 10, 100, 30);
		facebookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.facebook.com/conflictofpixels?__mref=message_bubble"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});

		// On ajoute tout nos composants à notre paneau de menu.
		add(v0);
		add(v1);
		add(v2);
		add(v3);
		add(v4);
		add(profilBar);
		add(detailPanel);
		add(versionLabel);
		add(twitterButton);
		add(facebookButton);

		add(pb);
		setComponentZOrder(pb, 0); // On met le bouton de jeu au premier plan.
	}

	@Override
	public void paint(Graphics g) { // On redéfinit la fonction paint() du JPanel.
		g.drawImage(background.getImage(), 0, 0, null); // Et on dessine notre arrière plan.
		super.paint(g); // On dessine ensuite tous les composants.
	}
}
