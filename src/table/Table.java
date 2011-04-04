package table;

import storage.*;
import cards.*;
import participant.*;
import game.Blackjack;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Table {
	Player tableOwner;
	ArrayList<Player> players;
	ArrayList<Spectator> spectators;

	public Table(Player owner) {
		this.tableOwner = owner;
		players = new ArrayList<Player>();
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

	public void requestJoin(Player player){
		boolean join;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println(this.tableOwner.getUsername() + " can player " + player.getUsername() +
			" join the game? Enter true or false");
		join = keyboard.nextBoolean();
   
		if(join==true){
			System.out.println("Request join accepted");
			//Add player to array list of players
			players.add(player);
		} else {
			System.out.println("Request join rejected");
		}
	} 

	public void requestLeave(Player player) {
		boolean leave;
		Scanner keyboard = new Scanner(System.in);
   
		System.out.println(this.tableOwner.getUsername() + " can player " + player.getUsername() +
				" leave the game? Enter true or false");
		leave = keyboard.nextBoolean();
		
		if(leave){
			System.out.println("Request leave accepted");
			players.remove(this.findIndex(player));
		} else {
			System.out.println("Request leave rejected");
        }
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
 
	public void requestView(Spectator spectator) {
		boolean view;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println(this.tableOwner.getUsername() + " can spectator " + spectator.getUsername() +
			" join the game? Enter true or false");
		view = keyboard.nextBoolean();
		
		if(view==true){
			System.out.println("TableOwner accepts spectator");
			//Add spectator to arraylist of spectators
			spectators.add(spectator);
		} else {
			System.out.println("TableOwner rejects spectator");
		}
	}
 
	public void requestLeave(Spectator spectator) {
		boolean leave;
	    Scanner keyboard = new Scanner(System.in); 
		
	    System.out.println(this.tableOwner.getUsername() + " can spectator " + spectator.getName() +
			" leave the game? Enter true or false");
	    leave = keyboard.nextBoolean();
		
		if(leave==true){
			System.out.println("TableOwner accepts spectator from leaving the table");
			//remove spectator from list of spectators
			spectators.remove(this.findIndex(spectator));
		} else {
			System.out.println("TableOwner rejects spectator from leaving the table");
		}
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
	public boolean saveHands(HashMap<Player,BlackjackHand> playerHand) {
		
		return false;
	}
 
	//how are we saving the hands if they're stored in the blackjacks
	public boolean saveGame(Deck deck, int gameID, Blackjack game){
		boolean isSuccessful = false;
		HashMap<Player,BlackjackHand> playerHand = game.getPlayerAndHands();
		
		if (this.savePlayers() && this.saveSpectators() && 
				BlackjackStorage.saveDeck(deck, gameID) && this.saveHands(playerHand)) {
			isSuccessful = true;
		}
		//this.saveSpectators();
		
		return isSuccessful;
		//return Storage.savePlayer(spectator);
	}
}