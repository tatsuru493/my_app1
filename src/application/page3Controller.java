package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class page3Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label numberLabel;

    @FXML
    private Label sentenceLabel;

    @FXML
    private TextField TextField1;

    @FXML
    private Button nextButton;

    @FXML
    private Button cancelButton_p3;

    @FXML
    void onActionTextField(ActionEvent event) {

    }

    @FXML
    void onCancel_p3(ActionEvent event) {

    }

    @FXML
    void onNext(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert numberLabel != null : "fx:id=\"numberLabel\" was not injected: check your FXML file 'page3.fxml'.";
        assert sentenceLabel != null : "fx:id=\"sentenceLabel\" was not injected: check your FXML file 'page3.fxml'.";
        assert TextField1 != null : "fx:id=\"TextField1\" was not injected: check your FXML file 'page3.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'page3.fxml'.";
        assert cancelButton_p3 != null : "fx:id=\"cancelButton_p3\" was not injected: check your FXML file 'page3.fxml'.";

    }
}
