package testStorage;
import junit.framework.*;
import storage.*;

public class TestStatistics extends TestCase {
	private int expectedValue;
	private int actualValue;
	boolean failed;
	
	public void testValidatePassword() {
		Statistics jack = Storage.loadPlayer("jack", "Black");
		
		boolean validPassword = jack.validatePassword("Black");
		assertEquals(validPassword, true);
		
		boolean invalidPassword = jack.validatePassword("black");
		assertEquals(invalidPassword, false);
	}
	
	public void testSetAgeNegativeTwo() {
		failed = false;
		Statistics jack = Storage.loadPlayer("jack", "Black");
		try {
			jack.setAge(-2);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, true);
	}
	
	public void testSetAgeNegativeOne() {
		failed = false;
		Statistics jack = Storage.loadPlayer("jack", "Black");
		try {
			jack.setAge(-1);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, false);
	}
	
	public void testSetAgePositive() {
		failed = false;
		Statistics jack = Storage.loadPlayer("jack", "Black");
		try {
			jack.setAge(64);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, false);
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
	
	public void testPositiveChips() {
		Statistics emily = Storage.loadPlayer("emily", "abc");
		
		expectedValue = emily.getChips() + 100;
		emily.addChips(100);
		actualValue = emily.getChips();
		assertEquals(expectedValue, actualValue);
	}
	public void testNegativeChips() {
		failed = false;
		Statistics emily = Storage.loadPlayer("emily", "abc");
		
		try {
			emily.setChips(-1);
		} catch (IllegalArgumentException e){
			failed = true;
		} finally {
			assertEquals(failed, true);
		}
	}
	
	public void testAddNegativeChips() {
		failed = false;
		Statistics emily = Storage.loadPlayer("emily", "abc");
		emily.setChips(1);
		emily.addChips(-1);
		assertEquals(emily.getChips(), 0);
	}
	
	public void testInsufficientChips() {
		failed = false;
		
		Statistics emily = Storage.loadPlayer("emily", "abc");
		
		emily.setChips(1);
		try {
			emily.setChips(-2);
		} catch (IllegalArgumentException e){
			failed = true;
		} finally {
			assertEquals(failed, true);
		}
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
	
	public void testChangePasswordInvalid() {
		Statistics jack = Storage.loadPlayer("jack", "Black");
		try {
			jack.changePassword("new,password");
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		assertEquals(failed, true);
	}
}
