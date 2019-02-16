
package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.ProductNotFoundException;
import model.dao.ICategoryDAO;
import model.dao.IProductDAO;
import model.entity.Category;
import model.entity.Product;

@Controller
public class PageController {

	 
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private ICategoryDAO catDAO;

	@Autowired
	private IProductDAO proDAO;
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");
		
		//logger message
		logger.info("Inside page conntroller index method - INFO");
		logger.debug("Inside page conntroller index method - DEBUG");
		
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
	
	@RequestMapping("/show/{id}/product")
	public ModelAndView showSingleCategory(@PathVariable int id) throws ProductNotFoundException
	{
		ModelAndView mv = new ModelAndView("page");
		
		Product pro = proDAO.get(id);
		
		//if user search for unavailable product, in db there will be no id so it will return null and null is stored in pro object 
		if(pro == null) 
			throw new ProductNotFoundException();
		
		//update view count in database
		pro.setViews(pro.getViews() + 1);
		proDAO.update(pro);
		
		mv.addObject("title",pro.getName());
		mv.addObject("product",pro);
		
		mv.addObject("userClickShowProduct",true);
		
		return mv;
		
		
	}

}
