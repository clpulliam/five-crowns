package com.fivecrowns.model.cardsets;

import com.fivecrowns.model.cards.Card;

public interface Setable {
	
	public boolean isA(Card card0, Card card1, Card... cards);
	
	public void destroy();
	
	public void print();
}
