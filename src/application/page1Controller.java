package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

public class page1Controller {

	Stage setStage = new Stage();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button modeQ_Button;

    @FXML
    private Button closeButton;

    @FXML
    private Button modeM_Button;

    @FXML
    void onModeM(ActionEvent event) {
    	try {
			AnchorPane root = FXMLLoader.load( getClass().getResource("page2.fxml") );
			setStage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onModeQ(ActionEvent event) {

    }

    @FXML
    void onClose(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert modeQ_Button != null : "fx:id=\"modeQ_Button\" was not injected: check your FXML file 'page1.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'page1.fxml'.";
        assert modeM_Button != null : "fx:id=\"modeM_Button\" was not injected: check your FXML file 'page1.fxml'.";

    }
}
