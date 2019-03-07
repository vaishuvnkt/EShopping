package handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import flow.model.RegisterModel;
import model.dao.IUserDAO;
import model.entity.Address;
import model.entity.Cart;
import model.entity.User;

@Component("registerHandler")
public class RegisterHandler {
	
	@Autowired
	private IUserDAO userDAO;

	public RegisterModel init()
	{
		System.out.println(new RegisterModel().hashCode());
		return (new RegisterModel());
	}
	
	public void addUser(RegisterModel registerModel,User user)
	{
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel,Address billing)
	{
		registerModel.setBilling(billing);
	}

	public String saveAll(RegisterModel model)
	{
		String transitionValue = "success";
		
		//fetch user
		User user = model.getUser();
		
		if(user.getRole().equals("ROLE"))
		{
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//save user
		userDAO.addUser(user);
		
		//get address
		Address billing = model.getBilling();	
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
	
}
