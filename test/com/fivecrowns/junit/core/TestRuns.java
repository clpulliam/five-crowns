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
import com.fivecrowns.model.cardsets.Setable;
import com.fivecrowns.model.cardsets.NotACardSetException;
import com.fivecrowns.model.cardsets.Run;

public class TestRuns {

	private Card fiveStar = null;
	private Card sixStar = null;
	private Card sevenStar = null;
	private Card eightStar = null;
	private Card nineStar = null;
	private Card threeSpade = null;
	private Card eightSpade = null;
	private Card tenSpade = null;
	private Card jackSpade = null;
	private Card queenSpade = null;
	private Card kingSpade = null;
	private WildCard jackStar = null;
	private JokerCard jokerCard = null;
	
	@Before
	public void setUp() throws Exception {
		fiveStar = new Card("star", 5);
		sixStar = new Card("star", 6);
		sevenStar = new Card("star", 7);
		eightStar = new Card("star", 8);
		nineStar = new Card("star", 9);
		threeSpade = new Card("spade", 3);
		eightSpade = new Card("spade", 8);
		tenSpade = new Card("spade", 10);
		jackSpade = new Card("spade", Card.FaceCard.JACK.getValue());
		queenSpade = new Card("spade", Card.FaceCard.QUEEN.getValue());
		kingSpade = new Card("spade", Card.FaceCard.KING.getValue());
		jackStar = new WildCard(new Card("star", Card.FaceCard.JACK.getValue()));
		jokerCard = new JokerCard();
	}

	@After
	public void tearDown() throws Exception {
		fiveStar = null;
		sixStar = null;
		sevenStar = null;
		eightStar = null;
		nineStar = null;
		threeSpade = null;
		eightSpade = null;
		tenSpade = null;
		jackStar = null;
		jackSpade = null;
		queenSpade = null;
		kingSpade = null;
		jokerCard = null;
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void throwsNotACardSetExceptionForTwoCards() throws NotACardSetException {
		exception.expect(NotACardSetException.class);
		exception.expectMessage("You cannot create a run with these cards!");
		@SuppressWarnings("unused")
		Setable testRun = new Run(fiveStar, sixStar);
	}
	
	@Test
	public void throwsNotACardSetExceptionForInvalidRun() throws NotACardSetException {
		exception.expect(NotACardSetException.class);
		exception.expectMessage("You cannot create a run with these cards!");
		@SuppressWarnings("unused")
		Setable testRun = new Run(sixStar, sevenStar, eightSpade);
	}
	
	@Test
	public void testThreeCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, sevenStar);
			assertTrue("This is a three card run", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidPostAddToThreeCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, sevenStar);
			Setable newRun = ((Run)testRun).addToRun(eightStar);
			assertTrue("This is a valid four card run", Run.isA(((Run)newRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidPreAddToThreeCardRun() {
		try {
			Setable testRun = new Run(sixStar, sevenStar, eightStar).addToRun(fiveStar);
			assertTrue("This is a valid four card run", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidPostWildCardAddToThreeCardRun() {
		try {
			Setable testRun = new Run(sixStar, sevenStar, eightStar).addToRun(jackStar);
			assertTrue("This is a valid four card run with wild card", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPostWildThreeCardRun() {
		try {
			Setable testRun = new Run(sevenStar, eightStar, jokerCard);
			assertTrue("This is a valid three card run with joker card", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMidWildThreeCardRun() {
		try {
			Setable testRun = new Run(fiveStar, jackStar, sevenStar);
			assertTrue("This is a valid three card run with central wild card", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPreWildThreeCardRun() {
		try {
			Setable testRun = new Run(jackStar, sixStar, sevenStar);
			assertTrue("This is a valid three card run with beginning wild card", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTwoMidWildFourCardRun() {
		try {
			Setable testRun = new Run(fiveStar, jackStar, jokerCard, eightStar);
			assertTrue("This is a valid four card run with two central wild cards", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTwoWildRandomFiveCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, jackStar, eightStar, jokerCard);
			assertTrue("This is a valid five card run with one central and one end wild cards", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTwoMidWildFiveCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, jackStar, jokerCard, nineStar);
			assertTrue("This is a valid five card run with two central wild cards", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInvalidCardToThreeCardRun() throws NotACardSetException {
		exception.expect(NotACardSetException.class);
		exception.expectMessage("You cannot add to this run with the " + eightSpade.toString() + " but prior run is maintained!");
		Setable testRun = new Run(fiveStar, sixStar, sevenStar);
		assertNotNull("This is not a four card run", ((Run)testRun).addToRun(eightSpade));
	}
	
	@Test
	public void testValidRemovalFromFourCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, sevenStar, eightStar);
			assertNotNull("This is a valid removal from a four card run to a run with three cards", ((Run)testRun).removeFromRun(eightStar));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidRemovalFromFourCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, sevenStar, eightStar);
			assertNull("This is an invalid removal from a four card run to a run with three cards", ((Run)testRun).removeFromRun(sevenStar));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidRemovalFromThreeCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, sevenStar);
			assertNull("This is an invalid removal from a three card run", ((Run)testRun).removeFromRun(sevenStar));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidRemovalFromFiveCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, jackStar, eightStar, jokerCard);
			assertNotNull("This is a valid five card run with one central and one end wild cards with end card being removed", ((Run)testRun).removeFromRun(jokerCard));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAnotherValidRemovalFromFiveCardRun() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, jackStar, eightStar, jokerCard);
			assertNotNull("This is a valid five card run with one central and one end wild cards with central card being removed", ((Run)testRun).removeFromRun(jackStar));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCardsInRunCreatedOutOfOrder() {
		try {
			Setable testRun = new Run(eightStar, fiveStar, sevenStar, jokerCard, nineStar);
			assertTrue("This is a valid five card run despite it being out of order", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiveCardRunWithWildAndFaceCards() {
		try {
			Setable testRun = new Run(eightSpade, jokerCard, tenSpade, jackSpade, queenSpade);
			assertTrue("This is a valid five card run with wild card and face cards", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFourCardRunEndBoundaryCondition() {
		try {
			Setable testRun = new Run(jackSpade, queenSpade, kingSpade, jokerCard);
			assertTrue("This is a valid four card run with a joker card", Run.isA(((Run)testRun).getRun()));
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidThreeCardRunEndBoundaryCondition() throws NotACardSetException {
		exception.expect(NotACardSetException.class);
		exception.expectMessage("You cannot create a run with these cards!");
		Setable testRun = new Run(queenSpade, kingSpade, threeSpade);
		assertFalse("This is an invalid four card run with \"loop\" around", Run.isA(((Run)testRun).getRun()));
	}
	
	@Test
	public void testDestroyMethod() {
		try {
			Setable testRun = new Run(fiveStar, sixStar, sevenStar);
			System.out.println("Cards in Destory Method: ");
			((Run)testRun).printRun();
			((Run)testRun).destroy();
			assertNull("This is now an empty run", ((Run)testRun).getRun());
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
		
	}
}
