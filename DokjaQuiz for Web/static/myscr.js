 $(document).ready(function () {
    $('#mlist').click(function () {
        $("#headOfList").html('문화/유람 <span class="caret"></span>');
    });
});

$(document).ready(function () {
    $('#hlist').click(function () {
        $("#headOfList").html('학술/연구 <span class="caret"></span>');
    });
});


$(document).ready(function() {
    $("#inputButton").on('click', function() {
		var part = $('#headOfList').text().replace('/', '')
		var inp = "/home/"+ part +"/" + $('#inputField').val();
        window.location.href = inp;
    });
});

$(document).ready(function() {
    $("#inputField").keypress(function(e) {
		if(e.which == 13){
			var part = $('#headOfList').text().replace('/', '')
			var url = "/home/"+ part +"/" + $('#inputField').val();
			window.location.href = url;
		}	
    });
});

$(document).ready(function() {
    $("a.del").click(function() {
		var value = $(this).text();
		var part = $(this).closest('tbody').attr("class");
		var url = "/home/"+ part +"/" + value;
        window.location.href = url;
    });
});

$(document).ready(function() {
    $("#makeButton").on('click', function() {
        window.location.href = "/make";
    });
});

$(document).ready(function() {
    $("#clearButton").on('click', function() {
        window.location.href = "/clear";
    });
});
