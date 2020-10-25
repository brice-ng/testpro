
$('document').ready(function(){
	
	$('table #editButton').on('click',function(event){
	
		event.preventDefault();
		
		//client.findById/?id=1
		
		var href = $(this).attr('href');
		
		$.get(href, function(client, status){
		
		$('#idEdit').val(client.id);		
		$('#txtNameEdit').val(client.name);
		$('#txtDetailsEdit').val(client.details);
		$('#txtWebsiteEdit').val(client.website);
		$('#txtAddressEdit').val(client.Address);
		$('#ddlStateEdit').val(client.stateid);
		$('#ddlCountryEdit').val(client.countryid);
		$('#txtCityEdit').val(client.city);
		$('#txtPhoneEdit').val(client.phone);
		$('#txtMobileEdit').val(client.mobile);
		$('#txtEmailEdit').val(client.email);
				
		});
		$('#editModal').modal();

	});


	$('table #detailsButton').on('click',function(event) {
	
		event.preventDefault();	
			
		var href= $(this).attr('href');
				
		$.get(href, function(client, status){
			$('#txtIdDetail').val(client.id);	

		$('#txtNameDetail').val(client.name);
		$('#txtDetailsDetail').val(client.details);
		$('#txtWebsiteDetail').val(client.website);
		$('#txtAddressDetail').val(client.Address);
		$('#ddlStateEdit').val(client.stateid);
		$('#ddlCountryEdit').val(client.countryid);
		$('#txtCityDetail').val(client.city);
		$('#txtPhoneDetail').val(client.phone);
		$('#txtMobileDetail').val(client.mobile);
		$('#txtEmailDetail').val(client.email);
			$('#lastModifiedByDetails').val(client.lastModifiedBy);
			
			$('#lastModifiedDateDetails').val(client.lastModifiedDate.substr(0,19).replace("T", " "));
		});		
			
		$('#detailsModal').modal();	
			
	});	



	$('table #deleteButton').on('click',function(event){

		event.preventDefault();
		
		var href = $(this).attr('href');
//affectation de l'url de deleteButton à confirmButton
		$('#delRef').attr('href', href);

		$('#deleteModal').modal();


	});


		
	
	
});
