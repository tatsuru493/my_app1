package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
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
    	int mode = Main.getInstance().getMode();
    	boolean perfect = Main.getInstance().getCount() != 0
    			&& Main.getInstance().getCount() == Main.getInstance().getScore();
    	
    	resultLabel.setText(Main.getInstance().setResult(mode));
    	scoreLabel.setText("正解数 : " + Main.getInstance().getScore() + "問");
    	perfectLabel.setText(Main.getInstance().setPerfect(perfect));
    	
    	items = FXCollections.observableArrayList();
    	ListView1.setItems(items);
    	for(int c = 0; c < Main.getInstance().getCount(); c++) {
    		items.add( Main.getInstance().getMark(c) );
    		items.add( "入力 : " + Main.getInstance().getAnswer(c) );
    		items.add( "正解 : " + Main.getInstance().getQuestion(c) );
    		if( !(Main.getInstance().getAnswer(c).equals(Main.getInstance().getQuestion(c)) ) ) {
    			items.add( "誤字 : " + Main.getInstance().getTypo(c) );
    		}
    		items.add(" ");
    	}
    	
    	// -この部分のネストが深くなってしまっている-
    	ListView1.setCellFactory(ListView -> {
    		final ListCell<String> cell = new ListCell<String>() {
    			@Override
    			protected void updateItem(String item, boolean empty) {
    				super.updateItem(item, empty);
    				if(empty || item == null) {
    					setText(null);
    					setGraphic(null);
    				} else {
    					setText( item.toString() );
    					if( item.contains("誤字 : ") ) {
    						setTextFill(Color.RED);
    					} else {
    						setTextFill(Color.BLACK);
    					}
    				}
    			}
    		};
    		return cell;
    	});
    	
    }
    
}
