package com.fivecrowns.model.cardsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

import com.fivecrowns.model.cards.Card;

public class Run implements Setable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1779748731224481744L;
	
	private List<Card> run;
	
	public Run(Card card0, Card card1, Card... cards) throws NotACardSetException {
		if (isA(card0, card1, cards)) {
			this.run = new ArrayList<Card>(3);
			this.run.add(card0);
			card0.setAvailable(false);
			this.run.add(card1);
			card1.setAvailable(false);
			for(Card otherCards : cards) {
				this.run.add(otherCards);
				otherCards.setAvailable(false);
			}
		}
		else throw new NotACardSetException("You cannot create a run with these cards!");
	}
	
	public Run(List<Card> run) {
		this.run = run;
	}
	
	public List<Card> getRun() {
		return run;
	}

	public void setRun(List<Card> run) {
		this.run = run;
	}

	public Run addToRun(Card card) throws NotACardSetException {
		List<Card> tempRun = getRun();
		List<Card> testRun = getRun();
		testRun.add(card);
		int comparisons = 0;	//compares for like suits and wild cards
		for (int i = 0; i < testRun.size() - 1; i++) {
			if (testRun.get(i).getSuit().equals(testRun.get(i + 1).getSuit()) || testRun.get(i).isWild() || testRun.get(i + 1).isWild()) {
				comparisons++;
			}
		}
		if (comparisons == testRun.size() - 1 && isOrdered(testRun)) {
			this.setRun(testRun);
			card.setAvailable(false);
			tempRun = null;
			testRun = null;
			return new Run(getRun());
		}
		else {
			this.setRun(tempRun);
			tempRun = null;
			testRun = null;
		}
		throw new NotACardSetException("You cannot add to this run with the " + card + " but prior run is maintained!");
	}
	
	public Run removeFromRun(Card card) {
		List<Card> testRun = getRun();
		if (testRun.remove(card)) {
			card.setAvailable(true);
			if (testRun.size() <= 2) {
				destroy();
				testRun = null;
				return null;
			}
			int comparisons = 0;	//compares for like suits and wild cards
			for (int i = 0; i < testRun.size() - 1; i++) {
				if (testRun.get(i).getSuit().equals(testRun.get(i + 1).getSuit()) || testRun.get(i).isWild() || testRun.get(i + 1).isWild()) {
					comparisons++;
				}
			}
			if (comparisons == testRun.size() - 1 && isOrdered(testRun)) {
				this.setRun(testRun);
				testRun = null;
				return new Run(getRun());
			}
			else {
				destroy();
				testRun = null;
			}
		}
		return null;
	}
	
	/**
	 * Shortcut for testing valid runs
	 * 
	 * @param testRun - run to be tested
	 * @return test result
	 */
	public static boolean isA(List<Card> testRun) {
		((ArrayList<Card>)testRun).trimToSize();
		if (testRun.size() <= 2) {
			return false;
		}
		else {
			int comparisons = 0;	//compares for like suits and wild cards
			for (int i = 0; i < testRun.size() - 1; i++) {
				if (testRun.get(i).getSuit().equals(testRun.get(i + 1).getSuit()) || testRun.get(i).isWild() || testRun.get(i + 1).isWild()) {
					comparisons++;
				}
			}
			if (comparisons != testRun.size() - 1) {
				return false;
			}
			if(isOrdered(testRun)) {
				//this.setRun(testRun);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void print() {
		for (Card card : getRun()) {
			System.out.println(card);
		}
	}
	
	@Override
	public boolean isA(Card card0, Card card1, Card... cards) {
		List<Card> testRun = new ArrayList<Card>();
		testRun.add(card0);
		testRun.add(card1);
		for (Card otherCards : cards) {
			testRun.add(otherCards);
		}
		((ArrayList<Card>)testRun).trimToSize();
		if (testRun.size() <= 2) {
			testRun = null;
			return false;
		}
		else {
			int comparisons = 0;	//compares for like suits and wild cards
			for (int i = 0; i < testRun.size() - 1; i++) {
				if (testRun.get(i).getSuit().equals(testRun.get(i + 1).getSuit()) || testRun.get(i).isWild() || testRun.get(i + 1).isWild()) {
					comparisons++;
				}
			}
			if (comparisons != testRun.size() - 1) {
				testRun = null;
				return false;
			}
			if(isOrdered(testRun)) {
				this.setRun(testRun);
				testRun = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * Implements the algorithm to determine whether a run of cards is in proper order
	 * 
	 * @param runCards - list of cards
	 * @return whether or not the list is in order
	 */
	private static boolean isOrdered(List<Card> runCards) {
		Collections.sort(runCards);
		int wildCards = 0;		//count number of wild cards in run
		for (int i = runCards.size() - 1; i >= 0; i--) {
			if (runCards.get(i).isWild()) {
				wildCards++;
			}
		}
		if (wildCards == 0) {
			int comparisons = 0;
			for (int i = 0; i < runCards.size() - 1; i++) {
				if (runCards.get(i + 1).getValue() - runCards.get(i).getValue() == 1) {
					comparisons++;
				}
			}
			return (comparisons == runCards.size() - 1) ? true : false;
		}
		else {
			for (int i = 0; i < runCards.size() - 1; i++) {
				int difference = runCards.get(i + 1).getValue() - runCards.get(i).getValue();
				if (runCards.get(i + 1).getValue() > 14 || runCards.get(i).getValue() > 14) {	//check the next card to see if it is wild
					break;
				}
				else {
					while (difference != 1) {
						wildCards--;
						difference--;
					}
					if (wildCards < 0) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public void destroy() {
		for (Card cards : getRun()) {
			cards.setAvailable(true);
		}
		setRun(null);
	}

}
