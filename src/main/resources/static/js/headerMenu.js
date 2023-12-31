function allHeaderSearch() {
    let keyword = $('#headerSearchBox').val();
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
    keyword = keyword.replace(replaceChar, "");
    keyword = keyword.replace(replaceNotFullKorean, "");
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

    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;

    // 완성형 아닌 한글 정규식
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;

    $("#headerSearchBox").on("focusout", function() {
        let x = $(this).val();
        if (x.length > 0) {
            if (x.match(replaceChar) || x.match(replaceNotFullKorean)) {

                x = x.replace(replaceChar, "").replace(replaceNotFullKorean, "");
            }
            $(this).val(x);
        }
    }).on("keyup", function() {
        $(this).val($(this).val().replace(replaceChar, ""));
    });


    $('#allMenu2 > ol > li').click(function(){
        $('#allMenu2 > ol > li > ol').slideUp();
        if ($(this).children('#allMenu2 > ol > li > ol').is(':hidden')){
            $(this).children('#allMenu2 > ol > li > ol').slideDown();
        } else{
            $(this).children('#allMenu2 > ol > li > ol').slideUp();
        }
    });
    let boolRegist = window.location.href.includes("regist");
    let boolMyPage = window.location.href.includes("myPage");
    if(boolRegist || boolMyPage) {
        $('.margin16').css("margin-top", "0px");
    }

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