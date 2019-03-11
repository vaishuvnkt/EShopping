package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.CartService;

@Controller
@RequestMapping("/cart")
@ComponentScan(basePackages = {"service"}) //to avoid unsatisfied dependency
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result" , required = false)String result)
	{
		ModelAndView mv = new ModelAndView("page");
		
		if(result != null)
		{
			switch(result)
			{
			
				case "updated" :  mv.addObject("message", "Cart updated successfully");break;
				case "error" : mv.addObject("message", "Failed to update cart");break;
				case "deleted" : mv.addObject("message", "Cartline deleted");break;
				case "added" : mv.addObject("message", "Product added to Cart successfully");break;
				case "maximum" : mv.addObject("message", "Quantity reached its maximum of 3");break;
				case "unavailable" : mv.addObject("message", "Product unavailable");break;
				case "modified" : mv.addObject("message", "One or more items modified in cart");break;
			}
		}
		else {
			String response = cartService.validateCartLine();
			if(response.equals("result=modified")) {
				mv.addObject("message", "One or more items inside cart has been modified!");
			}
		}
		
		mv.addObject("title", "Cart");
		mv.addObject("userClickShowCart",true);
		mv.addObject("cartLines",cartService.getCartLines());
		
		return mv;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId, @RequestParam int count)
	{
		String response = cartService.manageCartLine(cartLineId , count);
		return "redirect:/cart/show?"+response; 
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String updateCart(@PathVariable int cartLineId)
	{
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response; 
	}
	
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId)
	{
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response; 
	}
	
	/* after validating it redirect to checkout
	 * if result received is success proceed to checkout 
	 * else display the message to the user about the changes in cart page
	 * */	
	@RequestMapping("/validate")
	public String validateCart() {	
		String response = cartService.validateCartLine();
		if(!response.equals("result=success")) {
			return "redirect:/cart/show?"+response;
		}
		else {
			return "redirect:/cart/checkout";
		}
	}	
}
