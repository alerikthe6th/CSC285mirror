

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainController implements Initializable{
	
	@FXML
	private Button newOrderButton;
	
	    
	    @FXML
	    public void newOrderButtonPressed(ActionEvent e){
	    	System.out.println("New Order!");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("newOrderGUI.fxml"));
	        GridPane newWindow;
			try {
				newWindow = (GridPane)loader.load();
				//ChildController controller = loader.getController();
		        //controller.setMainWindow(this);
		        Stage stage = new Stage();
		        stage.initModality(Modality.WINDOW_MODAL);
		        stage.initOwner(newOrderButton.getScene().getWindow());
		        Scene scene = new Scene(newWindow);
		        stage.setScene(scene);
		        stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	          
	    	
	    	
	}





		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			assert newOrderButton != null : "fx:id=\"newOrderButton\" was not injected: check your FXML file 'potteryGUI.fxml'.";
			
		}

}
