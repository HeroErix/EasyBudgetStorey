import java.awt.Choice;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import UI.*;
import domainmodel.*;

public class Controller {
	private reView view;
	private ArrayList<User> accounts;
	private int userIndex;
	private User currentUser;	

	public Controller(reView v) {		
		accounts = new ArrayList<User>();
		view = v;	
		if (accounts.size()==0){addUser(new User("Default", "", 0));}
		currentUser = accounts.get(userIndex);
	}
	
	public void initController() {		
		
		view.getBtnNewAccount().addActionListener(e -> createUser());
		view.getMntmNewCatagory().addActionListener(e -> createCategory());
		view.getMntmNewTransaction().addActionListener(e -> createTransaction());
		view.getChoice().addItemListener(e -> setUser());
		view.getTabbedPane().addChangeListener(e -> swapTab());
//		view.getBtnAccount().addActionListener(e -> showAccountDetails());
//		view.getBtnEnterTransaction().addActionListener(e -> enterTransaction());
//		view.getBtnAddUser().addActionListener(e -> toggleAddUser());
//		view.getBtnToggleMenu().addActionListener(e -> toggleMenu());
//		view.getBtnEnterNewTransaction().addActionListener(e -> toggleTransactionPanel());
//		view.getBtnAddCategory().addActionListener(e -> toggleCategoryAdd());
//		
//		view.getBtnEnterCategory().addActionListener(e -> enterCategory());
//		view.getBtnShowRecentTransactions().addActionListener(e -> toggleTransactionRecord());
		
	}
	
	private void swapTab() {		
		int index = view.getTabbedPane().getSelectedIndex();
        String tab = view.getTabbedPane().getTitleAt(index);        
        if (tab.equals("Transactions")) {
        	populateTransactions();
        }else if (tab.equals("Categories")) {
        	populateCategories();
        }
	}
	
	private void populateTransactions() {
		JTextArea transactionBox = view.getTransactionList();
		transactionBox.setText("");
		transactionBox.append(
				"ID" + "\t" +
				"Date" + "\t\t\t" +
				"Amount" + "\t" +
				"Category" + "\n");
		for(int i=0; i<currentUser.getTransactionRecord().size(); i++) {
			Transaction thisTransaction = currentUser.getTransactionRecord().get(i);
				transactionBox.append(
						thisTransaction.getId() + "\t" +
						thisTransaction.getDate() + "\t\t" +
						thisTransaction.getValue() + "\t" +
						thisTransaction.getCategory().getTitle() + "\n");			
		}
		
	}
	
	private void populateCategories() {
		JTextArea categoryBox = view.getCategoryList();
		categoryBox.setText("");
		categoryBox.append(
				"Category" + "\t\t" +
				"Limit" + "\t" +
				"Enabled" + "\n");
		for(int i=0; i<currentUser.getBudgetCategories().size(); i++) {
			Category thisCategory = currentUser.getBudgetCategories().get(i);
			categoryBox.append(
					thisCategory.getTitle() + "\t\t" +
					thisCategory.getLimit() + "\t" + 
					thisCategory.isAlert() + "\n");
		}
		
	}
	
