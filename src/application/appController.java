package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class appController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//-----page1-----
    
    @FXML
    private Button modeQ_Button;

    @FXML
    private Button closeButton;

    @FXML
    private Button modeM_Button;

    @FXML
    void onModeM(ActionEvent event) {

    }

    @FXML
    void onModeQ(ActionEvent event) {

    }

    @FXML
    void onClose(ActionEvent event) {

    }
    
//-----page2-----
    
    @FXML
    private Button OK_Button;

    @FXML
    private Button cancelButton_p2;

    @FXML
    private Label checkLabel;

    @FXML
    void onOK(ActionEvent event) {

    }

    @FXML
    void onCancel_p2(ActionEvent event) {

    }

//-----page3-----
    
    @FXML
    private Button nextButton;

    @FXML
    private TextField TextField1;

    @FXML
    private Button cancelButton_p3;

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
    void onCancel_p3(ActionEvent event) {

    }
    
//-----page4-----
    
    @FXML
    private Label scoreLabel;

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
        assert modeQ_Button != null : "fx:id=\"modeQ_Button\" was not injected: check your FXML file 'page1.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'page1.fxml'.";
        assert modeM_Button != null : "fx:id=\"modeM_Button\" was not injected: check your FXML file 'page1.fxml'.";
        assert OK_Button != null : "fx:id=\"OK_Button\" was not injected: check your FXML file 'page2.fxml'.";
        assert cancelButton_p2 != null : "fx:id=\"cancelButton_p2\" was not injected: check your FXML file 'page2.fxml'.";
        assert checkLabel != null : "fx:id=\"checkLabel\" was not injected: check your FXML file 'page2.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'page3.fxml'.";
        assert TextField1 != null : "fx:id=\"TextField1\" was not injected: check your FXML file 'page3.fxml'.";
        assert cancelButton_p3 != null : "fx:id=\"cancelButton_p3\" was not injected: check your FXML file 'page3.fxml'.";
        assert numberLabel != null : "fx:id=\"numberLabel\" was not injected: check your FXML file 'page3.fxml'.";
        assert sentenceLabel != null : "fx:id=\"sentenceLabel\" was not injected: check your FXML file 'page3.fxml'.";
        assert scoreLabel != null : "fx:id=\"scoreLabel\" was not injected: check your FXML file 'page4.fxml'.";
        assert ListView1 != null : "fx:id=\"ListView1\" was not injected: check your FXML file 'page4.fxml'.";
        assert prevButton != null : "fx:id=\"prevButton\" was not injected: check your FXML file 'page4.fxml'.";
        assert perfectLabel != null : "fx:id=\"perfectLabel\" was not injected: check your FXML file 'page4.fxml'.";
        assert resultLabel != null : "fx:id=\"resultLabel\" was not injected: check your FXML file 'page4.fxml'.";

    }
}
