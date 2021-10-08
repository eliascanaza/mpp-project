package business;

import java.io.Serializable;

public class CheckoutRecord implements Serializable{
	private CheckoutEntry checkoutEntry;
	private String memberId;
	
	public CheckoutRecord(String memberId, CheckoutEntry checkoutEntry) {
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

	public CheckoutEntry getCheckoutEntry() {
		return checkoutEntry;
	}

	public void addEntry(CheckoutEntry checkoutEntry) {
		this.checkoutEntry = checkoutEntry;
	}
	
	@Override
	public String toString() {
		return "[CheckoutBook=memberId: " + memberId + ", checkoutEntry: " + checkoutEntry+"]";
	}

	private static final long serialVersionUID = 8097235941071965244L;
}
