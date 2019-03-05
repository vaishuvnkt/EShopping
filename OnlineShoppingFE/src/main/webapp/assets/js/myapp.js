$(function() {
	// solving active menu problem
	switch (menu) { // menu -> window.menu in page.jsp

	case 'About us':
		$('#about').addClass('active');// about - id for about us page given in
										// navbar.jsp
		break;
	case 'Contact us':
		$('#contact').addClass('active');// contact - id for contact us page
											// given in navbar.jsp
		break;
	case 'All Products':
		$('#listProducts').addClass('active');// listProducts - id for view
												// products page page given in
												// navbar.jsp
		break;
	case 'Manage Items':
		$('#manageProducts').addClass('active');// manageProducts - id for
												// manage products page page
												// given in navbar.jsp
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');// home - id for listProducts
												// page given in navbar.jsp;
												// Shows Online Shopping menu as
												// active menu in menu bar
		$('#a_' + menu).addClass('active');// active menu category tab
		break;

	}

	/*
	 * var products = [
	 * 
	 * ['1','ABC'],['2','DEF'],['3','GHI'],['4','JKL'],['5','MNO'],['6','PQR'],['7','TUV'],['8','XYZ']
	 *  ];
	 */

	var $table = $('#productListTable');

	// execute the code only when we are in the page which contains the table
	if ($table.length)// length of table != null
	{
		// console.log('Inside the table');

		var jsonUrl = '';
		if (window.categoryID == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryID + '/products';
		}

		$table
				.DataTable({
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},

					columns : [
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src = "' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class = "dataTableImg"/>'
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
								mRender : function(data, type, row) {
									return '&#8377; ' + data;
								}// to display rupees symbol || html code for
									// rupees symbol is &#8377;

							},

							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1)
										return '<span style = "color:red"> OUT OF STOCK </span>';
									return data;
								}
							},

							{
								data : 'id',
								bSortable : false, // to hide sort option
													// button in column header
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href = " '
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class = "btn btn-primary"> <span class = "glyphicon glyphicon-eye-open"></span> </a> &#160; '; // &#160;
																																						// html
																																						// code
																																						// for
																																						// spacing
									if (row.quantity < 1)
										str += '<a href = "javascript:void(0)" class = "btn btn-success disabled"> <span class = "glyphicon glyphicon-shopping-cart"></span> </a>';
									else
										str += '<a href = " '
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class = "btn btn-success"> <span class = "glyphicon glyphicon-shopping-cart"></span> </a>';
									return str;

								}
							} ]

				});
	}

	// timeout dismissal for product submitted notification

	var $alert = $('.alert');

	if ($alert.length)

	{

		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)

	}
	// ---------------------------------------


// datatable for admin

var $adminProTable = $('#adminProductsTable');

// execute the code only when we are in the page which contains the table
if ($adminProTable.length)// length of table != null
{
	// console.log('Inside the table');

	var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
	$adminProTable
			.DataTable({
				lengthMenu : [ [ 30, 50, 10, -1 ],
						[ '30 Records', '50 Records', '10 Records', 'ALL' ] ],
				pageLength : 30,
				ajax : {
					url : jsonUrl,
					dataSrc : ''
				},

				columns : [
					
						{
							data : 'id'
						},
					
						{
							data : 'code',
							bSortable : false,
							mRender : function(data, type, row) {
								return '<img src = "' + window.contextRoot
										+ '/resources/images/' + data
										+ '.jpg" class = "adminDataTableImg"/>'
							}
						},

						{
							data : 'name'
						},

						{
							data : 'brand'
						},

						{
							data : 'quantity',
							mRender : function(data, type, row) {
								if (data < 1)
									return '<span style = "color:red"> OUT OF STOCK </span>';
								return data;
							}
						},

						{
							data : 'unitPrice',
							mRender : function(data, type, row) {
								return '&#8377; ' + data;
							}// to display rupees symbol || html code for
								// rupees symbol is &#8377;

						},
						
						{
							data : 'active',
							bSortable : false,
							mRender : function(data, type, row)
							{
								var str = '';
								
								str += '<label class="switch">';
								
								if(data)
								{
									str += '<input type="checkbox" checked="checked" value="'+row.id+'" />';
								}
								else
								{
									str += '<input type="checkbox" value="'+row.id+'" />';		
								}
								
								str += '<div class="slider"></div>';

								str += '</label>';
								
								return str;

							}
						},
						
						{
							
							data : 'id',
							bSortable : false,
							mRender : function(data, type, row)
							{
								
								var str = '';

								str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
								
								str += '<span class="glyphicon glyphicon-pencil"></span>';
							
								str += '</a>';
							
								return str;
								
							}
							
						}
						
						],
						//product activation
						initComplete : function()
						{
							var api = this.api();
							api.	$('.switch input[type = "checkbox"]')
							.on(
									'change',
									function() {

										var checkbox = $(this);
										var checked = checkbox.prop('checked');
										var dMsg = (checked) ? 'Do you want to activate the item?'
												: 'Do you want to deactivate the item';
										var value = checkbox.prop('value');

										bootbox
												.confirm({

													size : 'medium',
													title : 'Product Status',
													message : dMsg,
													callback : function(confirmed) {

														if (confirmed) {
																	console.log(value);
																	var activationUrl = window.contextRoot + '/manage/product/'+value+'/activation';
																	
																	$.post(activationUrl , function(data){
																		
																		bootbox
																		.alert({

																			size : 'medium',
																			title : '',
																			message : data

																		});																		
																	});
																	
															}
														else {
															checkbox.prop('checked', !checked);
														}

													}

												});
									});

						}
						
			});
}


// --------------------

//validating the product form element	
// fetch the form element
$categoryForm = $('#categoryForm');

if($categoryForm.length) {
	
	($categoryForm).validate({			
			rules: {
				name: {
					required: true,
					minlength: 5
				},
				Description: {
					required: true,
					minlength: 5					
				}				
			},
			messages: {					
				name: {
					required: 'Category name required',
					minlength: 'Enter atleast five characters'
				},
				Description: {
					required: 'Category description',
					minlength: 'Enter atleast five characters'
				}					
			},
			errorElement : "em",
			errorPlacement : function(error, element) {
				//errorPlacement(error, element);
					//add the css class of help-block
					error.addClass('help-block');
					//add the error element after input block
					error.insertAfter(element);

			}				
		});
}
});