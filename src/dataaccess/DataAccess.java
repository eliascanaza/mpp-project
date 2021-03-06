package dataaccess;

import java.util.HashMap;

import business.Author;
import business.Book;
import business.CheckoutRecord;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String,Author> readAuthorMap();
	public HashMap<String,LibraryMember> readMemberMap();
	public HashMap<String,CheckoutRecord> readCheckoutBookMap();
	public void saveNewMember(LibraryMember member);
	public CheckoutRecord addCheckoutBook(CheckoutRecord record);
	public void saveBook(Book book);
}
