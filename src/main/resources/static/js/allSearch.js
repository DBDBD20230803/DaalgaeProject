$(document).ready( function() {

    // 검색어 가져오기
    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    $('.allSearchBox').val(urlParam.get("keyword"));

    // 옵션 선택
    $('.option3').val(urlParam.get("category1"));
    toOption3();
    $('.option2').val(urlParam.get("category2"));
    toOption2();
    $('.option1').val(urlParam.get("category3"));

    $('.option3').change(function () {
        toOption3();
    });
    $('.option2').change(function () {
        toOption2();
    });


    // 검색 관련
    $('.allSearchBox').focus();
    $(".allSearchBox").on("keyup",function(key){
        if(key.keyCode==13) {
            allSearch();
        }
    });

    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;

    // 완성형 아닌 한글 정규식
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;

    $(".allSearchBox").on("focusout", function() {
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
});

function allSearch() {
    let keyword = $('.allSearchBox').val();
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
    keyword = keyword.replace(replaceChar, "");
    keyword = keyword.replace(replaceNotFullKorean, "");
    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    let category1 = $('.option3').val();
    let category2 = $('.option2').val();
    let category3 = $('.option1').val();
    location.href = "/allSearch?keyword="+keyword+"&category1="+category1+"&category2="+category2+"&category3="+category3;
}

function toOption3() {
    $('.option2').empty();
    switch ($('.option3').val()) {
        case "전체":
            $('.option2').append("<option disabled selected>중분류</option>");
            $('.option2').append("<option value=\"전체\">전체</option>");
            $('.option1').empty();
            $('.option1').append("<option disabled selected>소분류</option>");
            $('.option1').append("<option value=\"전체\">전체</option>");
            $('.option2').val("전체");
            $('.option1').val("전체");
            break;
        case "여행지":
            $('.option2').append("<option disabled selected>중분류</option>");
            $('.option2').append("<option value=\"전체\">전체</option>");
            $('.option2').append("<option value=\"관광지\">관광지</option>");
            $('.option2').append("<option value=\"숙박시설\">숙박시설</option>");
            $('.option2').append("<option value=\"식당 및 카페\">식당 및 카페</option>");
            $('.option2').append("<option value=\"체험 활동\">체험 활동</option>");
            $('.option2').append("<option value=\"동물병원\">동물병원</option>");
            $('.option1').empty();
            $('.option1').append("<option disabled selected>소분류</option>");
            $('.option1').append("<option value=\"전체\">전체</option>");
            $('.option1').append("<option value=\"수도권\">수도권</option>");
            $('.option1').append("<option value=\"강원도\">강원도</option>");
            $('.option1').append("<option value=\"충청도\">충청도</option>");
            $('.option1').append("<option value=\"경상도\">경상도</option>");
            $('.option1').append("<option value=\"전라도\">전라도</option>");
            $('.option1').append("<option value=\"제주도\">제주도</option>");
            $('.option2').val("전체");
            $('.option1').val("전체");
            break;
        case "게시판":
            $('.option2').append("<option disabled selected>중분류</option>");
            $('.option2').append("<option value=\"전체\">전체</option>");
            $('.option2').append("<option value=\"자유게시판\">자유게시판</option>");
            $('.option2').append("<option value=\"자랑갤러리\">자랑갤러리</option>");
            $('.option2').append("<option value=\"공지사항\">공지사항</option>");
            $('.option1').empty();
            $('.option1').append("<option disabled selected>소분류</option>");
            $('.option1').append("<option value=\"전체\">전체</option>");
            $('.option2').val("전체");
            $('.option1').val("전체");
            break;
    }
}
function toOption2() {
    switch ($('.option2').val()) {
        case "전체":
            if($('.option3').val() =='게시판') {
                $('.option1').empty();
                $('.option1').append("<option disabled selected>소분류</option>");
                $('.option1').append("<option value=\"전체\">전체</option>");
                $('.option1').val("전체");
            }
            break;
        case "자유게시판":
            $('.option1').empty();
            $('.option1').append("<option disabled selected>소분류</option>");
            $('.option1').append("<option value=\"전체\">전체</option>");
            $('.option1').append("<option value=\"자유\">자유</option>");
            $('.option1').append("<option value=\"먹거리\">먹거리</option>");
            $('.option1').append("<option value=\"용품\">용품</option>");
            $('.option1').append("<option value=\"정보\">정보</option>");
            $('.option1').val("전체");
            break;
        case "자랑갤러리":
            $('.option1').empty();
            $('.option1').append("<option disabled selected>소분류</option>");
            $('.option1').append("<option value=\"전체\">전체</option>");
            $('.option1').val("전체");
            break;
        case "공지사항":
            $('.option1').empty();
            $('.option1').append("<option disabled selected>소분류</option>");
            $('.option1').append("<option value=\"전체\">전체</option>");
            $('.option1').append("<option value=\"공지\">공지</option>");
            $('.option1').append("<option value=\"이벤트\">이벤트</option>");
            $('.option1').val("전체");
            break;
    }
}