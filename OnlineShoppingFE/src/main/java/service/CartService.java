package service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flow.model.UserModel;
import model.dao.ICartLineDAO;
import model.dao.IProductDAO;
import model.entity.Cart;
import model.entity.CartLine;
import model.entity.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private ICartLineDAO cartLineDAO;

	@Autowired
	private HttpSession session;

	@Autowired
	private IProductDAO proDAO;
	
	// returns the cart of the user who is logged in
	private Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	// returns the entire cartline
	public List<CartLine> getCartLines() {

		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());

	}

	public String manageCartLine(int cartLineId, int count) {

		// fetching cart line from db
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null) {
			return "result=error";
		}

		else {
			double oldTotal = cartLine.getTotal();

			Product product = cartLine.getProduct();

			// check if that much quantity is available or not
			if (product.getQuantity() < count) {
				return "result=unavailable";

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

	public String deleteCartLine(int cartLineId) {

		// fetch cartline
		CartLine cartLine = cartLineDAO.get(cartLineId);

		// update the cart first before removing cartLine bcoz the info will not be
		// available about the cartline after getting deleted so we cannot perform
		// modifications
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);
		cartLineDAO.updateCart(cart);

		// remove cartline
		cartLineDAO.delete(cartLine);

		return "result=deleted";
	}

	public String addCartLine(int productId) {
		
		String response = null ;

		// gets the cart details of current user
		Cart cart = this.getCart();

		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);

		if (cartLine == null) {
			
			// add new cartline to the particular user
			cartLine = new CartLine();
			//fetch product details
			Product pro = proDAO.get(productId);
			//set cart_id
			cartLine.setCartId(cart.getId());
			//set product_id
			//since we have a one to one relation between cart and product we are adding product instead of product_id
			cartLine.setProduct(pro);
			//set buying price
			cartLine.setBuyingPrice(pro.getUnitPrice());
			//set product count
			//when user adds the product for first time in cart by default the value of product count is 1
			cartLine.setProductCount(1);
			//set total
			//since it is the first time we are adding product , total cost of the particular product(not grandTotal) is equal to the unit price
			cartLine.setTotal(pro.getUnitPrice());
			
			cartLine.setAvailable(true);
			
			//adding new cartline to the cart_line table
			cartLineDAO.add(cartLine);
			
			//updating cart
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
			
		}
		
		else
		{
			//check whether the quantity reached its maximum
			if(cartLine.getProductCount() < 3)
			{
				//update productCount for that product in the cart line
				response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount()+1);
			}
			else
			{
				response = "result=maximum";
			}
		}

		return response;
	}

}
