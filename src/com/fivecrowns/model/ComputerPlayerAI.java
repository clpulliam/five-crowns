package com.fivecrowns.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fivecrowns.model.cards.Card;
import com.fivecrowns.model.cardsets.Book;
import com.fivecrowns.model.cardsets.NotACardSetException;
import com.fivecrowns.model.cardsets.Run;
import com.fivecrowns.model.cardsets.Setable;
import com.fivecrowns.model.players.ComputerPlayer;

public class ComputerPlayerAI {

	private ComputerPlayer player;
	
	private Card discardedCard;
	
	private int numberOfCards;
	
	public ComputerPlayerAI(ComputerPlayer player, int numberOfCards) throws NotACardSetException {
		this.setPlayer(player);
		this.numberOfCards = numberOfCards;
		switch(numberOfCards) {
			case 3:
			case 4:
			case 5: getDiscardForThreeToFiveCards(player.getHand());
				break;
			case 6:
			case 7:
			case 8: getDiscardForSixToEightCards(player.getHand());
				break;
			case 9:
			case 10:
			case 11:
			case 12:
			case 13: getDiscardForNineToThirteenCards(player.getHand());
				break;
		}
	}
	
	public ComputerPlayer getPlayer() {
		return player;
	}

	public void setPlayer(ComputerPlayer player) {
		this.player = player;
	}
	
	public Card getDiscardedCard() {
		return discardedCard;
	}
	
	public void setDiscardedCard(Card discardedCard) {
		discardedCard.setAvailable(true);
		this.discardedCard = discardedCard;
	}

	public int getNumberOfCards() {
		return numberOfCards;
	}
	
	//may be refactored public into util class
	private static int countWildCards(List<Card> hand) {
		int numberOfWildCards = 0;
		for (Card c: hand) {
			if (c.isWild()) { numberOfWildCards++; }
		}
		return numberOfWildCards;
	}
	
	//3 - 5 cards
	private void getDiscardForThreeToFiveCards (List<Card> hand) throws NotACardSetException {
		int numberOfWildCards = countWildCards(hand);
		System.out.println("Number Of Wild Cards: " + numberOfWildCards);
		Collections.sort(hand);
		System.out.println("Hand: " + hand.toString());
		//all wild cards
		if (numberOfWildCards == hand.size() - 1) {
			List<Card> book = new ArrayList<Card>();
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).isWild()) {
					hand.get(i).setAvailable(false);
					book.add(hand.get(i));
				}
				else {
					setDiscardedCard(hand.get(i)); //if there is only 1 non-wild card especially for 3 cards
				}
			}
			Setable compBook = new Book(book);
			player.getContainer()[0] = compBook;
			if (discardedCard == null) {
				setDiscardedCard(hand.get(hand.size())); //simply returns the last card in the sorted set
			}
		}
		//mostly wild cards (3 cards)
		if (numberOfCards == 3 && numberOfWildCards == 2) {
			Card largestCard = null;
			List<Card> book = new ArrayList<Card>();
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).isWild()) {
					hand.get(i).setAvailable(false);
					book.add(hand.get(i));
				}
				else {
					//find the discard card
					if (largestCard == null) {
						largestCard = hand.get(i);
					} else {
						if (hand.get(i).getValue() > largestCard.getValue()) {
							book.add(largestCard);
							largestCard = hand.get(i);
						} else {
							book.add(hand.get(i));
						}
					}
				}
			}
			setDiscardedCard(largestCard);
			Setable compBook = new Book(book);
			player.getContainer()[0] = compBook;
		}
		//instant book or run (no wilds)
		if (numberOfWildCards == 0) {
			if (Book.isA(hand)) {
				setDiscardedCard(hand.get(hand.size() - 1));
				Setable compBook = new Book(hand);
				player.getContainer()[0] = compBook;
			}
			if (Run.isA(hand)) {
				setDiscardedCard(hand.get(hand.size() - 1));				
				Setable compRun = new Run(hand);
				player.getContainer()[0] = compRun;
			}
		}
		if (numberOfCards == 4 && numberOfWildCards == 2) {
			Card largestCard = null;
			//partial book + wild card(s) (4 cards)
			boolean matchOne = hand.get(0).getValue() == hand.get(1).getValue();
			boolean matchTwo = hand.get(1).getValue() == hand.get(2).getValue();
			if (matchOne || matchTwo) {
				List<Card> book = new ArrayList<Card>();
				if (matchOne) {
					hand.get(0).setAvailable(false);
					book.add(hand.get(0));
					hand.get(1).setAvailable(false);
					book.add(hand.get(1));
				}
				if (matchTwo) {
					hand.get(1).setAvailable(false);
					book.add(hand.get(1));
					hand.get(2).setAvailable(false);
					book.add(hand.get(2));
				}
				for (int i = 0; i < hand.size(); i++) {
					if (hand.get(i).isWild()) {
						hand.get(i).setAvailable(false);
						book.add(hand.get(i));
					}
					else {
						//find the discard card
						if (hand.get(i).isAvailable()) {
							largestCard = hand.get(i);
						}
					}
				}
				if (largestCard == null) {
					largestCard = hand.get(4);
				}
				setDiscardedCard(largestCard);
				Setable compBook = new Book(book);
				player.getContainer()[0] = compBook;	
			}
			//partial run + wild card(s) (4 cards)
		}
		
		//book + wild cards(s) 4-5 cards
		
		//run + wild cards(s) 4-5 cards
		
		//everything else
		
		
	}
	
	//6 - 8 cards
	private void getDiscardForSixToEightCards (List<Card> hand) {
		
	
	}
	
	//9 - 13 cards
	private void getDiscardForNineToThirteenCards (List<Card> hand) {
		
	
	}
}
