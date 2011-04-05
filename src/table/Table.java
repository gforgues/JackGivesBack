package table;

import storage.*;
import cards.*;
import participant.*;
import game.Blackjack;

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

	public boolean requestJoin(Player player, boolean canJoin){
		boolean join;
		Scanner keyboard = new Scanner(System.in);
		
//		System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can player ," 
//				+ player.getUsername() + ", join the game? Enter true or false:");
//		join = keyboard.nextBoolean();
   
		if (canJoin) {
		//if(join==true){
			System.out.println("Request join accepted");
			//Add player to array list of players
			players.add(player);
		} else {
			System.out.println("Request join rejected");
		}
		
		return canJoin;
		//return join;
	} 

	public boolean requestLeave(Player player, boolean canLeave) {
		boolean leave;
		Scanner keyboard = new Scanner(System.in);
   
//		System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can player ," 
//				+ player.getUsername() + ", leave the game? Enter true or false:");
//		leave = keyboard.nextBoolean();
		
		if (canLeave) {
//		if(leave){
			System.out.println("Request leave accepted");
			players.remove(this.findIndex(player));
		} else {
			System.out.println("Request leave rejected");
        }
		
		return canLeave;
//		return leave;
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
		boolean view;
		Scanner keyboard = new Scanner(System.in);
		
//		System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can spectator ," 
//				+ spectator.getUsername() + ", view the game? Enter true or false:");
//		view = keyboard.nextBoolean();
		
		if (canView) {
//		if(view==true){
			System.out.println("TableOwner accepts spectator");
			//Add spectator to arraylist of spectators
			spectators.add(spectator);
		} else {
			System.out.println("TableOwner rejects spectator");
		}
		
		return canView;
//		return view;
	}
 
	public boolean requestLeave(Spectator spectator, boolean canLeave) {
		boolean leave;
	    Scanner keyboard = new Scanner(System.in); 
		
//	    System.out.println("TableOwner, " + this.tableOwner.getUsername() + ", can spectator ," 
//				+ spectator.getUsername() + ", leave the game? Enter true or false:");
//	    leave = keyboard.nextBoolean();
		
	    if (canLeave) {
//		if(leave==true){
			System.out.println("TableOwner accepts spectator from leaving the table");
			//remove spectator from list of spectators
			spectators.remove(this.findIndex(spectator));
		} else {
			System.out.println("TableOwner rejects spectator from leaving the table");
		}
	    
	    return canLeave;
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
	public boolean saveHands(HashMap<Player,BlackjackHand> playerHand, int gameID) {
		Set set = playerHand.keySet();
		Iterator itr = set.iterator();
		Player player;
		boolean allSuccessful = true;
		
		while (itr.hasNext()) {
			player = ((Player)itr.next());
			if (!BlackjackStorage.saveHand(player.getUsername(), playerHand.get(player), gameID)) {
				allSuccessful = false;
			}
			//System.out.println(itr.next());
		}
		
		return allSuccessful;
	}
 
	//how are we saving the hands if they're stored in the blackjacks
	public boolean saveGame(Deck deck, int gameID, Blackjack game){
		boolean isSuccessful = true;
		HashMap<Player,BlackjackHand> playerHand = game.getPlayerAndHands();
		
		if (!this.savePlayers() || !this.saveSpectators() ||
				!BlackjackStorage.saveDeck(deck, gameID) || !this.saveHands(playerHand, gameID)) {
			isSuccessful = false;
		}
		//this.saveSpectators();
		
		return isSuccessful;
		//return Storage.savePlayer(spectator);
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