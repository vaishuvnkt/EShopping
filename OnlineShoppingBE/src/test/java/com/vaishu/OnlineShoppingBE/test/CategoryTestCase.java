package com.vaishu.OnlineShoppingBE.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import model.dao.ICategoryDAO;
import model.entity.Category;

public class CategoryTestCase {

private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static ICategoryDAO catDAO;
	
	
	private Category c=new Category();
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("model");//BASE PACKAGE OF ICategoryDAO
		context.refresh();
		catDAO = (ICategoryDAO)context.getBean("catDAO");
	}
	
	
	/*@Test
	public void testAddCategory() {
		
		c = new Category();
		
		c.setName("Laptop");
		c.setDescription("This is some description for laptop!");
		c.setImageurl("CAT_2.png");
		
		assertEquals("Successfully added a category inside the table!",true,catDAO.add(c));
		
		
	}*/
	
	
	/*@Test
	public void testGetCategory() {
		
		c = catDAO.get(2);
		
		
		assertEquals("Successfully fetched a single category from the table!","TELEVISION",c.getName());
		
		
	}*/
	
	/*
	@Test
	public void testUpdateCategory() {
		c = catDAO.get(2);
		
		c.setImageurl("CAT_1.PNG");
		
		assertEquals("Successfully updated a single category in the table!",true,catDAO.update(c));
		
	}*/
	

	/*@Test
	public void testDeleteCategory() {
		
		c = catDAO.get(4);		
		assertEquals("Successfully deleted a single category in the table!",true,catDAO.delete(c));
		
		
	}*/
	
	/*@Test
	public void testListCategory() {
					
		assertEquals("Successfully fetched the list of categories from the table!",3,catDAO.list().size());
		
		
	}*/
	
	@Test
	public void testCRUDCategory() {
		
		// add operation
		/*c = new Category();
		
		c.setName("Laptop");
		c.setDescription("This is some description for laptop!");
		c.setImageurl("CAT_1.png");
		
		assertEquals("Successfully added a category inside the table!",true,catDAO.add(c));
		
		
		c = new Category();
		
		c.setName("Television");
		c.setDescription("This is some description for television!");
		c.setImageurl("CAT_2.png");
		
		assertEquals("Successfully added a category inside the table!",true,catDAO.add(c));

		c = new Category();
		
		c.setName("Mobile");
		c.setDescription("This is some description for mobile!");
		c.setImageurl("CAT_3.png");
		
		assertEquals("Successfully added a category inside the table!",true,catDAO.add(c));*/

		
		// fetching and updating the category
		c = catDAO.get(2);
		
		//c.setName("TV");
		
		//assertEquals("Successfully updated a single category in the table!",true,catDAO.update(c));
		
		
		// delete the category
		//assertEquals("Successfully deleted a single category in the table!",true,catDAO.delete(c));
		
		
		//fetching the list
		assertEquals("Successfully fetched the list of categories from the table!",3,catDAO.list().size());		
				
		
	}
	
}

