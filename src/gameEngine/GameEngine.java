package gameEngine;
/**
 * @author JackGivesBack
 */
import game.Blackjack;
//import game.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import participant.Chips;
import participant.Player;
import table.Table;
import cards.BlackjackHand;
import cards.Deck;

public class GameEngine {
	private Blackjack myGame;

	private Table gameTable;

	//private ArrayList<Observer> observerList = new ArrayList<Observer>();

	private BlackjackHand dealerHand = new BlackjackHand();

	private HashMap<Player, BlackjackHand> playersAndHands;

	private HashMap<Player, Chips> playersAndChips;

	private final int DEALER_WON = 1;

	private final int PLAYER_WON = -1;

	private final int DRAW = 0;

	private final int WIN_CONSTANT = 2;

	private final int BLACKJACK_PAYOUT_CONSTANT = 3;
	
	private final int MUST_HIT = 16;
	
	boolean splitPlayed = false;


	public GameEngine(Player player) {

		gameTable = new Table(player);

	}

	public GameEngine(Deck deck){
		myGame=new Blackjack();
		myGame.setDeck(deck);
	}

	public void reset() {
		myGame = new Blackjack();
		playersAndHands = new HashMap<Player, BlackjackHand>();
		playersAndChips = new HashMap<Player, Chips>();
		dealerHand = new BlackjackHand();
	}

	public void resetKeepPlayers() {
		myGame = new Blackjack();
	}

	public void loadPlayers() {

		for ( int i = 0 ; i < gameTable.getAllPlayers().size() ; i++ ) {
			Player tmpPlayer = gameTable.getAllPlayers().get(i);
			if (!playersAndHands.containsKey(tmpPlayer)) {
				playersAndHands.put(tmpPlayer, new BlackjackHand());
			} 
		}
	}

	public void loadChips() {
		for ( int i = 0 ; i < gameTable.getAllPlayers().size() ; i++ ) {
			Player tmpPlayer = gameTable.getAllPlayers().get(i);
			if (!playersAndChips.containsKey(tmpPlayer)) {
				playersAndChips.put(tmpPlayer, new Chips(tmpPlayer.getChips()));
			} 
		}
	}

	private void setAllBets(Player player) {
		boolean invalid = false;
		System.out.println(player.getUsername() + ", you currently have " + player.getChips() + " chips.");
		System.out.println("How much do you want to bet?");
		int betValue = 0;
		Scanner keyboard = new Scanner(System.in);

		while (invalid == false) {
			try { 
				betValue = keyboard.nextInt();
				playersAndChips.get(player).setBet(betValue);
				playersAndChips.get(player).addChips(player, -betValue);

				System.out.println("You want to bet " + betValue);
				invalid = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Please enter a valid bet amount. Bets must be multiples of 10, between 10 and 100");
			} catch (InputMismatchException IE) {
                System.out.println("This is not a valid integer!");
                System.out.println(player.getUsername() + ", you currently have " + player.getChips() + " chips.");
                System.out.println("Please try again.");
                keyboard.nextLine();
			}
		}
	}

