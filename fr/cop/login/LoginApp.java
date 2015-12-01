package fr.cop.login;

import fr.cop.common.Profil;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.serverConnection.ServerListener;
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

	private LauncherV2 launcher; Stage stage; public static LoginApp app;

	@Override
	public void start(Stage primaryStage) {
		Game_Frame.serverListener = new ServerListener(Game_Frame.serverIP);
		if (Game_Frame.serverListener.isConnected()) {
			app = this;
			stage = primaryStage;
			Button btnConnect = new Button();
			Label lblID = new Label("Pseudo :");
			Label lblPW = new Label("Mot de passe :");
			TextField tfID = new TextField();
			TextField tfPW = new TextField();
			btnConnect.setText("Connect");
			btnConnect.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Game_Frame.serverListener.getSender().send("client:request_connection " + tfID.getText() + tfPW.getText());
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
		} else {
			Game_Frame.logger.logErr("<Connection !>", "Error, can not reach server....");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void connect(String UUID) {
		Game_Frame.connectedProfil = new Profil(UUID);
		Game_Frame.connectedProfil.setNickname(UUID);
		launcher = new LauncherV2();
		stage.close();
		launcher.show();

	}
}
