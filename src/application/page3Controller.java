package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class page3Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nextButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField TextField1;

    @FXML
    private Label numberLabel;

    @FXML
    private Label sentenceLabel;

    @FXML
    void onActionTextField(ActionEvent event) {

    }

    @FXML
    void onNext(ActionEvent event) {

    }

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'page3.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'page3.fxml'.";
        assert TextField1 != null : "fx:id=\"TextField1\" was not injected: check your FXML file 'page3.fxml'.";
        assert numberLabel != null : "fx:id=\"numberLabel\" was not injected: check your FXML file 'page3.fxml'.";
        assert sentenceLabel != null : "fx:id=\"sentenceLabel\" was not injected: check your FXML file 'page3.fxml'.";

    }
}
