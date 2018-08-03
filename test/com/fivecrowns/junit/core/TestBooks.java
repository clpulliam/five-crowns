package com.fivecrowns.junit.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fivecrowns.model.cards.Card;
import com.fivecrowns.model.cards.JokerCard;
import com.fivecrowns.model.cards.WildCard;
import com.fivecrowns.model.cardsets.Book;
import com.fivecrowns.model.cardsets.Setable;
import com.fivecrowns.model.cardsets.NotACardSetException;

public class TestBooks {

	private Card eightClub  = null;
	private Card eightDiamond = null;
	private Card eightStar = null;
	private Card eightSpade = null;
	private Card eightHeart = null;
	private Card tenDiamond = null;
	private WildCard jackStar = null;
	private JokerCard jokerCard = null;
	
	@Before
	public void setUp() throws Exception {
		eightClub = new Card("club", 8);
		eightDiamond = new Card("diamond", 8);
		eightStar = new Card("star", 8);
		eightSpade = new Card("spade", 8);
		eightHeart = new Card("heart", 8);
		tenDiamond = new Card("diamond", 10);
		jackStar = new WildCard(new Card("star", Card.FaceCard.JACK.getValue()));
		jokerCard = new JokerCard();
	}

	@After
	public void tearDown() throws Exception {
		eightClub = null;
		eightDiamond = null;
		eightStar = null;
		eightSpade = null;
		eightHeart = null;
		tenDiamond = null;
		jackStar = null;
		jokerCard = null;
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void throwsNotACardSetExceptionForTwoCards() throws NotACardSetException {
		exception.expect(NotACardSetException.class);
		exception.expectMessage("You cannot create a book with these cards!");
		@SuppressWarnings("unused")
		Setable testBook = new Book(eightClub, eightDiamond);
	}
	
	@Test
	public void testThreeCardBook() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, eightStar);
			assertTrue("This is a three card book", Book.isA(((Book)testBook).getBook()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void throwsNotACardSetExceptionForInvalidBook() throws NotACardSetException {
		exception.expect(NotACardSetException.class);
		exception.expectMessage("You cannot create a book with these cards!");
		@SuppressWarnings("unused")
		Setable testBook = new Book(eightClub, eightDiamond, tenDiamond);
	}
	
	@Test
	public void testAddToThreeCardBook() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, eightStar);
			Setable newBook = ((Book)testBook).addToBook(eightHeart);
			assertTrue("This is now a four card book", Book.isA(((Book)newBook).getBook()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddWildToThreeCardBook() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, eightStar).addToBook(jokerCard);
			assertTrue("This is now a four card book with wild card", Book.isA(((Book)testBook).getBook()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMidWildToThreeCardBook() {
		try {
			Setable testBook = new Book(eightClub, jokerCard, eightDiamond);
			assertTrue("This is a valid three card book with wild card in middle upon creation", Book.isA(((Book)testBook).getBook()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetBookMethod() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, eightStar);
			assertArrayEquals("Checking get book method", new Card[] {eightClub, eightDiamond, eightStar}, ((Book)testBook).getBook().toArray());
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInvalidCardToThreeCardBook() throws NotACardSetException {
		exception.expect(NotACardSetException.class);
		exception.expectMessage("You cannot add to this book with the " + tenDiamond.toString() + " but prior book is maintained!");
		Setable testBook = new Book(eightClub, eightDiamond, eightStar);
		assertNotNull("This is not a four card book", ((Book)testBook).addToBook(tenDiamond));	
	}
	
	@Test 
	public void testFourCardBook() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, eightStar, eightSpade);
			assertTrue("This is a four card book", Book.isA(((Book)testBook).getBook()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiveCardBookWithWildFaceCard() {
		try {
			Setable testBook = new Book(eightClub, jackStar, eightDiamond, eightStar, eightSpade);
			assertTrue("This is a five card book with 1 wild card", Book.isA(((Book)testBook).getBook()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiveCardBookWithTwoWildCards() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, jackStar, jokerCard, eightSpade);
			assertTrue("This is a five card book with 2 adjacent wild cards", Book.isA(((Book)testBook).getBook()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidRemovalFromFourCardBook() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, eightStar, eightSpade);
			assertNotNull("This is a three card book", ((Book)testBook).removeFromBook(eightSpade));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidRemovalFromThreeCardBook() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, eightStar);
			assertNull("This is an invalid book", ((Book)testBook).removeFromBook(eightStar));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDestroyMethod() {
		try {
			Setable testBook = new Book(eightClub, eightDiamond, eightStar);
			System.out.println("Cards in Destory Method: ");
			((Book)testBook).print();
			((Book)testBook).destroy();
			assertNull("This is now an empty book", ((Book)testBook).getBook());
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
}
