package com.prestonlee.emphasays.recognizer;

/**
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public class RecognizerGuess implements IGuess {

	protected String mWord;
	protected float mScore;

	public String getWord() {
		return this.mWord;
	}

	public void setWord(final String pWord) {
		this.mWord = pWord;
	}

	public float getScore() {
		return this.mScore;
	}

	public void setScore(final float pScore) {
		this.mScore = pScore;
	}

}
