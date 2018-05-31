package com.fivecrowns.model.players;

import java.util.List;
import java.util.Observer;

import com.fivecrowns.model.cards.Card;
import com.fivecrowns.model.cardsets.Setable;

public interface Player extends Observer {
	
	public int getScore();
	
	public String getName();
	
	public List<Card> getHand();
	
	public Setable[] getContainer();
	
	public boolean isTurn();
	
}
