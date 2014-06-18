$(document).ready(function() {
	// variable to hold request
	var request;
	//bind to the submit event of our form
	$("#emailMonitorConfigForm").submit(function(event){
		// abort any pending request
		 if (request) {
		     request.abort();
		 }
		 // setup some local variables
		 var $form = $(this);
		 // let's select and cache all the fields
		 var $inputs = $form.find("input, select, button, textarea");
		 // serialize the data in the form
		 var serializedData = $form.serialize();
		
		 // let's disable the inputs for the duration of the ajax request
		 // Note: we disable elements AFTER the form data has been serialized.
		 // Disabled form elements will not be serialized.
		 $inputs.prop("disabled", true);
		
		 // fire off the request to /api
		 request = $.ajax({
		     url: "config",
		     type: "post",
		     data: serializedData
		 });
		
		 // callback handler that will be called on success
		 request.done(function (response, textStatus, jqXHR){
		     // log a message to the console
			 bootbox.alert(response);
//		     console.log("Hooray, it worked!");
		 });
		
		 // callback handler that will be called on failure
		 request.fail(function (jqXHR, textStatus, errorThrown){
		     // log the error to the console
		     console.error(
		         "The following error occured: "+
		         textStatus, errorThrown
		     );
		     bootbox.alert("Please check values provided for input parameters!");
		 });
		
		 // callback handler that will be called regardless
		 // if the request failed or succeeded
		 request.always(function () {
		     // reenable the inputs
		     $inputs.prop("disabled", false);
		 });
		
		 // prevent default posting of form
		 event.preventDefault();
	});
	
	$("#emailMonitorQueryForm").submit(function(event){
		// abort any pending request
		 if (request) {
		     request.abort();
		 }
		 // setup some local variables
		 var $form = $(this);
		 // let's select and cache all the fields
		 var $inputs = $form.find("input, select, button, textarea");
		 // serialize the data in the form
		 var serializedData = $form.serialize();
		
		 // let's disable the inputs for the duration of the ajax request
		 // Note: we disable elements AFTER the form data has been serialized.
		 // Disabled form elements will not be serialized.
		 $inputs.prop("disabled", true);
		
		 // fire off the request to /api
		 request = $.ajax({
		     url: "query",
		     type: "post",
		     data: serializedData
		 });
		
		 // callback handler that will be called on success
		 request.done(function (response, textStatus, jqXHR){
		     // log a message to the console
			 $('#cepQueries').val('');
			 var jsonresponse = JSON.parse(response);
			 $('#storedQueries tbody').append('<tr><td>'+ jsonresponse.query +'</td><td>'+ jsonresponse.planNames.toString() +'</td></tr>');
			 bootbox.alert(jsonresponse.message);
			 
//		     console.log("Hooray, it worked!");
		 });
		
		 // callback handler that will be called on failure
		 request.fail(function (jqXHR, textStatus, errorThrown){
		     // log the error to the console
		     console.error(
		         "The following error occured: "+
		         textStatus, errorThrown
		     );
		     bootbox.alert("Please check query for correct sysntax and check whether email monitor is configured with correct details!");
		 });
		
		 // callback handler that will be called regardless
		 // if the request failed or succeeded
		 request.always(function () {
		     // reenable the inputs
		     $inputs.prop("disabled", false);
		 });
		
		 // prevent default posting of form
		 event.preventDefault();
	});
	
	$('#storedQueries tbody').on('click', 'tr', function() {
		var row = $(this);
		bootbox.confirm("Are you sure you want to delete execution plans " + row.children(":nth-child(2)").text() + "?", function(result) {
			if (result){			  
				var input = $("<input>").attr("type", "hidden").attr("name", "planNames").val(row.children(":nth-child(2)").text());
			    var form = $("<form>");
			    form.append($(input));
			    var serializedData = form.serialize();
			    
			    request = $.ajax({
				     url: "removeQuery",
				     type: "post",
				     data: serializedData
				 });
				
				 // callback handler that will be called on success
				 request.done(function (response, textStatus, jqXHR){
				     // log a message to the console
					 row.remove();
					 bootbox.alert(response);
		//		     console.log("Hooray, it worked!");
				 });
				
				 // callback handler that will be called on failure
				 request.fail(function (jqXHR, textStatus, errorThrown){
				     // log the error to the console
				     console.error(
				         "The following error occured: "+
				         textStatus, errorThrown
				     );
				     bootbox.alert("Please check values provided for input parameters!");
				 });
			 }
		});
	});
});