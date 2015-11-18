package fr.cop.launcherFX;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.core.Game_Frame;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LauncherV2 extends Stage {

	static Font f;

	public LauncherV2() {
		try {
			f = Font.loadFont(LauncherV2.class.getResourceAsStream("/fr/cop/resources/menus/fonts/zekton_rg.ttf"), 40);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AnchorPane root = new AnchorPane();
		Scene newsScene = new Scene(root, 1180.0, 584.0);
		// On cree des shapes (=formes) qui vont permettre de mettre les bonnes hitboxs aux boutons.
		Polygon p0 = new Polygon(0, 180, 400, 180, 512, 360, 400, 252, 0, 252);
		Polygon p1 = new Polygon(0, 252, 400, 252, 512, 360, 400, 324, 0, 324);
		Polygon p2 = new Polygon(0, 324, 400, 324, 512, 360, 400, 397, 0, 397);
		Polygon p3 = new Polygon(0, 397, 400, 397, 512, 360, 400, 469, 0, 469);
		Polygon p4 = new Polygon(0, 469, 400, 469, 512, 360, 400, 541, 0, 541);
		//		Polygon details = new Polygon(0, 230, 150, 0, 500, 0, 500, 504 - 40, 150, 504 - 40);
		DetailsPane details = new DetailsPane();

		//On cree des boutons pouvant contenir des images.
		ButtonMenu b0 = new ButtonMenu("Profil", Pos.TOP_LEFT, p0, f, new Image(LauncherV2.class.getResource("/fr/cop/resources/launcher/images/topTab2.png").toExternalForm(), 512, 181, false, true));
		ButtonMenu b1 = new ButtonMenu("Shop", Pos.TOP_LEFT, p1, f, new Image(LauncherV2.class.getResource("/fr/cop/resources/launcher/images/topTab1.png").toExternalForm(), 512, 109, false, true));
		ButtonMenu b2 = new ButtonMenu("News", Pos.CENTER_LEFT, p2, f, new Image(LauncherV2.class.getResource("/fr/cop/resources/launcher/images/middleTab.png").toExternalForm(), 418, 73, false, true));
		ButtonMenu b3 = new ButtonMenu("Friends", Pos.BOTTOM_LEFT, p3, f, new Image(LauncherV2.class.getResource("/fr/cop/resources/launcher/images/bottomTab1.png").toExternalForm(), 512, 109, false, true));
		ButtonMenu b4 = new ButtonMenu("Options", Pos.BOTTOM_LEFT, p4, f, new Image(LauncherV2.class.getResource("/fr/cop/resources/launcher/images/bottomTab2.png").toExternalForm(), 512, 181, false, true));
		RoundButton bCenter = new RoundButton("Play", f);
		ProfilBar profilBar = new ProfilBar();

		AnchorPane.setLeftAnchor(b0, 0.0);
		AnchorPane.setLeftAnchor(b1, 0.0);
		AnchorPane.setLeftAnchor(b2, 0.0);
		AnchorPane.setLeftAnchor(b3, 0.0);
		AnchorPane.setLeftAnchor(b4, 0.0);
		AnchorPane.setLeftAnchor(bCenter, 512.0 - 110);
		AnchorPane.setLeftAnchor(details, 550.0);
		AnchorPane.setLeftAnchor(profilBar, 0.0);

		AnchorPane.setTopAnchor(b0, 92.0 + 20);
		AnchorPane.setTopAnchor(b1, 72 * 2.0 + 20 + 20);
		AnchorPane.setTopAnchor(b2, 72 * 3.0 + 20 + 20);
		AnchorPane.setTopAnchor(b3, 72 * 3.0 + 56 + 20);
		AnchorPane.setTopAnchor(b4, 72 * 3.0 + 56 + 20);
		AnchorPane.setTopAnchor(bCenter, 175.0 + 20);
		AnchorPane.setTopAnchor(details, 20.0 + 20);
		AnchorPane.setTopAnchor(profilBar, 0.0);

		bCenter.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					hide();
					Conflict_Of_Pixels_Client.gameFrame = new Game_Frame();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		root.getChildren().addAll(b0, b1, b2, b3, b4, details, bCenter, profilBar);

		setScene(newsScene);
		sizeToScene();
		centerOnScreen();
		setResizable(false);
		setTitle("Conflict Of Pixels Launcher");
		getIcons().add(new Image(LauncherV2.class.getResource("/fr/cop/resources/icons/icon.png").toExternalForm(), 512, 181, false, true));
		show();
	}
}
