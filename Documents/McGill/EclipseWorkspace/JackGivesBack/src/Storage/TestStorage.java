package Storage;
import junit.framework.*;

public class TestStorage extends TestCase{
	private int expectedValue;
	private int actualValue;
	
	public void testAddNewPlayer() {
		Storage.loadPlayer("Jack","Black");
		Storage Jack = Storage.loadPlayer("Jack","Black");

		assertEquals(Jack.savePlayer(), true);
	}
	
	public void testSavePlayer() {
		Storage.loadPlayer("abc","123");
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
		expectedValue = 1;
		Jack.addWin(5);
		actualValue = Jack.getMaxWinStreak();
		
		assertEquals(expectedValue, actualValue);
		Jack.savePlayer();
	}
	
	public void testWinStreak() {
		Storage.loadPlayer("Bill","Black");
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
	public void testChangePassword() {
		Storage Jack = Storage.loadPlayer("Jack", "Black");
		
		Jack.changePassword("newpassword");
		
		boolean validPassword = Jack.validatePassword("newpassword");
		assertEquals(validPassword, true);
		
		boolean invalidPassword = Jack.validatePassword("Black");
		assertEquals(invalidPassword, false);
	}
	public void testHallOfFame() {
		HallOfFame.getHallOfFame();
	}
}
