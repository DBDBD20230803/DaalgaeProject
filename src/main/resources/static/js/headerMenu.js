function allHeaderSearch() {
    let keyword = $('#headerSearchBox').val();
    location.href = "/allSearch?keyword="+keyword;
}
$(document).ready(function (){
    $('input[class="burger-check"]').change(function() {
        if($('#burger-check').prop('checked') == true) {
            $('#burger-check2').prop('checked', false);
        }
    });
    $('input[class="burger-check2"]').change(function() {
        if($('#burger-check2').prop('checked') == true) {
            $('#headerSearchBox').focus();
            $('#burger-check').prop('checked', false);
        }
    });
    // 검색 파라미터 전달
    $("#headerSearchBox").on("keyup",function(key){
        if(key.keyCode==13) {
            allHeaderSearch();
        }
    });
    $('#allMenu2 > ol > li').click(function(){
        $('#allMenu2 > ol > li > ol').slideUp();
        if ($(this).children('#allMenu2 > ol > li > ol').is(':hidden')){
            $(this).children('#allMenu2 > ol > li > ol').slideDown();
        } else{
            $(this).children('#allMenu2 > ol > li > ol').slideUp();
        }
    });
});
$(function() {
    $(window).scroll(function() {
        if ($(this).scrollTop() > 200) {
            $('#toTop').fadeIn();
            // $('#toTop').css('left', $('#sidebar').offset().left);
        } else {
            $('#toTop').fadeOut();
        }
    });

    $("#toTop").click(function() {
        $('html, body').animate({
            scrollTop : 0
        }, 400);
        return false;
    });
});