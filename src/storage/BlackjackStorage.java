package storage;
import java.io.*;
import java.util.Scanner;

import cards.*;

public class BlackjackStorage {
	
	public static boolean saveDeck(Deck deck, int gameID) {
		try {
			FileWriter fstream = new FileWriter("/saves/" + gameID);
			BufferedWriter writer = new BufferedWriter(fstream);
			
			writer.write("Deck,");
			while (deck.size() > 0) {
				Card card = deck.draw();
				writer.write(card + ":");
			}
			writer.write("\r\n");
			return true;
			
		} catch (IOException e) {
			return false;
		}
	}
	
	public static boolean saveHand(String username, Hand hand, int gameID) {
		try {
			FileWriter fstream = new FileWriter("/saves/" + gameID);
			BufferedWriter writer = new BufferedWriter(fstream);
			
			writer.write(username + ",");
			while (hand.getNumberCards() > 0) {
				Card card = hand.removeCard(0);
				writer.write(card.toCompactString() + ":");
			}
			writer.write("\r\n");
			return true;	
			
		} catch (IOException e) {
			return false;
		}
	}
	public static Hand loadHand(String username, int gameID) {
		Hand hand = new Hand();
		try {
			Scanner reader = new Scanner(new File("/saves/" + gameID));
			while (reader.hasNextLine()) {
				String[] parsedLine = reader.nextLine().split(",");
				if (parsedLine[0].equals(username)) {
					String[] cardsList = parsedLine[1].split(":");
					for (String card : cardsList) {
						//hand.addCard(card);
					}
				}
			}
			return hand;
		} catch (IOException e) {
			System.out.println("Could not load hand");
			return hand;
		} 
	}
	public static Deck loadDeck(int gameID) {
		Deck deck = new Deck();
		try {
			Scanner reader = new Scanner(new File("/saves/" + gameID));

			String[] parsedLine = reader.nextLine().split(",");
			String[] cards = parsedLine[1].split(":");
			for (String card : cards) {
				// deck.addCard(card);
			}
			return deck;
		} catch (IOException e){
			return deck;
		}
	}
}
