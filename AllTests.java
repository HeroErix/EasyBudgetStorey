import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import UI.reView;
import domainmodel.User;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {	
	
	@Test
	void testInitialUser() {
		reView ui = new reView();
		Controller test = new Controller(ui);
		test.initController();
		User currentUser = test.getCurrentUser();
		boolean sameName = (currentUser.getName().equals("Default"));
		boolean samePass = (currentUser.getPassword().equals(""));
		boolean sameBal = (currentUser.getAcctBalance()==0);
		
		assertTrue(sameName);
		assertTrue(samePass);
		assertTrue(sameBal);
	}
	
	@Test
	void testNewUser() {
		reView ui = new reView();
		Controller test = new Controller(ui);
		test.initController();
		User newUser = new User("Jimmy", "Password", 1337.42);
		test.addUser(newUser);
		User currentUser = test.getCurrentUser();
		assertEquals(currentUser, newUser);
	}
}
