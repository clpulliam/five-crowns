package com.fivecrowns.model.cards;

/**
 * This is a representation of a WildCard
 * 
 * @author Calvin
 * @version 1.0
 */
public class WildCard extends Card {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7605405028829851203L;

	/**
	 * Placeholder for the original card prior to being wild
	 */
	private Card regularCard;
	
	/**
	 * Value of the original card
	 */
	private int regularCardValue;
	
	private static final int WILD_CARD_VALUE = 20;
	
	public WildCard() {
		super();
	}
	
	public WildCard(Card card) {
		super();
		this.regularCard = card;
		this.regularCardValue = card.getValue();
		this.wild = true;
		regularCard.setWild(true);
		this.available = true;
		regularCard.setAvailable(true);
		this.value = WILD_CARD_VALUE;
	}

	public Card getRegularCard() {
		return regularCard;
	}

	public void setRegularCard(Card regularCard) {
		this.regularCard = regularCard;
	}

	public void setWildCardAvailable(boolean wild) {
		this.available = wild;
		this.regularCard.setAvailable(wild);
	}
	
	public boolean isCardWildCard(Card c) {
		return regularCardValue == c.getValue();
	}

	public int getRegularCardValue() {
		return regularCardValue;
	}
	
	public void unsetWild(Card c) {
		this.regularCard = null;
		this.regularCardValue = 0;
		c.setWild(false);
		this.available = true;	
	}
}
