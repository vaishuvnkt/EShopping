package com.vaishu.OnlineShoppingBE.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import model.dao.ICartLineDAO;
import model.dao.IProductDAO;
import model.dao.IUserDAO;
import model.entity.Cart;
import model.entity.CartLine;
import model.entity.Product;
import model.entity.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ICartLineDAO cartLineDAO = null;
	private static IUserDAO userDAO = null;
	private static IProductDAO proDAO = null;
	
	private User user = null;
	private Cart cart = null;
	private Product pro = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("model");
		context.refresh();
		
		proDAO = (IProductDAO)context.getBean(IProductDAO.class);
		userDAO = (IUserDAO)context.getBean("userDAO");
		cartLineDAO = (ICartLineDAO)context.getBean("cartLineDAO");
	}
	
	@Test
	public void testAddNewCartLine()
	{
		
		//step 1 : get the user
		user = userDAO.getByEmail("ce@gmail.com");
		
		//step 2 : fetch the cart
		cart = user.getCart();
		
		//step 3 : get the product
		pro = proDAO.get(1);
		
		//get cartline
		cartLine = new CartLine();
		cartLine.setBuyingPrice(pro.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * pro.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(pro);
		
		assertEquals("Failed to add product into cart",true,cartLineDAO.add(cartLine));
		
		//update cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("Failed to update cart", true,cartLineDAO.updateCart(cart));
		
	}

}
