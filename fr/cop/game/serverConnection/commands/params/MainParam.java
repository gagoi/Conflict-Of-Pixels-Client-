package fr.cop.game.serverConnection.commands.params;

import java.util.ArrayList;

public class MainParam {
	
	ArrayList<?> avaibleValues = new ArrayList<>();
	
	
	public MainParam() {
	}
	
	
	public boolean test(Object obj){
		for (int i = 0; i < avaibleValues.size(); i++) {
			if(avaibleValues.get(i).equals(obj)) return true;
		}
		return false;
	}
}
