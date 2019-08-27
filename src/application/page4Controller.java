package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;

public class page4Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label correctLabel;

    @FXML
    private ListView<?> ListView1;

    @FXML
    private Button prevButton;

    @FXML
    private Label perfectLabel;

    @FXML
    private Label resultLabel;

    @FXML
    void onPrev(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert correctLabel != null : "fx:id=\"correctLabel\" was not injected: check your FXML file 'page4.fxml'.";
        assert ListView1 != null : "fx:id=\"ListView1\" was not injected: check your FXML file 'page4.fxml'.";
        assert prevButton != null : "fx:id=\"prevButton\" was not injected: check your FXML file 'page4.fxml'.";
        assert perfectLabel != null : "fx:id=\"perfectLabel\" was not injected: check your FXML file 'page4.fxml'.";
        assert resultLabel != null : "fx:id=\"resultLabel\" was not injected: check your FXML file 'page4.fxml'.";

    }
}
