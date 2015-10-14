package fr.cop.game.core;

import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelTest extends JPanel {

	public PanelTest() {
		setSize(1280, 720);
		int[] x = {0, 400, 512, 400, 0};
		int[][] y = {{180, 180, 360, 252, 252}, {252, 252, 360, 324, 324}, {324, 324, 360, 397, 397}, {397, 397, 360, 469, 469}, {469, 469, 360, 541, 541}};
		setLayout(null);
		
		PlayButton pb = new PlayButton(512, 360, 100);
		pb.setBounds(0, 0, getWidth(), getHeight());

		
		MenusButton v0 = new MenusButton(new Polygon(x, y[0], x.length), "Profil", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_haut_2.png")), pb);
		v0.setBounds(0, 0, getWidth(), getHeight());
		

		MenusButton v1 = new MenusButton(new Polygon(x, y[1], x.length), "Shop", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_haut_1.png")), pb);
		v1.setBounds(0, 0, getWidth(), getHeight());

		MenusButton v2 = new MenusButton(new Polygon(x, y[2], x.length), "News", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_middle.png")), pb);
		v2.setBounds(0, 0, getWidth(), getHeight());

		MenusButton v3 = new MenusButton(new Polygon(x, y[3], x.length), "Friends", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_bas_1.png")), pb);
		v3.setBounds(0, 0, getWidth(), getHeight());

		MenusButton v4 = new MenusButton(new Polygon(x, y[4], x.length), "Options", new ImageIcon(getClass().getResource("/fr/cop/resources/menus/menu_bas_2.png")), pb);
		v4.setBounds(0, 0, getWidth(), getHeight());

		v4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Conflict_Of_Pixels_Client.menuFrame.dispose();
				Conflict_Of_Pixels_Client.GAME = new Conflict_Of_Pixels_Client();

				Conflict_Of_Pixels_Client.GAME.f.setTitle("Conflict Of Pixels"); // Titre de la fenêtre.
				Conflict_Of_Pixels_Client.GAME.f.setResizable(false); // Empeche de redimensionner.
				Conflict_Of_Pixels_Client.GAME.f.setLayout(null); // On supprime les layouts.
				Conflict_Of_Pixels_Client.GAME.f.setSize(Conflict_Of_Pixels_Client.size); // On choisis la taille
				Conflict_Of_Pixels_Client.GAME.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Eteint le programme lorsque qu'on ferme la fenêtre.
				Conflict_Of_Pixels_Client.GAME.f.setLocationRelativeTo(null);
				Conflict_Of_Pixels_Client.GAME.f.add(Conflict_Of_Pixels_Client.GAME); // On ajoute notre jeu à la fenêtre.
				Conflict_Of_Pixels_Client.GAME.f.setIconImage(new ImageIcon(Conflict_Of_Pixels_Client.GAME.getClass().getResource("/fr/cop/resources/images/gameLogo.jpg")).getImage());

				Conflict_Of_Pixels_Client.GAME.f.setVisible(true); // On affiche la fenêtre.

				Conflict_Of_Pixels_Client.GAME.start(); // On lance le jeu.

			}
		});

		
		JPanel p = new JPanel();
		p.setBounds(0, 0, getWidth(), getHeight());
		p.setLayout(null);
		
		JPanel p2 = new JPanel();
		p2.setBounds(0, 0, getWidth(), getHeight());
		p2.setLayout(null);
		
		p.add(v0);
		p.add(v1);
		p.add(v2);
		p.add(v3);
		p.add(v4);
		p2.add(pb);
		add(p);
		add(p2);
		
		setComponentZOrder(pb, 0);
	}
}
