function pagingCalc(pageCase) {
    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    let keyword = urlParam.get("keyword");
    let country = urlParam.get("country");
    let no = urlParam.get("no");
    let totalPage = $('.totalPage').val();
    if(pageCase === -3) {
        location.href = "/tour/tourList?no=1&keyword="+keyword+"&country="+country;
    } else if(pageCase === 0) {
        let toUrl = "/tour/tourList?no=";
        toUrl += totalPage;
        toUrl += "&keyword="+keyword+"&country="+country;
        location.href = toUrl;
    } else if(pageCase === -1) {
        let toUrl = "/tour/tourList?no=";
        toUrl += Math.floor((no-1) / 5) * 5;
        toUrl += "&keyword="+keyword+"&country="+country;
        location.href = toUrl;
    } else if(pageCase === -2) {
        let toUrl = "/tour/tourList?no=";
        toUrl += Math.ceil(no / 5) * 5 + 1;
        toUrl += "&keyword="+keyword+"&country="+country;
        location.href = toUrl;
    } else {
        let toUrl = "/tour/tourList?no=";
        toUrl += pageCase;
        toUrl += "&keyword="+keyword+"&country="+country;
        location.href = toUrl;
    }

}
$(document).ready( function() {

    $(".tourListSearch").focus();
    $(".tourListSearch").on("keyup",function(key){
        if(key.keyCode==13) {
            tourListSearch();
        }
    });
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;

    // 완성형 아닌 한글 정규식
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;

    $(".tourListSearch").on("focusout", function() {
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

    const urlObject = new URL(decodeURI(window.location.href));
    const urlParam = urlObject.searchParams;
    let keyword = urlParam.get("keyword");
    let country = urlParam.get("country");
    let no = urlParam.get("no");
    let category = urlParam.get("category");
    let totalPage = $('.totalPage').val();
    let pageRange = $('.pageRange').val();
    let lastRange = $('.lastRange').val();
    let totalPageCalc = $('.totalPageCalc').val();

    $('.tourListSearch').val(keyword);
    if($('.option1').val(country).length > 0) {
        $('.option1').val(country);
    } else {
        $('.option1').val("지역");
    }
    if($('.option2').val(category).length > 0) {
        $('.option2').val(category);
    } else {
        $('.option2').val("분류");
    }


    /* 페이징 로직 */
    $('.paging').append("<button class=\"firstPage\" onclick=\"pagingCalc(-3)\"> << </button>");
    $('.paging').append("<button class=\"prevPage\" onclick=\"pagingCalc(-1)\"> < </button>");
    if(Number(no) > lastRange) {
        for(let i = parseInt((no-1) / 5) * 5 + 1; i <= totalPage; i++) {
            $('.paging').append("<button onclick=\"pagingCalc(" + i + ")\">" + i + "</button>");
            if($('.paging button').last().text() == no) {
                $('.paging button').last().attr("disabled", true);
                $('.paging button').last().css("color", "#222");
                $('.paging button').last().css("text-decoration", "underline");
            }
        }
    } else {
        for(let i = parseInt((no-1) / 5) * 5 + 1; i <= parseInt((no-1) / 5) * 5 + 5; i++) {
            $('.paging').append("<button onclick=\"pagingCalc(" + i + ")\">" + i + "</button>");
            if($('.paging button').last().text() == no) {
                $('.paging button').last().attr("disabled", true);
                $('.paging button').last().css("color", "#222");
                $('.paging button').last().css("text-decoration", "underline");
            }
        }
    }
    $('.paging').append("<button class=\"nextPage\" onclick=\"pagingCalc(-2)\"> > </button>");
    $('.paging').append("<button class=\"lastPage\" onclick=\"pagingCalc(0)\"> >> </button>");

    if(Number(no) == 1) {
        $('.firstPage').attr("disabled", true);
    }

    if(Number(no) <= 5) {
        $('.prevPage').attr("disabled", true);
    }

    if(Number(no) > lastRange) {
        $('.nextPage').attr("disabled", true);
    }

    if(Number(no) == totalPage) {
        $('.lastPage').attr("disabled", true);
    }





    /* 불러오기 */
    let getUrl = "/tour/getTourList?no=" + no + "&keyword=" + keyword + "&country=" + country + "&category=" + category;
    $.ajax({
        type:"get",
        url:getUrl,
        dataType:"json",
        success: function(data){
            for(let tourInfo of data) {
                $('.tourBoard').last().append("<div class=\"tourBoardUnit\">");
                let url = "<button onclick=\"location.href='/tour/tourDetail?no=" + tourInfo.tourCode + "\'\">";
                $('.tourBoardUnit').last().append(url);
                $('.tourBoardUnit button').last().append("<img class=\"tourBoardUnitThumbnail\">");
                if(tourInfo.tourPhoto.length > 0) {
                    $('.tourBoardUnitThumbnail').last().prop("src", tourInfo.tourPhoto);
                } else {
                    $('.tourBoardUnitThumbnail').last().prop("src", "/images/dogTour.png");
                    $('.tourBoardUnitThumbnail').last().prop("title", "대체 이미지");
                }

                $('.tourBoardUnit button').last().append("<div class=\"tourBoardUnitDesc\"></div>");
                $('.tourBoardUnitDesc').last().append("<div class=\"locationName\"></div>");
                $('.locationName').last().append(tourInfo.tourTitle);
                $('.tourBoardUnitDesc').last().append("<img class=\"locationLoc\" src=\"/images/location.png\" />");
                $('.tourBoardUnitDesc').last().append("<div class=\"locTextUp\"></div>");
                $('.locTextUp').last().append(tourInfo.addr);
                $('.locTextUp').last().prop("title", tourInfo.addr);
                $('.tourBoardUnitDesc').last().append("<img class=\"telNumber\" src=\"/images/phone.png\" />");
                $('.tourBoardUnitDesc').last().append("<div class=\"telNums\">\</div>");
                $('.telNums').last().append(tourInfo.tel);
                $('.telNums').last().prop("title", tourInfo.tel);
            }
        },
        error:function(){
            // console.log("통신에러3");
        }
    });
});
function tourListSearch() {
    let keyword = $('.tourListSearch').val();
    let country = $('.option1 option:selected').val();
    let category = $('.option2 option:selected').val();
    let no = $('.noTour').val();
    let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
    let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
    keyword = keyword.replace(replaceChar, "");
    keyword = keyword.replace(replaceNotFullKorean, "");
    location.href = "/tour/tourList?no=1&keyword="+keyword+"&country="+country+"&category="+category;
}