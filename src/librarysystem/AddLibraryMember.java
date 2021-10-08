//package librarysystem;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.swing.Box;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JSeparator;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//
//import business.Address;
//import business.ControllerInterface;
//import business.LibraryMember;
//import business.SystemController;
//import dataaccess.DataAccess;
//
//
////New class
//public class AddLibraryMember extends JFrame implements LibWindow{
//
//	public static final AddLibraryMember INSTANCE = new AddLibraryMember();
//	
//	public static final Pattern VALID_PHONE_NUMBER_ADDRESS_REGEX = 
//		    Pattern.compile("\"\\\\d{10}|(?:\\\\d{3}-){2}\\\\d{4}|\\\\(\\\\d{3}\\\\)\\\\d{3}-?\\\\d{4}\"", Pattern.CASE_INSENSITIVE);
//
//	// Components of the Form
//    private Container c;
//    private JLabel title;
//    private JLabel name;
//    private JTextField memberIdField, firstNameField, lastNameField, 
//    		streetField, cityField, stateField, zipField, telephoneField;
//    private JLabel memberIdLabel, firstNameLabel, lastNameLabel, 
//    		streetLabel, cityLabel, stateLabel, zipLabel, telephoneLabel;
//   
//    private JButton submitBtn, backBtn;
//    private JLabel res;
// 
//    public SystemController systemController = new SystemController();
//	private boolean isInitialized = false;
//	
//	private JPanel mainPanel;
//	private JPanel upperHalf;
//	private JPanel middleHalf;
//	private JPanel lowerHalf;
//	private JPanel container;
//	
//	private JPanel topPanel;
//	private JPanel middlePanel;
//	private JPanel lowerPanel;
//	private JPanel leftTextPanel;
//	private JPanel rightTextPanel;
//    
//	@Override
//	public void init() {
//		mainPanel = new JPanel();
//		defineUpperHalf();
//		defineMiddleHalf();
//		defineLowerHalf();
//		BorderLayout bl = new BorderLayout();
//		bl.setVgap(30);
//		mainPanel.setLayout(bl);
//		backBtn = new JButton();
//		mainPanel.add(upperHalf, BorderLayout.NORTH);
//		mainPanel.add(middleHalf, BorderLayout.CENTER);
//		mainPanel.add(lowerHalf, BorderLayout.SOUTH);
//		getContentPane().add(mainPanel);
//		isInitialized(true);
//		pack();
//	}
//
//	
//	private void defineUpperHalf() {
//		
//		upperHalf = new JPanel();
//		upperHalf.setLayout(new BorderLayout());
//		defineTopPanel();
//		defineMiddlePanel();
//		defineLowerPanel();
//		upperHalf.add(topPanel, BorderLayout.NORTH);
//		upperHalf.add(middlePanel, BorderLayout.CENTER);
//		upperHalf.add(lowerPanel, BorderLayout.SOUTH);
//		
//	}
//	private void defineMiddleHalf() {
//		middleHalf = new JPanel();
//		middleHalf.setLayout(new BorderLayout());
//		JSeparator s = new JSeparator();
//		s.setOrientation(SwingConstants.HORIZONTAL);
//		//middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
//		middleHalf.add(s, BorderLayout.SOUTH);
//		
//	}
//	private void defineLowerHalf() {
//
//		lowerHalf = new JPanel();
//		lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));
//		
//		JButton backButton = new JButton("<= Back to Main");
//		addBackButtonListener(backButton);
//		lowerHalf.add(backButton);
//		
//	}
//	private void defineTopPanel() {
//		topPanel = new JPanel();
//		JPanel intPanel = new JPanel(new BorderLayout());
//		intPanel.add(Box.createRigidArea(new Dimension(0,20)), BorderLayout.NORTH);
//		JLabel loginLabel = new JLabel("Add New Libray Member");
//		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
//		intPanel.add(loginLabel, BorderLayout.CENTER);
//		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		topPanel.add(intPanel);
//		
//	}
//	
//	
//	// Used gridbaglayout
//	private void defineMiddlePanel() {
//		middlePanel=new JPanel();
//		GridBagLayout grid = new GridBagLayout();  
//        GridBagConstraints gbc = new GridBagConstraints();  
//		middlePanel.setLayout(grid);
//		
//		gbc.fill = GridBagConstraints.HORIZONTAL;  
//		
//		//================== start of first row 
//		// for first row first name
//		
//		gbc.gridx = 0;  
//	    gbc.gridy = 0;
//	    memberIdLabel = new JLabel("Member ID:");
//	    middlePanel.add(memberIdLabel,gbc);
//		
//	    //for second column in first row
//	    gbc.gridx = 1;  
//	    gbc.gridy = 0;
//	    memberIdField = new JTextField(10);
//	    middlePanel.add(memberIdField, gbc);
//	    
//	    
//	    gbc.gridx = 2;  
//	    gbc.gridy = 0;
//	    firstNameLabel = new JLabel("First Name:");
//	    middlePanel.add(firstNameLabel,gbc);
//	    
//	    gbc.gridx = 3;  
//	    gbc.gridy = 0;
//	    firstNameField = new JTextField(10);
//	    middlePanel.add(firstNameField, gbc);
//	    
//	    
//	    gbc.gridx = 4;  
//	    gbc.gridy = 0;
//	    lastNameLabel = new JLabel("Last Name:");
//	    middlePanel.add(lastNameLabel, gbc);
//	    
//	    gbc.gridx = 5;  
//	    gbc.gridy = 0;
////	    gbc.gridwidth = 2;
//	    lastNameField = new JTextField(10);
//	    middlePanel.add(lastNameField,gbc);
//		
//	    
//	    //================ end of first row
//	    
//	    //================ start of second row
//	    gbc.fill = GridBagConstraints.HORIZONTAL;  
//	    
//	    gbc.gridwidth = 1;
//	    gbc.gridx = 0;  
//	    gbc.gridy = 1;
//	    streetLabel = new JLabel("Street:");
//	    middlePanel.add(streetLabel,gbc);
//	    
//	    gbc.gridx = 1;  
//	    gbc.gridy = 1;
//	    streetField = new JTextField(10);
//	    middlePanel.add(streetField, gbc);
//	    
//		gbc.gridx = 2;  
//	    gbc.gridy = 1;
//	    cityLabel = new JLabel("City:");
//	    middlePanel.add(cityLabel,gbc);
//		
//	    //for second column in first row
//	    gbc.gridx = 3;  
//	    gbc.gridy = 1;
//	    cityField = new JTextField(10);
//	    middlePanel.add(cityField, gbc);
//	    
//	    gbc.gridx = 4;  
//	    gbc.gridy = 1;
//	    stateLabel = new JLabel("State:");
//	    middlePanel.add(stateLabel,gbc);
//	    
//	    gbc.gridx = 5;  
//	    gbc.gridy = 1;
//	    stateField = new JTextField(10);
//	    middlePanel.add(stateField,gbc);
//	    
//	    gbc.gridx = 6;  
//	    gbc.gridy = 1;
//	    zipLabel = new JLabel("ZIP Code:");
//	    middlePanel.add(zipLabel,gbc);
//	    
//	    gbc.gridx = 7;  
//	    gbc.gridy = 1;
//	    zipField = new JTextField(10);
//	    middlePanel.add(zipField,gbc);
//		//================ end of second row
//	    
//	    //================ start of third row
//	    gbc.gridx = 0;  
//	    gbc.gridy = 2;
//	    telephoneLabel = new JLabel("Telephone Number:");
//	    middlePanel.add(telephoneLabel,gbc);
//	    
//	    gbc.gridx = 1;  
//	    gbc.gridy = 2;
//	    telephoneField = new JTextField(10);
//	    middlePanel.add(telephoneField, gbc);
//	    
//	    //
//		/*
//		 * submitBtn = new JButton("Submit"); addSubmitBtnActionListner(submitBtn);
//		 * 
//		 * gbc.gridx = 1; gbc.gridy = 2; telephoneField = new JTextField(10);
//		 * telephoneField.setSize(50, 20); middlePanel.add(telephoneField, gbc);
//		 */
//	    
//	}
//	
//	
//	private void defineLowerPanel() {
//		lowerPanel = new JPanel();
//		submitBtn = new JButton("Submit");
//		addSubmitBtnActionListner(submitBtn);
//		lowerPanel.add(submitBtn);
//	}
//
//	
//	@Override
//	public boolean isInitialized() {
//		return isInitialized;
//	}
//
//	@Override
//	public void isInitialized(boolean val) {
//		isInitialized = val;
//	}
//	
//	private void addSubmitBtnActionListner(JButton butn) {
//		butn.addActionListener(evt -> {
//			//String memberId, fistName, lastName, telephoneNo, street, city, state, zip;
//			
//			String memberId = memberIdField.getText().toString();
//			String fistName = firstNameField.getText().toString();
//			String lastName = lastNameField.getText().toString();
//			String telephoneNo = telephoneField.getText().toString();
//			
//			String street = streetField.getText().toString();
//			String city = cityField.getText().toString();
//			String state = stateField.getText().toString();
//			String zip = zipField.getText().toString();
//			Address address = new Address(street, city, state, zip);
//			LibraryMember libraryMemberToPresist = new LibraryMember(memberId, fistName, lastName, telephoneNo, address);
//			ControllerInterface ci = new SystemController();
//			
//			if(memberId.isBlank() || memberId.isEmpty()	){
//				JOptionPane.showMessageDialog(this,"Fields Are Empty");
//			}
//			else if(fistName.isBlank() || fistName.isEmpty()) {
//				JOptionPane.showMessageDialog(this,"First Name is Empty");
//			}
//			else if(lastName.isBlank() || lastName.isEmpty() ) {
//				JOptionPane.showMessageDialog(this,"Last Name is Empty");
//			}
//			
//			else if(street.isBlank() || street.isEmpty()) {
//				JOptionPane.showMessageDialog(this,"Street Field is Empty");
//			}
//			else if(city.isBlank() || city.isEmpty() ) {
//				JOptionPane.showMessageDialog(this,"City Field is Empty");
//			}
//			else if(state.isBlank() || state.isEmpty()) {
//				JOptionPane.showMessageDialog(this,"State Field is Empty");
//			}
//			else if(zip.isBlank() || zip.isEmpty()) {
//				JOptionPane.showMessageDialog(this,"ZIP Field is Empty");
//			}
//			else if(telephoneNo.isBlank() || telephoneNo.isEmpty()) {
//				JOptionPane.showMessageDialog(this,"Telephone Field is Empty");
//			}
//			else {
//				if(!isPhoneNumberValid(telephoneNo)){
//					JOptionPane.showMessageDialog(this,"Enter Correct Phone Number..!!");
//				}
//				else {
//					ci.saveLibraryMember(libraryMemberToPresist);
//					JOptionPane.showMessageDialog(this,"Library Member Added..!!");
//				}
//			}
//			//System.out.println("current data :" +memberId+ fistName+ lastName+ telephoneNo+ street + city+state+zip);
//
//			
//			
//
//				
//		});
//	}
//	
//	public static boolean isPhoneNumberValid(String string) {
//        Matcher matcher = VALID_PHONE_NUMBER_ADDRESS_REGEX.matcher(string);
//        return matcher.find();
//	}
//	
//	private void addBackButtonListener(JButton butn) {
//		butn.addActionListener(evt -> {
//			LibrarySystem.hideAllWindows();
//			System.out.println("back");
//			LibrarySystem.INSTANCE.setVisible(true);
//		});
//	}
//	
//}

package librarysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccess;

public class AddLibraryMember extends JFrame implements LibWindow{

	public static final AddLibraryMember INSTANCE = new AddLibraryMember();
	public static final Pattern VALID_PHONE_NUMBER_ADDRESS_REGEX =
		    Pattern.compile("\\\\d{10}|(?:\\\\d{3}-){2}\\\\d{4}|\\\\(\\\\d{3}\\\\)\\\\d{3}-?\\\\d{4}", Pattern.CASE_INSENSITIVE);

	// Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField memberIdField, firstNameField, lastNameField,
    		streetField, cityField, stateField, zipField, telephoneField;
    private JLabel memberIdLabel, firstNameLabel, lastNameLabel,
    		streetLabel, cityLabel, stateLabel, zipLabel, telephoneLabel;

    private JButton submitBtn, backBtn;
    private JLabel res;

    public SystemController systemController = new SystemController();
	private boolean isInitialized = false;

	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;
	private JPanel container;

	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;

	@Override
	public void init() {
		mainPanel = new JPanel();
		defineUpperHalf();
		defineMiddleHalf();
		defineLowerHalf();
		BorderLayout bl = new BorderLayout();
		bl.setVgap(30);
		mainPanel.setLayout(bl);
		backBtn = new JButton();
		mainPanel.add(upperHalf, BorderLayout.NORTH);
		mainPanel.add(middleHalf, BorderLayout.CENTER);
		mainPanel.add(lowerHalf, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		isInitialized(true);
		pack();
	}

	public void clear() {
		memberIdField.setText(""); 
		firstNameField.setText(""); 
		lastNameField.setText("");
		streetField.setText(""); 
		cityField.setText(""); 
		stateField.setText(""); 
		zipField.setText(""); 
		telephoneField.setText("");
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
		JLabel loginLabel = new JLabel("Add New Libray Member");
		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
		intPanel.add(loginLabel, BorderLayout.CENTER);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(intPanel);

	}



	private void defineMiddlePanel() {
		middlePanel=new JPanel();
		GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
		middlePanel.setLayout(grid);

		gbc.fill = GridBagConstraints.HORIZONTAL;

		//================== start of first row
		// for first row first name

		gbc.gridx = 0;
	    gbc.gridy = 0;
	    memberIdLabel = new JLabel("Member ID:");
	    middlePanel.add(memberIdLabel,gbc);

	    //for second column in first row
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    memberIdField = new JTextField(10);
	    middlePanel.add(memberIdField, gbc);


	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    firstNameLabel = new JLabel("First Name:");
	    middlePanel.add(firstNameLabel,gbc);

	    gbc.gridx = 3;
	    gbc.gridy = 0;
	    firstNameField = new JTextField(10);
	    middlePanel.add(firstNameField, gbc);


	    gbc.gridx = 4;
	    gbc.gridy = 0;
	    lastNameLabel = new JLabel("Last Name:");
	    middlePanel.add(lastNameLabel, gbc);

	    gbc.gridx = 5;
	    gbc.gridy = 0;
//	    gbc.gridwidth = 2;
	    lastNameField = new JTextField(10);
	    middlePanel.add(lastNameField,gbc);


	    //================ end of first row

	    //================ start of second row
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    gbc.gridwidth = 1;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    streetLabel = new JLabel("Street:");
	    middlePanel.add(streetLabel,gbc);

	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    streetField = new JTextField(10);
	    middlePanel.add(streetField, gbc);

		gbc.gridx = 2;
	    gbc.gridy = 1;
	    cityLabel = new JLabel("City:");
	    middlePanel.add(cityLabel,gbc);

	    //for second column in first row
	    gbc.gridx = 3;
	    gbc.gridy = 1;
	    cityField = new JTextField(10);
	    middlePanel.add(cityField, gbc);

	    gbc.gridx = 4;
	    gbc.gridy = 1;
	    stateLabel = new JLabel("State:");
	    middlePanel.add(stateLabel,gbc);

	    gbc.gridx = 5;
	    gbc.gridy = 1;
	    stateField = new JTextField(10);
	    middlePanel.add(stateField,gbc);

	    gbc.gridx = 6;
	    gbc.gridy = 1;
	    zipLabel = new JLabel("ZIP Code:");
	    middlePanel.add(zipLabel,gbc);

	    gbc.gridx = 7;
	    gbc.gridy = 1;
	    zipField = new JTextField(10);
	    middlePanel.add(zipField,gbc);
		//================ end of second row

	    //================ start of third row
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    telephoneLabel = new JLabel("Telephone Number:");
	    middlePanel.add(telephoneLabel,gbc);

	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    telephoneField = new JTextField(10);
	    middlePanel.add(telephoneField, gbc);

	    //
		/*
		 * submitBtn = new JButton("Submit"); addSubmitBtnActionListner(submitBtn);
		 *
		 * gbc.gridx = 1; gbc.gridy = 2; telephoneField = new JTextField(10);
		 * telephoneField.setSize(50, 20); middlePanel.add(telephoneField, gbc);
		 */

	}


	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		submitBtn = new JButton("Submit");
		addSubmitBtnActionListner(submitBtn);
		lowerPanel.add(submitBtn);
	}


	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private void addSubmitBtnActionListner(JButton butn) {
		butn.addActionListener(evt -> {
			//String memberId, fistName, lastName, telephoneNo, street, city, state, zip;

			String memberId = memberIdField.getText().toString();
			String fistName = firstNameField.getText().toString();
			String lastName = lastNameField.getText().toString();
			String telephoneNo = telephoneField.getText().toString();

			String street = streetField.getText().toString();
			String city = cityField.getText().toString();
			String state = stateField.getText().toString();
			String zip = zipField.getText().toString();
			Address address = new Address(street, city, state, zip);
			LibraryMember libraryMemberToPresist = new LibraryMember(memberId, fistName, lastName, telephoneNo, address);
			ControllerInterface ci = new SystemController();

			if(memberId.isBlank() || memberId.isEmpty()	){
				JOptionPane.showMessageDialog(this,"Fields Are Empty");
			}
			else if(fistName.isBlank() || fistName.isEmpty()) {
				JOptionPane.showMessageDialog(this,"First Name is Empty");
			}
			else if(lastName.isBlank() || lastName.isEmpty() ) {
				JOptionPane.showMessageDialog(this,"Last Name is Empty");
			}

			else if(street.isBlank() || street.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Street Field is Empty");
			}
			else if(city.isBlank() || city.isEmpty() ) {
				JOptionPane.showMessageDialog(this,"City Field is Empty");
			}
			else if(state.isBlank() || state.isEmpty()) {
				JOptionPane.showMessageDialog(this,"State Field is Empty");
			}
			else if(zip.isBlank() || zip.isEmpty()) {
				JOptionPane.showMessageDialog(this,"ZIP Field is Empty");
			}
			else if(telephoneNo.isBlank() || telephoneNo.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Telephone Field is Empty");
			}
			else {
				//if(isPhoneNumberValid(telephoneNo)){
					ci.saveLibraryMember(libraryMemberToPresist);
					JOptionPane.showMessageDialog(this,"Library Member Added..!!");
				//}
				//else {
				//	JOptionPane.showMessageDialog(this,"Enter Correct Phone Number..!!");
				//}
			}
			//System.out.println("current data :" +memberId+ fistName+ lastName+ telephoneNo+ street + city+state+zip);





		});
	}

	public static boolean isPhoneNumberValid(String string) {
        Matcher matcher = VALID_PHONE_NUMBER_ADDRESS_REGEX.matcher(string);
        return matcher.matches();
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			System.out.println("back");
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}

}

















