package com.fivecrowns.model.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.fivecrowns.model.FiveCrownsController;
import com.fivecrowns.model.cards.Card;
import com.fivecrowns.model.cardsets.Setable;

public class ComputerPlayer implements Player, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -171685639758137478L;

	private int score;
	
	private String name;
	
	private List<Card> hand;
	
	private Setable[] container;
	
	private boolean turn;
	
	private Observable observable;
	
	/**
	 * Computer player constructor
	 * 
	 * @param name - player name
	 * @param observable - FiveCrownsController
	 * 
	 * @Note upon creation of the controller class, need to input orientation
	 */
	public ComputerPlayer(String name, Observable observable) {
		this.observable = observable;
		this.observable.addObserver(this);
		this.name = (name == null || name.equals("")) ? name = observable.toString() : name;
		this.score = 0;
		this.hand = new ArrayList<Card>(4);
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
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	@Override
	public Setable[] getContainer() {
		return container;
	}

	public void setContainer(Setable[] container) {
		this.container = container;
	}

	@Override
	public boolean isTurn() {
		return turn;
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}

}
