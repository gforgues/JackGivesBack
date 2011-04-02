package storage;
import junit.framework.*;
import cards.*;

public class TestStorage extends TestCase{
	private int expectedValue;
	private int actualValue;
	boolean failed;
	
	public void testAddNewPlayer() {
		Statistics jack = Storage.loadPlayer("jack","Black");

		assertEquals(Storage.savePlayer(jack), true);
	}
	
	public void testSavePlayer() {
		Statistics abc = Storage.loadPlayer("abc","123");
		
		abc.setChips(5);
		Storage.savePlayer(abc);
		abc = Storage.loadPlayer("abc","123");
		actualValue = abc.getChips();
		expectedValue = 5;
		
		assertEquals(actualValue, expectedValue);
	}
	
	public void testLoadPlayerPeriod() {
		failed = false;
		try {
			Storage.loadPlayer("John.Test", "password");
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, true);
		
	}
	public void testLoadPlayerComma() {
		failed = false;
		try {
			Storage.loadPlayer("john,test", "password");
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, true);
	}

	
	public void testHallOfFame() {
		for (Statistics player : HallOfFame.getHallOfFame()) {
			System.out.println(player.getUsername() + ": " + player.getTotalChipsWon());
		}
	}
}
