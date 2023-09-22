$(document).ready( function() {
    $('#headers').css("position", "fixed");
    $('#headers').css("width", "100%");
    $('#headers').css("z-index", "5");
    $('#menu').css("margin-top", "-16px");
    $('.font12 button').css("font-size", '12px');
    $("#headerSearchBox").on("keyup",function(key){
        if(key.keyCode==13) {
            alert($("#headerSearchBox").val());
        }
    });
});