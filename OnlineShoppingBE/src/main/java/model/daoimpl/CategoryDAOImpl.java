package model.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.dao.ICategoryDAO;
import model.entity.Category;


@Repository //to avoid unsatisfied dependency
public class CategoryDAOImpl implements ICategoryDAO {

	private static List<Category> cat = new ArrayList<Category>();
	
	static
	{
		//first category
		
		Category c = new Category();
		c.setId(1);
		c.setName("Television");
		c.setImageurl("CAT_1.png");
		c.setDescription("This is some description abt television");
		c.setActive(true);
		cat.add(c);
		
		//second category
		
		c = new Category();
		c.setId(2);
		c.setName("Laptop");
		c.setImageurl("CAT_2.png");
		c.setDescription("This is some description abt laptop");
		c.setActive(true);
		cat.add(c);
		
		//third category
		
		c = new Category();
		c.setId(3);
		c.setName("Mobile");
		c.setImageurl("CAT_3.png");
		c.setDescription("This is some description abt mobile");
		c.setActive(true);
		cat.add(c);
	}

	public Category get(int id)
	{
		//for each statement
		for(Category categories : cat)
		{
			if(categories.getId() == id)
				return categories;
		}
		return null;
	}

	public List<Category> list() {
		// TODO Auto-generated method stub
		return cat;
	}


}