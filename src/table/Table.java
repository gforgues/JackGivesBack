package table;

import storage.*;
import cards.*;
import participant.*;
import game.Blackjack;
import game.Observer;
import gameEngine.GameEngine;
import game.Observer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Table implements Observer {
	Player tableOwner;
	ArrayList<Player> players;
	ArrayList<Spectator> spectators;

	public Table(Player owner) {
		this.tableOwner = owner;
		players = new ArrayList<Player>();
		players.add(this.tableOwner);
		spectators = new ArrayList<Spectator>();
	}
 
	public Player getTableOwner() {
		return tableOwner;
	}
	
	public ArrayList<Player> getAllPlayers() {
		return players;
	}
 
	public ArrayList<Spectator> getAllSpectators(){
		return spectators;
	}
 
	public Player getPlayer(int index) {
		if (index >= 0 && index<players.size()) {
			return players.get(index);
		} else {
			System.out.println("Index is out of bounds. Returns the Table Owner");
			return this.tableOwner;
		}
	}
 
	public Spectator getSpectator(int index) {
		return spectators.get(index);
	}

	public boolean requestJoin(Player player, boolean canJoin) {
   
		if (canJoin) {
			System.out.println("Request join accepted");
			players.add(player);
		} else {
			System.out.println("Request join rejected");
		}
		
		return canJoin;
	}
	
	public boolean requestJoin(Player player){
		boolean join=false;
		boolean validInput=false;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can player " 
				+ player.getUsername() + " join the game? Enter true or false:");
		
		while (!validInput) {
			try {
				join = keyboard.nextBoolean();
				validInput = true;
			} catch (Exception e) {
				// Discard the invalid keyboard input and loop back
				keyboard.nextLine();
				System.out.println("Please enter true or false");
			}
		}
   
		if (join) {
			System.out.println("Request join accepted");
			players.add(player);
		} else {
			System.out.println("Request join rejected");
		}
		
		return join;
	}

	public boolean requestLeave(Player player, boolean canLeave) {		
		if (canLeave) {
			System.out.println("Request leave accepted");
			players.remove(this.findIndex(player));
		} else {
			System.out.println("Request leave rejected");
        }
		
		return canLeave;
	}
	
	public boolean requestLeave(Player player) {
		boolean leave;
		Scanner keyboard = new Scanner(System.in);
   
		System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can player ," 
				+ player.getUsername() + ", leave the game? Enter true or false:");
		leave = keyboard.nextBoolean();
		
		if(leave){
			System.out.println("Request leave accepted");
			players.remove(this.findIndex(player));
		} else {
			System.out.println("Request leave rejected");
        }
		
		return leave;
	}
	
	//Helper method to find the index of the player in the ArrayList of players
	private int findIndex(Player player) {
		int index = 0;
		
		for (int i=0; i<players.size(); i++) {
			if (players.get(i).equals(player)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
 
	public boolean requestView(Spectator spectator, boolean canView) {
		if (canView) {
			System.out.println("TableOwner accepts spectator");
			spectators.add(spectator);
		} else {
			System.out.println("TableOwner rejects spectator");
		}
		
		return canView;
	}
	
	public boolean requestView(Spectator spectator) {
		boolean view;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can spectator ," 
				+ spectator.getUsername() + ", view the game? Enter true or false:");
		view = keyboard.nextBoolean();
		
		if(view==true){
			System.out.println("TableOwner accepts spectator");
			spectators.add(spectator);
		} else {
			System.out.println("TableOwner rejects spectator");
		}
		
		return view;
	}
 
	public boolean requestLeave(Spectator spectator, boolean canLeave) {		
	    if (canLeave) {
			System.out.println("TableOwner accepts spectator from leaving the table");
			spectators.remove(this.findIndex(spectator));
		} else {
			System.out.println("TableOwner rejects spectator from leaving the table");
		}
	    
	    return canLeave;
	}
	
	public boolean requestLeave(Spectator spectator) {
		boolean leave;
	    Scanner keyboard = new Scanner(System.in); 
		
	    System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can spectator ," 
				+ spectator.getUsername() + ", leave the game? Enter true or false:");
	    leave = keyboard.nextBoolean();
		
		if(leave==true){
			System.out.println("TableOwner accepts spectator from leaving the table");
			spectators.remove(this.findIndex(spectator));
		} else {
			System.out.println("TableOwner rejects spectator from leaving the table");
		}
	    
	    return leave;
	}
	
	//Helper method to find the index of the spectator in the ArrayList of spectators
	private int findIndex(Spectator spectator) {
		int index=0;
		
		for (int i=0; i<spectators.size(); i++) {
			if (spectators.get(i).equals(spectator)) {
				index=i;
				break;
			}
		}
	
		return index;
	}
	
	//need save and close methods for both player and spectator 
	private boolean savePlayers(int gameID) {
		boolean allSuccessful = true;
		
		for (int i=0; i<players.size(); i++) {
			if (BlackjackStorage.savePlayerNames(players.get(i).getUsername(), gameID)) {
			//if (!Storage.savePlayer(players.get(i).toStatistics())) {
				allSuccessful = false;
			}
		}
		
		return allSuccessful;
	}
	
	private ArrayList<Player> loadPlayers(int gameId) {		
		ArrayList<String> playerNames = BlackjackStorage.loadPlayerNames(gameId);
		ArrayList<Player> players = new ArrayList<Player>();
		String password;
		Scanner keyboard = new Scanner(System.in);
		boolean loggedIn = false;
		
		for (int i=0; i<playerNames.size(); i++) {
			loggedIn = false;
			while (loggedIn == false) {
				System.out.println(playerNames.get(i) + ", Please enter your password");
				password = keyboard.nextLine();
				try {
					players.add(new Player(playerNames.get(i), password));
					loggedIn = true;
				} catch (IllegalArgumentException e) {
					System.out.println(e);
				}
			}
		}
			
		return players;
	}
	
	private Deck loadDeck(int gameId) {
		return BlackjackStorage.loadDeck(gameId);
	}
 
	public boolean saveGame(Deck deck, int gameID) { //HashMap<Player, ArrayList<BlackjackHand>> playersAndHands){
		boolean isSuccessful = true;
		
		if (!BlackjackStorage.saveDeck(deck, gameID) || !this.savePlayers(gameID)) {
			isSuccessful = false;
		}
		
		return isSuccessful;
		}
	
	/*
	 * Loads the game and returns a new GameEngine with all of the loaded
	 * info (including players, their hands, and the game's deck)
	 */
	public GameEngine loadGame(int gameId) {		
		ArrayList<Player> players = this.loadPlayers(gameId);
		this.players = players;
		Deck deck = this.loadDeck(gameId);
		
		return new GameEngine(deck);
	}
	
	public String toString() {
		String string = "Table owner for this table is " + tableOwner.getUsername();
		
		if (!players.isEmpty()) {
			string += " and the players for this table are: \n";
				
			for (int i=0; i<players.size(); i++) {
				string += players.get(i).getUsername() + "\n";
			}
		
		}
		
		if (!spectators.isEmpty()) {
			string += "The spectators for this table are: \n";
			
			for (int i=0; i<spectators.size(); i++) {
				string += spectators.get(i).getUsername() + "\n";
			}
		}
		
		return string;
	}
	
	@Override
	public void handleEvent()
	{
		// TODO Auto-generated method stub
		
	}
}