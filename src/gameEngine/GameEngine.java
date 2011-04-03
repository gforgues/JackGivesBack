package gameEngine;

import java.util.ArrayList;
import java.util.HashMap;

import participant.Chips;
import participant.Player;
import participant.Table;
import game.*;
import cards.*;

	public class GameEngine	implements Observable {
		
		public GameEngine() {
			reset();
		}
		
		public void reset() {
			myGame = new Blackjack();
			playersHands = new HashMap<Player, ArrayList<Hand>>();
			playersChips = new HashMap<Player, Chips>();
			roundDone = false;
			this.notifyObservers();
		}	
		
		@Override
		public void notifyObservers()
		{
			if (!observerList.isEmpty()){
				for (Observer ob : observerList) {
					ob.handleEvent();
				}
			}			
		}

		@Override
		public void addObservers(Observer obsToAdd)
		{
			observerList.add(obsToAdd);
			
		}

		@Override
		public void removeObservers(Observer obsToRemove)
		{
			// TODO Auto-generated method stub
			
		}
		
		public void resetObservers() {
			
		}
		
		public void loadTable(Table table) {
			gameTable = table;
		}
		
		public void loadPlayers() {
			for ( int i = 0 ; i < gameTable.getAllPlayers().size() ; i++ ) {
				Player tmpPlayer = gameTable.getAllPlayers().get(i);
				if (!playersHands.containsKey(tmpPlayer)) {
					playersHands.put(tmpPlayer, new ArrayList<Hand>());
				} 
			}
		}
		
		public void loadHands() {
			
		}
		
		public void loadChips() {
			
		}
		
		public void loadBets() {
			
		}
		
		public boolean isRoundComplete() {
			return roundDone;
		}
		
		public void setDealer(Player player) {
			
		}
		
		public void addPlayer(Player player) {
			
		}
		
		public void getPlayerHand(Player player) {
			
		}

		public Player getWinner(Player player1, Player player2) {
			Player tmpWinner = null;
			
			
			return tmpWinner;
		}
		
		
		
//		public void resetKeepPlayers() {
//			myGame = new Blackjack();
//			winner = null;
//			
//			HashMap<Player, Hand> tmpPlayers = new HashMap<Player, Hand>();
//			for (int i = 0 ; i < gameTable.getAllPlayers().size() ; i++) {
//				Player tmpPlayer = gameTable.getPlayer(i);
//				tmpPlayers.put(tmpPlayer, new Hand());
//			}
//			playersHands = tmpPlayers;
//
//		}
	
		public int getNumberOfPlayers() {
			return playersHands.size();
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


		private Game myGame;

		private Table gameTable;

		private ArrayList<Observer> observerList = new ArrayList<Observer>();
		
		private boolean roundDone;

		private HashMap<Player, ArrayList<Hand>> playersHands;

		private HashMap<Player, Chips> playersChips;

		
	}
