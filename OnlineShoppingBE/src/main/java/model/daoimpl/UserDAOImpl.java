package model.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.dao.IUserDAO;
import model.entity.Address;
import model.entity.Cart;
import model.entity.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements IUserDAO {

	@Autowired
	private SessionFactory sf;

	@Override
	public boolean addUser(User user) {
		try {
			sf.getCurrentSession().persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sf.getCurrentSession().persist(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";

		try {
			return sf.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();
		} catch (Exception ex) {
			//ex.printStackTrace(); we commented printStackTrace() to avoid no entity exception when user page is loaded without logging in
			return null;
		}
	}

	/*@Override
	public Address getBillingAddress(User user) {
		String selectQuery = "FROM Address WHERE user  = :user AND billing = :billing";
		try {
			return sf.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("billing", true).getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Address> getShippingAddresses(User user) {
		String selectQuery = "FROM Address WHERE user  = :user AND shipping = :shipping";
		try {
			return sf.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("shipping", true).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
*/
	@Override
	public User get(int id) {
		try {			
			return sf.getCurrentSession().get(User.class, id);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public Address getAddress(int addressId) {

		try {			
			return sf.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	
	}

	@Override
	public boolean updateAddress(Address address) {

		try {			
			sf.getCurrentSession().update(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND billing = :isBilling";
		try {
			return sf.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("userId", userId)
					.setParameter("isBilling", true).getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public List<Address> getShippingAddresses(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :isShipping ORDER BY id DESC";
		return sf.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("userId", userId)
				.setParameter("isShipping", true).getResultList();

	}

}
