package model.dao;

import java.util.List;

import model.entity.Cart;
import model.entity.CartLine;

public interface ICartLineDAO {

	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	
	public List<CartLine> list(int cartId);
	
	//business methods
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId, int productId);
	
	//to update cart details to db when user adds item to cartS 
	boolean updateCart(Cart cart);

	
}
