package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class page2Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label modeCheckLabel;

    @FXML
    private Button OK_Button;

    @FXML
    private Button backToPage1Button;

    @FXML
    void onOK_Button(ActionEvent event) {
    	Main.getInstance().setPage3();
    }
    
    @FXML
    void onbackToPage1Button(ActionEvent event) {
    	Main.getInstance().backToPage1();
    }

    @FXML
    void initialize() {
        assert modeCheckLabel != null : "fx:id=\"checkLabel\" was not injected: check your FXML file 'page2.fxml'.";
        assert OK_Button != null : "fx:id=\"OK_Button\" was not injected: check your FXML file 'page2.fxml'.";
        assert backToPage1Button != null : "fx:id=\"cancelButton_p2\" was not injected: check your FXML file 'page2.fxml'.";

    }
    
    @Override
    public void initialize(URL loacation, ResourceBundle resources) {
    	//-引数にアクセサメソッドを書くと読みづらくなるため-
    	int mode = Main.getInstance().getMode();
    	modeCheckLabel.setText(Main.getInstance().checkLabelText(mode));
    }
    
}
