

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewOrderController implements Initializable {

	@FXML
	private Button cancelOrderButton;
	@FXML
	private MainApp mainApp;

	public NewOrderController() {

	}

	@FXML
	public void cancelOrderButtonPressed(ActionEvent e) {
		System.out.println("Cancel Order!");
		Stage stage = (Stage) cancelOrderButton.getScene().getWindow();
		// do what you have to do
		stage.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert cancelOrderButton != null : "fx:id=\"cancelOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
