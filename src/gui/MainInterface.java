package gui;
import java.util.Scanner;
import gameEngine.*;
import participant.*;
import storage.*;

public class MainInterface
{
	public static void main(String[] args) {
		new MainInterface();
	}
	MainInterface() {
		
		int menuChoice=-1;
		final int PLAYGAME = 1;
		final int MODIFYPROFILE = 2;
		final int VIEWSTATISTICS = 3;
		final int HALLOFFAME = 4;
		final int SWITCHUSER = 5;
		final int EXIT = 6;
		
		Scanner keyboard = new Scanner(System.in);
		Player player = login();
		while (menuChoice != EXIT) {
			System.out.println("------Main Menu------");
			System.out.println("Logged in as : " + player.getUsername());
			System.out.println("1. Play Blackjack");
			System.out.println("2. Modify Profile");
			System.out.println("3. View Statistics");
			System.out.println("4. Show Hall of Fame");
			System.out.println("5. Switch user");
			System.out.println("6. Exit");
			menuChoice = keyboard.nextInt();
			keyboard.nextLine();
			
			if (menuChoice == PLAYGAME) {
				GameEngine gameEngine = new GameEngine(player);
				new GameEngineInterface(gameEngine);
			}
			if (menuChoice == MODIFYPROFILE) {
				modifyProfile(player);
			}
			if (menuChoice == VIEWSTATISTICS) {
				viewStats(player);
			}
			if (menuChoice == HALLOFFAME) {
				HallOfFame.display();
			}
			if (menuChoice == SWITCHUSER) {
				player = login();
			}
		}
		
	}
	
	public static Player login() {
		Scanner keyboard = new Scanner(System.in);
		String username = "";
		String password = "";
		boolean loggedIn = false;
		System.out.println("-----Login-----");
		while (!loggedIn) {
			System.out.println("Enter username: ");
			username = keyboard.nextLine();
			System.out.println("Enter password: ");
			password = keyboard.nextLine();
			System.out.println(password);
			try {
				new Player(username, password);
				loggedIn = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e);
			}
		}
		return new Player(username, password);
	}
	
	public static void modifyProfile(Player player) {
		int menuChoice=-1;
		final int EXIT = 4;
		final int CHANGEPASS = 1;
		final int CHANGENAME = 2;
		final int CHANGEAGE = 3;
		
		Scanner keyboard = new Scanner(System.in);
		while (menuChoice != EXIT) {
			System.out.println("------Modify Profile------");
			System.out.println("1. Change Password");
			System.out.println("2. Change real name");
			System.out.println("3. Change age");
			System.out.println("4. Back to main menu");
			menuChoice = keyboard.nextInt();
			keyboard.nextLine();
			if (menuChoice == CHANGEPASS) {
				System.out.println("Enter your old password: ");
				String oldPass = keyboard.nextLine();
				System.out.println("Enter your new password: ");
				String newPass = keyboard.nextLine();
				if (player.toStatistics().validatePassword(oldPass)) {
					player.toStatistics().changePassword(newPass);
					Storage.savePlayer(player.toStatistics());
				} else {
					System.out.println("Password was invalid.");
				}
			}
			if (menuChoice == CHANGENAME) {
				System.out.println("Enter your real name: ");
				String name = keyboard.nextLine();
				player.toStatistics().setRealName(name);
				Storage.savePlayer(player.toStatistics());
			}
			if (menuChoice == CHANGEAGE) {
				System.out.println("Enter your age: ");
				int age = keyboard.nextInt();
				keyboard.nextLine();
				player.toStatistics().setAge(age);
				Storage.savePlayer(player.toStatistics());
			}
		}
	}
	public static void viewStats(Player player) {
		System.out.print(player);
	}
}
