
$('document').ready(function(){
	
	$('table #editButton').on('click',function(event){
	
		event.preventDefault();
		
		//countries.findById/?id=1
		
		var href = $(this).attr('href');
		
		$.get(href, function(location, status){

			$('#idEdit').val(location.id);		
			$('#nameEdit').val(location.description);
			$('#cityEdit').val(location.city);
			$('#addressEdit').val(location.address);
			$('#addCountryEdit').val(location.countryid);
			$('#addStatesEdit').val(location.stateid);
			$('#detailsEdit').val(location.details);

				
		});

		$('#editModal').modal();

	});



	$('table #detailsButton').on('click',function(event) {
	
		event.preventDefault();	
			
		var href= $(this).attr('href');
				
		$.get(href, function(location, status){
			$('#idDetails').val(location.id);
			$('#descriptionDetails').val(location.description);
			$('#cityDetails').val(location.city);
			$('#addressDetails').val(location.address);
			$('#ddlCountryDetails').val(location.countryid);
			$('#ddlStateDetails').val(location.stateid);
			$('#detailsDetails').val(location.details);


			//$('#lastModifiedByDetails').val(location.lastModifiedByDetails);
			
			//$('#lastModifiedDateDetails').val(location.lastModifiedDateDatails.substr(0,19).replace("T", " "));
		});		
			
		$('#detailsModal').modal();	
			
	});	



	$('table #deleteButton').on('click',function(event){

		event.preventDefault();
		
		var href = $(this).attr('href');
//affectation de l'url de deleteButton à confirmButton
		$('#confirmDeleteButton').attr('href', href);

		$('#deleteModal').modal();


	});


		
	
	
});
