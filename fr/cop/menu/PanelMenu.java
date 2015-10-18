package fr.cop.menu;

import java.awt.Desktop;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.cop.game.core.Conflict_Of_Pixels_Client;

@SuppressWarnings("serial")
public class PanelMenu extends JPanel {

	private JLabel versionLabel;
	private JButton twitterButton;
	public PanelMenu() {
		setSize(1280, 720); // On �tabli la taille de notre paneau.
		int[] x = { 0, 400, 512, 400, 0 }; // On definit un tableau portant des
											// coordonnes de points en x.
		int[][] y = { { 180, 180, 360, 252, 252 }, { 252, 252, 360, 324, 324 }, { 324, 324, 360, 397, 397 },
				{ 397, 397, 360, 469, 469 }, { 469, 469, 360, 541, 541 } };
		// Et la correspondance en y, en fonction du bouton
		setLayout(null); // On supprime le layout manager, afin de plac� nos
							// �l�ments sur le bon pixel.
		
		// On cr�� un bouton play, avec sa position, son diametre et son arriere
		// plan. Puis on d�finis les bords du boutons.
		PlayButton pb = new PlayButton(512, 360, 100,
				new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_round.png")));
		pb.setBounds(0, 0, getWidth(), getHeight());

		// On cr�� un bouton de menu, avec un pilygon comme base, prenant en
		// parametre nos coordonn�es pr�cedentes. On y met un text, et un arrier
		// plan. Puis on d�finit les bords du boutons.
		MenusButton v0 = new MenusButton(new Polygon(x, y[0], x.length), "Profil",
				new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_haut_2.png")), pb);
		v0.setBounds(0, 0, getWidth(), getHeight());
		// On cr�� un bouton de menu, avec un pilygon comme base, prenant en
		// parametre nos coordonn�es pr�cedentes. On y met un text, et un arrier
		// plan. Puis on d�finit les bords du boutons.

		MenusButton v1 = new MenusButton(new Polygon(x, y[1], x.length), "Shop",
				new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_haut_1.png")), pb);
		v1.setBounds(0, 0, getWidth(), getHeight());
		// On cr�� un bouton de menu, avec un pilygon comme base, prenant en
		// parametre nos coordonn�es pr�cedentes. On y met un text, et un arrier
		// plan. Puis on d�finit les bords du boutons.

		MenusButton v2 = new MenusButton(new Polygon(x, y[2], x.length), "News",
				new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_middle.png")), pb);
		v2.setBounds(0, 0, getWidth(), getHeight());
		// On cr�� un bouton de menu, avec un pilygon comme base, prenant en
		// parametre nos coordonn�es pr�cedentes. On y met un text, et un arrier
		// plan. Puis on d�finit les bords du boutons.

		MenusButton v3 = new MenusButton(new Polygon(x, y[3], x.length), "Friends",
				new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_bas_1.png")), pb);
		v3.setBounds(0, 0, getWidth(), getHeight());
		// On cr�� un bouton de menu, avec un pilygon comme base, prenant en
		// parametre nos coordonn�es pr�cedentes. On y met un text, et un arrier
		// plan. Puis on d�finit les bords du boutons.

		MenusButton v4 = new MenusButton(new Polygon(x, y[4], x.length), "Options",
				new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_bas_2.png")), pb);
		v4.setBounds(0, 0, getWidth(), getHeight());

		// On ajoute une action � notre bouton play.
		pb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// On ferme le launcher, et on lance le jeu.
				Conflict_Of_Pixels_Client.menuFrame.dispose();
				Conflict_Of_Pixels_Client.GAME = new Conflict_Of_Pixels_Client();

				Conflict_Of_Pixels_Client.GAME.f.setTitle("Conflict Of Pixels"); // Titre
																					// de
																					// la
																					// fenêtre.
				Conflict_Of_Pixels_Client.GAME.f.setResizable(false); // Empeche
																		// de
																		// redimensionner.
				Conflict_Of_Pixels_Client.GAME.f.setLayout(null); // On supprime
																	// les
																	// layouts.
				Conflict_Of_Pixels_Client.GAME.f.setSize(Conflict_Of_Pixels_Client.size); // On
																							// choisis
																							// la
																							// taille
				Conflict_Of_Pixels_Client.GAME.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Eteint
																									// le
																									// programme
																									// lorsque
																									// qu'on
																									// ferme
																									// la
																									// fenêtre.
				Conflict_Of_Pixels_Client.GAME.f.setLocationRelativeTo(null);
				Conflict_Of_Pixels_Client.GAME.f.add(Conflict_Of_Pixels_Client.GAME); // On
																						// ajoute
																						// notre
																						// jeu
																						// à
																						// la
																						// fenêtre.
				Conflict_Of_Pixels_Client.GAME.f.setIconImage(new ImageIcon(
						Conflict_Of_Pixels_Client.GAME.getClass().getResource("/fr/cop/resources/images/gameLogo.jpg"))
								.getImage());

				Conflict_Of_Pixels_Client.GAME.f.setVisible(true); // On affiche
																	// la
																	// fenêtre.

				Conflict_Of_Pixels_Client.GAME.start(); // On lance le jeu.

			}
		});

		// On cr�� un paneau pour nos details de menus (ex : news). Puis on lui
		// mets des bords.
		DetailsPanel detailPanel = new DetailsPanel();
		detailPanel.setBounds(50, 108, getWidth(), getHeight() - 200);

		// On cr�� un paneau pour notre barre de profil. Puis on lui
		// mets des bords.
		ProfilBarPanel profilBar = new ProfilBarPanel();
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

		// On ajoute tout � notre paneau de menu.
		add(v0);
		add(v1);
		add(v2);
		add(v3);
		add(v4);
		add(pb);
		add(profilBar);
		add(detailPanel);
		add(versionLabel);
		add(twitterButton);
	}
}
