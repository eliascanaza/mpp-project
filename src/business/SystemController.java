package business;

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
	public List<CheckoutBook> allCheckoutBook() {
		DataAccess da = new DataAccessFacade();
		List<CheckoutBook> retval = new ArrayList<>();
		
		for (CheckoutBook check : da.readCheckoutBookMap().values()) {
			System.out.println(check);
			retval.add(check);
		}
		
		return retval;
	}
	
	public boolean existsMemberId(String id) {
		List<String> listMemberID = allMemberIds();
		for(String memberID: listMemberID) 
			if(memberID.equals(id))
				return true;
		return false;
	}
	
	public boolean existsBookId(String id) {
		List<String> listBookID = allBookIds();
		for(String bookID: listBookID) 
			if(bookID.equals(id))
				return true;
		return false;
	}
}
