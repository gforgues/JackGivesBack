import java.io.*;
import java.util.*;
import java.lang.String;

/**
 * The Storage class reads and writes to the database file
 *  to store player information
 * 
 * @author Gabriel
 *
 */
public class Storage{
	private String playerName;
	private String password;
	private String realName;
	private int age;
	private int numChips;
	private int numWins;
	private int numLosses;
	private int numGames;
	private String line;
	private String[] fields;

	private void loadPlayer(String playername) throws IOException {
		Scanner reader = new Scanner(new File("database.csv"));
		playerName = playername;
		while (reader.hasNextLine()) {
			line = reader.nextLine();
			parseLine(line);
		}
	}
	
	private void parseLine(String input){
		fields = input.split(",");
		if (fields[0].equals(playerName)) {
			password = fields[1];
			realName = fields[2];
			age = Integer.parseInt(fields[3]);
			numChips = Integer.parseInt(fields[4]);
			numWins = Integer.parseInt(fields[5]);
			numLosses = Integer.parseInt(fields[6]);
			numGames = numWins + numLosses;
		}
	}
	
	public void saveNumChips(String playername, int betValue) throws IOException{
		loadPlayer(playername);
		numChips += betValue;
	}
	
	public int getNumChips(String playername) throws IOException{
		loadPlayer(playername);
		return numChips;
	}
	
	public void saveWin(String playername) throws IOException {
		loadPlayer(playername);
		numWins++;
	}
	
	public int getNumWins(String playername) throws IOException {
		loadPlayer(playername);
		return numWins;
	}
	
	public void saveLoss(String playername) throws IOException {
		loadPlayer(playername);
		numLosses++;
	}
	
	public int getNumLosses(String playername) throws IOException {
		loadPlayer(playername);
		return numLosses;
	}
	
	public void getStats(String playername) throws IOException {
		loadPlayer(playername);
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
	
	public boolean validatePassword(String playername, String inputPassword) throws IOException{
		loadPlayer(playername);
		if (inputPassword.equals(password)) {
			return true;
		}
		return false;
	}
	
}