	private void playThroughEachPlayer(Player player, BlackjackHand hand) {
		Scanner keyboard = new Scanner(System.in);
		while (hand.isPlayable() && !hand.isBust()){
			System.out.println(player.getUsername() + ", your current hand is: " + hand.toString());
			System.out.println(player.getUsername() + ", do you want to hit, stand, doubledown or split?");
			String s = keyboard.next();

			if (s.equals("hit")){
				myGame.hit(hand);
				System.out.println("hand update: " +hand.toString());
			}
			else if (s.equals("stand")){
				myGame.stand(hand);
				System.out.println("final hand: "+hand.toString());
			}
			else if (s.equals("doubledown")){
				if (player.getChips() < 2*playersAndChips.get(player).getBet()) {
					System.out.print("Sorry not enough chips, please enter a different move" );
					s = "";
				}
				else { 
					if(hand.getNumberCards() > 2){
						System.out.println("Cannot double down, you have already hit, please hit again or stand");
					}else{
					playersAndChips.get(player).addChips(player, -(playersAndChips.get(player).getBet()));
				    int doubleBet =((playersAndChips.get(player)).getBet())*2;
				    playersAndChips.get(player).setBet(doubleBet);
					myGame.doubleDown(hand);
					System.out.println("bet update: "+ doubleBet);
					System.out.println("hand update: "+hand.toString());
					}
				}

			}
			else if (s.equals("split")){
				splitPlayed = true;
				ArrayList<BlackjackHand> bothHands = myGame.split(hand);

		        if(bothHands.size() != 0){
		         myGame.playsSplitHands(bothHands, this, player);
		         this.splitPlayed = true;
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

	private void showDealerFinalHand() {
		//shows dealer's final hand
		System.out.println("Dealer's new hand: " + dealerHand.toString());	
	}

	private int processWinner(BlackjackHand hand) {

		if (hand.isBust())

			return DEALER_WON;

		if (!(dealerHand.isBust()) && (dealerHand.getBlackjackValue() > hand.getBlackjackValue()))

			return DEALER_WON;

		if (dealerHand.isBust()) {
			System.out.println("Dealer is bust!");
			return PLAYER_WON;
		}
		if (!(dealerHand.isBust()) && dealerHand.getBlackjackValue() < hand.getBlackjackValue()) {

			return PLAYER_WON;

		}

			return DRAW;

	}

	public String computeWinner(Player player, BlackjackHand hand) {
		String string = "";

		if (processWinner(hand) == DEALER_WON) {
			player.addChips(playersAndChips.get(player).getBet());
			player.addLoss(playersAndChips.get(player).getBet());
			string = player.getUsername() + " loses against dealer!";
		}
		if (processWinner(hand) == PLAYER_WON) {
			if (hand.checkBlackjack()) {
				player.addWin(BLACKJACK_PAYOUT_CONSTANT * playersAndChips.get(player).getBet());
				string = "BLACKJACK! X2 PAYOUT! " + player.getUsername()+ " wins!";
			} else {
				player.addWin(WIN_CONSTANT * playersAndChips.get(player).getBet());
				string = player.getUsername()+ " wins against dealer!";
			}
		}
		if (processWinner(hand) == DRAW) {
			playersAndChips.get(player).addChips(player, playersAndChips.get(player).getBet());
			string = player.getUsername() + " draws with dealer!";
		}
			
		if (!splitPlayed) {
			System.out.println(string);
		} else {
			splitPlayed = false;
		}
		
		return string;
	}

	public void gameStart() {
		reset();
		myGame.deal(dealerHand); 
		loadPlayers();
		loadChips();

		ArrayList<Player> players = gameTable.getAllPlayers();

		for (int i=0; i<players.size(); i++) {
			this.setAllBets(players.get(i));
		}
		
		System.out.println("Dealing everyone...");
		this.dealEveryonesHand(players);
		
		System.out.println("Dealer's current hand : " + dealerHand);
		this.playDealerHand(dealerHand);
		
		for (int i=0; i<players.size(); i++) {
			BlackjackHand hand = playersAndHands.get(players.get(i));
			this.playThroughEachPlayer(players.get(i), hand);
		}
		
		this.showDealerFinalHand();

		for (int i=0;i<players.size();i++){ 
			Player player = players.get(i);
			BlackjackHand hand = playersAndHands.get(player);
			this.computeWinner(player, hand);
		}
	}

	private void dealEveryonesHand(ArrayList<Player> players) {
		for (int i=0; i < players.size(); i++){
			BlackjackHand pHand = new BlackjackHand();
			playersAndHands.put(players.get(i), myGame.dealHand(pHand));
		//	BlackjackHand hand = playersAndHands.get(players.get(i));
			//System.out.println(players.get(i).getUsername() + ", your hand is currently: " + hand.toString());
		}
	}
	
	private void playDealerHand(BlackjackHand dealerHand) {
		if (dealerHand.checkBlackjack()) {
			dealerHand.setDone();	
		} else {
			while (dealerHand.getBlackjackValue() <= MUST_HIT) 
				myGame.hit(dealerHand);
			dealerHand.setDone();
		}
	}


	/**
	 * Returns the current game's Table object
	 * @return Table object
	 */
	public Table getTable() {
		return gameTable;
	}

	/**
	 * Returns the current game's Deck object
	 * @return Deck object
	 */
	public Deck getDeck() {
		return myGame.getDeck();
	}

	public void addPlayer(Player player) {
		BlackjackHand hb = new BlackjackHand();
		ArrayList<BlackjackHand> hbb = new ArrayList<BlackjackHand>();
		hbb.add(hb);
		gameTable.requestJoin(player);
	}
}