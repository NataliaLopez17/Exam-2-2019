import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CardTester {

	Card jokerDiamondCard;
	Card jokerClubsCard;
	Card AceHeartsCard;
	Card twoDiamondCard;
	Card twoDiamondCard2;
	Card fiveSpadesCard;
	Card nineHeartsCard;
	Card tenClubsCard;
	Card jHeartsCard;
	Card qHeartsCard;
	Card kSpadesCard;
	
	@Before
	public void setUp() {
		jokerDiamondCard = new Card(Card.Suit.DIAMONDS, Card.Rank.JOKER);
		jokerClubsCard = new Card(Card.Suit.CLUBS, Card.Rank.JOKER);
		AceHeartsCard = new Card(Card.Suit.HEARTS, Card.Rank.A);
		twoDiamondCard = new Card(Card.Suit.DIAMONDS, Card.Rank.TWO);
		twoDiamondCard2 = new Card(Card.Suit.DIAMONDS, Card.Rank.TWO);
		fiveSpadesCard = new Card(Card.Suit.SPADES, Card.Rank.FIVE);
		nineHeartsCard = new Card(Card.Suit.HEARTS, Card.Rank.NINE);
		tenClubsCard = new Card(Card.Suit.CLUBS, Card.Rank.TEN);
		jHeartsCard = new Card(Card.Suit.HEARTS, Card.Rank.J);
		qHeartsCard = new Card(Card.Suit.HEARTS, Card.Rank.Q);
		kSpadesCard = new Card(Card.Suit.SPADES, Card.Rank.K);
	}
	
	@Test
	public void cardInCommonTest() {
		// Decks
		Card[] emptyDeck = new Card[0];
		Card[] JokerD2D2D9H = { jokerDiamondCard, twoDiamondCard , twoDiamondCard2, nineHeartsCard };
		Card[] QH2D10C = { qHeartsCard, twoDiamondCard, tenClubsCard };
		Card[] KSJockerC5S = {kSpadesCard, jokerClubsCard, fiveSpadesCard };
		
		// Empty Arrays
		assertFalse("The first array is empty.", Card.cardInCommon(emptyDeck, JokerD2D2D9H));
		assertFalse("The second array is empty.", Card.cardInCommon(QH2D10C, emptyDeck));
		
		// Cards In Common
		assertTrue("The same array have cards in common.", Card.cardInCommon(QH2D10C, QH2D10C));
		assertTrue("They have the 2 of Diamonds in Common.", Card.cardInCommon(JokerD2D2D9H, QH2D10C));
		
		// No Cards In Common
		assertFalse("They have no cards in common.", Card.cardInCommon(JokerD2D2D9H, KSJockerC5S));
		assertFalse("They have no cards in common.", Card.cardInCommon(KSJockerC5S, JokerD2D2D9H));
		
	}
	
	@Test
	public void removeJokerTest() {
		Card[] emptyDeck = new Card[0];
		Card[] KSJockerC5S = { kSpadesCard, jokerClubsCard, fiveSpadesCard };
		Card[] TenCJockerC9HJokerD = { tenClubsCard, jokerClubsCard, nineHeartsCard, jokerDiamondCard };
		Card[] JockerDeck = {jokerClubsCard, jokerDiamondCard };
		Card[] JokerD2DJokerCJokerD = { jokerDiamondCard, twoDiamondCard, jokerClubsCard, jokerDiamondCard };
		
		// Empty Deck
		Card[] emptyDeckResult = Card.removeJokers(emptyDeck, 0);
		assertEquals("Result should be empty", 0, emptyDeckResult.length);
		
		// One Joker
		Card[] KSJockerC5SResult = Card.removeJokers(KSJockerC5S, 1);
		assertEquals("Result should have two items", 2, KSJockerC5SResult.length);
		assertEquals("Item 1 should be King of Spades", kSpadesCard, KSJockerC5SResult[0]);
		assertEquals("Item 2 should be Five of Spades", fiveSpadesCard, KSJockerC5SResult[1]);
		
		// Two Jokers
		Card[] TenJockerC9HJokerDResult = Card.removeJokers(TenCJockerC9HJokerD, 2);
		assertEquals("Result should have two items", 2, TenJockerC9HJokerDResult.length);
		assertEquals("Item 1 should be Ten of Clubs", tenClubsCard, TenJockerC9HJokerDResult[0]);
		assertEquals("Item 2 should be Nine of Hearts", nineHeartsCard, TenJockerC9HJokerDResult[1]);
		
		// Only Jokers
		Card[] JockerDeckResult = Card.removeJokers(JockerDeck, 2);
		assertEquals("Result should be empty", 0, JockerDeckResult.length);
		
		// Three Jokers
		Card[] JokerD2DJokerCJokerDResult = Card.removeJokers(JokerD2DJokerCJokerD, 3);
		assertEquals("Result should have one items", 1, JokerD2DJokerCJokerDResult.length);
		assertEquals("Item 1 should be Two of Diamonds", twoDiamondCard, JokerD2DJokerCJokerDResult[0]);
	}
	
	@Test
	public void findHighCardTest() {
		Card[] emptyDeck = new Card[0];
		Card[] oneCardDeck = { qHeartsCard };
		Card[] NineHKSJockerC5S = {nineHeartsCard, jokerClubsCard, kSpadesCard, fiveSpadesCard };
		Card[] JokerD2DJokerC2DJokerD = { jokerDiamondCard, twoDiamondCard, jokerClubsCard, twoDiamondCard2, jokerDiamondCard };
		
		assertTrue("There are no cards in the deck.", Card.findHighCard(emptyDeck)==null);
		assertEquals("It should return the only card.", qHeartsCard, Card.findHighCard(oneCardDeck));
		assertEquals("It should return the highest card.", kSpadesCard, Card.findHighCard(NineHKSJockerC5S));
		assertEquals("It should return the first high card.", twoDiamondCard, Card.findHighCard(JokerD2DJokerC2DJokerD));
	}
	
	@Test
	public void isInDeck() {
		Card[] emptyDeck = new Card[0];
		Card[] oneCardDeck = { qHeartsCard };
		Card[] NineHKSJockerC5S = {nineHeartsCard, jokerClubsCard, kSpadesCard, fiveSpadesCard };
		Card[] JokerD210CD2D9H = { jokerDiamondCard, twoDiamondCard, tenClubsCard, twoDiamondCard2, nineHeartsCard };
		
		assertFalse("The deck has no cards", qHeartsCard.isInDeck(emptyDeck));
		assertFalse("The deck has no Ten of Clubs", tenClubsCard.isInDeck(NineHKSJockerC5S));

		assertTrue("The deck has the Five of Spades", fiveSpadesCard.isInDeck(NineHKSJockerC5S));
		assertTrue("The deck has the Two of Diamonds", twoDiamondCard.isInDeck(JokerD210CD2D9H));

	}
	
	@Test
	public void cardValue() {
		
		assertEquals("Card is a Joker should return 0", jokerDiamondCard.cardValue(), 0);
		assertEquals("Card is an Ace should return 21", AceHeartsCard.cardValue(), 21); 
		assertEquals("Card is an Two should return 2", twoDiamondCard.cardValue(), 2); 
		assertEquals("Card is an Five should return 5", fiveSpadesCard.cardValue(), 5); 
		assertEquals("Card is an Ten should return 10", tenClubsCard.cardValue(), 10); 
		assertEquals("Card is an J should return 13", jHeartsCard.cardValue(), 13); 
		assertEquals("Card is an Q should return 14", qHeartsCard.cardValue(), 14); 
		assertEquals("Card is an k should return 15", kSpadesCard.cardValue(), 15); 
		
	}

}