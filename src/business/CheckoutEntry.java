package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable{
	
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;
	
	public CheckoutEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy) {
		super();
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.bookCopy = bookCopy;
	}
	
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public BookCopy getBookCopy() {
		return bookCopy;
	}
	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}
	@Override
	public String toString() {
		
		return "[CheckoutRecordEntry= checkoutDate: "+checkoutDate+", dueDate: "+dueDate + ", book:"+ bookCopy+"]";
	}
	private static final long serialVersionUID = -7930921395464163212L;
}
