package model.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.dao.IProductDAO;
import model.entity.Product;

@Repository("ProductDAO")
@Transactional
public class ProductDAOImpl implements IProductDAO{

	@Autowired
	private SessionFactory sf;
	
	public Product get(int pid)
	{
		try
		{			
			return sf.getCurrentSession().get(Product.class,Integer.valueOf(pid));			
		}
		catch(Exception ex)
		{		
			ex.printStackTrace();			
		}
		return null;
	}

	public List<Product> list() 
	{
		return sf.getCurrentSession().createQuery("FROM Product" , Product.class).getResultList();
	}

	public boolean add(Product p)
	{
		try 
		{			
			sf.getCurrentSession().persist(p);
			return true;
		}
		catch(Exception ex)
		{		
			ex.printStackTrace();			
		}		
		return false;
	}

	public boolean update(Product p) 
	{
		try
		{			
			sf.getCurrentSession().update(p);
			return true;
		}
		catch(Exception ex)
		{		
			ex.printStackTrace();			
		}		
		return false;		
	}

	public boolean delete(Product p)
	{
		try
		{
			
			p.setActive(false);
			// call the update method
			return this.update(p);
		}
		catch(Exception ex)
		{		
			ex.printStackTrace();			
		}		
		return false;			
	}
	
	//business methods
	public List<Product> listActiveProducts()
	{
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sf.getCurrentSession().createQuery(selectActiveProducts, Product.class).setParameter("active", true).getResultList();

	}

	public List<Product> listActiveProductsByCategory(int cid) 
	{
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryID = :categoryID";
		return sf.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class).setParameter("active", true).setParameter("categoryID",cid).getResultList();

	}

	public List<Product> getLatestActiveProducts(int count) 
	{
		return sf.getCurrentSession().createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class).setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();					

	}


}
