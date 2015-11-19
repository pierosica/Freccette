package org.ilpider.games.freccette;

import org.ilpider.games.freccette.model.PartitaModel;
import org.ilpider.games.freccette.view.WelcomeViewController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/WelcomeView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);

			PartitaModel partitaModel = new PartitaModel() ;
			WelcomeViewController controller = loader.getController() ;
			controller.setPartitaModel(partitaModel);

			scene.getStylesheets().add(getClass().getResource("view/Freccette.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.setMinWidth(640);
			primaryStage.setMinHeight(350);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
