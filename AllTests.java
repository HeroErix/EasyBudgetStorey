import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import UI.reView;
import domainmodel.Category;
import domainmodel.Transaction;
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
	
	@Test
	void testNewCategory() {
		reView ui = new reView();
		Controller test = new Controller(ui);
		test.initController();
		User testUser = test.getCurrentUser();
		Category testCategory = new Category("Food", 50);
		testUser.addCategory(testCategory);
		assertEquals(testUser.getBudgetCategories().get(0), testCategory);
		
	}
	
	@Test
	void testUpdateCategoryList() {
		reView ui = new reView();
		Controller test = new Controller(ui);
		test.initController();
		User testUser = test.getCurrentUser();
		Category testCategory = new Category("Food", 50);
		testUser.addCategory(testCategory);
		ui.getTabbedPane().setSelectedIndex(2);
		//System.out.println(ui.getCategoryList().getText());
		//System.out.println("Category\t\tLimit\tEnabled\nFood\t\t50.0\ttrue\n");
		assertEquals(ui.getCategoryList().getText(), 
				"Category\t\t"
				+ "Limit\t"
				+ "Enabled\n"+
				testCategory.getTitle()+"\t\t"+
				testCategory.getLimit()+"\t"+
				testCategory.isAlert()+"\n");
	}
	
	@Test
	void testNewTransaction() {
		reView ui = new reView();
		Controller test = new Controller(ui);
		test.initController();
		User testUser = test.getCurrentUser();
		Category testCategory = new Category("Food", 50);
		testUser.addCategory(testCategory);
		Date testDate = new Date();
		Transaction testTransaction = new Transaction(25, testCategory, 0, testDate);
		testUser.addTransaction(testTransaction);
		assertEquals(testUser.getTransactionRecord().get(0), testTransaction);
	}
	
	@Test
	void testUpdateTransactionList() {
		reView ui = new reView();
		Controller test = new Controller(ui);
		test.initController();
		User testUser = test.getCurrentUser();
		Category testCategory = new Category("Food", 50);
		testUser.addCategory(testCategory);
		Date testDate = new Date();
		Transaction testTransaction = new Transaction(25, testCategory, 0, testDate);
		testUser.addTransaction(testTransaction);
		ui.getTabbedPane().setSelectedIndex(1);
		assertEquals(ui.getTransactionList().getText(), 
				"ID\t"
				+ "Date\t\t\t"
				+ "Amount\t"
				+ "Category\n"+
				testTransaction.getId()+"\t"+
				testDate.toString()+"\t\t"+
				testTransaction.getValue()+"\t"+
				testTransaction.getCategory().getTitle()+"\n");

	}
}
