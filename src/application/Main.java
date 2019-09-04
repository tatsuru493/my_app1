package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import java.util.List;
import java.util.ArrayList;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
//---------------------------------
	
	public static Main singleton;	//　インスタンスを保持
	public static Main getInstance() {
		return singleton;
	}
	
	private Stage setStage = new Stage();	//　他のコントローラからStageを取得
	
	private int mode;	// 1が10分間モード、2が10問モード
	
	private int count;	//　問題数
	
	private int score;	//　正解数
	
	private List<String> questionList = new ArrayList<String>();	//　問の文を格納
	private List<String> answerList = new ArrayList<String>();	//　解答の文を格納
	private List<String> markList = new ArrayList<String>();	//　正誤（○×）を格納
	
//---------------------------------
	
	@Override
	public void start(Stage primaryStage) {
		try {
			singleton = this;
			setStage = primaryStage;
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("page1.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
