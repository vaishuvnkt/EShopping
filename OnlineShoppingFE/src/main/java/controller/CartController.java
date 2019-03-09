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
			
				case "updated" : mv.addObject("message", "Cart updated successfully");break;
				case "error" : mv.addObject("message", "Failed to update cart");break;
				case "deleted" : mv.addObject("message", "Cartline deleted");
				
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
}
