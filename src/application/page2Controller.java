package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class page2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button OK_Button;

    @FXML
    private Label label1;

    @FXML
    void onOK(ActionEvent event) {

    }

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'page2.fxml'.";
        assert OK_Button != null : "fx:id=\"OK_Button\" was not injected: check your FXML file 'page2.fxml'.";
        assert label1 != null : "fx:id=\"label1\" was not injected: check your FXML file 'page2.fxml'.";

    }
}
