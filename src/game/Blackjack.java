package game;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Scanner;

import cards.*;
//import cards.Card.Rank;
import participant.Player;
//import cards.Hand;
//import storage.Storage;
import gameEngine.GameEngine;

/**
* @author JackGivesBack
*/

public class Blackjack implements Game {
 
	// Deal exactly 2 cards for the first initial deal to a player
	private final int INITIAL_DEAL_VALUE = 2;
 	private final int MAX_HAND_SIZE = 2;
 
	// Number of decks to use 
	private final int NUM_DECKS = 6;
 
	// Number of cards left to trigger new deck
	private final int DECK_RESET_VALUE = 104;
 
	// Field to store chip count
	public int chipCount;
 
	// Initialize new deck
	private Deck gameDeck;
 
	/** 
	 * Blackjack game initialization
	 */
	public Blackjack() {
		gameDeck = new Deck();
		gameDeck.addDeck(NUM_DECKS);
		gameDeck.shuffle();
	}

	 /**
	  * Reset player's hand state
	  * @param pHand A given BlackjackHand of a player
	  */
	public void resetHandState(BlackjackHand pHand) {
		pHand.clearHand();
	}

	 /**
	  * Gets the game's deck
	  * @return Game deck
	  */
	public Deck getDeck() {
		return this.gameDeck;
	} 
 
	 /**
	  * Sets the game's deck
	  * @param the deck to set
	  */
	public void setDeck(Deck deck){
		this.gameDeck=deck;
	}

 
	 /**
	  * Check if Deck is low on cards
	  * @param
	  * @return True if Deck is low on cards, false otherwise
	  */ 
	public void checkDeck() {
		if ( gameDeck.size() < DECK_RESET_VALUE ) {
			gameDeck = new Deck();
			gameDeck.addDeck(NUM_DECKS);
			gameDeck.shuffle();
		}
	}

	 /**
	  * Deals the initial amount of cards to a BlackjackHand
	  * @param pHand BlackjackHand to deal
	  */
	public void deal(BlackjackHand pHand) {
		// Clear player's hand first
		resetHandState(pHand);
  
		// Check deck status
		checkDeck();
  
		// Deal exactly two cards for the first initial deal
		for ( int i = 0 ; i < INITIAL_DEAL_VALUE ; i++ ){
			pHand.addCard(gameDeck.draw());
		}
	}
 
	 /**
	  * Deals a card to a hand
	  * @param pHand BlackjackHand to add card to
	  * @return dealHand BlackjackHand with the cards added
	  */
	public BlackjackHand dealHand(BlackjackHand pHand) {
		resetHandState(pHand);
		checkDeck();
		for (int i=0; i < INITIAL_DEAL_VALUE; i++) {
			pHand.addCard(gameDeck.draw());
		}
		return pHand;
	}

	 /**
	  * Adds a card to a Hand
	  * @param pHand BlackjackHand to add card to
	  * @return
	  */
	public void hit(BlackjackHand pHand) {
		// Check deck status
		checkDeck();
  
		// Check if player's hand state is done or not
		if ( pHand.isPlayable() )
   
		// Draws a card from the Deck and adds it to the Hand
		pHand.addCard(gameDeck.draw());
	}
	
	 /**
	  * Set player's Hand state to be done
	  * @param pHand BlackjackHand to set state
	  * @return
	  */
	public void stand(BlackjackHand pHand) {
		pHand.setDone();
	}

	 /**
	  * @param pHand BlackjackHand to apply doubledown to
	  * @return
	  */
	public void doubleDown(BlackjackHand pHand) {
		// Check deck status
		checkDeck();
		//hit the hand and stand 
		hit(pHand);
		stand(pHand);
	}

	 /**
	  * Splits a pair into two separate BlackjackHands
	  * @param pHand BlackjackHand to apply split to
	  * @return Returns a BlackjackHand that has one of the pairs in pHand
	  */
 
	public ArrayList<BlackjackHand> split(BlackjackHand pHand) {
		ArrayList<BlackjackHand> bothHands = new ArrayList<BlackjackHand>();
		if (pHand.checkPair() == false) {
			System.out.println("You dont have a pair! Select a different move");
		} else {
			// Clones the BlackjackHand
			BlackjackHand newHand =  pHand.clone();
   
			// Remove first card in given hand and second card in cloned hand
			// Automatically hit each hand so that there are two cards in each hand
    
			pHand.removeCard(0);
			hit(pHand);
			newHand.removeCard(1);
			hit(newHand);
			bothHands.add(pHand);
			bothHands.add(newHand);
			return bothHands;
		}
		return bothHands;
	}
 
	/**
	 * Plays the "split" hands derived from the user operation split
	 * @param bothHands ArrayList<BlackjackHand>, array list of type BlackjackHand that contains both the "split" hands
	 * @param game GameEngine, game of the type GameEngine 
	 * @param player Player, player of the type Player
	 * @return
	 */
	public void playsSplitHands(ArrayList<BlackjackHand> bothHands, GameEngine game, Player player) {
		boolean invalidInput = false;
		String handNumber = "";
		String s;
		boolean isBust = false;
 
		Scanner keyboard=new Scanner(System.in);
	
		for (int i=0; i<MAX_HAND_SIZE; i++) {
			if (i==0) {
				handNumber = "First hand";
			} else if (i==1) {
				handNumber = "Second hand"; 
			}
		
			while (!invalidInput || bothHands.get(i).isPlayable() || !isBust) { 
				invalidInput = false;
				System.out.println(handNumber + ": " + bothHands.get(i).toString());
				System.out.println("For your " + handNumber + ", do you want to hit or stand");
				s = keyboard.next();
			
				if (s.equals("hit")) {
					this.hit(bothHands.get(i));
					System.out.println("hand update: " + bothHands.get(i).toString());
					isBust = bothHands.get(i).isBust();
					invalidInput = true;
				} else if (s.equals("stand")){
					this.stand(bothHands.get(i));
					System.out.println("final hand: "+bothHands.get(i).toString());
					invalidInput = true;
					break;
				} else {
					System.out.println("Invalid input, enter hit/stand: ");
				}
			}
		
			game.computeWinner(player, bothHands.get(i));
		}
	}
}
 