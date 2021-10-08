package dataaccess;

import java.util.HashMap;

import business.Book;
import business.CheckoutBook;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String, CheckoutBook> readCheckoutBookMap();
	public void saveNewMember(LibraryMember member);
	public CheckoutBook addCheckoutBook(String memberID, String bookID);
}
