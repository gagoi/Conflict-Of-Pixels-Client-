package fr.cop.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import fr.cop.common.Profil;
import fr.cop.game.core.Game;
import fr.cop.game.core.Game_Frame;
import fr.cop.game.serverConnection.ServerListener;
import fr.cop.launcherFX.LauncherV2;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginApp extends Application {

	private LauncherV2 launcher;
	Stage stage;
	boolean isConnected;
	public static LoginApp app;

	@Override
	public void start(Stage primaryStage) {
		app = this;

		Button btnConnect = new Button();
		Label lblID = new Label("Pseudo :");
		Label lblPW = new Label("Mot de passe :");
		Label lblUUID = new Label("UUID :");
		Label lblIP = new Label("IP :");
		TextField tfID = new TextField();
		TextField tfUUID = new TextField();
		TextField tfPW = new TextField();
		TextField tfIP = new TextField();
		CheckBox cbID = new CheckBox();
		CheckBox cbUUID = new CheckBox();
		CheckBox cbPW = new CheckBox();
		CheckBox cbIP = new CheckBox();

		File logPropFile = new File(Game_Frame.GAME.serverGame.getPath() + "\\config\\login.properties"); // Fichiers de propriétes.
		if (!logPropFile.exists()) {
			try {
				logPropFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(logPropFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String val = "";
		if (!(val = prop.getProperty("tfUUID_value", "")).equals("")) {
			cbUUID.setSelected(true);
			tfUUID.setText(val);
		}
		if (!(val = prop.getProperty("tfID_value", "")).equals("")) {
			cbID.setSelected(true);
			tfID.setText(val);
		}
		if (!(val = prop.getProperty("tfPW_value", "")).equals("")) {
			cbPW.setSelected(true);
			tfPW.setText(val);
		}
		if (!(val = prop.getProperty("tfIP_value", "")).equals("")) {
			cbIP.setSelected(true);
			tfIP.setText(val);
		}
		cbUUID.setTooltip(new Tooltip("Cocher cette case afin de garder en m�moire les informations de la ligne."));
		cbID.setTooltip(new Tooltip("Cocher cette case afin de garder en m�moire les informations de la ligne."));
		cbPW.setTooltip(new Tooltip("Cocher cette case afin de garder en m�moire les informations de la ligne."));
		cbIP.setTooltip(new Tooltip("Cocher cette case afin de garder en m�moire les informations de la ligne."));

		btnConnect.setText("Connect");
		btnConnect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (verifyIp(tfIP.getText()) && tfUUID.getText() != null && tfUUID.getText().length() == 16 && tfID.getText() != null && tfPW.getText() != null) {

					if (cbUUID.isSelected()) prop.setProperty("tfUUID_value", tfUUID.getText());
					else prop.remove("tfUUID_value");
					if (cbID.isSelected()) prop.setProperty("tfID_value", tfID.getText());
					else prop.remove("tfID_value");
					if (cbPW.isSelected()) prop.setProperty("tfPW_value", tfPW.getText());
					else prop.remove("tfPW_value");
					if (cbIP.isSelected()) prop.setProperty("tfIP_value", tfIP.getText());
					else prop.remove("tfIP_value");
					
					try {
						prop.store(new FileWriter(logPropFile), "");
					} catch (IOException e) {
						e.printStackTrace();
					}

					Game_Frame.serverListener = new ServerListener(tfIP.getText());
					Game_Frame.serverListener.send("client:request_connection " + tfUUID.getText() + " " + tfID.getText() + " " + tfPW.getText());

					while (!isConnected) {
						if (!Game_Frame.serverListener.isConnected()) {
							JOptionPane.showMessageDialog(null, "Le serveur ne r�pond pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
							break;
						}

						launcher = new LauncherV2();
						primaryStage.close();
						launcher.show();
						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Une des cases n'est pas renseigner correctement.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(2.0f);
		root.setVgap(5.0f);
		root.add(lblUUID, 0, 0);
		root.add(tfUUID, 1, 0);
		root.add(cbUUID, 2, 0);
		root.add(lblID, 0, 1);
		root.add(tfID, 1, 1);
		root.add(cbID, 2, 1);
		root.add(lblPW, 0, 2);
		root.add(tfPW, 1, 2);
		root.add(cbPW, 2, 2);
		root.add(lblIP, 0, 3);
		root.add(tfIP, 1, 3);
		root.add(cbIP, 2, 3);
		root.add(btnConnect, 0, 4);

		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Conflict of Pixels : Login");
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.out.println("Deconnection");
				System.exit(0);

			}
		});
		primaryStage.show();

	}

	public static void main(String[] args) {

		Game.GAME_FOLDER = new File("C:\\Conflict Of Pixels\\");
		launch(args);
	}

	public void connect(String UUID, String ID) {
		Game_Frame.connectedProfil = new Profil(UUID);
		Game_Frame.connectedProfil.setNickname(ID);
		isConnected = true;
	}

	private boolean verifyIp(String txt) {
		txt = txt.concat(".");
		ArrayList<String> t = new ArrayList<>();
		String act = "";
		for (int i = 0; i < txt.length(); i++) {
			if (txt.charAt(i) == '.') {
				t.add(act);
				act = "";
			} else act = act + txt.charAt(i);
		}
		if (t != null && t.size() == 4) {
			for (int i = 0; i < t.size(); i++) {
				try {
					if (Integer.parseInt(t.get(i)) > 254 || Integer.parseInt(t.get(i)) < 0) {
						return false;
					}
				} catch (Exception e) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
