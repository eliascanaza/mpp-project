package business;

import java.io.Serializable;
import java.time.LocalDate;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord record;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord checkout(BookCopy copy, LocalDate today, LocalDate dueDate) {
		copy.changeAvailability();
		
		CheckoutEntry entry = new CheckoutEntry(today, dueDate, copy);
		record = new CheckoutRecord(memberId, entry);
		record.addEntry(entry);
		
		return record;
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
