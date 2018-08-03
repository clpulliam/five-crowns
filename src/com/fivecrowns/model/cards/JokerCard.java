package com.fivecrowns.model.cards;

/**
 * This is a representation of a Joker
 * 
 * @author Calvin
 * @version 1.0
 */
public class JokerCard extends WildCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1512701067426831135L;
	
	private static final int JOKER_CARD_VALUE = 50;
	
	public JokerCard() {
		super();
		this.wild = true;
		this.available = true;
		this.value = JOKER_CARD_VALUE;
	}
	
	@Override
	public String toString() {
		return "Joker";
	}
	
}
