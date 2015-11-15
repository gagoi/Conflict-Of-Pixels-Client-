package fr.cop.launcher;

import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.game.core.Game_Frame;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class LauncherV2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		AnchorPane root = new AnchorPane();
		Scene newsScene = new Scene(root, 1280.0, 720.0);
		Polygon b0 = new Polygon(0, 180, 400, 180, 512, 360, 400, 252, 0, 252);
		Polygon b1 = new Polygon(0, 252, 400, 252, 512, 360, 400, 324, 0, 324);
		Polygon b2 = new Polygon(0, 324, 400, 324, 512, 360, 400, 397, 0, 397);
		Polygon b3 = new Polygon(0, 397, 400, 397, 512, 360, 400, 469, 0, 469);
		Polygon b4 = new Polygon(0, 469, 400, 469, 512, 360, 400, 541, 0, 541);
		Polygon details = new Polygon(0, 230, 150, 0, 500, 0, 500, newsScene.getHeight()-40, 150, newsScene.getHeight()-40);
		Circle center = new Circle(0, 0, 100);

		AnchorPane.setLeftAnchor(b0, 0.0);
		AnchorPane.setLeftAnchor(b1, 0.0);
		AnchorPane.setLeftAnchor(b2, 0.0);
		AnchorPane.setLeftAnchor(b3, 0.0);
		AnchorPane.setLeftAnchor(b4, 0.0);
		AnchorPane.setLeftAnchor(center, 512.0-100);
		AnchorPane.setLeftAnchor(details, 512.0);

		AnchorPane.setTopAnchor(b0, 72.0);
		AnchorPane.setTopAnchor(b1, 72 * 2.0);
		AnchorPane.setTopAnchor(b2, 72 * 3.0);
		AnchorPane.setTopAnchor(b3, 72 * 3.0 + 36);
		AnchorPane.setTopAnchor(b4, 72 * 3.0 + 36);
		AnchorPane.setTopAnchor(center, 150.0);
		AnchorPane.setTopAnchor(details, 20.0);

		b0.setFill(Color.AQUAMARINE);
		b1.setFill(Color.DARKCYAN);
		b2.setFill(Color.CRIMSON);
		b3.setFill(Color.STEELBLUE);
		b4.setFill(Color.TEAL);
		details.setFill(Color.DARKRED);

		center.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					stop();
					primaryStage.hide();
					Conflict_Of_Pixels_Client.gameFrame = new Game_Frame();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		root.getChildren().addAll(b0, b1, b2, b3, b4, details,center);

		primaryStage.setScene(newsScene);
		primaryStage.show();

	}

}
