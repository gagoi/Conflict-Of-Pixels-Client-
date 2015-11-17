package fr.cop.launcherFX;

import fr.cop.common.Profil;
import fr.cop.game.core.Conflict_Of_Pixels_Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ProfilBar extends StackPane {

	Profil prof = Conflict_Of_Pixels_Client.testProfil;

	public ProfilBar() {
		Image bg = new Image(LauncherV2.class.getResource("/fr/cop/resources/launcher/images/profilBarBg.png").toExternalForm(), 650, 150 * 2 / 3, false, true);
		setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		setPrefSize(bg.getWidth(), bg.getHeight());

		ProgressBar xpBar = new ProgressBar(prof.getXpAmmountInLevel());
		setAlignment(xpBar, Pos.BOTTOM_LEFT);
		setMargin(xpBar, new Insets(5, 0, 20, 0));
		xpBar.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, new Insets(0))));
		xpBar.setPrefSize(430, 15);
		xpBar.setTooltip(new Tooltip(prof.getXpAmmountInLevel() + "/" + prof.getNextLevelNeededXp() + " xp"));

		Image icon = new Image(LauncherV2.class.getResource("/fr/cop/resources/icons/" + prof.getIcon() + ".png").toExternalForm(), 50, 50, false, true);
		ImageView iconView = new ImageView(icon);
		setAlignment(iconView, Pos.TOP_LEFT);
		setMargin(iconView, new Insets(5));
		
		Text nicknameText = new Text("     " + prof.getNickname());
		nicknameText.setFont(Font.font("Zekton Rg", 50));
		nicknameText.setFill(Color.YELLOW);
		setAlignment(nicknameText, Pos.TOP_LEFT);
		
		

		getChildren().addAll(xpBar, iconView, nicknameText);
	}
}
