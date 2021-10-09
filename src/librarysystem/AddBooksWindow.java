package librarysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.LoginException;
import business.SystemController;

public class AddBooksWindow extends JFrame implements LibWindow {
    
	public static final AddBooksWindow INSTANCE = new AddBooksWindow();
	public SystemController systemController = new SystemController();
	private boolean isInitialized = false;
	private ControllerInterface ci;
	
	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;
	
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;
	private JList lstAuthor;
	private String[] listAuthors;
	
	private JTextField jISBN, jTitle, jMaximumLength, jNumCopies;
	private JLabel label;
	private JButton addBookButton;
	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	
	public void clear() {
		jISBN.setText("");
		jTitle.setText("");
		jMaximumLength.setText("");
		jNumCopies.setText("");
	}
	
	/* This class is a singleton */
    private AddBooksWindow () {}
    
    public void init() {   
    		initInstances();
    		mainPanel = new JPanel();
    		defineUpperHalf();
    		defineMiddleHalf();
    		defineLowerHalf();
    		BorderLayout bl = new BorderLayout();
    		bl.setVgap(30);
    		mainPanel.setLayout(bl);
    					
    		mainPanel.add(upperHalf, BorderLayout.NORTH);
    		mainPanel.add(middleHalf, BorderLayout.CENTER);
    		mainPanel.add(lowerHalf, BorderLayout.SOUTH);
    		getContentPane().add(mainPanel);
    		isInitialized(true);
    		pack();
    		//setSize(660, 500);

    	
    }
    
    private void initInstances() {
    	ci = new SystemController();
    	
    	listAuthors = ci.allAuthors().toArray(new String[0]);
    	lstAuthor = new JList(listAuthors);
	}
    
