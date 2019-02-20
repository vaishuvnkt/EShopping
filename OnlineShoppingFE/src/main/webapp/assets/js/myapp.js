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
	case 'Manage Items':
		$('#manageProducts').addClass('active');//manageProducts - id for manage products page page given in navbar.jsp
		break;
	default:
		if(menu == "Home")
			break;
		$('#listProducts').addClass('active');//home - id for listProducts page given in navbar.jsp; Shows Online Shopping menu as active menu in menu bar 
		$('#a_'+menu).addClass('active');//active menu category tab
		break;
	
	}


	/*var products = [
		
					['1','ABC'],['2','DEF'],['3','GHI'],['4','JKL'],['5','MNO'],['6','PQR'],['7','TUV'],['8','XYZ']
		
					];*/

	var $table = $('#productListTable');
	
	//execute the code only when we are in the page which contains the table
	if($table.length)//length of table != null
		{
			//console.log('Inside the table');
			
			var jsonUrl = '';
			if(window.categoryID == '')
				{
					jsonUrl = window.contextRoot + '/json/data/all/products';
				}
			else
				{
					jsonUrl = window.contextRoot + '/json/data/category/'+window.categoryID+'/products';
				}
		
			$table.DataTable({
				lengthMenu:[[3,5,10,-1],['3 Records','5 Records','10 Records','ALL']],
				pageLength: 5,
				ajax : 
				{
					url : jsonUrl,
					dataSrc : ''
				},
				
				columns:
				[
					{
						data : 'code',
						bSortable : false,
						mRender : function(data, type, row)
						{
							return '<img src = "'+window.contextRoot+'/resources/images/'+data+'.jpg" class = "dataTableImg"/>'
						}
					},
					
					{
						data : 'name' 
					},

					{
						data : 'brand' 
					},

					{
						data : 'unitPrice',
						mRender : function(data, type, row)
									{return '&#8377; '+data;}//to display rupees symbol || html code for rupees symbol is &#8377; 
							
					},

					{
						data : 'quantity',
						mRender : function(data, type, row)
						{
							if(data < 1)
								return '<span style = "color:red"> OUT OF STOCK </span>';
							return data;
						}
					},
					
					{
						data : 'id',
						bSortable : false, //to hide sort option button in column header
						mRender : function(data,type,row)
									{
										
										var str = '';
										str += '<a href = " '+window.contextRoot+'/show/'+data+'/product" class = "btn btn-primary"> <span class = "glyphicon glyphicon-eye-open"></span> </a> &#160; '; //&#160; html code for spacing
										if(row.quantity < 1)
											str += '<a href = "javascript:void(0)" class = "btn btn-success disabled"> <span class = "glyphicon glyphicon-shopping-cart"></span> </a>';
										else
											str += '<a href = " '+window.contextRoot+'/cart/add/'+data+'/product" class = "btn btn-success"> <span class = "glyphicon glyphicon-shopping-cart"></span> </a>';
										return str;
										
									}
					}
				]
				
				});
		}
});