package gameEngine;

import java.util.*;

import participant.Chips;
import participant.Player;
import participant.Table;
import game.*;
import game.Observable;
import game.Observer;
import cards.*;

	public class GameEngine	implements Observable {
		
		public GameEngine() {
			gameStart();
		}
		
		public void reset() {
			myGame = new Blackjack();
			playersHands = new HashMap<Player, ArrayList<Hand>>();
			playersChips = new HashMap<Player, Chips>();
			roundDone = false;
			this.notifyObservers();
		}
		
		public void gameStart() {
			
			reset();

			for ( int i = 0 ; i < playersHands.size() ; i++ ) {
				Scanner keyboard = new Scanner(System.in);
				int a= keyboard.nextInt();
				System.out.print(a);
			}
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
			observerList.remove(obsToRemove);
			
		}
		
		public void resetObservers() {
			observerList = new ArrayList<Observer>();
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
		
		public ArrayList<Hand> getPlayerHand(Player player) {
			 return playersHands.get(player);
		}

		/**
		 * Returns winner between two BlackjackHands
		 * @param hand1 First BlackjackHand to compare
		 * @param hand2 Second BlackjackHand to compare
		 * @return BlackjackHand winner, null if no winner
		 */
		public BlackjackHand getWinner(BlackjackHand hand1, BlackjackHand hand2) {

			if (hand1.getBlackjackValue() > hand2.getBlackjackValue())
				return hand1;
			
			if (hand1.getBlackjackValue() < hand2.getBlackjackValue())
				return hand2;
			
				return null;
			
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
