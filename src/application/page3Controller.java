package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class page3Controller implements Initializable {

	Sentence sentence = new Sentence();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label quesNumLabel;

    @FXML
    private Label quesSentLabel;

    @FXML
    private TextField ansTextField;

    @FXML
    private Button nextQuestionButton;

    @FXML
    private Button cancelButton;

    @FXML
    void onAnsTextFieldAction(ActionEvent event) {
    	Main.getInstance().nextQuestion(quesSentLabel.getText(), ansTextField.getText());
    }
    
    @FXML
    void onNextQuestionButton(ActionEvent event) {
    	Main.getInstance().nextQuestion(quesSentLabel.getText(), ansTextField.getText());
    }

    @FXML
    void onCancelButton(ActionEvent event) {
    	Main.getInstance().cancel();
    }

    @FXML
    void initialize() {
        assert quesNumLabel != null : "fx:id=\"numberLabel\" was not injected: check your FXML file 'page3.fxml'.";
        assert quesSentLabel != null : "fx:id=\"sentenceLabel\" was not injected: check your FXML file 'page3.fxml'.";
        assert ansTextField != null : "fx:id=\"TextField1\" was not injected: check your FXML file 'page3.fxml'.";
        assert nextQuestionButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'page3.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton_p3\" was not injected: check your FXML file 'page3.fxml'.";

    }
    
    @Override
    public void initialize(URL loacation, ResourceBundle resources) {
    	quesNumLabel.setText((Main.getInstance().getQuesCount() + 1)  + "問目");
    	quesSentLabel.setText(sentence.question());
    }
    
}
