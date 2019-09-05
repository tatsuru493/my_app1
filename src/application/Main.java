package application;
	
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXMLLoader;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.Calendar;


public class Main extends Application {
	
//---------------------------------
	
	public static Main singleton;	//　インスタンスを保持
	public static Main getInstance() {
		return singleton;
	}
	
	private Stage setStage = new Stage();	//　他のコントローラからStageを取得
	
	private int mode;	//　1が10分間モード、2が10問モード
	
	private int count;	//　問題数
	
	private int score;	//　正解数
	
	private List<String> questionList = new ArrayList<String>();	//　問の文を格納
	private List<String> answerList = new ArrayList<String>();	//　解答の文を格納
	private List<String> markList = new ArrayList<String>();	//　正誤（○×）を格納
	
	private Timer measureTimer = new Timer(false);	//　10分間モード用タイマー
	private Timer animationTimer = new Timer(false);	//　アニメーションを挟んだ画面遷移に使う
	
	private Calendar startTime;	//　10問モード結果表示用
	private Calendar endTime;	//　end.getTimeInMillis()-start.getTimeInMillis()でタイムを計算
	
//---------------------------------

	//　--10問モード結果表示画面移行時に実行、タイムを分秒で出力--
	public String resultTime() {
		String tm;
		//　-測定終了、かかった時間をミリ秒で計算-
		endTime = Calendar.getInstance();
		long mill = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		//　-ミリ秒を分秒に変換-
		int TIME = (int)(mill/1000);
		int min = TIME / 60;
		int sec = TIME % 60;
		tm = (min + "分" + sec + "秒");
		return tm;
	}
	
	//　--画面遷移時にページがめくられるようなアニメーションを挟む--
	public void View() {
		Group g = new Group();
		Rectangle rect = new Rectangle(0,-10,10,410);
		DropShadow dropshadow = new DropShadow(BlurType.GAUSSIAN, Color.GRAY, 10, 0.3, -1, 0);
		rect.setEffect(dropshadow);
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.3), rect);
		animation.setFromY(0);
		animation.setToY(0);
		animation.setFromX(-10);
		animation.setToX(910);
		animation.play();
		g.getChildren().add(rect);
		setStage.setScene(new Scene(g, 900, 400));
	}
	
	//　--上記と逆方向にページがめくられるアニメーション--
	public void backView() {
		Group g = new Group();
		Rectangle rect = new Rectangle(0,-10,10,410);
		DropShadow dropshadow = new DropShadow(BlurType.GAUSSIAN, Color.GRAY, 10, 0.3, -1, 0);
		rect.setEffect(dropshadow);
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.3), rect);
		animation.setFromY(0);
		animation.setToY(0);
		animation.setFromX(910);
		animation.setToX(-10);
		animation.play();
		g.getChildren().add(rect);
		setStage.setScene(new Scene(g, 900, 400));
	}
	
//---------------------------------
	
	public void setPage2_10minMode() {
		
	}
	
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
	
	public static void main(String[] args) {
		launch(args);
	}
		
}
