package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class page4Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label scoreLabel;

    @FXML
    private ListView<String> ListView1;
    private ObservableList<String> items;

    @FXML
    private Button prevButton;

    @FXML
    private Label perfectLabel;

    @FXML
    private Label resultLabel;

    @FXML
    void onPrev(ActionEvent event) {
    	Main.getInstance().backToPage1();
    }

    @FXML
    void initialize() {
        assert scoreLabel != null : "fx:id=\"correctLabel\" was not injected: check your FXML file 'page4.fxml'.";
        assert ListView1 != null : "fx:id=\"ListView1\" was not injected: check your FXML file 'page4.fxml'.";
        assert prevButton != null : "fx:id=\"prevButton\" was not injected: check your FXML file 'page4.fxml'.";
        assert perfectLabel != null : "fx:id=\"perfectLabel\" was not injected: check your FXML file 'page4.fxml'.";
        assert resultLabel != null : "fx:id=\"resultLabel\" was not injected: check your FXML file 'page4.fxml'.";

    }
    
    @Override
    public void initialize(URL loacation, ResourceBundle resources) {
    	switch(Main.getInstance().getMode()) {
    	case 1:
    		resultLabel.setText("回答数 : " + Main.getInstance().getCount() + "問");
    		break;
    	case 2:
    		resultLabel.setText(Main.getInstance().resultTime());
    		break;
    	default:
    		resultLabel.setText("error");
    		break;
    	}
    	if(Main.getInstance().getCount() != 0 && Main.getInstance().getCount() == Main.getInstance().getScore()) {
    		scoreLabel.setText("正解数 : " + Main.getInstance().getScore() + "問");
    		perfectLabel.setText("PERFECT!!");
    	} else {
    		scoreLabel.setText("正解数 : " + Main.getInstance().getScore() + "問");
    		perfectLabel.setText(" ");
    	}
    	items = FXCollections.observableArrayList();
    	ListView1.setItems(items);
    	for(int c = 0; c < Main.getInstance().getCount(); c++) {
    		items.add(Main.getInstance().getMark(c));
    		items.add(Main.getInstance().getAnswer(c));
    		items.add(Main.getInstance().getQuestion(c));
    		items.add(" ");
    	}
    }
    
}
