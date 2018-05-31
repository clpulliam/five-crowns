package com.fivecrowns.model.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.fivecrowns.model.FiveCrownsController;
import com.fivecrowns.model.cards.Card;
import com.fivecrowns.model.cardsets.Setable;

public class HumanPlayer implements Player, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6696449270233599981L;

	private int score;
	
	private String name;
	
	private List<Card> hand;
	
	private Setable[] container;
	
	private boolean turn;
	
	private Observable observable;
	
	/**
	 * Human player constructor
	 * 
	 * @param name - player name
	 * @param observable - FiveCrownsController
	 * 
	 * Note: upon creation of the controller class, need to input orientation
	 */
	public HumanPlayer(String name, Observable observable) {
		this.observable = observable;
		this.observable.addObserver(this);
		this.name = (name == null || name.equals("")) ? name = observable.toString() : name;
		this.score = 0;
		this.hand = new ArrayList<Card>(4); //four cards are used initially to make room for the extra card before discarding
		this.container = new Setable[4];
		this.turn = false;
	}
	
	@Override
	public void update(Observable obs, Object arg1) {
		if (obs instanceof FiveCrownsController) {
			FiveCrownsController fcController = (FiveCrownsController) obs; //possibly set update the turn variable for observer
			if (arg1 instanceof Boolean) {
				setTurn((Boolean)arg1);
			}
		}
	}

	@Override
	public List<Card> getHand() {
		return hand;
	}
	
	@Override
	public Setable[] getContainer() {
		return container;
	}
	
	@Override
	public int getScore() {
		return score;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	/**
	 * Enables a swap between two cards in a hand
	 * @param firstCard - the first card to be swapped
	 * @param secondCard - the second card to be swapped
	 */
	public void swap(Card firstCard, Card secondCard) {
		int firstCardIndex = getHand().indexOf(firstCard);
		int secondCardIndex = getHand().indexOf(secondCard);
		Card temp = getHand().get(firstCardIndex);
		getHand().set(firstCardIndex, secondCard);
		getHand().set(secondCardIndex, temp);
	}

}
