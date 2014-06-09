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
		     url: "api",
		     type: "post",
		     data: serializedData
		 });
		
		 // callback handler that will be called on success
		 request.done(function (response, textStatus, jqXHR){
		     // log a message to the console
		     alert("ok");
		     console.log("Hooray, it worked!");
		 });
		
		 // callback handler that will be called on failure
		 request.fail(function (jqXHR, textStatus, errorThrown){
		     // log the error to the console
		     console.error(
		         "The following error occured: "+
		         textStatus, errorThrown
		     );
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
});