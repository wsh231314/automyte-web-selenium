$(document).ready(function() {


	var rid = $("#rid").val();
	var url = $("#ctx").val() + "/details?id=" + rid;
	$.ajax({
		type : "GET",
		url : url,
		dataType : "json",
		success : function (data) {
			loadDetails(data);
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});
	

	$("#updateReceipt").on("click",function(){
		var urlU = $("#ctx").val() + "/update";
		initMessageArea();
		$.ajax({
			type : "POST",
			url : urlU,
	        contentType: "application/json; charset=utf-8",
	        data: JSON.stringify(getJsonData()),
			dataType : "json",
			success : function (data) {
				showInfoMessage(data);
			},
			error : function (XMLHttpRequest, textStatus, errorThrown) {
				console.log(errorThrown);
			}
		});
	});
	

	$("#cancelReceipt").on("click",function(){
		var urlList = $("#ctx").val() + "/list";
		window.location.href = urlList;
	});
	
	initMessageArea();
});

function loadDetails(data) {
	if (data && data.id) {
		$("#receiptImage").attr("src", data.image_url); 
		$("#category_id").val(data.category_id); 
		$("#receiptDate").val(data.date); 
		$("#amount").val(data.amount); 
		$("#receiptName").val(data.name); 
	}
}

function getJsonData() {

	var rid = $("#rid").val(); 
	var json = {
        "id": rid,
        "category_id": $("#category_id").val(),
        "date": $("#receiptDate").val(),
        "amount": $("#amount").val(),
        "name": $("#receiptName").val(),
    };
    return json;
}

//========================================================================
// message area setting
//========================================================================
function initMessageArea() {
	$("#messageInfo").text(""); 
	$("#messageError").text(""); 
}

function showInfoMessage(data) {
	if (data && data.status) {
		if (data.status === "0") {
			$("#messageInfo").text(data.message); 
		} else {
			$("#messageError").text(data.message); 
		}
	}
}

