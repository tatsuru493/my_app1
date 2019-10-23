package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.application.Platform;

public class page1Controller {
	
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
    	Main.getInstance().setPage2(1);
    }

    @FXML
    void onModeQ(ActionEvent event) {
    	Main.getInstance().setPage2(2);
    }

    @FXML
    void onClose(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void initialize() {
        assert modeQ_Button != null : "fx:id=\"modeQ_Button\" was not injected: check your FXML file 'page1.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'page1.fxml'.";
        assert modeM_Button != null : "fx:id=\"modeM_Button\" was not injected: check your FXML file 'page1.fxml'.";

    }
}
