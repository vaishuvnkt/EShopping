package model.dao;

import java.util.List;

import model.entity.Address;
import model.entity.Cart;
import model.entity.User;

public interface IUserDAO {

	//to add a user in db
	boolean addUser(User user);
	
	//to find a particular user
	User getByEmail(String email);
	
	//to add address in db
	boolean addAddress(Address address);
	
	Address getBillingAddress(User user);
	List<Address> getShippingAddresses(User user);
	
	//alternative to above 2 methods
	//Address getBillingAddress(int user_id);
	//List<Address> getShippingAddresses(int user_id);
	
}
