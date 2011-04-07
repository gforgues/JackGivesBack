package cards;

import cards.Card;
import cards.Card.Rank;
import cards.Card.Suit;

/**
 * An immutable description of a playing card.
 */
public final class Card
{
	private static final int POINTS_ACE = 1;
	private static final int POINTS_TWO = 2;
	private static final int POINTS_THREE = 3;
	private static final int POINTS_FOUR = 4;
	private static final int POINTS_FIVE = 5;
	private static final int POINTS_SIX = 6;
	private static final int POINTS_SEVEN = 7;
	private static final int POINTS_EIGHT = 8;
	private static final int POINTS_NINE = 9;
	private static final int POINTS_TEN = 10;
	private static final int POINTS_JACK = 11;
	private static final int POINTS_QUEEN = 12;
	private static final int POINTS_KING = 13;
	
	/**
	 * Enum type representing the rank of the card.
	 */
	public enum Rank 
	{ TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}
	
	/**
	 * Enum type representing the suit of the card.
	 */
	public enum Suit 
	{ CLUBS, DIAMONDS, SPADES, HEARTS }
	
	private final Rank fRank;
	private final Suit fSuit;

	/**
	 * Create a new card object. 
	 * @param rank The rank of the card.
	 * @param suit The suit of the card.
	 */
	Card(Rank rank, Suit suit)
	{
		fRank = rank;
		fSuit = suit;
	}
	
	/**
	 * @return The number of points that a card is worth
	 * dependent on their corresponding Rank
	 */
	public int getPoints()
		{
			if(fRank==Rank.ACE)
			{
				return POINTS_ACE;
			}
			else if(fRank==Rank.TWO)
			{
				return POINTS_TWO;
			}			
			else if(fRank==Rank.THREE)
			{
				return POINTS_THREE;
			}
			else if(fRank==Rank.FOUR)
			{
				return POINTS_FOUR;
			}
			else if(fRank==Rank.FIVE)
			{
				return POINTS_FIVE;
			}
			else if(fRank==Rank.SIX)
			{
				return POINTS_SIX;
			}
			else if(fRank==Rank.SEVEN)
			{
				return POINTS_SEVEN;
			}
			else if(fRank==Rank.EIGHT)
			{
				return POINTS_EIGHT;
			}
			else if(fRank==Rank.NINE)
			{
				return POINTS_NINE;
			}
			else if(fRank==Rank.TEN)
			{
				return POINTS_TEN;
			}
			else if(fRank==Rank.JACK)
			{
				return POINTS_JACK;
			}
			else if(fRank==Rank.QUEEN)
			{
				return POINTS_QUEEN;
			}
			else if(fRank==Rank.KING)
			{
				return POINTS_KING;
			}
			return 0;
		}
	
	/**
	 * Obtain the rank of the card.
	 * @return An object representing the rank of the card.
	 */
	public Rank getRank()
	{
		return fRank;
	}
	
	/**
	 * @return A 2-character string representation of the hand.
	 */
	public String toCompactString()
	{
		String lReturn = "";
		if(fRank.ordinal()<=Rank.NINE.ordinal())
		{
			lReturn = new Integer(fRank.ordinal() + 2).toString();
		}
		else
		{
			lReturn = fRank.toString().substring(0, 1);
		}
		return lReturn + fSuit.toString().substring(0, 1);
	}
	
	/**
	 * Obtain the suit of the card.
	 * @return An object representing the suit of the card.
	 */
	public Suit getSuit()
	{
		return fSuit;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * @return See above.
	 */
	public String toString()
	{
		return fRank + " of " + fSuit;
	}
	
	/**
	 * Converts saved String rank and String suit to a Card object
	 * @param pRank The String representation of the rank
	 * @param pSuit The String representation of the suit
	 * @return A Card object from a given String rank and suit.
	 */
	public static Card fromString(String pRank, String pSuit)
	{
		Rank lRank = null;
		Suit lSuit = null;
		for (Suit suits : Suit.values()) {
			if (suits.toString().equals(pSuit)) {
				lSuit = suits;
				for (Rank ranks : Rank.values()) {
					if (ranks.toString().equals(pRank)) {
						lRank = ranks;
						break;
					}
				}
				break;
			}
		}
		return new Card(lRank,lSuit);
	}

	/** 
	 * The hashcode for a card is the suit*13 + that of the rank (perfect hash).
	 * @return the hashcode
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		return getSuit().ordinal() * Rank.values().length + getRank().ordinal();
	}
}