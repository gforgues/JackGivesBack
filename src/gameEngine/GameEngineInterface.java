package gameEngine;

import java.util.Scanner;

import table.*;
import participant.Player;
import participant.Spectator;
import gui.MainInterface;
import storage.BlackjackStorage;

public class GameEngineInterface {
	GameEngine gameEngine;
	int menuChoice=-1;
	final int PLAYROUND = 1;
	final int ADDPLAYER = 2;
	final int ADDSPECTATOR = 3;
	final int SAVEGAME = 4;
	final int LOADGAME = 5;
	final int EXIT = 6;
	
	public GameEngineInterface(GameEngine ge) {
		gameEngine = ge;
		gameMenu();
	}
	public void gameMenu() {
		Scanner keyboard = new Scanner(System.in);
		
		while (menuChoice != EXIT) {
			System.out.println("-----Blackjack-----");
			System.out.println("1. Play a round");
			System.out.println("2. Add Player");
			System.out.println("3. Add Spectator");
			System.out.println("4. Save Game");
			System.out.println("5. Load Game");
			System.out.println("6. Exit");
			menuChoice = keyboard.nextInt();
			keyboard.nextLine();
		
			if (menuChoice == PLAYROUND) {
				gameEngine.gameStart();
			}
			if (menuChoice == ADDPLAYER) {
				addPlayer();
			}
			if (menuChoice == ADDSPECTATOR) {
				addSpectator();
			}
			if (menuChoice == SAVEGAME) {
				System.out.println("Choose a save slot number (1-9) :");
				int saveNumber = keyboard.nextInt();
				keyboard.nextLine();
				try {
					gameEngine.getTable().saveGame(gameEngine.getDeck(), saveNumber);
					System.out.println("Save successful.");
				} catch (Exception e) {
					System.out.println("Save unsuccessful.");
				} 
			}
			if (menuChoice == LOADGAME) {
				System.out.println("Choose a save slot number to load(1-9) :");
				int saveNumber = keyboard.nextInt();
				keyboard.nextLine();
				try {
					for (String username : BlackjackStorage.loadPlayerNames(saveNumber)) {
						if (!username.equals(gameEngine.getTable().getTableOwner().getUsername())) {
							addPlayer(username);
						}
					}
					System.out.println("Load successful");
				} catch (Exception e) {
					System.out.println(e);
					System.out.println("Could not load game");
				}
			}
			
		}
	}
	
	public void addPlayer() {
		Player newPlayer = MainInterface.login();
		gameEngine.addPlayer(newPlayer);
	}
	
	public void addPlayer(String username) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter " + username + "'s password: ");
		String password = keyboard.nextLine();
		try {
			Player playerToAdd = new Player(username, password);
			gameEngine.addPlayer(playerToAdd);
		} catch (Exception e) {
			System.out.println("Could not add " + username);
		}
	}
	
	public void addSpectator() {
		Player newPlayer = MainInterface.login();
		System.out.println(newPlayer.getUsername() + " is now a Spectator");
	}
}
