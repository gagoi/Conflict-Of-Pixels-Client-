package fr.cop.game.graphics.hud;

import fr.cop.game.core.Game_Frame;

public class HUD_map extends HUD_Element {

	public HUD_map() {
		super("/fr/cop/resources/textures/hud/mapBackground", "HUD_Map");
		setScale(0.25f);
		setCoords(Game_Frame.size.width-getScaledWidth(), Game_Frame.size.height-getScaledHeight());
	}
}
