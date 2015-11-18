package fr.cop.launcherFX;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class DetailsPane extends StackPane {

	int id = 0;
	static final int TYPE_NEWS = 0;

	public DetailsPane() {
		Image bg = new Image(DetailsPane.class.getResourceAsStream("/fr/cop/resources/launcher/images/detailsPane.png"));
		ImageView imageView = new ImageView(bg);

		getChildren().add(imageView);
		update();
	}

	public void update() {
		switch (id) {
			case TYPE_NEWS:
				Text tv = new Text();
				String file = "";
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/gagoi/Conflict-Of-Pixels-Client-/master/fr/cop/resources/changelog.txt").openConnection().getInputStream()));
					String line = "";
					while ((line = br.readLine()) != null) {
						if (file == null) file = line;
						else file += line;
					}
					tv.setText(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				getChildren().add(tv);
				break;
		}
	}
}
