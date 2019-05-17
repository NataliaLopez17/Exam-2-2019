import java.util.ArrayList;

/**
 * Card Class
 */
public class Card {

	/**
	 * Enum types for the card variables
	 * - Suit
	 * - Rank
	 */
	enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
	enum Rank { JOKER, A, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHTH,
		NINE, TEN, J, Q, K }

	Suit suit;
	Rank rank;

	/**
	 * Main card class
	 * @param s
	 * @param r
	 */
	public Card(Suit s, Rank r) {
		suit = s;
		rank = r;
	}

	/**
	 * Checks if two cards are the same.
	 * @param arg0
	 */
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Card)) { return false; }
		Card c = (Card) arg0;
		return suit == c.suit && rank == c.rank;
	}

	/**
	 * Compares two cards.
	 * Returns == to 0 if both cards are equal.
	 * Returns > to 0 if the target object is bigger than the given card.
	 * Returns < to 0 if the target object is smaller than the given card.
	 */
	public int compareTo(Card o) {
		if (rank.compareTo(o.rank) == 0)
			return this.suit.compareTo(o.suit);
		else
			return this.rank.compareTo(o.rank);

	}

	/**
	 * Exercise 1
	 * Checks true if there are any cards in common between two decks.
	 * @param deck1
	 * @param deck2
	 * @return
	 */
	public static boolean cardInCommon(Card[] deck1, Card[] deck2) {
		for(int i = 0; i < deck1.length; i++) {
			for(int j = 0; j < deck2.length; j++) {
				if(deck1[i].equals(deck2[j])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Exercise 2
	 * Returns a new deck with all the Joker cards removed
	 * from the original deck given how many joker cards are
	 * in the given array.
	 * @param deck
	 * @param jokerCount
	 * @return
	 */
	public static Card[] removeJokers(Card[] deck, int jokerCount) {
		//Card[] neu = new Card[deck.length];
		ArrayList<Card> neue = new ArrayList<Card>();
		for(int i = 0; i < deck.length; i++) {
			if(deck[i].rank != Rank.JOKER) {
				neue.add(deck[i]);
			}
		}
		return neue.toArray(new Card[0]);
	}

	/**
	 * Exercise 3
	 * Returns the High Card (Card with the Highest Value)
	 * if available. (Ignore Poker Rules)
	 * Hint: Use the method CompareTo() Method.
	 * @param deck
	 * @return
	 */
	public static Card findHighCard(Card[] deck) {
		if(deck.length == 0) {
			return null;
		}
		Card neue = deck[0];
		for(int i = 0; i < deck.length; i++) {
			if(deck[i].compareTo(neue) > 1) {
				neue = deck[i];
			}
		}
		return neue;
	}

	/**
	 * Exercise 4
	 * Returns true only if the target object is present inside
	 * the given array.
	 * @param deck
	 * @return
	 */
	public boolean isInDeck(Card[] deck) {
		for(int i = 0; i < deck.length; i++) {
			if(this == deck[i]) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Exercise 5
	 * Returns the value of a card based on the rank.
	 * 
	 * Cards with rank Two to Ten have the same value as its name.
	 * Rank Joker is zero.
	 * Rank J, Q, K is 13, 14 and 15 respectively.
	 * Rank A is 21.
	 * 
	 * Hint: The enum types that is called .ordinal() which returns the 
	 * value assigned based on its position in the enum type list. 
	 * @return
	 */
	public int cardValue() {
		if(this.rank.ordinal() >= 2 && this.rank.ordinal() <= 10) return this.rank.ordinal();
		if(this.rank == Rank.JOKER) return 0;
		if(this.rank == Rank.J) return 13;
		if(this.rank == Rank.Q) return 14;
		if(this.rank == Rank.K) return 15;
		return 21;
	}
}