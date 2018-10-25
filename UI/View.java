package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JRadioButton;

import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Choice;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JLabel lblEnterName;
	private JButton btnCreateAccount;
	private JButton btnEnterTransaction;
	private JButton btnShowRecentTransactions;
	private JButton btnAccount;
	private JTextField amountField;
	private JLabel lblAmount;
	private JRadioButton rdbtnIncome; 
	private JRadioButton rdbtnExpense;
	private JLabel lblEnterSavings;
	private JTextField balanceField;
	private JLabel lblEnterANew;
	private JPanel acctDetailsPanel;
	private JLabel lblUsername;
	private JLabel lblBalance;
	private JLabel lblBalanceAmt;
	private JLabel lblEnterPassword;
	private JButton btnEnterNewTransaction;
	private JPanel transactionPanel;
	private JPanel addUserPanel;
	private JPanel menuPanel;
	private JButton btnToggleMenu;
	private JLabel lblEasyBudget;
	private JButton btnAddUser;
	private JTextField passwordField;
	private ButtonGroup transactType;
	private JLabel lblNewAccount;
	private JLabel lblAccountDetails;
	private Choice userChoice;
	private Choice categoryChoice;
	private JLabel lblCategory;
	private JButton btnShowCategories;
	private JButton btnAddCategory;
	private JPanel newCategoyPanel;
	private JLabel lblEnterANew_1;
	private JLabel lblId;
	private JTextField categoryIDField;
	private JTextField categoryLimitField;
	private JLabel lblLimit;
	private JButton btnEnterCategory;
	private JPanel recordPanel;
	private JLabel lblHistory;


	/**
	 * Create the frame.
	 */
	public View() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		transactType = new ButtonGroup();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 740, 401);
		contentPane.add(tabbedPane);
		
		newCategoyPanel = new JPanel();
		tabbedPane.addTab("New Category", null, newCategoyPanel, null);
		newCategoyPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		newCategoyPanel.setLayout(null);
		
		lblEnterANew_1 = new JLabel("ENTER A NEW CATEGORY");
		lblEnterANew_1.setBounds(42, 6, 157, 16);
		newCategoyPanel.add(lblEnterANew_1);
		
		lblId = new JLabel("ID:");
		lblId.setBounds(6, 31, 37, 16);
		newCategoyPanel.add(lblId);
		
		categoryIDField = new JTextField();
		categoryIDField.setBounds(69, 26, 130, 26);
		newCategoyPanel.add(categoryIDField);
		categoryIDField.setColumns(10);
		
		lblLimit = new JLabel("Limit:");
		lblLimit.setBounds(6, 65, 61, 16);
		newCategoyPanel.add(lblLimit);
		
		categoryLimitField = new JTextField();
		categoryLimitField.setBounds(69, 60, 130, 26);
		newCategoyPanel.add(categoryLimitField);
		categoryLimitField.setColumns(10);
		
		btnEnterCategory = new JButton("Enter Category");
		btnEnterCategory.setBounds(122, 91, 117, 29);
		newCategoyPanel.add(btnEnterCategory);
		
		acctDetailsPanel = new JPanel();
		tabbedPane.addTab("Account", null, acctDetailsPanel, null);
		acctDetailsPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLACK, null));
		acctDetailsPanel.setLayout(null);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(6, 58, 164, 39);
		acctDetailsPanel.add(lblUsername);
		
		lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(6, 98, 140, 39);
		acctDetailsPanel.add(lblBalance);
		
		lblBalanceAmt = new JLabel("NULL");
		lblBalanceAmt.setBounds(115, 103, 140, 28);
		acctDetailsPanel.add(lblBalanceAmt);
		
		lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setBounds(6, 7, 265, 65);
		acctDetailsPanel.add(lblAccountDetails);
		
		userChoice = new Choice();
		userChoice.setBounds(148, 58, 154, 22);
		acctDetailsPanel.add(userChoice);
		
		recordPanel = new JPanel();
		tabbedPane.addTab("Records", null, recordPanel, null);
		recordPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		recordPanel.setLayout(null);
		
		lblHistory = new JLabel("History");
		lblHistory.setBounds(93, 6, 61, 16);
		recordPanel.add(lblHistory);
		
		btnToggleMenu = new JButton("Hide Menu");
		tabbedPane.addTab("Menu Btn", null, btnToggleMenu, null);
		btnToggleMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		lblEasyBudget = new JLabel("EASY BUDGET");
		tabbedPane.addTab("Label", null, lblEasyBudget, null);
		lblEasyBudget.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		
		menuPanel = new JPanel();
		tabbedPane.addTab("Menu", null, menuPanel, null);
		menuPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		menuPanel.setLayout(null);
		
		btnAccount = new JButton("Account Details ");
		btnAccount.setEnabled(false);
		btnAccount.setBounds(13, 47, 138, 29);
		menuPanel.add(btnAccount);
		
		btnShowRecentTransactions = new JButton("Transaction History");
		btnShowRecentTransactions.setBounds(13, 131, 151, 26);
		menuPanel.add(btnShowRecentTransactions);
		btnShowRecentTransactions.setEnabled(false);
		
		btnEnterNewTransaction = new JButton("Enter New Transaction");
		btnEnterNewTransaction.setEnabled(false);
		
		btnEnterNewTransaction.setBounds(13, 88, 175, 29);
		menuPanel.add(btnEnterNewTransaction);
		
		btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(13, 6, 117, 29);
		menuPanel.add(btnAddUser);
		
		btnShowCategories = new JButton("Show Categories");
		btnShowCategories.setEnabled(false);
		btnShowCategories.setBounds(13, 210, 138, 29);
		menuPanel.add(btnShowCategories);
		
		btnAddCategory = new JButton("Add Category");
		btnAddCategory.setEnabled(false);
		btnAddCategory.setBounds(13, 169, 117, 29);
		menuPanel.add(btnAddCategory);
		
		addUserPanel = new JPanel();
		tabbedPane.addTab("New User", null, addUserPanel, null);
		addUserPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		addUserPanel.setLayout(null);
		
		lblEnterName = new JLabel("Enter Name:");
		lblEnterName.setBounds(6, 41, 76, 16);
		addUserPanel.add(lblEnterName);
		
		lblEnterPassword = new JLabel("Enter Password:");
		lblEnterPassword.setBounds(6, 69, 99, 21);
		addUserPanel.add(lblEnterPassword);
		
		lblEnterSavings = new JLabel("Enter Savings:");
		lblEnterSavings.setBounds(6, 102, 99, 16);
		addUserPanel.add(lblEnterSavings);
		
		nameField = new JTextField();
		nameField.setBounds(117, 36, 123, 26);
		addUserPanel.add(nameField);
		nameField.setColumns(10);
		
		balanceField = new JTextField();
		balanceField.setBounds(117, 97, 123, 26);
		addUserPanel.add(balanceField);
		balanceField.setColumns(10);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(77, 138, 163, 29);
		addUserPanel.add(btnCreateAccount);
		
		passwordField = new JTextField();
		passwordField.setBounds(117, 66, 122, 26);
		addUserPanel.add(passwordField);
		passwordField.setColumns(10);
		
		lblNewAccount = new JLabel("New Account");
		lblNewAccount.setBounds(77, 13, 83, 16);
		addUserPanel.add(lblNewAccount);
		
		transactionPanel = new JPanel();
		tabbedPane.addTab("Transaction", null, transactionPanel, null);
		transactionPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		transactionPanel.setLayout(null);
		
		lblEnterANew = new JLabel("ENTER A NEW TRANSACTION");
		lblEnterANew.setBounds(33, 6, 181, 16);
		transactionPanel.add(lblEnterANew);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(6, 38, 81, 16);
		transactionPanel.add(lblAmount);
		
		amountField = new JTextField();
		amountField.setBounds(88, 33, 151, 26);
		transactionPanel.add(amountField);
		amountField.setColumns(10);
		
		rdbtnIncome = new JRadioButton("Income");
		rdbtnIncome.setBounds(155, 61, 81, 23);
		transactionPanel.add(rdbtnIncome);
		
		rdbtnExpense = new JRadioButton("Expense");
		rdbtnExpense.setBounds(55, 61, 89, 23);
		transactionPanel.add(rdbtnExpense);
		transactType.add(rdbtnIncome);
		transactType.add(rdbtnExpense);
		
		btnEnterTransaction = new JButton("Enter Transaction");
		btnEnterTransaction.setBounds(96, 130, 143, 29);
		transactionPanel.add(btnEnterTransaction);
		
		lblCategory = new JLabel("Category: ");
		lblCategory.setBounds(6, 96, 70, 16);
		transactionPanel.add(lblCategory);
		
		categoryChoice = new Choice();
		categoryChoice.setBounds(92, 90, 143, 27);
		transactionPanel.add(categoryChoice);
		
	}



	public JPanel getContentPane() {
		return contentPane;
	}


	public JTextField getNameField() {
		return nameField;
	}



	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}



	public JLabel getLblEnterName() {
		return lblEnterName;
	}



	public void setLblEnterName(JLabel lblEnterName) {
		this.lblEnterName = lblEnterName;
	}



	public JButton getBtnCreateAccount() {
		return btnCreateAccount;
	}



	public void setBtnCreateAccount(JButton btnCreateAccount) {
		this.btnCreateAccount = btnCreateAccount;
	}



	public JButton getBtnEnterTransaction() {
		return btnEnterTransaction;
	}



	public void setBtnEnterTransaction(JButton btnEnterTransaction) {
		this.btnEnterTransaction = btnEnterTransaction;
	}



	public JButton getBtnShowRecentTransactions() {
		return btnShowRecentTransactions;
	}



	public void setBtnShowRecentTransactions(JButton btnShowRecentTransactions) {
		this.btnShowRecentTransactions = btnShowRecentTransactions;
	}



	public JButton getBtnAccount() {
		return btnAccount;
	}



	public void setBtnAccount(JButton btnAccount) {
		this.btnAccount = btnAccount;
	}



	public JTextField getAmountField() {
		return amountField;
	}



	public void setAmountField(JTextField amountField) {
		this.amountField = amountField;
	}



	public JTextField getPasswordField() {
		return passwordField;
	}



	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}



	public JLabel getLblAmount() {
		return lblAmount;
	}



	public void setLblAmount(JLabel lblAmount) {
		this.lblAmount = lblAmount;
	}



	public JRadioButton getRdbtnIncome() {
		return rdbtnIncome;
	}



	public void setRdbtnIncome(JRadioButton rdbtnIncome) {
		this.rdbtnIncome = rdbtnIncome;
	}



	public JRadioButton getRdbtnExpense() {
		return rdbtnExpense;
	}



	public void setRdbtnExpense(JRadioButton rdbtnExpense) {
		this.rdbtnExpense = rdbtnExpense;
	}



	public JLabel getLblEnterSavings() {
		return lblEnterSavings;
	}



	public void setLblEnterSavings(JLabel lblEnterSavings) {
		this.lblEnterSavings = lblEnterSavings;
	}



	public JTextField getBalanceField() {
		return balanceField;
	}



	public void setBalanceField(JTextField textField_1) {
		this.balanceField = textField_1;
	}



	public JLabel getLblEnterANew() {
		return lblEnterANew;
	}



	public void setLblEnterANew(JLabel lblEnterANew) {
		this.lblEnterANew = lblEnterANew;
	}



	public JPanel getAcctDetailsPanel() {
		return acctDetailsPanel;
	}



	public void setAcctDetailsPanel(JPanel acctDetailsPanel) {
		this.acctDetailsPanel = acctDetailsPanel;
	}



	public JLabel getLblUsername() {
		return lblUsername;
	}



	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}


	public JLabel getLblBalance() {
		return lblBalance;
	}



	public void setLblBalance(JLabel lblBalance) {
		this.lblBalance = lblBalance;
	}



	public JLabel getLblBalanceAmt() {
		return lblBalanceAmt;
	}



	public void setLblBalanceAmt(JLabel lblBalanceAmt) {
		this.lblBalanceAmt = lblBalanceAmt;
	}



	public JLabel getLblEnterPassword() {
		return lblEnterPassword;
	}



	public void setLblEnterPassword(JLabel lblEnterPassword) {
		this.lblEnterPassword = lblEnterPassword;
	}



	public JButton getBtnEnterNewTransaction() {
		return btnEnterNewTransaction;
	}



	public void setBtnEnterNewTransaction(JButton btnEnterNewTransaction) {
		this.btnEnterNewTransaction = btnEnterNewTransaction;
	}



	public JPanel getTransactionPanel() {
		return transactionPanel;
	}



	public void setTransactionPanel(JPanel transactionPanel) {
		this.transactionPanel = transactionPanel;
	}



	public JPanel getAddUserPanel() {
		return addUserPanel;
	}



	public void setAddUserPanel(JPanel addUserPanel) {
		this.addUserPanel = addUserPanel;
	}



	public JPanel getMenuPanel() {
		return menuPanel;
	}



	public void setMenuPanel(JPanel menuPanel) {
		this.menuPanel = menuPanel;
	}



	public JButton getBtnToggleMenu() {
		return btnToggleMenu;
	}



	public void setBtnToggleMenu(JButton btnToggleMenu) {
		this.btnToggleMenu = btnToggleMenu;
	}



	public JLabel getLblEasyBudget() {
		return lblEasyBudget;
	}



	public void setLblEasyBudget(JLabel lblEasyBudget) {
		this.lblEasyBudget = lblEasyBudget;
	}



	public JButton getBtnAddUser() {
		return btnAddUser;
	}



	public void setBtnAddUser(JButton btnAddUser) {
		this.btnAddUser = btnAddUser;
	}



	public ButtonGroup getTransactType() {
		return transactType;
	}



	public void setTransactType(ButtonGroup transactType) {
		this.transactType = transactType;
	}



	public JLabel getLblNewAccount() {
		return lblNewAccount;
	}



	public void setLblNewAccount(JLabel lblNewAccount) {
		this.lblNewAccount = lblNewAccount;
	}



	public JLabel getLblAccountDetails() {
		return lblAccountDetails;
	}



	public void setLblAccountDetails(JLabel lblAccountDetails) {
		this.lblAccountDetails = lblAccountDetails;
	}



	public Choice getUserChoice() {
		return userChoice;
	}



	public void setUserChoice(Choice userChoice) {
		this.userChoice = userChoice;
	}



	public Choice getCategoryChoice() {
		return categoryChoice;
	}



	public void setCategoryChoice(Choice categoryChoice) {
		this.categoryChoice = categoryChoice;
	}



	public JLabel getLblCategory() {
		return lblCategory;
	}



	public void setLblCategory(JLabel lblCategory) {
		this.lblCategory = lblCategory;
	}


	public JButton getBtnShowCategories() {
		return btnShowCategories;
	}



	public void setBtnShowCategories(JButton btnShowCategories) {
		this.btnShowCategories = btnShowCategories;
	}



	public JButton getBtnAddCategory() {
		return btnAddCategory;
	}



	public void setBtnAddCategory(JButton btnAddCategory) {
		this.btnAddCategory = btnAddCategory;
	}



	public JPanel getNewCategoyPanel() {
		return newCategoyPanel;
	}



	public void setNewCategoyPanel(JPanel newCategoyPanel) {
		this.newCategoyPanel = newCategoyPanel;
	}



	public JLabel getLblEnterANew_1() {
		return lblEnterANew_1;
	}



	public void setLblEnterANew_1(JLabel lblEnterANew_1) {
		this.lblEnterANew_1 = lblEnterANew_1;
	}



	public JLabel getLblId() {
		return lblId;
	}



	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}



	public JTextField getCategoryIDField() {
		return categoryIDField;
	}



	public void setCategoryIDField(JTextField categoryIDField) {
		this.categoryIDField = categoryIDField;
	}



	public JTextField getCategoryLimitField() {
		return categoryLimitField;
	}



	public void setCategoryLimitField(JTextField categoryLimitField) {
		this.categoryLimitField = categoryLimitField;
	}



	public JLabel getLblLimit() {
		return lblLimit;
	}



	public void setLblLimit(JLabel lblLimit) {
		this.lblLimit = lblLimit;
	}



	public JButton getBtnEnterCategory() {
		return btnEnterCategory;
	}



	public void setBtnEnterCategory(JButton btnEnterCategory) {
		this.btnEnterCategory = btnEnterCategory;
	}



	public JPanel getRecordPanel() {
		return recordPanel;
	}



	public void setRecordPanel(JPanel recordPanel) {
		this.recordPanel = recordPanel;
	}



	public JLabel getLblHistory() {
		return lblHistory;
	}



	public void setLblHistory(JLabel lblHistory) {
		this.lblHistory = lblHistory;
	}
}