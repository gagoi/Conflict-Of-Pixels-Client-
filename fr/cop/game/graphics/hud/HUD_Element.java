package fr.cop.game.graphics.hud;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.swing.ImageIcon;

import fr.cop.game.core.Game_Frame;

public class HUD_Element extends MouseAdapter {

	protected float scale = 0.1f; // Permet de redimensionner les éléments.
	protected Image background; // Image d'arriere plan du composant.
	private int initialCoordX = 50, initialCoordY = 300, clickCoordX = initialCoordX, clickCoordY = initialCoordY; // Coordonnées par défauts.
	protected int tempCoordX = initialCoordX; // Permet de déplacer le composant.
	protected int tempCoordY = initialCoordY; // Permet de déplacer le composant.
	protected int maxX, maxY; // Non utilisé pour le moment.
	protected int width; // Taille de l'élément (non redimensionner).
	protected int height; // Taille de l'élément (non redimensionner).

	private String hudElementName; // Noms de l'élément, utilisé pour sauvgarder le positionnement.<

	public HUD_Element(String imageName, String hudElementName) { // Premier constructeur, prenant en paramètre l'image et le nom.
		background = new ImageIcon(getClass().getResource(imageName + ".png")).getImage(); // On récupère l'image.
		width = background.getWidth(null); // On définit la taille en fonction de l'image.
		height = background.getHeight(null); // On définit la taille en fonction de l'image.
		this.hudElementName = hudElementName; // On met le nom de l'élément dans la variable d'instance prévue.
	}

	public HUD_Element(String imageName, String hudElementName, float scale) { // Second constructeur, identique au premier mais prenant en paramètre le redimensionnement par défaut.
		background = new ImageIcon(getClass().getResource(imageName + ".png")).getImage(); // On récupère l'image.
		width = background.getWidth(null); // On définit la taille en fonction de l'image.
		height = background.getHeight(null); // On définit la taille en fonction de l'image.
		setScale(scale); // On définit le redimensionnement en fonction du paramêtre.
		this.hudElementName = hudElementName; // On met le nom de l'élément dans la variable d'instance prévue.
	}

	public HUD_Element(String hudElementName, int width, int height) { // Troisième constructeur, prenant en paramètre un nom, et une taille.
		// On met tous les paramètres dans une variable d'insance.
		this.width = width; 
		this.height = height;
		this.hudElementName = hudElementName;
	}

	public void refresh(Graphics g) { // Fonction pour dessiner un arrière plan.
		if (background != null) g.drawImage(background, getPosX(), getPosY(), getScaledWidth(), getScaledHeight(), null);// Si l'image existe, on la dessine aux coordonnées de l'élément et de la taille de l'élément redimensionné.
	}

	@Override
	public void mousePressed(MouseEvent e) { // Activé lorsque l'on clique.
		clickCoordX = e.getX();  // On récupère les coordonnées du clic.
		clickCoordY = e.getY(); // On récupère les coordonnées du clic.
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + getScaledWidth() && clickCoordY > initialCoordY && clickCoordY < initialCoordY + getScaledHeight()) { // Si on clique dans l'espace que prends l'élément...
			Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Press>", "Click at (" + clickCoordX + ";" + clickCoordY + ")."); // On affiche un message de debug.
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) { // Activé lors d'un cliqué/glissé (après le mousePressed(MouseEvent e)).
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + getScaledWidth() && clickCoordY > initialCoordY && clickCoordY < initialCoordY + getScaledHeight()) { // Si la souris est dans l'élément (évite de prendre d'autres éléments) ...
			Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Drag>", "Click at (" + e.getX() + ";" + e.getY() + ")."); // ... on affiche dans la console ...
			tempCoordX = e.getX() - (clickCoordX - initialCoordX); // ... puis on modifie les coordonnée temporaire de l'élément en x ...
			tempCoordY = e.getY() - (clickCoordY - initialCoordY); // ... et en Y.
		}
	};

	@Override
	public void mouseReleased(MouseEvent e) { // Activé lorsque le clic est laché.
		if (clickCoordX > initialCoordX && clickCoordX < initialCoordX + getScaledWidth() && clickCoordY > initialCoordY && clickCoordY < initialCoordY + getScaledHeight()) { // On vérifie la psition du clic, pour les mêmes raisons que dans le MouseDrag(MouseEvent e).
			Game_Frame.logger.logTxt("Mouse Listener <" + hudElementName + ":Release>", "Unclick at (" + e.getX() + ";" + e.getY() + ")."); // On affiche dans la console.
			if (tempCoordX != initialCoordX || tempCoordY != initialCoordY) { // Si les coordonées ont changées par rapport à celle avant de cliquer...
				initialCoordX = tempCoordX; // On modifie ses valeurs en X,
				initialCoordY = tempCoordY; // puis en Y.
			}
		}
	}

	public void setMaxCoords(int w, int h) { // Permet de définir les coordonnes maximum qu'un composant peut atteindre, pas encore utilisé.
		this.maxX = w; // En x.
		this.maxY = h; // En y.
	}

	public void storeProps(Properties prop) { // Permet de stocker les valeurs de positions et de taille des éléments, dans un fichier properties.
		Game_Frame.logger.logTxt(hudElementName + " Saving", hudElementName + " Properties.");
		prop.setProperty(hudElementName + "_scale", "" + this.getScale());
		prop.setProperty(hudElementName + "_coord_x", "" + initialCoordX);
		prop.setProperty(hudElementName + "_coord_y", "" + initialCoordY);
	}

	public void readProps(Properties prop) { // Permet de lire les valeurs de positions et de taille des éléments, dans un fichier properties.
		Game_Frame.logger.logTxt(hudElementName + " Loading", hudElementName + " Properties.");
		this.setScale(Float.parseFloat(prop.getProperty(hudElementName + "_scale"))); // Mettre le ratio à une valeur convertie en float de l'élément lu.
		this.setCoords(Integer.parseInt(prop.getProperty(hudElementName + "_coord_x")), Integer.parseInt(prop.getProperty(hudElementName + "_coord_y"))); // Mettre les coordonnées aux valeur convertie en int des éléments lu.
	}

	public void setScale(float scale) { // Permet de modifier le scale.
		this.scale = scale;
	}

	public void setCoords(int x, int y) { // Permet de changer les coordonnées.
		this.initialCoordX = this.tempCoordX = x;
		this.initialCoordY = this.tempCoordY = y;
	}

	public int getPosX() { // Permet de récupérer la position en X.
		return tempCoordX;
	}

	public int getPosY() { // Permet de récupérer la position en Y.
		return tempCoordY;
	}

	public float getScale() { // Permet de récupérer le ratio de redimentionnage.
		return scale;
	}

	public int getScaledWidth() { // Permet de récupérer la taille de l'élément après avoir été redimmensionné.
		return (int) (width * getScale());
	}

	public int getScaledHeight() { // Permet de récupérer la taille de l'élément après avoir été redimmensionné.
		return (int) (height * getScale());
	}
}