import java.io.*;
import java.util.*;
import java.lang.String;

/**
 * A simple database interface
 * 
 * @author Gabriel
 *
 */
public class Storage {
	private String playerName;
	private int numWins;
	private int numLosses;
	private int numGames;
	private String line;
	private String[] fields;
	
	public void parse(String input){
		fields = input.split(",");
		if (fields[0].equals(playerName)) {
			numWins = Integer.parseInt(fields[1]);
			numLosses = Integer.parseInt(fields[2]);
			numGames = Integer.parseInt(fields[3]);
		}
	}
	
	public void getStats(String playername) throws IOException {
		Scanner reader = new Scanner(new File("database.csv"));
		playerName = playername;
		while (reader.hasNextLine()) {
			line = reader.nextLine();
			parse(line);
		}
		System.out.println(playerName + " scoreboard:");
		System.out.println("Number of games won: " + numWins);
		System.out.println("Number of games lost: " + numLosses);
		System.out.println("Percentage games won: " + (double)100*numWins/(double)(numWins + numLosses) + "%");
	}
	
}
