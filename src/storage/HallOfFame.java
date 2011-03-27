package storage;

import java.io.*;
import java.util.*;

public class HallOfFame {
	
	/**
	 * Finds the lowest scoring player among the top five
	 * @param topFive - An ArrayList<Storage> which contains the top five players
	 * @return
	 */
	private static Storage findLowestPlayer(ArrayList<Storage> topFive) {
		Storage lowestPlayer = topFive.get(0);
		int currentMinimum = 0;
		
		for (Storage player : topFive) {
			currentMinimum = player.getTotalChipsWon();
			if (currentMinimum < lowestPlayer.getTotalChipsWon()) {
				lowestPlayer = player;
			}
		}
		return lowestPlayer;
	}
	/**
	 * Compares a Storage object with the top five scoring players. If this new object has
	 * a better score than the lowest player in the top five, replaces this lowest player.
	 * @param playerToAdd - A Storage object to compare with the top five
	 * @param topFive - An ArrayList<Storage> which contains the top five players
	 * @return
	 */
	private static ArrayList<Storage> compareWithTopFive(Storage playerToAdd, ArrayList<Storage> topFive) {
		if (topFive.size() < 5) {
			topFive.add(playerToAdd);
		} else {
			Storage lowestPlayer = findLowestPlayer(topFive);
			if (playerToAdd.getTotalChipsWon() > lowestPlayer.getTotalChipsWon()) {
				topFive.remove(lowestPlayer);
				topFive.add(playerToAdd);
			}
		}
		return topFive;
	}
	
	/**
	 * Searches through all player profiles to find the top 5 totalChipsWon
	 * @return An ArrayList<Storage> which contains the five top scoring players
	 */
	public static ArrayList<Storage> getHallOfFame() {
		Scanner profileReader;

		File directory = new File("profiles");
		File profiles[] = directory.listFiles();
		ArrayList<Storage> topFive = new ArrayList<Storage>();
		
		for (File profile : profiles) {
			Storage player;
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
		
		return topFive;
	}
}
