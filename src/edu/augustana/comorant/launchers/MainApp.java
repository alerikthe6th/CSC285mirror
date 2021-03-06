/* Authors: Michael J. Currie, Al Vi, Scott Doberstein, Joe Godfrey
 * Augustana Computer Science 285 - Software development 
 * Fall 2015 (August - November)
 * Do not reproduce (as a whole or as pieces of code) without prior permission.
 */

package edu.augustana.comorant.launchers;

import java.io.IOException;

import edu.augustana.comorant.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class MainApp extends Application {

	Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Cormorant Order Tracking Software v1.0");
		this.primaryStage.getIcons().add(new Image("comorantIconBorder2.png"));

		initRootLayout();
	}

	/** Initializes the root layout. */
	void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/edu/augustana/comorant/fxml/potteryGUI.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			// Give the controller access to the main app.
			MainController mainController = loader.getController();
			mainController.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** launches the application */
	public static void main(String[] args) {
		launch(args);
	}
}


