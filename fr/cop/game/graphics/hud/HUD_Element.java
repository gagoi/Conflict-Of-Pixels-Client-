package fr.cop.game.graphics.hud;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.swing.ImageIcon;

import fr.cop.game.core.Game_Frame;

public class HUD_Element extends MouseAdapter {

	protected float scale = 0.1f; // Permet de redimensionner les �l�ments.
	protected Image background; // Image d'arriere plan du composant.
	private int initialCoordX = 50, initialCoordY = 300, clickCoordX = initialCoordX, clickCoordY = initialCoordY; // Coordonn�es par d�fauts.
	protected int tempCoordX = initialCoordX; // Permet de d�placer le composant.
	protected int tempCoordY = initialCoordY; // Permet de d�placer le composant.
	protected int maxX, maxY; // Non utilis� pour le moment.
	protected int width; // Taille de l'�l�ment (non redimensionner).
	protected int height; // Taille de l'�l�ment (non redimensionner).

	private String hudElementName; // Noms de l'�l�ment, utilis� pour sauvgarder le positionnement.<

	public HUD_Element(String imageName, String hudElementName) { // Premier constructeur, prenant en param�tre l'image et le nom.
		background = new ImageIcon(getClass().getResource(imageName + ".png")).getImage(); // On r�cup�re l'image.
		width = background.getWidth(null); // On d�finit la taille en fonction de l'image.
		height = background.getHeight(null); // On d�finit la taille en fonction de l'image.
		this.hudElementName = hudElementName; // On met le nom de l'�l�ment dans la variable d'instance pr�vue.
	}

	public HUD_Element(String imageName, String hudElementName, float scale) { // Second constructeur, identique au premier mais prenant en param�tre le redimensionnement par d�faut.
		background = new ImageIcon(getClass().getResource(imageName + ".png")).getImage(); // On r�cup�re l'image.
		width = background.getWidth(null); // On d�finit la taille en fonction de l'image.
		height = background.getHeight(null); // On d�finit la taille en fonction de l'image.
		setScale(scale); // On d�finit le redimensionnement en fonction du param�tre.
		this.hudElementName = hudElementName; // On met le nom de l'�l�ment dans la variable d'instance pr�vue.
	}

	public HUD_Element(String hudElementName, int width, int height) { // Troisi�me constructeur, prenant en param�tre un nom, et une taille.
		// On met tous les param�tres dans une variable d'insance.
		this.width = width; 
		this.height = height;
		this.hudElementName = hudElementName;
	}

	public void refresh(Graphics g) { // Fonction pour dessiner un arri�re plan.
		if (background != null) g.drawImage(background, getPosX(), getPosY(), getScaledWidth(), getScaledHeight(), null);// Si l'image existe, on la dessine aux coordonn�es de l'�l�ment et de la taille de l'�l�ment redimensionn�.
	}

	@Override
	public void mousePressed(MouseEvent e) { // Activ� lorsque l'on clique.
		clickCoordX = e.getX();  // On r�cup�re les coordonn�es du clic.
		clickCoordY = e.getY(); // On r�cup�re les coordonn�es du clic.
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + getScaledWidth() && clickCoordY > initialCoordY && clickCoordY < initialCoordY + getScaledHeight()) { // Si on clique dans l'espace que prends l'�l�ment...
			Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Press>", "Click at (" + clickCoordX + ";" + clickCoordY + ")."); // On affiche un message de debug.
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) { // Activ� lors d'un cliqu�/gliss� (apr�s le mousePressed(MouseEvent e)).
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + getScaledWidth() && clickCoordY > initialCoordY && clickCoordY < initialCoordY + getScaledHeight()) { // Si la souris est dans l'�l�ment (�vite de prendre d'autres �l�ments) ...
			Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Drag>", "Click at (" + e.getX() + ";" + e.getY() + ")."); // ... on affiche dans la console ...
			tempCoordX = e.getX() - (clickCoordX - initialCoordX); // ... puis on modifie les coordonn�e temporaire de l'�l�ment en x ...
			tempCoordY = e.getY() - (clickCoordY - initialCoordY); // ... et en Y.
		}
	};

	@Override
	public void mouseReleased(MouseEvent e) { // Activ� lorsque le clic est lach�.
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + getScaledWidth() && clickCoordY > initialCoordY && clickCoordY < initialCoordY + getScaledHeight()) { // On v�rifie la psition du clic, pour les m�mes raisons que dans le MouseDrag(MouseEvent e).
			Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Release>", "Unclick at (" + e.getX() + ";" + e.getY() + ")."); // On affiche dans la console.
			if (tempCoordX != initialCoordX || tempCoordY != initialCoordY) { // Si les coordon�es ont chang�es par rapport � celle avant de cliquer...
				initialCoordX = tempCoordX; // On modifie ses valeurs en X,
				initialCoordY = tempCoordY; // puis en Y.
			}
		}
	}

	public void setMaxCoords(int w, int h) { // Permet de d�finir les coordonnes maximum qu'un composant peut atteindre, pas encore utilis�.
		this.maxX = w; // En x.
		this.maxY = h; // En y.
	}

	public void storeProps(Properties prop) { // Permet de stocker les valeurs de positions et de taille des �l�ments, dans un fichier properties.
		Game_Frame.logger.logTxt(hudElementName + " Saving", hudElementName + " Properties.");
		prop.setProperty(hudElementName + "_scale", "" + this.getScale());
		prop.setProperty(hudElementName + "_coord_x", "" + initialCoordX);
		prop.setProperty(hudElementName + "_coord_y", "" + initialCoordY);
	}

	public void readProps(Properties prop) { // Permet de lire les valeurs de positions et de taille des �l�ments, dans un fichier properties.
		Game_Frame.logger.logTxt(hudElementName + " Loading", hudElementName + " Properties.");
		this.setScale(Float.parseFloat(prop.getProperty(hudElementName + "_scale"))); // Mettre le ratio � une valeur convertie en float de l'�l�ment lu.
		this.setCoords(Integer.parseInt(prop.getProperty(hudElementName + "_coord_x")), Integer.parseInt(prop.getProperty(hudElementName + "_coord_y"))); // Mettre les coordonn�es aux valeur convertie en int des �l�ments lu.
	}

	public void setScale(float scale) { // Permet de modifier le scale.
		this.scale = scale;
	}

	public void setCoords(int x, int y) { // Permet de changer les coordonn�es.
		this.initialCoordX = this.tempCoordX = x;
		this.initialCoordY = this.tempCoordY = y;
	}

	public int getPosX() { // Permet de r�cup�rer la position en X.
		return tempCoordX;
	}

	public int getPosY() { // Permet de r�cup�rer la position en Y.
		return tempCoordY;
	}

	public float getScale() { // Permet de r�cup�rer le ratio de redimentionnage.
		return scale;
	}

	public int getScaledWidth() { // Permet de r�cup�rer la taille de l'�l�ment apr�s avoir �t� redimmensionn�.
		return (int) (width * getScale());
	}

	public int getScaledHeight() { // Permet de r�cup�rer la taille de l'�l�ment apr�s avoir �t� redimmensionn�.
		return (int) (height * getScale());
	}
}