package model.daoimpl;

//import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.dao.ICategoryDAO;
import model.entity.Category;


@Repository("catDAO")//to avoid unsatisfied dependency
@Transactional
public class CategoryDAOImpl implements ICategoryDAO {

	@Autowired
	private SessionFactory sf;
		public Category get(int id)
	{
		return sf.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	public List<Category> list() 
	{
		String selectActiveCategory = "FROM Category WHERE active = :active";
		
		Query query = sf.getCurrentSession().createQuery(selectActiveCategory);
				
		query.setParameter("active", true);
						
		return query.getResultList();

	}

	
	public boolean add(Category c) {
		
		try 
		{
			sf.getCurrentSession().persist(c);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean update(Category c) {

		try
		{
			// update the category to the database table
			sf.getCurrentSession().update(c);
			return true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(Category c) {
		
		c.setActive(false);
		
		try 
		{
			// setting active status false instead of delete the category to the database table
			sf.getCurrentSession().delete(c);
			return true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

}


