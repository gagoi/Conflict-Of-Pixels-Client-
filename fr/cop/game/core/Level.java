package fr.cop.game.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.cop.game.graphics.Sprite;
import fr.cop.game.graphics.Sprites;

public class Level {

	private final String PATH; // Chemin d'accès au fichier de la map (déclaré final car ne sera pas changé).
	private final int SIZE; // Taille de la map (déclaré final car ne sera pas changé).
	private String map; // Contenu de la map (suite de char).

	public Level(String path, int size) { // Objet Level ===> map
		SIZE = size; // On instancie la taille.
		PATH = path; // On instancie le chemin d'accès au fichier.
		try { // On essaie de ...
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/fr/cop/resources/maps/" + PATH + ".txt"))); // ... lire le fichier.
			String line = " "; // Notre ligne actuelle vaut " ".
			while ((line = br.readLine()) != null) { // Ligne = ligne pas encore lue. Si cette ligne n'est pas vide :
				if (map == null) map = line; // // Si c'est la première ligne, on instancie notre String map ---> Evite les NPEs.
				else map += line; // Sinon on ajoute la ligne à notre String map. 
			}
			map.trim();
		} catch (IOException e) { // Si une erreur est levée ...
			e.printStackTrace(); // ... on écrit le rapport d'erreur dans 
		}
	}

	public Sprite getSpriteAt(int x, int y, int time, boolean isAnimated) { // Méthode pour savoir Sprite se trouve aux coordonnees (x;y)
		int xMap = x / 16; // On récupère sur quel tile on se trouve en x (Une troncature est faite implicitement).
		int yMap = y / 16; // On récupère sur quel tile on se trouve en y (Une troncature est faite implicitement).
		if (isAnimated) return Sprites.getAnimatedSprite(map.charAt(xMap + yMap * SIZE), time); // Si le jeu doit afficher les animations, on récupère le bon AnimatedSprite. Puis on renvoie le sprite d'id time.
		else return Sprites.getSprite(map.charAt(xMap + yMap * SIZE)); // Sinon on renvoie le bon sprite.
	}

	public int getSize() { // Méthode pour récupérer la taille de la map. ==> Utilisée essentiellement pour le rendu.
		return SIZE; // Renvoie la taille de la map.
	}
}