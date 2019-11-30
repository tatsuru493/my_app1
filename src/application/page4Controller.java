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
    private ListView<String> ansDispListView;
    private ObservableList<String> items;

    @FXML
    private Button backToPage1Button;

    @FXML
    private Label evalLabel;

    @FXML
    private Label resultLabel;

    @FXML
    void onBackToPage1Button(ActionEvent event) {
    	Main.getInstance().backToPage1();
    }

    @FXML
    void initialize() {
        assert scoreLabel != null : "fx:id=\"correctLabel\" was not injected: check your FXML file 'page4.fxml'.";
        assert ansDispListView != null : "fx:id=\"ListView1\" was not injected: check your FXML file 'page4.fxml'.";
        assert backToPage1Button != null : "fx:id=\"prevButton\" was not injected: check your FXML file 'page4.fxml'.";
        assert evalLabel != null : "fx:id=\"perfectLabel\" was not injected: check your FXML file 'page4.fxml'.";
        assert resultLabel != null : "fx:id=\"resultLabel\" was not injected: check your FXML file 'page4.fxml'.";

    }
    
    @Override
    public void initialize(URL loacation, ResourceBundle resources) {
    	int mode = Main.getInstance().getMode();
    	boolean perfect = !(Main.getInstance().getQuesCount() == 0)
    					&& Main.getInstance().getQuesCount() == Main.getInstance().getScore();
    	boolean good = !(Main.getInstance().getQuesCount() == 0)
    					&& (float)Main.getInstance().getQuesCount()*4/5 <= (float)Main.getInstance().getScore();
    	
    	resultLabel.setText(Main.getInstance().setResult(mode));
    	scoreLabel.setText("正解数 : " + Main.getInstance().getScore() + "問");
    	evalLabel.setText(Main.getInstance().setPerfect(perfect, good));
    	
    	items = FXCollections.observableArrayList();
    	ansDispListView.setItems(items);
    	for(int c = 0; c < Main.getInstance().getQuesCount(); c++) {
    		items.add( Main.getInstance().getMark(c) );
    		items.add( "入力 : " + Main.getInstance().getAnswer(c) );
    		items.add( "正解 : " + Main.getInstance().getQuestion(c) );
    		if( !(Main.getInstance().getAnswer(c).equals(Main.getInstance().getQuestion(c)) ) ) {
    			items.add( "誤字 : " + Main.getInstance().getTypo(c) );
    		}
    		items.add(" ");
    	}
    	
    	ansDispListView.setCellFactory(ListView -> {
    		final ListCell<String> cell = new ListCell<String>() {
    			@Override
    			protected void updateItem(String item, boolean empty) {
    				super.updateItem(item, empty);
    				if(empty || item == null) {
    					setText(null);
    					setGraphic(null);
    					return;
    				}
    				setText( item.toString() );
    				if( item.contains("誤字 : ") ) {
    					setTextFill(Color.RED);
    				} else {
    					setTextFill(Color.BLACK);
    				}
    			}
    		};
    		return cell;
    	});
    	
    }
    
}
