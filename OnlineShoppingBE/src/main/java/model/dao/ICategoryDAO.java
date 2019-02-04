package model.dao;

import java.util.List;

import model.entity.Category;

public interface ICategoryDAO {


	boolean add(Category c);
	boolean update(Category c);
	boolean delete(Category c);
	List<Category> list();

    Category get(int id);
	
}
