package storage;

import java.io.*;
import java.util.*;

/**
 * Hall Of Fame searches through player profiles and return the top five scoring players (in terms of total chips won)
 * @author Gabriel
 */
public class HallOfFame {
	
	/**
	 * Sorts the players from highest to lowest in terms of total chips won
	 * @param topFive An ArrayList<Statistics> which contains the top five players
	 * @return A sorted ArrayList<Statistics> with the top five players
	 */
	private static ArrayList<Statistics> sort(ArrayList<Statistics> topFive) {
		ArrayList<Statistics> sortedTopFive = new ArrayList<Statistics>();
		while (!topFive.isEmpty()) {
			Statistics lowestPlayer = findLowestPlayer(topFive);
			sortedTopFive.add(0,lowestPlayer);
			topFive.remove(lowestPlayer);
		}
		return sortedTopFive;
	}
	/**
	 * Finds the lowest scoring player among the top five
	 * @param topFive - An ArrayList<Statistics> which contains the top five players
	 * @return A Storage object of the lowest player currently in the top five
	 */
	private static Statistics findLowestPlayer(ArrayList<Statistics> topFive) {
		Statistics lowestPlayer = topFive.get(0);
		int currentMinimum = 0;
		
		for (Statistics player : topFive) {
			currentMinimum = player.getTotalChipsWon();
			if (currentMinimum < lowestPlayer.getTotalChipsWon()) {
				lowestPlayer = player;
			}
		}
		return lowestPlayer;
	}
	/**
	 * Compares a Statistics object with the top five scoring players in terms of total chips won. If this new object has
	 * a better score than the lowest player in the top five, replaces this lowest player.
	 * @param playerToAdd - A Statistics object to compare with the top five
	 * @param topFive - An ArrayList<Statistics> which contains the top five players
	 * @return An ArrayList<Statistics> of the updated list of top five players
	 */
	private static ArrayList<Statistics> compareWithTopFive(Statistics playerToAdd, ArrayList<Statistics> topFive) {
		if (topFive.size() < 5) {
			topFive.add(playerToAdd);
		} else {
			Statistics lowestPlayer = findLowestPlayer(topFive);
			if (playerToAdd.getTotalChipsWon() > lowestPlayer.getTotalChipsWon()) {
				topFive.remove(lowestPlayer);
				topFive.add(playerToAdd);
			}
		}
		return topFive;
	}
	
	/**
	 * Searches through all player profiles to find the top 5 totalChipsWon
	 * @return An ArrayList<Statistics> which contains the five top scoring players
	 */
	public static ArrayList<Statistics> getHallOfFame() {
		Scanner profileReader;

		File directory = new File("profiles");
		File profiles[] = directory.listFiles();
		ArrayList<Statistics> topFive = new ArrayList<Statistics>();
		
		for (File profile : profiles) {
			Statistics player;
			String username = profile.getName();
			try {
				profileReader = new Scanner(new File("profiles/" + username));
				String parsedLine[] = profileReader.nextLine().split(",");
				String password = parsedLine[0];
				username = username.substring(0, username.lastIndexOf('.'));
				player = Storage.loadPlayer(username, password);
			} catch (FileNotFoundException e) {
				continue;
			}
			topFive = compareWithTopFive(player, topFive);
		}
		topFive = sort(topFive);
		return topFive;
	}
	
	/**
	 * Prints the Hall of fame's contents to the command line.
	 */
	public static void display() {
		System.out.println("Hall of Fame (Total Chips Won):");
		for (Statistics player : HallOfFame.getHallOfFame()) {
			System.out.println(player.getUsername() + ": " + player.getTotalChipsWon());
		}
	}
}
