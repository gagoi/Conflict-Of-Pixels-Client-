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
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class ButtonMenu extends Button {

	Polygon p;

	public ButtonMenu(Pos textPos, Polygon p, Font f, Image img) {
		super();
		this.p = p;
		setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		setAlignment(textPos);
		getChildren().add(p);
		setPrefSize(img.getWidth(), img.getHeight());
		setTextFill(Color.YELLOW);
		setFont(f);
	}

	public ButtonMenu(String text, Pos textPos, Polygon p, Font f, Image img) {
		this(textPos, p, f, img);
		setText(text);
	}

	@Override
	public boolean contains(double localX, double localY) {
		return p.contains(localX, localY);
	}

}
