package com.fivecrowns.junit.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fivecrowns.model.cards.Card;
import com.fivecrowns.model.cards.JokerCard;
import com.fivecrowns.model.cards.WildCard;

public class TestWildCards {

	private WildCard threeDiamond = null;
	private JokerCard jokerCard1 = null;
	private WildCard jackClub = null;
	
	@Before
	public void setUp() throws Exception {
		threeDiamond = new WildCard(new Card("diamond", 3));
		jokerCard1 = new JokerCard();
		jackClub = new WildCard(new Card("club", Card.FaceCard.JACK.getValue()));
		
	}

	@After
	public void tearDown() throws Exception {
		threeDiamond = null;
		jokerCard1 = null;
		jackClub = null;
	}

	@Test
	public void testWildCardValue() {
		assertEquals("The wild card value is: ", 20, threeDiamond.getValue());
	}
	
	@Test
	public void testJokerCardValue() {
		assertEquals("The joker card value is: ", 50, jokerCard1.getValue());
	}
	
	@Test
	public void testWildFaceCardValue() {
		assertEquals("The wild face card value is: ", 20, jackClub.getValue());
	}
	
	@Test
	public void testFaceCardValue() {
		assertEquals("The face card value is: ", Card.FaceCard.JACK.getValue(), jackClub.getRegularCard().getValue());
	}

}
