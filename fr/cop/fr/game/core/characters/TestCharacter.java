package fr.game.core.characters;

import java.awt.Color;
import java.awt.Graphics;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.visual.Animation;

public class TestCharacter extends Character {

	public TestCharacter() {
	}

	@Override
	public int getId() {
		return 1;
	}

	@Override
	public String getName() {
		return "Test";
	}

	@Override
	public void spell0() {
		Conflict_Of_Pixels_Client.animations.add(new Animation(0, 0, 500, 500, 40, "/fr/cop/resources/images/characters/Test/spellAnimation/Spell0.png"));
	}

	@Override
	public void spell1() {
		Conflict_Of_Pixels_Client.animations.add(new Animation(0, 0, 500, 500, 40, "/fr/cop/resources/images/characters/Test/spellAnimation/Spell1.png"));
		
	}

	@Override
	public void spell2() {
		Conflict_Of_Pixels_Client.animations.add(new Animation(0, 0, 500, 500, 40, "/fr/cop/resources/images/characters/Test/spellAnimation/Spell2.png"));
		
	}

	@Override
	public void spell3() {
		Conflict_Of_Pixels_Client.animations.add(new Animation(0, 0, 500, 500, 40, "/fr/cop/resources/images/characters/Test/spellAnimation/Spell3.png"));
		
	}
}
