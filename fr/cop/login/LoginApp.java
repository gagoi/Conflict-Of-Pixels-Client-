package fr.cop.login;

import fr.cop.common.Profil;
import fr.cop.game.core.Conflict_Of_Pixels_Client;
import fr.cop.launcherFX.LauncherV2;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginApp extends Application {

	private static String progArgs[];
	private LauncherV2 launcher;
	
	@Override
	public void start(Stage primaryStage) {
		Button btnConnect = new Button();
		Label lblID = new Label("Pseudo :");
		Label lblPW = new Label("Mot de passe :");
		TextField tfID = new TextField();
		TextField tfPW = new TextField();
		btnConnect.setText("Connect");
		btnConnect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Conflict_Of_Pixels_Client.testProfil = new Profil();
				Conflict_Of_Pixels_Client.testProfil.setNickname(tfID.getText());
				launcher = new LauncherV2();
				primaryStage.close();
				launcher.show();
			}
		});

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(2.0f);
		root.setVgap(5.0f);
		root.add(lblID, 0, 0);
		root.add(tfID, 1, 0);
		root.add(lblPW, 0, 1);
		root.add(tfPW, 1, 1);
		root.add(btnConnect, 0, 2);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Conflict of Pixels : Login");
		primaryStage.show();
	}

	public static void main(String[] args) {
		progArgs = args;
		Conflict_Of_Pixels_Client.testProfil = new Profil();
		launch(args);
	}
}