package com.fivecrowns.junit.core;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.fivecrowns.model.ComputerPlayerAI;
import com.fivecrowns.model.FiveCrownsController;
import com.fivecrowns.model.cards.Card;
import com.fivecrowns.model.cards.JokerCard;
import com.fivecrowns.model.cards.WildCard;
import com.fivecrowns.model.cardsets.NotACardSetException;
import com.fivecrowns.model.players.ComputerPlayer;

public class TestCompAIBook {

	public ComputerPlayer testPlayer = null;
	public List<Card> threeWildCardHand = null;
	private WildCard threeStar = null;
	private WildCard threeSpade = null;
	private Card eightDiamond = null;
	private JokerCard jokerCard = null;
	private JokerCard jokerCardTwo = null;
	
	@Before
	public void setUp() throws Exception {
		testPlayer = new ComputerPlayer("test", new FiveCrownsController());
		threeStar = new WildCard(new Card("star", 3));
		threeSpade = new WildCard(new Card("spade", 3));
		eightDiamond = new Card("diamond", 8);
		jokerCard = new JokerCard();
		jokerCardTwo = new JokerCard();
		threeWildCardHand = new ArrayList<Card>();
		threeWildCardHand.add(threeStar);
		threeWildCardHand.add(threeSpade);
		threeWildCardHand.add(eightDiamond);
		threeWildCardHand.add(jokerCard);
		
	}
	
	@After
	public void tearDown() throws Exception {
		testPlayer = null;
		threeWildCardHand = null;
		threeStar = null;
		threeSpade = null;
		eightDiamond = null;
		jokerCard = null;
		jokerCardTwo = null;
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testGetDiscardForThreeToFiveCards() {
		try {
			testPlayer.setHand(threeWildCardHand);
			ComputerPlayerAI testPlayerAI = new ComputerPlayerAI(testPlayer, 3);
			assertEquals("Result",eightDiamond,testPlayerAI.getDiscardedCard());
		} catch (NotACardSetException e) {
			e.printStackTrace();
		}
	}
	
}
