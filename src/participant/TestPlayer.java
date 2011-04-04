package participant;

import storage.Statistics;
import storage.Storage;
import junit.framework.*;

public class TestPlayer extends TestCase {
	private int expectedValue;
	private int actualValue;
	private String expectedStringValue;
	private String actualStringValue;
	boolean failed;
	
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
	
	
}