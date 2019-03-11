package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import flow.model.UserModel;
import model.dao.IUserDAO;
import model.entity.User;

@ControllerAdvice
public class GlobalController {

	@Autowired 
	private HttpSession session;
	
	@Autowired
	private IUserDAO userDAO;
	
	private UserModel userModel = null ;

	
	@ModelAttribute("userModel")
	public UserModel getUserModel()
	{
		if(session.getAttribute("userModel") == null)
		{
			// add the user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(!authentication.getPrincipal().equals("anonymousUser"))
			{
			User user = userDAO.getByEmail(authentication.getName());
			
			if(user != null)
			{
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setFullName("Hi!! " + user.getFirstname());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
			
			if(user.getRole().equals("USER"))
			{
				userModel.setCart(user.getCart());
			}
			
			session.setAttribute("userModel",userModel);
			return userModel;
			}
		}
	
		}
		
		return (UserModel)session.getAttribute("userModel");
	}
	
}
