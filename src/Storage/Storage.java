package Storage;

import java.io.*;
import java.util.*;
import java.lang.String;

/**
 * The Storage class reads and writes to the database file
 *  to store player information
 *  
 *  realName is an optional attribute and has default value "noName"
 *  age is an optional attribute and has default value -1
 * 
 * @author Gabriel
 *
 */


public class Storage implements StorageInterface{
	
	private String playerName;
	private String password;
	private String realName;
	private int age;
	private int numChips;
	private int numWins;
	private int numLosses;
	private int numGames;
	private ArrayList<Storage> database = new ArrayList();

	// Empty constructor
	Storage() {
	}
	
	// Constructor
	Storage(String line) {
		String[] fields;
		fields = line.split(",");
		
		this.playerName = fields[0];
		this.password = fields[1];
		this.realName = fields[2];
		this.age = Integer.parseInt(fields[3]);
		this.numChips = Integer.parseInt(fields[4]);
		this.numWins = Integer.parseInt(fields[5]);
		this.numLosses = Integer.parseInt(fields[6]);
		this.numGames = numWins + numLosses;	
	}
	
	private void loadDatabase() throws IOException {
		Storage storedPlayer;
		Scanner reader = new Scanner(new File("database.csv"));
		String line;
		
		while (reader.hasNextLine()) {
			line = reader.nextLine();
			storedPlayer = new Storage(line);
			database.add(storedPlayer);
		}
		reader.close();
	}
	
	private void saveDatabase() throws IOException {
		FileWriter fstream = new FileWriter("database.csv");
		BufferedWriter writer = new BufferedWriter(fstream);
		
		for ( Storage player : database ) {
			writer.write(player.playerName + ","
					+ player.password + ","
					+ player.realName + ","
					+ player.age + ","
					+ player.numChips + ","
					+ player.numWins + ","
					+ player.numLosses);
			writer.write("\r\n");
		}
		writer.close();
	}
	
	public void setPassword(String inputPlayerName, String inputPassword) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				player.password = inputPassword;
			}
		}
		saveDatabase();
	}
	
	public void setPlayerName(String inputPlayerName, String inputNewPlayerName) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				player.playerName = inputNewPlayerName;
			}
		}
		saveDatabase();
	}
	
	public void setRealName(String inputPlayerName, String inputRealName) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				player.realName = inputRealName;
			}
		}
		saveDatabase();
	}
	
	public String getRealName(String inputPlayerName) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				return player.realName;
			}
		}
		return "Player Not Found";
	}
	
	public void setAge(String inputPlayerName, int inputAge) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				player.age = inputAge;
			}
		}
		saveDatabase();
	}
	
	public int getAge(String inputPlayerName) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				return player.age;
			}
		}
		return -1;
	}
	
	public void setNumChips(String inputPlayerName, int betValue) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				player.numChips += betValue;
			}
		}
		saveDatabase();
	}
	
	public int getNumChips(String inputPlayerName) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				return player.numChips++;
			}
		}
		return -1;
	}
	
	public void setWin(String inputPlayerName) throws IOException {
		loadDatabase();		
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				player.numWins++;
			}
		}
		saveDatabase();
	}
	
	public int getNumWins(String inputPlayerName) throws IOException {
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				return player.numWins++;
			}
		}
		return -1;
	}
	
	public void setLoss(String inputPlayerName) throws IOException {
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				player.numLosses++;
			}
		}
		saveDatabase();
	}
	
	public int getNumLosses(String inputPlayerName) throws IOException {
		loadDatabase();
		for ( Storage player : database ) {
			if (player.playerName.equals(inputPlayerName)) {
				return player.numLosses++;
			}
		}
		return -1;
	}
	/*
	public void getStats(String inputPlayerName) throws IOException {
		loadDatabase();
		System.out.println(playerName + " scoreboard:");
		if (!realName.equals("noName")) {
			System.out.println("Real Name: " + realName);
		}
		if (age != -1) {
			System.out.println("Age: " + age);
		}
		System.out.println("Current chip count: " + numChips);
		System.out.println("Number of games won: " + numWins);
		System.out.println("Number of games lost: " + numLosses);
		System.out.println("Percentage games won: " + (double)100*numWins/(double)(numGames) + "%");
	}
	*/
	
	public boolean validatePassword(String inputPlayerName, String inputPassword) throws IOException{
		loadDatabase();
		for ( Storage player : database ) {
			if (inputPlayerName.equals(player.playerName) &&
				inputPassword.equals(player.password)) {
				return true;
			}
		}
		return false;
	}	
}
