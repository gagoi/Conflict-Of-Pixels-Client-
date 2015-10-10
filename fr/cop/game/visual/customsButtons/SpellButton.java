package game.visual.customsButtons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.core.characters.Character;

@SuppressWarnings("serial")
public class SpellButton extends JButton{

	private ImageIcon ico; // Variable contenant l'icone du sort.
	public SpellButton(Conflict_Of_Pixels_Client game, Character champ, int idSpell) {
		ico =new ImageIcon(getClass().getResource("/fr/cop/resources/images/characters/"+ champ.getName()+"/spellIcon/" + idSpell +".png")); // On instancie l'image en fonction de idSpell.
		this.setIcon(ico); //On met l'icone au bouton.
		setBounds(530+60*idSpell, 600, 50, 50); // On délimite les bords du boutons. En fonction de l'id.
		setEnabled(true);
		setBorder(null);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				champ.spell(idSpell);
			}
		});
	}
	
	
}
