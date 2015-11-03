package org.ilpider.games.freccette;

import org.ilpider.games.freccette.model.PartitaModel;
import org.ilpider.games.freccette.view.WelcomeController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Welcome.fxml"));

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);

			PartitaModel partitaModel = new PartitaModel() ;
			WelcomeController controller = loader.getController() ;
			controller.setPartitaModel(partitaModel);

			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
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

	// TODO metodo creaNuovaPartita in Main
	public void creaNuovaPartita() {
		PartitaModel partitaModel = new PartitaModel() ;
		WelcomeController controller = loader.getController() ;
		controller.setPartitaModel(partitaModel);
//		return model;
	}
}
