package librarysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

public class CheckoutBookWindow extends JFrame implements LibWindow {

	public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();
	
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
	
	private JTextField memberID;
	private JTextField isbn;
	private JLabel label;
	private JButton checkoutButton;
	private CustomTableModel tableModel;
	private JScrollPane scrollPane;
	private JTable table;
	
	private List<CheckoutRecord> listCheckout;
	private List<String[]> listCheck;
	
	private static final int SCREEN_WIDTH = 640;
	private static final int SCREEN_HEIGHT = 480;
	private static final int TABLE_WIDTH = (int) (0.75 * SCREEN_WIDTH);
    private static final int DEFAULT_TABLE_HEIGHT = (int) (0.75 * SCREEN_HEIGHT);
    private final float [] COL_WIDTH_PROPORTIONS =
    	{0.25f, 0.25f, 0.25f, 0.25f};
    private final String[] DEFAULT_COLUMN_HEADERS 
	   = { "Member ID", "ISBN", "Checkout date", "Due Data" };
	
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
    private CheckoutBookWindow () {}
    
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
    }
    
    private void initInstances() {
    	ci = new SystemController();
    	listCheckout = ci.allCheckoutBook();
    	
    	tableModel = new CustomTableModel();
    	listCheck = new ArrayList<>();
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
    		defineRightTextPanel();
    		middlePanel.add(leftTextPanel);
    		middlePanel.add(rightTextPanel);
    	}
    	private void defineLowerPanel() {
    		lowerPanel = new JPanel();
    		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
    		checkoutButton = new JButton("Checkout book");
    		validaCheckoutBookButtonListener(checkoutButton);
    		lowerPanel.add(checkoutButton);
    		tableCheckoutBook();
    	}

    	private void defineLeftTextPanel() {
    		
    		JPanel topText = new JPanel();
    		JPanel bottomText = new JPanel();
    		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
    		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
    		
    		memberID = new JTextField(10);
    		label = new JLabel("Member ID");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		topText.add(memberID);
    		bottomText.add(label);
    		
    		leftTextPanel = new JPanel();
    		leftTextPanel.setLayout(new BorderLayout());
    		leftTextPanel.add(topText,BorderLayout.NORTH);
    		leftTextPanel.add(bottomText,BorderLayout.CENTER);
    	}
    	private void defineRightTextPanel() {
    		
    		JPanel topText = new JPanel();
    		JPanel bottomText = new JPanel();
    		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
    		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
    		
    		isbn = new JTextField(10);
    		label = new JLabel("ISBN");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		topText.add(isbn);
    		bottomText.add(label);
    		
    		rightTextPanel = new JPanel();
    		rightTextPanel.setLayout(new BorderLayout());
    		rightTextPanel.add(topText,BorderLayout.NORTH);
    		rightTextPanel.add(bottomText,BorderLayout.CENTER);
    	}
    	
    	private void addBackButtonListener(JButton butn) {
    		butn.addActionListener(evt -> {
    			LibrarySystem.hideAllWindows();
    			LibrarySystem.INSTANCE.setVisible(true);
    		});
    	}
    	
    	private void validaCheckoutBookButtonListener(JButton butn) {
    		
    		butn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if(!validateCheckoutBook(getMemberId(), getISBN())){
							displayMessage("Book ID has been carried with the Member ID before.");
						}else {
							addDataTable(ci.checkoutBook(getMemberId(), getISBN()));
							displayMessage("Checkout Record Saved!");
						}
					} catch (LibrarySystemException e1) {
						displayMessage(e1.getMessage());
					}
				}

			});
    	}
    	
    	private boolean validateCheckoutBook(String memberId, String isbn) {
    		System.out.println("total dato: "+tableModel.getRowCount());
    		System.out.println("memberId: "+memberId+"-"+isbn);
    		for (int count = 0; count < tableModel.getRowCount(); count++){
    			System.out.println("value: "+tableModel.getValueAt(count, 0) + "."+tableModel.getValueAt(count, 1));
    			  if(memberId.equals(tableModel.getValueAt(count, 0).toString()) && 
    					  isbn.equals(tableModel.getValueAt(count, 1).toString())){
    				  return false;
    			  }
    			}
			return true;
		}
    	
    	private void addDataTable(CheckoutRecord check) {
    		listCheck.clear();
    		
    		String[] checkItem = {
        			check.getMemberId(), 
        			check.getCheckoutEntry().getBookCopy().getBook().getIsbn(),
        			check.getCheckoutEntry().getCheckoutDate().toString(),
        			check.getCheckoutEntry().getDueDate().toString()};
        	
        	listCheck.add(checkItem);
        	setValuesTableCheckoutBook(tableModel, listCheck);
			table.updateUI();
    	}
    	
    	private void tableCheckoutBook() {
    		JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Checkout book records", TitledBorder.CENTER, TitledBorder.TOP));
            
            for(CheckoutRecord check: listCheckout) {
            	String[] checkItem = {
            			check.getMemberId(), 
            			check.getCheckoutEntry().getBookCopy().getBook().getIsbn(),
            			check.getCheckoutEntry().getCheckoutDate().format(DateTimeFormatter.ofPattern("dd-MMM-yy")).toString(),
            			check.getCheckoutEntry().getDueDate().format(DateTimeFormatter.ofPattern("dd-MMM-yy")).toString()};
            	
            	listCheck.add(checkItem);
            }	
            
			table = new JTable(tableModel);
			
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(
					new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
			scrollPane.getViewport().add(table);
			
			createCustomColumns(table, TABLE_WIDTH,
		            COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);
			panel.add(scrollPane);
			lowerPanel.add(panel);
			
			setValuesTableCheckoutBook(tableModel, listCheck);
			table.updateUI();
    	}
    	
    	private void setValuesTableCheckoutBook(CustomTableModel model, List<String[]> data) {
    		model.setTableValues(data);
    	}
    	
    	private void createCustomColumns(JTable table, int width, float[] proportions,
    			  String[] headers) {
    			table.setAutoCreateColumnsFromModel(false);
    	        int num = headers.length;
    	        for(int i = 0; i < num; ++i) {
    	            TableColumn column = new TableColumn(i);
    	            column.setHeaderValue(headers[i]);
    	            column.setMinWidth(Math.round(proportions[i]*width));
    	            table.addColumn(column);
    	        }
    		}
    	
    	private void displayMessage(String message) {
    		JOptionPane.showMessageDialog(INSTANCE,message);
    	}
    	
    	private String getMemberId() {
    		return memberID.getText();
    	}
    	
    	private String getISBN() {
    		return isbn.getText();
    	}
	
    	private static final long serialVersionUID = -4881509539494674087L;
}