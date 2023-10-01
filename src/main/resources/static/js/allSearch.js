$(document).ready( function() {

    // 검색어 가져오기
    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    $('.allSearchBox').val(urlParam.get("keyword"));

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

    /* 보여줄 항목 */
    if($('.option3').val() == '전체') {
        console.log(1);
    } else {
        console.log(2);
    }

});

function allSearch() {
    let keyword = $('.allSearchBox').val();
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
    keyword = keyword.replace(replaceChar, "");
    keyword = keyword.replace(replaceNotFullKorean, "");
    location.href = "/allSearch?keyword="+keyword;
}

function allSearchDetail(sort) {
    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    let keyword = urlParam.get("keyword");
    switch (sort) {
        case 1:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=분류"
            break;
        case 2:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=관광지"
            break;
        case 3:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=숙박시설"
            break;
        case 4:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=식당 및 카페"
            break;
        case 5:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=체험 활동"
            break;
        case 6:
            location.href = "/tour/tourList?no=1&keyword=" + keyword + "&country=지역&category=동물병원"
            break;
        case 7:
            location.href = "/board/freeBoard?currentPage=1&searchCondition=postTitle&searchValue=" + keyword;
            break;
        case 8:
            location.href = "/board/annoBoard?currentPage=1&searchCondition=postTitle&searchValue=" + keyword;
            break;
        case 9:
            location.href = "/board/boastBoard?currentPage=1&searchCondition=postTitle&searchValue=" + keyword;
            break;
    }
}