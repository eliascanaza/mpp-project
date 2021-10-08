package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	//Added logout
	public void logout() {
		this.currentAuth = null;
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	@Override
	public void saveLibraryMember(LibraryMember libraryMember) {
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(libraryMember);
	}
	
	@Override
	public List<CheckoutRecord> allCheckoutBook() {
		DataAccess da = new DataAccessFacade();
		List<CheckoutRecord> retval = new ArrayList<>();
		
		for (CheckoutRecord check : da.readCheckoutBookMap().values()) {
			retval.add(check);
		}
		
		return retval;
	}
	
		@Override
	public List<LibraryMember> getAllLibraryMember() {
		DataAccessFacade da = new DataAccessFacade();
		HashMap<String, LibraryMember> libmem = da.readMemberMap();
		System.out.println(libmem);
		return null;
	}
	
	@Override
	public List<Book> allBook() {
		DataAccess da = new DataAccessFacade();
		List<Book> retval = new ArrayList<>();
		
		for (Book book : da.readBooksMap().values()) {
			retval.add(book);
		}
		return retval;
	}
	
	public boolean searchMember(String id) {
		List<String> listMemberID = allMemberIds();
		for(String memberID: listMemberID) 
			if(memberID.equals(id))
				return true;
		return false;
	}
	
	public boolean searchBook(String id) {
		List<String> listBookID = allBookIds();
		for(String bookID: listBookID) 
			if(bookID.equals(id))
				return true;
		return false;
	}
	
	private Book getBook(String id) {
		DataAccess da = new DataAccessFacade();
		return da.readBooksMap().get(id);
	}
	
	private LibraryMember getLibraryMember(String id) {
		DataAccess da = new DataAccessFacade();
		return da.readMemberMap().get(id);
	}
	
	@Override
	public void saveLibraryMember(List<LibraryMember> libraryMember) {
		DataAccessFacade.loadMemberMap(libraryMember);
	}
	
	@Override
	public CheckoutRecord checkoutBook(String memberID, String bookID) throws LibrarySystemException{
		DataAccess da = new DataAccessFacade();
		
		if(memberID.isEmpty() || bookID.isEmpty())
			throw new LibrarySystemException("The fields should be notempty.");
		
		if(!searchMember(memberID))
			throw new LibrarySystemException("Member ID is not found.");
		
		if(!searchBook(bookID))
			throw new LibrarySystemException("Book ID is not found.");
		
		Book book = getBook(bookID);
		
		BookCopy copy = book.getNextAvailableCopy();
		LibraryMember lMember = getLibraryMember(memberID);
		
		if(copy == null)
			throw new LibrarySystemException("Book Copy is not Available.");
		
		int maxCheckout = book.getMaxCheckoutLength();
		
		if(!book.isAvailable()) 
			throw new LibrarySystemException("Book is not Available.");
		
		CheckoutRecord record = lMember.checkout(copy, LocalDate.now(), LocalDate.now().plusDays(maxCheckout));
		da.addCheckoutBook(record);
		da.saveBook(book);
		System.out.println("CheckoutBook saved!");
		return record;
	}
	
	@Override
	public void addBookCopy(String id) throws LibrarySystemException{
		DataAccess da = new DataAccessFacade();
		if(!searchBook(id))
			throw new LibrarySystemException("Book ID is not found.");
		
		Book book = getBook(id);
		book.addCopy();
		da.saveBook(book);
		System.out.println("Book Copy saved!");
	}
}
