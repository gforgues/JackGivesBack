package storage;
import junit.framework.*;

public class TestStorage extends TestCase{
	private int expectedValue;
	private int actualValue;
	
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
	
	public void testValidatePassword() {
		Statistics jack = Storage.loadPlayer("jack", "Black");
		
		boolean validPassword = jack.validatePassword("Black");
		assertEquals(validPassword, true);
		
		boolean invalidPassword = jack.validatePassword("black");
		assertEquals(invalidPassword, false);
	}
	
	public void testChipCounts() {
		Statistics emily = Storage.loadPlayer("emily", "abc");
		
		expectedValue = emily.getChips() + 1000;
		emily.addWin(500);
		emily.addWin(500);
		actualValue = emily.getChips();
		assertEquals(expectedValue, actualValue);
		Storage.savePlayer(emily);
		
	}
	public void testAddWin() {
		Statistics jack = Storage.loadPlayer("jack", "Black");
		expectedValue = 1;
		jack.addWin(5);
		actualValue = jack.getMaxWinStreak();
		
		assertEquals(expectedValue, actualValue);
		Storage.savePlayer(jack);
	}
	
	public void testWinStreak() {
		Statistics bill = Storage.loadPlayer("bill", "Black");
		
		bill.addLoss(5);
		bill.addWin(5);
		bill.addWin(5);
		bill.addWin(5);
		expectedValue = 3;
		actualValue = bill.getMaxWinStreak();
		
		assertEquals(expectedValue, actualValue);
		Storage.savePlayer(bill);
	}
	public void testLossStreak() {
		Statistics jack = Storage.loadPlayer("jack","Black");

		jack.addWin(5);
		jack.addLoss(10);
		jack.addLoss(10);
		jack.addLoss(10);
		expectedValue = 3;
		actualValue = jack.getMaxLossStreak();
		
		assertEquals(expectedValue, actualValue);
		Storage.savePlayer(jack);
		
	}
	public void testChangePassword() {
		Statistics jack = Storage.loadPlayer("jack", "Black");
		
		jack.changePassword("newpassword");
		
		boolean validPassword = jack.validatePassword("newpassword");
		assertEquals(validPassword, true);
		
		boolean invalidPassword = jack.validatePassword("Black");
		assertEquals(invalidPassword, false);
	}
	public void testHallOfFame() {
		for (Statistics player : HallOfFame.getHallOfFame()) {
			System.out.println(player.getUsername() + ": " + player.getTotalChipsWon());
		}
	}
}
