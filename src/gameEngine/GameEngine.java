package gameEngine;

import java.util.HashMap;

import participant.Player;
import game.*;
import cards.*;

	public class GameEngine	implements Observable {
		
		public GameEngine() {
			reset();
		}
		
		@Override
		public void notifyObservers()
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addObservers(Observer obsToAdd)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeObservers(Observer obsToRemove)
		{
			// TODO Auto-generated method stub
			
		}
		
		public void reset() {
			myGame = new Blackjack();
			winner = null;
			this.notifyObservers();
		}
	
		public int getNumberOfPlayers() {
			return playerList.size();
		}
//		
//		public void playDoubleDown() {
//	    	if ( hasSufficientChips(player, chipCount)) {
//	    		player.chips -= chipCount;
//	    		chipCount += chipCount;
//			
//		}
		
		/**
		 * Checks if there is sufficient specified amount of chips
		 * @param amount The amount to be checked
		 * @return True if there is sufficient chips, false otherwise
		 */
//	    public boolean hasSufficientChips(Player player, int amount) {
//	    	if ( player.getChips() < amount ) {
//	    		return false;
//	    	}
//	    	return true;
//	    }
		
		/**
		 *
		 * @param
		 * @return
		 */
//	    public void bet(Player player, int amount) {
//	    	if ( hasSufficientChips(player,amount) ) {
//	    		chipCount += amount;
//	    		player.getChips() -= amount;
//	    	}
//	    }	  

		/**
		 * Game to play
		 */
		private Game myGame;
		/**
		 * 
		 */
		private boolean gameDone;
		/**
		 * Field to store current set of players
		 */
		private HashMap<String,Player> playerList;
		/**
		 * Field to store winner
		 */
		private Player winner;

		
	}
