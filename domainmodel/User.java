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
		Category none = new Category("None", 0);
		none.setAlert(false);
		addCategory(none);
		
		//for testing purposes
		addTestMaterial();
	}
	
	public void addTestMaterial() {
		addCategory("Food", 50);
		addCategory("Gas", 100);
		addCategory("Bills", 500);
		addCategory("Job", 1000);
		addCategory("Entertainment", 100);
		
		addTransaction(-20, budgetCategories.get(1), new Date(2018));
		addTransaction(500, budgetCategories.get(4), new Date(205618));
		addTransaction(-100, budgetCategories.get(2), new Date(201465138));
		addTransaction(-30, budgetCategories.get(5), new Date(2015468));
	}
	
	public void addTransaction(double amt, Category cat, Date date) {
		Transaction t = new Transaction(amt, cat, nextTransactionID++, date);
		acctBalance += amt;
		transactionRecord.add(t);
	}
	
	public void addTransaction(Transaction transaction) {
		acctBalance += transaction.getValue();
		transactionRecord.add(transaction);
	}
	
	public void addCategory(String title, double limit) {
		Category c = new Category(title, limit);
		budgetCategories.add(c);	
	}
	
	public void addCategory(Category category) {
		budgetCategories.add(category);	
	}
	
	public void removeCategory(Category toDelete){
		Transaction thisTransaction = null;
				
		//Replace category of all transactions that have the selected category with None
		for (int i = 0; i < transactionRecord.size(); i++) {
			thisTransaction = transactionRecord.get(i);
			if (thisTransaction.getCategory() == toDelete) {
				thisTransaction.setCategory(budgetCategories.get(0));
			}
		}
		
		budgetCategories.remove(toDelete);		
	}
	
	public void removeTransaction(Transaction toDelete){
		acctBalance-=toDelete.getValue();
		transactionRecord.remove(toDelete);		
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