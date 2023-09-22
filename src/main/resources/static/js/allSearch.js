$(document).ready( function() {
    $("#allSearchBox").on("keyup",function(key){
        if(key.keyCode==13) {
            alert($("#allSearchBox").val());
        }
    });
});