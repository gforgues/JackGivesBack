package Storage;
import junit.framework.*;

public class TestStorage extends TestCase{
	private int expectedValue;
	private int actualValue;
	
	public void testAddNewPlayer() {
		Storage.addNewPlayer("Jack","Black");
		Storage Jack = Storage.loadPlayer("Jack","Black");
		
		int current = Jack.getNumWins();
		Jack.addWin();
		Jack.addWin();
		Jack.savePlayer();
		
		assertEquals(Jack.getNumWins(), current + 2);
	}
	
	public void testSavePlayer() {
		Storage.addNewPlayer("abc","123");
		Storage abc = Storage.loadPlayer("abc","123");
		
		abc.addChips(-5); //reset chips to 0
		abc.addChips(5);
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
	
	public void testSetWin() {
		Storage Jack = Storage.loadPlayer("Jack", "Black");
		expectedValue = Jack.getNumWins() + 1;
		Jack.addWin();
		actualValue = Jack.getNumWins();
		
		assertEquals(expectedValue, actualValue);
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
