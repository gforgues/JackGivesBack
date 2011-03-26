package Storage;

import java.io.*;
import java.util.*;

public class HallOfFame {
	
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
	
	public static void getHallOfFame() {
		Scanner profileReader;
		String username;
		String password;
		String parsedLine[];
		File directory = new File("profiles");
		File profiles[] = directory.listFiles();
		ArrayList<Storage> topFive = new ArrayList<Storage>();
		
		for (File profile : profiles) {
			Storage player;
			username = profile.getName();
			try {
				profileReader = new Scanner(new File("profiles/" + username));
				parsedLine = profileReader.nextLine().split(",");
				password = parsedLine[0];
				username = username.substring(0, username.lastIndexOf('.'));
				player = Storage.loadPlayer(username, password);
			} catch (FileNotFoundException e) {
				continue;
			}
			topFive = compareWithTopFive(player, topFive);
		}
		
		for (Storage topPlayer : topFive) {
			System.out.print(topPlayer.getUsername() + ": ");
			System.out.println(topPlayer.getTotalChipsWon());
		}
	}
}
