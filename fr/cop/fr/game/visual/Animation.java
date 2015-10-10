package fr.game.visual;

import javax.swing.ImageIcon;

import fr.cop.game.core.Conflict_Of_Pixels_Client;

public class Animation {
	
	private int speed, actualX, actualY, maxX;
	private ImageIcon ico;
	
	@SuppressWarnings("static-access")
	public Animation(int x, int y, int x2, int y2, int speed, String path){
		this.speed = speed/Conflict_Of_Pixels_Client.GAME.nbUps;
		ico = new ImageIcon(getClass().getResource(path));
		actualX = 0;
		actualY = 0;
		maxX = x2;
	}

	public ImageIcon getIco() {
		return ico;
	}

	public int getActualX() {
		return actualX;
	}

	public void setActualX(int actualX) {
		this.actualX = actualX;
	}

	public int getActualY() {
		return actualY;
	}

	public void setActualY(int actualY) {
		this.actualY = actualY;
	}

	public int getSpeed() {
		return speed;
	}

	public void move() {
		setActualX(actualX+speed);
		setActualY(actualY+speed);
		if(getActualX() == getMaxX())
			destroy();
	}
	
	private int getMaxX() {
		return maxX;
	}

	public void destroy(){
//		Conflict_Of_Pixel_Client.GAME.animations.remove(this);
	}
	
}
