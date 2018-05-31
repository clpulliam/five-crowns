package com.fivecrowns.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;
import java.io.Serializable;

import com.fivecrowns.model.cards.Card;
/**
 * Generates cards, players, and facilitates player turns in a Five Crowns game
 * 
 * @author Calvin
 *
 * @Note The controller will know what to name each individual player upon registration
 */
public class FiveCrownsController extends Observable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6805634550766580741L;
	
	private final static int MAX_PLAYERS = 4;		//will be adjusted to 8 in a future version
	private final static int NUMBER_OF_CARDS = 116;
	
	private List<Card> deck = new ArrayList<Card>(NUMBER_OF_CARDS);
	
	private List<Card> draw = new Stack<Card>();
	private List<Card> discard = new Stack<Card>();
	
	private int numberOfPlayers; 
	
	//initialize all cards and shuffle deck
	private void setupDeck() {
		
	}
	
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	public List<Card> getDeck() {
		return this.deck;
	}
}
