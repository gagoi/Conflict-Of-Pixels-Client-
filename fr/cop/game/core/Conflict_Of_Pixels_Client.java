package fr.cop.game.core;

import java.util.ArrayList;
import java.util.Iterator;

import fr.cop.game.core.characters.CharacterList;
import fr.cop.game.core.characters.TestCharacter;
import fr.cop.game.visual.Animation;
import fr.cop.game.visual.GameWindow;

public class Conflict_Of_Pixels_Client implements Runnable {
	
	private GameWindow window;
	public static int nbUps = 1000/60;
	private static int nbFps = 1000/60;
	
	private TestCharacter champTest;
	private Thread t;
	public static Conflict_Of_Pixels_Client GAME;
	public static CharacterList CHARACTER_LIST;
	public static ArrayList<Animation> animations = new ArrayList<Animation>();
	
	public Conflict_Of_Pixels_Client(){ // Objet etant notre jeu. 
		t = new Thread(this);// On creer un Thread permettant d'optimiser notre programme pour le multi-core.
		t.start();// On demarre notre Thread.
	}

	public static void main(String[] args) { // Methode de démarrage d'un programme en Java.
		GAME = new Conflict_Of_Pixels_Client();
	}

	@Override
	public void run() {  
		CHARACTER_LIST = new CharacterList();
		setChampTest(CharacterList.test);
		window = new GameWindow(); // On ouvre la fenetre du jeu.
		
		long startTimeUPS = System.currentTimeMillis(); //Temps de depart du programme, utilise pour les UPS.
		long startTimeFPS = System.currentTimeMillis(); //Temps de depart du programme, utilise pour les FPS.
		while (true) { // Boucle principale de notre programme.
			long currentTime = System.currentTimeMillis(); //Temps en millisecondes.
			if(currentTime >= startTimeUPS+nbUps) { // Si le temps du processeur est supérieur au temps de depart + nbUps...
				System.out.println("T_UPS : " + (currentTime-startTimeUPS));
				startTimeUPS = System.currentTimeMillis(); // ... on remet le temps de depart a 0 ...
				gameUpdate(); //... puis on fait une update.
				System.err.println("TEST");
			}
			if(currentTime >= startTimeFPS+nbFps) { // Si le temps du processeur est supérieur au temps de depart + nbFps...
				System.out.println("T_FPS : " + (currentTime-startTimeFPS));
				startTimeFPS = System.currentTimeMillis(); // ... on remet le temps de depart a 0 ...
				visualUpdate(); //... puis on fait une update.
			}
		}
		
	}
	
	public synchronized void gameUpdate(){ // Methode visant l'actualisation du jeu. Le jeu sera limite a 60ups (update per second). Pour que tout le monde joue a la meme vitesse.
		
		/* TO-DO :
		 * - Utilisation des touches + souris.
		 * - Envoie des packets au server.
		 * - Lecture des packets du server.
		 * */
		
		//Temporaire avant serveur
		for (Iterator iterator = animations.iterator(); iterator.hasNext();) {
			Animation anim = (Animation) iterator.next();
			anim.move();
		}
	}
	
	public synchronized void visualUpdate(){ // Methode actualisant l'ecran. Pas le jeu. Peut etre different entre chaque joueur.
		/* TO-DO :
		 * - Actualisation du jeu dans la frame.S
		 * 
		 */
//		GAME.getWindow().getGamePanel().repaint();
		
		GAME.getWindow().getUiPanel().repaint();
		
		GAME.getWindow().getGamePanel().revalidate();
	}

	public GameWindow getWindow() {
		return window;
	}

	public TestCharacter getChampTest() {
		return champTest;
	}

	public void setChampTest(TestCharacter champTest) {
		this.champTest = champTest;
	}
}
