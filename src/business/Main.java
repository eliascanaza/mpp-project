package business;

import java.util.*;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class Main {

	public static void main(String[] args) {
//		System.out.println(allWhoseZipContains3());
//		System.out.println(allHavingOverdueBook());
//		System.out.println(allHavingMultipleAuthors());
		System.out.println(allBook());
	}
	//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		List<String> listZip = new ArrayList<>();
		
		for(LibraryMember member: mems) {
			if(member.getAddress().getZip().contains("3")) {
				listZip.add(member.getAddress().getZip());
			}
		}
		
		return listZip;
	}
	//Returns a list of all book copies
	public static List<Book> allBook() {
		DataAccess da = new DataAccessFacade();
		Collection<Book> copies = da.readBooksMap().values();
		List<Book> copy = new ArrayList<>();
		copy.addAll(copies);
		return copy;
	}
	
	//Returns a list of all ids of  LibraryMembers that have an overdue book
	public static List<String> allHavingOverdueBook() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		//implement
		return null;
		
	}
	
	//Returns a list of all isbns of  Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() {
		DataAccess da = new DataAccessFacade();
		Collection<Book> books = da.readBooksMap().values();
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		List<String> listISBN = new ArrayList<>();
		
		for(Book book: bs) {
			if(book.getAuthors().size() > 1) {
				listISBN.add(book.getIsbn());
			}
		}
		
		return listISBN;	
	}

}
