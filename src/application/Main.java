package application;
	
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;


public class Main extends Application {
	
//---------------------------------
	
	public static Main singleton;	//　インスタンスを保持
	public static Main getInstance() {
		return singleton;
	}
	
	private Stage setStage = new Stage();	//　他のコントローラからStageを取得
	
	private int mode;	//　1が10分間モード、2が10問モード
	public int getMode() {
		return mode;
	}
	
	private int count;	//　問題数
	
	private int score;	//　正解数
	
	private List<String> questionList = new ArrayList<String>();	//　問の文を格納
	private List<String> answerList = new ArrayList<String>();	//　解答の文を格納
	private List<String> markList = new ArrayList<String>();	//　正誤（○×）を格納
	
	private Timer measureTimer = new Timer(false);	//　10分間モード用タイマー
	private Timer animationTimer = new Timer(false);	//　アニメーションを挟んだ画面遷移に使う
	
	private Calendar startTime;	//　10問モード測定用、測定開始時の現在時間
	private Calendar endTime;	//　10問モード測定用、測定終了時の現在時間
								//　end.getTimeInMillis()-start.getTimeInMillis()でタイムを計算
	
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
		//　-シーングラフ作成-
		Group g = new Group();
		//　-動く図形の形と効果の設定、シーングラフに追加-
		Rectangle rect = new Rectangle(0,-10,10,410);
		DropShadow dropshadow = new DropShadow(BlurType.GAUSSIAN, Color.GRAY, 10, 0.3, -1, 0);
		rect.setEffect(dropshadow);
		g.getChildren().add(rect);
		//　-アニメーションの軌道、時間を設定-
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.3), rect);
		//　-開始、終了座標の設定-
		animation.setFromY(0);
		animation.setToY(0);
		animation.setFromX(-10);
		animation.setToX(910);
		//　-シーンをセット-
		setStage.setScene(new Scene(g, 900, 400));
		//　-アニメーション開始-
		animation.play();
	}
	
	//　--上記と逆方向にページがめくられるアニメーション、座標を変えただけ--
	public void backView() {
		Group g = new Group();
		Rectangle rect = new Rectangle(0,-10,10,410);
		DropShadow dropshadow = new DropShadow(BlurType.GAUSSIAN, Color.GRAY, 10, 0.3, -1, 0);
		rect.setEffect(dropshadow);
		g.getChildren().add(rect);
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.3), rect);
		animation.setFromY(0);
		animation.setToY(0);
		animation.setFromX(910);
		animation.setToX(-10);
		setStage.setScene(new Scene(g, 900, 400));
		animation.play();
	}
	
	//　--遷移するページを設定するための変数とアクセサメソッド--
	private String[] pageIndex = new String[] {"page1.fxml", "page2.fxml", "page3.fxml", "page4.fxml"};
	private int pageNum;
	public void setNum(int num) {pageNum = num;}
	public int getNum() {return pageNum;}	
	
	//　--アニメーションから指定のページに遷移する--
	public TimerTask transitionTask() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					//　-ページ番号を設定、シーンを取得-
					int num = getNum();
					AnchorPane root = FXMLLoader.load( getClass().getResource(pageIndex[num]) );
					//　-シーンをセット-
					//　アニメーション処理中にシーンをセットしようとするとエラーとなってしまうので、
					//　Platform.runLaterでアイドル状態になるまで待機してから実行する
					Platform.runLater( () -> setStage.setScene(new Scene(root)) );
					//　-タイマーをキャンセルしておかないと2回目以降しようできなくなる-
					animationTimer.cancel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		return task;
	}
	
	public TimerTask measure10minTask() {
		TimerTask measureTask = new TimerTask() {
			@Override
			public void run()  {
					Platform.runLater( () -> View() );
					transitionTask();
					animationTimer.schedule(transitionTask(), 400);
					measureTimer.cancel();
			}
		};
		return measureTask;
	}
	
//---------------------------------
	
	public void setPage2_10minMode() {
		//　-タイマー初期化、ページとモードの設定-
		measureTimer = new Timer(false);
		animationTimer = new Timer(false);
		mode = 1;
		setNum(1);
		//　-アニメーション-
		View();
		//　-指定した時間（0.4秒後）にpage2へ遷移-
		animationTimer.schedule(transitionTask(), 400);
	}
	
	public void setPage2_10Q_Mode() {
		//　-タイマー初期化、ページとモードの設定-
		animationTimer = new Timer(false);
		mode = 2;
		setNum(1);
		//　-アニメーション-
		View();
		//　-指定した時間（0.4秒後）にpage2へ遷移-
		animationTimer.schedule(transitionTask(), 400);
	}
	
	public void closeWindow(ActionEvent event) {
		//　-ボタンのアクションイベントからシーンを取得、シーンからウィンドウを取得-
		Scene scene = ( (Node) event.getSource() ).getScene();
		Window window = scene.getWindow();
		//　-取得したウィンドウをhideメソッドによって閉じる-
		window.hide();
	}
	
	public void backToPage1() {
		animationTimer = new Timer(false);
		setNum(0);
		backView();
		animationTimer.schedule(transitionTask(), 400);
	}
	
	public void setPage3() {
		count = 0;
		score = 0;
		questionList.clear();
		answerList.clear();
		markList.clear();
		animationTimer = new Timer(false);
		startTime = Calendar.getInstance();
		if(mode == 1) {
			measureTimer.schedule(measure10minTask(), 600000);
		}
		View();
		animationTimer.schedule(transitionTask(), 400);
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
