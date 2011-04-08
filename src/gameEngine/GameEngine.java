package gameEngine;

import java.util.*;

import gui.MainInterface;

import participant.Chips;
import participant.Player;
import table.Table;
import game.*;
import game.Observable;
import game.Observer;
import cards.*;

	public class GameEngine	implements Observable {
		private Blackjack myGame;

		private Table gameTable;

		private ArrayList<Observer> observerList = new ArrayList<Observer>();

		private BlackjackHand dealerHand = new BlackjackHand();

		private HashMap<Player, ArrayList<BlackjackHand>> playersAndHands;

		private HashMap<Player, Chips> playersAndChips;

		private final int MUST_HIT = 16;

		private final int STARTING_CHIPS = 500;

		private final int DEALER_WON = 1;

		private final int PLAYER_WON = -1;

		private final int DRAW = 0;

		private final int WIN_CONSTANT = 2;

		private final int BLACKJACK_PAYOUT_CONSTANT = 3;

		private final int MIN_BET = 10;

		private final int MAX_BET = 100;



		public GameEngine(Player player) {

			gameTable = new Table(player);

		}

		/* Currently, storage/blackjackStorage can only save and load one hand for each player
		 * so players.size() must equal to hand.size() */

		public GameEngine(Deck deck){
			myGame=new Blackjack();
			myGame.setDeck(deck);
		}
		public void reset() {
			myGame = new Blackjack();
			playersAndHands = new HashMap<Player, ArrayList<BlackjackHand>>();
			playersAndChips = new HashMap<Player, Chips>();
			dealerHand = new BlackjackHand();
			this.notifyObservers();
		}

		public void resetKeepPlayers() {
			myGame = new Blackjack();
			this.notifyObservers();
		}

		public HashMap<Player, ArrayList<BlackjackHand>> getPlayersAndHands() {
			return this.playersAndHands;
		}
		
		private int checkValidInputBet(Player player) {
			boolean invalid = false;
			System.out.println("how much do you want to bet?");
			int a = 0;
			Scanner keyboard = new Scanner(System.in);
			
			while (invalid == false) {
				try { 
					a = keyboard.nextInt();
					playersAndChips.get(player).setBet(a);
					playersAndChips.get(player).addChips(player, -a);
	
					System.out.println(" you want to bet " + a);
					invalid = true;
	
				} catch (Exception IllegalArgumentException) {
						System.out.println(IllegalArgumentException.getMessage() + " You currently have " + player.getChips() + " chips.");
						System.out.println("Please try again.");
	
				}
			}
			
			return a;
		}

		public void gameStart() {

			reset();
			myGame.deal(dealerHand); 
			loadPlayers();
			loadChips();

			ArrayList<Player> players = gameTable.getAllPlayers();
			Player player;
			Scanner keyboard = new Scanner(System.in);

			for (int i=0; i<players.size(); i++) {
			
				player = players.get(i);
				System.out.println(player.getUsername() + ", you have " + player.getChips() + " chips.");
				
				int a = this.checkValidInputBet(player);

				//set the bet and remove the chips


			}

			
			System.out.println("Dealer's hand: " + dealerHand.toString());	

			if (dealerHand.checkBlackjack()) {
				dealerHand.setDone();
				System.out.println("Game over!");	
			} else {

				while (dealerHand.getBlackjackValue() < MUST_HIT) 
					myGame.hit(dealerHand);
				dealerHand.setDone();

			/*
			 * Deal everyone
			 */
			System.out.println("Dealing everyone...");
			for (int i=0; i<players.size(); i++){
				System.out.println(players.get(i).getUsername());
			}

			for (ArrayList<BlackjackHand> b : playersAndHands.values()) {
				BlackjackHand tmpHand = new BlackjackHand();
				myGame.deal(tmpHand);
				b.add(tmpHand);
			}

			for (int i=0;i<getNumberOfPlayers();i++){
				ArrayList<BlackjackHand> b = playersAndHands.get(players.get(i));
				System.out.println(players.get(i).getUsername() + ", your first hand is currently: " + b.get(0).toString());
				if (b.size() == 2) {
					System.out.println(players.get(i).getUsername() + ", your second hand is currently: " + b.get(1).toString());
				}
			}

				

				for (int i=0; i<players.size(); i++){

					ArrayList<BlackjackHand> b = playersAndHands.get(players.get(i));

					System.out.println(players.get(i).getUsername()+": ");
				


					for (int j=0; j < b.size(); j++) {


						while (b.get(j).isPlayable() && !(b.get(j).isBust())){

							System.out.println("Hit, stand, doubledown or split?");
							String s = keyboard.next();

							if (s.equals("hit")){
								myGame.hit(b.get(j));
								System.out.println("hand update: " +b.get(j).toString());
							}
							else if (s.equals("stand")){
								myGame.stand(b.get(j));
								System.out.println("final hand: "+b.get(j).toString());
							}
							else if (s.equals("doubledown")){
								if (players.get(j).getChips() < 2*playersAndChips.get(players.get(j)).getBet()) {
									System.out.print("Sorry not enough chips, please enter a different move" );
									s = "";
								}
								else { 
									if(b.get(j).getNumberCards()>2){
										System.out.println("Cannot double down, you have already hit, please hit again or stand");
									}else{
									playersAndChips.get(players.get(j)).addChips(players.get(j), -(playersAndChips.get(players.get(j)).getBet()));
								    int doubleBet;
								    doubleBet=((playersAndChips.get(players.get(j))).getBet())*2;
								    (playersAndChips.get(players.get(j))).setBet(doubleBet);
									myGame.doubleDown(b.get(j));
									System.out.println("bet update: "+ doubleBet);
									System.out.println("hand update: "+b.get(j).toString());
									}
								}

							}
							else if (s.equals("split")){
								ArrayList<BlackjackHand> bothHands = myGame.split(b.get(j));

						        if(bothHands.size() != 0){
						         myGame.playsSplitHands(bothHands);
						         //System.out.println("split to be implemented: "+b.get(j).toString());
						        } else {
						        System.out.println("please enter something valid");
						        s = "";
						       }
						      } else {
								System.out.println("please enter something valid");
								s = "";
							}
						}
					}
				}


			//shows dealer's final hand
			System.out.println("Dealer's new hand: " + dealerHand.toString());	
			if (dealerHand.isBust()) 
					System.out.println("Dealer bust!");


			/*
			 * Compute all winners
			 */
			for (int i=0;i<getNumberOfPlayers();i++){

				ArrayList<BlackjackHand> b = playersAndHands.get(players.get(i));

				if (b.size() == 1) {
					if (processWinner(b.get(0)) == 1) {
						players.get(i).addChips(playersAndChips.get(players.get(i)).getBet());
						players.get(i).addLoss(playersAndChips.get(players.get(i)).getBet());
						System.out.println("Dealer wins!");
					}
					if (processWinner(b.get(0)) == -1) {
						if (b.get(0).checkBlackjack()) {
//							playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), BLACKJACK_PAYOUT_CONSTANT * playersAndChips.get(gameTable.getPlayer(i)).getBet());
							players.get(i).addWin(BLACKJACK_PAYOUT_CONSTANT * playersAndChips.get(players.get(i)).getBet());
							System.out.println("BLACKJACK! X2 PAYOUT! " + gameTable.getPlayer(i).getUsername()+ " wins!");
						} else {
							players.get(i).addWin(WIN_CONSTANT * playersAndChips.get(players.get(i)).getBet());
//							playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), WIN_CONSTANT * playersAndChips.get(gameTable.getPlayer(i)).getBet());
							System.out.println(gameTable.getPlayer(i).getUsername()+ " wins!");
						}
					}
					if (processWinner(b.get(0)) == 0) {
						playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), playersAndChips.get(gameTable.getPlayer(i)).getBet());
						System.out.println("Draw!");
					}

				} else {

					if (processWinner(b.get(0)) == 1) {
						players.get(i).addChips(playersAndChips.get(players.get(i)).getBet());
						players.get(i).addLoss(playersAndChips.get(players.get(i)).getBet());
						System.out.println("Dealer wins!");
					}
					if (processWinner(b.get(0)) == -1) {
						if (b.get(0).checkBlackjack()) {
							players.get(i).addWin(BLACKJACK_PAYOUT_CONSTANT * playersAndChips.get(players.get(i)).getBet());
//							playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), BLACKJACK_PAYOUT_CONSTANT * playersAndChips.get(gameTable.getPlayer(i)).getBet());
							System.out.println("BLACKJACK! X2 PAYOUT! " + gameTable.getPlayer(i).getUsername()+ " wins!");
						} else {
							players.get(i).addWin(WIN_CONSTANT * playersAndChips.get(players.get(i)).getBet());
//							playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), WIN_CONSTANT * playersAndChips.get(gameTable.getPlayer(i)).getBet());
							System.out.println(gameTable.getPlayer(i).getUsername()+ " wins!");
						}
					}
					if (processWinner(b.get(0)) == 0) {
						playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), playersAndChips.get(gameTable.getPlayer(i)).getBet());
						System.out.println("Draw!");
					}

					if (processWinner(b.get(1)) == 1) {
						players.get(i).addChips(playersAndChips.get(players.get(i)).getBet());
						players.get(i).addLoss(playersAndChips.get(players.get(i)).getBet());
						System.out.println("Dealer wins!");
					}
					if (processWinner(b.get(1)) == -1) {
						if (b.get(0).checkBlackjack()) {
							players.get(i).addWin(BLACKJACK_PAYOUT_CONSTANT * playersAndChips.get(players.get(i)).getBet());
//							playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), BLACKJACK_PAYOUT_CONSTANT * playersAndChips.get(gameTable.getPlayer(i)).getBet());
							System.out.println("BLACKJACK! X2 PAYOUT! " + gameTable.getPlayer(i).getUsername()+ " wins!");
						} else {
							players.get(i).addWin(WIN_CONSTANT * playersAndChips.get(players.get(i)).getBet());
//							playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), WIN_CONSTANT * playersAndChips.get(gameTable.getPlayer(i)).getBet());
							System.out.println(gameTable.getPlayer(i).getUsername()+ " wins!");
						}
					}
					if (processWinner(b.get(1)) == 0) {
						playersAndChips.get(gameTable.getPlayer(i)).addChips(players.get(i), playersAndChips.get(gameTable.getPlayer(i)).getBet());
						System.out.println("Draw!");
					}

				}

			}

			}

		}

		/**
		 * Returns the current game's Deck object
		 * @return Deck object
		 */
		public Deck getDeck() {
			return myGame.getDeck();
		}

		/**
		 * Returns the current game's Table object
		 * @return Table object
		 */
		public Table getTable() {
			return gameTable;
		}



		public void addPlayer(Player player) {
			BlackjackHand hb = new BlackjackHand();
			ArrayList<BlackjackHand> hbb = new ArrayList<BlackjackHand>();
			hbb.add(hb);
			gameTable.requestJoin(player);
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

			for ( int i = 0 ; i < getPlayers().size() ; i++ ) {
				Player tmpPlayer = getPlayers().get(i);
				if (!playersAndHands.containsKey(tmpPlayer)) {
					playersAndHands.put(tmpPlayer, new ArrayList<BlackjackHand>());
				} 
			}
		}

		public ArrayList<Player> getPlayers() {
			return gameTable.getAllPlayers();
		}


		public void loadChips() {
			for ( int i = 0 ; i < getPlayers().size() ; i++ ) {
				Player tmpPlayer = getPlayers().get(i);
				if (!playersAndChips.containsKey(tmpPlayer)) {
					playersAndChips.put(tmpPlayer, new Chips(tmpPlayer.getChips()));
				} 
			}
		}


		public ArrayList<BlackjackHand> getPlayerHand(Player player) {
			 return playersAndHands.get(player);
		}

		/**
		 * Returns winner between hands of dealer and player
		 * @param hand BlackjackHand to compare
		 * @return 1 if dealer won, 0 if no winner, -1 if player won
		 */
		
		
		public int processWinner(BlackjackHand hand) {

			if ((hand.isBust()) && !(dealerHand.isBust()))

				return DEALER_WON;

			if (!(hand.isBust()) && !(dealerHand.isBust()) && (dealerHand.getBlackjackValue() > hand.getBlackjackValue()))

				return DEALER_WON;
			
			if ((hand.isBust()) && (dealerHand.isBust()))

				return DEALER_WON;

			if (!(hand.isBust()) && (dealerHand.isBust()))

				return PLAYER_WON;

			if (!(hand.isBust()) && !(dealerHand.isBust()) && dealerHand.getBlackjackValue() < hand.getBlackjackValue()) {

				return PLAYER_WON;
				
			}

				return DRAW;

		}


		public int getNumberOfPlayers() {
			return playersAndHands.size();
		}


	}
