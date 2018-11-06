import java.awt.Choice;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

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
		view.getMntmEditCatagory().addActionListener(e -> editCategory());
		view.getMntmEditTransaction().addActionListener(e -> editTransaction());
		view.getMntmDeleteCatagory().addActionListener(e -> deleteCategory());
		view.getMntmDeleteTransaction().addActionListener(e -> deleteTransaction());
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
				"Date" + "\t\t" +
				"Amount" + "\t" +
				"Category" + "\n");
		for(int i=0; i<currentUser.getTransactionRecord().size(); i++) {
			Transaction thisTransaction = currentUser.getTransactionRecord().get(i);
				transactionBox.append(
						thisTransaction.getId() + "\t" +
						removeTime(thisTransaction.getDate()) + "\t" +
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
		JPasswordField passwordField = new JPasswordField(5);
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
			@SuppressWarnings("deprecation")
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
				double limit = Double.parseDouble(limitField.getText());				
				currentUser.addCategory(titleField.getText(), limit);
			} catch (NumberFormatException e) {
			    //Limit must be double
				JOptionPane.showMessageDialog(null,	"Balance must be double.", "Info", JOptionPane.INFORMATION_MESSAGE);
				createCategory();
			}
		}
		populateCategories();
	}
	
	private Category selectCategory() {
		Category cat = null;
		Choice categoryChoice = new Choice();		
		//Populate categoryChoice based on currentUser
		categoryChoice.removeAll();
		for(int i = 1; i<currentUser.getBudgetCategories().size(); i++) {
			categoryChoice.add(currentUser.getBudgetCategories().get(i).getTitle());
		}
				
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Category:"));
		myPanel.add(categoryChoice);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Select Category", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			//Select category
			String catID = categoryChoice.getSelectedItem();			
			for(int i=0; i<currentUser.getBudgetCategories().size(); i++) {
				if(catID.compareTo(currentUser.getBudgetCategories().get(i).getTitle()) == 0) {
					cat = currentUser.getBudgetCategories().get(i);
				}
			}				
		}
		return cat;
	}
	
	private void editCategory(){
		Category cat = selectCategory();
		if (cat != null) {
			JTextField titleField = new JTextField(cat.getTitle(), 10);
			JTextField limitField = new JTextField(Double.toString(cat.getLimit()), 10);
			JToggleButton enabledButton = new JToggleButton("Enabled", cat.isAlert());
			
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("New Title:"));
			myPanel.add(titleField);
			myPanel.add(Box.createVerticalStrut(15)); // a spacer
			myPanel.add(new JLabel("Limit:"));
			myPanel.add(limitField);
			myPanel.add(Box.createVerticalStrut(15)); // a spacer
			myPanel.add(enabledButton);
			
			int result = JOptionPane.showConfirmDialog(null, myPanel, "Edit Category", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {			    
				    //Create category				
					double limit = Double.parseDouble(limitField.getText());
					cat.setTitle(titleField.getText());
					cat.setLimit(limit);
					cat.setAlert(enabledButton.isSelected());				
				} catch (NumberFormatException e) {
				    //Limit must be double
					JOptionPane.showMessageDialog(null,	"Balance must be double.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			populateCategories();
		}
	}
	
	private void deleteCategory(){
		Category cat = selectCategory();
		if (cat != null) {
			//Delete stuff
			currentUser.removeCategory(cat);
			populateCategories();
		}
	}
	
	private void createTransaction() {		
		JTextField amountField = new JTextField(5);
		JRadioButton rdbtnIncome = new JRadioButton("Income"), rdbtnExpense = new JRadioButton("Expense");
		Choice categoryChoice = new Choice();		
		//Populate categoryChoice based on currentUser
		categoryChoice.removeAll();
		for(int i = 0; i<currentUser.getBudgetCategories().size(); i++) {
			categoryChoice.add(currentUser.getBudgetCategories().get(i).getTitle());
		}
		DatePicker datePick = new DatePicker();
		
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
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "New Transaction", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			//Create transaction
			Category cat = null;
			String catID = categoryChoice.getSelectedItem();
			double amount = Double.parseDouble(amountField.getText());
			Date date = datePick.getDate();
			
			if(rdbtnExpense.isSelected()) {
				amount = 0 - amount;
			}
			
			for(int i=0; i<currentUser.getBudgetCategories().size(); i++) {
				if(catID.compareTo(currentUser.getBudgetCategories().get(i).getTitle()) == 0) {
					cat = currentUser.getBudgetCategories().get(i);
				}
			}
			
			currentUser.addTransaction(amount, cat, date);			
			view.getLblBalanceAmt().setText(Double.toString(currentUser.getAcctBalance()));
			
		}
		populateTransactions();
		
	}
	
	private Transaction selectTransaction() {
		Transaction trans = null;
		JTextField idField = new JTextField(5);						
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Transaction:"));
		myPanel.add(idField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Select Transaction", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			//Select transaction
			int transID = Integer.parseInt(idField.getText());			
			for(int i=0; i<currentUser.getTransactionRecord().size(); i++) {
				if(transID==currentUser.getTransactionRecord().get(i).getId()) {
					trans = currentUser.getTransactionRecord().get(i);
				}
			}
		}
		return trans;
	}
	
	private void editTransaction(){
		Transaction trans = selectTransaction();
		if (trans != null) {
			JTextField amountField = new JTextField(Double.toString(Math.abs(trans.getValue())), 5);
			JRadioButton rdbtnIncome = new JRadioButton("Income"), rdbtnExpense = new JRadioButton("Expense");
			if (trans.getValue()<0) {
				rdbtnExpense.setSelected(true);
			} else {
				rdbtnIncome.setSelected(true);
			}
			Choice categoryChoice = new Choice();
			//Populate categoryChoice based on currentUser
			categoryChoice.removeAll();
			for(int i = 0; i<currentUser.getBudgetCategories().size(); i++) {
				categoryChoice.add(currentUser.getBudgetCategories().get(i).getTitle());
				if (currentUser.getBudgetCategories().get(i).getTitle().compareTo(trans.getCategory().getTitle())==0) {
					categoryChoice.select(i);
				}
			}
			DatePicker datePick = new DatePicker(trans.getDate());
					
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
			
			int result = JOptionPane.showConfirmDialog(null, myPanel, "Edit Transaction", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				//Edit transaction
				Category cat = null;
				String catID = categoryChoice.getSelectedItem();
				double amount = Double.parseDouble(amountField.getText());
				Date date = datePick.getDate();
				
				if(rdbtnExpense.isSelected()) {
					amount = 0 - amount;
				}
				
				for(int i=0; i<currentUser.getBudgetCategories().size(); i++) {
					if(catID.compareTo(currentUser.getBudgetCategories().get(i).getTitle()) == 0) {
						cat = currentUser.getBudgetCategories().get(i);
					}
				}
				
				trans.setCategory(cat);
				currentUser.setAcctBalance(currentUser.getAcctBalance()+(amount-trans.getValue()));
				trans.setValue(amount);
				trans.setDate(date);
				view.getLblBalanceAmt().setText(Double.toString(currentUser.getAcctBalance()));
			}
			populateTransactions();
		}
	}
	
	private void deleteTransaction(){
		Transaction trans = selectTransaction();
		if (trans != null) {
			//Delete stuff
			currentUser.removeTransaction(trans);
			view.getLblBalanceAmt().setText(Double.toString(currentUser.getAcctBalance()));
			populateTransactions();
		}
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	@SuppressWarnings("deprecation")
	public String removeTime(Date dateWithTime) {
		
		String dateWithoutTime, day, month, date, year = "";
		switch(dateWithTime.getDay()) {
			case 0:
				day = "Sun";
				break;
			case 1:
				day = "Mon";
				break;
			case 2:
				day = "Tue";
				break;
			case 3:
				day = "Wed";
				break;
			case 4:
				day = "Thu";
				break;
			case 5:
				day = "Fri";
				break;
			case 6:
				day = "Sat";
				break;
			default:
				day = "Sun";
		}
		switch(dateWithTime.getMonth()) {
			case 0:
				month = "Jan";
				break;
			case 1:
				month = "Feb";
				break;
			case 2:
				month = "Mar";
				break;
			case 3:
				month = "Apr";
				break;
			case 4:
				month = "May";
				break;
			case 5:
				month = "Jun";
				break;
			case 6:
				month = "Jul";
				break;	
			case 7:
				month = "Aug";
				break;
			case 8:
				month = "Sep";
				break;
			case 9:
				month = "Oct";
				break;	
			case 10:
				month = "Nov";
				break;	
			case 11:
				month = "Dec";
				break;
			default:
				month = "Jan";
		}
		date = "" + dateWithTime.getDate();
		year = "" + (dateWithTime.getYear()+1900);
		dateWithoutTime = day + " " + month + " " + date + " " + year;
		return dateWithoutTime;		
	}
	
}