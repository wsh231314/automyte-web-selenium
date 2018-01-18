$(document).ready(function() {

	var url = $("#ctx").val() + "/tables";
	$('#example').DataTable( {
//		"processing": true,
//		"serverSide": true,
        "ajax": url,
        "columns": [
            { "data": "product_no" },
            { "data": "product_name" },
            { "data": "shop_name" },
            { "data": "product_price" }
        ]
    } );
    

	var compareUrl = $("#ctx").val() + "/compare?pno=";
    $('#example tbody').on( 'click', 'tr', function () {
    	var table = $('#example').DataTable();
        if (table) {
        	var data = table.row(this._DT_RowIndex).data();
        	if (data) {
                window.location.href = compareUrl+data.product_no;
        	}
        }
        
    } );
    
    $("#imageUpload").click(function(){
    	
    	  
    });
    
    
} );
