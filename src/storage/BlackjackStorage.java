package storage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import cards.*;

public class BlackjackStorage {
	
	public static boolean saveDeck(Deck deck, int gameID) {
		try {
			FileWriter fstream = new FileWriter("saves/" + gameID + ".csv");
			BufferedWriter writer = new BufferedWriter(fstream);
			
			writer.write("Deck,");
			while (deck.size() > 1) {
				Card card = deck.draw();
				writer.write(card.toString() + ":");
			}
			writer.write(deck.draw().toString());
			writer.write("\r\n");
			writer.close();
			return true;
			
		} catch (IOException e) {
			return false;
		}
	}
	
	public static ArrayList<String> loadPlayerNames(int gameId) {
		ArrayList<String> string = new ArrayList<String>();
		try {
			Scanner reader = new Scanner(new File("saves/" + gameId + ".csv"));
			while (reader.hasNextLine()) {
				String[] parsedLine = reader.nextLine().split(",");
				string.add(parsedLine[0]);
			}
			reader.close();
			return string;
		} catch (IOException e) {
			System.out.println("Could not load player's name");
			return string;
		}
	}
	
	public static boolean saveHand(String username, Hand hand, int gameID) {
		try {
			FileWriter fstream = new FileWriter("saves/" + gameID + ".csv", true);
			BufferedWriter writer = new BufferedWriter(fstream);
			
			writer.write(username + ",");
			while (hand.getNumberCards() > 1) {
				Card card = hand.removeCard(0);
				writer.write(card + ":");
			}
			writer.write(hand.removeCard(0).toString());
			writer.write("\r\n");
			writer.close();
			return true;	
			
		} catch (IOException e) {
			return false;
		}
	}
	public static Hand loadHand(String username, int gameID) {
		Hand hand = new Hand();
		try {
			Scanner reader = new Scanner(new File("saves/" + gameID + ".csv"));
			while (reader.hasNextLine()) {
				String[] parsedLine = reader.nextLine().split(",");
				if (parsedLine[0].equals(username)) {
					String[] cardsList = parsedLine[1].split(":");
					for (String UnparsedCard : cardsList) {
						String parsedCard[] = UnparsedCard.split(" ");
						hand.addCard(Card.fromString(parsedCard[0], parsedCard[2]));
						
					}
				}
			}
			reader.close();
			return hand;
		} catch (IOException e) {
			System.out.println("Could not load hand");
			return hand;
		} 
	}
	public static Deck loadDeck(int gameID) {
		Deck deck = new Deck();
		try {
			Scanner reader = new Scanner(new File("saves/" + gameID + ".csv"));

			String[] parsedLine = reader.nextLine().split(",");
			String[] cards = parsedLine[1].split(":");
			for (String UnparsedCard : cards) {
				String parsedCard[] = UnparsedCard.split(" ");
				deck.addCard(Card.fromString(parsedCard[0], parsedCard[2]));
			}
			reader.close();
			return deck;
		} catch (IOException e){
			return deck;
		}
	}
}
