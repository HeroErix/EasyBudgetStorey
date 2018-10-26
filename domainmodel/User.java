package domainmodel;
import java.util.ArrayList;
import java.util.Date;

public class User {
	private String name;
	private String password;
	private double acctBalance;
	private ArrayList<Transaction> transactionRecord;
	private ArrayList<Category> budgetCategories;
	private int nextTransactionID;
	
	public User(String name, String password, double acctBalance) {
		this.name = name;
		this.password = password;
		this.acctBalance = acctBalance;
		this.transactionRecord = new ArrayList<Transaction>();
		this.budgetCategories = new ArrayList<Category>();		
		this.nextTransactionID = 0;
		
		//for testing purposes
		//addTestMaterial();
	}
	
	public void addTestMaterial() {
		addCategory("Food", 100);
		addCategory("Gas", 100);
		addCategory("Bills", 100);
		addCategory("Job", 100);
		addCategory("Entertainment", 100);
		
		addTransaction(-20, budgetCategories.get(0), new Date(2018));
		addTransaction(500, budgetCategories.get(3), new Date(205618));
		addTransaction(-100, budgetCategories.get(1), new Date(201465138));
		addTransaction(-30, budgetCategories.get(4), new Date(2015468));
	}
	
	public void addTransaction(double amt, Category cat, Date date) {
		Transaction t = new Transaction(amt, cat, nextTransactionID++, date);
		acctBalance += amt;
		transactionRecord.add(t);
	}
	
	public void addCategory(String title, double limit) {
		Category c = new Category(title, limit);
		budgetCategories.add(c);	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Transaction> getTransactionRecord(){
		return transactionRecord; 
	}

	public double getAcctBalance() {
		return acctBalance;
	}

	public void setAcctBalance(double acctBalance) {
		this.acctBalance = acctBalance;
	}

	public ArrayList<Category> getBudgetCategories() {
		return budgetCategories;
	}

	public int getNextTransactionID() {
		return nextTransactionID;
	}

	public void setNextTransactionID(int nextTransactionID) {
		this.nextTransactionID = nextTransactionID;
	}

}