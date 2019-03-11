package model.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.dao.ICartLineDAO;
import model.entity.Cart;
import model.entity.CartLine;
import model.entity.OrderDetail;
import model.entity.User;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements ICartLineDAO {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public CartLine get(int id) {
		return sf.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean add(CartLine cartLine) {
		try
		{
			sf.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try
		{
			sf.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartLine) {
		try
		{
			sf.getCurrentSession().delete(cartLine);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> list(int cartId) {
		String selectQuery = "FROM CartLine WHERE cartId = :cartId";
		return sf.getCurrentSession().createQuery(selectQuery, CartLine.class).setParameter("cartId", cartId)
					.getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String selectQuery = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		return sf.getCurrentSession().createQuery(selectQuery, CartLine.class).setParameter("cartId", cartId).setParameter("available",true)
					.getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String selectQuery = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		try {
			return sf.getCurrentSession().createQuery(selectQuery, CartLine.class).setParameter("cartId", cartId).setParameter("productId", productId)
					.getSingleResult();
			}
		catch(Exception e)
		{
			return null;
		}
		
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sf.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		try {			
			sf.getCurrentSession().persist(orderDetail);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}


}
