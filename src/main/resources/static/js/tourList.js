$(document).ready( function() {
    $(".tourListSearch").focus();
    $(".tourListSearch").on("keyup",function(key){
        if(key.keyCode==13) {
            tourListSearch();
        }
    });

    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    $('.tourListSearch').val(urlParam.get("keyword"));
});
function tourListSearch() {
    let keyword = $('.tourListSearch').val();
    const country = $('.selectSearchOption option:selected').val();
    location.href = "/tour/tourList?keyword="+keyword+"&country="+country;
}