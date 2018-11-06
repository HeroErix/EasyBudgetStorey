package UI;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class reView extends JFrame {
	private JButton btnNewAccount;
	private Choice choice;
	private JLabel lblBalanceAmt;
	private JMenuItem mntmNewCatagory;
	private JMenuItem mntmNewTransaction;
	private JPanel transactionPanel;
	private JTabbedPane tabbedPane;
	private JTextArea transactionList;
	private JTextArea categoryList;
	private JMenuItem mntmEditCatagory;
	private JMenuItem mntmEditTransaction;
	private JMenuItem mntmDeleteCatagory;
	private JMenuItem mntmDeleteTransaction;
	public reView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 489);
		setTitle("Easy Budget");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JPanel accountPanel = new JPanel();
		tabbedPane.addTab("Account", null, accountPanel, null);
		accountPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel newUserPanel = new JPanel();
		accountPanel.add(newUserPanel, BorderLayout.SOUTH);
		
		btnNewAccount = new JButton("New");
		newUserPanel.add(btnNewAccount);
		btnNewAccount.setSize(130, 26);
		
		JPanel accountDetailsPanel = new JPanel();
		accountPanel.add(accountDetailsPanel, BorderLayout.NORTH);
		
		JLabel lblAccountDetails = new JLabel("Account Details");
		accountDetailsPanel.add(lblAccountDetails);
		
		JPanel accountOptionsPanel = new JPanel();
		accountPanel.add(accountOptionsPanel, BorderLayout.CENTER);
		GridBagLayout gbl_accountOptionsPanel = new GridBagLayout();
		gbl_accountOptionsPanel.columnWidths = new int[]{367, 367, 0};
		gbl_accountOptionsPanel.rowHeights = new int[]{130, 130, 0};
		gbl_accountOptionsPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_accountOptionsPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		accountOptionsPanel.setLayout(gbl_accountOptionsPanel);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		accountOptionsPanel.add(lblUsername, gbc_lblUsername);
		
		choice = new Choice();
		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice.insets = new Insets(0, 0, 5, 0);
		gbc_choice.gridx = 1;
		gbc_choice.gridy = 0;
		accountOptionsPanel.add(choice, gbc_choice);
		
		JLabel lblBalance = new JLabel("Balance:");
		GridBagConstraints gbc_lblBalance = new GridBagConstraints();
		gbc_lblBalance.fill = GridBagConstraints.VERTICAL;
		gbc_lblBalance.insets = new Insets(0, 0, 0, 5);
		gbc_lblBalance.gridx = 0;
		gbc_lblBalance.gridy = 1;
		accountOptionsPanel.add(lblBalance, gbc_lblBalance);
		
		lblBalanceAmt = new JLabel("NULL");
		GridBagConstraints gbc_lblBalanceAmt = new GridBagConstraints();
		gbc_lblBalanceAmt.fill = GridBagConstraints.VERTICAL;
		gbc_lblBalanceAmt.gridx = 1;
		gbc_lblBalanceAmt.gridy = 1;
		accountOptionsPanel.add(lblBalanceAmt, gbc_lblBalanceAmt);
		
		transactionPanel = new JPanel();
		tabbedPane.addTab("Transactions", null, transactionPanel, null);
		transactionPanel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar transactionMenuBar = new JMenuBar();
		transactionPanel.add(transactionMenuBar, BorderLayout.NORTH);
		
		JMenu mnManageTransaction = new JMenu("Manage");
		transactionMenuBar.add(mnManageTransaction);
		
		mntmNewTransaction = new JMenuItem("New Transaction");
		mnManageTransaction.add(mntmNewTransaction);
		
		mntmEditTransaction = new JMenuItem("Edit Transaction");
		mnManageTransaction.add(mntmEditTransaction);
		
		mntmDeleteTransaction = new JMenuItem("Delete Transaction");
		mnManageTransaction.add(mntmDeleteTransaction);
		
		JMenu mnSortTransaction = new JMenu("Sort");
		transactionMenuBar.add(mnSortTransaction);
		
		JMenuItem mntmSortByAmount = new JMenuItem("Sort by Amount");
		mnSortTransaction.add(mntmSortByAmount);
		
		JMenuItem mntmSortByCategory = new JMenuItem("Sort by Category");
		mnSortTransaction.add(mntmSortByCategory);
		
		JMenuItem mntmSortByDate = new JMenuItem("Sort by Date");
		mnSortTransaction.add(mntmSortByDate);
		
		JMenuItem mntmSortById = new JMenuItem("Sort by ID");
		mnSortTransaction.add(mntmSortById);
		
		JMenuItem mntmReverseOrderTransaction = new JMenuItem("Reverse order");
		mnSortTransaction.add(mntmReverseOrderTransaction);
		
		transactionList = new JTextArea();
		transactionPanel.add(transactionList, BorderLayout.CENTER);
		
		JPanel categoryPanel = new JPanel();
		tabbedPane.addTab("Categories", null, categoryPanel, null);
		categoryPanel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar categoryMenuBar = new JMenuBar();
		categoryPanel.add(categoryMenuBar, BorderLayout.NORTH);
		
		JMenu mnManageCatagory = new JMenu("Manage");
		categoryMenuBar.add(mnManageCatagory);
		
		mntmNewCatagory = new JMenuItem("New Catagory");
		mnManageCatagory.add(mntmNewCatagory);
		
		mntmEditCatagory = new JMenuItem("Edit Catagory");
		mnManageCatagory.add(mntmEditCatagory);
		
		mntmDeleteCatagory = new JMenuItem("Delete Catagory");
		mnManageCatagory.add(mntmDeleteCatagory);
		
		JMenu mnSortCatagory = new JMenu("Sort");
		categoryMenuBar.add(mnSortCatagory);
		
		JMenuItem mntmSortByLimit = new JMenuItem("Sort by Limit");
		mnSortCatagory.add(mntmSortByLimit);
		
		JMenuItem mntmSortByTitle = new JMenuItem("Sort by Title");
		mnSortCatagory.add(mntmSortByTitle);
		
		JMenuItem mntmReverseOrderCatagory = new JMenuItem("Reverse order");
		mnSortCatagory.add(mntmReverseOrderCatagory);
		
		categoryList = new JTextArea();
		categoryPanel.add(categoryList, BorderLayout.CENTER);
		
		JPanel statsPanel = new JPanel();
		tabbedPane.addTab("Statistics", null, statsPanel, null);
	}

	public JButton getBtnNewAccount() {
		return btnNewAccount;
	}
	public Choice getChoice() {
		return choice;
	}
	public JLabel getLblBalanceAmt() {
		return lblBalanceAmt;
	}
	public JMenuItem getMntmNewCatagory() {
		return mntmNewCatagory;
	}
	public JMenuItem getMntmNewTransaction() {
		return mntmNewTransaction;
	}
	public JPanel getTransactionPanel() {
		return transactionPanel;
	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	public JTextArea getTransactionList() {
		return transactionList;
	}
	public JTextArea getCategoryList() {
		return categoryList;
	}
	public JMenuItem getMntmEditCatagory() {
		return mntmEditCatagory;
	}
	public JMenuItem getMntmEditTransaction() {
		return mntmEditTransaction;
	}
	public JMenuItem getMntmDeleteCatagory() {
		return mntmDeleteCatagory;
	}
	public JMenuItem getMntmDeleteTransaction() {
		return mntmDeleteTransaction;
	}
}
