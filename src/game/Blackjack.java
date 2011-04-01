package game;

import java.util.ArrayList;
import java.util.HashMap;

import cards.*;
import cards.Card.Rank;
import participant.Player;
import cards.Hand;
import storage.Storage;


	/**
	 *
	 * @author JackGivesBack
	 */
	public class Blackjack implements Game {
		/**
		 *
		 * @param
		 * @return
		 */

	    
	    /**
	     * Deal exactly 2 cards for the first initial deal to a player
	     */
	    private final int INITIAL_DEAL_VALUE = 2;
	    /**
	     * Number of cards left to trigger new deck
	     */
	    private final int DECK_RESET_VALUE = 104;
		/**
		 * Field to store chip count
		 */
	    public int chipCount;
	    /**
		 * Initialize new deck
		 */
		private Deck gameDeck;
		/**
		 * Field to store current set of players
		 */
		private HashMap<Player,BlackjackHand> playerHand;
		//private HashMap<String,Player> playerList;
		/**
		 * Field to store winner
		 */
		private Player winner;
		/**
		 * Field to store the hand
		 */
		//private BlackjackHand hand;
		
		/**
		 * Blackjack game initialization
		 */
		public Blackjack() {
			gameDeck = new Deck();
			gameDeck.shuffle();
			winner = null;
			playerHand = new HashMap<Player,BlackjackHand>();
			//playerList = new HashMap<String,Player>();
			chipCount = 0;
			
		}

		/**
		 * Reset player's hand state
		 * @param
		 */
		public void resetHandState(Player player) {
			//if player doesn't exist will this drop an exception?
			playerHand.remove(player);
			//player.getHand = new ArrayList<Card>();
			//player.done = false;
		}
		
		/**
		 *
		 * @param
		 * @return
		 */
		public void checkDeck() {
			if ( gameDeck.size() < DECK_RESET_VALUE ) {
				gameDeck = new Deck();
				gameDeck.shuffle();
			}
		}
		
		/**
		 *
		 * @param
		 * @return
		 */
	    public void deal(Player player) {
	    	/*
	    	 * Clear player's hand first
	    	 */
	    	resetHandState(player);
	    	/*
	    	 * Check deck status
	    	 */
	    	checkDeck();
	    	/*
	    	 * Deal exactly two cards for the first initial deal
	    	 */
	        BlackjackHand hand = playerHand.get(player);
	    	for ( int i = 0 ; i < INITIAL_DEAL_VALUE ; i++ ){
	    		//player.getHand.add(gameDeck.draw());
	    		hand.addCard(gameDeck.draw());
	    		playerHand.put(player,hand);
	        }

	    }
	    
		/**
		 *
		 * @param
		 * @return
		 */
	    public void hit(Player player) {
	    	/*
	    	 * Check deck status
	    	 */
	    	checkDeck();
	    	/*
	    	 * Check if player's hand state is done or not
	    	 */
	    	BlackjackHand hand = playerHand.get(player);
	    	//if ( player.done == false )
	    		hand.addCard(gameDeck.draw());
	    		playerHand.put(player, hand);
	    }
	    
		/**
		 *
		 * @param
		 * @return
		 */
	    public ArrayList<Card>  stand(Player player) {
	    	/*
	    	 * Set player's hand state to be done
	    	 */
	    	
	    	return player.getHand();
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public boolean sufficientChips(Player player, int amount) {
	    	if ( player.getChip() < amount ) {
	    		return false;
	    	}
	    	return true;
	    }
	    
		/**
		 *
		 * @param
		 * @return
		 */
	    public void doubleDown(Player player) {
	    	/*
	    	 * Check deck status
	    	 */
	    	checkDeck();
	    	
	    	if (sufficientChips(player, chipCount)) {
	    		player.setChips(player.getChips() - chipCount);
	    		chipCount += chipCount;
	    		hit(player);
	    		stand(player);
	    	}

	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public void split(Player player) {
	    	/*
	    	 * Check deck status
	    	 */
	    	checkDeck();
	    	
	    	BlackjackHand hand = playerHand.get(player);
	    	
	    	Card firstCard = hand.removeCard(0);
	    	Card secondCard = hand.removeCard(1);
	    	Hand newHand = new Hand();
	    	hand.clearHand();
	    	hand.addCard(firstCard);
	    	newHand.addCard(secondCard);
	    	
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public void bet(Player player, int amount) {
	    	if ( sufficientChips(player,amount) ) {
	    		chipCount += amount;
	    		player.getChips() -= amount;
	    	}
	    }
	    
		/**
		 *
		 * @param
		 * @return
		 */
	    public int getPoints(Player player) {
	    	BlackjackHand hand = playerHand.get(player);
	    	return hand.getBlackjackValue();
//	    	int points = 0;
//	    	/*
//	    	 * Gather all points from each card in player's hand
//	    	 */
//	    		for ( Card c : player.hand )
//	    			points += c.getPoints();
//	    		
//	    	return points;
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public boolean checkBust(Player player) {
	    	BlackjackHand hand = playerHand.get(player);
	    	return hand.isBust();
//	        if ( getPoints(player) > 21 )
//	        	return true;
//	        else 
//	        	return false;
	        
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public boolean checkBlackjack(Player player) {
	    	BlackjackHand hand = playerHand.get(player);
	    	/*
	    	 * Check handsize exactly 2
	    	 */
	    	if ( hand.getNumberCards() == 2 ) {
	    		/*
	    		 * Check if 1 ace present
	    		 */
	    		if ( hand.getNumberCardsOfRank(Rank.ACE) == 1 ) {
	    			/*
	    			 * Check if 1 ten,jack,queen,king present
	    			 */
	    			if ( hand.getNumberCardsOfRank(Rank.TEN) == 1 || hand.getNumberCardsOfRank(Rank.JACK) == 1 ||
	    					hand.getNumberCardsOfRank(Rank.QUEEN) == 1 || hand.getNumberCardsOfRank(Rank.KING) == 1 )
	    			return true;
	    		}
	    	} 
	    	return false;
	    }
		
	}
