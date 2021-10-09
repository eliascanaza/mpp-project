package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public List<String> allAuthors();
	public void saveLibraryMember(LibraryMember libraryMember);
	public void saveLibraryMember(List<LibraryMember> libraryMember);
	public List<LibraryMember> getAllLibraryMember();
	public boolean searchMember(String id);
	public boolean searchBook(String id);
	public void addBookCopy(String id) throws LibrarySystemException;
	public List<CheckoutRecord> allCheckoutBook();
	public List<Book> allBook();
	public CheckoutRecord checkoutBook(String memberID, String bookID) throws LibrarySystemException;
	public void logout();
	public void saveBook(String ISBN, String title, String[] Author, int MaxCheckoutLength, int numCopies) throws LibrarySystemException;
}
