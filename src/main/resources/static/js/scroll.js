$(document).ready(function() {

	var s = $("#start").val();
	var l = $("#length").val();
	var json = {
        "start" : s,
        "length" : l
    };

	$('#content').scrollPagination({
		'contentPage': 'http://localhost:8089/myte/content', // the url you are fetching the results
		'contentData': JSON.stringify({
	        "start" : s,
	        "length" : l
	    }), // these are the variables you can pass to the request, for example: children().size() to know which page you are
		'scrollTarget': $(window), // who gonna scroll? in this example, the full window
		'heightOffset': 10, // it gonna request when scroll is 10 pixels before the page ends
		'beforeLoad': function(){ // before load function, you can display a preloader div
			$('#loading').fadeIn();	
			$('#content').stopScrollPagination();
		},
		'afterLoad': function(elementsLoaded){ // after loading content, you can use this function to animate your new elements
			$("body").mLoading("hide");//隐藏loading组件
			 $('#loading').fadeOut();
			 var i = 0;
			 $(elementsLoaded).fadeInWithDelay();
			 var start = $("#start").val();
			 start = parseInt(start) + 1;
			 $("#start").val(start);
			 $('#content').scrollPagination({
				 'contentData': JSON.stringify({
				        "start" : start,
				        "length" : l
				    })
			 });
			 console.log(start);
			 if ($('#content').children().length > 100){ // if more than 100 results already loaded, then stop pagination (only for testing)
			 	$('#nomoreresults').fadeIn();
				$('#content').stopScrollPagination();
			 }
			 $('#content').startScrollPagination();
		}
	});
	
	// code for fade in element by element
	$.fn.fadeInWithDelay = function(){
		var delay = 0;
		return this.each(function(){
			$(this).delay(delay).animate({opacity:1}, 200);
			delay += 100;
		});
	};
    
    
} );
