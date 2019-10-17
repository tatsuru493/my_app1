package application;

import java.util.Random;

public class Sentence {
	
	public String qestion() {
		int value = random.nextInt(sentence.length);
		return sentence[value];
	}
	
	private Random random = new Random();
	
	private String[] sentence = new String[] {
			""
	};

}
