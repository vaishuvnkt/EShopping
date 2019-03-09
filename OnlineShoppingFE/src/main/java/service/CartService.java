package service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flow.model.UserModel;
import model.dao.ICartLineDAO;
import model.entity.Cart;
import model.entity.CartLine;
import model.entity.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private ICartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	//returns the cart of the user who is logged in
	private Cart getCart()
	{
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}

	//returns the entire cartline
	public List<CartLine> getCartLines()
	{
		
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());
		
	}

	public String manageCartLine(int cartLineId, int count) {
	
		//fetching cart line from db
		CartLine cartLine = cartLineDAO.get(cartLineId);		

		double oldTotal = cartLine.getTotal();

		
		Product product = cartLine.getProduct();
		
		if(cartLine == null)
		{
			return "result=error";
		}
		
		else 
		{
		// check if that much quantity is available or not
		if(product.getQuantity() < count) {
			count =  product.getQuantity();		
		}	
		
		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice() * count);
		cartLineDAO.update(cartLine);

	
		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
		cartLineDAO.updateCart(cart);
		
		return "result=updated";

		}
	
	}
	
}
 