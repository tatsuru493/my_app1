package application;
	
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;


public class Main extends Application {
	
//---↓↓メンバ変数とアクセサメソッド↓↓---
	
	// 他のコントローラ間で値を共有したいのでシングルトンパターンとした
	public static Main singleton;
	public static Main getInstance() { return singleton; }
	
	private Stage setStage = new Stage();
	
	private int mode;
	public int getMode() { return mode; }
	
	private int count;
	public int getCount() { return count; }
	
	private int score;
	public int getScore() { return score; }
	
	private List<String> markList = new ArrayList<String>();
	private List<String> answerList = new ArrayList<String>();
	private List<String> questionList = new ArrayList<String>();
	public String getMark(int c) { return (c + 1) + "問目 : " + markList.get(c); }
	public String getAnswer(int c) { return answerList.get(c); }
	public String getQuestion(int c) { return questionList.get(c); }
	
	private Timer measureTimer = new Timer(false);
	private Timer animationTimer = new Timer(false);
	
	private Calendar startTime;
	private Calendar endTime;
	
	private final String[] pageIndex
		= new String[] { "page1.fxml", "page2.fxml", "page3.fxml", "page4.fxml" };
	private int pageNum;
	
//---↓↓メソッド↓↓---

	//　--10問モード結果表示画面移行時に実行、タイムを分秒で出力--
	public String resultTime() {
		String tm;
		long mill = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		int TIME = (int)(mill/1000);
		int min = TIME / 60;
		int sec = TIME % 60;
		tm = ("TIME : " + min + "分" + sec + "秒");
		return tm;
	}
	
	//　--ページがめくられるようなアニメーション--
	public void View() {
		Group g = new Group();
		Rectangle rect = new Rectangle(0,-10,10,410);
		DropShadow dropshadow = new DropShadow(BlurType.GAUSSIAN, Color.GRAY, 10, 0.3, -1, 0);
		rect.setEffect(dropshadow);
		g.getChildren().add(rect);
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.3), rect);
		animation.setFromY(0);
		animation.setToY(0);
		animation.setFromX(-10);
		animation.setToX(910);
		setStage.setScene(new Scene(g, 900, 400));
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
	
	//　--アニメーションから指定のページに遷移する--
	public void transitionTask() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					AnchorPane root = FXMLLoader.load(getClass().getResource(pageIndex[pageNum]));
					//　スレッド処理中にシーンをセットしようとするとエラーとなってしまうのでPlatform.runLaterでアイドル状態になるまで待機してから実行する
					Platform.runLater( () -> setStage.setScene(new Scene(root)) );
					animationTimer.cancel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		animationTimer = new Timer();
		animationTimer.schedule(task, 400);
	}
	
	//　--10分間モード時に結果表示するためのタスク--
	public void measure10minTask() {
		TimerTask measureTask = new TimerTask() {
			@Override
			public void run()  {
				Platform.runLater( () -> View() );
				pageNum = 3;
				transitionTask();
				measureTimer.cancel();
			}
		};
		measureTimer = new Timer(false);
		measureTimer.schedule(measureTask, 6000);
	}
	
	
	//　--誤字があった箇所の文字列--
	public String getTypo(int c) {
		List<Character> answerChar = new ArrayList<Character>();
		List<Character> questionChar = new ArrayList<Character>();
		List<String> typoList = new ArrayList<String>();
		String typo = "";
		for(int a = 0; a < answerList.get(c).length(); a++) {
			answerChar.add( answerList.get(c).charAt(a) );
		}
		for(int a = 0; a < questionList.get(c).length(); a++) {
			questionChar.add(questionList.get(c).charAt(a));
		}
		for(int a= 0; a < answerChar.size() && a < questionChar.size(); a++) {
			if(answerChar.get(a) == questionChar.get(a)) {
				typoList.add("  ");
			} else {
				typoList.add(String.valueOf(answerChar.get(a)));
			}
		}
		for(int a = 0; a < typoList.size(); a++) {
			typo = typo + typoList.get(a);
		}
		return typo;
	}
	
//---↓↓↓イベント・ハンドラにて実行されるメソッド↓↓↓---
	
	//　--page1のonModeMとonModeQにて実行、引数でモードを設定しpage2へ遷移--
	public void setPage2(int md) {
		mode = md;
		pageNum = 1;
		View();
		transitionTask();
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
	
	
	//　--他ページからpage1に戻るときに実行、backViewで戻っているように見せる--
	public void backToPage1() {
		pageNum = 0;
		backView();
		transitionTask();
	}
	
	
	//　--page2のonOKにて実行、計測の準備をしつつpage3へ遷移--
	public void setPage3() {
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
		
		pageNum = 2;
		View();
		transitionTask();
	}
	
	
	//　--page3のonActionTextFieldとonNextにて実行、page3を再びセットする--
	public void nextQuestion(String sentence, String textField) {
		
		if(sentence.equals(textField)) {
			score++;
			markList.add("○");
		} else {
			markList.add("×");
		}
		questionList.add(sentence);
		answerList.add(textField);
		count++;
		
		// -10問モードの場合、10問目が終わった時点で測定を終了させ、結果表示画面（page4）へ遷移する-
		if(mode == 2 && count > 9) {
			endTime = Calendar.getInstance();
			pageNum = 3;
			View();
			transitionTask();
			return;
		}
		
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("page3.fxml"));
			setStage.setScene(new Scene(root));			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// --page3のonCancel_p3にて実行、測定を終了させ結果表示画面(page4)へ遷移する--
	public void cancel() {
		if(mode == 1) {
			measureTimer.cancel();
		} else if(mode == 2) {
			endTime = Calendar.getInstance();
		}
		pageNum = 3;
		View();
		transitionTask();
	}
	
	// --page4にてモードによってresultLabelに入る文字列を返す--
	public String setResult(int md) {
		switch(md) {
		case 1:
			return ("回答数 : " + count + "問");
		case 2:
			return resultTime();
		default:
			return ("error");
		}
	}
	
	// --page4にてモードによってperfectLabelに入る文字列を返す--
	public String setPerfect(boolean b) {
		if(b) {
			return ("PERFECT!!");
		} else {
			return (" ");
		}
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
	
	@Override
	public void stop() {
		System.exit(0);
	}
		
	public static void main(String[] args) {
		launch(args);
	}
		
}
