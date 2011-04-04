package participant;

import junit.framework.*;

public class TestPlayer extends TestCase {
	private int expectedValue;
	private int actualValue;
	boolean failed;
	
	public void testModifyProfileCorrectPassword() {
		Player jack = new Player("jack", "Black");//Storage.loadPlayer("jack", "Black");
		
		jack.modifyProfile("Black", "password");
		
		//boolean validPassword = jack.validatePassword("password");
		//assertEquals(validPassword, true);
		
		//boolean invalidPassword = jack.validatePassword("black");
		//assertEquals(invalidPassword, false);
	}
	
	
}