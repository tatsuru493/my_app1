package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class page2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label checkLabel;

    @FXML
    private Button OK_Button;

    @FXML
    private Button cancelButton_p2;

    @FXML
    void onCancel_p2(ActionEvent event) {

    }

    @FXML
    void onOK(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert checkLabel != null : "fx:id=\"checkLabel\" was not injected: check your FXML file 'page2.fxml'.";
        assert OK_Button != null : "fx:id=\"OK_Button\" was not injected: check your FXML file 'page2.fxml'.";
        assert cancelButton_p2 != null : "fx:id=\"cancelButton_p2\" was not injected: check your FXML file 'page2.fxml'.";

    }
}