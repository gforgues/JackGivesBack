package game;

import java.util.ArrayList;
import java.util.HashMap;

import cards.*;
import cards.Card.Rank;

	/**
	 *
	 * @author JackGivesBack
	 */
	public class Blackjack implements Game {

		/**
		 * Blackjack game initialization
		 */
		public Blackjack() {
			gameDeck = new Deck();
			gameDeck.shuffle();
			winner = null;
			playerList = new HashMap<String,Player>();
			chipCount = 0;
			
		}

		/**
		 * Reset player's hand state
		 * @param
		 */
		public void resetHandState(Player player) {
			player.getHand = new ArrayList<Card>();
			player.done = false;
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
	        for ( int i = 0 ; i < INITIAL_DEAL_VALUE ; i++ ){
	        	player.getHand.add(gameDeck.draw());
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
	    	if ( player.done == false )
	    		player.getHand.add(gameDeck.draw());
	    }
	    
		/**
		 *
		 * @param
		 * @return
		 */
	    public void stand(Player player) {
	    	/*
	    	 * Set player's hand state to be done
	    	 */
	    	player.done = true;
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public boolean sufficientChips(Player player, int amount) {
	    	if ( player.chips < amount ) {
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
	    		player.chips -= chipCount;
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
	    public void split() {
	    	/*
	    	 * Check deck status
	    	 */
	    	checkDeck();
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public void dealerDoes() {

	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public void bet(Player player, int amount) {
	    	if ( sufficientChips(player,amount) ) {
	    		chipCount += amount;
	    		player.chips -= amount;
	    	}
	    }
	    
		/**
		 *
		 * @param
		 * @return
		 */
	    public int getPoints(Player player) {
	    	int points = 0;
	    	/*
	    	 * Gather all points from each card in player's hand
	    	 */
	    		for ( Card c : player.hand )
	    			points += c.getPoints();
	    		
	    	return points;
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public boolean checkBust(Player player) {
	        if ( getPoints(player) > 21 )
	        	return true;
	        else 
	        	return false;
	        
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public boolean checkBlackjack(Player player) {
	    	/*
	    	 * Check handsize exactly 2
	    	 */
	    	if ( player.hand.size() == 2 ) {
	    		/*
	    		 * Check if 1 ace present
	    		 */
	    		if ( player.hand.getCardsOfRank(Rank.ACE).size() == 1 ) {
	    			/*
	    			 * Check if 1 ten,jack,queen,king present
	    			 */
	    			if ( player.hand.getCardOfRank(Rank.TEN).size() == 1 || player.hand.getCardOfRank(Rank.JACK).size() == 1 ||
	    					player.hand.getCardOfRank(Rank.QUEEN).size() == 1 || player.hand.getCardOfRank(Rank.KING).size() == 1 )
	    			return true;
	    		}
	    	} 
	    	return false;
	    }

		/**
		 *
		 * @param
		 * @return
		 */
	    public void checkWin(CardList hand) {

	    }
	    
	    
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
		private HashMap<String,Player> playerList;
		/**
		 * Field to store winner
		 */
		private Player winner;
		
	}
