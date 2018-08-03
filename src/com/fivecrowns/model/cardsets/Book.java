package com.fivecrowns.model.cardsets;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import com.fivecrowns.model.cards.Card;

/**
 * Represents the book that can contain at least 3 cards
 * 
 * @author Calvin
 * @version 1.0
 */
public class Book implements Setable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9102468174922445401L;
	
	/**
	 * Represents a book 
	 */
	private List<Card> book;
	
	/**
	 * Constructor which validates a book
	 * 
	 * @param card0 - first card
	 * @param card1 - second card
	 * @param cards - cards thereafter
	 * @throws NotACardSetException - checks for invalid books
	 */
	public Book(Card card0, Card card1, Card... cards) throws NotACardSetException {
		if(isA(card0, card1, cards)) {
			this.book = new ArrayList<Card>(3);
			this.book.add(card0);
			card0.setAvailable(false);
			this.book.add(card1);
			card1.setAvailable(false);
			for (Card card : cards) {
				this.book.add(card);
				card.setAvailable(false);
			}
		}
		else throw new NotACardSetException("You cannot create a book with these cards!");
	}
	
	/**
	 * Alternative constructor which is used for creating books from other books
	 * 
	 * @param book - a hopefully valid book
	 */
	public Book(List<Card> book) {
		this.book = book;
	}
	
	/**
	 * Getter for the book
	 * 
	 * @return the instance variable for book
	 */
	public List<Card> getBook() {
		return book;
	}
	
	/**
	 * Prints cards in the book on console (used for testing)
	 */
	@Override
	public void print() {
		for (Card card : getBook()) {
			System.out.println(card);
		}
	}
	
	/**
	 * Setter for the book
	 * 
	 * @param book - used to set the book instance variable
	 */
	public void setBook(List<Card> book) {
		this.book = book;
	}

	/**
	 * Adds additional cards to book upon validation otherwise the previous book is returned
	 * 
	 * @param card - card to be added
	 * @return a valid book 
	 * @throws NotACardSetException - exception thrown if the card to be added does not make a new book
	 */
	public Book addToBook(Card card) throws NotACardSetException {
		List<Card> tempBook = getBook();		//maintains copy of original book prior to addition
		List<Card> testBook = getBook();
		testBook.add(card);
		int comparisons = 0;
		for (int i = 0; i < testBook.size() - 1; i++) {
			if (testBook.get(i).getValue() == testBook.get(i + 1).getValue() || testBook.get(i).isWild() || testBook.get(i + 1).isWild()) {
				comparisons++;
			}
		}
		if (comparisons == testBook.size() - 1) {
			this.setBook(testBook);
			card.setAvailable(false);
			tempBook = null;
			testBook = null;
			return new Book(getBook());
		}
		else {
			this.setBook(tempBook);
			testBook = null;
			tempBook = null;
		}
		throw new NotACardSetException("You cannot add to this book with the " + card + " but prior book is maintained!");
	}

	/**
	 * Shortcut for testing valid books
	 * 
	 * @param testBook - book to be tested
	 * @return test result
	 */
	public static boolean isA(List<Card> testBook) {
		((ArrayList<Card>)testBook).trimToSize();
		if (testBook.size() <= 2) {
			return false;
		}
		else {
			//focusing on number of comparisons
			int comparisons = 0;
			for (int i = 0; i < testBook.size() - 1; i++) {
				if (testBook.get(i).getValue() == testBook.get(i + 1).getValue() || testBook.get(i).isWild() || testBook.get(i + 1).isWild()) {
					comparisons++;
				}
			}
			if (comparisons == testBook.size() - 1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isA(Card card0, Card card1, Card... cards) {
		List<Card> testBook = new ArrayList<Card>();
		testBook.add(card0);
		testBook.add(card1);
		for (Card otherCards : cards) {
			testBook.add(otherCards);
		}
		((ArrayList<Card>)testBook).trimToSize();
		if (testBook.size() <= 2) {
			testBook = null;
			return false;
		}
		else {
			//focusing on number of comparisons
			int comparisons = 0;
			for (int i = 0; i < testBook.size() - 1; i++) {
				if (testBook.get(i).getValue() == testBook.get(i + 1).getValue() || testBook.get(i).isWild() || testBook.get(i + 1).isWild()) {
					comparisons++;
				}
			}
			if (comparisons == testBook.size() - 1) {
				testBook = null;
				return true;
			}
		}
		testBook = null;
		return false;
		
	}

	/**
	 * Removes a specified card from a book
	 * 
	 * @param card - card to be removed
	 * @return either a new book without the card or null denoting an invalid book
	 */
	public Book removeFromBook(Card card) {
		List<Card> testBook = getBook();
		if (testBook.remove(card)) {
			card.setAvailable(true);
			if (testBook.size() <= 2) {
				destroy();
				testBook = null;
				return null;
			}
			int comparisons = 0;
			for (int i = 0; i < testBook.size() - 1; i++) {
				if (testBook.get(i).getValue() == testBook.get(i + 1).getValue() || testBook.get(i).isWild() || testBook.get(i + 1).isWild()) {
					comparisons++;
				}
			}
			if (comparisons == testBook.size() - 1) {
				this.setBook(testBook);
				testBook = null;
				return new Book(getBook()) ;
			}
			else {
				destroy();
				testBook = null;
			}
		}
		return null;
	}

	@Override
	public void destroy() {
		for (Card cards : getBook()) {
			cards.setAvailable(true);
		}
		setBook(null);
	}

}
