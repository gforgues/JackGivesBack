package storage;

import java.io.*;
import java.util.Scanner;

/**
 * Reads and writes to unique player profile files to save data
 * 
 *  realName is an optional attribute and has default value "noName"
 *  age is an optional attribute and has default value -1
 *  
 * @author Gabriel
 */

public class Storage {
	private String playerName;
	private String password;
	private String realName;
	private int age;
	private int numChips;
	private int totalMoneyWon;
	private int gamesWonStreak;
	private int biggestGamesWonStreak;
	private int chipsWonStreak;
	private int biggestChipsWonStreak;
	private int gamesLostStreak;
	private int biggestGamesLostStreak;
	private int chipsLostStreak;
	private int biggestChipsLostStreak;
	private int numWins;
	private int numLosses;
	private int numGames;

	// Constructors
	Storage(String playerName, String password) {
		this(playerName, password, "noName", -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}	
	Storage(String playerName, String password, String realName, int age) {
		this(playerName, password, realName, age, 0, 0, 0, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 0);
	}
	Storage(String playerName, String password,
			String realName, int age, int numChips, int totalMoneyWon,
			int gamesWonStreak, int biggestGamesWonStreak,
			int chipsWonStreak, int biggestChipsWonStreak,
			int gamesLostStreak, int biggestGamesLostStreak,
			int chipsLostStreak, int biggestChipsLostStreak,
			int numWins, int numLosses) {
		this.playerName = playerName;
		this.password = password;
		this.realName = realName;
		this.age = age;
		this.numChips = numChips;
		this.totalMoneyWon = totalMoneyWon;
		this.gamesWonStreak = gamesWonStreak;
		this.biggestGamesWonStreak = biggestGamesWonStreak;
		this.chipsWonStreak = chipsWonStreak;
		this.biggestChipsWonStreak = biggestChipsWonStreak;
		this.gamesLostStreak = gamesLostStreak;
		this.biggestGamesLostStreak = biggestGamesLostStreak;
		this.chipsLostStreak = chipsLostStreak;
		this.biggestChipsLostStreak = biggestChipsLostStreak;
		this.numWins = numWins;
		this.numLosses = numLosses;
		this.numGames = numWins + numLosses;	
	}
	
	/**
	 * Creates a new profile file if the input playerName is not already taken
	 * @param playerName
	 * @param password
	 */
	public static void addNewPlayer(String playerName, String password) {
		File fileName = new File("profiles/" + playerName + ".csv");
		if (fileName.isFile()) {
			System.out.println("Player already exists.");
		} else {
			Storage newPlayer = new Storage(playerName,password);
			newPlayer.savePlayer();
		}
	}
	
	/**
	 * Loads a player's profile if its name and password are valid
	 * @param inputName
	 * @param inputPassword
	 * @return a Storage object containing a player's statistics
	 */
	public static Storage loadPlayer(String inputName, String inputPassword) {
		try {
			 Scanner reader = new Scanner(new File("profiles/" + inputName + ".csv"));
			 
			 // Split line from file into different fields
			 String[] parsedLine;
			 parsedLine = reader.nextLine().split(",");
			 
			 // load variables from the parsed line
			 String password = parsedLine[0];
			 String realName = parsedLine[1];
			 int age = Integer.parseInt(parsedLine[2]);
			 int numChips = Integer.parseInt(parsedLine[3]);
			 int totalMoneyWon = Integer.parseInt(parsedLine[4]);
			 int gamesWonStreak = Integer.parseInt(parsedLine[5]);
			 int biggestGamesWonStreak = Integer.parseInt(parsedLine[6]);
			 int chipsWonStreak = Integer.parseInt(parsedLine[7]);
			 int biggestChipsWonStreak = Integer.parseInt(parsedLine[8]);
			 int gamesLostStreak = Integer.parseInt(parsedLine[9]);
			 int biggestGamesLostStreak = Integer.parseInt(parsedLine[10]);
			 int chipsLostStreak = Integer.parseInt(parsedLine[11]);
			 int biggestChipsLostStreak = Integer.parseInt(parsedLine[12]);
			 int numWins = Integer.parseInt(parsedLine[13]);
			 int numLosses = Integer.parseInt(parsedLine[14]);
			 
			 // Instantiate player with the loaded variables
			 Storage storedPlayer = new Storage(inputName, password,
					 							realName, age,
					 							numChips, totalMoneyWon,
					 							gamesWonStreak,	biggestGamesWonStreak,
					 							chipsWonStreak,	biggestChipsWonStreak,
					 							gamesLostStreak, biggestGamesLostStreak,
					 							chipsLostStreak, biggestChipsLostStreak,
					 							numWins, numLosses);
			 
			reader.close();

			 if (storedPlayer.validatePassword(inputPassword)) {
				 return storedPlayer;
			 } else {
				 System.out.println("Invalid Password");
				 return null;
			 }
		} catch (FileNotFoundException e){
			System.out.println("Player does not exist");
			return null;
		}
	}
	
	/**
	 * Writes a Storage object's information back to the profile file
	 * @return true if it saved succesfully, false otherwise
	 */
	public boolean savePlayer() {
		try {
			FileWriter fstream = new FileWriter("profiles/" + playerName + ".csv");
			BufferedWriter writer = new BufferedWriter(fstream);

			writer.write(password + ","	+ realName + ","+ age + ","
					+ numChips + "," + totalMoneyWon + ","
					+ gamesWonStreak + "," + biggestGamesWonStreak + ","
					+ chipsWonStreak + "," + biggestChipsWonStreak + ","
					+ gamesLostStreak + ","	+ biggestGamesLostStreak + ","
					+ chipsLostStreak + ","	+ biggestChipsLostStreak +","
					+ numWins + ","	+ numLosses);
			
			writer.close();
			return true;
		} catch (IOException e) {
			System.out.println("Could not save player");
			return false;
		}
	}
	
	/**
	 * Checks if the input password matches the player's profile password
	 * @param inputPassword
	 * @return true if the password is valid, false otherwise
	 */
	public boolean validatePassword(String inputPassword){
		if (inputPassword.equals(this.password)) {
			return true;
		}
		return false;
	}	
	public void changePassword(String password) {
		this.password = password;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRealName() {
		return realName;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setChips(int numChips) {
		this.numChips = numChips;
	}
	public int getChips() {
		return numChips;
	}
	public int getBiggestGamesWonStreak() {
		return biggestGamesWonStreak;
	}
	public int getBiggestChipsWonStreak() {
		return biggestChipsWonStreak;
	}
	public void addWin(int betValue) {
		numWins++;
		numChips += betValue;
		totalMoneyWon++;
		gamesWonStreak++;
		chipsWonStreak += betValue;
		if (gamesWonStreak > biggestGamesWonStreak){
			biggestGamesWonStreak = gamesWonStreak;
		}
		if (chipsWonStreak > biggestChipsWonStreak) {
			biggestChipsWonStreak = chipsWonStreak;
		}
		gamesLostStreak = 0;
		chipsLostStreak = 0;
	}
	public int getNumWins() {
		return numWins;
	}
	public void addLoss(int betValue) {
		numLosses++;
		numChips -= betValue;
		gamesLostStreak++;
		chipsLostStreak += betValue;
		if (gamesLostStreak > biggestGamesLostStreak){
			biggestGamesLostStreak = gamesLostStreak;
		}
		if (chipsLostStreak > biggestChipsLostStreak) {
			biggestChipsLostStreak = chipsLostStreak;
		}
		gamesWonStreak = 0;
		chipsWonStreak = 0;
	}
	public int getNumLosses() {
		return numLosses;
	}
	public int getNumGames() {
		return numGames;
	}
}
