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
	private String username;
	private String password;
	private String realName;
	private int age;
	private int numChips;
	private int totalChipsWon;
	private int winStreak;
	private int maxWinStreak;
	private int chipsWinStreak;
	private int maxChipsWinStreak;
	private int lossStreak;
	private int maxLossStreak;
	private int chipsLossStreak;
	private int maxChipsLossStreak;

	// Constructors
	Storage(String username, String password) {
		this(username, password, "noName", -1, 0, 0, 0, 0, 0, 0);
	}	
	Storage(String username, String password, String realName, int age) {
		this(username, password, realName, age, 0, 0, 0, 0, 0, 0);
	}
	Storage(String username, String password,	String realName, int age,
			int numChips, int totalMoneyWon, int maxWinStreak,
			int maxChipsWinStreak, int maxLossStreak, int maxChipsLossStreak){
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.age = age;
		this.numChips = numChips;
		this.totalChipsWon = totalMoneyWon;
		this.maxWinStreak = maxWinStreak;
		this.maxChipsWinStreak = maxChipsWinStreak;
		this.maxLossStreak = maxLossStreak;
		this.maxChipsLossStreak = maxChipsLossStreak;
		this.winStreak = 0;
		this.chipsWinStreak = 0;
		this.lossStreak = 0;
		this.chipsLossStreak = 0;
	}
	
	/**
	 * Creates a new profile file if the input username is not already taken
	 * @param username
	 * @param password
	 */
	private static void addNewPlayer(String username, String password) {
		File fileName = new File("profiles/" + username + ".csv");
		if (fileName.isFile()) {
			System.out.println("Player already exists.");
		} else {
			Storage newPlayer = new Storage(username,password);
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
		String[] parsedLine;
		try {
			 Scanner reader = new Scanner(new File("profiles/" + inputName + ".csv"));
			 
			 // Split line from file into different fields
			 parsedLine = reader.nextLine().split(",");
			 
			 // load variables from the parsed line
			 String password = parsedLine[0];
			 String realName = parsedLine[1];
			 int age = Integer.parseInt(parsedLine[2]);
			 int numChips = Integer.parseInt(parsedLine[3]);
			 int totalMoneyWon = Integer.parseInt(parsedLine[4]);
			 int maxWinStreak = Integer.parseInt(parsedLine[5]);
			 int maxChipsWinStreak = Integer.parseInt(parsedLine[6]);
			 int maxLossStreak = Integer.parseInt(parsedLine[7]);
			 int maxChipsLossStreak = Integer.parseInt(parsedLine[8]);
			 
			 // Instantiate player with the loaded variables
			 Storage loadedPlayer = new Storage(inputName, password,
					 							realName, age,
					 							numChips, totalMoneyWon,
					 							maxWinStreak,
					 							maxChipsWinStreak,
					 							maxLossStreak,
					 							maxChipsLossStreak);
			reader.close();

			 if (loadedPlayer.validatePassword(inputPassword)) {
				 return loadedPlayer;
			 } else {
				 System.out.println("Invalid Password");
				 return null;
			 }
		} catch (FileNotFoundException e){
			addNewPlayer(inputName, inputPassword);
			return loadPlayer(inputName, inputPassword);
		}
	}
	
	/**
	 * Writes a Storage object's information back to the profile file
	 * @return true if it saved successfully, false otherwise
	 */
	public boolean savePlayer() {
		try {
			FileWriter fstream = new FileWriter("profiles/" + username + ".csv");
			BufferedWriter writer = new BufferedWriter(fstream);

			writer.write(password + ","	+ realName + ","+ age + ","
					+ numChips + "," + totalChipsWon + ","
					+ maxWinStreak + "," + maxChipsWinStreak + ","
					+ maxLossStreak + ","+ maxChipsLossStreak);
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
	public String getUsername() {
		return username;
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
	public int getTotalChipsWon() {
		return totalChipsWon;
	}
	
	public int getMaxWinStreak() {
		return maxWinStreak;
	}
	public int getMaxChipsWinStreak() {
		return maxChipsWinStreak;
	}
	public void addWin(int betValue) {
		winStreak++;
		
		numChips += betValue;
		chipsWinStreak += betValue;
		totalChipsWon += betValue;
		
		if (winStreak > maxWinStreak){
			maxWinStreak = winStreak;
		}
		if (chipsWinStreak > maxChipsWinStreak) {
			maxChipsWinStreak = chipsWinStreak;
		}
		lossStreak = 0;
		chipsLossStreak = 0;
	}
	
	public void addLoss(int betValue) {
		lossStreak++;
		
		numChips -= betValue;
		chipsLossStreak += betValue;
		
		if (lossStreak > maxLossStreak){
			maxLossStreak = lossStreak;
		}
		if (chipsLossStreak > maxChipsLossStreak) {
			maxChipsLossStreak = chipsLossStreak;
		}
		winStreak = 0;
		chipsWinStreak = 0;
	}
	
	public int getMaxLossStreak() {
		return maxLossStreak;
	}
}