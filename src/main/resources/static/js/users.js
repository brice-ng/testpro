
$('document').ready(function(){
	



	$('div #deleteButton').on('click',function(event){

		event.preventDefault();
		
		var href = $(this).attr('href');
//affectation de l'url de deleteButton à confirmButton
		$('#confirmDeleteButton').attr('href', href);

		$('#deleteModal').modal();


	});


		
	
	
});
