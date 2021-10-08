package librarysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

public class AddBookCopy extends JFrame implements LibWindow {

	public static final AddBookCopy INSTANCE = new AddBookCopy();
	
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
	
	private JTextField bookID;
	private JLabel label;
	private JButton checkoutButton;
	
	
	
	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private JTextField messageBar = new JTextField();
	public void clear() {
		messageBar.setText("");
	}
	
	/* This class is a singleton */
    private AddBookCopy () {}
    
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
    		JLabel checkoutLabel = new JLabel("Checkout Book");
    		Util.adjustLabelFont(checkoutLabel, Color.BLUE.darker(), true);
    		intPanel.add(checkoutLabel, BorderLayout.CENTER);
    		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    		topPanel.add(intPanel);
    	}
    
    	private void defineMiddlePanel() {
    		middlePanel=new JPanel();
    		middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    		defineLeftTextPanel();
    		middlePanel.add(leftTextPanel);
    	}
    	private void defineLowerPanel() {
    		lowerPanel = new JPanel();
    		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
    		checkoutButton = new JButton("Add book copy");
    		addBookCopyButtonListener(checkoutButton);
    		lowerPanel.add(checkoutButton);
    	}

    	private void defineLeftTextPanel() {
    		
    		JPanel topText = new JPanel();
    		JPanel bottomText = new JPanel();
    		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
    		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
    		
    		bookID = new JTextField(10);
    		label = new JLabel("ISBN");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		topText.add(bookID);
    		bottomText.add(label);
    		
    		leftTextPanel = new JPanel();
    		leftTextPanel.setLayout(new BorderLayout());
    		leftTextPanel.add(topText,BorderLayout.NORTH);
    		leftTextPanel.add(bottomText,BorderLayout.CENTER);
    	}
    	
    	private void addBackButtonListener(JButton butn) {
    		butn.addActionListener(evt -> {
    			LibrarySystem.hideAllWindows();
    			LibrarySystem.INSTANCE.setVisible(true);
    		});
    	}
    	
    	private void addBookCopyButtonListener(JButton butn) {
    		butn.addActionListener(evt -> {
    			try {
					ci.addBookCopy(getISBN());
					displayMessage("Add Copy saved!");
				} catch (LibrarySystemException e) {
					displayMessage(e.getMessage());
				}
    		});
    	}
    	
    	private void displayMessage(String message) {
    		JOptionPane.showMessageDialog(INSTANCE,message);
    	}
    	
    	private String getISBN() {
    		return bookID.getText();
    	}
	
    	private static final long serialVersionUID = -4881509539494674087L;
}