	private void showAccountDetails() {
		
		JOptionPane.showMessageDialog(null, 
				"\nUsername: " + currentUser.getName() +
				"\nPassword: " + currentUser.getPassword() +
				"\nBalance : " + currentUser.getAcctBalance()
				, "Info", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	
	private void setUser() {
		
		String switchUser = view.getChoice().getSelectedItem();
		for(int i=0; i<accounts.size(); i++) {
			if(switchUser.compareTo(accounts.get(i).getName()) == 0) {
				userIndex = i;
				currentUser = accounts.get(userIndex);
			}
		}
		view.getChoice().select(currentUser.getName());
		view.getLblBalanceAmt().setText(Double.toString(currentUser.getAcctBalance()));
	}
		
	//Shows Dialog to user to create new account
	private void createUser() {
		JTextField nameField = new JTextField(5);
		JTextField passwordField = new JTextField(5);
		JTextField balanceField = new JTextField(5);
		
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Name:"));
		myPanel.add(nameField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Password:"));
		myPanel.add(passwordField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Balance:"));
		myPanel.add(balanceField);
		int result = JOptionPane.showConfirmDialog(null, myPanel, "New User", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			User newUser = new User(nameField.getText(), passwordField.getText(), Double.parseDouble(balanceField.getText()));
			addUser(newUser);
		}				
	}
	
	//Adds new account to the list
	void addUser(User newUser) {		
		accounts.add(newUser);
		userIndex = accounts.size()-1;
		currentUser = accounts.get(userIndex);
		
		view.getChoice().add(newUser.getName());
		view.getChoice().select(newUser.getName());
		view.getLblBalanceAmt().setText(Double.toString(newUser.getAcctBalance()));		
		
		if (!newUser.getName().equals("Default")){
			JOptionPane.showMessageDialog(null, "New User Created", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void createCategory() {
		JTextField titleField = new JTextField(5);
		JTextField limitField = new JTextField(5);
		
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Title:"));
		myPanel.add(titleField);
		myPanel.add(Box.createVerticalStrut(15)); // a spacer
		myPanel.add(new JLabel("Limit:"));
		myPanel.add(limitField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "New Category", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			try {			    
			    //Create category				
				int categoryNumber;
				double limit = Double.parseDouble(limitField.getText());
				
				currentUser.addCategory(titleField.getText(), limit);
				
				categoryNumber = currentUser.getBudgetCategories().size();
			
				JOptionPane.showMessageDialog(null,
					"New Category Entered: " +
					"\nCategory Number: " + categoryNumber +
					"\nID: " + currentUser.getBudgetCategories().get(categoryNumber-1).getTitle() + 
					"\nLimit: " + currentUser.getBudgetCategories().get(categoryNumber-1).getLimit(), 
					"Info", JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException e) {
			    //Limit must be double
				JOptionPane.showMessageDialog(null,	"Balance must be double.", "Info", JOptionPane.INFORMATION_MESSAGE);
				createCategory();
			}
		}
	}
	
	private void createTransaction() {		
		JTextField amountField = new JTextField(5);
		JRadioButton rdbtnIncome = new JRadioButton("Income"), rdbtnExpense = new JRadioButton("Expense");
		Choice categoryChoice = new Choice();		
		//Populate categoryChoice based on currentUser
		categoryChoice.removeAll();
		categoryChoice.add("None");
		for(int i = 0; i<currentUser.getBudgetCategories().size(); i++) {
			categoryChoice.add(currentUser.getBudgetCategories().get(i).getTitle());
		}
		DatePicker datePick = new DatePicker("yyyy-MM-dd", new Date());
		
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Amount:"));
		myPanel.add(amountField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer				
		myPanel.add(rdbtnIncome);
		myPanel.add(rdbtnExpense);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Category:"));
		myPanel.add(categoryChoice);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Date:"));
		myPanel.add(datePick);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "New User", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			//Create transaction
			Category cat = null;
			String catID = categoryChoice.getSelectedItem();
			double amount = Double.parseDouble(amountField.getText());
			String type = "Income";
			Date date = datePick.getDate();
			
			if(rdbtnExpense.isSelected()) {
				amount = 0 - amount;
				type = "Expense";
			}
			
			for(int i=0; i<currentUser.getBudgetCategories().size(); i++) {
				if(catID.compareTo("None") == 0) {
					cat = null;
				}
				else if(catID.compareTo(currentUser.getBudgetCategories().get(i).getTitle()) == 0) {
					cat = currentUser.getBudgetCategories().get(i);
				}
			}
			
			currentUser.addTransaction(amount, cat, date);			
			view.getLblBalanceAmt().setText(Double.toString(currentUser.getAcctBalance()));
			
			JOptionPane.showMessageDialog(null,
					"New Transaction Entered: " +
					"\nTransaction Number: " + (currentUser.getNextTransactionID()-1) +
					"\nType: " + type + 
					"\nAmount: " + amountField.getText() + 
					"\nCategory: " + categoryChoice.getSelectedItem()
					, "Transaction Created", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}