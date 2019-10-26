package application;

import java.util.Random;

public class Sentence {
	//　メンバー順序がおかしいが、sentence配列が長くなり読みづらくなるので入れ替えた
	public String question() {
		int value = random.nextInt(sentence.length);
		return sentence[value];
	}
	
	private Random random = new Random();
	
	private final String[] sentence = new String[] {
			"questionメソッドはsentence配列からランダムに文字列を返すメソッドです。",
			"page3Controllerクラスのイニシャライザにてラベルにセットする文字列として使います。",
			"ラベルにセットされた文字列と同じになるようにテキストフィールドに入力してください。",
			"入力が完了したらそのままenterキーを押すとnextQuestionメソッドが呼び出されます。",
			"nextQuestionメソッドはラベルとテキストフィールドの文字列を引数とします。",
			"2つの引数が同じか比較し、真の場合は○、偽の場合は×をmarkListに追加します。",
			"また、真の場合のみscore変数をインクリメントします。比較された引数はその後、",
			"ラベルの値はquestionListに、テキストフィールドの値はanswerListに追加します。",
			"その後、count変数にインクリメントし、再びpage3をセットすることでイニシャライザにて",
			"questionメソッドからランダムに文字列が返されラベルにセットされます。",
			"以上がquestionメソッドが用いられる処理のざっくりとした流れです。",
			"SampleText",
			"サンプルテキスト"
			
	};

}
