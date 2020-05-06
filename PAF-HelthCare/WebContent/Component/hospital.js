$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
});

// Form validation-------------------
var status = validateHospitalForm();
if (status != true) {
	$("#alertError").text(status);
	$("#alertError").show();
	return;
}

// If valid------------------------
var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";

$.ajax({
	url : "HospitalAPI",
	type : type,
	data : $("#formHospital").serialize(),
	dataType : "text",
	complete : function(response, status) {
		onHospitalSaveComplete(response.responseText, status);
	}
});

function onHospitalSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divHospitalGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} 
		else if (status == "error"){
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} 
		else{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
		}
	
		$("#hidIDSave").val("");
		$("#formHospital")[0].reset(); 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidIDSave").val($(this).closest("tr").find('#hidIDUpdate').val());
	$("#hospitalname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#address").val($(this).closest("tr").find('td:eq(1)').text());
	$("#hotline").val($(this).closest("tr").find('td:eq(2)').text());
	$("#contact").val($(this).closest("tr").find('td:eq(3)').text());
	$("#dis").val($(this).closest("tr").find('td:eq(3)').text());
});

//REMOVE=============================================
$(document).on("click", "_btnRemove",function(event){
	$.ajax({
		url : "HospitalAPI",
		type : "DELETE",
		data : "hos_Id="+ $(this).data("hos_Id"),
		dataType : "text",
		complete : function(response, status) {
			onHospitalSaveComplete(response.responseText, status);
		}
	});
});

function  onHospitalDeleteComplelete(){
	if(status == "success"){
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully deleted.");
				$("#alertSuccess").show();
				$("#divItemsGrid").html(resultSet.data);
			} 
			else if (resultSet.status.trim() == "error"){
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
			
			else if (status == "error"){
				$("#alertError").text("Error while deleting.");
				$("#alertError").show();
			} 
			else{
				$("#alertError").text("Unknown error while deleting..");
				$("#alertError").show();
			}
		
		$("#hidIDSave").val("");
			$("#formHospital")[0].reset(); 
	}
}
}

// CLIENT-MODEL================================================================
function validateHospitalForm() {
	// HOSPITAL NAME
	if ($("#hospitalname").val().trim() == "") {
		return "Insert Hospital name.";
	}
	// ADDRESS
	if ($("#address").val().trim() == "") {
		return "Insert Hospital Address.";
	}
	// HOT-LINE-------------------------------
	if ($("#hotline").val().trim() == "") {
		return "Insert Emergency contact.";
	}
	 

	// CONTACT NUMBER
	if ($("#contact").val().trim() == "") {
		return "Insert Regular contact number.";
	}

	// DESCRIPTION------------------------
	if ($("#dis").val().trim() == "") {
		return "Insert Description.";
	}
	return true;
}

