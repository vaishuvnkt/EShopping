package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.dao.ICategoryDAO;
import model.entity.Category;

@Controller
public class PageController {

	 
	@Autowired
	private ICategoryDAO catDAO;
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");
		
		//adding categories
		mv.addObject("categories",catDAO.list());
		mv.addObject("title","Home");
		mv.addObject("userClickHome",true);
		return mv;
	}

	@RequestMapping(value= {"/about"})
	public ModelAndView about()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About us");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	
	@RequestMapping(value= {"/contact"})
	public ModelAndView contact()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact us");
		mv.addObject("userClickContact",true);
		return mv;
	}
	
	//to display all products page
	@RequestMapping(value= {"/show/all/products"})
	public ModelAndView showAllProducts()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","All Products");

		//adding categories
		mv.addObject("categories",catDAO.list());
		
		mv.addObject("userClickAllProducts",true);
		
		return mv;
	}

	//to display products based on category
	@RequestMapping(value= {"/show/{id}/products"} , method=RequestMethod.GET)
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)
	{
		ModelAndView mv = new ModelAndView("page");
		
		//use Category and ICategoryDAO to fetch the name of the category selected to display
		Category cat = null;
		cat = catDAO.get(id);
		
		mv.addObject("title",cat.getName());

		//adding categories
		mv.addObject("categories",catDAO.list());

		//adding single category
		mv.addObject("cat",cat);

		mv.addObject("userClickCategoryProducts",true);

		return mv;
	}

}
