package librarysystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;


public class LibrarySystem extends JFrame implements LibWindow {
	
	ControllerInterface ci = new SystemController();
	public final static LibrarySystem INSTANCE =new LibrarySystem();
	JPanel mainPanel;
	JMenuBar menuBar;
    JMenu options, adminOptions, librarianOptions;
    JMenuItem login, logout, allBookIds, allMemberIds, checkoutBook, addBookCopy, addBooks, addMember, checkOutBook; 
    String pathToImage;
    private boolean isInitialized = false;
    
    private static LibWindow[] allWindows = { 
    	LibrarySystem.INSTANCE,
		LoginWindow.INSTANCE,
		AllMemberIdsWindow.INSTANCE,	
		AllBookIdsWindow.INSTANCE,
		AddBookCopyWindow.INSTANCE,
		CheckoutBookWindow.INSTANCE,
		AddLibraryMember.INSTANCE  // added new instance
	};
    	
	public static void hideAllWindows() {
		
		for(LibWindow frame: allWindows) {
			frame.setVisible(false);
			
		}
	}
    
    
    private LibrarySystem() {}
    
    public void init() {
    	formatContentPane();
    	setPathToImage();
    	insertSplashImage();
		
		createMenus();
		//pack();
		setSize(660,500);
		isInitialized = true;
    }
    
