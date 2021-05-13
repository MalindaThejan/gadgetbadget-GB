$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateInventorForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidInventorIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "InventorApi",
		type : t,
		data : $("#formInventor").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onInventorSaveComplete(response.responseText, status);
		}
	});
}); 

function onInventorSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidInventorIDSave").val("");
	$("#formCustomer")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidInventorIDSave").val($(this).closest("tr").find('#hidInventorUpdate').val());     
	$("#InventorName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#InventorEmail").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#InventorType").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#InventorContact").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "InventorApi",
		type : "DELETE",
		data : "InventorID=" + $(this).data("inventorid"),
		dataType : "text",
		complete : function(response, status)
		{
			onInventorDeletedComplete(response.responseText, status);
		}
	});
});

function onInventorDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateInventorForm() {  
	// NAME  
	if ($("#InventorName").val().trim() == "")  {   
		return "Insert customerName.";  
		
	} 
	
	 // Email 
	if ($("#InventorEmail").val().trim() == "")  {   
		return "Insert Email.";  
	} 
	
	
	//Type
	if ($("#InventorType").val().trim() == "")  {   
		return "Insert customerType."; 
		 
	}
	 
	 // is numerical value  
	var tmpMobile = $("#InventorContact").val().trim();  
	if (!$.isNumeric(tmpMobile))  {   
		return "Insert a numerical value for Mobile Number.";  
		
	}
	 
	
		

	 
	 return true; 
	 
}
