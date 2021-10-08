package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public boolean searchMember(String id);
	public boolean searchBook(String id);
	public List<CheckoutRecord> allCheckoutBook();
	public List<Book> allBook();
	public CheckoutRecord checkoutBook(String memberID, String bookID) throws LibrarySystemException;
}
