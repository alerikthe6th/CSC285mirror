
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML
	private Button newOrderButton;
	private MainApp mainApp;
	@FXML
	private ComboBox<String> cmbOrderStatus;

	public MainController() {

	}

	@FXML
	public void newOrderButtonPressed(ActionEvent e) {
		System.out.println("New Order!");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("newOrderGUI.fxml"));
			Stage stage = new Stage();
			stage.setTitle("New Order");
			stage.setScene(new Scene(root));
			stage.show();

			// hide this current window (if this is what you want
			// ((Node)(e.getSource())).getScene().getWindow().hide();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void populateOrderStatus() {

		ObservableList<String> options = FXCollections.observableArrayList("Option A", "Option B", "Option C");
		cmbOrderStatus.setItems(options);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert newOrderButton != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		assert cmbOrderStatus != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
		populateOrderStatus();

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}