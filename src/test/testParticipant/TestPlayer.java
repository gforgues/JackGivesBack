package test.testParticipant;

import participant.*;

import java.io.FileNotFoundException;

import storage.Statistics;
import storage.Storage;
import junit.framework.*;

public class TestPlayer extends TestCase {
	private int expectedValue;
	private int actualValue;
	private String expectedStringValue;
	private String actualStringValue;
	boolean failed;
	
	public void testCreateNewPlayer() {
		Player player = new Player("newPlayer","test");
		Storage.savePlayer(player.toStatistics());
	}
	
	public void testModifyProfileCorrectPassword() {
		Player jack = new Player("jack", "Black");
		
		jack.modifyProfile("Black", "password");
		
		expectedStringValue = "password";
		actualStringValue = jack.toStatistics().getPassword();
		
		assertEquals(expectedStringValue, actualStringValue);
	}
	
	public void testModifyProfileIncorrectPassword() {
		Player player = new Player("jack", "Black");
		
		player.modifyProfile("black", "password");
		
		expectedStringValue = "Black";
		actualStringValue = player.toStatistics().getPassword();
		
		assertEquals(expectedStringValue, actualStringValue);
	}
	
	public void testModifyProfileCheckRealNameAndCheckAge() {
		Player player = new Player("jack", "Black");
		
		player.modifyProfile("Black", "password", "Jackson", 55);
		
		expectedStringValue = "Jackson";
		actualStringValue = player.getRealName();
		assertEquals(expectedStringValue, actualStringValue);
		
		expectedValue = 55;
		actualValue = player.getAge();
		assertEquals(expectedValue, actualValue);
	}
	
	public void testModifyProfileNegativeAge() {		
		failed = false;
		Player jack = new Player("jack", "Black");
		try {
			jack.modifyProfile("Black", "password", "Jackson", -3);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, true);
	}
	
	public void testGetUsername() {
		Player player = new Player("emily", "abc");
		
		expectedStringValue = "emily";
		actualStringValue = player.getUsername();
		
		assertEquals(expectedStringValue, actualStringValue);
	}
	
//	public void testSetChips() {
//		Player player = new Player("jessica", "password");
//		
//		player.setChips(5000);
//		expectedValue = 35000;
//		actualValue = player.getChips();
//		
//		assertEquals(expectedValue, actualValue);
//	}
	
	public void testAddChipsPositiveAmount() {
		failed = false;
		Player player = new Player("abcde", "1234");
		try {
			expectedValue = player.getChips() + 100;
			player.addChips(100);
			actualValue = player.getChips();
			
			assertEquals(expectedValue, actualValue);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, false);
	}
	
	public void testAddChipsNegativeAmount() {
		failed = true;
		Player player = new Player("abc", "123");
		try {
			expectedValue = player.getChips() - 3;
			player.addChips(-3);
			actualValue = player.getChips();
			
			assertEquals(expectedValue, actualValue);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, true);
	}
	
	public void testAddChipsPositiveAmountGreaterThanActualChips() {
		failed = false;
		Player player = new Player("jessica", "password");
		try {
			player.addChips(10000000);
			expectedValue = 35000;
			actualValue = player.getChips();
			
			assertEquals(expectedValue, actualValue);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, true);
	}
	
	public void testAddChipsNegativeAmountGreaterThanActualChips() {
		failed = false;
		Player player = new Player("jessica", "password");
		try {
			player.addChips(-10000000);
			expectedValue = 35000;
			actualValue = player.getChips();
			
			assertEquals(expectedValue, actualValue);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, true);
	}
	
	public void testAddChipsNegativeAmountEqualToChipAmount() {
		failed = false;
		Player player = new Player("bill", "Black");
		try {
			expectedValue = player.getChips() - 400;
			player.addChips(-400);
			actualValue = player.getChips();
			
			assertEquals(expectedValue, actualValue);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, false);
	}
	
	public void testAddWinBetAmountEqualToChips() {
		Player player = new Player("jack", "Black");
		
		expectedValue = player.getChips() + 34;
		player.addWin(34);
		actualValue = player.getChips();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testAddLossBetAmountEqualToChips() {
		Player player = new Player("jack", "Black");
		
		expectedValue = player.getChips() - 34;
		player.addLoss(34);
		actualValue = player.getChips();
		
		assertEquals(expectedValue, actualValue);
	}
	
}