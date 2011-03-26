package storage;
import junit.framework.*;

public class TestStorage extends TestCase{
	private int expectedValue;
	private int actualValue;
	
	public void testAddNewPlayer() {
		Storage.addNewPlayer("Jack","Black");
		Storage Jack = Storage.loadPlayer("Jack","Black");

		Jack.addWin(5);
		Jack.addWin(5);

		assertEquals(Jack.savePlayer(), true);
	}
	
	public void testSavePlayer() {
		Storage.addNewPlayer("abc","123");
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
	
	public void testAddWin() {
		Storage Jack = Storage.loadPlayer("Jack", "Black");
		expectedValue = Jack.getGamesWonStreak() + 1;
		Jack.addWin(5);
		actualValue = Jack.getGamesWonStreak();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testWinStreak() {
		Storage.addNewPlayer("Bill","Black");
		Storage Bill = Storage.loadPlayer("Bill", "Black");
		
		Bill.addLoss(5);
		Bill.addWin(5);
		Bill.addWin(5);
		Bill.addWin(5);
		expectedValue = 3;
		actualValue = Bill.getBiggestGamesWonStreak();
		
		assertEquals(expectedValue, actualValue);
		Bill.savePlayer();
	}
	public void testChangePassword() {
		Storage Jack = Storage.loadPlayer("Jack", "Black");
		
		Jack.changePassword("newpassword");
		
		boolean validPassword = Jack.validatePassword("newpassword");
		assertEquals(validPassword, true);
		
		boolean invalidPassword = Jack.validatePassword("Black");
		assertEquals(invalidPassword, false);
	}
}
