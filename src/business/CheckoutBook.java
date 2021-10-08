package business;

import java.io.Serializable;

public class CheckoutBook implements Serializable{
	private CheckoutRecordEntry checkoutEntry;
	private String memberId;
	
	public CheckoutBook(String memberId, CheckoutRecordEntry checkoutEntry) {
		super();
		this.memberId = memberId;
		this.checkoutEntry = checkoutEntry;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public CheckoutRecordEntry getCheckoutEntry() {
		return checkoutEntry;
	}

	public void setCheckoutEntry(CheckoutRecordEntry checkoutEntry) {
		this.checkoutEntry = checkoutEntry;
	}
	
	@Override
	public String toString() {
		return "[CheckoutBook=memberId: " + memberId + ", checkoutEntry: " + checkoutEntry+"]";
	}

	private static final long serialVersionUID = 8097235941071965244L;
}
