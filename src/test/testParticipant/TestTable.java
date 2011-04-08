package test.testParticipant;

import game.Blackjack;
import gameEngine.GameEngine;
import junit.framework.*;
import java.util.*;

import cards.*;
import cards.Card.Rank;
import cards.Card.Suit;
import participant.*;
import storage.*;
import table.Table;

public class TestTable extends TestCase {
	private Player p1; 
    private Player p2;
    private Player p3;
    private Player tableOwner; 
    private Table table;
    boolean failed;
    int expectedValue;
    int actualValue;
    
    public void setUp() { 
       p1 = new Player("jessica", "password");
       p2 = new Player("jack", "Black");
       p3 = new Player("abc", "123");
       tableOwner = new Player("emily", "abc");
       table = new Table(tableOwner);
    }
	
	public void testRequestJoinOnePlayerTableAccepts() {
		table.requestJoin(p1, true);
		assertEquals(table.getAllPlayers().size(), 2);
	}
	
	public void testRequestJoin2PlayersTableRejectsOne() {		
		p1.requestJoin(table, true);
		p2.requestJoin(table, false);
		
		assertEquals(table.getAllPlayers().size(), 2);
	}
	
	public void testRequestJoinAcceptAllThreePlayers() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		assertEquals(table.getAllPlayers().size(), 4);
	}
	
	public void testRequestJoinRejectAllThreePlayers() {
		p1.requestJoin(table, false);
		p2.requestJoin(table, false);
		p3.requestJoin(table, false);
		
		assertEquals(table.getAllPlayers().size(), 1);
	}
	
	public void testRequestLeaveAcceptOnePlayer() {
		p1.requestJoin(table, true);
		p1.requestLeave(table, true);
		
		assertEquals(table.getAllPlayers().size(), 1);
	}
	
	public void testRequestLeaveRejectPlayer() {
		p1.requestJoin(table, true);
		p1.requestLeave(table, false);
		
		assertEquals(table.getAllPlayers().size(), 2);
	}
	
	public void testRequestLeaveAcceptAllPlayers() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		p1.requestLeave(table, true);
		p2.requestLeave(table, true);
		p3.requestLeave(table, true);
		
		assertEquals(table.getAllPlayers().size(), 1);
	}
	
	public void testRequestLeaveFor3PlayersAcceptTwoOfThem() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		p1.requestLeave(table, false);
		p2.requestLeave(table, true);
		p3.requestLeave(table, true);
		
		assertEquals(table.getAllPlayers().size(), 2);
	}
	
	public void testRequestLeaveRejectAll3Players() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		p1.requestLeave(table, false);
		p2.requestLeave(table, false);
		p3.requestLeave(table, false);
		
		assertEquals(table.getAllPlayers().size(), 4);
	}
	
	public void testRequestViewAcceptTwoSpectators() {
		Spectator spect = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello","bye");
		
		spect.requestView(table, true);
		s2.requestView(table, true);
		
		assertEquals(table.getAllSpectators().size(), 2);
	}
	
	public void testRequestViewRejectTwoSpectators() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, false);
		s2.requestView(table, false);
		
		assertEquals(table.getAllSpectators().size(), 0);
	}
	
	public void testRequestViewAcceptOneSpectatorRejectOther() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, true);
		s2.requestView(table, false);
		
		assertEquals(table.getAllSpectators().size(), 1);
	}
	
	public void testRequestLeaveAcceptAllSpectators() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, true);
		s2.requestView(table, true);
		
		s1.requestLeave(table, true);
		s2.requestLeave(table, true);
		
		assertEquals(table.getAllSpectators().size(), 0);
	}
	
	public void testRequestLeaveRejectAllSpectators() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, true);
		s2.requestView(table, true);
		
		s1.requestLeave(table, false);
		s2.requestLeave(table, false);
		
		assertEquals(table.getAllSpectators().size(), 2);
	}
	
	public void testRequestLeaveRejectOneSpectatorAcceptOther() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, true);
		s2.requestView(table, true);
		
		s1.requestLeave(table, true);
		s2.requestLeave(table, false);
		
		assertEquals(table.getAllSpectators().size(), 1);
	}
	
	public void testSaveAndLoadGameOnePlayerWithDeck() {
		Deck deck = new Deck();
		deck.addDeck(1);
		//Player player = new Player("jessica", "password");
		
		table.saveGame(deck, 4);
		GameEngine game = table.loadGame(4);
		
		ArrayList<Player> players = table.getAllPlayers();
		ArrayList<String> playerNames = new ArrayList<String>();
		
		for (int i=0; i<players.size(); i++) {
			playerNames.add(players.get(i).getUsername());
		}
		
		String expectedString = table.getPlayer(0).getUsername();
		assertEquals(expectedString, playerNames.get(0));
	}
	
	public void testSaveAndLoadGameThreePlayersWithDeck() {
		Deck deck = new Deck();
		deck.addDeck(1);
		
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		table.saveGame(deck, 5);
		GameEngine game = table.loadGame(5);
		
		ArrayList<Player> players = table.getAllPlayers();
		ArrayList<String> playerNames = new ArrayList<String>();
		
		for (int i=0; i<players.size(); i++) {
			playerNames.add(players.get(i).getUsername());
		}
		
		String expectedString = table.getPlayer(0).getUsername();
		assertEquals(expectedString, playerNames.get(0));
		String s2 = table.getPlayer(1).getUsername();
		assertEquals(s2, playerNames.get(1));
		String s3 = table.getPlayer(2).getUsername();
		assertEquals(s3, playerNames.get(2));
	}
}