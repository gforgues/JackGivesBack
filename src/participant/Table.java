package participant;

import game.Observer;

import java.util.ArrayList;

public class Table implements Observer {
	Player tableOwner;
	ArrayList<Player> players = new ArrayList<Player>();

	public Table(Player owner) {
		this.tableOwner = owner;
		players.add(owner);
	}
	
	public Player getTableOwner() {
		return tableOwner;
	}
	
	public ArrayList<Player> getAllPlayers() {
		return players;
	}
	
	public Player getPlayer(int index) {
		return players.get(index);
	}
	
//	public Player getPlayer(Player player) {
//		return players.
//	}
	
	public static void closeGame() {
		System.out.println("Game has been closed");
	}
	
	//returns false for now...
	public static void requestJoin(Player player) {
		System.out.println("Table must accept or reject this player from the game");
	}
	
	public static void requestLeave(Player player) {
		System.out.println("Table must release the player from the game");
	}
	
	public static void saveGame(Player player) {
		System.out.println("The game has been saved for this specific player");
	}
	
	public static void requestView(Spectator spectator) {
		System.out.println("TableOwner accepts or rejects spectator from game");
	}
	
	public static void requestLeave(Spectator spectator) {
		System.out.println("TableOwner accepts or rejects spectator from leaving the table");
	}
	
	public static void saveGame(Spectator spectator) {
		System.out.println("Game has been saved by this spectator");
	}

	@Override
	public void handleEvent()
	{
		// TODO Auto-generated method stub
		
	}
	
	
}