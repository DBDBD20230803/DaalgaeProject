$(document).ready( function() {
    $("#allSearchBox").on("keyup",function(key){
        if(key.keyCode==13) {
            alert("엔터키 이벤트");
        }
    });
});