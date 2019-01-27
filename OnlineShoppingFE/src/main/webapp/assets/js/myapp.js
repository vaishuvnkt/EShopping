$(function(){
	//solving active menu problem
	switch(menu){ //menu -> window.menu in page.jsp
	
	case 'About us':
		$('#about').addClass('active');//about - id for about us page given in navbar.jsp
		break;
	case 'Contact us':
		$('#contact').addClass('active');//contact - id for contact us page given in navbar.jsp
		break;
	case 'All Products':
		$('#listProducts').addClass('active');//listProducts - id for view products page page given in navbar.jsp
		break;
	default:
		if(menu == "Home")
			break;
		$('#listProducts').addClass('active');//home - id for listProducts page given in navbar.jsp; Shows Online Shopping menu as active menu in menu bar 
		$('#a_'+menu).addClass('active');//active menu category tab
		break;
	
	}



});