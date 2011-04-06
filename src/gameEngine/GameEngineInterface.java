package gameEngine;

import java.util.Scanner;
import participant.Player;
import gui.MainInterface;

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
				// spectators still not implemented?
			}
			if (menuChoice == SAVEGAME) {
				// still not implemented?
			}
			if (menuChoice == LOADGAME) {
				// still not implemented?
			}
			
		}
	}
	
	public void addPlayer() {
		Player newPlayer = MainInterface.login();
		gameEngine.addPlayer(newPlayer);
	}
}
