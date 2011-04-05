package table;

import storage.*;
import cards.*;
import participant.*;
import game.Blackjack;
import gameEngine.GameEngine;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Table {
	Player tableOwner;
	ArrayList<Player> players;
	ArrayList<Spectator> spectators;

	public Table(Player owner) {
		this.tableOwner = owner;
		players = new ArrayList<Player>();
		players.add(this.tableOwner);
		spectators = new ArrayList<Spectator>();
	}
 
	public ArrayList<Player> getAllPlayers() {
		return players;
	}
 
	public ArrayList<Spectator> getAllSpectators(){
		return spectators;
	}
 
	public Player getPlayer(int index) {
		return players.get(index);
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
		boolean join;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can player ," 
				+ player.getUsername() + ", join the game? Enter true or false:");
		join = keyboard.nextBoolean();
   
		if(join==true){
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
	public boolean savePlayers() {
		boolean allSuccessful = true;
		
		for (int i=0; i<players.size(); i++) {
			if (!Storage.savePlayer(players.get(i).toStatistics())) {
				allSuccessful = false;
			}
		}
		
		return allSuccessful;
		//return Storage.savePlayer(player);
	}
	
	//How do we load the players if we just know the gameId? And not their username/password
	
//	public ArrayList<Player> loadPlayers(int gameId) {		
//		return Storage.loadPlayer("temp", "temp");
//	}
	
	//Ho do we load the hand if we don't know the players username?
	
//	public Hand loadHand(int gameId) {
//		
//		return hand;
//	}
	
	public Deck loadDeck(int gameId) {
		return BlackjackStorage.loadDeck(gameId);
	}
	
	public boolean saveSpectators() {
		boolean allSuccessful = true;
		
		for (int i=0; i<players.size(); i++) {
//			if (!Storage.savePlayer(spectators.get(i).toStatistics())) {
//				allSuccessful = false;
//			}
		}
		
		return allSuccessful;
	}
	
	//How do cycle through each key of a HashMap??? so that we can get each players
	//hand and save it using BlackjackStorage.saveHand(..);
	public boolean saveHands(HashMap<Player,ArrayList<BlackjackHand>> playerHand, int gameID) {
		Set set = playerHand.keySet();
		Iterator itr = set.iterator();
		Player player;
		boolean allSuccessful = true;
		
		while (itr.hasNext()) {
			player = ((Player)itr.next());
			for (int i=0; i<playerHand.get(player).size(); i++) {
				if (!BlackjackStorage.saveHand(player.getUsername(), playerHand.get(player).get(i), gameID)) {
					allSuccessful = false;
				}
			}
			//System.out.println(itr.next());
		}
		
		return allSuccessful;
	}
 
	//how are we saving the hands if they're stored in the blackjacks
	public boolean saveGame(Deck deck, int gameID, GameEngine game){
		boolean isSuccessful = true;
		HashMap<Player, ArrayList<BlackjackHand>> playersAndHands = game.getPlayersAndHands();
		
		if (!this.savePlayers() || !this.saveSpectators() ||
				!BlackjackStorage.saveDeck(deck, gameID) || !this.saveHands(playersAndHands, gameID)) {
			isSuccessful = false;
		}
		//this.saveSpectators();
		
		return isSuccessful;
		//return Storage.savePlayer(spectator);
	}
	
	//When we load the game, how are we passing the deck, players, and hands back to
	//the game??
	public void loadGame(int gameId) {
		//this.loadPlayers(gameId);
		//this.loadSpectators(gameId);
		//this.loadHand(gameId);
		//this.loadDeck(gameId);
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
}