    private void formatContentPane() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,1));
		getContentPane().add(mainPanel);	
	}
    
    private void setPathToImage() {
    	String currDirectory = System.getProperty("user.dir");
    	pathToImage = currDirectory+"/src/librarysystem/library.jpg";
    }
    
    private void insertSplashImage() {
        ImageIcon image = new ImageIcon(pathToImage);
		mainPanel.add(new JLabel(image));	
    }
    private void createMenus() {
    	menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createRaisedBevelBorder());
		addMenuItems();
		setJMenuBar(menuBar);		
    }
    
    private void addMenuItems() {
       options = new JMenu("Options");  
 	   menuBar.add(options);
 	   login = new JMenuItem("Login");
 	   login.addActionListener(new LoginListener());
 	   allBookIds = new JMenuItem("All Book Ids");
 	   allBookIds.addActionListener(new AllBookIdsListener());
 	   allMemberIds = new JMenuItem("All Member Ids");
 	   allMemberIds.addActionListener(new AllMemberIdsListener());
 	   
 	   options.add(login);
 	   options.add(allBookIds);
 	   options.add(allMemberIds);
    }
	
	/**
     * This is to create admin menu for user with admin auth
     */
    private void addItemsInAdminMenu() {
       adminOptions = new JMenu("Admin Menu");  
  	   menuBar.add(adminOptions);
  	   
  	   addBooks = new JMenuItem("Add Books");
  	   addBooks.addActionListener(new addBookListener());
  	   addMember = new JMenuItem("Add Member");
  	   addMember.addActionListener(new addNewLibraryMember());
  	   
  	   addBookCopy = new JMenuItem("Add Book Copy");
  	   addBookCopy.addActionListener(new AddBookCopyListener());
  	   
  	   adminOptions.add(addBooks);
  	   adminOptions.add(addMember);
  	   adminOptions.add(addBookCopy);
  	   
     }
    
	
    public void updateMenuBarAfterLogin() {
   
             	menuBar.remove(options);
        	if(SystemController.currentAuth == Auth.ADMIN) {
        		menuBar.remove(adminOptions);	
        	}
        	else if(SystemController.currentAuth == Auth.LIBRARIAN) {
        		menuBar.remove(librarianOptions);
        	}
        	else if(SystemController.currentAuth == Auth.BOTH) {
        		menuBar.remove(adminOptions);
        		menuBar.remove(librarianOptions);
        	}
      	   
      	   options = new JMenu("Options");  
    	   menuBar.add(options);
      	   
    	   logout = new JMenuItem("Logout");
      	   allBookIds = new JMenuItem("All Book Ids");
      	   allMemberIds = new JMenuItem("All Member Ids");
      	   
      	   
      	   logout.addActionListener(new LogoutListner());
      	   allBookIds.addActionListener(new AllBookIdsListener());	
      	   allMemberIds.addActionListener(new AllMemberIdsListener());
      	  
      	   options.add(logout);
      	   options.add(allBookIds);
      	   options.add(allMemberIds);
      	   
      	   if(SystemController.currentAuth == Auth.ADMIN) {
        	   addItemsInAdminMenu();
      	 	}
      	   else if(SystemController.currentAuth == Auth.LIBRARIAN) {
      		   addItemsInLibrarianmenu();
      	   }
      	   else if(SystemController.currentAuth == Auth.BOTH) {
      	  	   addItemsInAdminMenu();
      	  	   addItemsInLibrarianmenu();
      	   }
        }
    
    /**
     * This is to create Librarian menu for user with librarian auth
     */
    
    // 
    private void addItemsInLibrarianmenu() {
    	librarianOptions = new JMenu("Librarian Menu");
    	menuBar.add(librarianOptions);
    	
    	checkOutBook = new JMenuItem("Check Out Books");
    	checkOutBook.addActionListener(new CheckoutBookListener());
    	
    	librarianOptions.add(checkOutBook);
    	
    }
    
    /**
     * This is to update the UI of the main LibrarySystem
     */
    
    public void updateUiAccordingToAuth(Auth auth) {
    	if(auth == (Auth.ADMIN)) {
    		addItemsInAdminMenu();
    	}
    	else if(auth == Auth.LIBRARIAN) {
    		addItemsInLibrarianmenu();
    	}
    	else if(auth == Auth.BOTH) {
    		addItemsInAdminMenu();
    		addItemsInLibrarianmenu();
    	}
    }

	
    class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			
			if(!LoginWindow.INSTANCE.isInitialized()) {
				LoginWindow.INSTANCE.init();
				LoginWindow.INSTANCE.pack();
				LoginWindow.INSTANCE.isInitialized(true);
			}
			LoginWindow.INSTANCE.clear();
			LoginWindow.INSTANCE.setVisible(true);
			
			Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
		}
    }
	class LogoutListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			SystemController.currentAuth = null;
			Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
			init();
			LibrarySystem.INSTANCE.setVisible(true);
			
		}
    	
    }

    class AllBookIdsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			AllBookIdsWindow.INSTANCE.init();
			
			List<String> ids = ci.allBookIds();
			Collections.sort(ids);
			StringBuilder sb = new StringBuilder();
			for(String s: ids) {
				sb.append(s + "\n");
			}
			System.out.println(sb.toString());
			AllBookIdsWindow.INSTANCE.setData(sb.toString());
			AllBookIdsWindow.INSTANCE.pack();
			//AllBookIdsWindow.INSTANCE.setSize(660,500);
			Util.centerFrameOnDesktop(AllBookIdsWindow.INSTANCE);
			AllBookIdsWindow.INSTANCE.setVisible(true);
			
		}
    	
    }
    
    class AllMemberIdsListener implements ActionListener {

    	@Override
		public void actionPerformed(ActionEvent e) {
    		
    		
    		LibrarySystem.hideAllWindows();
			
			if(!AllMemberIdsWindow.INSTANCE.isInitialized()) {
				AllMemberIdsWindow.INSTANCE.init();
				AllMemberIdsWindow.INSTANCE.pack();
				AllMemberIdsWindow.INSTANCE.isInitialized(true);
			}
			
			List<String> ids = ci.allMemberIds();
			
			Collections.sort(ids);
			StringBuilder sb = new StringBuilder();
			for(String s: ids) {
				sb.append(s + "\n");
			}
			System.out.println(sb.toString());
			AllMemberIdsWindow.INSTANCE.setData(sb.toString());
			AllMemberIdsWindow.INSTANCE.setVisible(true);
			
			Util.centerFrameOnDesktop(AllMemberIdsWindow.INSTANCE);
		}
    	
    }
	
	class addNewLibraryMember implements ActionListener {
    	@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			
			if(!AddLibraryMember.INSTANCE.isInitialized()) {
				AddLibraryMember.INSTANCE.init();
				AddLibraryMember.INSTANCE.pack();
				AddLibraryMember.INSTANCE.isInitialized(true);
			}
			AddLibraryMember.INSTANCE.clear();
			AddLibraryMember.INSTANCE.setVisible(true);
			
			Util.centerFrameOnDesktop(AddLibraryMember.INSTANCE);
		}
    	
    }
	
	class addBookListener implements ActionListener {
    	@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			
			if(!AddBooksWindow.INSTANCE.isInitialized()) {
				AddBooksWindow.INSTANCE.init();
				AddBooksWindow.INSTANCE.pack();
				AddBooksWindow.INSTANCE.isInitialized(true);
			}
			AddBooksWindow.INSTANCE.clear();
			AddBooksWindow.INSTANCE.setVisible(true);
			
			Util.centerFrameOnDesktop(AddBooksWindow.INSTANCE);
		}
    	
    }
    
    class CheckoutBookListener implements ActionListener {

    	@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			
			if(!CheckoutBookWindow.INSTANCE.isInitialized()) {
				CheckoutBookWindow.INSTANCE.init();
				CheckoutBookWindow.INSTANCE.pack();
				CheckoutBookWindow.INSTANCE.isInitialized(true);
			}
			CheckoutBookWindow.INSTANCE.clear();
			CheckoutBookWindow.INSTANCE.setVisible(true);
			
			Util.centerFrameOnDesktop(CheckoutBookWindow.INSTANCE);
		}
    	
    }
    
    class AddBookCopyListener implements ActionListener {

    	@Override
		public void actionPerformed(ActionEvent e) {
    		LibrarySystem.hideAllWindows();
    		
    		if(!AddBookCopyWindow.INSTANCE.isInitialized()) {
    			AddBookCopyWindow.INSTANCE.init();
    			AddBookCopyWindow.INSTANCE.pack();
    			AddBookCopyWindow.INSTANCE.isInitialized(true);
			}
    		AddBookCopyWindow.INSTANCE.clear();
    		AddBookCopyWindow.INSTANCE.setVisible(true);
    		Util.centerFrameOnDesktop(AddBookCopyWindow.INSTANCE);
		}
    	
    }

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}


	@Override
	public void isInitialized(boolean val) {
		isInitialized =val;
		
	}
	
	private static final long serialVersionUID = 7507031264177216075L;
}
