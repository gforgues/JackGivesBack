package Storage;

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

public class Storage implements StorageInterface {
	private String playerName;
	private String password;
	private String realName;
	private int age;
	private int numChips;
	private int numWins;
	private int numLosses;
	private int numGames;

	// Constructors
	Storage(String playerName, String password) {
		this(playerName, password, "noName", -1, 0, 0, 0);
	}	
	Storage(String playerName, String password, String realName, int age) {
		this(playerName, password, realName, age, 0, 0, 0);
	}
	Storage(String playerName, String password, String realName,
			int age, int numChips, int numWins, int numLosses) {
		this.playerName = playerName;
		this.password = password;
		this.realName = realName;
		this.age = age;
		this.numChips = numChips;
		this.numWins = numWins;
		this.numLosses = numLosses;
		this.numGames = numWins + numLosses;	
	}
	
	/**
	 * Creates a new profile file the input playerName is not already taken
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
			 String[] parsedLine;
			 parsedLine = reader.nextLine().split(",");
			 reader.close();
			 
			 // Store information from the parsed line
			 String password = parsedLine[0];
			 String realName = parsedLine[1];
			 int age = Integer.parseInt(parsedLine[2]);
			 int numChips = Integer.parseInt(parsedLine[3]);
			 int numWins = Integer.parseInt(parsedLine[4]);
			 int numLosses = Integer.parseInt(parsedLine[5]);
			 Storage storedPlayer = new Storage(inputName, password, realName,
					 age, numChips, numWins, numLosses);
			 
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
			FileWriter fstream = new FileWriter("profiles/" + this.playerName + ".csv");
			BufferedWriter writer = new BufferedWriter(fstream);

			writer.write(this.password + ","
					+ this.realName + ","
					+ this.age + ","
					+ this.numChips + ","
					+ this.numWins + ","
					+ this.numLosses);
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
		return this.realName;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return this.age;
	}
	public void addChips(int betValue) {
		if (this.numChips + betValue > 0) {
			this.numChips += betValue;
		}
		else {
			this.numChips = 0;
		}
	}
	public int getChips() {
		return this.numChips;
	}
	public void addWin() {
		this.numWins++;
	}
	public int getNumWins() {
		return this.numWins;
	}
	public void addLoss() {
		this.numLosses++;
	}
	public int getNumLosses() {
		return this.numLosses;
	}
	public int getNumGames() {
		return this.numGames;
	}
}
