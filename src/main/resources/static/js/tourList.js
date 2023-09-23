$(document).ready( function() {
    $(".tourListSearch").on("keyup",function(key){
        if(key.keyCode==13) {
            tourListSearch();
        }
    });
});
function tourListSearch() {
    let keyword = $('.tourListSearch').val();
    const select = $('.selectSearchOption option:selected').val();
    location.href = "/tour/tourList?keyword="+keyword+"&condition="+select;
}