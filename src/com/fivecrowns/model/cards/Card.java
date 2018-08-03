package com.fivecrowns.model.cards;

import java.util.Comparator;
import java.io.Serializable;

/**
 * This is the representation of a basic card for the Five Crowns Game. 
 * Need to eventually add graphics such as a card front and back
 * 
 * @author Calvin
 * @version 1.0
 */
public class Card implements Comparable<Card>, Comparator<Card>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3019037196835786174L;

	private String suit;
	
	protected int value;
	
	/**
	 * Indicator if a particular card value is wild
	 */
	protected boolean wild;
	
	/**
	 * Indicator if card is within a card set
	 */
	protected boolean available;
	
	public Card() {
		suit = "";
		value = 0;
	}
	
	public Card(String suit, int value) {
		this.suit = suit;
		this.value = value;
		this.wild = false;
		this.available = true;
	}
	
	public enum FaceCard {
		JACK(11), QUEEN(12), KING(13);
		private int value;
		
		FaceCard(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public FaceCard getByValue(int value) {
			for(FaceCard fc: FaceCard.values()) {
				if(fc.getValue() == value)
					return fc;
			}
			return null;
		}
	}
	
	public boolean isFaceCard() {
		return (value > 10 || value <= 13);
	}
	
	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isWild() {
		return wild;
	}

	public void setWild(boolean wild) {
		this.wild = wild;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return this.value + " " + this.suit;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Card && this.value == ((Card)obj).getValue() && this.suit.equals(((Card)obj).getSuit());
	}
	
	@Override
	public int compare(Card arg0, Card arg1) {
		return arg0.compareTo(arg1);
	}

	@Override
	public int compareTo(Card that) {
		return (this.value < that.value) ? - 1 : 
					(this.value == that.value) ? 0 : 1;
	}

}
