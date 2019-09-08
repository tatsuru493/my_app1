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
	
	//　--View()とは逆方向にページがめくられるアニメーション、座標を変えただけ--
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
	public void transitionTask() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					//　-ページ番号を設定、シーンを取得-
					int num = getNum();
					AnchorPane root = FXMLLoader.load( getClass().getResource(pageIndex[num]) );
					//　-シーンをセット-
					//　スレッド処理中にシーンをセットしようとするとエラーとなってしまうので、
					//　Platform.runLaterでアイドル状態になるまで待機してから実行する
					Platform.runLater( () -> setStage.setScene(new Scene(root)) );
					//　-タイマーをキャンセルしておかないと2回目以降使用できなくなる-
					animationTimer.cancel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		//　-タイマーを生成し、アニメーション開始から0.4秒後にtaskを実行、画面遷移する-
		animationTimer = new Timer();
		animationTimer.schedule(task, 400);
	}
	
	//　--10分間モード時に結果表示するためのタスク--
	public void measure10minTask() {
		TimerTask measureTask = new TimerTask() {
			@Override
			public void run()  {
				//　-スレッド処理中なのでPlatform.runLater-
				Platform.runLater( () -> View() );
				setNum(3);
				transitionTask();
				measureTimer.cancel();
			}
		};
		//　-10分後に結果表示画面へ-
		measureTimer = new Timer(false);
		measureTimer.schedule(measureTask, 6000);
	}
	
//---------------------------------
	
	//　--page1のonModeMとonModeQにて実行、引数でモードを設定しpage2へ遷移--
	public void setPage2(int md) {
		//　-ページとモードの設定-
		mode = md;
		setNum(1);
		//　-アニメーション-
		View();
		//　-画面遷移-
		transitionTask();
	}
	
	//　--page1のonCloseにて実行、ウィンドウを閉じアプリを終了させる--
	public void closeWindow(ActionEvent event) {
		//　-ボタンのアクションイベントからシーンを取得、シーンからウィンドウを取得-
		Scene scene = ( (Node) event.getSource() ).getScene();
		Window window = scene.getWindow();
		//　-取得したウィンドウをhideメソッドによって閉じる-
		window.hide();
	}
	
	//　--page2のinitializeで実行され、モードによってcheckLabelに入る文字列を返す--
	public String checkLabelText(int md) {
		switch(md) {
    	case 1:
    		return ("10分間に何問できるか計測します");
    	case 2:
    		return ("10問にかかる時間を計測します");
    	default:
    		return ("error");
    	}
	}
	
	//　-他ページからpage1に戻るときに使用、backViewで戻っているように見せる-
	public void backToPage1() {
		setNum(0);
		backView();
		transitionTask();
	}
	
	//　--page2のonOKにて実行、計測の準備をしつつpage3へ遷移--
	public void setPage3() {
		//　-計測用データを初期化-
		count = 0;
		score = 0;
		questionList.clear();
		answerList.clear();
		markList.clear();
		startTime = Calendar.getInstance();
		//　-10分間モードではここでタスクをセットし10分後自動的に結果表示画面（page4）へ遷移する-
		if(mode == 1) {
			measure10minTask();
		}
		//　-page3へ遷移-
		setNum(2);
		View();
		transitionTask();
	}
	
	//　--page3のinitializeで実行され、numberLabelに入る問題数の文字列を返す--
	
	
	//　--page3のonActionTextFieldとonNextにて実行、
	//　　　問題文と入力内容を比較、正誤判定し各種データに代入、
	//　　　次の問題へ移行--
	
	
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