    private void defineUpperHalf() {
    		
    		upperHalf = new JPanel();
    		upperHalf.setLayout(new BorderLayout());
    		defineTopPanel();
    		defineMiddlePanel();
    		defineLowerPanel();
    		upperHalf.add(topPanel, BorderLayout.NORTH);
    		upperHalf.add(middlePanel, BorderLayout.CENTER);
    		upperHalf.add(lowerPanel, BorderLayout.SOUTH);
    		
    	}
    	private void defineMiddleHalf() {
    		middleHalf = new JPanel();
    		middleHalf.setLayout(new BorderLayout());
    		JSeparator s = new JSeparator();
    		s.setOrientation(SwingConstants.HORIZONTAL);
    		//middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
    		middleHalf.add(s, BorderLayout.SOUTH);
    		
    	}
    	private void defineLowerHalf() {

    		lowerHalf = new JPanel();
    		lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));
    		
    		JButton backButton = new JButton("<= Back to Main");
    		addBackButtonListener(backButton);
    		lowerHalf.add(backButton);
    		
    	}
    	private void defineTopPanel() {
    		topPanel = new JPanel();
    		JPanel intPanel = new JPanel(new BorderLayout());
    		intPanel.add(Box.createRigidArea(new Dimension(0,20)), BorderLayout.NORTH);
    		JLabel loginLabel = new JLabel("Add new book");
    		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
    		intPanel.add(loginLabel, BorderLayout.CENTER);
    		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    		topPanel.add(intPanel);
    		
    	}
    	
    	
    	
    	private void defineMiddlePanel() {
    		middlePanel=new JPanel();
    		middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    		defineLeftTextPanel();
    		defineRightTextPanel();
    		middlePanel.add(leftTextPanel);
    		middlePanel.add(rightTextPanel);
    	}
    	private void defineLowerPanel() {
    		lowerPanel = new JPanel();
    		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
    		
    		label = new JLabel("Select Authors");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		lowerPanel.add(label);
    		label.setAlignmentX(CENTER_ALIGNMENT);
    		
    		lstAuthor.setFixedCellHeight(25);
    		lstAuthor.setFixedCellWidth(200);
    		lstAuthor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    		lstAuthor.setVisibleRowCount(4);
    		lowerPanel.add(new JScrollPane(lstAuthor));
    		
    		addBookButton = new JButton("Add book");
    		addBookListener(addBookButton);
    		lowerPanel.add(addBookButton);
    		addBookButton.setAlignmentX(CENTER_ALIGNMENT);
    	}

    	private void defineLeftTextPanel() {
    		
    		leftTextPanel = new JPanel();
    		leftTextPanel.setLayout(new BoxLayout(leftTextPanel, BoxLayout.Y_AXIS));		
    		
    		jISBN = new JTextField(10);
    		label = new JLabel("ISBN");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		leftTextPanel.add(jISBN);
    		leftTextPanel.add(label);
    		label.setAlignmentX(LEFT_ALIGNMENT);
    		jISBN.setAlignmentX(LEFT_ALIGNMENT);
    		
    		jNumCopies = new JTextField(10);
    		label = new JLabel("Number copies");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		leftTextPanel.add(jNumCopies);
    		leftTextPanel.add(label);
    		label.setAlignmentX(LEFT_ALIGNMENT);
    		jNumCopies.setAlignmentX(LEFT_ALIGNMENT);
    	}
    	private void defineRightTextPanel() {
    		
    		rightTextPanel = new JPanel();
    		rightTextPanel.setLayout(new BoxLayout(rightTextPanel, BoxLayout.Y_AXIS));
    		
    		jTitle = new JTextField(10);
    		label = new JLabel("Title");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		rightTextPanel.add(jTitle);
    		rightTextPanel.add(label);
    		label.setAlignmentX(TOP_ALIGNMENT);
    		jTitle.setAlignmentX(TOP_ALIGNMENT);
    		
    		jMaximumLength = new JTextField(10);
    		label = new JLabel("Max checkout length");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		rightTextPanel.add(jMaximumLength);
    		rightTextPanel.add(label);
    		label.setAlignmentX(TOP_ALIGNMENT);
    		jMaximumLength.setAlignmentX(TOP_ALIGNMENT);
    	}
    	
    	private void addBackButtonListener(JButton butn) {
    		butn.addActionListener(evt -> {
    			LibrarySystem.hideAllWindows();
    			if(SystemController.currentAuth != null) {
    				LibrarySystem.INSTANCE.init();
    				LibrarySystem.INSTANCE.updateUiAccordingToAuth(systemController.currentAuth);
        			LibrarySystem.INSTANCE.updateMenuBarAfterLogin();
    			}
    			LibrarySystem.INSTANCE.setVisible(true);
    		});
    	}
    	
    	private void addBookListener(JButton butn) {
    		butn.addActionListener(evt -> {
    			try {
    				//System.out.println(lstAuthor.getSelectedValuesList());
					ci.saveBook(getISBN(), getTitleBook(),getAuthor(), getMaximumLength(), getNumCopies() );
					displayMessage("Add Book saved!");
				} catch (LibrarySystemException e) {
					displayMessage(e.getMessage());
				}
    		});
    	}
    	
    	private String getISBN() {
    		return jISBN.getText();
    	}
    	
    	private String getTitleBook(){
    		return jTitle.getText();
    	}
    	
    	@SuppressWarnings("unchecked")
		private String[] getAuthor() {
    		return (String[]) lstAuthor.getSelectedValuesList().toArray(new String[0]);
    	}
    	
    	private int getMaximumLength() {
    		try {
    			return Integer.parseInt(jMaximumLength.getText());
    		}catch(Exception e) {
    			return 0;
    		}
    	}
    	
    	private int getNumCopies() {
    		try {
    			return Integer.parseInt(jNumCopies.getText());
    		}catch(Exception e) {
    			return 0;
    		}
    	}
    	
    	private void displayMessage(String message){
    		JOptionPane.showMessageDialog(INSTANCE,message);
    	}
    	
    	public static void main(String[] args) {
    		AddBooksWindow aa = new AddBooksWindow();
    		aa.init();
    		aa.pack();
    		aa.setVisible(true);
		}
	
    	private static final long serialVersionUID = 4796512019460077930L;
}
