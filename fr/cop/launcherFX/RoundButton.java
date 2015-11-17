package fr.cop.launcherFX;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class RoundButton extends Button {

	Circle c;

	public RoundButton(String text, Font f) {
		super(text);
		Circle c = new Circle(100);
		getChildren().add(c);
		Image img = new Image(LauncherV2.class.getResource("/fr/cop/resources/launcher/images/roundBg.png").toExternalForm(), 200, 200, false, true);
		setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		setAlignment(Pos.CENTER);
		setPrefSize(img.getWidth(), img.getHeight());
		setFont(Font.font("Zekton Rg", 60));
		setTextFill(Color.YELLOW);
	}

	@Override
	public boolean contains(double localX, double localY) {
		return c.contains(localX, localY);
	}
}
