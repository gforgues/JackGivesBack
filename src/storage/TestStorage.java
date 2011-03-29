package storage;
import junit.framework.*;

public class TestStorage extends TestCase{
	private int expectedValue;
	private int actualValue;
	
	public void testAddNewPlayer() {
		Storage Jack = Storage.loadPlayer("Jack","Black");

		assertEquals(Jack.savePlayer(), true);
	}
	
	public void testSavePlayer() {
		Storage abc = Storage.loadPlayer("abc","123");
		
		abc.setChips(5);
		abc.savePlayer();
		abc = Storage.loadPlayer("abc","123");
		actualValue = abc.getChips();
		expectedValue = 5;
		
		assertEquals(actualValue, expectedValue);
	}
	
	public void testValidatePassword() {
		Storage Jack = Storage.loadPlayer("Jack", "Black");
		
		boolean validPassword = Jack.validatePassword("Black");
		assertEquals(validPassword, true);
		
		boolean invalidPassword = Jack.validatePassword("black");
		assertEquals(invalidPassword, false);
	}
	
	public void testChipCounts() {
		Storage chipWinner = Storage.loadPlayer("chipWinner", "abc");
		
		expectedValue = chipWinner.getChips() + 1000;
		chipWinner.addWin(500);
		chipWinner.addWin(500);
		actualValue = chipWinner.getChips();
		assertEquals(expectedValue, actualValue);
		chipWinner.savePlayer();
		
	}
	public void testAddWin() {
		Storage Jack = Storage.loadPlayer("Jack", "Black");
		expectedValue = 1;
		Jack.addWin(5);
		actualValue = Jack.getMaxWinStreak();
		
		assertEquals(expectedValue, actualValue);
		Jack.savePlayer();
	}
	
	public void testWinStreak() {
		Storage Bill = Storage.loadPlayer("Bill", "Black");
		
		Bill.addLoss(5);
		Bill.addWin(5);
		Bill.addWin(5);
		Bill.addWin(5);
		expectedValue = 3;
		actualValue = Bill.getMaxWinStreak();
		
		assertEquals(expectedValue, actualValue);
		Bill.savePlayer();
	}
	public void testLossStreak() {
		Storage Jack = Storage.loadPlayer("Jack","Black");

		Jack.addWin(5);
		Jack.addLoss(10);
		Jack.addLoss(10);
		Jack.addLoss(10);
		expectedValue = 3;
		actualValue = Jack.getMaxLossStreak();
		
		assertEquals(expectedValue, actualValue);
		Jack.savePlayer();
		
	}
	public void testChangePassword() {
		Storage Jack = Storage.loadPlayer("Jack", "Black");
		
		Jack.changePassword("newpassword");
		
		boolean validPassword = Jack.validatePassword("newpassword");
		assertEquals(validPassword, true);
		
		boolean invalidPassword = Jack.validatePassword("Black");
		assertEquals(invalidPassword, false);
	}
	public void testHallOfFame() {
		for (Storage player : HallOfFame.getHallOfFame()) {
			System.out.println(player.getUsername() + ": " + player.getTotalChipsWon());
		}
	}
}
